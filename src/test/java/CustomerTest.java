import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import service.integration.Customer;

/**
 * Unitary testing of Customer class
 */


public class CustomerTest {

	@Before
	public void announceUnitTest() {
		System.out.println(">>>>>>>>>>>> RUNNING UNIT TESTING");
	}
	
	@Test
	public void testCustomer() {
		final String NAME = "andrew jones";
		final Date BIRTHDAY = new Date();
		final BigDecimal DEBT = new BigDecimal(3.4f);
		
		Customer c = new Customer(NAME, BIRTHDAY, DEBT);
		
		Assert.assertEquals(NAME, c.getName());
		Assert.assertEquals(BIRTHDAY, c.getBirthday());
		Assert.assertEquals(DEBT, c.getDebt());
	}
}
