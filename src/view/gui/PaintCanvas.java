package view.gui;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends JComponent
{
    private static volatile PaintCanvas instance = null;

    private PaintCanvas() { }

    public static PaintCanvas getInstance() {
        if (instance == null) {
            synchronized(PaintCanvas.class) {
                if (instance == null) {
                    instance = new PaintCanvas();
                }
            }
        }
        return instance;
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
}
