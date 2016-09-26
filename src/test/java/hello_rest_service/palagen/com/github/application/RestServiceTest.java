package hello_rest_service.palagen.com.github.application;

import hello_rest_service.palagen.com.github.dao.ContactDao;
import hello_rest_service.palagen.com.github.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RestServiceTest extends MainControllerTest {

    @Autowired
    private ContactDao contactDao;

    @Test
    public void testContactListIsEmpty() {

        List<Contact> contactList = contactDao.findAll();
        Assert.assertFalse(contactList.isEmpty());

    }

    @Test
    public void testGetContactsBySqlRestrictionNotContain() {

        List<Contact> contactList = contactDao.getContactsBySqlRestriction("^A.$");
        Assert.assertFalse(contactList.contains("Alexandra"));

    }

    @Test
    public void testGetContactsByRegExNotContain() {

        List<Contact> contactList = contactDao.getContactsByRegEx("^A.$");
        Assert.assertFalse(contactList.contains("Alexandra"));

    }

    @Test
    public void testGetContactsBySqlRestrictionContain() {

        List<Contact> contactList = contactDao.getContactsBySqlRestriction("A.$");
        Assert.assertFalse(contactList.contains("Alexandra"));

    }

    @Test
    public void testGetContactsByRegExContain() {

        List<Contact> contactList = contactDao.getContactsByRegEx("A.$");
        Assert.assertTrue(contactList.contains("Alexandra"));

    }

}
