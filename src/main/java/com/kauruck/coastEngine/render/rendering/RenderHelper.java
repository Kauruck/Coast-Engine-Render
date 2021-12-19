package com.kauruck.coastEngine.render.rendering;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHelper {

    public static Mesh createMesh(float[] positions, int[] indices ) {
        int vao = BufferHelper.genVAO();
        int posVBO = BufferHelper.storeData(0,3,positions);
        BufferHelper.bindIndices(indices);
        BufferHelper.unbindVAO();
        Mesh mesh = new Mesh(vao, indices.length);
        mesh.addVBO(Mesh.POSITION_BUFFER, posVBO);
        return mesh;
    }


    public static void setDataToMesh(Mesh mesh, int attribute, int dimensions, float[] data){
        if(mesh.hasVBO(Mesh.COLOR_BUFFER)) {
            int vbo = mesh.getVBO(Mesh.COLOR_BUFFER);
            BufferHelper.updateData(vbo, data);
        }
        else {
            BufferHelper.bindVAO(mesh.getVaoID());
            int vbo = BufferHelper.storeData(attribute, dimensions, data);
            BufferHelper.unbindVAO();
            mesh.addVBO(Mesh.COLOR_BUFFER, vbo);
        }
    }
}
