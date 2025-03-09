package net.frei.postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postcode_value")
@CrossOrigin
public class PostcodeValueController {

    @Autowired
    private PostcodeValueService service;

    @GetMapping
    public ResponseEntity<List<PostcodeValue>> getPostcodes() {
	return new ResponseEntity<>(service.getPostcodeValues(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{plz}")
    public ResponseEntity<PostcodeValue> getPostcode(@PathVariable int plz) {
	return new ResponseEntity<>(service.getPostcodeValue(plz), HttpStatus.FOUND);
    }

}
