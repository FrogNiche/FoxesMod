package com.frogniche.foxmod.events;

import com.frogniche.foxmod.FoxMod;
import com.frogniche.foxmod.entity.EntityInit;
import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsEntity;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable.UnbreakableEntity;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer.DevourerEntity;
import com.frogniche.foxmod.entity.legendsmobs.variants.seeker.SeekerEntity;
import com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic.SporeMedicEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = FoxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerForgeEvents {

    public static void entityAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.KING_PAWS.get(), KingPawsEntity.createAttributes());

        event.put(EntityInit.SEEKER.get(), SeekerEntity.createAttributes());
        //spore horde
        event.put(EntityInit.DEVOURER.get(), DevourerEntity.makeAttributes());

        event.put(EntityInit.UNBREAKABLE.get(), UnbreakableEntity.createAttributes());


        event.put(EntityInit.SPORE_MEDIC.get(), SporeMedicEntity.createAttributes());
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(ServerForgeEvents::enquqedCommonWork);
    }

    private static void enquqedCommonWork(){
        SpawnPlacements.register(EntityInit.DEVOURER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
    }
}
