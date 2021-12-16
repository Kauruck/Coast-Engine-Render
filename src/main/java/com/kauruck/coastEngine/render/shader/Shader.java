package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.math.Vector3;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class Shader {

    public static final List<Shader> shaders = new ArrayList<>();

    public static void cleanUp(){
        for(Shader current : shaders){
            GL20.glDetachShader(current.programID, current.fragmentShader.getId());
            GL20.glDetachShader(current.programID, current.vertexShader.getId());
            GL20.glDeleteShader(current.fragmentShader.getId());
            GL20.glDeleteShader(current.vertexShader.getId());
            GL20.glDeleteProgram(current.programID);
        }
    }

    private int programID;

    private FloatBuffer matrix = BufferUtils.createFloatBuffer(16);

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
        getAllUniformLocations();
        shaders.add(this);
    }

    public Shader(ResourceLocation resourceLocation) throws NoHandlerException, IOException {
        vertexShader = ResourceLoader.loadResources(resourceLocation, VertexShader.class);
        fragmentShader = ResourceLoader.loadResources(resourceLocation, FragmentShader.class);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShader.getId());
        GL20.glAttachShader(programID, fragmentShader.getId());
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        //getAllUniformLocations();
        bindAttributes();
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

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3 vector) {
        GL20.glUniform3f(location, (float) vector.getX(), (float) vector.getY(), (float) vector.getZ());
    }

    protected void loadBoolean(int location, boolean value) {
        float tovec = 0;
        if(value) {
            tovec = 1;
        }
        GL20.glUniform1f(location, tovec);
    }


    //TODO Add Matrix to Core
    /*
    protected void loadMatrix(int location, Matrix4f value) {
        value.store(matrix);
        matrix.flip();
        GL20.glUniformMatrix4fv(location, false, matrix);
    }*/
}
