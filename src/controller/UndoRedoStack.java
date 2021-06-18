package controller;

import com.sun.source.tree.LambdaExpressionTree;
import model.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoRedoStack {
    private Stack<List<Shape>> stack;
    private Stack<List<Shape>> redoStack;

    public UndoRedoStack() {
        stack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push() {
        stack.push(deepCopyShapeList());
    }

    public void pop() {
        if (stack.size() > 0) {
            redoStack.push(deepCopyShapeList());
            Shape.shapes = stack.pop();
            Shape.selectedShapes.clear();
            MouseHandler.draw();
        }
    }

    public void unpop() {
        if (redoStack.size() > 0) {
            Shape.shapes = redoStack.pop();
            Shape.selectedShapes.clear();
            MouseHandler.draw();
        }
    }

    private List<Shape> deepCopyShapeList() {
        List<Shape> copiedShapes = new ArrayList<>();
        for (Shape shape : Shape.shapes) {
            if (shape instanceof Group) {
                List<Shape> groupShapes = ((Group) shape).groupShapes;
                List<Shape> newGroupShapes = new ArrayList<>();
                for (Shape s : groupShapes) {
                    Point newStartPoint = new Point(s.getStartPoint());
                    Point newEndPoint = new Point(s.getEndPoint());
                    Shape newShape = new Shape(newStartPoint, newEndPoint, s.getPrimaryColor(), s.getSecondaryColor(), s.getShadingType(), s.getShapeStrategy());
                    newGroupShapes.add(newShape);
                }
                Group newGroup = new Group(newGroupShapes);
                copiedShapes.add(newGroup);
            } else {
                Point newStartPoint = new Point(shape.getStartPoint());
                Point newEndPoint = new Point(shape.getEndPoint());
                Shape copiedShape = new Shape(newStartPoint, newEndPoint, shape.getPrimaryColor(), shape.getSecondaryColor(), shape.getShadingType(), shape.getShapeStrategy());
                copiedShapes.add(copiedShape);
            }
        }
        return copiedShapes;
    }

}
