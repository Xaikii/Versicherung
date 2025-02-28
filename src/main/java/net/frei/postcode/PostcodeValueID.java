package net.frei.postcode;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

@Embeddable
public class PostcodeValueID implements Serializable {
    private static final long serialVersionUID = 4387757723633682105L;

    @OneToOne
    Postcode postcode;
    
    public PostcodeValueID() {
    }

    public PostcodeValueID(Postcode postcode) {
	super();
	this.postcode = postcode;
    }

    public Postcode getPostcode() {
	return postcode;
    }

    public void setPostcode(Postcode postcode) {
	this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	return postcode == ((PostcodeValueID) o).getPostcode();
    }

    @Override
    public int hashCode() {
	return Objects.hash(postcode);
    }

}
