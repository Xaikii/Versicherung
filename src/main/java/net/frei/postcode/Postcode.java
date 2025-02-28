package net.frei.postcode;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

@Entity
@Table
public class Postcode implements Serializable {
    /**
     * Has to be Serializable to be an Entity. Sequencing, if we dont provide a
     * value in our Primary Key (POSTLEITZAHL) it will generate one. With Sequence
     * we can also have custom generation of it.
     */
    private static final long serialVersionUID = 4066971436844961042L;

    String ISO_3166_1_ALPHA_2;
    String ISO_3166_1_ALPHA_2_REGION_CODE;
    String REGION1;
    String REGION2;
    String REGION3;
    String REGION4;
    @Positive
    @Digits(integer = 5, fraction = 0)
    @Id
    /**
     * Has to be positive, can have at may 5 digits and 0 decimal points.
     */
    Integer POSTLEITZAHL; // PRIMARY KEY
    String ORT;
    String AREA1;
    String AREA2;
    double LATITUDE;
    double LONGITUDE;
    String ZEITZONE;
    boolean SOMMERZEIT;
    String ACTIVE;

    public Postcode() {
	this.ISO_3166_1_ALPHA_2_REGION_CODE = "N/A";
	this.REGION1 = "N/A";
	this.REGION3 = "N/A";
	this.REGION4 = "N/A";
	this.ORT = "N/A";
    }

    public Postcode(String ISO_3166_1_ALPHA_2, String ISO_3166_1_ALPHA_2_REGION_CODE, String REGION1, String REGION2,
	    String REGION3, String REGION4, @Positive @Digits(integer = 5, fraction = 0) Integer POSTLEITZAHL,
	    String ORT, String AREA1, String AREA2, Double LATITUDE, Double LONGITUDE, String ZEITZONE,
	    Boolean SOMMERZEIT, String ACTIVE) {
	this.ISO_3166_1_ALPHA_2 = ISO_3166_1_ALPHA_2;
	this.ISO_3166_1_ALPHA_2_REGION_CODE = ISO_3166_1_ALPHA_2_REGION_CODE;
	this.REGION1 = REGION1;
	this.REGION2 = REGION2;
	this.REGION3 = REGION3;
	this.REGION4 = REGION4;
	this.POSTLEITZAHL = POSTLEITZAHL;
	this.ORT = ORT;
	this.AREA1 = AREA1;
	this.AREA2 = AREA2;
	this.LATITUDE = LATITUDE;
	this.LONGITUDE = LONGITUDE;
	this.ZEITZONE = ZEITZONE;
	this.SOMMERZEIT = SOMMERZEIT;
	this.ACTIVE = ACTIVE;
    }

    @Override
    public String toString() {
	return "%d : %s - %s:%s:%s %s".formatted(POSTLEITZAHL, ISO_3166_1_ALPHA_2_REGION_CODE, REGION1, REGION3,
		REGION4, ORT);
    }

    public String getISO_3166_1_ALPHA_2() {
	return ISO_3166_1_ALPHA_2;
    }

    public void setISO_3166_1_ALPHA_2(String iSO_3166_1_ALPHA_2) {
	this.ISO_3166_1_ALPHA_2 = iSO_3166_1_ALPHA_2;
    }

    public String getISO_3166_1_ALPHA_2_REGION_CODE() {
	return ISO_3166_1_ALPHA_2_REGION_CODE;
    }

    public void setISO_3166_1_ALPHA_2_REGION_CODE(String iSO_3166_1_ALPHA_2_REGION_CODE) {
	this.ISO_3166_1_ALPHA_2_REGION_CODE = iSO_3166_1_ALPHA_2_REGION_CODE;
    }

    public String getREGION1() {
	return REGION1;
    }

    public void setREGION1(String rEGION1) {
	this.REGION1 = rEGION1;
    }

    public String getREGION2() {
	return REGION2;
    }

    public void setREGION2(String rEGION2) {
	this.REGION2 = rEGION2;
    }

    public String getREGION3() {
	return REGION3;
    }

    public void setREGION3(String rEGION3) {
	this.REGION3 = rEGION3;
    }

    public String getREGION4() {
	return REGION4;
    }

    public void setREGION4(String rEGION4) {
	this.REGION4 = rEGION4;
    }

    public Integer getPOSTLEITZAHL() {
	return POSTLEITZAHL;
    }

    public void setPOSTLEITZAHL(Integer pOSTLEITZAHL) {
	this.POSTLEITZAHL = pOSTLEITZAHL;
    }

    public String getORT() {
	return ORT;
    }

    public void setORT(String oRT) {
	this.ORT = oRT;
    }

    public String getAREA1() {
	return AREA1;
    }

    public void setAREA1(String aREA1) {
	this.AREA1 = aREA1;
    }

    public String getAREA2() {
	return AREA2;
    }

    public void setAREA2(String aREA2) {
	this.AREA2 = aREA2;
    }

    public Double getLATITUDE() {
	return LATITUDE;
    }

    public void setLATITUDE(Double lATITUDE) {
	this.LATITUDE = lATITUDE;
    }

    public Double getLONGITUDE() {
	return LONGITUDE;
    }

    public void setLONGITUDE(Double lONGITUDE) {
	this.LONGITUDE = lONGITUDE;
    }

    public String getZEITZONE() {
	return ZEITZONE;
    }

    public void setZEITZONE(String zEITZONE) {
	this.ZEITZONE = zEITZONE;
    }

    public Boolean getSOMMERZEIT() {
	return SOMMERZEIT;
    }

    public void setSOMMERZEIT(Boolean sOMMERZEIT) {
	this.SOMMERZEIT = sOMMERZEIT;
    }

    public String getACTIVE() {
	return ACTIVE;
    }

    public void setACTIVE(String aCTIVE) {
	this.ACTIVE = aCTIVE;
    }

}
