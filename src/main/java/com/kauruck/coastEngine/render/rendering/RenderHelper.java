package com.kauruck.coastEngine.render.rendering;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHelper {

    public static Mesh createMesh(float[] positions, int[] indices ) {
        int vao = BufferHelper.genVAO();
        BufferHelper.storeData(0,3,positions);
        BufferHelper.bindIndices(indices);
        BufferHelper.unbindVAO();
        return new Mesh(vao,indices.length);
    }


    public static void setDataToMesh(Mesh mesh, int attribute, int dimensions, float[] data){
        int vao = mesh.getVaoID();
        BufferHelper.bindVAO(vao);
        BufferHelper.storeData(attribute, dimensions, data);
        BufferHelper.unbindVAO();
    }
}
