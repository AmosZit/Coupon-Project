package coupon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationUtils {

	// Email Regex java
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validateEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	public static boolean isValidPassword(String password) {
		if (password.length() >= 8) {
			return true;
		}
		return false;
	}

	public static boolean isValidNumberPhone(String phone) {
		if (phone.length() == 10) {
			return true;
		}
		return false;
	}

	public static boolean isValidName(String name) {
		if (name.length() > 2) {
			return true;
		}
		return false;
	}

}
