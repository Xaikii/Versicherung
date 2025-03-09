package net.frei.vehicle;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Vehicle implements Serializable {
    /**
     * Has to be Serializable to be an Entity. Sequencing, if we dont provide a
     * value in our Primary Keys (company, model, produced) it will generate one.
     * With Sequence we can also have custom generation of it.
     */
    private static final long serialVersionUID = -7003621006839101091L;

    public static Vehicle of(String company, String model, LocalDate produced) {
	return new Vehicle(VehicleID.of(company, model, produced));
    }
    
    @EmbeddedId
    private VehicleID id;

    public Vehicle() {
    }

    public Vehicle(VehicleID id) {
	this.id = id;
    }

    public VehicleID getId() {
	return id;
    }

    public Vehicle setId(VehicleID id) {
	this.id = id;
	return this;
    }

    public String getCompany() {
	return id.getCompany();
    }

    public String getModel() {
	return id.getModel();
    }

    public LocalDate getProduced() {
	return id.getProduced();
    }
    
    @Override
    public String toString() {
        return "Vehicle: %s : %s - %s".formatted(id.getCompany(), id.getModel(), id.getProduced());
    }

}
