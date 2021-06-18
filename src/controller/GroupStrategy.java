package controller;

import model.IShapeStrategy;
import model.Rectangle;
import model.Shape;
import view.interfaces.PaintCanvasBase;
import java.util.List;

import java.awt.*;

public class GroupStrategy implements IShapeStrategy {
    private List<Shape> shapes;

    public GroupStrategy(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void fillShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
        for (Shape shape : shapes) {
            shape.draw(paintCanvas);
        }
    }

    @Override
    public void outlineShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
        for (Shape shape : shapes) {
            shape.draw(paintCanvas);
        }
    }

    @Override
    public void dashShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
        Decorator shapeDecorator = new Decorator(new Rectangle());
        shapeDecorator.outline(paintCanvas, startPoint, endPoint);
    }
}
