package com.kauruck.test;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.centum.world.World;
import com.kauruck.coastEngine.core.Core;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.resources.ResourceHandler;
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
        Window.createWindow("com.kauruck.test.Test");
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
            SquareComponent square = new SquareComponent(new Color(0.0f, 1.0f, 0.0f, 1.0f));
            testEntity.addComponent(square);
            world.addEntity(testEntity);
        });

    }
}
