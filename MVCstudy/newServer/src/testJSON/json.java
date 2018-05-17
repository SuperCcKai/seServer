package testJSON;

import java.io.FileReader;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class json {
	
	@Test
	public void test1() {
		JsonParser jParse = new JsonParser(); //´´½¨json½âÎöÆ÷
		try {
			JsonObject json = (JsonObject)jParse.parse(new FileReader
					("resource/weather.json"));
			System.out.println("resultcode: " + json.get("resultcode")
					.getAsInt());
			System.out.println("reason: " + json.get("reason")
					.getAsString());
			JsonObject result = json.get("result").getAsJsonObject();
			JsonObject today = result.get("today").getAsJsonObject();
			System.out.println("temperature: " + new String(today.get(
					"temperature").getAsString().getBytes("gbk"), "utf-8"));
			System.out.println("weather: " + new String(today.get("weather")
					.getAsString().getBytes("gbk"), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("");
		}
	}//test1()
	
	@Test
	public void test2() {
		try {
			JsonParser jParser = new JsonParser();
			JsonObject json = (JsonObject)jParser.parse(new FileReader(
					"resource/it.json"));
			System.out.println("cat: " + json.get("cat").getAsString());
			System.out.println("pop: " + json.get("pop").getAsBoolean());
			JsonArray language = json.get("language").getAsJsonArray();
			for(int i=0; i<language.size(); i++) {
				System.out.println("--------------------");
				JsonObject obj = language.get(i).getAsJsonObject();
				System.out.println("id: " + obj.get("id").getAsInt());
				System.out.println("ide: " + obj.get("ide").getAsString());
				System.out.println("name: " + obj.get("name").getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
