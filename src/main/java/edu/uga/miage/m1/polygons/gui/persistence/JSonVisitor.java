package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;

import java.util.List;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private StringBuilder representation = new StringBuilder();
    public JSonVisitor() {
            // Ce constructeur est laissé vide intentionnellement.
    }

    private static final String SHAPE_STRUCTURE = ",\"y\":" ;
    private static final String END_STRING = "},";
    @Override
    public void visit(Circle circle) {
        appendRepresentation("{ \"type\": \"circle\",\"x\":" + circle.getX() + SHAPE_STRUCTURE + circle.getY() + END_STRING);
    }

    @Override
    public void visit(Square square) {
        appendRepresentation("{\"type\": \"square\",\"x\":" + square.getX() + SHAPE_STRUCTURE + square.getY() + END_STRING);
    }

    @Override
    public void visit(Triangle triangle) {
        appendRepresentation("{\"type\": \"triangle\",\"x\":" + triangle.getX() + SHAPE_STRUCTURE + triangle.getY() + END_STRING);
    }

    @Override
    public void visit(Cube cube) {
        appendRepresentation("{\"type\": \"triangle\",\"x\":" + cube.getX() + SHAPE_STRUCTURE + cube.getY() + END_STRING);
    }

    public void visit(CompositeShape compositeShape) {
        appendRepresentation("{ \"type\": \"composite\", \"shapes\": [");

        // Parcourir la liste de shapes dans le compositeShape
        List<SimpleShape> shapes = compositeShape.getShapeList();
        for (int i = 0; i < shapes.size(); i++) {
            // Utiliser le visiteur pour obtenir la représentation JSON de chaque forme
            shapes.get(i).accept(this);
            // Ajouter une virgule si ce n'est pas la dernière forme
            if (i < shapes.size() - 1) {
                appendRepresentation(",");
            }
        }

        appendRepresentation("]},");
    }
    private void appendRepresentation(String representation) {
        this.representation.append(representation);
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return this.representation.toString();
    }
}
