package com.kauruck.coastengine.render;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastengine.render.components.RenderComponent;
import com.kauruck.coastengine.render.systems.RenderSystem;

public class Render {

    public static void init(){
        Centum.registerSystem(new RenderSystem(30, RenderComponent.class));
    }


}
