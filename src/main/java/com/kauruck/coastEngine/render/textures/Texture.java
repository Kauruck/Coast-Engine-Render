package com.kauruck.coastEngine.render.textures;

import com.kauruck.coastEngine.core.resources.ResourceLocation;

import java.util.HashMap;

public class Texture {

    public static final HashMap<ResourceLocation, Integer> TEXTURE_IDS = new HashMap<>();

    private final ResourceLocation resourceLocation;

    private final int id;

    public Texture(int id, ResourceLocation resourceLocation) {
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
