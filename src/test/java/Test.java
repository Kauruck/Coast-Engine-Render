import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.centum.world.World;
import com.kauruck.coastengine.render.Render;
import com.kauruck.coastengine.render.components.RenderComponent;
import com.kauruck.coastengine.render.rendering.SquareRender;
import com.kauruck.coastengine.render.window.Window;

public class Test {

    public static void main(String[] args){
        Render.init();
        Window.createWindow("Test");
        World world = new World();
        Centum.registerWorld(world);
        Centum.startSystems();
        world.setActive(true);
        Entity testEntity = new Entity();
        testEntity.addComponent(new RenderComponent(new SquareRender()));
        world.addEntity(testEntity);
    }
}
