package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.repository.AdRepository;

@Service
public class AdServiceImpl implements IAdService {
	@Autowired
	AdRepository adRepository;

	@Override
	public int ajouterAd(Ad ad) {
		adRepository.save(ad);
		return 0;
		
	}

}
