package com.samarthsoft.prabandhak.webapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentDashboardController {

	@RequestMapping(value = "/studentdashboard", method = RequestMethod.GET)
    public ModelAndView showStudentDashboard(HttpServletRequest request, ModelMap model) {
		return new ModelAndView("studentdashboard",model);
	}
}
