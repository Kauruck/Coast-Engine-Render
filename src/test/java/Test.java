import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.centum.world.World;
import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.rendering.SquareRender;
import com.kauruck.coastEngine.render.window.Window;

public class Test {

    public static void main(String[] args) throws NoSuchProcessException {
        Render.init();
        Window.createWindow("Test");
        Render.showWindow(() -> {
            World world = new World();
            Centum.registerWorld(world);
            Centum.startSystems();
            world.setActive(true);
            Entity testEntity = new Entity();
            testEntity.addComponent(new RenderComponent(new SquareRender()));
            world.addEntity(testEntity);
        });

    }
}
