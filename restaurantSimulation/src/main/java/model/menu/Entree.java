package model.menu;

import rstntSimObjects.Ingredient;

import java.util.Map;

public class Entree extends AMenuItem {

	public Entree(String name, int timeToMake, int timeToEat, double probabilityOfGettingOrdered, double probabilityOfBeingSentBack, Map<String, Integer> ingredients) {
		super(name, timeToMake, timeToEat, probabilityOfGettingOrdered, probabilityOfBeingSentBack, ingredients);
	}

}
