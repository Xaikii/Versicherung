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
@RequestMapping("/vehicle_value")
@CrossOrigin
public class VehicleValueController {

    private final VehicleValueService service;

    @Autowired
    public VehicleValueController(VehicleValueService service) {
	this.service = service;
    }

    @GetMapping
    public List<VehicleValue> getVehicle() {
	return service.getVehicles();
    }

    @GetMapping("/{company}~{model}~{produced}")
    public VehicleValue getVehicle(@PathVariable String company, @PathVariable String model,
	    @PathVariable String produced) {
	return service.getVehicle(VehicleValueID.of(company, model, produced));
    }

    @PostMapping
    public void addVehicle(@RequestBody VehicleValue vh) {
	service.addVehicle(vh);
    }

    @PutMapping("/{company}~{model}~{produced}")
    public void replaceVehicle(@RequestBody VehicleValue vh, @PathVariable String company, @PathVariable String model,
	    @PathVariable String produced) {
	service.replaceVehicle(vh, VehicleValueID.of(company, model, produced));
    }

    @DeleteMapping("/{company}~{model}~{produced}")
    public void deleteVehicle(@PathVariable String company, @PathVariable String model,
	    @PathVariable String produced) {
	service.deleteVehicle(VehicleValueID.of(company, model, produced));
    }
}
