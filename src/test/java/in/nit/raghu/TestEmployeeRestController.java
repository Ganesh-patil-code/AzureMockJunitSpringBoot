package in.nit.raghu;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
public class TestEmployeeRestController {

	@Autowired
	private MockMvc mockMvc;


	@Disabled
	@Test
	public void testSaveEmployee() throws Exception {

		//1.  Create Dummy Request..
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/employee/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"empName\":\"SAM\",\"empSal\":3000.0}");

		//2.  execute request and ger result..
		MvcResult result = mockMvc.perform(request).andReturn();

		//3.  Read responce..
		MockHttpServletResponse response = result.getResponse();

		//4.  Test using assert method..
		//is status code is - 200
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("Employee Saved..")) {
			fail("SAVE EMPLOYEE NOT WORKING !!!");	
		}	
	}

	@Test
	@Disabled
	public void testGetAllEmployee() throws Exception
	{
		//1. Create Dummy Request or Object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/all")
				.contentType(MediaType.APPLICATION_JSON);
		//2. Execute request and get result
		MvcResult result = mockMvc.perform(request).andReturn();
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		//4. Test Using assert method
		if(!(response.getContentAsString().length()<=0)) {
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		}else {
			fail("Employee records Not available..");
		}
	}


	@Test
	@Disabled
	public void testGetOneEmployee() throws Exception
	{
		//1. Create Dummy Request or Object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/one/10")
				.contentType(MediaType.APPLICATION_JSON);

		//2. Execute request and get response
		MvcResult result = mockMvc.perform(request).andReturn();

		//3. Read Response
		MockHttpServletResponse response = result.getResponse();

		//4. Test Using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if(response.getContentAsString().isEmpty()) {
			fail("Employee records Not available..");
		}	
	}
	
	@Test
	@Disabled
	public void testGetOneEmployeeNotExist() throws Exception {
		// 1. Create One dummy object 
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/employee/one/30");
	
		// 2. Execute request and getResponse
		MvcResult result = mockMvc.perform(request).andReturn();
		
		// 3. Read Response
		MockHttpServletResponse response = result.getResponse();
				
		// 4. Test Using Asssert Method
		//status code is 404
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		if(!response.getContentAsString().equals("Employee Not Exist")) {
			fail("May be data not exist, please check again");
		}	
	}
	
	@Test
    @Disabled
	public void testDeleteEmployee() throws Exception {
		
		//1.create one dummy object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/remove/9");
	
		//2.execute request and getResponse
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3.Read response
		MockHttpServletResponse response = result.getResponse();
		
		//4.Test Using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("Employee Deleted", response.getContentAsString());
	
	}
	
	@Test
	@Disabled
	public void testDeleteEmployeeNotExist() throws Exception {
		
		//1. Create one dummy Request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/employee/remove/20");
		
		//2. execute request and getResponse
		MvcResult result =mockMvc.perform(request).andReturn();
		
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
				
		//4. Test Using assert method
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		assertEquals("Employee Not Exist", response.getContentAsString());	
	}
	
	@Test
	@Disabled
	public void testUpdateEmployee() throws Exception {
		
		//1. Create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/employee/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"empId\": 1,\"empName\": \"ganesh\", \"empSal\": 10000}");
		
		//2. execute request and get response
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert method
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals("updated..", response.getContentAsString());
		//when(HttpStatus.OK.value()).thenReturn(response.getStatus());
		
		}
	
	@Test
	public void testUpdateEmployeeNotExist() throws Exception {
		
		//1. Create one dummy request/object
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/employee/update/10")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"empId\": 10,\"empName\": \"ABC\", \"empSal\": 9000}");
		
		//2. execute request and get response
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//3. Read Response
		MockHttpServletResponse response = result.getResponse();
		
		//4. Test using assert method
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
		assertEquals("employee not exist for update", response.getContentAsString());
		
		}
}