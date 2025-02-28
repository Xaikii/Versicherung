package net.frei.bonus;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.frei.postcode.PostcodeValue;
import net.frei.vehicle.VehicleValue;

@Entity
@Table
public class Bonus {

    @EmbeddedId // Embedds an ID and makes it a Primary Key
    BonusID id;
    public float usage;
    float value;

    public Bonus() {
    }

    public Bonus(BonusID id, float usage) {
	this.id = id;
	this.value = BonusService.getAmount(id.getPostcode(), id.getVehicle(), usage);
    }

    @Override
    public String toString() {
	return id.postcode + " * " + id.vehicle + " * " + usage + "  = " + value;
    }

    public PostcodeValue getPostcode() {
	return id.postcode;
    }

    public VehicleValue getVehicle() {
	return id.vehicle;
    }

    public float getUsage() {
	return usage;
    }

    public void setUsage(float usage) {
	this.usage = usage;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

    @Embeddable
    public static class BonusID implements Serializable {
	private static final long serialVersionUID = 4792335820954619806L;
	@ManyToOne(targetEntity = PostcodeValue.class)
	public PostcodeValue postcode;
	@ManyToOne(targetEntity = VehicleValue.class)
	public VehicleValue vehicle;

	public static BonusID of(String company, String model, LocalDateTime produced, int plz) {
	    return new BonusID();
	}

	public BonusID() {
	}

	public BonusID(PostcodeValue postcode, VehicleValue vehicle) {
	    this.postcode = postcode;
	    this.vehicle = vehicle;
	}

	public PostcodeValue getPostcode() {
	    return postcode;
	}

	public void setPostcode(PostcodeValue postcode) {
	    this.postcode = postcode;
	}

	public VehicleValue getVehicle() {
	    return vehicle;
	}

	public void setVehicle(VehicleValue vehicle) {
	    this.vehicle = vehicle;
	}

    }

}
