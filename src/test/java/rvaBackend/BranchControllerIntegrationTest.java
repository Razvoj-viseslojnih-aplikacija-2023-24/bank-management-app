package rvaBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import rvaBackend.models.Branch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BranchControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Branch>> response = template.exchange("/branch", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Branch>>() {
				});
		ArrayList<Branch> list = (ArrayList<Branch>) response.getBody();
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
	void testGetAllBranches() {
		ResponseEntity<List<Branch>> response = template.exchange("/branch", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Branch>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Branch> branches = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(branches);
	}

	@Test
	@Order(2)
	void testFindBranchById() {
		long id = 1;
		ResponseEntity<Branch> response = template.getForEntity("/porudzbina/id/" + id, Branch.class);
		int statusCode = response.getStatusCode().value();
		Branch branch = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(branch);
		assertEquals(id, branch.getId());
	}

	@Test
	@Order(3)
	void testFindBranchByAddress() {
		String address = "Bulevar oslobođenja 5, Novi Sad";
		ResponseEntity<List<Branch>> response = template.exchange("/branch/address/" + address, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Branch>>(){});
		int statusCode = response.getStatusCode().value();
		List<Branch> branches =  response.getBody();
		String branchAddress =   branches.get(0).getAddress();
		
		assertEquals(200, statusCode );
		assertNotNull(branches.get(0));
		assertTrue(branchAddress.startsWith(address));	
	}

	@Test
	@Order(4)
	void testGetBranchesByBank() {
		long bankId = 1;
		ResponseEntity<List<Branch>> response = template.exchange("/branch/bank/" + bankId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Branch>>(){});
		int statusCode = response.getStatusCode().value();
		List<Branch> branches =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(branches.get(0));
		for(Branch b: branches) {
			assertTrue(b.getBank().getId() == 1);
		}
	}

	@Test
	@Order(5)
	void testCreateBranch() {
		Branch branch = new Branch();
		branch.setAddress("Test addres");
		branch.setCounters(4);
		branch.setHasBoss(false);
		
		HttpEntity<Branch> entity = new HttpEntity<Branch>(branch);
		createHighestId();
		ResponseEntity<Branch> response  = template.postForEntity("/branch", entity, Branch.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(branch.getAddress(), response.getBody().getAddress());
		assertEquals(4, response.getBody().getCounters());
		assertFalse(response.getBody().isHasBoss());
	}

	@Test
	@Order(6)
	void testUpdateBranch() {
		Branch branch = new Branch();
		branch.setCounters(5);
		branch.setHasBoss(true);
		
		HttpEntity<Branch> entity = new HttpEntity<Branch>(branch);
		getHighestId();
		ResponseEntity<Branch> response  = template.exchange("/branch/id/" + largestId, HttpMethod.PUT, entity, Branch.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(5, response.getBody().getCounters());
		assertTrue(response.getBody().isHasBoss());
	}

	@Test
	@Order(7)
	void testDeleteBranch() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/branch/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}

}
