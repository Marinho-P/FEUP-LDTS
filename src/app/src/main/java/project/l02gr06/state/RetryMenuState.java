package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.RetryMenuViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.RetryMenuController;
import project.l02gr06.model.menu.RetryMenu;

public class RetryMenuState extends State<RetryMenu>{
    public RetryMenuState(RetryMenu retryMenu){
        super(retryMenu);
    }
    @Override
    public Viewer<RetryMenu> getViewer() {
        return new RetryMenuViewer(getModel());
    }

    @Override
    public Controller<RetryMenu> getController() {
        return new RetryMenuController(getModel());
    }
}
