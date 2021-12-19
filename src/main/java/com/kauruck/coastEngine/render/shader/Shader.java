package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.math.Vector3;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Shader {

    public static final List<Shader> shaders = new ArrayList<>();

    private final Map<String, Integer> uniforms;

    public static void cleanUp(){
        for(Shader current : shaders){
            GL20.glDetachShader(current.programID, current.fragmentShader.getId());
            GL20.glDetachShader(current.programID, current.vertexShader.getId());
            GL20.glDeleteShader(current.fragmentShader.getId());
            GL20.glDeleteShader(current.vertexShader.getId());
            GL20.glDeleteProgram(current.programID);
        }
    }

    private final int programID;

    private final VertexShader vertexShader;
    private final FragmentShader fragmentShader;

    public Shader(VertexShader vertexShader, FragmentShader fragmentShader){
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShader.getId());
        GL20.glAttachShader(programID, fragmentShader.getId());
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        uniforms = new HashMap<>();
        getAllUniformLocations();
        if(this.uniforms.containsKey("projectionMatrix")){
            try {
                this.loadMatrix("projectionMatrix", Render.projectionMatrix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        shaders.add(this);
    }

    public Shader(ResourceLocation resourceLocation) throws NoHandlerException, IOException {
        vertexShader = ResourceLoader.loadResources(resourceLocation, VertexShader.class);
        fragmentShader = ResourceLoader.loadResources(resourceLocation, FragmentShader.class);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShader.getId());
        GL20.glAttachShader(programID, fragmentShader.getId());
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        uniforms = new HashMap<>();
        getAllUniformLocations();
        this.start();
        if(this.uniforms.containsKey("projectionMatrix")){
            try {
                this.loadMatrix("projectionMatrix", Render.projectionMatrix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.stop();
        shaders.add(this);
    }

    public void start(){
        GL20.glUseProgram(programID);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    protected abstract void bindAttributes();

    protected abstract void getAllUniformLocations();

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected void createUniform(String uniformName) throws Exception {
        int uniformLocation = GL20.glGetUniformLocation(programID,
                uniformName);
        if (uniformLocation < 0) {
            throw new Exception("Could not create uniform:" +
                    uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    protected int getUniformID(String name) throws Exception {
        if(!uniforms.containsKey(name)){
            createUniform(name);
        }

        return uniforms.get(name);
    }

    protected void loadFloat(String name, float value) throws Exception {
        GL20.glUniform1f(getUniformID(name), value);
    }

    protected void loadVector(String name, Vector3 vector) throws Exception {
        GL20.glUniform3f(getUniformID(name), (float) vector.getX(), (float) vector.getY(), (float) vector.getZ());
    }

    protected void loadBoolean(String name, boolean value) throws Exception {
        float tovec = 0;
        if(value) {
            tovec = 1;
        }
        GL20.glUniform1f(getUniformID(name), tovec);
    }

    protected void loadMatrix(String name, Matrix4f value) throws Exception {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            GL20.glUniformMatrix4fv(getUniformID(name), false, fb);
        }
    }

    public static void reloadProjectionsMatrix(){
        for(Shader current : shaders){
            if(current.uniforms.containsKey("projectionMatrix")){
                try {
                    current.loadMatrix("projectionMatrix", Render.projectionMatrix);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void loadInt(String name, int value) throws Exception {
        GL20.glUniform1i(getUniformID(name), value);
    }
}
