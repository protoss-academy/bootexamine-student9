package com.protosstechnology.train.bootexamine;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protosstechnology.train.bootexamine.datamodel.Document;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class BootexamineApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void addDocument_thenOk() throws Exception {

		Document document = new Document();
		document.setId(Long.parseLong("0"));
		document.setDocumentNumber("TEMP-0");
		document.setDescription("Current Account");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(document);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/document")
				.contentType(MediaType.APPLICATION_JSON).content(json);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(2)
	void getDocument_thenOk() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/document/{id}", "0");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(3)
	void getDocument_NotFound() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/document/{id}", "99");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@Order(4)
	void editDocument_thenOk() throws Exception {

		Document document = new Document();
		document.setId(Long.parseLong("0"));
		document.setDocumentNumber("Edit-123");
		document.setDescription("BTC Account");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(document);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/document/{id}", "0")
				.contentType(MediaType.APPLICATION_JSON).content(json);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@Order(5)
	void editDocument_notFound() throws Exception {

		Document document = new Document();
		document.setDocumentNumber("Edit-123");
		document.setDescription("BTC Account");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(document);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/document/{id}", "99")
				.contentType(MediaType.APPLICATION_JSON).content(json);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@Order(6)
	void deleteDocument_notFound() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/document/{id}", "99");

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@Order(7)
	void deleteDocument_thenok() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/document/{id}", "0");

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}

}

