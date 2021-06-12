package in.nit.raghu;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class TestProductRestController {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Disabled
	void testSaveProduct() throws Exception {
		// 1. create dummy object/request
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/product/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"pid\":\"7\",\"pname\":\"Jbl speaker\",\"price\":\"15000.0\"}");
	   // 2. execute request and get result
			MvcResult result = mockMvc.perform(request).andReturn();
 		// 3. read response
		MockHttpServletResponse response = result.getResponse();
		// 4. Test using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("Product Saved..")) {
			fail("Product Data not saved..Please check test class");
		}
	}
	
	@Test
	@Disabled
	public void testGetAllProduct() throws Exception {
		
		//1.Create One dummy object/request
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/product/all")
				.contentType(MediaType.APPLICATION_JSON);
		
		//2.execute request and get result 
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3.read response
		MockHttpServletResponse response = result.getResponse();
		
		//4.Test using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if(response.getContentAsString().isEmpty()) {
			fail("product records not available");
		}
	}
	
	@Test
	@Disabled
	public void testGetOneProduct() throws Exception {
		
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/product/one/2")
				.contentType(MediaType.APPLICATION_JSON);
		
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		
		//4. test using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
	}
	
	@Test
	@Disabled
	public void testGetOneProductNotExist() throws Exception
	{
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/product/one/30")
				.contentType(MediaType.APPLICATION_JSON);
		
		//2. Execute request and get result
		MvcResult result  = mockMvc.perform(request).andReturn();
		
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert method
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if(!response.getContentAsString().equals("Product Not Found")) {
			fail("may be product data not found...");
		}
	}
	
	@Test
	@Disabled
	public void testDeleteOneProduct() throws Exception {
		
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/product/delete/one/2");
		
		//2. Execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		
		//4. test using assert
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("product deleted", response.getContentAsString());	
	}
	
	@Test
	@Disabled
	public void testDeleteOneProductNotExist() throws Exception {
		
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/product/delete/one/30");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		
		//4. test using assert method
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		assertEquals("Product Not Found", response.getContentAsString());
	}
	
	@Test
	@Disabled
	public void testUpdateProduct() throws Exception {
		
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/product/update/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pid\": 2,\"pname\": \"pen-pen\", \"price\": 100}");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
				
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		
		//4. test using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("updated", response.getContentAsString());		
	}
	
	@Test
	public void testUpdateProductNotExist() throws Exception {
		
		//1. create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/product/update/30")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pid\": 30,\"pname\": \"pen-pen\", \"price\": 100}");
		
		//2. execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
				
		//3. read response
		MockHttpServletResponse response = result.getResponse();
		
		//4. test using assert method
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		assertEquals("product not found", response.getContentAsString());
	}
	
}