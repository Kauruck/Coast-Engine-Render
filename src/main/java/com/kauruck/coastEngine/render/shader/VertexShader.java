package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.resources.ResourceLocation;

public class VertexShader {
    private final int id;
    private final ResourceLocation resourceLocation;

    public VertexShader(int id, ResourceLocation resourceLocation) {
        this.id = id;
        this.resourceLocation = resourceLocation;
    }

    public int getId() {
        return id;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }
}
