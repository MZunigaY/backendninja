package com.udemy.presentacion.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udemy.negocio.service.ContactService;
import com.udemy.presentacion.model.ContactModel;
import com.udemy.util.Constantes;

@Controller
//@PreAuthorize("permitAll()")
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name="id", required=false) int id, Model model) {
		ContactModel contactModel = new ContactModel();
		if (id != 0) {
			contactModel = contactService.findContactByIdModel(id);
		}
		
		model.addAttribute("contactModel", contactModel);
		return Constantes.CONTACT_FORM;
	}
	
	@PostMapping("/addcontact")
	public ModelAndView addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, RedirectAttributes redirAtr) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD: addContact() -- PARAMS: "+contactModel.toString());
		mav.setViewName("redirect:/contacts/showcontacts");
		
		if(contactService.addContact(contactModel) != null)
			redirAtr.addFlashAttribute("result",1);
		else
			redirAtr.addFlashAttribute("result",2);
		
		return mav;
	}
	
	/*No mostraba el mensaje de nuevo contacto añadido porque no devolvia
	 * una vista, sino un redirect. Con esto se perdian los atributos añadidos*/
//	@PostMapping("/addcontact")
//	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel, Model model) {
//		LOG.info("METHOD: addContact() -- PARAMS: "+contactModel.toString());	
//		if(contactService.addContact(contactModel) != null)
//			model.addAttribute("result",1);
//		else
//			model.addAttribute("result",0);
//		
//		return "redirect:/contacts/showcontacts";
//	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(Constantes.CONTACTS);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		
		mav.addObject("contacts",contactService.listAllContacts());
		return mav;
	}
	
//	Debería hacerse como POST, no como GET. Haciendolo como POST se tiene que usar js, ajax en la parte de front
	@GetMapping("/removecontact")
	public ModelAndView removecontact(@RequestParam(name="id", required=true) int id) {
		System.out.println(" -- ESTE ES EL id USADO: -- "+id);
		contactService.removeContact(id);
		ModelAndView mav = new ModelAndView("redirect:/contacts/showcontacts");
		return mav;
	}
	
	
//	@GetMapping("/cancel")
//	public ModelAndView cancel() {
//		return new ModelAndView("");
//	}
	
//	@GetMapping("/contactForm")
//	public ModelAndView redirectContactForm() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("contactModel", new ContactModel());
//		mav.setViewName(Constantes.CONTACT_FORM);
//		return mav; 
//	}
	
}
