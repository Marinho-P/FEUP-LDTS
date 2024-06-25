package project.l02gr06.state;

import project.l02gr06.viewer.Viewer;
import project.l02gr06.viewer.menu.InstructionsMenuViewer;
import project.l02gr06.controller.Controller;
import project.l02gr06.controller.menu.InstructionsMenuController;
import project.l02gr06.model.menu.InstructionsMenu;

public class InstructionsMenuState extends State<InstructionsMenu>{
    public InstructionsMenuState(InstructionsMenu instructionsMenu){
        super(instructionsMenu);
    }
    @Override
    protected Viewer<InstructionsMenu> getViewer() {
        return new InstructionsMenuViewer(getModel());
    }

    @Override
    protected Controller<InstructionsMenu> getController() {
        return new InstructionsMenuController(getModel());
    }
}
