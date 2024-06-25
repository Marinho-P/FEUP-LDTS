package project.l02gr06.saveManager;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SaveFileManager implements SaveManager{


    private int numberOfLevels;
    private File saveFile;
    public SaveFileManager() {
        try {
            this.saveFile = new File("save.txt");
            saveFile.createNewFile();
            this.numberOfLevels = initializeSave();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    private int initializeSave() throws IOException {
        int counter = 1;
        URL levelFile = SaveFileManager.class.getResource("/levels/level"+ counter + ".lvl");
        BufferedReader reader = new BufferedReader(new FileReader(saveFile));
        String line = reader.readLine();
        String subIn = "";
        while(levelFile != null){
            if(line == null ){
                subIn = subIn + "0,0\n";
            }
            else{
                subIn = subIn + line + "\n";
            }
            counter++;
            levelFile = SaveFileManager.class.getResource("/levels/level"+ counter + ".lvl");
            line = reader.readLine();

        }
        FileWriter writer = new FileWriter(saveFile);
        writer.write(subIn);
        writer.close();
        reader.close();
        return counter - 1;
    }

    @Override
    public void saveLevelCompletion(int levelNumber,boolean gotGem) throws IOException {
        String subIn = "";
        BufferedReader reader = new BufferedReader(new FileReader(saveFile));
        String line = reader.readLine();
        int counter  = 1;
        while ( line != null){
            if(counter == levelNumber ){
                String toSave = "1";
                if(gotGem){
                    toSave = toSave + ",1\n";
                }
                else{
                    toSave = toSave + ",0\n";
                }
                subIn = subIn + toSave;
            }
            else{
                subIn = subIn + line + "\n";
            }
            counter++;
            line = reader.readLine();
        }
        FileWriter writer = new FileWriter(saveFile);
        writer.write(subIn);
        reader.close();
        writer.close();
    }

    @Override
    public List<Boolean> getLevelsUnlocked() {
        try {
            int counter = 0;
            List<Boolean> levelsUnlocked = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            String line = reader.readLine();
            levelsUnlocked.add(true);
            while (line != null) {
                levelsUnlocked.add(line.charAt(0) == '1');
                counter++;
                line = reader.readLine();
            }
            levelsUnlocked.set(counter,true);
            reader.close();
            return levelsUnlocked;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getNumberOfGems()  {
        try {
            int gemCounter = 0;
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));
            String line = reader.readLine();
            while (line != null) {
                if (line.charAt(2) == '1') {
                    gemCounter++;
                }
                line = reader.readLine();
            }
            reader.close();
            return gemCounter;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int getNumberOfLevels() {
        return numberOfLevels;
    }
}
