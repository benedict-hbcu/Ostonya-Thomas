package model.menu;

//import java.util.List;
import java.util.Map;

public class Menu {
	private final Map<Appetizer,Double> appetizers;
	private final Map<Entree,Double> entrees;
	private final Map<Dessert,Double> desserts;

	public Menu(Map<Appetizer,Double> appetizers, Map<Entree,Double> entrees, Map<Dessert,Double> desserts) {
		this.appetizers = appetizers;
		this.entrees = entrees;
		this.desserts = desserts;
	}

	public Map<Appetizer, Double> getAppetizers() {
		return appetizers;
	}

	public Map<Entree, Double> getEntrees() {
		return entrees;
	}

	public Map<Dessert, Double> getDesserts() {
		return desserts;
	}
}
