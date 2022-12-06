package model.menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMenuHelper implements Serializable {

    @Expose private List<Appetizer> appetizers;
    @Expose private List<Entree> mains;
    @Expose private List<Dessert> desserts;

    public Menu convertToMenu() {
        Map<Appetizer, Double> apps = new HashMap<>();
        for (Appetizer app : this.appetizers) {
            apps.put(app, app.getProbabilityOfGettingOrdered());
        }
        Map<Entree, Double> mains = new HashMap<>();
        for (Entree main : this.mains) {
            mains.put(main, main.getProbabilityOfGettingOrdered());
        }
        Map<Dessert, Double> desserts = new HashMap<>();
        for (Dessert app : this.desserts) {
            desserts.put(app, app.getProbabilityOfGettingOrdered());
        }

        return new Menu(apps, mains, desserts);
    }

    public static void main(String[] args) throws IOException {
        String json = Files.readString(new File("/Users/bcinternship2/git/Ostonya-Thomas/restaurantSimulation/src/main/resources/menu.json").toPath());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonMenuHelper helper = gson.fromJson(json, JsonMenuHelper.class);
        Menu menu = helper.convertToMenu();
        System.out.println(menu);
    }
}
