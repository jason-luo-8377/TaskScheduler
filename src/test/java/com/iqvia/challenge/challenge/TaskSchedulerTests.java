package com.iqvia.challenge.challenge;

import com.iqvia.challenge.challenge.exceptions.BaseException;
import com.iqvia.challenge.challenge.model.RestResponse;
import com.iqvia.challenge.challenge.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskSchedulerTests {
	@Autowired
	private WebApplicationContext applicationContext;

	private MockMvc mockMvc;

	@Autowired
	private ResourceLoader resourceLoader;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	void testScheduleTaskInvalidDeliveryTime() throws Exception {
		byte[] requestBody = resourceLoader.getResource("classpath:request_body/RequestBodyDeliveryTimeInThePast.json").getInputStream().readAllBytes();
		MockHttpServletResponse response = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/challenge/scheduleTask").content(requestBody).contentType("application/json"))
				.andReturn().getResponse();

		//Validate Http code(400 expected)
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

		RestResponse restResponse = TestUtil.parseMockHttpServletResponse(response);

		//Validate the code field in the response body(VALIDATION_ERROR expected)
		assertThat(restResponse.getCode()).isEqualTo(BaseException.ERROR_CODE_VALIDATION);
	}

	@Test
	void testScheduleTask() throws Exception {
		byte[] requestBody = resourceLoader.getResource("classpath:request_body/RequestBodyValid.json").getInputStream().readAllBytes();
		MockHttpServletResponse response = this.mockMvc.perform(
						MockMvcRequestBuilders.post("/challenge/scheduleTask").content(requestBody).contentType("application/json"))
				.andReturn().getResponse();

		//Validate Http code(202 expected)
		assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

		RestResponse restResponse = TestUtil.parseMockHttpServletResponse(response);

		Task task = TestUtil.getTaskObjectResponse(restResponse);

		//The id field should not be null
		assertThat(task.getId()).isNotNull();

		//The status should be new(0)
		assertThat(task.getStatus()).isEqualTo(SchedulerConstants.TASK_STATUS_NEW);
	}
}
