package rvaBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rvaBackend.models.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<User>> response = template.exchange("/user", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		ArrayList<User> list = (ArrayList<User>) response.getBody();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (largestId <= list.get(i).getId()) {
				largestId = list.get(i).getId() + 1;
			}
		}

	}

	private void getHighestId() {
		createHighestId();
		largestId--;
	}

	@Test
	@Order(1)
	void testGetAllUsers() {
		ResponseEntity<List<User>> response = template.exchange("/user", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<User> users = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(users);
	}

	@Test
	@Order(2)
	void testGetUserById() {
		int id = 1;
		ResponseEntity<User> response = template.getForEntity("/user/id/" + id, User.class);
		int statusCode = response.getStatusCode().value();
		User user = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(user);
		assertEquals(id, user.getId());
	}

	@Test
	@Order(3)
	void testGetUsersByIdNumber() {
		String idNumber = "2201002800015";
		ResponseEntity<List<User>> response = template.exchange("/user/idNumber/" + idNumber, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<User>>() {});
		int statusCode = response.getStatusCode().value();
		List<User> users =  response.getBody();
		
		assertEquals(200, statusCode );
		assertTrue(!users.isEmpty());
		for(User u : users) {
			assertTrue(u.getIdNumber().contains(idNumber));
	}
}

	@Test
	@Order(4)
	void testCreateUser() {
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		user.setIdNumber("1234567891234");
		
		HttpEntity<User> entity = new HttpEntity<User>(user);
		createHighestId();
		ResponseEntity<User> response  = template.postForEntity("/user", entity, User.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(user.getName(), response.getBody().getName());
		assertEquals(user.getSurname(), response.getBody().getSurname());
		assertEquals(user.getIdNumber(), response.getBody().getIdNumber());
	}

	@Test
	@Order(5)
	void testUpdateUser() {
		User user = new User();
		user.setName("Changed name");
		user.setSurname("Changed surname");
		user.setIdNumber("Changed idNumber");
		
		HttpEntity<User> entity = new HttpEntity<User>(user);
		getHighestId();
		ResponseEntity<User> response  = template.exchange("/user/id/" + largestId, HttpMethod.PUT, entity, User.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(user.getName(), response.getBody().getName());
		assertEquals(user.getSurname(), response.getBody().getSurname());
		assertEquals(user.getIdNumber(), response.getBody().getIdNumber());
	}

	@Test
	@Order(6)
	void testDeleteUser() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/user/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("Has been deleted!"));
	}
}
