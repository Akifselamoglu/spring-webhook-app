package com.example.demo.helper;

import com.example.demo.domain.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class PlaceholderHelperTest {
    PlaceholderHelper placeholderHelper;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        placeholderHelper = new PlaceholderHelper();
    }

    @Test
    public void messageProecessor_test(){
        String message = "Dear #CONTACT, Today ( #TODAY ) Bitcoin and many other subCoins fell down. Please checke the price #BITCOINPRICE";
        Contact contact = new Contact();
        contact.setName("KEMAL");
        String text = placeholderHelper.messageProecessor(message, contact);
        Assert.assertNotNull(text);
    }
}
