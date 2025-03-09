package net.frei.bonus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BonusService {

    @Autowired
    private BonusRepository repo;

    @Transactional
    public List<Bonus> getBonuses() {
	return repo.findAll();
    }

    @Transactional
    public Bonus getBonus(BonusID id) {
	return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
		"Bonus is not registered for " + id.toString()));
    }

    @Transactional
    public Bonus addBonus(Bonus bonus) {
	if (repo.exists(Example.of(bonus))) {
	    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bonus already exists");
	}
	return repo.save(bonus);
    }

    @Transactional
    public Bonus replaceBonus(Bonus bonus, BonusID id) {
	if (repo.existsById(id))
	    return repo.save(bonus);
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deleteBonus(BonusID id) {
	if (repo.existsById(id)) {
	    repo.deleteById(id);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
