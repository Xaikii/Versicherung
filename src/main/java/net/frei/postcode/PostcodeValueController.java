package net.frei.postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode_value")
@CrossOrigin
public class PostcodeValueController {

    private final PostcodeValueService service;

    @Autowired
    public PostcodeValueController(PostcodeValueService serv) {
	this.service = serv;
    }

    @GetMapping
    public List<PostcodeValue> getPostcodes() {
	return service.getPostcodeValues();
    }

    @GetMapping("/{plz}")
    public PostcodeValue getPostcode(@PathVariable int plz) {
	return service.getPostcodeValue(plz);
    }

//    @PostMapping
//    public void addPostcode(@RequestBody PostcodeValue pc) {
//	service.addPostcode(pc);
//    }
//
////    @PutMapping("/{plz}")
//    public void replacePostcode(@RequestBody PostcodeValue pc, @PathVariable int plz) {
//	service.replacePostcode(pc, plz);
//    }
//
////    @DeleteMapping("/{plz}")
//    public void deletePostcode(@PathVariable int plz) {
//	service.deletePostcode(plz);
//    }
}
