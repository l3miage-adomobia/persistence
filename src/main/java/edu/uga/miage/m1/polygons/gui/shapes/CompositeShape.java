package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape implements SimpleShape {

    private List<SimpleShape> shapeList = new ArrayList<>();

    public void addToShape(SimpleShape simpleShape){
        shapeList.add(simpleShape);
    }

    public void removeToShape(SimpleShape simpleShape){
        shapeList.remove(simpleShape);
    }

    public List<SimpleShape> getShapeList() {
        return shapeList;
    }

    @Override
    public void accept(Visitor visitor) {visitor.visit(this);}

    @Override
    public void draw(Graphics2D g2) {
        for (SimpleShape simpleShape : shapeList){
            simpleShape.draw(g2);
        }
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setmY(int mY) {
        // laissé vide
    }

    @Override
    public void setmX(int mX) {
        //laissé vide
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void move(int diffX, int diffY) {
        // laissé vide
    }

    @Override
    public String getType() {
        return "compositeShape";
    }
}
