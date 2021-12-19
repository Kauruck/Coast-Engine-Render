package com.kauruck.coastEngine.render.resources;

import com.kauruck.coastEngine.core.resources.ResourceHandler;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.textures.Texture;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class TextureHandler extends ResourceHandler<Texture> {
    @Override
    public String[] getValidFileExtensions() {
        return new String[]{"png"};
    }

    @Override
    public Texture loadFromStream(InputStream s, ResourceLocation resourceLocation) {
        AtomicInteger id = new AtomicInteger(-1);
        Render.scheduleOnRenderThreadBlocking(() -> {

            try (MemoryStack stack = MemoryStack.stackPush()){
                id.set(GL11.glGenTextures());
                GL11.glBindTexture(GL_TEXTURE_2D, id.get());

                // Set texture parameters
                // Repeat image in both directions
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
                // When stretching the image, pixelate
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                // When shrinking an image, pixelate
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

                IntBuffer width = stack.mallocInt(1);
                IntBuffer height = stack.mallocInt(1);
                IntBuffer channels = stack.mallocInt(1);

                byte[] data = s.readAllBytes();

                ByteBuffer imageBuffer = stack.malloc(data.length);
                imageBuffer.put(data);
                imageBuffer.flip();

                ByteBuffer image = stbi_load_from_memory(imageBuffer, width, height, channels, 0);

                if (image != null) {
                    if (channels.get(0) == 3) {
                        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0),
                                0, GL_RGB, GL_UNSIGNED_BYTE, image);
                    } else if (channels.get(0) == 4) {
                        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0),
                                0, GL_RGBA, GL_UNSIGNED_BYTE, image);
                    } else {
                        assert false : "Error: (Texture) Unknown number of channesl '" + channels.get(0) + "'";
                    }
                } else {
                    assert false : "Error: (Texture) Could not load image '" + resourceLocation + "'";
                }

                stbi_image_free(image);

                Texture.TEXTURE_IDS.put(resourceLocation, id.get());


            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        if(id.get() != -1)
            return new Texture(id.get(), resourceLocation);
        else
            return null;

    }

    @Override
    public boolean useStream() {
        return true;
    }
}
