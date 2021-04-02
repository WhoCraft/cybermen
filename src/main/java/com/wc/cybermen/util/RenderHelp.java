package com.wc.cybermen.util;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Matrix4f;

/* Created by Craig on 02/04/2021 */
public class RenderHelp {

    public static void renderFilledBox(Matrix4f matrix, IVertexBuilder builder, AxisAlignedBB boundingBox, float red, float green, float blue, float alpha, int combinedLightIn) {
        renderFilledBox(matrix, builder, (float) boundingBox.minX, (float) boundingBox.minY, (float) boundingBox.minZ, (float) boundingBox.maxX, (float) boundingBox.maxY, (float) boundingBox.maxZ, red, green, blue, alpha, combinedLightIn);
    }

    public static void renderFilledBox(Matrix4f matrix, IVertexBuilder builder, float startX, float startY, float startZ, float endX, float endY, float endZ, float red, float green, float blue, float alpha, int combinedLightIn) {
        //down
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        //up
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        //east
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        //west
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        //south
        builder.vertex(matrix, endX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, endX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();

        //north
        builder.vertex(matrix, startX, startY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, startY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, endY, endZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
        builder.vertex(matrix, startX, endY, startZ).color(red, green, blue, alpha).uv2(combinedLightIn).endVertex();
    }

    public static void drawGlowingLine(Matrix4f matrix, IVertexBuilder builder, float length, float width, float red, float green, float blue, float alpha, int combinedLightIn) {
        AxisAlignedBB box = new AxisAlignedBB(-width / 2F, 0, -width / 2F, width / 2F, length, width / 2F);
        renderFilledBox(matrix, builder, box, 1F, 1F, 1F, alpha, combinedLightIn);

        for (int i = 0; i < 3; i++) {
            renderFilledBox(matrix, builder, box.inflate(i * 0.5F * 0.0625F), red, green, blue, (1F / i / 2) * alpha, combinedLightIn);
        }
    }


}
