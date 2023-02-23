package ru.yandex.praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger = new Burger();
    @Mock
    Ingredient ingredient;
    @Mock
    Database database;
    private final List<Bun> buns = Arrays.asList(new Bun("grey bun",100.50F));

    private final String bunName = "grey bun";
    private final Ingredient ingredientSauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 100F);
    private final Ingredient ingredientFilling = new Ingredient(IngredientType.FILLING, "sausage", 300F);
    @Before
    public void setDefaultBun() {
        Mockito.when(database.availableBuns()).thenReturn(buns);
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(database.availableBuns().get(0));
        assertEquals(bunName, burger.bun.getName());
    }

    @Test
    public void checkGetPrice() {
        Mockito.when(ingredient.getPrice()).thenReturn(125.5F);
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        assertEquals(452, burger.getPrice(), 0);
    }

    @Test
    public void checkGetReceipt() {
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        String expected = "(==== grey bun ====)" + "\n"
                + "= sauce hot sauce =" + "\n"
                +"= filling sausage ="+ "\n"
                + "(==== grey bun ====)" + "\n\n"
                + "Price: 601,000000" + "\n";
        assertEquals(expected, burger.getReceipt());
    }

}