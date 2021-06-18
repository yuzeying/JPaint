package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public class Triangle implements IShapeStrategy{
	@Override
	public void fillShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		int xPoints[] = {startPoint.x, startPoint.x, endPoint.x};
	    int yPoints[] = {startPoint.y, endPoint.y, endPoint.y};
	    int nPoints = 3;
		graphics2d.setColor(primaryColor);
		graphics2d.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void outlineShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		int xPoints[] = {startPoint.x, startPoint.x, endPoint.x};
	    int yPoints[] = {startPoint.y, endPoint.y, endPoint.y};
	    int nPoints = 3;
		graphics2d.setColor(secondaryColor);
		graphics2d.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void dashShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor,
			Color secondaryColor) {
		Graphics2D graphics2d = paintCanvas.getGraphics2D();
		float dash[] = { 6.0f };
		graphics2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 6.0f, dash, 0.0f));
		int xPoints[] = {startPoint.x, startPoint.x, endPoint.x};
	    int yPoints[] = {startPoint.y, endPoint.y, endPoint.y};
	    int nPoints = 3;
		graphics2d.setColor(secondaryColor);
		graphics2d.drawPolygon(xPoints, yPoints, nPoints);
	}
}
