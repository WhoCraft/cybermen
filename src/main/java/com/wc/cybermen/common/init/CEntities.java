package com.wc.cybermen.common.init;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.common.entity.projectile.LaserProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/* Created by Craig on 02/04/2021 */
public class CEntities {

    public static final DeferredRegister< EntityType< ? > > ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Cybermen.MODID);
    public static RegistryObject< EntityType< LaserProjectile > > LASER = ENTITIES.register("laser", () -> registerNoSpawnerBase(LaserProjectile::new, EntityClassification.MISC, 0.25F, 0.25F, 128, 1, true, "laser"));

    private static < T extends Entity > EntityType< T > registerNoSpawnerBase(EntityType.IFactory< T > factory, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(Cybermen.MODID, name);
        EntityType.Builder< T > builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);

        builder.sized(width, height);
        return builder.build(loc.toString());
    }

    private static < T extends Entity > EntityType< T > registerBase(EntityType.IFactory< T > factory, IClientSpawner< T > client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(Cybermen.MODID, name);
        EntityType.Builder< T > builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        return builder.build(loc.toString());
    }

    // Fire Resistant Entity Creation
    private static < T extends Entity > EntityType< T > registerFireImmuneBase(EntityType.IFactory< T > factory, IClientSpawner< T > client, EntityClassification classification, float width, float height, int trackingRange, int updateFreq, boolean sendUpdate, String name) {
        ResourceLocation loc = new ResourceLocation(Cybermen.MODID, name);
        EntityType.Builder< T > builder = EntityType.Builder.of(factory, classification);
        builder.setShouldReceiveVelocityUpdates(sendUpdate);
        builder.setTrackingRange(trackingRange);
        builder.setUpdateInterval(updateFreq);
        builder.fireImmune();
        builder.sized(width, height);
        builder.setCustomClientFactory((spawnEntity, world) -> client.spawn(world));
        EntityType< T > type = builder.build(loc.toString());
        return type;
    }

    private static < T extends Entity > EntityType< T > registerFireResistMob(EntityType.IFactory< T > factory, IClientSpawner< T > client, EntityClassification classification, float width, float height, String name, boolean velocity) {
        return registerFireImmuneBase(factory, client, classification, width, height, 80, 3, velocity, name);
    }

    private static < T extends Entity > EntityType< T > registerStatic(EntityType.IFactory< T > factory, IClientSpawner< T > client, EntityClassification classification, float width, float height, String name) {
        return registerBase(factory, client, classification, width, height, 64, 40, false, name);
    }

    private static < T extends Entity > EntityType< T > registerMob(EntityType.IFactory< T > factory, IClientSpawner< T > client, EntityClassification classification, float width, float height, String name, boolean velocity) {
        return registerBase(factory, client, classification, width, height, 80, 3, velocity, name);
    }

    private static < T extends Entity > EntityType< T > registerNonSpawner(EntityType.IFactory< T > factory, EntityClassification classification, float width, float height, boolean velocity, String name) {
        return registerNoSpawnerBase(factory, classification, width, height, 64, 40, velocity, name);
    }

    public interface IClientSpawner< T > {
        T spawn(World world);
    }

}
