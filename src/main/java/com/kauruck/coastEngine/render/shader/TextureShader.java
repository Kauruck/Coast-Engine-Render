package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.io.IOException;

public class TextureShader extends Shader{

    //TODO Add default namespace to core
    public TextureShader() throws NoHandlerException, IOException {
        super(new ResourceLocation("coastEngine", "shader/texture"));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "uv");
    }

    @Override
    protected void getAllUniformLocations() {
        try {
            super.createUniform("texture_sampler");
            super.loadInt("texture_sampler", 0);
            //super.createUniform("color");
            super.createUniform("transformationMatrix");
            super.createUniform("projectionMatrix");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTextureSampler(int sampler){
        try {
            super.loadInt("texture_sampler", sampler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        try {
            super.loadMatrix("transformationMatrix", matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
