package net.frei;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import net.frei.vehicle.Vehicle;

class PostcodeTests {

    RestClient defaultClient = RestClient.create();

    @Test
    void test() {
	String getResult = defaultClient.get().uri("http://localhost:8080/postcode_value").retrieve()
		.body(String.class);
	System.out.println(getResult);

	LocalDateTime now = LocalDateTime.now();
	Vehicle vh = Vehicle.of("AUDI", "A8 D5", now);
	ResponseEntity<Vehicle> postResult = defaultClient.post()
		.uri("http://localhost:8080/vehicle")
		.contentType(MediaType.APPLICATION_JSON).body(vh).retrieve().toEntity(Vehicle.class);
	System.out.println(postResult);
	
	ResponseEntity<Vehicle> putResult = defaultClient.put()
		.uri("http://localhost:8080/vehicle/AUDI/A8/"+now)
		.contentType(MediaType.APPLICATION_JSON).body(vh.setId(vh.getId().setProduced(LocalDateTime.now()))).retrieve().toEntity(Vehicle.class);
	System.out.println(putResult);
	
	
	
    }

}
