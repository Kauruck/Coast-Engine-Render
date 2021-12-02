package com.kauruck.coastEngine.render.rendering;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHelper {

    public static Mesh createMesh(float[] positions, int[] indices) {
        int vao = BufferHelper.genVAO();
        BufferHelper.storeData(0,3,positions);
        BufferHelper.bindIndices(indices);
        GL30.glBindVertexArray(0);
        return new Mesh(vao,indices.length);
    }
}
