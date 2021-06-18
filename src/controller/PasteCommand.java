package controller;

import model.Shape;

public class PasteCommand implements ICommand{

	@Override
	public void run() {
		for (Shape shape : Shape.copiedShapes) {
			Shape.shapes.add(shape);
		}
		MouseHandler.draw();
	}
	
}
