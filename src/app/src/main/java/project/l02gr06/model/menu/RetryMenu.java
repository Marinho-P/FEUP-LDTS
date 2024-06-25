package project.l02gr06.model.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RetryMenu extends Menu{
    private String deathMessage;
    private int currentLevel;
    public RetryMenu(int currentLevel){
        super();
        this.menuOptions = Arrays.asList("Try again","Exit");
        this.currentLevel = currentLevel;
        this.deathMessage = generateRandomDeathMessage();
    }
    public boolean isTryAgainOption(){
        return isSelected(0);
    }
    public int getCurrentLevel(){return currentLevel;}
    public boolean isExit(){
        return isSelected(1);
    }
    private String generateRandomDeathMessage() {
        Random random = new Random();
        int index = random.nextInt(deathMessages.size());
        return deathMessages.get(index);
    }
    private static final List<String> deathMessages = List.of(
            "You Died",
            "Game Over",
            "Skill issue",
            "Better luck next time"
    );

    public String getDeathMessage() {
        return deathMessage;
    }
}
