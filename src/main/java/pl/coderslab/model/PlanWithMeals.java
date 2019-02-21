package pl.coderslab.model;

public class PlanWithMeals {


    private String day_name;
    private int day_name_order;
    private String meal_name;
    private int meal_order;
    private String recipe_name;
    private int recipe_order;
    private int recipe_id;

    public PlanWithMeals(String day_name, int day_name_order, String meal_name, int meal_order, String recipe_name, int recipe_order, int recipe_id) {
        this.day_name = day_name;
        this.day_name_order = day_name_order;
        this.meal_name = meal_name;
        this.meal_order = meal_order;
        this.recipe_name = recipe_name;
        this.recipe_order = recipe_order;
        this.recipe_id = recipe_id;
    }

    public PlanWithMeals(){}

    @Override
    public String toString() {
        return "PlanWithMeals{" +
                "day_name='" + day_name + '\'' +
                ", day_name_order=" + day_name_order +
                ", meal_name='" + meal_name + '\'' +
                ", meal_order=" + meal_order +
                ", recipe_name='" + recipe_name + '\'' +
                ", recipe_order=" + recipe_order +
                ", recipe_id=" + recipe_id +
                '}';
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public int getDay_name_order() {
        return day_name_order;
    }

    public void setDay_name_order(int day_name_order) {
        this.day_name_order = day_name_order;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public int getMeal_order() {
        return meal_order;
    }

    public void setMeal_order(int meal_order) {
        this.meal_order = meal_order;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public int getRecipe_order() {
        return recipe_order;
    }

    public void setRecipe_order(int recipe_order) {
        this.recipe_order = recipe_order;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }






}

