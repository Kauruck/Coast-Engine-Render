package com.kauruck.coastEngine.render.rendering;

import java.util.HashMap;
import java.util.Map;

public class Mesh {

    //Names for the VBOs
    public static final String POSITION_BUFFER = "position";
    public static final String COLOR_BUFFER = "color";

    private int vao;
    private int vertices;

    private Map<String, Integer> vbos = new HashMap<>();

    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
    }
    public int getVaoID() {
        return vao;
    }
    public int getVertexCount() {
        return vertices;
    }

    public void addVBO(String name, int vbo) {
        vbos.put(name, vbo);
    }

    public boolean hasVBO(String name){
        return vbos.containsKey(name);
    }

    public int getVBO(String name){
        return vbos.get(name);
    }
}
