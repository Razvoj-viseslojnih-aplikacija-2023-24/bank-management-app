package rvaBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import rvaBackend.models.BankService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankServiceControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<BankService>> response = template.exchange("/bankService", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BankService>>() {
				});
		ArrayList<BankService> list = (ArrayList<BankService>) response.getBody();
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
	void testGetAllBankService() {
		ResponseEntity<List<BankService>> response = template.exchange("/bankService", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BankService>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<BankService> bankService = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(bankService);
	}

	@Test
	@Order(2)
	void testFindBankServiceById() {
		long id = 1;
		ResponseEntity<BankService> response = template.getForEntity("/bankService/id/" + id, BankService.class);
		int statusCode = response.getStatusCode().value();
		BankService bankService = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(bankService);
		assertEquals(id, bankService.getId());
	}

	@Test
	@Order(3)
	void testFindBankServiceByName() {
		String name = "Izrada platne kartice";
		ResponseEntity<List<BankService>> response = template.exchange("/bankService/name/" + name, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BankService>>(){});
		int statusCode = response.getStatusCode().value();
		List<BankService> bankService =  response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(bankService.get(0));
		for(BankService b : bankService) {
			assertTrue(b.getName().contains(name));
		}
	}

	@Test
	@Order(4)
	void testGetBankServiceByBranch() {
		long branchId = 1;
		ResponseEntity<List<BankService>> response = template.exchange("/bankService/branch/" + branchId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BankService>>(){});
		int statusCode = response.getStatusCode().value();
		List<BankService> bankService =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(bankService.get(0));
		for(BankService b: bankService) {
			assertTrue(b.getBranch().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testGetBankServiceByUser() {
		long userId = 1;
		ResponseEntity<List<BankService>> response = template.exchange("/bankService/user/" + userId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BankService>>(){});
		int statusCode = response.getStatusCode().value();
		List<BankService> bankService =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(bankService.get(0));
		for(BankService b: bankService) {
			assertTrue(b.getUser().getId() == 1);
		}
	}

	@Test
	@Order(6)
	void testCreateBankService() {
		BankService bankService = new BankService();
		bankService.setName("Test name");
		bankService.setServiceDescription("Test Description");
		Date date = new Date();
		bankService.setContractDate(date);
		bankService.setCommission(5);
		
		HttpEntity<BankService> entity = new HttpEntity<BankService>(bankService);
		createHighestId();
		ResponseEntity<BankService> response  = template.postForEntity("/bankService", entity, BankService.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(bankService.getName(), response.getBody().getName());
		assertEquals(bankService.getServiceDescription(), response.getBody().getServiceDescription());
		assertEquals(bankService.getContractDate(), response.getBody().getContractDate());
		assertEquals(bankService.getCommission(), response.getBody().getCommission());
	}

	@Test
	@Order(7)
	void testUpdateBankService() {
		BankService bankService = new BankService();
		bankService.setName("Changed name");
		bankService.setServiceDescription("Changed description");
		bankService.setCommission(9);
		
		HttpEntity<BankService> entity = new HttpEntity<BankService>(bankService);
		getHighestId();
		ResponseEntity<BankService> response  = template.exchange("/bankService/id/" + largestId, HttpMethod.PUT, entity, BankService.class);
		
		assertTrue(response.hasBody());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(bankService.getName(), response.getBody().getName());
		assertEquals(bankService.getServiceDescription(), response.getBody().getServiceDescription());
		assertEquals(bankService.getCommission(), response.getBody().getCommission());
	}

	@Test
	@Order(8)
	void testDeleteBankService() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/bankService/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}
}
