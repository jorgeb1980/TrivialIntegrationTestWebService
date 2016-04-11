package service.integration;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * This class implements a fake customers CRUD
 */
@Path("/customer")
public class CustomerService {

	//-------------------------------------------------------------------
	// Service constants
	
	// Date formatter
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//-------------------------------------------------------------------
	// Service properties
	
	// Array of customers for search purposes
	private Customer[] customers = new Customer[]{
		new Customer("Jersey, John", parseDate("1980-10-01"), new BigDecimal(0.0f)),
		new Customer("Jones, Jane", parseDate("1965-05-12"), new BigDecimal(0.0f)),
		new Customer("Jacobson, Jame", parseDate("1990-11-21"), new BigDecimal(0.0f))
	};
	
	//-------------------------------------------------------------------
	// Service methods
	
	// Utility to parse dates
	private static Date parseDate(String string) {
		Date ret = null;
		try {
			ret = sdf.parse(string);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	// This method will return customer information by name
	
	@GET
	@Path("/{name}")
	public Response getCustomers(@PathParam("name") String name) {
		List<Customer> ret = new LinkedList<>();
		
		System.err.println("--> customers");
		
		String test = name.toLowerCase();
		for (Customer c: customers) {
			if (c.getName().toLowerCase().contains(test)) {
				ret.add(c);
			}
		}
		
		String output = new ObjectListWrapper<Customer>("customers", ret).toString();		
		return Response.status(200).entity(output).build();
	}
}
