package utils.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import player.Player;

public class GsonUtils {
	public static String SkatersToJson(final ArrayList<Player> listOfSkaters) {
		Gson gson = new Gson();
		String json = gson.toJson(listOfSkaters);
		return json;
	}
	
	public static void WriteJsonFile(final String list) {
		try {
			FileWriter writer = new FileWriter("C:/temp/test.json");
			writer.write(list);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<Player> SkatersFromJson(final String json) {
		Gson gson = new Gson();
		ArrayList<Player> listOfSkater = gson.fromJson(json, new TypeToken<ArrayList<Player>>(){}.getType());
		return listOfSkater;
	}
	
	public static String ReadJsonFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/temp/test.json"));
			String ret = br.lines().collect(Collectors.joining());
			br.close();
			return ret;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
