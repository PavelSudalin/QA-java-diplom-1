import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger = new Burger();
    @Mock
    Database databaseMock;
    List<Bun> bunMock = Arrays.asList(new Bun("grey bun",100.50F));
    Database database = new Database();
    List<Bun> buns = database.availableBuns();
    List<Ingredient> ingredients = database.availableIngredients();

    @Test
    public void checkSetBunsMock() {
        Mockito.when(databaseMock.availableBuns()).thenReturn(bunMock);
        burger.setBuns(databaseMock.availableBuns().get(0));
        assertEquals(databaseMock.availableBuns().get(0), burger.bun);
    }

    @Test
    public void checkAddIngredients() {
        ingredients.forEach((n) -> burger.addIngredient(n));
        assertEquals(ingredients, burger.ingredients);
    }

    @Test
    public void checkRemoveIngredients() {
        List<Ingredient> expectedRemove = Arrays.asList(ingredients.get(0));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.removeIngredient(1);
        assertEquals(expectedRemove, burger.ingredients);
    }

    @Test
    public void checkMoveIngredient() {
        List<Ingredient> expectedMove = Arrays.asList(ingredients.get(1), ingredients.get(0));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.moveIngredient(0,1);
        assertEquals(expectedMove, burger.ingredients);
    }

    @Test
    public void checkGetPrice() {
        burger.setBuns(buns.get(1));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        assertEquals(700, burger.getPrice(), 0);
    }

    @Test
    public void checkGetReceipt() {
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(5));
        burger.setBuns(buns.get(2));
        String expected = "(==== red bun ====)" + "\n"
                + "= sauce hot sauce =" + "\n"
                +"= filling sausage ="+ "\n"
                + "(==== red bun ====)" + "\n\n"
                + "Price: 1000,000000" + "\n";
        assertEquals(expected, burger.getReceipt());
    }

}
