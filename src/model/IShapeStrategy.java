package model;

import java.awt.Color;
import java.awt.Point;
import view.interfaces.PaintCanvasBase;

public interface IShapeStrategy {
	void fillShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor);
	void outlineShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor);
	void dashShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor);
	
	default void fillAndOutlineShape(PaintCanvasBase paintCanvas, Point startPoint, Point endPoint, Color primaryColor, Color secondaryColor) {
		fillShape(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor);
		outlineShape(paintCanvas, startPoint, endPoint, primaryColor, secondaryColor); 
	} 
 
} 