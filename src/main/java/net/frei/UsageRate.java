package net.frei;

public class UsageRate {

    public static float getUsageFactor(float distance) {
	if (distance > 5000f && distance <= 10000f) {
	    return 1.0f;
	} else if (distance > 10000f && distance <= 20000f) {
	    return 1.5f;
	} else if (distance > 20000f) {
	    return 2.0f;
	} else {
	    return 0.5f;
	}
    }
}
