package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.NextLevelMenuViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.NextLevelMenuController;
import project.l02gr06.model.menu.NextLevelMenu;

public class NextLevelMenuState extends State<NextLevelMenu>{
    public NextLevelMenuState(NextLevelMenu nextLevelMenu){
        super(nextLevelMenu);
    }
    @Override
    protected Viewer<NextLevelMenu> getViewer() {
        return new NextLevelMenuViewer(getModel());
    }

    @Override
    protected Controller<NextLevelMenu> getController() {
        return new NextLevelMenuController(getModel());
    }
}
