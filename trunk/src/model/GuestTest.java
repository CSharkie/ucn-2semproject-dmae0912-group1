package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuestTest {

	@Test
	public void testGuest() {
		Guest guest = new Guest();
		guest.setAddress("Aalborg");
		guest.setDiscount(10.0);
		guest.setEmail("toma@gmail.com");
		guest.setFirstName("Cristi");
		guest.setPassportNo(251625);
		guest.setPassword("1234567");
		guest.setPersonId(1);
		guest.setPhoneNo("50560177");
		guest.setSurName("Toma");
		assertEquals(1, guest.getPersonId(),0);
		assertEquals("Cristi", guest.getFirstName());
		assertEquals("Toma", guest.getSurName());
		assertEquals("Aalborg", guest.getAddress());
		assertEquals("50560177", guest.getPhoneNo());
		assertEquals("toma@gmail.com", guest.getEmail());
		assertEquals(251625, guest.getPassportNo(),0);
		assertEquals("1234567", guest.getPassword());
		assertEquals(10.0, guest.getDiscount(),0);
	}
	
	@Test
	public void testGetPassword() {
		Guest guest1 = new Guest();
		guest1.setPassword("1234567");
		assertEquals("1234567", guest1.getPassword());
	}

}
