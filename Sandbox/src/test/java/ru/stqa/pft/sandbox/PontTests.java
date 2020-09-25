package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PontTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(5, 7);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(5, 7);
        assert p1.distance(p2) == 0.0;
    }

}
