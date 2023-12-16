import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

public class JDrawingFrameTest {

    private JDrawingFrame drawingFrame;

    @Before
    public void setUp() {
        drawingFrame = new JDrawingFrame("TestFrame");
    }

    @Test
    public void testGetShapeList() {
        // Create a mock shape list
        List<String[]> mockShapeList = new ArrayList<>();
        mockShapeList.add(new String[]{"square", "50", "50"});
        mockShapeList.add(new String[]{"circle", "100", "100"});

        // Set the mock shape list using reflection (for testing purposes)
        java.lang.reflect.Field field = null;
        try {
            field = JDrawingFrame.class.getDeclaredField("mShapeList");
            field.setAccessible(true);
            field.set(drawingFrame, mockShapeList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Get the shape list using the getter
        List<String[]> retrievedShapeList = drawingFrame.getShapeList();

        // Check if the retrieved list is the same as the original list
        assertEquals(mockShapeList, retrievedShapeList);
    }

    @Test
    public void testGetListOfShapes() {
        // Create a mock shape list
        SimpleShape mockShape = new Square(50, 50);
        List<SimpleShape> mockListOfShapes = new ArrayList<>();
        mockListOfShapes.add(mockShape);

        // Set the mock list of shapes using reflection (for testing purposes)
        java.lang.reflect.Field field = null;
        try {
            field = JDrawingFrame.class.getDeclaredField("listOfShapes");
            field.setAccessible(true);
            field.set(drawingFrame, mockListOfShapes);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Get the list of shapes using the getter
        List<SimpleShape> retrievedListOfShapes = drawingFrame.getListOfShapes();

        // Check if the retrieved list is the same as the original list
        assertEquals(mockListOfShapes, retrievedListOfShapes);
    }

    @Test
    public void testSetSquareButton() {
        // Create a mock JButton
        JButton mockButton = mock(JButton.class);

        // Set the square button using the method
        drawingFrame.setSquareButton(mockButton);

        // Get the square button using the getButtons method
        JButton retrievedButton = drawingFrame.getButtons().get(JDrawingFrame.Shapes.SQUARE);

        // Check if the retrieved button is the same as the original button
        assertEquals(mockButton, retrievedButton);
    }

    @Test
    public void testSetTriangleButton() {
        // Create a mock JButton
        JButton mockButton = mock(JButton.class);

        // Set the triangle button using the method
        drawingFrame.setTriangleButton(mockButton);

        // Get the triangle button using the Shapes enum
        JButton retrievedButton = drawingFrame.getButtons().get(JDrawingFrame.Shapes.TRIANGLE);

        // Check if the retrieved button is the same as the original button
        assertEquals(mockButton, retrievedButton);
    }

    @Test
    public void testSetCircleButton() {
        // Create a mock JButton
        JButton mockButton = mock(JButton.class);

        // Set the circle button using the method
        drawingFrame.setCircleButton(mockButton);

        // Get the circle button using the Shapes enum
        JButton retrievedButton = drawingFrame.getButtons().get(JDrawingFrame.Shapes.CIRCLE);

        // Check if the retrieved button is the same as the original button
        assertEquals(mockButton, retrievedButton);
    }

    @Test
    public void testGetSelectedShape() {
        // Set the selected shape using the method
        JDrawingFrame.Shapes selectedShape = JDrawingFrame.Shapes.SQUARE;
        drawingFrame.setSelectedShape(selectedShape);

        // Get the selected shape using the getter
        JDrawingFrame.Shapes retrievedShape = drawingFrame.getSelectedShape();

        // Check if the retrieved shape is the same as the original shape
        assertEquals(selectedShape, retrievedShape);
    }
}