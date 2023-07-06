package com.example.junit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.UserController;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	UserService userService;
	
	@Mock
	User user;
	
	@Mock
	ObjectMapper objectMapper;
	
	//Junit Test
	@ParameterizedTest
	@MethodSource("getUserData")
	public void createUserJunitTest(UserDTO userDTO) throws JsonProcessingException, Exception {
		when(userService.createUser(userDTO)).thenReturn(user);
		System.out.println("User Data :"+userDTO.toString());
		ObjectMapper mapper=new ObjectMapper();
		this.mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDTO))
				.characterEncoding("utf-8"))
				.andExpect(status().isOk());
	}
	
	public static Object[][] getUserData() throws JsonMappingException, JsonProcessingException{
		String userDtoJson = """
				[
					{
						"firstName":"Raj",
						"lastName":"Kumar",
						"phone":1234567890,
						"email":"raj@gmail.com",
						"password":"ABCD",
						"city":"Bengaluru",
						"state":"Karnakata",
						"pincode":560001
					}
				]
				""";
		ObjectMapper mapper=new ObjectMapper();
		System.out.println(userDtoJson);
		UserDTO[] userArray =  mapper.readValue(userDtoJson, UserDTO[].class);
		System.out.println("line 71 : "+userArray[0].getFirstName());
		Object obj[][] = new Object[1][1];
		obj[0][0] = userArray[0];
		return obj;
	}
}
