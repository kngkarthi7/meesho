package com.meeshoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meeshoapi.constants.ErrorConstants;
import com.meeshoapi.exception.ValidationException;
import com.meeshoapi.repository.IUserLoginDetailsRepository;
import com.meeshoapi.util.MeeshoUtil;
import com.meeshoapi.view.UserLoginDetailsView;

@Component
public class UserRegisterService {

	@Autowired
	MeeshoUtil meeshoUtil = null;

	@Autowired
	IUserLoginDetailsRepository userLDRepo = null;

	public void handleRegisterRequest(String username, String password) throws ValidationException {
		checkValidUserName(username);
		checkValidPassword(password);
		checkUserAlreadyExist(username);
		System.out.println("Basic Checks Complete");
		UserLoginDetailsView uldView = new UserLoginDetailsView(username, password);
		userLDRepo.save(uldView);
	}

	private void checkValidPassword(String password) throws ValidationException {
		boolean isValidPassword = meeshoUtil.isValidPassword(password);
		if (!isValidPassword) {
			throw new ValidationException(ErrorConstants.ERROR_CODE_INVALID_PASSWORD);
		}
	}

	private void checkValidUserName(String username) throws ValidationException {
		boolean isValidUsername = meeshoUtil.checkIsValidEmail(username);
		if (!isValidUsername) {
			throw new ValidationException(ErrorConstants.ERROR_CODE_INVALID_USERNAME);
		}
	}
	
	private void checkUserAlreadyExist(String username) throws ValidationException {
		List<UserLoginDetailsView> uldList = userLDRepo.findByUsername(username);
		if(uldList != null && uldList.size() > 0) {
			throw new ValidationException(ErrorConstants.ERROR_CODE_USER_ALREADY_REGISTERED);
		}
	}
}
