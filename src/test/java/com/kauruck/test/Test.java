package com.kauruck.test;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.centum.componetns.Transform;
import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.centum.world.World;
import com.kauruck.coastEngine.core.Core;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.input.InputAction;
import com.kauruck.coastEngine.core.input.KeyCode;
import com.kauruck.coastEngine.core.math.Vector3;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.color.Color;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.components.SquareComponent;
import com.kauruck.coastEngine.render.components.TextureComponent;
import com.kauruck.coastEngine.render.rendering.SquareRender;
import com.kauruck.coastEngine.render.textures.Texture;
import com.kauruck.coastEngine.render.window.Window;

import java.io.IOException;

public class Test {

    private static Texture testTexture;

    public static void main(String[] args) {
        Core.init();
        Render.init();
        Window.createWindow("Test");
        Render.showWindow(() -> {
            World world = new World();
            Centum.registerWorld(world);
            Centum.startSystems();
            try {
                testTexture = ResourceLoader.loadResources(new ResourceLocation("test", "assets/test"), Texture.class);
            } catch (IOException | NoHandlerException e) {
                e.printStackTrace();
            }
            world.setActive(true);
            Entity testEntity = new Entity();
            Entity testEntity2 = new Entity();
            SquareComponent square = new SquareComponent(new Color(0.0f, 1.0f, 0.0f, 1.0f));
            testEntity2.addComponent(square);
            TextureComponent component = new TextureComponent(testTexture);
            testEntity.addComponent(component);
            world.addEntity(testEntity);
            world.addEntity(testEntity2);
            Input.registerKeyListener(KeyCode.B, InputAction.Down, () -> {
                square.setColor(new Color(0.0f, 0.0f, 1.0f, 1.0f));
            });
            Input.registerKeyListener(KeyCode.G, InputAction.Down, () -> {
                square.setColor(new Color(0.0f, 1.0f, 0.0f, 1.0f));
            });

            Transform transform = new Transform();
            transform.setPosition(new Vector3(0,0,-1 ));
            transform.setRotation(new Vector3(0, 0, 0));
            transform.setScale(1);

            testEntity.addComponent(transform);

            Transform transform2 = new Transform();
            transform2.setPosition(new Vector3(1,0,-1 ));
            transform2.setRotation(new Vector3(0, 0, 0));
            transform2.setScale(1);

            testEntity2.addComponent(transform2);

        });

    }
}
