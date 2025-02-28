package net.frei.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
	this.service = service;
    }

    @GetMapping
    public List<Vehicle> getVehicle() {
	return service.getVehicles();
    }

    @GetMapping("/{company}~{model}~{produced}")
    public Vehicle getVehicle(@PathVariable String company, @PathVariable String model,
	    @PathVariable String produced) {
	return service.getVehicle(VehicleID.of(company, model, produced));
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vh) {
	service.addVehicle(vh);
    }

    @PutMapping("/{company}/{model}/{produced}")
    public void replaceVehicle(@RequestBody Vehicle vh, @PathVariable("company") String company,
	    @PathVariable("model") String model, @PathVariable("produced") String produced) {
	service.replaceVehicle(vh, VehicleID.of(company, model, produced));
    }

    @DeleteMapping("/{company}/{model}/{produced}")
    public void deleteVehicle(@PathVariable("company") String company, @PathVariable("model") String model,
	    @PathVariable("produced") String produced) {
	service.deleteVehicle(VehicleID.of(company, model, produced));
    }
}
