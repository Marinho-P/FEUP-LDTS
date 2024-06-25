package project.l02gr06.model.menu;

import java.util.Arrays;

public class InstructionsMenu extends Menu{
    public InstructionsMenu(){
        super();
        this.menuOptions = Arrays.asList("Back to Main Menu","Play");
    }
    public boolean isBackToMainMenu(){return isSelected(0);}
    public boolean isPlayOption(){return isSelected(1);}
}
