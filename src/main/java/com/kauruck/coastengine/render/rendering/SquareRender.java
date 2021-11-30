package com.kauruck.coastengine.render.rendering;

import com.kauruck.coastEngine.centum.entity.Entity;
import org.lwjgl.opengl.GL11;

public class SquareRender extends BaseRender {

    @Override
    public Renderable render(Entity entity) {
        return () -> {
            System.out.println("Square");
        };
    }
}
