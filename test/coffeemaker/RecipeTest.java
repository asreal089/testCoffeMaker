package coffeemaker;

import coffeemaker.exceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {

    private Recipe r3;

    @BeforeEach
    public  void setUp() throws Exception {
        r3 = new Recipe("Latte",75,3,1,1,0);
    }

    @Test
    public void testCreateValidRecipe() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,4,0,1,0);
    }

    @Test
    public void testCreateInvalidRecipeAmtCoffee() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,-1,0,1,0));
    }

    @Test
    public void testCreateInvalidRecipeAmtChocolate() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,0,0,1,-1));
    }

    @Test
    public void testCreateInvalidRecipeAmtMilk() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",60,1,-2,1,0));
    }

    @Test
    public void testCreateInvalidRecipeAmtSugar() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,0,-1,0));
    }

    @Test
    public void testCreateInvalidCostRecipeAmtSugar() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",-50,1,0,1,0));
    }

    @Test
    public void testCreateInvalidNameRecipeAmtSugar() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("",50,1,0,1,0));
    }

    @Test
    public void testSetAmtMilk() throws InvalidValueException {
        r3.setAmtMilk(10);
        assertEquals(10, r3.getAmtMilk());
    }

    @Test
    public void testSetAmtSugar() throws InvalidValueException {
        r3.setAmtSugar(10);
        assertEquals(10, r3.getAmtSugar());
    }

    @Test
    public void testSetAmtChocolate() throws InvalidValueException {
        r3.setAmtChocolate(10);
        assertEquals(10, r3.getAmtChocolate());
    }

    @Test
    public void testSetAmtCoffe() throws InvalidValueException {
        r3.setAmtCoffee(10);
        assertEquals(10, r3.getAmtCoffee());
    }

    @Test
    public void testSetInvalidAmtMilk() throws InvalidValueException {
        assertThrows(InvalidValueException.class, ()->r3.setAmtMilk(-10));
    }

    @Test
    public void testSetInvalidAmtSugar() throws InvalidValueException {
        assertThrows(InvalidValueException.class, ()->r3.setAmtSugar(-10));
    }

    @Test
    public void testSetInvalidAmtChocolate() throws InvalidValueException {
        assertThrows(InvalidValueException.class, ()->r3.setAmtChocolate(-10));
    }

    @Test
    public void testSetInvalidAmtCoffe() throws InvalidValueException {
        assertThrows(InvalidValueException.class, ()->r3.setAmtCoffee(-10));
    }

}
