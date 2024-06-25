package project.l02gr06.model.menu;
import java.util.Arrays;

public class MainMenu extends Menu{
    public MainMenu(){
        super();
        this.menuOptions = Arrays.asList("Start","Instructions","Exit");
    }
    public boolean isStartOption(){
        return isSelected(0);
    }
    public boolean isExitOption(){
        return isSelected(2);
    }
    public boolean isInstructionsOption(){
        return isSelected(1);
    }
}
