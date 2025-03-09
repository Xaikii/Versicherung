package net.frei;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import net.frei.vehicle.Vehicle;
import net.frei.vehicle.VehicleID;

class PostcodeTests {

    RestClient defaultClient = RestClient.create();
    Logger logger = Logger.getAnonymousLogger();

    @Test
    void test() {
	String getPostcodeResult = defaultClient.get().uri("http://localhost:8080/postcode_value").retrieve()
		.body(String.class);
	assertNotNull(getPostcodeResult);

	LocalDate now = LocalDate.now();
	Vehicle vh = Vehicle.of("AUDI", "A8 D5", now);
	ResponseEntity<Vehicle> postResult = defaultClient.post().uri("http://localhost:8080/vehicle")
		.contentType(MediaType.APPLICATION_JSON).body(vh).retrieve().toEntity(Vehicle.class);
	assertInstanceOf(Vehicle.class, postResult.getBody());
	logger.info(postResult.toString());

	Vehicle getVehicleResult = defaultClient.get()
		.uri("http://localhost:8080/vehicle/AUDI/A8 D5/" + now).retrieve().body(Vehicle.class);
	logger.info(getVehicleResult.toString());

	ResponseEntity<Vehicle> putResult = defaultClient.put().uri("http://localhost:8080/vehicle/AUDI/A8 D5/" + now)
		.contentType(MediaType.APPLICATION_JSON)
		.body(vh.setId(VehicleID.of(vh.getCompany(), vh.getModel(), LocalDate.now()))).retrieve()
		.toEntity(Vehicle.class);
	logger.info(putResult.toString());
	assertNotEquals(postResult.getBody(), putResult.getBody());

    }

}
