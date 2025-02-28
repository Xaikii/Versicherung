package net.frei.vehicle;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
/**
 * A seperate Class where the Vehicle gets a Value assigned
 */
public class VehicleValue implements Serializable {
    /**
     * Has to be Serializable to be an Entity. Sequencing, if we dont provide a
     * value in our Primary Key (company, model, produced) it will generate one.
     * With Sequence we can also have custom generation of it.
     */
    private static final long serialVersionUID = -878167396684411374L;

    @EmbeddedId // Embedds an ID and makes it a Primary Key
    VehicleValueID id;

    float value;

    public VehicleValue() {
    }

    public VehicleValue(VehicleValueID vehicle, float value) {
	this.id = vehicle;
	this.value = value;
    }

    @Override
    public final String toString() {
	return "%s = %f".formatted(id.vehicle.toString(), value);
    }

    public Vehicle getVehicle() {
	return id.vehicle;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

    public VehicleValueID getId() {
	return id;
    }

    public void setId(VehicleValueID id) {
	this.id = id;
    }

}
