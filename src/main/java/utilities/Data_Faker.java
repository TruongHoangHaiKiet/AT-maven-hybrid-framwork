package utilities;

import com.github.javafaker.Faker;

public class Data_Faker {
	Faker faker;
	
	public Data_Faker() {
		faker = new Faker();
	}
	
	public static Data_Faker getDataFaker() {
		return new Data_Faker();
	}
	
	public String getFirstName() {
		return faker.address().firstName();
	}
	
	public String getLastName() {
		return faker.address().lastName();
	}
	
	public String getCityName() {
		return faker.address().cityName();
	}
	
	public String getStateName() {
		return faker.address().state();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
	
	public String getStreetAddress() {
		return faker.address().streetAddress();
	}
	
	public String getCellPhone() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
	
	
	
}
