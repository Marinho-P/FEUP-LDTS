package project.l02gr06.viewer.game.Space;

import project.l02gr06.model.game.objects.*;
import project.l02gr06.model.game.objects.Object;
import project.l02gr06.viewer.game.Space.object.*;

public class ObjectViewerFactory {

    public static ObjectViewer getObjectViewer(Object object) { // sub in for generics if possible
        if (object == null) {
            return new EmptyViewer();
        }
        if (object instanceof EndPortal) {
            return new EndPortalViewer();
        }
        if (object instanceof Gem) {
            return new GemViewer();
        }
        if (object instanceof Inverter) {
            return new InverterViewer();
        }
        if (object instanceof Key) {
            return new KeyViewer();
        }
        if (object instanceof Lock) {
            return new LockViewer();
        }
        if (object instanceof Player) {
            return new PlayerViewer();
        }
        if (object instanceof PushableBlock) {
            return new PushableBlockViewer();
        }
        if (object instanceof Wall) {
            return new WallViewer();
        }
        return null;
    }
}