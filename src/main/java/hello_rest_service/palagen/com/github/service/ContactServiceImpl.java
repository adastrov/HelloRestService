package hello_rest_service.palagen.com.github.service;

import hello_rest_service.palagen.com.github.dao.ContactDao;
import hello_rest_service.palagen.com.github.dto.ContactDTO;
import hello_rest_service.palagen.com.github.model.Contact;
import hello_rest_service.palagen.com.github.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    public ContactServiceImpl() {
    }

    @Override
    public List<ContactDTO> findContactsByRegExp(String range) {

        List<Contact> contactList = contactDao.findAll();

        List<ContactDTO> contactsDTOList = Transformer.transformContactListToContactDTOList(contactList);

        return filterListByRegExp(contactsDTOList, range);

    }

    @Override
    public List<ContactDTO> findAll() {

        List<Contact> contactList = contactDao.findAll();

        return Transformer.transformContactListToContactDTOList(contactList);

    }

    private List<ContactDTO> filterListByRegExp(List<ContactDTO> list, String regex) {

        return list.stream().filter(s ->
                !s.getContactName().matches(regex)).collect(Collectors.toList());

    }

}
