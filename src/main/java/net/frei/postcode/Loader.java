package net.frei.postcode;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.opencsv.CSVReader;

@Configuration
public class Loader {

    /**
     * Will be run as soon as a full startup has happened, and parses the data from
     * the postcodes.csv
     * 
     * @param service
     * @return
     */

    @Bean
    CommandLineRunner initDatabase(PostcodeService service, PostcodeValueService valueService) {
	return A -> {
	    System.out.println("Starting to parse the postcodes.csv");
	    CSVReader csv = new CSVReader(new FileReader(System.getProperty("user.dir") + "/postcodes.csv"));
	    List<String[]> list = csv.readAll();

	    if (list == null)
		return;
	    long time = System.nanoTime();
	    int i = 1, n = list.size();
	    for (; i < n; ++i) {
		String[] set = list.get(i);
		try {
		    /**
		     * Try Catch as our primary Key (POSTLEITZAHL) needs to parsed again and should
		     * never be null/invalid Normalizing Strings to remove unrealistic white spaces.
		     */

		    String ISO_3166_1_ALPHA_2 = StringUtils.normalizeSpace(set[0]);
		    String ISO_3166_1_ALPHA_2_REGION_CODE = StringUtils.normalizeSpace(set[1]);
		    String REGION1 = StringUtils.normalizeSpace(set[2]);
		    String REGION2 = StringUtils.normalizeSpace(set[3]);
		    String REGION3 = StringUtils.normalizeSpace(set[4]);
		    String REGION4 = StringUtils.normalizeSpace(set[5]);
		    int POSTLEITZAHL = Integer.parseInt(StringUtils.normalizeSpace(set[6]));
		    String ORT = StringUtils.normalizeSpace(set[7]);
		    String AREA1 = StringUtils.normalizeSpace(set[8]);
		    String AREA2 = StringUtils.normalizeSpace(set[9]);

		    double LATITUDE = Double.parseDouble(set[10]);
		    double LONGITUDE = Double.parseDouble(set[11]);
		    String TIMEZONE = StringUtils.normalizeSpace(set[12]);
		    boolean SOMMERZEIT = StringUtils.normalizeSpace(set[14]).equals("true");
		    String ACTIVE = StringUtils.normalizeSpace(set[15]);
		    if (isNotNull(ISO_3166_1_ALPHA_2, ISO_3166_1_ALPHA_2_REGION_CODE, REGION1, REGION2, REGION3,
			    REGION4, ORT, TIMEZONE, ACTIVE) && POSTLEITZAHL != 0 && LATITUDE != 0 && LONGITUDE != 0) {
			//We do it via setter in the case of future addditions
			Postcode postcode = new Postcode();
			postcode.setISO_3166_1_ALPHA_2(ISO_3166_1_ALPHA_2);
			postcode.setISO_3166_1_ALPHA_2_REGION_CODE(ISO_3166_1_ALPHA_2_REGION_CODE);
			postcode.setREGION1(REGION1);
			postcode.setREGION2(REGION2);
			postcode.setREGION3(REGION3);
			postcode.setREGION4(REGION4);
			postcode.setPOSTLEITZAHL(POSTLEITZAHL);
			postcode.setORT(ORT);
			postcode.setAREA1(AREA1);
			postcode.setAREA2(AREA2);
			postcode.setLATITUDE(LATITUDE);
			postcode.setLONGITUDE(LONGITUDE);
			postcode.setZEITZONE(TIMEZONE);
			postcode.setSOMMERZEIT(SOMMERZEIT);
			postcode.setACTIVE(ACTIVE);
			try {
			    service.addPostcode(postcode);

				valueService.addPostcodeValue(new PostcodeValue(new PostcodeValueID(postcode),
					0.75f + (Objects.hashCode(postcode) % 50) * 0.01f));
			}catch(Exception e) {
			    
			}
			
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "csv contains improper information");
		}
	    }

	    System.out.println(n + " entries processed");
	    System.out.println(service.getPostcodes().size() + " entries inside");
	    System.out.println(toLagString("required time for parsing", System.nanoTime() - time));

	};
    }

    public static String toLagString(String prefix, long lag) {
	return new StringBuilder(prefix).append(": ").append(lag / 1000000000L).append("s, ").append(lag / 1000000L)
		.append("ms, ").append(lag / 1000L).append("µs, ").append(lag).append("ns").toString();
    }

    public boolean isNotNull(String... objs) {
	for (String obj : objs) {
//	    if (!(Objects.nonNull(obj) && !obj.isBlank())) //Only fully valid entries allowed
	    if (!Objects.nonNull(obj))
		return false;
	}
	return true;
    }
}
