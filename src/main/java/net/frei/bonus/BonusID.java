package net.frei.bonus;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import net.frei.postcode.Postcode;
import net.frei.postcode.PostcodeValue;
import net.frei.postcode.PostcodeValueID;
import net.frei.vehicle.VehicleValue;
import net.frei.vehicle.VehicleValueID;

@Embeddable
public class BonusID implements Serializable {
    private static final long serialVersionUID = 4792335820954619806L;
    @ManyToOne(targetEntity = PostcodeValue.class)
    public PostcodeValue postcode;
    @ManyToOne(targetEntity = VehicleValue.class)
    public VehicleValue vehicle;
    public float usage;

    public static BonusID of(String company, String model, LocalDateTime produced, float vehicleValue, int plz, float postcodeValue, float usage) {
	return new BonusID(new PostcodeValue(
		new PostcodeValueID(new Postcode()), postcodeValue), new VehicleValue(VehicleValueID.of(company, model, produced), vehicleValue));
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

    public float getUsage() {
        return usage;
    }

    public void setUsage(float usage) {
        this.usage = usage;
    }

}