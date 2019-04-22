package view.gui;

import controller.IShapeObserver;
import controller.ObserverHandler;
import model.ShapeShadingType;

import java.awt.*;

public class Triangle implements IShapeObserver
{
    private ShapeConfig dataObject;
    PaintCanvas paintCanvas = PaintCanvas.getInstance();

    public Triangle(ShapeConfig dataObject)
    {
        this.dataObject = dataObject;
    }

    public void update()
    {
        Graphics2D g2 = paintCanvas.getGraphics2D();
        g2.setStroke(new BasicStroke(4));

        int[] xCoords = {dataObject.getStartX() + ((dataObject.getEndX() - dataObject.getStartX()) / 2),
                dataObject.getStartX(), dataObject.getEndX()};
        int[] yCoords = {dataObject.getStartY(), dataObject.getEndY(), dataObject.getEndY()};

        if (dataObject.getFillType().equals(ShapeShadingType.FILLED_IN))
        {
            g2.setPaint(dataObject.getpColor());
            g2.fillPolygon(xCoords, yCoords, 3);
        }
        else if (dataObject.getFillType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
        {
            g2.setPaint(dataObject.getsColor());
            g2.drawPolygon(xCoords, yCoords, 3);

            g2.setPaint(dataObject.getpColor());
            g2.fillPolygon(xCoords, yCoords, 3);
        }
        else // fillType is OUTLINE
        {
            g2.setPaint(dataObject.getsColor());
            g2.drawPolygon(xCoords, yCoords, 3);
        }
    }

    @Override
    public ShapeConfig getShapeConfig() {
        return dataObject;
    }
}
