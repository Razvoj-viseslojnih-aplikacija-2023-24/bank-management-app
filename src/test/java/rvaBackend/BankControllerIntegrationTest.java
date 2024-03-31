package rvaBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rvaBackend.models.Bank;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Bank>> response = template.exchange(
				"/bank", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Bank>>() {});
		
		List<Bank> list = response.getBody();
		for(int i=0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()){
				highestId = list.get(i).getId() + 1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void TestGetAllArtikls() {
		ResponseEntity<List<Bank>> response = template.exchange(
				"/bank", HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Bank>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Bank> banks = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!banks.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetBankById() {
		int id = 1;
		ResponseEntity<Bank> response = template.exchange(
				"/bank/id/" + id, HttpMethod.GET, null, Bank.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	void testGetBanksByTin() {
		int tin = 100001159;
		ResponseEntity<List<Bank>> response = template.exchange(
				"/bank/tin/" + tin, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Bank>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Bank> banks = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(banks.get(0));
		for(Bank b : banks) {
			assertTrue(b.getTin()== tin);
			// U slucaju brojcane vrednosti
			// assertTrue(a.getVrednost() < predefinisanaVrednost)
			// ILI
			// assertTrue(a.getVrednost() > predefinisanaVrednost)
			
			//U slucaju boolean vrednosti
			// assertTrue(a.getBooleanVrednost());
		}
	}
	
	@Test
	@Order(4)
	void testCreateBank() {
		Bank bank = new Bank();
		bank.setName("Test name");
		bank.setContact("Test contact");
		bank.setTin(100501154);
		
		HttpEntity<Bank> entity = new HttpEntity<Bank>(bank);
		createHighestId();
		
		ResponseEntity<Bank> response = template.exchange(
				"/bank", HttpMethod.POST, entity, Bank.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/bank/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(bank.getName(), response.getBody().getName());
		assertEquals(bank.getContact(), response.getBody().getContact());
		assertEquals(bank.getTin(), response.getBody().getTin());
	}
	

	@Test
	@Order(5)
	void TestUpdateBank() {
		Bank bank = new Bank();
		bank.setName("Test name");
		bank.setContact("Test contact");
		bank.setTin(100501154);
		
		HttpEntity<Bank> entity = new HttpEntity<Bank>(bank);
		getHighestId();
		
		ResponseEntity<Bank> response = template.exchange(
				"/bank/id/" + highestId, HttpMethod.PUT, entity, Bank.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Bank);
		assertEquals(bank.getName(), response.getBody().getName());
		assertEquals(bank.getContact(), response.getBody().getContact());	
	
	}
	
	@Test
	@Order(6)
	void TestDeleteBankById() {
		getHighestId();
		ResponseEntity<String> response = template.exchange(
				"/bank/id/" + highestId, HttpMethod.DELETE, null, String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been deleted."));
				
	}
}
