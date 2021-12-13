package com.kauruck.coastEngine.render.shader;

import com.kauruck.coastEngine.core.resources.ResourceLocation;

public class FragmentShader {
    private final int id;
    private final ResourceLocation resourceLocation;

    public FragmentShader(int id, ResourceLocation resourceLocation) {
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
