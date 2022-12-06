package model.menu;

import rstntSimObjects.Ingredient;

import java.util.Map;

public class Dessert extends AMenuItem {

	public Dessert(String name, int timeToMake, int timeToEat, double probabilityOfGettingOrdered, double probabilityOfBeingSentBack, Map<String, Integer> ingredients) {
		super(name, timeToMake, timeToEat, probabilityOfGettingOrdered, probabilityOfBeingSentBack, ingredients);
	}
}
