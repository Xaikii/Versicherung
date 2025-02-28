package net.frei.vehicle;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class VehicleValueService {

    private VehicleValueRepository repo;

    public VehicleValueService(VehicleValueRepository repo) {
	this.repo = repo;
    }

    @Transactional
    public List<VehicleValue> getVehicles() {
	return repo.findAll();
    }
    
    @Transactional
    public VehicleValue getVehicle(VehicleValueID id) {
	return repo.getReferenceById(id);
    }

    @Transactional
    public boolean addVehicle(VehicleValue vh) {
	if (repo.exists(Example.of(vh))) {
	    return false;
	}
	repo.save(vh);
	return true;
    }

    @Transactional
    public void replaceVehicle(VehicleValue vh, VehicleValueID id) {
	if(repo.existsById(id))
	    repo.save(vh);
    }
    
    @Transactional
    public void deleteVehicle(VehicleValueID id) {
	repo.deleteById(id);
    }
}
