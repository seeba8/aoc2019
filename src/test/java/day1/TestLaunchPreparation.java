package day1;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLaunchPreparation {

    @Test
    public void testFuelRequirement() {
        assertEquals(2, LaunchPreparation.getFuelRequirement(12));
        assertEquals(2, LaunchPreparation.getFuelRequirement(14));
        assertEquals(654, LaunchPreparation.getFuelRequirement(1969));
        assertEquals(33583, LaunchPreparation.getFuelRequirement(100756));
    }

    @Test
    public void testHolisticFuelRequirement() {
        assertEquals(2, LaunchPreparation.getHolisticFuelRequirement(14));
        assertEquals(966, LaunchPreparation.getHolisticFuelRequirement(1969));
        assertEquals(50346, LaunchPreparation.getHolisticFuelRequirement(100756));
    }
}
