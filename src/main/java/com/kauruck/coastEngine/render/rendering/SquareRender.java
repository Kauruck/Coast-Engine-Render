package com.kauruck.coastEngine.render.rendering;

import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.render.components.RenderComponent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class SquareRender extends BaseRender {

    private final float[] vertices = {-0.5f,-0.5f,0f,
            0.5f, -0.5f, 0f,
            0f,0.5f,0f};
    private final int[] indices = {0,1,2};
    private final float[] uvs = {0,0,0};

    private Mesh mesh;

    public static SquareRender INSTANCE;


    public SquareRender(){

    }

    @Override
    public void create() {
        INSTANCE = new SquareRender();
        INSTANCE.mesh = RenderHelper.createMesh(vertices, uvs, indices);
    }

    @Override
    public void render(RenderComponent component, Entity entity) {
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
