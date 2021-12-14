package com.kauruck.coastEngine.render.components;

import com.kauruck.coastEngine.render.rendering.BaseRender;
import com.kauruck.coastEngine.render.rendering.RenderHelper;
import com.kauruck.coastEngine.render.rendering.SquareRender;
import com.kauruck.coastEngine.render.rendering.TextureRender;
import com.kauruck.coastEngine.render.textures.Texture;

public class TextureComponent extends RenderComponent{

    private Texture texture;

    public TextureComponent() {
        super(TextureRender.INSTANCE, RenderHelper.createMesh(TextureRender.VERTICES, TextureRender.INDICES));
    }

    public TextureComponent(Texture texture) {
        super(TextureRender.INSTANCE, RenderHelper.createMesh(TextureRender.VERTICES, TextureRender.INDICES));
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
