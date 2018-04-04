package com.udemy.persistencia.repository.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.udemy.persistencia.repository.ContactRepositoryCustom;

@Repository("contactRepositoryImpl")
public class ContactRepositoryImpl implements ContactRepositoryCustom, Serializable {

	private static final long serialVersionUID = 1L;

}
