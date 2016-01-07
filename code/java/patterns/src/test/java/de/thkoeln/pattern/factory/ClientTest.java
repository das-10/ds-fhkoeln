package de.thkoeln.pattern.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClientTest{
	
	private Gift chritsmasGift;

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		ChristmasGift = new ChristmasGift();
//	}
	
	@Before
	public void setUpBeforeTest(){
		chritsmasGift = new ChristmasGift();
	}

//	@After
//	public void tearDownAfterTest() throws Exception {
//		chritsmasGift = null;
//	}

	@Test
	public void testVoucherChristmasValue() {
		assertTrue(chritsmasGift.createVoucher().getValue() > 20);
		assertTrue(new String("FatherChristmas").equals(
				chritsmasGift.createCommercialGift().getName()));
	}
	
	@Test
	public void testCommercialGiftChristmasValue() {
		assertTrue(new String("FatherChristmas").equals(
				chritsmasGift.createCommercialGift().getName()));
	}

}
