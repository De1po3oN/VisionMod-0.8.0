package com.visionmod.mixin;

import com.visionmod.VisionExtras;
import com.visionmod.VisionModClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Low, compact first-person fire overlay. Off by default for compatibility. */
@Mixin(ScreenEffectRenderer.class)
public abstract class LowFireMixin {
    @Inject(method = "renderFire", at = @At("HEAD"), require = 0)
    private static void visionmod$fireBegin(Minecraft mc, PoseStack pose, CallbackInfo ci) {
        if (VisionModClient.enabled && VisionExtras.lowFire) {
            pose.pushPose();
            pose.translate(0.0f, -0.12f, 0.0f);
            pose.scale(1.0f, 0.72f, 1.0f);
        }
    }
    @Inject(method = "renderFire", at = @At("RETURN"), require = 0)
    private static void visionmod$fireEnd(Minecraft mc, PoseStack pose, CallbackInfo ci) {
        if (VisionModClient.enabled && VisionExtras.lowFire) pose.popPose();
    }
}
