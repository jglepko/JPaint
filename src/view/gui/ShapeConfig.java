package view.gui;

import controller.IShapeObserver;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;

import java.awt.*;

public class ShapeConfig
{
    private Point startCoords;
    private Point endCoords;
    private Color pColor;
    private Color sColor;
    private ShapeShadingType fillType;
    private ShapeType shapeType;

    public ShapeConfig(Point startCoords, Point endCoords, Color pColor, Color sColor,
                       ShapeShadingType fillType, ShapeType shapeType)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
        this.pColor = pColor;
        this.sColor = sColor;
        this.fillType = fillType;
        this.shapeType = shapeType;
    }

    // Copy ctor

    public ShapeConfig(ShapeConfig original)
    {
        if (original == null)
        {
            System.out.println("Fatal error.");
            System.exit(0);
        }

        startCoords = original.startCoords;
        endCoords = original.endCoords;
        pColor = original.pColor;
        sColor = original.sColor;
        fillType = original.fillType;
        shapeType = original.shapeType;
    }

    public void setStartCoords(Point otherStartCoords)
    {
        if (otherStartCoords != null)
            this.startCoords = otherStartCoords;
    }

    public void setEndCoords(Point otherEndCoords)
    {
        if (otherEndCoords != null)
            this.endCoords = otherEndCoords;
    }

    public int getStartX()
    {
        return (int) startCoords.x;
    }

    public int getStartY()
    {
        return (int) startCoords.y;
    }

    public int getWidth() { return (int) endCoords.x - startCoords.x; }

    public int getHeight() { return (int) endCoords.y - startCoords.y; }

    public int getEndX()
    {
        return (int) endCoords.x;
    }

    public int getEndY()
    {
        return (int) endCoords.y;
    }

    public Color getpColor()
    {
        return this.pColor;
    }

    public Color getsColor() { return this.sColor; }

    public ShapeShadingType getFillType() { return this.fillType; }

    public ShapeType getShapeType() { return this.shapeType; }
}
