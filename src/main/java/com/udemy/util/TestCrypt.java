package com.udemy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		System.out.println(crypt.encode("usuario"));
	}

}
