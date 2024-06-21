package com.cts.auth.util;

public class UserRole {

	public static Integer getRole(String name) {
		
		if(name.contains("admin")) {
			return 1;
		}
		return 0;
	}
}
