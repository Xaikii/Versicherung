package net.frei.vehicle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles() {
	return new ResponseEntity<>(service.getVehicles(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{company}/{model}/{produced}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced) {
	return new ResponseEntity<>(service.getVehicle(VehicleID.of(company, model, produced)), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vh) {
	return new ResponseEntity<>(service.addVehicle(vh), HttpStatus.CREATED);
    }

    @PutMapping("/{company}/{model}/{produced}")
    public ResponseEntity<Vehicle> replaceVehicle(@RequestBody Vehicle vh, @PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced) {
	return new ResponseEntity<>(service.replaceVehicle(vh, VehicleID.of(company, model, produced)),
		HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{company}/{model}/{produced}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced) {
	service.deleteVehicle(VehicleID.of(company, model, produced));
	return new ResponseEntity<>(HttpStatus.GONE);
    }
}
