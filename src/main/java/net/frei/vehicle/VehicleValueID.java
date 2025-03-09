package net.frei.vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

@Embeddable
public class VehicleValueID implements Serializable {
    private static final long serialVersionUID = -9027702146713521476L;

    public static VehicleValueID of(String company, String model, LocalDate produced) {
	return new VehicleValueID(Vehicle.of(company, model, produced));
    }
    
    @OneToOne
    Vehicle vehicle;

    public VehicleValueID() {
    }

    public VehicleValueID(Vehicle vehicle) {
	super();
	this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	VehicleValueID vehicleID = (VehicleValueID) o;
	return Objects.equals(vehicle, vehicleID.vehicle);
    }

    @Override
    public int hashCode() {
	return Objects.hash(vehicle);
    }

}