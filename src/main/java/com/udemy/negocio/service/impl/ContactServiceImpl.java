package com.udemy.negocio.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.negocio.converter.ContactConverter;
import com.udemy.negocio.service.ContactService;
import com.udemy.persistencia.entity.Contact;
import com.udemy.persistencia.repository.ContactRepository;
import com.udemy.presentacion.model.ContactModel;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		return contactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<ContactModel> listaContactsModel = new ArrayList<ContactModel>();
		listaContactsModel = contactConverter.convertListContact2ContactModel(contactRepository.findAll());
		return listaContactsModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContact2ContactModel(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(contact != null) {
			ContactModel contactModel = contactConverter.convertContact2ContactModel(contact);
			System.out.println(" -- CONTACT A ELIMINAR: -- "+contactModel.toString());
			contactRepository.delete(contact);
			
		}
	}

}
