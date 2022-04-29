package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 5);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }

    @Test
    public void testMinusDistance() {
        Point p1 = new Point(-2, -3);
        Point p2 = new Point(-4, -5);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }

    @Test
    public void testNullDistance() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(3, 3);
        Assert.assertEquals(p1.distance(p2),0.0);
    }
}
