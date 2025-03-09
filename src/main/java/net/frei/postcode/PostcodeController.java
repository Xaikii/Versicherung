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
@RequestMapping("/postcode")
@CrossOrigin
public class PostcodeController {

    @Autowired
    private PostcodeService service;

    @GetMapping
    public ResponseEntity<List<Postcode>> getPostcodes() {
	return new ResponseEntity<>(service.getPostcodes(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{plz}")
    public ResponseEntity<Postcode> getPostcode(@PathVariable int plz) {
	return new ResponseEntity<>(service.getPostcode(plz), HttpStatus.FOUND);
    }

}
