package controller;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Oval;
import model.Rectangle;
import model.Shape;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.Triangle;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter{
	private Point startPoint;
	private Point endPoint;
	private static PaintCanvasBase staticPaintCanvas;
	private ApplicationState appState;
	private static final MouseHandler SINGLE_INSTANCE = new MouseHandler();

	private MouseHandler() {}
	public static MouseHandler getInstance() {
		return SINGLE_INSTANCE;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		System.out.println("StartPoint " + startPoint.toString());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
		if(appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
			Shape.undoRedoStack.push();
			handleDrawMode();
		}else if ((appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT)) {
			handleSelectMode();
		}else { //move mode
			Shape.undoRedoStack.push();
			handleMoveMode();
		}
	}
	
	private void handleDrawMode() {
 		Shape currentshape = null;
		if (appState.getActiveShapeType() == ShapeType.RECTANGLE) {
			currentshape = new Shape(startPoint, endPoint, appState.getActivePrimaryColor(), appState.getActiveSecondaryColor(), appState.getActiveShapeShadingType(), new Rectangle());
		} else if (appState.getActiveShapeType() == ShapeType.ELLIPSE) {
			currentshape = new Shape(startPoint, endPoint, appState.getActivePrimaryColor(), appState.getActiveSecondaryColor(), appState.getActiveShapeShadingType(), new Oval());
		} else {
			currentshape = new Shape(startPoint, endPoint, appState.getActivePrimaryColor(), appState.getActiveSecondaryColor(), appState.getActiveShapeShadingType(), new Triangle());
		}
		Shape.shapes.add(currentshape);
		draw();
	}
	
	private void handleSelectMode() {
		Shape.selectedShapes.clear(); 
		for(Shape shape: Shape.shapes) {
			 if(shape.collides(startPoint, endPoint)) {
				 Shape.selectedShapes.add(shape);
				 System.out.println("found shape");
			 }
		 }
		draw();
	}
	
	private void handleMoveMode() {
		int deltax = endPoint.x - startPoint.x;
		int deltay = endPoint.y - startPoint.y;
		for(Shape shape : Shape.selectedShapes) {
			shape.move(deltax, deltay);
		}
		draw();
	}
	
	public static void draw() {
		Graphics2D g = MouseHandler.staticPaintCanvas.getGraphics2D();
		g.clearRect(0, 0, MouseHandler.staticPaintCanvas.getWidth(), MouseHandler.staticPaintCanvas.getHeight());
		for (Shape shape : Shape.shapes) {
			shape.draw(MouseHandler.staticPaintCanvas);
		}
		for (Shape shape : Shape.selectedShapes) {
			Decorator currentDecorator = new Decorator(shape.getShapeStrategy());
			currentDecorator.outline(MouseHandler.staticPaintCanvas, shape.getStartPoint(), shape.getEndPoint());
 		}
	} 
	
	
	public void setApplicationState( ApplicationState appState) {
		this.appState = appState;
	}
	public void setPaintCanvas(PaintCanvasBase paintCanvas) {
		MouseHandler.staticPaintCanvas = paintCanvas;
	}
	
}
