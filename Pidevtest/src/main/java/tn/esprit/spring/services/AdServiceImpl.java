package tn.esprit.spring.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import tn.esprit.spring.PidevtestApplication;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.CommentRepository;

@Service
public class AdServiceImpl implements IAdService {
	@Autowired
	AdRepository adRepository;
	@Autowired
	CommentRepository commentRepository;
	public static final Logger l = LogManager.getLogger(AdServiceImpl.class);


	@Override
	public Ad addAd(Ad ad) {
		adRepository.save(ad);		
		return ad;
	}

	@Override
	public List<Ad> retrieveAllAds() {
		List<Ad> ads=(List<Ad>)adRepository.findAll();
		for (Ad ad : ads) {
			l.info("ad +++"+ad);	
		}
		return ads;
	}

	/*ou bien
	@Override
	public List<Ad> retrieveAllAds() {
		List<Ad> ads=(List<Ad>)adRepository.findAll();
		for (Ad ad : ads) {
			ad.getIdAd();
		}
		return ads;
	}*/

	@Override
	public void deleteAd(int id) {

		adRepository.delete(adRepository.findById(id).get());
	}

	@Override
	public Ad updateAd(Ad ad) {
		adRepository.save(ad);
		return ad;
	}



	@Override
	public List<String> getAllCommentsByAd(int AdId) {
		Ad aa = adRepository.findById(AdId).get();
		List<String> CommentsDescription = new ArrayList<>();

		for(Comment com : aa.getComments()){
			CommentsDescription.add(com.getDescriptionComment());	

		}

		return CommentsDescription;
	}

	@Override
	public Ad getAdById(int adId) {
		return adRepository.findById(adId).get();	

	}

	@Override
	public Comment addComment(Comment comment) {
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public void deleteComment(int commentId) {
		commentRepository.deleteById(commentId);

	}


	@Override
	public Comment UpdateComment(Comment comment) {
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public void AssignCommentToanAd(int CommentId, int AdId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countComments() {
		int max=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		for(Comment comments :com) {
			max++;
		}
		l.info(" you have "+ max + "comments");

		return max;

	}


	@Override
	public int nbrLike(int IdComment) {	
		int max1=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();		
		for(Comment aa : com ) {

			max1 +=aa.getNumberLikes();	 

		}

		l.info(" you have "+ max1 + "comments");

		return max1;

	}

	@Override
	public List<Ad>getAdWithBestLikesOnCommentsJPQL() {

		return adRepository. getAdWithBestLikesOnCommentsJPQL();
	}

	//max des nbres de likes dans commentaires
	@Override
	public int maxnblike() {	
		int k=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();		
		for(Comment aa : com ) {

			if(aa.getNumberLikes()> k) {
				k=aa.getNumberLikes();	  
			}  
		}

		l.info(" you have "+k+ " comments");
		return k;

	}


	public boolean succes() {

		List<Comment> com=(List<Comment>) commentRepository.findAll();
		List<Ad> zz=(List<Ad>) adRepository.findAll();	

		for(Comment aa :com) {
			for(Ad ee :zz) {
				if(aa.getNumberLikes()>=100 && ee.getViewsNumber()>=100) {

					ee.setSuccess(true); 
					adRepository.save(ee);

					l.info("True");

				} 
				else  {ee.setSuccess(false);
				l.info("False");}
			}


		}return true; 

	}

	//moyenne nombre commantaires par ans
	public double AVG_nbcomment() {	
		int A=0;
		double b=0;
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		for(Comment comments :com) {
			A++;
		}
		b=(A*360)/100;
		l.info(" you have "+ b + "comments");

		return b;
	}
	
	public void ScoreIncrement()  {
		List<Ad> zz=(List<Ad>)adRepository.findAll();
		int b=0;  
		for(Ad aa :zz) {

			if(aa.getViewsNumber()>=1000) {
				b = aa.getScore()+100;
				aa.setScore(b);
				adRepository.save(aa);
			}

		} 
	}

	//total des ads par jour
	@Override
	public int AdsForToday() {
		List<Ad> ads=(List<Ad>)adRepository.findAll();
			int nbr_ads_for_day=0;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date();
			l.info("******" + dateFormat.format(date));
			for(Ad a:ads) {	
				if ((a.getAdDate().getDay()== date.getDay() ) && (a.getAdDate().getMonth()== date.getMonth()) && (a.getAdDate().getYear()== date.getYear())) {
				nbr_ads_for_day++;
				
				l.info("******" + dateFormat.format(date) + a);
			
			}} 
			
			l.info("you have "+ nbr_ads_for_day +" ads today" );
			return nbr_ads_for_day;	
		}
		
	
	
	//Bloquer le comments avec des mots insultants	
	
	public boolean BlockCommentsWithInsultingWords()  {
		 
		List<Comment> com=(List<Comment>) commentRepository.findAll();
		List<String> c = new ArrayList<>();
		java.nio.file.Path path= Paths.get("C:\\Users\\user\\Desktop\\4infoB-S2\\pidev\\Full_Bad_Word.txt");
		 try {
			for(String line :Files.readAllLines(path)) {
				c.add(line);
			
			 }
			System.out.println("\n");
		} catch (IOException e) {
		}
		 
		for(Comment aa :com) {
				
			if(c.contains(aa.getDescriptionComment()) && c.contains(com)) {
						aa.setIsBlocked(false);
				commentRepository.save(aa);
				//return true;
			} else 

				aa.setIsBlocked(true);
			commentRepository.save(aa);

		}return true;

	}
	}

   




