package hello_rest_service.palagen.com.github.service;

import hello_rest_service.palagen.com.github.dto.ContactDTO;

import java.util.List;

public interface ContactService {

    List<ContactDTO> findContactsByRegExp(String range);
    List<ContactDTO> findAll();

}
