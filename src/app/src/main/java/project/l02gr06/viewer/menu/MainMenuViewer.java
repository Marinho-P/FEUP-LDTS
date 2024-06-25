package project.l02gr06.viewer.menu;

import project.l02gr06.gui.GUI;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.MainMenu;



public class MainMenuViewer extends Viewer<MainMenu> {
    public MainMenuViewer(MainMenu mainMenu){
        super(mainMenu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(5, 5), "Welcome to", "#FFFFFF");
        gui.drawText(new Position(6, 6), "Splunk Adventure", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberOfOptions(); i++)
            gui.drawText(
                    new Position(5, 8 + i),
                    getModel().getOption(i),
                   getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}

