package lab6_317;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Class to save user and UtilityCompany to json files to keep data persistent
public class DataController {
	private static Gson gson = new Gson();
	
	//Saves a user to "user.json"
	public static void saveUser(User user) throws IOException {
		String json = gson.toJson(user);
		FileWriter writer = new FileWriter("user.json");
		writer.write(json);
		writer.close();
	}
	
	//Loads a user from "user.json"
	public static User loadUser() throws IOException {
		FileReader reader = new FileReader("user.json");
		return gson.fromJson(reader, User.class);
	}
	
	//Saves a UtilityCompany to "utilityCompany.json"
	public static void saveUtilityCompany(UtilityCompany utilityCompany) throws IOException	{
		String json = gson.toJson(utilityCompany);
		FileWriter writer = new FileWriter("utilityCompany.json");
		writer.write(json);
		writer.close();
	}
	
	//Loads a UtilityCompany from "utilityCompany.json"
	public static UtilityCompany loadUtilityCompany() throws IOException {
		FileReader reader = new FileReader("utilityCompany.json");
		return gson.fromJson(reader, UtilityCompany.class);
	}
}
