package me.bloodred.dfl.mixin;

import me.bloodred.dfl.FreelookManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public Options options;

    @Inject(method = "handleKeybinds", at = @At("HEAD"))
    private void onHandleKeybinds(CallbackInfo ci) {
        FreelookManager manager = FreelookManager.getInstance();
        if (manager.isFreelookEnabled()) {
            
            while (this.options.keyTogglePerspective.consumeClick()) {
                manager.stopFreelook();
                
                
                
                
            }
        }
    }
}