package hello_rest_service.palagen.com.github.service;

import hello_rest_service.palagen.com.github.dao.ContactDao;
import hello_rest_service.palagen.com.github.dto.ContactDTO;
import hello_rest_service.palagen.com.github.model.Contact;
import hello_rest_service.palagen.com.github.util.Transformer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ejb.EJB;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Repository
public class ContactServiceImpl implements ContactService{

    @EJB
    private ContactDao contactDao;

    @Autowired
    private SessionFactory sessionFactory;

    public ContactServiceImpl() {
    }

    public ContactServiceImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

    }

    @Override
    public List<ContactDTO> findContactsByRegExp(String range) {

        List<Contact> contactList = contactDao.findAll();

        List<ContactDTO> contactsDTOList = Transformer.transformContactListToContactDTOlist(contactList);

        return filterListByRegExp(contactsDTOList, range);

    }

    @Override
    public List<Contact> findAll() {

        List<Contact> contactList = contactDao.findAll();
        return contactList;

    }

    private List<ContactDTO> filterListByRegExp(List<ContactDTO> list, String regex) throws PatternSyntaxException {

        return list.stream().filter(s ->
                !s.getContactName().matches(regex)).collect(Collectors.toList());

    }

}
