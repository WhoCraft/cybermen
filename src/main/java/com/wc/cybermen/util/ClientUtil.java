package com.wc.cybermen.util;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.client.entity.RenderLaser;
import com.wc.cybermen.common.init.CEntities;
import com.wc.cybermen.common.init.CItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/* Created by Craig on 02/04/2021 */
public class ClientUtil {

    public static void init() {
        itemPredicates();
        entityRender();
    }

    private static void entityRender() {
        RenderingRegistry.registerEntityRenderingHandler(CEntities.LASER.get(), RenderLaser::new);
    }

    private static void itemPredicates() {
        ItemModelsProperties.register(CItems.GUN.get(), new ResourceLocation(Cybermen.MODID, "aim"), (stack, p_call_2_, livingEntity) -> {
            if (livingEntity == null) {
                return 0;
            }
            return livingEntity.getUseItemRemainingTicks() > 0 ? 1 : 0;
        });

    }
}
