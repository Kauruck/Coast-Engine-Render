package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.resources.ResourceLocation;

import java.io.IOException;

public class BaseShader extends Shader{
    public BaseShader() throws NoHandlerException, IOException {
        super(new ResourceLocation("coastEngine", "shader/base"));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(0, "color");
    }

    @Override
    protected void getAllUniformLocations() {

    }
}
