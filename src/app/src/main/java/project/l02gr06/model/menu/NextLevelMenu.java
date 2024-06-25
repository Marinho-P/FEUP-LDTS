package project.l02gr06.model.menu;

import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.saveManager.SaveManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NextLevelMenu extends Menu{
    private int currentLevel;
    private String congratsMessage;
    public NextLevelMenu(int currentLevel){
        super();
        this.menuOptions = Arrays.asList("Go to next level","Back to Level Menu");
        this.currentLevel = currentLevel;
        this.congratsMessage = generateCongratsMessage();
    }
    public boolean isNextLevel(){
        return isSelected(0);
    }
    public boolean isExit(){
        return isSelected(1);
    }
    public int getCurrentLevel(){return currentLevel;}

    private String generateCongratsMessage() {
        SaveManager saveManager = new SaveFileManager();
        if(getCurrentLevel() == saveManager.getNumberOfLevels()){
            return "Congratulations, you have beaten the game!";
        }
        else {
            Random random = new Random();
            int index = random.nextInt(congratsMessages.size());
            return congratsMessages.get(index);
        }
    }
    private static final List<String> congratsMessages = List.of(
            "Hurray you did it!",
            "Congratulations!",
            "That was easy right?",
            "Bravo! Victory achieved!"
    );
    public String getCongratsMessage(){
        return congratsMessage;
    }
}
