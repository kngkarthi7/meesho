package com.meeshoapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meeshoapi.exception.ValidationException;
import com.meeshoapi.repository.IUserLoginDetailsRepository;
import com.meeshoapi.service.UserRegisterService;

@RestController
@RequestMapping("useroperation")
public class UserRegisterController {

	@Autowired
	UserRegisterService userRegisterService = null;
	
	

	@GetMapping(value = "registerUser")
	public String registerUserService(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException, JsonProcessingException {
		try {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			System.out.println("Received username : " + username + " , password : " + password);
			userRegisterService.handleRegisterRequest(username, password);
			System.out.println("Operation Completed successfully");
		} catch (ValidationException validationExceptionMsg) {
			return validationExceptionMsg.getMessage();
		} 

		return "Success";
	}
}
