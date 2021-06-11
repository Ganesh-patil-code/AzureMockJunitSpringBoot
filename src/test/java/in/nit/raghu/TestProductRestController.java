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
}