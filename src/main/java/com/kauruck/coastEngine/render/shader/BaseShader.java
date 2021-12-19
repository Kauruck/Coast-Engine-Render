package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.color.Color;
import org.joml.Matrix4f;

import java.io.IOException;

public class BaseShader extends Shader{
    public BaseShader() throws NoHandlerException, IOException {
        super(new ResourceLocation("coastEngine", "shader/base"));
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    @Override
    protected void getAllUniformLocations() {
        try {
            super.createUniform("transformationMatrix");
            super.createUniform("color");
            super.createUniform("projectionMatrix");
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

    public void loadProjectionMatrix(Matrix4f matrix){
        try {
            super.loadMatrix("projectionMatrix", matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadColor(Color color){
        try {
            super.loadVector("color", color.toVector());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
