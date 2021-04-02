package com.wc.cybermen.common.item;

import com.wc.cybermen.common.entity.projectile.LaserProjectile;
import com.wc.cybermen.common.init.CEntities;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

/* Created by Craig on 01/03/2021 */
public class GunItem extends Item {

    private final int cooldown;
    private final float damage;
    private final Supplier< SoundEvent> gunSound;

    public GunItem(int shotsPerRound, int cooldown, float damage, Supplier< SoundEvent> soundEventSupplier) {
        super(new Properties().tab(null).durability(shotsPerRound).setNoRepair());
        this.cooldown = cooldown;
        this.damage = damage;
        this.gunSound = soundEventSupplier;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        return super.useOn(context);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity) entityLiving;
            if (stack.getDamageValue() < stack.getMaxDamage() && !playerIn.getCooldowns().isOnCooldown(this)) {
                worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), gunSound.get(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                playerIn.getCooldowns().addCooldown(this, cooldown);
                setDamage(stack, getDamage(stack) + 1);
                if (!worldIn.isClientSide) {
                    LaserProjectile laserProjectile = new LaserProjectile(CEntities.LASER.get(), playerIn, worldIn);
                    laserProjectile.setDamage(damage);
                    laserProjectile.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
                    entityLiving.playSound(gunSound.get(), 1.0F, 0.4F / (worldIn.random.nextFloat() * 0.4F + 0.8F));
                    worldIn.addFreshEntity(laserProjectile);
                }
            }
        }
    }

    @Override
    public ActionResult< ItemStack > use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.startUsingItem(handIn);
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (entityIn.tickCount % 100 == 0) {
            if (getDamage(stack) > 0) {
                setDamage(stack, getDamage(stack) - 1);
            }
        }

        if (entityIn instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) entityIn;
            if (getDamage(stack) == getMaxDamage(stack)) {
                playerEntity.getCooldowns().addCooldown(this, getMaxDamage(stack) * cooldown * 20);
            }
        }
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return false;
    }
}