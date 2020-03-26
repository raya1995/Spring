package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.services.IAdService;

@RestController
public class RestControlAd {
	@Autowired
	IAdService iadService;

	// localhost:8081/SpringMVC/servlet/retrieve-all-ads
	//GET
	@GetMapping("/retrieve-all-ads") 
	@ResponseBody 
	public List<Ad> getAds() { 
		List<Ad> list = iadService.retrieveAllAds(); 
		return list;  } 

	// localhost:8081/SpringMVC/servlet/getAdById-ad/{ad-id} 
	//GET
	@GetMapping("/getAdById-ad/{ad-id}") 
	@ResponseBody  
	public Ad getAdById
	(@PathVariable("ad-id") int adId) {
		return iadService.getAdById(adId);} 

	// Ajouter AD : 
	// localhost:8081/SpringMVC/servlet/add-ad
	//POST
	/*{
    "kindofgood": null,
    "comments": [],
    "multimedias": null,
    "location": null,
    "description": null,
    "area": 0,
    "idAd": 7,
    "adDate": null,
    "success": null,
    "score": 0,
    "viewsNumber": 0
}*/
	@PostMapping("/add-ad") 
	@ResponseBody 
	public Ad addAd(@RequestBody Ad ad) { 
		iadService.addAd(ad); 
		return ad; }

	// /localhost:8081/SpringMVC/servlet/remove-ad/{ad-id} 
	//DELETE
	@DeleteMapping("/remove-ad/{ad-id}") 
	@ResponseBody 
	public void removeAd(@PathVariable("ad-id") int adId) { 
		iadService.deleteAd(adId);}  

	//////////////////////////////////////////////////////////COMMENT/////////////////////////////////////////////////////////////////////////////////////
	// Ajouter Comment : 
	// localhost:8081/SpringMVC/servlet/add-comment
	//POST

	@PostMapping("/add-comment") 
	@ResponseBody 
	public Comment addComment(@RequestBody Comment comment) { 
		iadService.addComment(comment); 
		return comment; }

	// localhost:8081/SpringMVC/servlet/modify-ad  
	//PUT
	@PutMapping("/modify-ad") 
	@ResponseBody 
	public Ad modifyAd(@RequestBody Ad ad) 
	{ 	return iadService.updateAd(ad); }

	// /localhost:8081/SpringMVC/servlet/remove-comment/{comment-id} 
	//DELETE
	@DeleteMapping("/remove-comment/{comment-id}") 
	@ResponseBody 
	public void removeComment(@PathVariable("comment-id") int commentId) { 
		iadService.deleteComment(commentId);}  

	// localhost:8081/SpringMVC/servlet/modify-comment  
	//PUT
	@PutMapping("/modify-comment") @ResponseBody 
	public Comment modifyComment(@RequestBody Comment comment) { 
		return iadService.UpdateComment(comment); }


	// http://localhost:8081/SpringMVC/servlet/AssignCommentToanAd/1/1
	@PutMapping(value = "/AssignCommentToanAd/{idcomment}/{idad}") 
	public void AssignCommentToanAd(@PathVariable("idcomment")int CommentId, @PathVariable("idad")int AdId) {
		iadService.AssignCommentToanAd(CommentId, AdId); 
	}



	// http://localhost:8081/SpringMVC/servlet/getAllCommentsByAd/1
	@GetMapping(value = "getAllCommentsByAd/{idad}")
	@ResponseBody
	public List<String> getAllCommentsByAd (@PathVariable("idad") int AdId) {
		return iadService.getAllCommentsByAd(AdId);
	}

	// http://localhost:8081/SpringMVC/servlet/countComments
	@GetMapping(value = "/countComments")
	@ResponseBody
	public int countComments() {
		return iadService.countComments();
	}


	// http://localhost:8081/SpringMVC/servlet/nbrLike
	@GetMapping(value = "/nbrLike")
	@ResponseBody
	public int nbrLike(int IdComment)  {
		return iadService.nbrLike(IdComment);
	}


	// http://localhost:8081/SpringMVC/servlet/getAdWithBestLikesOnCommentsJPQL/100
	@GetMapping(value = "/getAdWithBestLikesOnCommentsJPQL")
	@ResponseBody
	public List<Ad>getAdWithBestLikesOnCommentsJPQL()  {
		return iadService.getAdWithBestLikesOnCommentsJPQL();
	}


	// http://localhost:8081/SpringMVC/servlet/maxnblike
	@GetMapping(value = "/maxnblike")
	@ResponseBody
	public int maxnblike()  {
		return iadService.maxnblike();
	}



	// http://localhost:8081/SpringMVC/servlet/succes
	@PutMapping(value = "/succes")
	@ResponseBody
	public boolean succes() {
		return iadService.succes();
	}

	// http://localhost:8081/SpringMVC/servlet/AVG_nbcomment
	@GetMapping(value = "/AVG_nbcomment")
	@ResponseBody
	public double AVG_nbcomment() {
		return iadService.AVG_nbcomment();
	}

	// http://localhost:8081/SpringMVC/servlet/BlockCommentsWithInsultingWords
	@PutMapping(value = "/BlockCommentsWithInsultingWords")
	@ResponseBody
	public boolean BlockCommentsWithInsultingWords()  {
		return iadService.BlockCommentsWithInsultingWords();
	}

	// http://localhost:8081/SpringMVC/servlet/ScoreIncrement
	@PutMapping(value = "/ScoreIncrement")
	@ResponseBody
	public void ScoreIncrement() {
		iadService.ScoreIncrement();
	}

	// http://localhost:8081/SpringMVC/servlet/AdsForToday
	@GetMapping(value = "/AdsForToday")
	@ResponseBody
	public int AdsForToday() {
		return iadService.AdsForToday();
	}
}

