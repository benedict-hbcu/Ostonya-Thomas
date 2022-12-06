package model.menu;

import com.google.gson.annotations.Expose;
import rstntSimObjects.Ingredient;

import java.util.Map;

public abstract class AMenuItem {
	@Expose
	private final String name;
	@Expose
	private final int timeToMake;
	@Expose
	private final int timeToEat;
	@Expose
	private final double probabilityOfGettingOrdered;
	@Expose
	private final double probabilityOfBeingSentBack;
	@Expose
	private final Map<String, Integer> ingredients;

	public AMenuItem(String name, int timeToMake, int timeToEat, double probabilityOfGettingOrdered, double probabilityOfBeingSentBack, Map<String, Integer> ingredients) {
		this.name = name;
		this.timeToMake = timeToMake;
		this.timeToEat = timeToEat;
		this.probabilityOfGettingOrdered = probabilityOfGettingOrdered;
		this.probabilityOfBeingSentBack = probabilityOfBeingSentBack;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public int getTimeToEat() {
		return timeToEat;
	}
	public double getProbabilityOfGettingOrdered() {
		return probabilityOfGettingOrdered;
	}

	public double getProbabilityOfBeingSentBack() {
		return probabilityOfBeingSentBack;
	}

	public Map<String, Integer> getIngredients() {
		return ingredients;
	}
}
