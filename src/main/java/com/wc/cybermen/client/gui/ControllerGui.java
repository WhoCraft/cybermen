package com.wc.cybermen.client.gui;

import com.wc.cybermen.Cybermen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**
 * Created by Nictogen on 9/22/20.
 */
public abstract class ControllerGui extends Screen
{
	private static ResourceLocation BACKGROUND = new ResourceLocation(Cybermen.MODID, "textures/gui/controllerbackground.png");
	private static ResourceLocation FOREGROUND = new ResourceLocation(Cybermen.MODID, "textures/gui/controllerforeground.png");

	public ControllerGui()
	{
		super(new StringTextComponent("Cyber Controller"));
	}

	@Override public void render(int mouseX, int mouseY, float p_render_3_)
	{
		super.render(width, height, p_render_3_);
		minecraft.getTextureManager().bindTexture(BACKGROUND);
		blit(10, 10, 0, 0, width - 20, height - 20, width - 20, height - 20);

		renderContent(mouseX, mouseY, p_render_3_);

		minecraft.getTextureManager().bindTexture(FOREGROUND);
		blit(10, 10, 0, 0, width - 10, height - 10, width - 20, height - 20);

	}

	public abstract void renderContent(int mouseX, int mouseY, float p_render_3_);
}
