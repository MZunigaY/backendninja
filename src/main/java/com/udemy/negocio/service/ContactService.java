package com.udemy.negocio.service;

import java.util.List;

import com.udemy.persistencia.entity.Contact;
import com.udemy.presentacion.model.ContactModel;

public interface ContactService {

	public ContactModel addContact(ContactModel contactModel);
	
	public List<ContactModel> listAllContacts();
	
	public Contact findContactById(int id);
	
	public ContactModel findContactByIdModel(int id);
	
	public void removeContact(int id);
	
}
