import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private String name;
    private float price;
    public final static Double DELTA = 0.00001;


    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {"Обычная булочка", 100},
                {"English bun", 0.0000001F},
                {null, 300},
                {"Булочка с очень длинным названием, таким длинным, " +
                        "что такое название может даже куда-нибудь не уместиться " +
                        "и придется переносить это название на следующую строку", 9999999999.99F},
                {"БуJIo4ka $ o4ΣHb $tPaHHbIΜ ÑÂ3BΔHueM u $πÊц-символами!", -1},
                {"", 0}
        };
    }

    @Test
    public void setBunName() {
        Bun bun = new Bun(name, price);
        String expected = name;
        String actual = bun.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void setBunPrice() {
        Bun bun = new Bun(name, price);
        float expected = price;
        float actual = bun.getPrice();
        assertEquals(expected, actual, DELTA);
    }
}
