package project.l02gr06.model.menu;

import project.l02gr06.saveManager.SaveFileManager;
import project.l02gr06.saveManager.SaveManager;

import java.util.ArrayList;
import java.util.List;


public class LevelsMenu extends Menu{
    private List<Boolean> selectableOptions;
    private int numberOfGems;
    public LevelsMenu() {
        super();
        SaveManager saveManager = new SaveFileManager();
        this.menuOptions = generateLevelOptions(saveManager.getNumberOfLevels());
        this.selectableOptions = saveManager.getLevelsUnlocked();
        this.numberOfGems = saveManager.getNumberOfGems();
    }


    private List<String> generateLevelOptions(int numberOfLevels) {

        List<String> levelOptions = new ArrayList<>();
        for (int i = 1; i <= numberOfLevels - 1; i++) {
            levelOptions.add("Level " + i);
        }
        levelOptions.add("Bonus level - 7 gems needed" );
        levelOptions.add("Back to Main Menu");
        return levelOptions;
    }

    public boolean isSelectable(int currentOption){
        if(currentOption == getNumberOfOptions() - 2) return numberOfGems == 7;
        return selectableOptions.get(currentOption);
    }
    public int getNumberOfGems(){
        return numberOfGems;
    }

}
