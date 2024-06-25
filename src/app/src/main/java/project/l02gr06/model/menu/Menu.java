package project.l02gr06.model.menu;

import java.util.List;

public abstract class Menu {
    protected List<String> menuOptions;
    protected int currentOption;

    public Menu(){
        currentOption=0;
    }

    public void nextOption(){
        currentOption++;
        if(currentOption > this.menuOptions.size()-1) currentOption=0;
    }

    public List<String> getMenuOptions() {
        return menuOptions;
    }


    public void previousOption(){
        currentOption--;
        if(currentOption < 0) currentOption = this.menuOptions.size()-1;
    }
    public String getOption(int currentOption) {
        return menuOptions.get(currentOption);
    }
    public boolean isSelected(int option) {
        return currentOption == option;
    }
    public int getNumberOfOptions() {
        return this.menuOptions.size();
    }
    public int getCurrentOption(){
        return currentOption;
    }

}
