package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.services.IAdService;


@Controller
public class IAdControllerImpl {
	@Autowired
	IAdService iadservice;
	
	public int ajouterAd(Ad ad)
	{
		iadservice.ajouterAd(ad);
		return ad.getIdAd();
	}
}
