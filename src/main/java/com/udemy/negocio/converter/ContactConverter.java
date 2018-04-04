package com.udemy.negocio.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.udemy.persistencia.entity.Contact;
import com.udemy.presentacion.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModel2Contact(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		contact.setCity(contactModel.getCity());
		
		return contact;
	}
	
	public ContactModel convertContact2ContactModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());
		contactModel.setCity(contact.getCity());
		
		return contactModel;
	}
	
	public List<Contact> convertListContactModel2Contact(List<ContactModel> listaContactsModel) {
		List<Contact> listaContacts = new ArrayList<Contact>();
		for (ContactModel contactModel : listaContactsModel) {
			listaContacts.add(convertContactModel2Contact(contactModel));
		}	
		return listaContacts;
	}
	
	public List<ContactModel> convertListContact2ContactModel(List<Contact> listaContacts) {
		List<ContactModel> listaContactsModel = new ArrayList<ContactModel>();
		for (Contact contact : listaContacts) {
			listaContactsModel.add(convertContact2ContactModel(contact));
		}
		return listaContactsModel;
	}
}
