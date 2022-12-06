package Generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.menu.JsonMenuHelper;
import model.menu.Menu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MenuGenerator {
	public MenuGenerator() throws IOException {
	}
	public static String readMenuJsonIntoString() throws IOException {
		String json = Files.readString(new File("/Users/bcinternship2/git/Ostonya-Thomas/restaurantSimulation/src/main/resources/menu.json").toPath());
		return json;
	}


	public static Menu generateMenu() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonMenuHelper helper = gson.fromJson(readMenuJsonIntoString(), JsonMenuHelper.class);
		Menu menu = helper.convertToMenu();
		return menu;
	}



	//public static Menu generateMenu(String menuJsonString) throws IOException {
	//	return new Gson().fromJson(menuJsonString, Menu.class);
	//}
	//private static final String MENU_FILE_NAME = "menu.json";

	//public static String readMenuJsonIntoString() throws IOException {
	//	return new String(Files.readAllBytes(Path.of(MenuGenerator.class.getClassLoader().getResource(MENU_FILE_NAME).getPath())));
	//}



}
