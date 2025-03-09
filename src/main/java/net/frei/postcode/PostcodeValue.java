package net.frei.postcode;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
/**
 * A seperate Class where the Postcode gets a Value assigned
 */
public class PostcodeValue implements Serializable {
    /**
     * Has to be Serializable to be an Entity. Sequencing, if we dont provide a
     * value in our Primary Key (POSTLEITZAHL) it will generate one. With Sequence
     * we can also have custom generation of it.
     */

    private static final long serialVersionUID = -7815169774888625433L;

    @EmbeddedId // Embedds an ID and makes it a Primary Key
    private PostcodeValueID id;
    private float value;

    public PostcodeValue() {
    }

    public PostcodeValue(PostcodeValueID postcode, float value) {
	this.id = postcode;
	this.value = value;
    }

    public Postcode getPostcode() {
	return id.postcode;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

    public PostcodeValueID getId() {
	return id;
    }

    public void setId(PostcodeValueID id) {
	this.id = id;
    }

    @Override
    public final String toString() {
	return "%s = %f".formatted(id.postcode.toString(), value);
    }

}
