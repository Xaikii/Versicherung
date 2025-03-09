package net.frei.bonus;

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

import net.frei.postcode.PostcodeValueService;
import net.frei.vehicle.VehicleValueID;
import net.frei.vehicle.VehicleValueService;

@RestController
@RequestMapping("/bonus")
@CrossOrigin
public class BonusController {

    @Autowired
    private BonusService service;
    @Autowired
    private PostcodeValueService postcodeService;
    @Autowired
    private VehicleValueService vehicleService;

    @GetMapping
    public ResponseEntity<List<Bonus>> getBonus() {
	return new ResponseEntity<>(service.getBonuses(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{company}/{model}/{produced}/{plz}/{usage}")
    public ResponseEntity<Bonus> getBonus(@PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced,
	    @PathVariable int plz, @PathVariable float usage) {
	return new ResponseEntity<>(service.getBonus(new BonusID(postcodeService.getPostcodeValue(plz),
		vehicleService.getVehicle(VehicleValueID.of(company, model, produced)))), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Bonus> addBonus(@RequestBody Bonus bon) {
	return new ResponseEntity<>(service.addBonus(bon), HttpStatus.CREATED);
    }

    @PutMapping("/{company}/{model}/{produced}/{plz}/{usage}")
    public ResponseEntity<Bonus> replaceBonus(@RequestBody Bonus bon, @PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced,
	    @PathVariable int plz, @PathVariable float usage) {
	return new ResponseEntity<>(
		service.replaceBonus(bon,
			new BonusID(postcodeService.getPostcodeValue(plz),
				vehicleService.getVehicle(VehicleValueID.of(company, model, produced)))),
		HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{company}/{model}/{produced}/{plz}/{usage}")
    public ResponseEntity<Void> deleteBonus(@PathVariable(name = "company") String company,
	    @PathVariable(name = "model") String model, @PathVariable(name = "produced") LocalDate produced,
	    @PathVariable int plz, @PathVariable float usage) {
	service.deleteBonus(new BonusID(postcodeService.getPostcodeValue(plz),
		vehicleService.getVehicle(VehicleValueID.of(company, model, produced))));
	return new ResponseEntity<>(HttpStatus.GONE);
    }

}
