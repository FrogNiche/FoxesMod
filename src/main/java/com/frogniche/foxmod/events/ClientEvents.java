package com.frogniche.foxmod.events;

import com.frogniche.foxmod.FoxMod;
import com.frogniche.foxmod.entity.EntityInit;
import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsRenderer;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable.UnbreakableModel;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer.DevourerModel;
import com.frogniche.foxmod.entity.legendsmobs.variants.seeker.SeekerRenderer;
import com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic.SporeMedicModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FoxMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    public static void clientSetup(FMLClientSetupEvent event){
        //Dungeons Mobs
        EntityRenderers.register(EntityInit.KING_PAWS.get(), KingPawsRenderer::new);

        EntityRenderers.register(EntityInit.SEEKER.get(), SeekerRenderer::new);
//Horde of the spore
        EntityRenderers.register(EntityInit.DEVOURER.get(), makeRenderer(new DevourerModel()));

        EntityRenderers.register(EntityInit.UNBREAKABLE.get(), makeRenderer(new UnbreakableModel()));

        EntityRenderers.register(EntityInit.SPORE_MEDIC.get(), makeRenderer(new SporeMedicModel()));
    }

    public static <T extends LivingEntity & IAnimatable> EntityRendererProvider<T> makeRenderer(AnimatedGeoModel<T> model){
        return m -> new HelperGeoRenderer<>(m, model);
    }

    public static class HelperGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {

        public HelperGeoRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
            super(renderManager, modelProvider);
        }
    }
}
