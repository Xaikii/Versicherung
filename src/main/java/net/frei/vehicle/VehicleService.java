package net.frei.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repo;

    @Transactional
    public List<Vehicle> getVehicles() {
	return repo.findAll();
    }

    @Transactional
    public Vehicle getVehicle(VehicleID id) {
	return repo.getReferenceById(id);
    }

    @Transactional
    public Vehicle addVehicle(Vehicle vh) {
	if (repo.exists(Example.of(vh))) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already exists");
	}
	return repo.save(vh);
    }

    @Transactional
    public Vehicle replaceVehicle(Vehicle vh, VehicleID id) {
	if (repo.existsById(id))
	    return repo.save(vh);
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deleteVehicle(VehicleID id) {
	if (repo.existsById(id)) {
	    repo.deleteById(id);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
