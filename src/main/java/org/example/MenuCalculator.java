package org.example;

import java.util.Objects;

public class MenuCalculator {
    // calcule le prix payé par l'utilisateur dans le restaurant, en fonction de type de
    // repas qu'il prend (assiette ou sandwich), de la taille de la boisson (petit, moyen, grand), du dessert et
    // de son type (normal ou special) et si il prend un café ou pas (yes ou no).
    // les prix sont fixes pour chaque type de chose mais des réductions peuvent s'appliquer
    // si cela rentre dans une formule!

    static int getMenuPrice(String mealType, String mealName, String beverage, String beverageSize, String dessert, String menuType, String coffee) throws Exception {
        if (mealName == null || mealName.isEmpty()) throw new Exception("Meal mealName cannot be empty");

        // prix total à payer pour le client
        int formulaPrice = getFormulasPrices(mealType, beverageSize, menuType);
        if (formulaPrice != -1) return formulaPrice;

        int totalPrice = 0;
        totalPrice += getMealTypePrice(mealType);
        totalPrice += getBeveragePrice(beverageSize);
        totalPrice += getMenuTypePrice(menuType);
        totalPrice += getCoffeePrice(mealType, beverageSize, menuType, coffee);

        return totalPrice;
    }

    static private int getFormulasPrices(String mealType, String beverageSize, String menuType) throws Exception {
        if (beverageSize.equals("moyen") && menuType.equals("normal")) {
            System.out.print("Prix Formule Standard appliquée ");
            if (mealType.equals("assiette")) return 18;
            else if (mealType.equals("sandwich")) return 13;
        } else if (beverageSize.equals("grand") && menuType.equals("special")) {
            System.out.print("Prix Formule Max appliquée ");
            if (mealType.equals("assiette")) return 21;
            else if (mealType.equals("sandwich")) return 16;
        }
        return -1;
    }

    static private int getMealTypePrice(String type) throws Exception {
        return switch (type) {
            case "assiette" -> 15;
            case "sandwich" -> 10;
            default -> throw new Exception("meal type must be 'assiette' or 'sandwich'");
        };
    }

    static private int getBeveragePrice(String type) {
        return switch (type) {
            case "petit" -> 2;
            case "moyen" -> 3;
            case "grand" -> 4;
            default -> 0;
        };
    }

    static private int getMenuTypePrice(String type) {
        return switch (type) {
            case "normal" -> 2;
            case "special" -> 4;
            default -> 0;
        };
    }

    static private int getCoffeePrice(String mealType, String beverageSize, String menuType, String coffee) {
        if (coffee.equals("yes")) {
            if (mealType.equals("assiette") && beverageSize.equals("moyen") && menuType.equals("normal")) {
                System.out.print(" avec café offert!");
            } else {
                return 1;
            }
        }
        return 0;
    }
}
