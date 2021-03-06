package com.daposeidonguy.teamsmod.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;

import java.awt.*;

/* Button with no background */
public class ClearButton extends Button {

    public ClearButton(int xIn, int yIn, int widthIn, int heightIn, final String message, final IPressable onPressIn) {
        super(xIn, yIn, widthIn, heightIn, message, onPressIn);
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, final float p_renderButton_3_) {
        Minecraft.getInstance().fontRenderer.drawString(this.getMessage(), x, y, Color.GRAY.getRGB());
    }
}
