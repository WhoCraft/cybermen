package com.wc.cybermen.common.init;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.common.item.GunItem;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/* Created by Craig on 25/03/2021 */
public class CItems {
    public static final DeferredRegister< Item > ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cybermen.MODID);

    public static RegistryObject< Item > GUN = ITEMS.register("gun_earthshock", () -> new GunItem(18, 5, 4.0F, () -> SoundEvents.CAT_AMBIENT));


}
