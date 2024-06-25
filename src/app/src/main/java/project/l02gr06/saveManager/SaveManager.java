package project.l02gr06.saveManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SaveManager {
    public void saveLevelCompletion(int levelNumber, boolean gotGem) throws IOException;

    List<Boolean> getLevelsUnlocked();

    public int getNumberOfGems();
    public int getNumberOfLevels();
}
