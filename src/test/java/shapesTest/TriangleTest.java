package shapesTest;

import static org.junit.Assert.assertEquals;

import com.example.shapes.*;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void testGetX() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // V�rifie la valeur de X
        assertEquals(25, triangle.getX());
    }

    @Test
    public void testGetY() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // V�rifie la valeur de Y
        assertEquals(25, triangle.getY());
    }
    @Test
    public void testMove() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // D�place le triangle de (dx, dy)
        int dx = 10;
        int dy = 20;
        triangle.move(dx, dy);

        // V�rifie les nouvelles valeurs de X et Y apr�s le d�placement
        assertEquals(25 + dx, triangle.getX());
        assertEquals(25 + dy, triangle.getY());
    }

    @Test
    public void testSetmX() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        triangle.setmX(newX);

        // V�rifie la nouvelle valeur de X
        assertEquals(newX, triangle.getX());
    }

    @Test
    public void testSetmY() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        triangle.setmY(newY);

        // V�rifie la nouvelle valeur de Y
        assertEquals(newY, triangle.getY());
    }

    @Test
    public void testGetType() {
        // Cr�e un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // V�rifie le type de la forme
        assertEquals("triangle", triangle.getType());
    }
}

