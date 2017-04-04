package com.samarthsoft.prabandhak.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserProfileController {
	
    @RequestMapping(value = "/viewprofile", method = RequestMethod.GET)
    public ModelAndView renderUserProfile(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	return new ModelAndView("");
    }
    
    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    public ModelAndView updateUserProfile(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	return new ModelAndView("user_profile");
    }
}