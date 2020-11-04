package coffeemaker;

import coffeemaker.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoffeeMakerTest {

    private CoffeeMaker CM;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;

    @BeforeEach
    public  void setUp() throws Exception {
        CM = new CoffeeMaker();
        r1 = new Recipe("Coffee",50,4,0,1,0);
        r2 = new Recipe("Hot Chocolate",75,0,3,1,3);
        r3 = new Recipe("Latte",75,3,1,1,0);
        r4 = new Recipe("Mix",100,1,2,1,2);
    }

    @Test
    public void testAddOneRecipe() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
    }

    @Test
    public void testAddThreeRecipes() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesSameName() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setName(r1.getName());
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(r2));
    }

    /*
    * testes de faz receitas com mais ingredientes do que o disponivel no estoque
    * */

    @Test
    public void testAddInvalidAmounthOfMilk() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        int maxMilk = CM.checkMilkInventory();
        r1.setAmtMilk(maxMilk + 1);
        boolean ok = CM.addRecipe(r1);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), r1.getPrice()));
    }
    @Test
    public void testAddInvalidAmounthOfCoffe() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        int maxCoffe = CM.checkCoffeeInventory();
        r1.setAmtMilk(maxCoffe + 1);
        boolean ok = CM.addRecipe(r1);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), r1.getPrice()));
    }

    @Test
    public void testAddInvalidAmounthOfSuggar() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        int maxSuggar = CM.checkSugarInventory();
        r1.setAmtMilk(maxSuggar + 1);
        boolean ok = CM.addRecipe(r1);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), r1.getPrice()));
    }

    @Test
    public void testAddInvalidAmounthOfSChocolate() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException, InventoryException, RecipeException {
        int maxChocolate = CM.checkChocolateInventory();
        r1.setAmtMilk(maxChocolate + 1);
        boolean ok = CM.addRecipe(r1);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), r1.getPrice()));
    }

    /*
    * testa delete recipe
    * */

    @Test
    public void testDeleteRecipe() throws AmountOfRecipeException, DuplicatedRecipeException, RecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.deleteRecipe("Coffee");
        assertTrue(ok);
        Vector<Recipe> receitas = CM.getRecipes();
        boolean containReceitaDeletada = receitas.contains(r1);
        assertFalse(containReceitaDeletada);
    }

    @Test
    public void testDeleteInvalidRecipe() throws  AmountOfRecipeException, DuplicatedRecipeException, RecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(RecipeException.class, () -> CM.deleteRecipe("Batata"));
    }
    /*
    * testes de add to inventory
    * */

    @Test
    public void testAddCoffeeInventory() throws InvalidValueException{
        int qtdInicial = CM.checkCoffeeInventory();
        CM.addCoffeeInventory(10);
        assertEquals(qtdInicial + 10, CM.checkCoffeeInventory());
    }

    @Test
    public void testAddInvalidCoffeeInventory() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(-10));
    }

    @Test
    public void testAddMilkInventory() throws InvalidValueException{
        int qtdInicial = CM.checkMilkInventory();
        CM.addMilkInventory(10);
        assertEquals(qtdInicial + 10, CM.checkMilkInventory());
    }

    @Test
    public void testAddInvalidMilkInventory() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(-10));
    }

    @Test
    public void testAddChocolateInventory() throws InvalidValueException{
        int qtdInicial = CM.checkChocolateInventory();
        CM.addChocolateInventory(10);
        assertEquals(qtdInicial + 10, CM.checkChocolateInventory());
    }

    @Test
    public void testAddInvalidChocolateInventory() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(-10));
    }

    @Test
    public void testAddSugarInventory() throws InvalidValueException{
        int qtdInicial = CM.checkSugarInventory();
        CM.addSugarInventory(10);
        assertEquals(qtdInicial + 10, CM.checkSugarInventory());
    }

    @Test
    public void testAddInvalidSugarInventory() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(-10));
    }

    /*
    *  Test check inventory nao sei se é teste válido
    * */

    @Test
    public void testCheckSugarInventory(){
        CM.checkSugarInventory();
    }

    @Test
    public void testCheckCoffeeInventory(){
        CM.checkCoffeeInventory();
    }

    @Test
    public void testCheckMilkInventory(){
        CM.checkMilkInventory();
    }

    @Test
    public void testCheckChocolateInventory(){
        CM.checkChocolateInventory();
    }

    /*
    * test make coffe
    * */

    @Test
    public void testMakeCoffeeInvalidAmountOfMoney() throws RecipeException, InventoryException, InvalidValueException, AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException{
        CM.addRecipe(r1);
        assertThrows(InsufficientAmountOfMoneyException.class, () -> CM.makeCoffee("Coffee", 10));
    }

    @Test
    public void testMakeCoffeeInvalidRecipeNameOfMoney() throws RecipeException, InventoryException, InvalidValueException, AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException{
        CM.addRecipe(r1);
        assertThrows(RecipeException.class, () -> CM.makeCoffee("Blabla", 100));
    }

    /*
     * test make coffe - correct change
     * */

    @Test
    public void testMakeCoffeeCorrectChange() throws RecipeException, InventoryException, InvalidValueException, AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException{
        CM.addRecipe(r1);
        int price = r1.getPrice();
        int change = CM.makeCoffee("Coffee", 200);
        assertEquals(price+change, 200);
    }
    /*
     * test add more than 3 recipies
     * */
    @Test
    public void testAddMoreThanTreeRecepies() throws  AmountOfRecipeException, DuplicatedRecipeException{
        CM.addRecipe(r1);
        CM.addRecipe(r2);
        CM.addRecipe(r3);
        assertThrows(AmountOfRecipeException.class, ()->CM.addRecipe(r4));
    }

    @Test
    public void testOutPutAddMoreThanTreeRecepies() throws  AmountOfRecipeException, DuplicatedRecipeException{
        CM.addRecipe(r1);
        CM.addRecipe(r2);
        CM.addRecipe(r3);
        boolean notOK = CM.addRecipe(r4);
        assertFalse(notOK);
    }
    @Test
    public void testContainsTreeAddedRecepies() throws  AmountOfRecipeException, DuplicatedRecipeException{
        CM.addRecipe(r1);
        CM.addRecipe(r2);
        CM.addRecipe(r3);
        assertTrue(CM.getRecipes().contains(r1));
        assertTrue(CM.getRecipes().contains(r2));
        assertTrue(CM.getRecipes().contains(r3));
    }
    /*
     * teste de invetario apos fazer coffe
     * */
    @Test
    public void testInventoryCoffeAfterMakeCoffe() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, InvalidValueException, InventoryException, RecipeException {
        CM.addRecipe(r1);
        int qtdCoffe = CM.checkCoffeeInventory();
        System.out.println(CM.checkCoffeeInventory());

        r1.getAmtCoffee();
        CM.makeCoffee(r1.getName(), r1.getPrice());
        System.out.println(CM.checkCoffeeInventory());
        assertEquals(qtdCoffe-r1.getAmtCoffee(), CM.checkCoffeeInventory());
    }

    @Test
    public void testInventoryMilkAfterMakeCoffe() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, InvalidValueException, InventoryException, RecipeException {
        CM.addRecipe(r4);
        int qtdMilk = CM.checkMilkInventory();
        r4.getAmtMilk();
        CM.makeCoffee(r4.getName(), r4.getPrice());
        assertEquals(qtdMilk-r4.getAmtMilk(), CM.checkMilkInventory());
    }

    @Test
    public void testInventoryChocolateAfterMakeCoffe() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, InvalidValueException, InventoryException, RecipeException {
        CM.addRecipe(r4);
        int qtdChocolate = CM.checkChocolateInventory();
        r4.getAmtChocolate();
        CM.makeCoffee(r4.getName(), r4.getPrice());
        assertEquals(qtdChocolate-r4.getAmtChocolate(), CM.checkChocolateInventory());
    }

    @Test
    public void testInventorySugarAfterMakeCoffe() throws AmountOfRecipeException, DuplicatedRecipeException, InsufficientAmountOfMoneyException, InvalidValueException, InventoryException, RecipeException {
        CM.addRecipe(r4);
        int qtdSugar = CM.checkSugarInventory();
        r4.getAmtSugar();
        CM.makeCoffee(r4.getName(), r4.getPrice());
        assertEquals(qtdSugar-r4.getAmtSugar(), CM.checkSugarInventory());
    }


}
