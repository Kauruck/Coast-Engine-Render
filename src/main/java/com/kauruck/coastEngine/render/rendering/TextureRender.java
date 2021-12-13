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

    float[] vertices = new float[]{
            -0.5f,  0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f,  0.5f, 0.0f,
    };
    int[] indices = new int[]{
            0, 1, 3, 3, 1, 2,
    };
    private final float[] uvs = {0,1};
    float[] colours = new float[]{
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
    };

    private TextureShader shader;
    private Mesh mesh;

    public static TextureRender INSTANCE;

    @Override
    public void create() {
        INSTANCE = new TextureRender();
        INSTANCE.mesh = RenderHelper.createMesh(vertices, uvs, indices, colours);
        try {
            INSTANCE.shader = new TextureShader();
        } catch (NoHandlerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(RenderComponent component, Entity entity) {
        if(!(component instanceof TextureComponent))
            return;

        shader.start();
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((TextureComponent)component).getTexture().getId());
        //GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
