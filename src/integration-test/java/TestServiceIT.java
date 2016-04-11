import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class implements a simple test against the deployed service 
 */
public class TestServiceIT {

	Properties properties = new Properties();
	
	@Before
	public void announceIntegrationTest() {
		try {InputStream is = TestServiceIT.class.getClassLoader().getResourceAsStream("jetty.properties");
			properties.load(is);
			System.out.println(">>>>>>>>>>>> RUNNING INTEGRATION TESTING -> " + properties.getProperty("jetty.port"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private HttpResponse queryJetty(String parameter) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		// No context path, port stored in pom.xml
		URI uri = new URI("http://localhost:"
				+ properties.getProperty("jetty.port")
				+ "/rest-api/customer/"
				+ parameter);
		System.out.println("testing " + uri);
		HttpUriRequest request = RequestBuilder.get(uri).build();
		return client.execute(request);
	}
	
	private JSONObject parseResponse(HttpResponse response) throws Exception {
		JSONObject ret = null;
		HttpEntity entity = response.getEntity();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		entity.writeTo(baos);
		ret = (JSONObject) new JSONTokener(new ByteArrayInputStream(baos.toByteArray())).nextValue();
		return ret;
	}
	
	@Test
	public void testCustomers1() {
		try {
			
			HttpResponse response = queryJetty("er");
			// Check that it has not failed
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
			JSONObject obj = parseResponse(response);
			// Make some assumptions on the json object.  It should contain a single record under
			//	its 'customers' array
			JSONArray array = obj.getJSONArray("customers");
			Assert.assertEquals(1, array.length());
			JSONObject object = array.getJSONObject(0);
			Assert.assertEquals("Jersey, John", object.get("name"));			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomers2() {
		try {
			HttpResponse response = queryJetty("e");
			// Check that it has not failed
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
			JSONObject obj = parseResponse(response);
			// Make some assumptions on the json object.  It should contain a single record under
			//	its 'customers' array
			JSONArray array = obj.getJSONArray("customers");
			Assert.assertEquals(3, array.length());			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
