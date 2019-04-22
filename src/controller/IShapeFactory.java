package controller;

import view.gui.Circle;
import view.gui.Rectangle;
import view.gui.ShapeConfig;
import view.gui.Triangle;

public class IShapeFactory
{
    public IShapeFactory() { }

    public static IShapeObserver createCircle(ShapeConfig dataObject)
    {
        return new Circle(dataObject);
    }

    public static IShapeObserver createRectangle(ShapeConfig dataObject)
    {
        return new Rectangle(dataObject);
    }

    public static IShapeObserver createTriangle(ShapeConfig dataObject)
    {
        return new Triangle(dataObject);
    }
}
