package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.LevelsMenuViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.LevelsMenuController;
import project.l02gr06.model.menu.LevelsMenu;

public class LevelsMenuState extends State<LevelsMenu>{
    public LevelsMenuState(LevelsMenu levelsMenu){
        super(levelsMenu);
    }
    @Override
    protected Viewer<LevelsMenu> getViewer() {
        return new LevelsMenuViewer(getModel());
    }

    @Override
    protected Controller<LevelsMenu> getController() {
        return new LevelsMenuController(getModel());
    }
}
