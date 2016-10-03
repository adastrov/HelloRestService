package hello_rest_service.palagen.com.github.service;

import hello_rest_service.palagen.com.github.dto.ContactDTO;
import hello_rest_service.palagen.com.github.model.Contact;

import java.util.List;

public interface ContactService {

    List<ContactDTO> findContactsByRegExp(String range);
    List<Contact> findAll();

}
