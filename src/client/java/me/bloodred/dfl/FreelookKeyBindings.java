package me.bloodred.dfl;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class FreelookKeyBindings {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath("decentfreelook", "general")
    );
    
    public static KeyMapping FREELOOK_KEY;

    public static void register() {
        FREELOOK_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.decentfreelook.freelook",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F6,
            CATEGORY
        ));
    }
}