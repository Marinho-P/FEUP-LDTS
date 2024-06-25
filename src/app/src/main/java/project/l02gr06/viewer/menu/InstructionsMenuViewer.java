package project.l02gr06.viewer.menu;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.Position;
import project.l02gr06.model.menu.InstructionsMenu;

import java.util.Arrays;
import java.util.List;

public class InstructionsMenuViewer extends Viewer<InstructionsMenu> {
    public InstructionsMenuViewer(InstructionsMenu mainMenu){
        super(mainMenu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(8, 1), "INSTRUCTIONS", "#FFFFFF");
        gui.drawText(new Position(0, 4), ">Hello PathFinder", "#FFFFFF");
        List<String> arrows = Arrays.asList("\u2191", "\u2193", "\u2190", "\u2192");
        gui.drawText(new Position(1,6),"1.Move with ","#FFFFFF");
        gui.drawText(new Position(13,6),"\u2191 \u2193 \u2190 \u2192","#32CD32");
        gui.drawText(new Position(1,8),"2.","#FFFFFF");
        gui.drawText(new Position(3,8),"\u00C3 ","#FF0000");
        gui.drawText(new Position(5,8),"Lava becomes UnstableTile","#FFFFFF");
        gui.drawText(new Position(0,9),"and vice-versa","#FFFFFF");
        gui.drawText(new Position(1,11),"3.","#FFFFFF");
        gui.drawText(new Position(3,11),"\u00C4","#FFAC1C");
        gui.drawText(new Position(5,11),"Can be pushed by a player","#FFFFFF");
        gui.drawText(new Position(1,13),"4.","#FFFFFF");
        gui.drawText(new Position(3,13),"\u00C7","#FFAC1C");
        gui.drawText(new Position(5,13),"Key required to unlock","#FFFFFF");
        gui.drawText(new Position(1,15),"5.","#FFFFFF");
        gui.drawSpace(new Position(3,15),"#2a2a2a","#FFFFFF",'\u00A5');
        gui.drawText(new Position(5,15),"Unstable tile changes to","#FFFFFF");
        gui.drawText(new Position(1,16),"lava after player leaves","#FFFFFF");
        gui.drawText(new Position(1,18),"Lastly go and have some fun!","#FFFFFF");
        for (int i = 0; i < getModel().getNumberOfOptions(); i++)
            gui.drawText(
                    new Position(i*25, 20 ),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
