package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public class Oval implements IShapeStrategy{
	@Override
	public void fillShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int w = Math.abs(startPoint.x - endPoint.x);
		int h = Math.abs(startPoint.y - endPoint.y);
		graphics2d.setColor(primaryColor);
	    graphics2d.fillOval( x, y, w, h); 
	}

	@Override
	public void outlineShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int w = Math.abs(startPoint.x - endPoint.x);
		int h = Math.abs(startPoint.y - endPoint.y);
		graphics2d.setColor(secondaryColor);
	    graphics2d.drawOval(x, y, w, h);
	}

	@Override
	public void dashShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor,
			Color secondaryColor) {
 		Graphics2D graphics2d = paintCanvas.getGraphics2D(); 
		float dash[] = { 6.0f };
		graphics2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 6.0f, dash, 0.0f));
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int w = Math.abs(startPoint.x - endPoint.x);
		int h = Math.abs(startPoint.y - endPoint.y);
		graphics2d.setColor(secondaryColor);
	    graphics2d.drawOval(x, y, w, h);
		
		
		
	}
}