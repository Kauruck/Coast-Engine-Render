package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.resources.ResourceLocation;

import java.io.IOException;

public class TextureShader extends Shader{

    //TODO Add default namespace to core
    public TextureShader() throws NoHandlerException, IOException {
        super(new ResourceLocation("coastEngine", "shader/texture"));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    @Override
    protected void getAllUniformLocations() {

    }

}
