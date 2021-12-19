package com.kauruck.coastEngine.render.rendering;

import com.kauruck.coastEngine.centum.componetns.Transform;
import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.components.SquareComponent;
import com.kauruck.coastEngine.render.math.MathUtils;
import com.kauruck.coastEngine.render.shader.BaseShader;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.IOException;

public class SquareRender extends BaseRender {

    public static float[] VERTICES = {
            -0.5f, 0.5f, 0, //V0
            -0.5f, -0.5f, 0, //V1
            0.5f, -0.5f, 0, //V2
            0.5f, 0.5f, 0 // V3
    };
    public static final int[] INDICES = new int[]{
            0, 1, 3, //TOP LEFT V0 V1 V3
            3, 1, 2 // BOTTOM RIGHT V3 V1 V2
    };

    private BaseShader shader;

    public static SquareRender INSTANCE;


    public SquareRender(){

    }

    @Override
    public void create() {
        INSTANCE = new SquareRender();
        try {
            INSTANCE.shader = new BaseShader();
        } catch (NoHandlerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(RenderComponent pComponent, Entity entity) {
        if(! (pComponent instanceof SquareComponent))
            return;
        SquareComponent component = (SquareComponent) pComponent;


        shader.start();
        GL30.glBindVertexArray(component.getMesh().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        Transform transform = (Transform) component.getEntity().getComponent(Transform.class);
        Matrix4f transformationMatrix = MathUtils.ZERO_TRANSFORM;
        if(transform != null)
        transformationMatrix = MathUtils.createTransformationMatrix(transform);
        shader.loadTransformationMatrix(transformationMatrix);
        shader.loadColor(component.getColor());

        GL11.glDrawElements(GL11.GL_TRIANGLES, component.getMesh().getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
