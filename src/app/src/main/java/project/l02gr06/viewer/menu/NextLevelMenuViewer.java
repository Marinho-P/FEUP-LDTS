package project.l02gr06.viewer.menu;

import project.l02gr06.gui.GUI;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.NextLevelMenu;

public class NextLevelMenuViewer extends Viewer<NextLevelMenu> {

    public NextLevelMenuViewer(NextLevelMenu nextLevelMenu){
        super(nextLevelMenu);
    }
    @Override
    public void drawElements(GUI gui){
        String congratsMessage = getModel().getCongratsMessage();
        gui.drawText(new Position(5, 5),congratsMessage, "#FFFFFF");

        for (int i = 0; i < getModel().getNumberOfOptions(); i++) {
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
        }
    }


}
