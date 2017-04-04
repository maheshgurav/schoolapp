package com.samarthsoft.prabandhak.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.entities.User;
import com.samarthsoft.prabandhak.form.entities.LoginFormObject;

@Controller
public class LogoutController {
	private static Logger logger = LoggerFactory
			.getLogger(LogoutController.class);

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			request.getSession().invalidate();
			model.put("User", new LoginFormObject());
			return new ModelAndView("redirect:/login.htm", model);
		} catch (Exception ex) {
			model.put("error", ex);
			logger.error("", ex);
		}
		return new ModelAndView("error", model);
	}
}