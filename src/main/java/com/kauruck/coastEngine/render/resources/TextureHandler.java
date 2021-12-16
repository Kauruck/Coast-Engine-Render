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

import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;

public class TextureHandler extends ResourceHandler<Texture> {
    @Override
    public String[] getValidFileExtensions() {
        return new String[]{"png"};
    }

    @Override
    public Texture loadFromStream(InputStream s, ResourceLocation resourceLocation) {
        AtomicInteger id = new AtomicInteger(-1);
        Render.scheduleOnRenderThreadBlocking(() -> {
            int width;
            int height;
            ByteBuffer buffer;
            try (MemoryStack stack = MemoryStack.stackPush()){
                IntBuffer w = stack.mallocInt(1);
                IntBuffer h = stack.mallocInt(1);

                byte[] bytes = s.readAllBytes();

                buffer = BufferUtils.createByteBuffer(bytes.length);
                buffer.put(bytes);
                buffer.flip();

                width = w.get();
                height = h.get();

                id.set(GL11.glGenTextures());
                Texture.TEXTURE_IDS.put(resourceLocation, id.get());
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, id.get());

                GL11.glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
                GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
                //Maybe I do need a stbi_image_free, but I think I don't, for that i never load an image with stbi_image_load

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
