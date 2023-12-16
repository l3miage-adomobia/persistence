package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;

import java.util.List;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    public XMLVisitor() {
            // Ce constructeur est laissé vide intentionnellement.
    }

    private static final String SHAPE_CLOSURE = "</y></shape>";

    @Override
    public void visit(Circle circle) {
        representation = "<shape><type>circle</type><x>" + circle.getX() + "</x>"  +
                "<y>" + circle.getY() + SHAPE_CLOSURE;
    }
    private String endString = "</x><y>";
    @Override
    public void visit(Square square) {
        representation ="<shape><type>square</type><x>" + square.getX() + endString+ square.getY() + SHAPE_CLOSURE;
    }

    @Override
    public void visit(Triangle triangle) {
        representation = "<shape><type>triangle</type><x>" + triangle.getX() + endString + triangle.getY() + SHAPE_CLOSURE;
        
    }

    @Override
    public void visit(Cube cube){
        representation = "<shape><type>Cube</type><x>" + cube.getX() + endString + cube.getY() +  SHAPE_CLOSURE;
    }

    @Override
    public void visit(CompositeShape compositeShape) {
        StringBuilder tempRepresentation = new StringBuilder("<shape><type>composite</type><shapes>");

        // Parcourir la liste de shapes dans le compositeShape
        List<SimpleShape> shapes = compositeShape.getShapeList();
        for (SimpleShape shape : shapes) {
            // Utiliser le visiteur pour obtenir la représentation XML de chaque forme
            shape.accept(this);
            // Ajouter la représentation à la chaîne temporaire
            tempRepresentation.append(this.representation);
        }

        tempRepresentation.append("</shapes></shape>");
        this.representation = tempRepresentation.toString();
    }

    /**
     * @return the representation in JSon example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return this.representation;
    }
}
