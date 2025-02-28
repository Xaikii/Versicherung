package net.frei.bonus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.frei.UsageRate;
import net.frei.bonus.Bonus.BonusID;
import net.frei.postcode.PostcodeValue;
import net.frei.postcode.PostcodeValueService;
import net.frei.vehicle.VehicleValue;
import net.frei.vehicle.VehicleValueService;

@Service
public class BonusService {

    @Autowired
    private BonusRepository repo;
    @Autowired
    private PostcodeValueService pcService;
    @Autowired
    private VehicleValueService vhService;

    @Transactional
    public List<Bonus> getBonuses() {
	return repo.findAll();
    }

    @Transactional
    public Bonus getBonus(BonusID id) {
	return repo.getReferenceById(id);
    }

    @Transactional
    public boolean addBonus(Bonus bon) {
	if (repo.exists(Example.of(bon))) {
	    return false;
	}
	repo.save(bon);
	return true;
    }

    @Transactional
    public void replaceBonus(Bonus bon, BonusID id) {
	if (repo.existsById(id))
	    repo.save(bon);
    }

    @Transactional
    public void deleteBonus(BonusID id) {
	repo.deleteById(id);
    }

    public float getAmount(BonusID bonus, float usage) {
	return getAmount(pcService.getPostcodeValue(bonus.postcode.getId()), vhService.getVehicle(bonus.vehicle.getId()), usage);
    }

    public static float getAmount(PostcodeValue postcode, VehicleValue vehicle, float usage) {
	return postcode.getValue() * vehicle.getValue() * UsageRate.getUsageFactor(usage);
    }

}
