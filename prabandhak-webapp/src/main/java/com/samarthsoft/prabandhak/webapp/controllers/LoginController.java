package com.samarthsoft.prabandhak.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.PasswordEncoder;
import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.form.entities.LoginFormObject;
import com.samarthsoft.prabandhak.utilities.LoginControllerUtility;
import com.samarthsoft.prabandhak.webapp.security.CustomAuthenticationProvider;
import com.samarthsoft.prabandhak.webapp.validators.LoginFormValidator;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Controller
public class LoginController {
	
	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
	private LoginFormValidator loginFormValidator;
	@Autowired
	private LoginControllerUtility loginControllerUtility;
    
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView renderLoginForm(HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
        model.put("user", new LoginFormObject());
    	return new ModelAndView("login",model);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(@Valid LoginFormObject loginFormObject,BindingResult validationResult, HttpServletRequest request,
            HttpServletResponse response,ModelMap model) {
    	if(!validationResult.hasErrors()){
    	try{
    		Authentication auth = customAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginFormObject.getUserName(), PasswordEncoder.hashPassword(loginFormObject.getPassword())));
	    	LoginInformation loginInformation = loginControllerUtility.getLoginInformationByUserName(loginFormObject.getUserName());
	    	if(auth!=null && auth.isAuthenticated()){
	    		request.getSession().setAttribute("session", loginControllerUtility.getApplicationSession(loginInformation));
	    		return new ModelAndView("redirect:/dashboard.htm");
	    	}
    	} catch(Exception e) {
                if (e instanceof LockedException)
                	model.put("accountLocked", true);
                if (e instanceof AccountExpiredException)
                	model.put("accountExpired", true);
                if (e instanceof BadCredentialsException) {
                	//TODO:Save invalid login attempts
                	//TODO:If invalid login attempts are more than 5 then lock user account and reset the password as well
                	//TODO:Show password reset message on login page
                	model.put("invalidCredentials", true);
                }
            }
    	}
    	ValidatorCommon.checkAndAddFieldErrors(validationResult, model);
    	model.put("user", loginFormObject);
    	return new ModelAndView("login",model);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
    	binder.setValidator(loginFormValidator);
    }
}