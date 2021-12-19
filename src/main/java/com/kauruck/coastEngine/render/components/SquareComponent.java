package com.kauruck.coastEngine.render.components;

import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.color.Color;
import com.kauruck.coastEngine.render.rendering.BaseRender;
import com.kauruck.coastEngine.render.rendering.RenderHelper;
import com.kauruck.coastEngine.render.rendering.SquareRender;

public class SquareComponent extends RenderComponent{

    private Color color;

    public SquareComponent() {
        super(SquareRender.INSTANCE);
        createMesh();
    }

    public SquareComponent(Color color) {
        super(SquareRender.INSTANCE);
        createMesh();
        this.setColor(color);
    }

    private void createMesh(){
        Render.scheduleOnRenderThreadBlocking(() -> {
            this.setMesh(RenderHelper.createMesh(SquareRender.VERTICES, SquareRender.INDICES));
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        Render.scheduleOnRenderThread(() -> {
            RenderHelper.setDataToMesh(this.getMesh(), 1, 4, color.forGL(getMesh().getVertexCount()));
        });
    }
}
