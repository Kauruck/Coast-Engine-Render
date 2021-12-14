package com.kauruck.coastEngine.render.rendering;

import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.components.TextureComponent;
import com.kauruck.coastEngine.render.shader.TextureShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.openvr.Texture;

import java.io.IOException;

public class TextureRender extends BaseRender{

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

    private TextureShader shader;

    public static TextureRender INSTANCE;

    @Override
    public void create() {
        INSTANCE = new TextureRender();
        try {
            INSTANCE.shader = new TextureShader();
        } catch (NoHandlerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(RenderComponent pComponent, Entity entity) {
        if(!(pComponent instanceof TextureComponent))
            return;

        TextureComponent component = (TextureComponent) pComponent;

        shader.start();
        GL30.glBindVertexArray(component.getMesh().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, component.getMesh().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
