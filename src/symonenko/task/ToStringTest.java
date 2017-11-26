package symonenko.task;

import org.junit.*;
import symonenko.task.ShoppingCart.Item;

public class ToStringTest extends junit.framework.TestCase {

    private Item cola, beer, milk, water;
    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        cart.addItem("Coca-Cola", 2.25f, 1, Item.Type.SALE);
        cart.addItem("Bud Light", 3.5f, 1, Item.Type.DISCOUNT);
        cart.addItem("Best Milk For Kids", 1.75f,  2, Item.Type.SECOND);
        cart.addItem("Super Clean Refreshing Water", 9.99f,  1, Item.Type.REGULAR);
    }

    @Test
    public void testEmptyCart() {
        ShoppingCart emptyCart = new ShoppingCart();
        assertEquals("No items.", emptyCart.toString());
    }

    @Test
    public void testFilledCart() {
        assertEquals(" # Item                   Price Quan. Discount     Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Coca-Cola              $2.25    1      80%       $.45\n" +
                        " 2 Bud Light              $3.50    1      10%      $3.15\n" +
                        " 3 Best Milk For Kids     $1.75    2      50%      $1.75\n" +
                        " 4 Super Clean Refre...   $9.99    1        -      $9.99\n" +
                        "---------------------------------------------------------\n" +
                        " 4                                                $15.34",
                cart.toString());
    }

    @Test
    public void testHeader() {
        assertEquals( " # Item                   Price Quan. Discount     Total" +
                "\n---------------------------------------------------------",
                getHeader(cart.toString()));
    }

    @Test
    public void testBody() {
        assertEquals(" 1 Coca-Cola              $2.25    1      80%       $.45\n" +
                        " 2 Bud Light              $3.50    1      10%      $3.15\n" +
                        " 3 Best Milk For Kids     $1.75    2      50%      $1.75\n" +
                        " 4 Super Clean Refre...   $9.99    1        -      $9.99",
                getBody(cart.toString()));
    }

    @Test
    public void testFooter() {
        assertEquals("---------------------------------------------------------\n" +
                        " 4                                                $15.34",
                getFooter(cart.toString()));
    }


    private String getHeader(String text) {
        int headerEnd = text.indexOf("\n", text.indexOf("\n") + 1);
        return text.substring(0, headerEnd);
    }

    private String getBody(String text) {
        int first = text.indexOf("\n");
        int second = text.indexOf("\n", first + 1);
        String body = text.substring(second + 1);
        body = body.substring(0, body.indexOf(getFooter(text))-1);
        return body;
    }

    private String getFooter(String text) {
        return text.substring(text.lastIndexOf("---------------------------------------------------------"));
    }

}