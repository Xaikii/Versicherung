package net.frei.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehicleValueService {

    @Autowired
    private VehicleValueRepository repo;

    @Transactional
    public List<VehicleValue> getVehicles() {
	return repo.findAll();
    }

    @Transactional
    public VehicleValue getVehicle(VehicleValueID id) {
	return repo.findById(id).orElseThrow(
		() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle has no Value or is not registered"));
    }

    @Transactional
    public VehicleValue addVehicle(VehicleValue vh) {
	if (repo.exists(Example.of(vh))) {
	    throw new ResponseStatusException(HttpStatus.FOUND, "Vehicle already has a Value");
	}
	return repo.save(vh);
    }

    @Transactional
    public VehicleValue replaceVehicle(VehicleValue vh, VehicleValueID id) {
	if (repo.existsById(id))
	    return repo.save(vh);
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deleteVehicle(VehicleValueID id) {
	if (repo.existsById(id)) {
	    repo.deleteById(id);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
