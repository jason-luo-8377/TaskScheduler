package com.iqvia.challenge.challenge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqvia.challenge.challenge.model.RestResponse;
import com.iqvia.challenge.challenge.model.Task;
import org.springframework.mock.web.MockHttpServletResponse;

public class TestUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static RestResponse parseMockHttpServletResponse(MockHttpServletResponse response) throws Exception {
        return objectMapper.readValue(response.getContentAsString(), RestResponse.class);
    }

    public static Task getTaskObjectResponse(RestResponse restResponse) throws Exception {
        return objectMapper.readValue(objectMapper.writeValueAsString(restResponse.getData()), Task.class);
    }
}
