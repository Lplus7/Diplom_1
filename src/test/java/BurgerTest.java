import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    Burger burger = new Burger();
    Ingredient [] ingredients = new Ingredient[] {
            new Ingredient(SAUCE, "Кетчуп", 5),
            new Ingredient(FILLING, "Соленый огурец", 20),
            new Ingredient(FILLING, "Котлета пожаренная на углях", 140),
            new Ingredient(FILLING, "Сыр Чеддер", 40),
            new Ingredient(FILLING, "Свежий помидор", 30),
            new Ingredient(FILLING, "Салат Айсберг", 10),
            new Ingredient(FILLING, "Лук", 5),
            new Ingredient(SAUCE, "Фирменный соус", 15),
    };

    public final static Double DELTA = 0.00001;

    @Test
    public void setBunsTest() {
        burger.setBuns(new Bun("Половинка свежей булочки", 30));
        assertEquals("Половинка свежей булочки", burger.bun.getName());
        assertEquals(30, burger.bun.getPrice(), DELTA);
    }

    @Test
    public void addIngredientsToIngredientList() {
        for (int i = 0; i < ingredients.length; i++) {
            burger.addIngredient(ingredients[i]);
            assertEquals("Ингредиент отличается от ожидаемого",
                    ingredients[i], burger.ingredients.get(i));
        }
    }

    @Test
    public void removeIngredientFromIngredientList() {
        for (int i = 0; i < ingredients.length; i++) {
            burger.addIngredient(ingredients[i]);
        }
        burger.removeIngredient(6);
        assertEquals("Ингредиентов больше, чем должно быть",
                7, burger.ingredients.size());
    }

    @Test
    public void moveIngredientInIngredientList() {
        for (int i = 0; i < ingredients.length; i++) {
            burger.addIngredient(ingredients[i]);
        }
        burger.moveIngredient(0, 7);
        burger.moveIngredient(6, 0);
        assertEquals("Порядок ингредиентов отличается от ожидаемого",
                ingredients[0], burger.ingredients.get(7));
        assertEquals("Порядок ингредиентов отличается от ожидаемого",
                ingredients[7], burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(30F);
        Mockito.when(ingredient.getPrice()).thenReturn(10F);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float actual = burger.getPrice();
        float expected = 70;
        assertEquals("Цена бургера не соответствует ожидаемой", expected, actual, DELTA);
    }

    @Test
    public void getReceipt() throws IOException {
        String reference = Files.readString(Paths.get("src/main/java/praktikum/Receipt"));
        for (int i = 0; i < ingredients.length; i++) {
            burger.addIngredient(ingredients[i]);
        }
        burger.setBuns(new Bun("Половинка свежей булочки", 30));
        String actual = burger.getReceipt();
        assertEquals("Рецепт отличается от ожидаемого", reference, actual);
    }

}