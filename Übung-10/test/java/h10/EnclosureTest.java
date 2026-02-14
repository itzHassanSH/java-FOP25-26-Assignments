package h10;

import h10.animals.Animal;
import h10.animals.birds.Penguin;
import h10.animals.mammals.JugglingLion;
import h10.animals.mammals.Lion;
import org.junit.jupiter.api.Test;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class EnclosureTest {

    @Test
    @StudentImplementationRequired("H10.5.1")
    public void testAnyMatch() {
        // TODO: H10.5.1
        // Create Lions
        Lion lion1 = new Lion("Bob", 5);
        Lion lion2 = new Lion("Jeff", 4);
        JugglingLion lion3 = new JugglingLion("Jess", 4);

        // Add the normal Lions
        GroundEnclosure<Lion> lionEnclosure = new GroundEnclosure<>();
        lionEnclosure.getAnimals().push(lion1);
        lionEnclosure.getAnimals().push(lion2);

        // Test with only normal lions
        assertFalse(lionEnclosure.anyMatch(Enclosure.HAS_2_LEGS));

        // Add Jess the juggling lion
        lionEnclosure.getAnimals().push(lion3);

        // Test again but with Jess inside Enclosure
        assertTrue(lionEnclosure.anyMatch(Enclosure.HAS_2_LEGS));

    }


    @Test
    @StudentImplementationRequired("H10.5.2")
    public void testMapFunctional() {
        // Here's an example for an expected sign after all transformations for 3 penguins
        String exampleSign = "Adult Penguin Pool: " +
            "\nSir Pip (3 years old)" +
            "\nSir Penny (4 years old)" +
            "\nSir Percy (2 years old)";

        // TODO: H10.5.2
        // Create Penguins
        Penguin penguin1 = new Penguin("Pepe", 1);
        Penguin penguin2 = new Penguin("Pengu", 2);
        Penguin penguin3 = new Penguin("Poppy", 3);

        // Create their enclosure and place them inside
        WaterEnclosure<Penguin> penguinWaterEnclosure = new WaterEnclosure<>();
        penguinWaterEnclosure.getAnimals().push(penguin1);
        penguinWaterEnclosure.getAnimals().push(penguin2);
        penguinWaterEnclosure.getAnimals().push(penguin3);

        // Mapper function (übersichtlicher zu machen)
        Function<Penguin, Penguin> mapper = penguin -> {
            return new Penguin("Sir %s".formatted(penguin.getName()), penguin.getAge()+1);
        };
        // Supplier Function
        Supplier<GroundEnclosure<Penguin>> supplier = GroundEnclosure::new;
        // New penguin ground enclosure
        GroundEnclosure<Penguin> penguinGroundEnclosure = penguinWaterEnclosure.mapFunctional(supplier, mapper);

        // New penguin Water enclosure
        WaterEnclosure<Penguin> penguinWaterEnclosure2 = penguinGroundEnclosure.mapFunctional(WaterEnclosure::new, p -> p);

        // Reducing all the penguins to a single string:
        String r = "Adult Penguin Pool: ";
        BiFunction<String, Animal, String> biMapper = Enclosure.SIGN_WITH_ANIMAL_NAME;
        String reducedPenguins = penguinWaterEnclosure2.reduce(r, biMapper);

        // Expected String
        String expectedReducedPenguins = "Adult Penguin Pool: \nSir Pepe (2 years old)\nSir Pengu (3 years old)\nSir Poppy (4 years old)";

        assertEquals(expectedReducedPenguins, reducedPenguins);

    }
}
