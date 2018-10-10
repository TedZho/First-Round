package com.h2rd.refactoring.usermanagement.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.h2rd.refactoring.usermanagement.User;

/**
 * 
 * @author
 *
 */
public class UserUtils {
	
	public static int validateUserEmail(User user) {
		int flag = ErrorStatus.NO_ERROR.getStatus();
		
		if (!StringUtils.isNotBlank(user.getEmail())) {
			flag = ErrorStatus.EMAIL_REQUIRED_ERROR.getStatus();
		} else {
			// Validate the email format
			String regEx1 = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
	        Pattern p = Pattern.compile(regEx1);
	        Matcher m = p.matcher(user.getEmail());
	        if (!m.matches())
	        	flag = ErrorStatus.EMAIL_FORMAT_ERROR.getStatus();
		}
		
		return flag;
	}

	public static int validateUserRoles(User user) {
		int flag = ErrorStatus.NO_ERROR.getStatus();
		// A user should have at least 1 role.
		if (user.getRoles() == null || (user.getRoles().size() == 0)) {
			flag = ErrorStatus.USER_ROLE_MIN_ERROR.getStatus();
		}
		
		return flag;
	}

}
