package day14;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNanoFactory {

    private NanoFactory getFirstExample() {
        return NanoFactory.getFactoryFromRecipes(
                "10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL");
    }

    private NanoFactory getSecondExample() {
        return NanoFactory.getFactoryFromRecipes(
                "9 ORE => 2 A\n" +
                        "8 ORE => 3 B\n" +
                        "7 ORE => 5 C\n" +
                        "3 A, 4 B => 1 AB\n" +
                        "5 B, 7 C => 1 BC\n" +
                        "4 C, 1 A => 1 CA\n" +
                        "2 AB, 3 BC, 4 CA => 1 FUEL"
        );
    }

    private NanoFactory getThirdExample() {
        return NanoFactory.getFactoryFromRecipes(
                "157 ORE => 5 NZVS\n" +
                        "165 ORE => 6 DCFZ\n" +
                        "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
                        "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
                        "179 ORE => 7 PSHF\n" +
                        "177 ORE => 5 HKGWZ\n" +
                        "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
                        "165 ORE => 2 GPVTF\n" +
                        "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT"
        );
    }

    private NanoFactory getFourthExample() {
        return NanoFactory.getFactoryFromRecipes(
                "2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG\n" +
                        "17 NVRVD, 3 JNWZP => 8 VPVL\n" +
                        "53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL\n" +
                        "22 VJHF, 37 MNCFX => 5 FWMGM\n" +
                        "139 ORE => 4 NVRVD\n" +
                        "144 ORE => 7 JNWZP\n" +
                        "5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC\n" +
                        "5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV\n" +
                        "145 ORE => 6 MNCFX\n" +
                        "1 NVRVD => 8 CXFTF\n" +
                        "1 VJHF, 6 MNCFX => 4 RFSQX\n" +
                        "176 ORE => 6 VJHF\n"
        );
    }

    private  NanoFactory getFifthExample() {
        return NanoFactory.getFactoryFromRecipes(
                "171 ORE => 8 CNZTR\n" +
                        "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\n" +
                        "114 ORE => 4 BHXH\n" +
                        "14 VRPVC => 6 BMBT\n" +
                        "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\n" +
                        "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\n" +
                        "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\n" +
                        "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\n" +
                        "5 BMBT => 4 WPTQ\n" +
                        "189 ORE => 9 KTJDG\n" +
                        "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\n" +
                        "12 VRPVC, 27 CNZTR => 2 XDBXC\n" +
                        "15 KTJDG, 12 BHXH => 5 XCVML\n" +
                        "3 BHXH, 2 VRPVC => 7 MZWV\n" +
                        "121 ORE => 7 VRPVC\n" +
                        "7 XCVML => 6 RJRHP\n" +
                        "5 BHXH, 4 VRPVC => 5 LTCX\n"
        );
    }


    private NanoFactory getExampleTwoRecipes() {
        Recipe r = new Recipe();
        r.addIngredient("A", 3);
        r.addIngredient("B", 4);
        r.setOutput("AB", 1);
        Recipe r2 = new Recipe();
        r2.addIngredient("B", 5);
        r2.addIngredient("C", 7);
        r2.setOutput("BC", 1);
        NanoFactory nf = new NanoFactory();
        nf.addRecipe(r);
        nf.addRecipe(r2);
        return nf;
    }


    @Test
    public void testRecipeFinder() {
        Recipe r = new Recipe();
        r.addIngredient("A", 3);
        r.addIngredient("B", 4);
        r.setOutput("AB", 1);
        Recipe r2 = new Recipe();
        r2.addIngredient("B", 5);
        r2.addIngredient("C", 7);
        r2.setOutput("BC", 1);
        NanoFactory nf = new NanoFactory();
        nf.addRecipe(r);
        nf.addRecipe(r2);
        assertEquals(r2, nf.getRecipeProducingOutput("BC"));
        assertEquals(r, nf.getRecipeProducingOutput("AB"));
    }

    @Test
    public void testNoRecipeFound() {
        NanoFactory exampleTwoRecipes = getExampleTwoRecipes();
        assertThrows(NoSuchElementException.class, () -> exampleTwoRecipes.getRecipeProducingOutput("NO"));
    }

    @Test
    public void testGetRecipeFromString() {
        NanoFactory nf = getFirstExample();
        assertEquals("10 ORE => 10 A", nf.getRecipe(0).toString());
        assertEquals("7 A, 1 E => 1 FUEL", nf.getRecipe(5).toString());
    }

    @Test
    public void testGetRawMaterialsWithoutComplication() {
        NanoFactory nf = getFirstExample();
        assertEquals(10, nf.getRawMaterials("A", 1));
        assertEquals(10+1, nf.getRawMaterials("C",1));
    }

    @Test
    public void testGetRawMaterialsWithCombination() {
        assertEquals(31, getFirstExample().getRawMaterials("FUEL", 1));
        assertEquals(165, getSecondExample().getRawMaterials("FUEL", 1));
        assertEquals(13312, getThirdExample().getRawMaterials("FUEL", 1));
        assertEquals(180697 , getFourthExample().getRawMaterials("FUEL", 1));
        assertEquals(2210736 , getFifthExample().getRawMaterials("FUEL", 1));
    }

    @Test
    public void testGetOutputQuantity() {
        long trillion = 1_000_000_000_000L;
        assertEquals(82_892_753L, getThirdExample().getOutputQuantity("FUEL", trillion));
        assertEquals(5_586_022L, getFourthExample().getOutputQuantity("FUEL", trillion));
        assertEquals(460_664L, getFifthExample().getOutputQuantity("FUEL", trillion));
    }
}
