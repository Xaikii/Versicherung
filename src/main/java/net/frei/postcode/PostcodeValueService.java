package net.frei.postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostcodeValueService {

    @Autowired
    private PostcodeValueRepository repo;

    @Transactional
    public List<PostcodeValue> getPostcodeValues() {
	return repo.findAll();
    }

    @Transactional
    public PostcodeValue getPostcodeValue(PostcodeValueID postcodeID) {
	return repo.findById(postcodeID)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postcode has no Value, or isn't registered"));
    }

    @Transactional
    public PostcodeValue getPostcodeValue(int postcode) {
	return repo.findAll().stream().filter(postcodeValue -> postcodeValue.getPostcode().POSTLEITZAHL == postcode)
		.findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postcode has no Value, or isn't registered"));
    }

    @Transactional
    public PostcodeValue addPostcodeValue(PostcodeValue postcodeID) {
	if (repo.existsById(postcodeID.getId())) {
	    throw new ResponseStatusException(HttpStatus.FOUND, "Postcode already has a Value");
	}
	return repo.save(postcodeID);
    }

    @Transactional
    public PostcodeValue replacePostcodeValue(PostcodeValue postcodeValue, PostcodeValueID postcodeID) {
	if (repo.existsById(postcodeID))
	    return repo.save(postcodeValue);
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public void deletePostcodeValue(PostcodeValueID postcodeID) {
	if (repo.existsById(postcodeID)) {
	    repo.deleteById(postcodeID);
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
