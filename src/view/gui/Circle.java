package view.gui;

import controller.IShapeObserver;
import model.ShapeShadingType;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements IShapeObserver
{
    private ShapeConfig dataObject;
    PaintCanvas paintCanvas = PaintCanvas.getInstance();

    public Circle(ShapeConfig dataObject)
    {
        this.dataObject = dataObject;
    }

    public void update()
    {
        Graphics2D g2 = paintCanvas.getGraphics2D();
        g2.setStroke(new BasicStroke(6));

        int x = Math.min(dataObject.getStartX(), dataObject.getEndX());
        int y = Math.min(dataObject.getStartY(), dataObject.getEndY());
        int w = Math.abs(dataObject.getStartX() - dataObject.getEndX());
        int h = Math.abs(dataObject.getStartY() - dataObject.getEndY());

        if (dataObject.getFillType().equals(ShapeShadingType.FILLED_IN))
        {
            g2.setPaint(dataObject.getpColor());
            g2.fill(new Ellipse2D.Double(x, y, w, h));
        }
        else if (dataObject.getFillType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
        {
            g2.setPaint(dataObject.getsColor());
            g2.draw(new Ellipse2D.Double(x, y, w, h));

            g2.setPaint(dataObject.getpColor());
            g2.fill(new Ellipse2D.Double(x, y, w, h));
        }
        else // fillType is OUTLINE
        {
            g2.setPaint(dataObject.getsColor());
            g2.draw(new Ellipse2D.Double(x, y, w, h));
        }
    }

    @Override
    public ShapeConfig getShapeConfig() {
        return dataObject;
    }
}
