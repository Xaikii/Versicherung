package net.frei.postcode;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

@Service
public class PostcodeValueService {

    private PostcodeValueRepository repo;

    public PostcodeValueService(PostcodeValueRepository repo) {
	this.repo = repo;
    }

    @Transactional
    public List<PostcodeValue> getPostcodeValues() {
	return repo.findAll();
    }

    @Transactional
    public PostcodeValue getPostcodeValue(PostcodeValueID pc) {
	return repo.findById(pc)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "postcode not found"));
    }

    @Transactional
    public PostcodeValue getPostcodeValue(int plz) {
	return repo.findAll().stream().filter(T -> T.getPostcode().POSTLEITZAHL == plz).findFirst()
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "postcode not found"));
    }

    @Transactional
    public boolean addPostcodeValue(PostcodeValue pc) {
	if (!repo.existsById(pc.getId())) {
	    repo.save(pc);
	    return true;
	}
	return false;
    }

    @Transactional
    public void replacePostcodeValue(PostcodeValue pcv, PostcodeValueID pc) {
	if (repo.existsById(pc))
	    repo.save(pcv);
    }

    @Transactional
    public void deletePostcodeValue(PostcodeValueID pc) {
	repo.deleteById(pc);
    }

}
