package com.kauruck.coastEngine.render.resources;

import com.kauruck.coastEngine.core.resources.ResourceHandler;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.shader.FragmentShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class FragmentShaderHandler extends ResourceHandler<FragmentShader> {
    @Override
    public String[] getValidFileExtensions() {
        return new String[]{"fs"};
    }

    @Override
    public FragmentShader loadFromString(String source, ResourceLocation resourceLocation) {
        int id = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(id, source);
        GL20.glCompileShader(id);
        if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            Render.LOGGER.error("Couldn't compile the shader: " + resourceLocation + "\n -----[Error Log]---- \n" + GL20.glGetShaderInfoLog(id, 512));
            System.exit(-1);
        }
        return new FragmentShader(id, resourceLocation);
    }
}
