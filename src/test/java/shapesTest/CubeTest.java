package shapesTest;

import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CubeTest {

    @Test
    public void testGetX() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Vérifie la valeur de X
        assertEquals(25, cube.getX());
    }

    @Test
    public void testGetY() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Vérifie la valeur de Y
        assertEquals(50, cube.getY()*2);
    }

    @Test
    public void testMove() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Déplace le cube de (dx, dy)
        int dx = 10;
        int dy = 20;
        cube.move(dx, dy);

        // Vérifie les nouvelles valeurs de X et Y après le déplacement
        assertEquals(50 + dx, cube.getX() + 25);
        assertEquals(50 + dy, cube.getY() + 25);
    }

    @Test
    public void testSetmX() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        cube.setmX(newX);

        // Vérifie la nouvelle valeur de X
        assertEquals(newX, cube.getX());
    }

    @Test
    public void testSetmY() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        cube.setmY(newY);

        // Vérifie la nouvelle valeur de Y
        assertEquals(newY, cube.getY());
    }

    @Test
    public void testGetType() {
        // Crée un objet Cube
        Cube cube = new Cube(50, 50, 50);

        // Vérifie le type de la forme
        assertEquals("cube", cube.getType());
    }
}