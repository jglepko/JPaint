package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.*;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import javax.swing.event.MouseInputAdapter;

public class Main extends MouseInputAdapter
{
    public static void main(String[] args)
    {
        PaintCanvas paintCanvas = PaintCanvas.getInstance();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        ClickHandler clickHandler = new ClickHandler(paintCanvas, appState);
        paintCanvas.addMouseListener(clickHandler);
        paintCanvas.addMouseMotionListener(clickHandler);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
    }
}
