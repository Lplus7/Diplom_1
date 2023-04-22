import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {
    private IngredientType type;
    private String name;
    private float price;
    public final static Double DELTA = 0.00001;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {SAUCE, "Кетчуп", 100},
                {SAUCE, "Barbecue", 0.0000001F},
                {FILLING, "Котлета с очень длинным названием, таким длинным, " +
                        "что такое название может даже куда-нибудь не уместиться " +
                        "и придется переносить это название на следующую строку", 9999999999.99F},
                {null, "", 0},
                {SAUCE, null, 3},
                {FILLING, "गøᛖน∂ổ₱ $ o4ΣHb $tPaHHbIΜ ÑÂ3BΔHueM u $πÊц-символами!", -1}
        };
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        String expected = name;
        String actual = ingredient.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        float expected = price;
        float actual = ingredient.getPrice();
        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType expected = type;
        IngredientType actual = ingredient.getType();
        assertEquals(expected, actual);
    }

}
