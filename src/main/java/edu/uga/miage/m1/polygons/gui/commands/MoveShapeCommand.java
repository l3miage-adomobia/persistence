package edu.uga.miage.m1.polygons.gui.commands;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class MoveShapeCommand implements Command{

    private SimpleShape simpleShape;
    private int diffX;
    private int diffY;

    public MoveShapeCommand(SimpleShape simpleShape, int x, int y) {
        this.simpleShape = simpleShape;
        this.diffX = x;
        this.diffY = y;
    }

    @Override
    public void execute() {
        simpleShape.move(diffX, diffY);
    }

}
