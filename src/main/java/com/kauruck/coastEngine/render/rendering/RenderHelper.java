package com.kauruck.coastEngine.render.rendering;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHelper {

    public static Mesh createMesh(float[] positions, float[] UVs, int[] indices ) {
        int vao = BufferHelper.genVAO();
        BufferHelper.storeData(0,3,positions);
        BufferHelper.storeData(1, 2,UVs);
        BufferHelper.bindIndices(indices);
        GL30.glBindVertexArray(0);
        return new Mesh(vao,indices.length);
    }

    public static Mesh createMesh(float[] positions, float[] UVs, int[] indices, float[] color) {
        int vao = BufferHelper.genVAO();
        BufferHelper.storeData(0,3,positions);
        BufferHelper.storeData(1, 2,UVs);
        BufferHelper.storeData(2, 3,color);
        BufferHelper.bindIndices(indices);
        GL30.glBindVertexArray(0);
        return new Mesh(vao,indices.length);
    }
}
