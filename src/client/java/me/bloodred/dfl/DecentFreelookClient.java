package me.bloodred.dfl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class DecentFreelookClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FreelookKeyBindings.register();
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            boolean isPressed = FreelookKeyBindings.FREELOOK_KEY.isDown();
            FreelookManager.getInstance().handleKeyInput(isPressed);
        });
    }
}