package lab6_317;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Need to save time, currentDeposit, currentWithdraw, checking balance, currentTransfer, saving balance
//Username, password, account number, bill payment history (last 3), next bill payment amount/due date
public class DataController {
	private static Gson gson = new Gson();
	
	public static void saveUser(User user) throws IOException {
		String json = gson.toJson(user);
		FileWriter writer = new FileWriter("user.json");
		writer.write(json);
		writer.close();
	}
	
	public static User loadUser() throws IOException {
		FileReader reader = new FileReader("user.json");
		return gson.fromJson(reader, User.class);
	}
	
	public static void saveUtilityCompany(UtilityCompany utilityCompany) throws IOException	{
		String json = gson.toJson(utilityCompany);
		FileWriter writer = new FileWriter("utilityCompany.json");
		writer.write(json);
		writer.close();
	}
	
	public static UtilityCompany loadUtilityCompany() throws IOException {
		FileReader reader = new FileReader("utilityCompany.json");
		return gson.fromJson(reader, UtilityCompany.class);
	}
}
