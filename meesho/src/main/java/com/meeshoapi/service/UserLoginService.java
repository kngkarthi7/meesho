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
public class UserLoginService {

	@Autowired
	MeeshoUtil meeshoUtil = null;
	
	@Autowired
	IUserLoginDetailsRepository userLDRepo = null;
	
	public void handleLoginRequest(String username, String password) throws ValidationException{
		checkUserAlreadyExist(username,password);
	}
	
	private void checkUserAlreadyExist(String username,String password) throws ValidationException {
		List<UserLoginDetailsView> uldList = userLDRepo.findByUsername(username);
		if(uldList == null || uldList.size() == 0) {
			throw new ValidationException(ErrorConstants.ERROR_CODE_USER_NOT_EXIST);
		}else if(!password.equals(uldList.get(0).getPassword())) {
			throw new ValidationException(ErrorConstants.ERROR_CODE_USER_NOT_EXIST);
		}
		
	}
}
