package data.techpanda;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class AccountDataJson {
	
	// Read Data from JSON file
	public static AccountDataJson getAccountData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.DATA_PATH + "Account.json"), AccountDataJson.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static class Login {
		@JsonProperty("firstName")
		private String firstName;
		
		@JsonProperty("lastName")
		private String lastName;
		
		@JsonProperty("emailAddress")
		private String emailAddress;
		
		@JsonProperty("webMail")
		private String webMail;
		
		@JsonProperty("passWord")
		private String passWord;
	}
	
	static class Register{
		@JsonProperty("phoneNumber")
		private String phoneNumber;
	}
	
	@JsonProperty("Login")
	private Login login;
	
	@JsonProperty("Register")
	private Register register;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("city")
	private String city;

	public String getFirstName() {
		return login.firstName;
	}

	public String getLastName() {
		return login.lastName;
	}

	public String getEmailAddress() {
		return login.emailAddress;
	}

	public String getWebMail() {
		return login.webMail;
	}

	public String getPassWord() {
		return login.passWord;
	}
	
	public String getFullName() {
		return login.firstName + " " + login.lastName;
	}
	
	public String getPhoneNumber() {
		return register.phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}
}
