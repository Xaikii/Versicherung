package net.frei.postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode")
@CrossOrigin
public class PostcodeController {

    private final PostcodeService service;

    @Autowired
    public PostcodeController(PostcodeService serv) {
	this.service = serv;
    }

    @GetMapping
    public List<Postcode> getPostcodes() {
	return service.getPostcodes();
    }

    @GetMapping("/{plz}")
    public Postcode getPostcode(@PathVariable int plz) {
	return service.getPostcode(plz);
    }

//    @PostMapping
    public void addPostcode(@RequestBody Postcode pc) {
	service.addPostcode(pc);
    }

//    @PutMapping("/{plz}")
    public void replacePostcode(@RequestBody Postcode pc, @PathVariable int plz) {
	service.replacePostcode(pc, plz);
    }

//    @DeleteMapping("/{plz}")
    public void deletePostcode(@PathVariable int plz) {
	service.deletePostcode(plz);
    }
}
