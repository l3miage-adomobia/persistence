/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.singleshape.CubePanel;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Cube implements SimpleShape, Visitable {

    int mX;

    int mY;

    int mT;

    public Cube(int mt,int x, int y) {
        mX = x - 25;
        mY = y - 25;
        mT = mt;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        CubePanel c = new CubePanel(100, getX(), getY());
        c.paintComponent(g2);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    public int getX() {
        int x = this.mX + 1;
        x--;
        return x;
    }

    public int getY() { return this.mY;}
    public void setmY(int mY) {this.mY = mY;}
    public void setmX(int mX) {this.mX = mX;}

    public boolean contains(int x, int y) {
        int size = 50;
        Rectangle2D cubeBounds = new Rectangle(mX, mY, size, size);
        return cubeBounds.contains(x, y);
    }
    public void move(int diffX, int diffY){
        this.mX += diffX;
        this.mY += diffY;
    }
    public String getType(){
        return "cube";
    }

}
