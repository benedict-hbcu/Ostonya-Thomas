package model.menu;

import rstntSimObjects.Ingredient;

import java.util.Map;

public class Appetizer extends AMenuItem {

	public Appetizer(String name, int timeToMake, int timeToEat, double probabilityOfGettingOrdered, double probabilityOfBeingSentBack, Map<String, Integer> ingredients) {
		super(name, timeToMake, timeToEat, probabilityOfGettingOrdered, probabilityOfBeingSentBack, ingredients);
	}
}
