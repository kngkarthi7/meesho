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
import com.meeshoapi.service.UserLoginService;

@RestController
@RequestMapping("useroperation")
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService = null;

	@GetMapping(value = "loginUser")
	public String loginUserService(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException, JsonProcessingException {
		try {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			System.out.println("Received username : " + username + " , password : " + password);
			userLoginService.handleLoginRequest(username, password);
		} catch (ValidationException validationExceptionMsg) {
			return validationExceptionMsg.getMessage();
		}

		return "Login Success";
	}
}
