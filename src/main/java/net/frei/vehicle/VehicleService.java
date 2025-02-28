package net.frei.vehicle;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class VehicleService {

    private VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
	this.repo = repo;
    }

    @Transactional
    public List<Vehicle> getVehicles() {
	return repo.findAll();
    }
    
    @Transactional
    public Vehicle getVehicle(VehicleID id) {
	return repo.getReferenceById(id);
    }

    @Transactional
    public boolean addVehicle(Vehicle vh) {
	if (repo.exists(Example.of(vh))) {
	    return false;
	}
	repo.save(vh);
	return true;
    }

    @Transactional
    public void replaceVehicle(Vehicle vh, VehicleID id) {
	if(repo.existsById(id))
	    repo.save(vh);
    }
    
    @Transactional
    public void deleteVehicle(VehicleID id) {
	repo.deleteById(id);
    }
}
