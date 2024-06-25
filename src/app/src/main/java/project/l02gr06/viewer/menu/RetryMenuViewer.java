package project.l02gr06.viewer.menu;

import project.l02gr06.gui.GUI;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.RetryMenu;

public class RetryMenuViewer extends Viewer<RetryMenu> {

    public RetryMenuViewer(RetryMenu retryMenu){
        super(retryMenu);
    }
    @Override
    public void drawElements(GUI gui) {
        String deathMessage = getModel().getDeathMessage();
        gui.drawText(new Position(5, 5), deathMessage, "#FFFFFF");

        for (int i = 0; i < getModel().getNumberOfOptions(); i++)
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }

}
