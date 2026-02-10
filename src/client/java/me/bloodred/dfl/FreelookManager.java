package me.bloodred.dfl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;
import net.minecraft.client.player.LocalPlayer;

public class FreelookManager {
    private static final FreelookManager INSTANCE = new FreelookManager();
    
    private boolean freelookEnabled = false;
    private boolean toggleMode = false;
    private long pressTime = 0;
    private CameraType originalPerspective = CameraType.FIRST_PERSON;
    
    private float cameraYaw = 0.0f;
    private float cameraPitch = 0.0f;
    private boolean firstTime = true;

    public static FreelookManager getInstance() {
        return INSTANCE;
    }

    private boolean lastPressedState = false;

    public void handleKeyInput(boolean isPressed) {
        long currentTime = System.currentTimeMillis();
        
        if (isPressed && !lastPressedState) {
            
            if (!freelookEnabled) {
                startFreelook();
                pressTime = currentTime;
            } else if (toggleMode) {
                stopFreelook();
            }
        } else if (!isPressed && lastPressedState) {
            
            if (freelookEnabled && !toggleMode) {
                if (currentTime - pressTime > 250) { 
                    stopFreelook();
                } else {
                    toggleMode = true;
                }
            }
        }
        
        lastPressedState = isPressed;
    }

    public void startFreelook() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        freelookEnabled = true;
        toggleMode = false;
        firstTime = true;
        
        cameraYaw = mc.player.getYRot();
        cameraPitch = mc.player.getXRot();
        
        originalPerspective = mc.options.getCameraType();
        
        
        if (originalPerspective == CameraType.FIRST_PERSON) {
            mc.options.setCameraType(CameraType.THIRD_PERSON_BACK);
        }
    }

    public void stopFreelook() {
        if (!freelookEnabled) return;
        
        freelookEnabled = false;
        toggleMode = false;
        
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.options.setCameraType(originalPerspective);
        }
    }

    public void updateRotation(double deltaX, double deltaY) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) return;

        if (firstTime) {
            cameraYaw = player.getYRot();
            cameraPitch = player.getXRot();
            firstTime = false;
        }

        double sensitivity = mc.options.sensitivity().get() * 0.6 + 0.2;
        double multiplier = sensitivity * sensitivity * sensitivity * 8.0;
        
        cameraYaw += (float) (deltaX * multiplier * 0.15);
        cameraPitch += (float) (deltaY * multiplier * 0.15);
        cameraPitch = Math.max(-90.0f, Math.min(90.0f, cameraPitch));
    }

    public boolean isFreelookEnabled() {
        return freelookEnabled;
    }

    public float getCameraYaw() {
        return cameraYaw;
    }

    public float getCameraPitch() {
        return cameraPitch;
    }
}