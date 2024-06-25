package project.l02gr06.viewer.menu;

import project.l02gr06.gui.GUI;
import project.l02gr06.viewer.Viewer;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.LevelsMenu;

public class LevelsMenuViewer extends Viewer<LevelsMenu> {
    public LevelsMenuViewer(LevelsMenu levelsMenu){
        super(levelsMenu);
    }
    @Override
    public void drawElements(GUI gui){
        gui.drawText(new Position(2, 1), "Levels Menu", "#FFFFFF");
        gui.drawText(new Position(2,3), "Gems collected:" + getModel().getNumberOfGems(),"#FFFFFF");
        for (int i = 0; i < getModel().getNumberOfOptions(); i++) {
            String color = getColor(i);

                gui.drawText(
                        new Position(2, 5 + i),
                        getModel().getOption(i),
                        color);

        }
    }
    public String getColor(int currentOption){
        if(getModel().isSelected(currentOption)){
            if(getModel().isSelectable(currentOption)){
                return "#FFD700";
            }
            else{
                return "#D10000";
            }
        }
        else{
            return "#FFFFFF";
        }
    }
}

