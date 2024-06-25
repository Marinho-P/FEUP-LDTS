package project.l02gr06.controller;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import project.l02gr06.controller.game.PlayerController;
import project.l02gr06.gui.GUI;
import project.l02gr06.model.game.level.Level;
import project.l02gr06.model.game.level.LevelBuilder;

import java.io.IOException;
import java.util.List;

public class LevelIsInescapableTest {

    private int width = 30;
    private int height = 20;

    @Provide
    Arbitrary<GUI.ACTION> moveActions() {
        return Arbitraries.of(GUI.ACTION.UP, GUI.ACTION.RIGHT, GUI.ACTION.DOWN, GUI.ACTION.LEFT);
    }

    @Property
    void allArenasAreClosed(@ForAll @IntRange(min = 1, max = 12) int level, @ForAll List<GUI.@From("moveActions") ACTION> actions) {
        try {
            LevelBuilder levelBuilder = new LevelBuilder(level);
            Level nivel = levelBuilder.createLevel();
            PlayerController controller = new PlayerController(nivel);
            for (GUI.ACTION action : actions) {
                controller.step(null, action, 10000);
                assert (controller.getModel().getPlayer().getPosition().getX() > 0);
                assert (controller.getModel().getPlayer().getPosition().getY() > 0);
                assert (controller.getModel().getPlayer().getPosition().getX() < width - 1);
                assert (controller.getModel().getPlayer().getPosition().getY() < height - 1);
                assert (!controller.getModel().isWall(controller.getModel().getPlayer().getPosition()));
                if(controller.getModel().findLava(controller.getModel().getPlayer().getPosition()) != null){
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
