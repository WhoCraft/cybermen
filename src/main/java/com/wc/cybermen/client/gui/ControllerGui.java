package com.wc.cybermen.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.wc.cybermen.Cybermen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**
 * Created by Nictogen on 9/22/20.
 */
public abstract class ControllerGui extends Screen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(Cybermen.MODID, "textures/gui/controllerbackground.png");
    private static final ResourceLocation FOREGROUND = new ResourceLocation(Cybermen.MODID, "textures/gui/controllerforeground.png");

    public ControllerGui() {
        super(new StringTextComponent("Cyber Controller"));
    }

    //TODO, Hi Drake, I think I ported this up badly, that or it looked that way before, but the BACKGROUND seems to repeat
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        matrixStack.push();
        minecraft.getTextureManager().bindTexture(BACKGROUND);
        blit(matrixStack, 10, 10, 0, 0, width - 20, height - 20, width - 20, height - 20);
        matrixStack.pop();

        renderContent(matrixStack, mouseX, mouseY, partialTicks);

        matrixStack.push();
        minecraft.getTextureManager().bindTexture(FOREGROUND);
        blit(matrixStack, 10, 10, 0, 0, width - 10, height - 10, width - 20, height - 20);
        matrixStack.pop();
    }


    public abstract void renderContent(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks);
}
