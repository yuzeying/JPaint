package controller;

import model.Shape;
import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.*;
import java.util.List;

class Group extends Shape {
    List<Shape> groupShapes;

    Group(List<Shape> groupShapes) {
        super(new Point(0,0), new Point(100,100), ShapeColor.BLACK, ShapeColor.BLACK, ShapeShadingType.FILLED_IN, new GroupStrategy(groupShapes));

        this.groupShapes = groupShapes;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Shape shape : groupShapes) {
            if (shape.getStartPoint().x < minX) {
                minX = shape.getStartPoint().x;
            }
            if (shape.getEndPoint().x < minX) {
                minX = shape.getEndPoint().x;
            }
            if (shape.getStartPoint().x > maxX) {
                maxX = shape.getStartPoint().x;
            }
            if (shape.getEndPoint().x > maxX) {
                maxX = shape.getEndPoint().x;
            }
            if (shape.getStartPoint().y < minY) {
                minY = shape.getStartPoint().y;
            }
            if (shape.getEndPoint().y < minY) {
                minY = shape.getEndPoint().y;
            }
            if (shape.getStartPoint().y > maxY) {
                maxY = shape.getStartPoint().y;
            }
            if (shape.getEndPoint().y > maxY) {
                maxY = shape.getEndPoint().y;
            }
        }
        Point startPoint = new Point(minX, minY);
        Point endPoint = new Point(maxX, maxY);

        getStartPoint().x = startPoint.x;
        getStartPoint().y = startPoint.y;
        getEndPoint().x = endPoint.x;
        getEndPoint().y = endPoint.y;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        getStartPoint().x += deltaX;
        getStartPoint().y += deltaY;
        getEndPoint().x += deltaX;
        getEndPoint().y += deltaY;
        for (Shape shape : groupShapes) {
            shape.move(deltaX, deltaY);
        }
    }
}
