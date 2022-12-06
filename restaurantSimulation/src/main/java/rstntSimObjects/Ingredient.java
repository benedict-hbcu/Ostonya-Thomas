package rstntSimObjects;

public enum Ingredient {
    ;
    private String name;
    private double price;

   Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
   }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
