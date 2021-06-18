package controller;

import java.awt.Point;

import model.Shape;

public class DeleteCommand implements ICommand {

	@Override
	public void run() {
		for (Shape shape : Shape.selectedShapes) {
			Shape.shapes.remove(shape);
			Shape.copiedShapes.remove(shape);
		}
		Shape.selectedShapes.clear();
		MouseHandler.draw();
	}

}
