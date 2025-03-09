package net.frei.postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostcodeService {

    @Autowired
    private PostcodeRepository repo;

    @Transactional
    public List<Postcode> getPostcodes() {
	return repo.findAll();
    }

    @Transactional
    public Postcode getPostcode(int postcodeNumber) {
	return repo.findById(postcodeNumber)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postcode is not registered"));
    }

    @Transactional
    public Postcode addPostcode(Postcode postcode) {
	if (repo.existsById(postcode.getPOSTLEITZAHL())) {
	    throw new ResponseStatusException(HttpStatus.FOUND, "Postcode is already registered");
	}
	return repo.save(postcode);
    }

    @Transactional
    public Postcode replacePostcode(Postcode postcode, int postcodeNumber) {
	if (repo.existsById(postcodeNumber))
	    return repo.save(postcode);
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deletePostcode(int postcodeNumber) {
	if (repo.existsById(postcodeNumber)) {
	    repo.deleteById(postcodeNumber);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
