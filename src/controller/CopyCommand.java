package controller;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import model.Shape;

public class CopyCommand implements ICommand {

	@Override
	public void run() {
		Shape.copiedShapes.clear();
		for (Shape shape : Shape.selectedShapes) {
			if (shape instanceof Group) {
				List<Shape> groupShapes = ((Group) shape).groupShapes;
				List<Shape> newGroupShapes = new ArrayList<>();

				int deltaX = Math.abs(150);
				int deltaY = Math.abs(150);

				for (Shape s : groupShapes) {
					Point newStartPoint = new Point(s.getStartPoint().x, s.getStartPoint().y);
					Point newEndPoint = new Point(s.getEndPoint().x, s.getEndPoint().y);
					Shape newShape = new Shape(newStartPoint, newEndPoint, s.getPrimaryColor(), s.getSecondaryColor(), s.getShadingType(), s.getShapeStrategy());
					newGroupShapes.add(newShape);
				}

				Group newGroup = new Group(newGroupShapes);
				newGroup.move(deltaX, deltaY);

				Shape.copiedShapes.add(newGroup);
			} else {
				Point newStartPoint = new Point();
				Point newEndPoint = new Point();
				newStartPoint.x = shape.getStartPoint().x + 150;
				newStartPoint.y = shape.getStartPoint().y - 50;
				newEndPoint.x = newStartPoint.x + shape.getEndPoint().x - shape.getStartPoint().x;
				newEndPoint.y = newStartPoint.y + shape.getEndPoint().y - shape.getStartPoint().y;
				Shape copiedShape = new Shape(newStartPoint, newEndPoint, shape.getPrimaryColor(), shape.getSecondaryColor(), shape.getShadingType(), shape.getShapeStrategy());
				Shape.copiedShapes.add(copiedShape);
			}
		} 
	}

}
