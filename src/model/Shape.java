package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.UndoRedoStack;
import view.interfaces.PaintCanvasBase;

public class Shape {
	public static List<Shape> shapes;
	public static List<Shape> selectedShapes;
	public static List<Shape> copiedShapes;
	public static UndoRedoStack undoRedoStack;
	
	private final static Map<ShapeColor, Color> colorMap;
	static {
		shapes = new ArrayList<>();
		selectedShapes = new ArrayList<>();
		copiedShapes = new ArrayList<>();
		undoRedoStack = new UndoRedoStack();
		colorMap = new HashMap<>();
		colorMap.put(ShapeColor.BLACK, Color.black);
		colorMap.put(ShapeColor.BLUE, Color.blue);
		colorMap.put(ShapeColor.CYAN, Color.cyan);
		colorMap.put(ShapeColor.DARK_GRAY, Color.darkGray);
		colorMap.put(ShapeColor.GRAY, Color.gray);
		colorMap.put(ShapeColor.GREEN, Color.green);
		colorMap.put(ShapeColor.LIGHT_GRAY, Color.lightGray);
		colorMap.put(ShapeColor.MAGENTA, Color.magenta);
		colorMap.put(ShapeColor.ORANGE, Color.orange);
		colorMap.put(ShapeColor.PINK, Color.pink);
		colorMap.put(ShapeColor.RED, Color.red);
		colorMap.put(ShapeColor.WHITE, Color.white);
		colorMap.put(ShapeColor.YELLOW, Color.yellow);
	}
 
	private Point startPoint;
	private Point endPoint;
	private ShapeColor primaryColor;
	private ShapeColor secondaryColor;
	private ShapeShadingType shadingType;
	private IShapeStrategy shapeStrategy; 
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public ShapeColor getPrimaryColor() {
		return primaryColor;
	}
	
	public IShapeStrategy getShapeStrategy() {
		return this.shapeStrategy;
	}

	public void setPrimaryColor(ShapeColor primaryColor) {
		this.primaryColor = primaryColor;
	}

	public ShapeColor getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(ShapeColor secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public ShapeShadingType getShadingType() {
		return shadingType;
	}

	public void setShadingType(ShapeShadingType shadingType) {
		this.shadingType = shadingType;
	}

	public static Map<ShapeColor, Color> getColormap() {
		return colorMap;
	}


	public Shape(Point startPoint, Point endPoint, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType, IShapeStrategy shapeStrategy) {
		 this.startPoint = startPoint;
		 this.endPoint = endPoint;
		 this.primaryColor = primaryColor;
		 this.secondaryColor = secondaryColor;
		 this.shadingType = shadingType;
		 this.shapeStrategy = shapeStrategy;
	}
	
	public boolean collides(Point p1, Point p2) {
		int width1 = Math.abs(startPoint.x - endPoint.x);
		int width2 = Math.abs(p1.x - p2.x);
		int height1 = Math.abs(startPoint.y - endPoint.y);
		int height2 = Math.abs(p1.y - p2.y);
		int rect1x = Math.min(startPoint.x, endPoint.x);
		int rect2x = Math.min(p1.x, p2.x);	
		int rect1y = Math.min(startPoint.y, endPoint.y);
		int rect2y = Math.min(p1.y, p2.y);
		return rect1x < rect2x + width2 &&
				rect1x + width1 > rect2x &&
				rect1y < rect2y + height2 &&
				rect1y + height1 > rect2y;
	}
	
	public void draw(PaintCanvasBase canvas) {  
	    if (this.shadingType == ShapeShadingType.OUTLINE) { 
	    	this.shapeStrategy.outlineShape(canvas, startPoint, endPoint, colorMap.get(primaryColor), colorMap.get(secondaryColor));
	    } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
	    	this.shapeStrategy.fillShape(canvas, startPoint, endPoint, colorMap.get(primaryColor), colorMap.get(secondaryColor)); 
		} else { 
	    	this.shapeStrategy.fillAndOutlineShape(canvas, startPoint, endPoint, colorMap.get(primaryColor), colorMap.get(secondaryColor)); 
	    }
	}

	public void move(int deltaX, int deltaY) {
		this.startPoint.x += deltaX;
		this.startPoint.y += deltaY;
		this.endPoint.x += deltaX;
		this.endPoint.y += deltaY;
	}
}



