package controller;

import java.awt.Color;
import java.awt.Point;

import model.IShapeStrategy;
import view.interfaces.PaintCanvasBase;

public class Decorator {
	private IShapeStrategy strategy;
	
	public Decorator(IShapeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void outline(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint) {
		int minX = Math.min(startPoint.x, endPoint.x);
		int minY = Math.min(startPoint.y, endPoint.y);
		int maxX = Math.max(startPoint.x, endPoint.x);
		int maxY = Math.max(startPoint.y, endPoint.y);
		
		minX -= 10; maxX += 10;
		minY -= 10; maxY += 10;
		
		Point p1 = new Point(minX, minY);
		Point p2 = new Point(maxX, maxY);
		
		strategy.dashShape(paintCanvas, p1, p2, Color.black, Color.black);
	}
}
