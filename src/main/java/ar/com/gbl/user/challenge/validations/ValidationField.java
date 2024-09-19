package ar.com.gbl.user.challenge.validations;

public final class ValidationField {
	
	public static boolean isValidEmail(String email) {
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}
	
	public static boolean isValidPassword(String pass) {		
		return pass.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,12}");
	}

}
