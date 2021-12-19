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

    public static Mesh createMesh(float[] positions, int[] indices, float[] uv ) {
        int vao = BufferHelper.genVAO();
        int posVBO = BufferHelper.storeData(0,3,positions);
        BufferHelper.bindIndices(indices);
        BufferHelper.unbindVAO();
        Mesh mesh = new Mesh(vao, indices.length);
        mesh.addVBO(Mesh.POSITION_BUFFER, posVBO);
        setDataToMesh(mesh, Mesh.UV_BUFFER, 1, 2, uv);
        return mesh;
    }


    public static void setDataToMesh(Mesh mesh, String name, int attribute, int dimensions, float[] data){
        if(mesh.hasVBO(name)) {
            int vbo = mesh.getVBO(name);
            BufferHelper.updateData(vbo, data);
        }
        else {
            BufferHelper.bindVAO(mesh.getVaoID());
            int vbo = BufferHelper.storeData(attribute, dimensions, data);
            BufferHelper.unbindVAO();
            mesh.addVBO(name, vbo);
        }
    }
}
