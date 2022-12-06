package rstntSimObjects;


import model.menu.Entree;
import rstntSimObjects.utilities.OrderUtility;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
    public Entree pickMainCourse(model.menu.Menu menu){
        return OrderUtility.pickItemFromMap(menu.getEntrees());
    }
}
