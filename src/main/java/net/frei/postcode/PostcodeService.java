package net.frei.postcode;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PostcodeService {

    private PostcodeRepository repo;

    public PostcodeService(PostcodeRepository repo) {
	this.repo = repo;
    }

    @Transactional
    public List<Postcode> getPostcodes() {
	return repo.findAll();
    }
    
    @Transactional
    public Postcode getPostcode(int plz) {
	return repo.getReferenceById(plz);
    }

    @Transactional
    public boolean addPostcode(Postcode pc) {
	if (repo.exists(Example.of(pc))) {
	    return false;
	}
	repo.save(pc);
	return true;
    }

    @Transactional
    public void replacePostcode(Postcode pc, int plz) {
	if(repo.existsById(plz))
	    repo.save(pc);
    }
    
    @Transactional
    public void deletePostcode(int plz) {
	repo.deleteById(plz);
    }
    
}
