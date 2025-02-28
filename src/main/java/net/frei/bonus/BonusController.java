package net.frei.bonus;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.frei.bonus.Bonus.BonusID;

@RestController
@RequestMapping("/bonus")
@CrossOrigin
public class BonusController {

    private final BonusService service;

    public BonusController(BonusService service) {
	this.service = service;
    }

    @GetMapping
    public List<Bonus> getBonus() {
	return service.getBonuses();
    }

    @GetMapping("{company}~{model}~{produced}&{plz}")
    public Bonus getBonus(@PathVariable String company, @PathVariable String model,
	    @PathVariable LocalDateTime produced, @PathVariable int plz) {
	return service.getBonus(BonusID.of(company, model, produced, plz));
    }

    @PostMapping
    public void addBonus(@RequestBody Bonus bon) {
	service.addBonus(bon);
    }

    @PutMapping("{company}~{model}~{produced}&{plz}")
    public void replaceBonus(@RequestBody Bonus bon, @RequestBody BonusID id) {
	service.replaceBonus(bon, id);
    }

    @DeleteMapping("{company}~{model}~{produced}&{plz}")
    public void deleteBonus(@RequestBody BonusID id) {
	service.deleteBonus(id);
    }

}
