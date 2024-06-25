package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.MainMenuViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.model.menu.MainMenu;
import project.l02gr06.controller.menu.MainMenuController;

public class MainMenuState extends State<MainMenu>{
    public MainMenuState(MainMenu model){
        super(model);
    }
    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
