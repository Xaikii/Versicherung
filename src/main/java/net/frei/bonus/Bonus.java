package net.frei.bonus;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import net.frei.postcode.PostcodeValue;
import net.frei.vehicle.VehicleValue;

@Entity
@Table
public class Bonus {

    @EmbeddedId // Embedds an ID and makes it a Primary Key
    BonusID id;
    float value;

    public Bonus() {
    }

    public Bonus(BonusID id) {
	this.id = id;
	this.value = id.getPostcode().getValue() * id.getVehicle().getValue() * id.getUsage();
    }

    @Override
    public String toString() {
	return id.postcode + " * " + id.vehicle + " * " + id.usage + "  = " + value;
    }

    public PostcodeValue getPostcode() {
	return id.postcode;
    }

    public VehicleValue getVehicle() {
	return id.vehicle;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

}
