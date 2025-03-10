package net.frei.vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Embeddable
public class VehicleID implements Serializable {
    private static final long serialVersionUID = -9027702146713521476L;
    @NotBlank
    String company;
    @NotBlank
    String model;
    @PastOrPresent
    LocalDate produced;

    public static VehicleID of(String company, String model, LocalDate produced) {
	return new VehicleID(company, model, produced);
    }
    
    public VehicleID() {
    }

    public VehicleID(String company, String model, LocalDate produced) {
        this.company = company;
        this.model = model;
        this.produced =produced;
    }

    public String getCompany() {
        return company;
    }

    public VehicleID setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleID setModel(String model) {
        this.model = model;
        return this;
    }

    public LocalDate getProduced() {
        return produced;
    }

    public VehicleID setProduced(LocalDate produced) {
        this.produced = produced;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleID vehicleID = (VehicleID) o;
        return Objects.equals(company, vehicleID.company) &&
               Objects.equals(model, vehicleID.model) &&
               Objects.equals(produced, vehicleID.produced);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, model, produced);
    }
    
}