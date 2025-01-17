package com.cannolicatfish.rankine;

import com.cannolicatfish.rankine.client.renders.*;
import com.cannolicatfish.rankine.enchantment.*;
import com.cannolicatfish.rankine.fluids.*;
import com.cannolicatfish.rankine.init.*;
import com.cannolicatfish.rankine.init.packets.RankinePacketHandler;
import com.cannolicatfish.rankine.loot.SurfRodModifier;
import com.cannolicatfish.rankine.potion.RankineEffects;
import com.cannolicatfish.rankine.potion.RankinePotions;
import com.cannolicatfish.rankine.recipe.*;
import com.cannolicatfish.rankine.util.WorldgenUtils;
import com.cannolicatfish.rankine.util.colors.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod("rankine")
public class ProjectRankine {
    public static final String MODID = "rankine";
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static RankineSetup setup = new RankineSetup();
    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectRankine() {
        IEventBus Bus = FMLJavaModLoadingContext.get().getModEventBus();


        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG, "rankine-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT_CONFIG, "rankine-client.toml");

        MinecraftForge.EVENT_BUS.register(this);
        ForgeMod.enableMilkFluid();
        Bus.addListener(this::CommonSetup);
        Bus.addListener(this::ClientSetup);

        RankineBlocks.BLOCKS.register(Bus);
        RankineItems.ITEMS.register(Bus);
        RankineEntityTypes.ENTITY_TYPES.register(Bus);
        RankineBlockEntityTypes.BLOCK_ENTITY_TYPES.register(Bus);
        RankineContainers.CONTAINERS.register(Bus);
        RankineFeatures.FEATURES.register(Bus);
        RankineConfiguredFeatures.CONFIGURED_FEATURES.register(Bus);
        RankinePlacedFeatures.PLACED_FEATURES.register(Bus);
        RankineSoundEvents.SOUNDS.register(Bus);

        Bus.addListener(this::LoadComplete);

        WorldgenUtils.initOreTextures();

    }

    private void CommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.debug("Rankine: \"CommonSetup\" Starting...");

        WorldgenUtils.initConfigs();
        RankinePacketHandler.register();
        proxy.init();


        event.enqueueWork(() -> {
            RankineRecipes.registerPredicates();
            RankineRecipes.registerPotionRecipes();
            RankineRecipes.registerDispenserBehaviors();
        });
        for (WoodType wood : RankineLists.WOOD_TYPES) {
            WoodType.register(wood);
        }

        LOGGER.info("Rankine: \"CommonSetup\" Event Complete!");
    }

    private void ClientSetup(FMLClientSetupEvent event) {
        LOGGER.debug("Rankine: \"ClientSetup Event\" Starting...");

        event.enqueueWork(ClientProxy::registerItemProperties);
        event.enqueueWork(() -> {
            RankineBlockEntityTypes.registerBlockEntityRenders();

            for (WoodType wood : RankineLists.WOOD_TYPES) {
                Sheets.addWoodType(wood);
            }
        });
        LOGGER.info("Rankine: \"ClientSetup\" Event Complete!");
    }

    private void LoadComplete(FMLLoadCompleteEvent event) {
        LOGGER.debug("Rankine: \"Load Complete Event\" Starting...");

        VanillaIntegration.init();

        LOGGER.info("Rankine: \"Load Complete\" Event Complete!");
    }



    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void onItemColorRegistry(final ColorHandlerEvent.Item event) {
            for (Block b : RankineLists.GRASS_BLOCKS) {
                event.getItemColors().register(new GrassItemBaseColor(), b.asItem());
            }
            for (Block b : Stream.of(RankineLists.SHEETMETALS,RankineLists.ALLOY_SHEETMETALS).flatMap(Collection::stream).collect(Collectors.toList())) {
                event.getItemColors().register(new NonAlloyItemColor(), b);
            }
            for (Block b : RankineLists.GLAZED_PORCELAIN_BLOCKS) {
                event.getItemColors().register(new NonAlloyItemColor(), b);
            }
            for (Block b : RankineLists.ALLOY_POLES) {
                event.getItemColors().register(new NonAlloyItemColor(), b);
            }
            for (Block b : RankineLists.ALLOY_BARS) {
                event.getItemColors().register(new NonAlloyItemColor(), b);
            }
            event.getItemColors().register(new GrassItemBaseColor(), RankineItems.SHORT_GRASS.get());

            event.getItemColors().register(new GrassItemBaseColor(), RankineItems.SOD_BLOCK.get());

            event.getItemColors().register(new LeavesItemBaseColor(), RankineBlocks.BIRCH_LEAF_LITTER.get(), RankineBlocks.SPRUCE_LEAF_LITTER.get(), RankineBlocks.ACACIA_LEAF_LITTER.get(), RankineBlocks.JUNGLE_LEAF_LITTER.get(), RankineBlocks.DARK_OAK_LEAF_LITTER.get(), RankineBlocks.OAK_LEAF_LITTER.get());

            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_BLOCK::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_GEAR::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_DUST::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_INGOT::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_NUGGET::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_PLATE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_ROD::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_WIRE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_PICKAXE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_AXE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_SHOVEL::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_SWORD::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_HOE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_SPEAR::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_HAMMER::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_KNIFE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_CROWBAR::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_BLUNDERBUSS::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_SURF_ROD::get);
            event.getItemColors().register(new TemplateItemColor(), RankineItems.ALLOY_TEMPLATE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_HELMET::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_CHESTPLATE::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_LEGGINGS::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_BOOTS::get);
            event.getItemColors().register(new AlloyItemColor(), RankineItems.ALLOY_ARROW::get);
            event.getItemColors().register(new SGVDItemColor(), RankineItems.SHULKER_GAS_VACUUM::get);
        }


        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void onBlockColorRegistry(final ColorHandlerEvent.Block event) {
            event.getBlockColors().register(new CrucibleColor(), RankineBlocks.CRUCIBLE_BLOCK.get());
            for (Block b : RankineLists.GRASS_BLOCKS) {
                event.getBlockColors().register(new GrassBlockBaseColor(), b);
            }
            event.getBlockColors().register(new AlloyBlockColor(), RankineBlocks.ALLOY_BLOCK.get());
            event.getBlockColors().register(new OrnamentColor(), RankineBlocks.ORNAMENT.get());
            event.getBlockColors().register(new GrassBlockBaseColor(), RankineBlocks.SHORT_GRASS.get());
            event.getBlockColors().register(new GrassBlockBaseColor(), RankineBlocks.SOD_BLOCK.get());
            event.getBlockColors().register(new LeavesBlockBaseColor(), RankineBlocks.BIRCH_LEAF_LITTER.get(), RankineBlocks.SPRUCE_LEAF_LITTER.get(), RankineBlocks.ACACIA_LEAF_LITTER.get(), RankineBlocks.JUNGLE_LEAF_LITTER.get(), RankineBlocks.DARK_OAK_LEAF_LITTER.get(), RankineBlocks.OAK_LEAF_LITTER.get());

            for (Block b : Stream.of(RankineLists.SHEETMETALS,RankineLists.ALLOY_SHEETMETALS).flatMap(Collection::stream).collect(Collectors.toList())) {
                event.getBlockColors().register(new NonAlloyBlockColor(), b);
            }
            for (Block b : RankineLists.GLAZED_PORCELAIN_BLOCKS) {
                event.getBlockColors().register(new NonAlloyBlockColor(), b);
            }
            for (Block b : RankineLists.ALLOY_POLES) {
                event.getBlockColors().register(new NonAlloyBlockColor(), b);
            }
            for (Block b : RankineLists.ALLOY_BARS) {
                event.getBlockColors().register(new NonAlloyBlockColor(), b);
            }

        }

        @SubscribeEvent
        public static void onPOIRegistry(final RegistryEvent.Register<PoiType> event) {
            event.getRegistry().register(RankinePOIs.TEMPLATE_TABLE_POI.setRegistryName(ProjectRankine.MODID,"template_table_poi"));
            event.getRegistry().register(RankinePOIs.PISTON_CRUSHER_POI.setRegistryName(ProjectRankine.MODID,"piston_crusher_poi"));
            event.getRegistry().register(RankinePOIs.BOTANIST_STATION_POI.setRegistryName(ProjectRankine.MODID,"potted_plant_poi"));
            event.getRegistry().register(RankinePOIs.GEM_CUTTER_POI.setRegistryName(ProjectRankine.MODID,"gem_cutter_poi"));
            event.getRegistry().register(RankinePOIs.ROCK_COLLECTOR_POI.setRegistryName(ProjectRankine.MODID,"rock_collector_poi"));
        }


        @SubscribeEvent
        public static void onVillagerProfessionRegistry(final RegistryEvent.Register<VillagerProfession> event) {
            event.getRegistry().register(RankineVillagerProfessions.METALLURGIST.setRegistryName(ProjectRankine.MODID,"metallurgist"));
            event.getRegistry().register(RankineVillagerProfessions.MINERALOGIST.setRegistryName(ProjectRankine.MODID,"mineralogist"));
            event.getRegistry().register(RankineVillagerProfessions.BOTANIST.setRegistryName(ProjectRankine.MODID,"botanist"));
            event.getRegistry().register(RankineVillagerProfessions.GEM_CUTTER.setRegistryName(ProjectRankine.MODID,"gem_cutter"));
            event.getRegistry().register(RankineVillagerProfessions.ROCK_COLLECTOR.setRegistryName(ProjectRankine.MODID,"rock_collector"));
        }

        @SubscribeEvent
         public static void onRecipeSerializersRegistry(final RegistryEvent.Register<RecipeSerializer<?>> event) {
            event.getRegistry().register(AlloyCraftingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"alloy_crafting"));
            event.getRegistry().register(CrushingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"crushing"));
            event.getRegistry().register(AlloyingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"alloying"));
            event.getRegistry().register(BeehiveOvenRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"beehive_oven"));
            event.getRegistry().register(SluicingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"sluicing"));
            event.getRegistry().register(ElementRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"element"));
            event.getRegistry().register(CrucibleRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"crucible"));
            event.getRegistry().register(EvaporationRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"evaporation"));
            event.getRegistry().register(FusionFurnaceRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"fusion_furnace"));
            event.getRegistry().register(RockGeneratorRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"rock_generator"));
            event.getRegistry().register(TreetappingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"treetapping"));
            event.getRegistry().register(StrippingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"stripping"));
            event.getRegistry().register(ForagingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"foraging"));
            event.getRegistry().register(AirDistillationRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"air_distillation"));
            event.getRegistry().register(MixingRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"mixing"));
            event.getRegistry().register(AlloyModifierRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"alloy_modifier"));

            event.getRegistry().register(JamRecipe.SERIALIZER.setRegistryName(ProjectRankine.MODID,"crafting_special_jam"));
        }

        @SubscribeEvent
        public static void onGlobalLootModifierSerializersRegistry(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry().register(SurfRodModifier.SERIALIZER.setRegistryName(ProjectRankine.MODID,"surf_rod_modifier"));
        }


        @SubscribeEvent
        public static void clientSetupEvent(FMLClientSetupEvent event)
        {
;
        }

        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerEntityRenderer(RankineEntityTypes.ALLOY_SPEAR.get(), SpearEntityRenderer.instance);
            event.registerEntityRenderer(RankineEntityTypes.REACTIVE_ITEM.get(), ReactiveItemRenderer::new);
            event.registerEntityRenderer(RankineEntityTypes.RANKINE_BOAT.get(),RankineBoatRenderer.instance);
            event.registerEntityRenderer(RankineEntityTypes.ROPE_COIL_ARROW.get(), RopeCoilArrowRenderer.instance);
            event.registerEntityRenderer(RankineEntityTypes.ALLOY_ARROW.get(),AlloyArrowRenderer.instance);
            event.registerEntityRenderer(RankineEntityTypes.CANNONBALL.get(), CannonballRenderer::new);
            event.registerEntityRenderer(RankineEntityTypes.ENDERBALL.get(), EnderballRenderer::new);
            event.registerEntityRenderer(RankineEntityTypes.CARCASS.get(), CarcassRenderer::new);
            event.registerEntityRenderer(RankineEntityTypes.BALLOON.get(), BalloonRenderer::new);
        }


        @SubscribeEvent
        public static void registerEffects(final RegistryEvent.Register<MobEffect> event) {
            event.getRegistry().registerAll(
                    RankineEffects.MERCURY_POISONING,
                    RankineEffects.CONDUCTIVE,
                    RankineEffects.RADIATION_POISONING
            );
        }

        @SubscribeEvent
        public static void registerPotions(final RegistryEvent.Register<Potion> event) {
            event.getRegistry().registerAll(
                    RankinePotions.MERCURY_POISON,
                    RankinePotions.CONDUCTIVE_POTION
            );
        }

        @SubscribeEvent
        public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.LIQUID_MERCURY, () -> RankineFluids.LIQUID_MERCURY_FLOWING, FluidAttributes.builder(LiquidMercuryFluid.FLUID_STILL, LiquidMercuryFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(LiquidMercuryFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.LIQUID_MERCURY_BUCKET).block(() -> (LiquidBlock) RankineBlocks.LIQUID_MERCURY.get())).setRegistryName(ProjectRankine.MODID,"liquid_mercury"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.LIQUID_MERCURY, () -> RankineFluids.LIQUID_MERCURY_FLOWING, FluidAttributes.builder(LiquidMercuryFluid.FLUID_STILL, LiquidMercuryFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(LiquidMercuryFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.LIQUID_MERCURY_BUCKET).block(() -> (LiquidBlock) RankineBlocks.LIQUID_MERCURY.get())).setRegistryName(ProjectRankine.MODID,"liquid_mercury_flowing"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.SAP, () -> RankineFluids.FLOWING_SAP, FluidAttributes.builder(SapFluid.FLUID_STILL,SapFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(SapFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.SAP_BUCKET).block(() -> (LiquidBlock) RankineBlocks.SAP.get())).setRegistryName(ProjectRankine.MODID,"sap"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.SAP, () -> RankineFluids.FLOWING_SAP, FluidAttributes.builder(SapFluid.FLUID_STILL,SapFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(SapFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.SAP_BUCKET).block(() -> (LiquidBlock) RankineBlocks.SAP.get())).setRegistryName(ProjectRankine.MODID,"flowing_sap"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.MAPLE_SAP, () -> RankineFluids.FLOWING_MAPLE_SAP, FluidAttributes.builder(MapleSapFluid.FLUID_STILL,MapleSapFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(MapleSapFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.MAPLE_SAP_BUCKET).block(() -> (LiquidBlock) RankineBlocks.MAPLE_SAP.get())).setRegistryName(ProjectRankine.MODID,"maple_sap"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.MAPLE_SAP, () -> RankineFluids.FLOWING_MAPLE_SAP, FluidAttributes.builder(MapleSapFluid.FLUID_STILL,MapleSapFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(MapleSapFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.MAPLE_SAP_BUCKET).block(() -> (LiquidBlock) RankineBlocks.MAPLE_SAP.get())).setRegistryName(ProjectRankine.MODID,"flowing_maple_sap"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.LATEX, () -> RankineFluids.FLOWING_LATEX, FluidAttributes.builder(LatexFluid.FLUID_STILL,LatexFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(LatexFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.LATEX_BUCKET).block(() -> (LiquidBlock) RankineBlocks.LATEX.get())).setRegistryName(ProjectRankine.MODID,"latex"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.LATEX, () -> RankineFluids.FLOWING_LATEX, FluidAttributes.builder(LatexFluid.FLUID_STILL,LatexFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(LatexFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.LATEX_BUCKET).block(() -> (LiquidBlock) RankineBlocks.LATEX.get())).setRegistryName(ProjectRankine.MODID,"flowing_latex"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.JUGLONE, () -> RankineFluids.FLOWING_JUGLONE, FluidAttributes.builder(JugloneFluid.FLUID_STILL,JugloneFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(JugloneFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.JUGLONE_BUCKET).block(() -> (LiquidBlock) RankineBlocks.JUGLONE.get())).setRegistryName(ProjectRankine.MODID,"juglone"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.JUGLONE, () -> RankineFluids.FLOWING_JUGLONE, FluidAttributes.builder(JugloneFluid.FLUID_STILL,JugloneFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(JugloneFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.JUGLONE_BUCKET).block(() -> (LiquidBlock) RankineBlocks.JUGLONE.get())).setRegistryName(ProjectRankine.MODID,"flowing_juglone"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.RESIN, () -> RankineFluids.FLOWING_RESIN, FluidAttributes.builder(ResinFluid.FLUID_STILL,ResinFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(ResinFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.RESIN_BUCKET).block(() -> (LiquidBlock) RankineBlocks.RESIN.get())).setRegistryName(ProjectRankine.MODID,"resin"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.RESIN, () -> RankineFluids.FLOWING_RESIN, FluidAttributes.builder(ResinFluid.FLUID_STILL,ResinFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(ResinFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.RESIN_BUCKET).block(() -> (LiquidBlock) RankineBlocks.RESIN.get())).setRegistryName(ProjectRankine.MODID,"flowing_resin"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.AQUA_REGIA, () -> RankineFluids.FLOWING_AQUA_REGIA, FluidAttributes.builder(AquaRegiaFluid.FLUID_STILL,AquaRegiaFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(AquaRegiaFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.AQUA_REGIA_BUCKET).block(() -> (LiquidBlock) RankineBlocks.AQUA_REGIA.get())).setRegistryName(ProjectRankine.MODID,"aqua_regia"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.AQUA_REGIA, () -> RankineFluids.FLOWING_AQUA_REGIA, FluidAttributes.builder(AquaRegiaFluid.FLUID_STILL,AquaRegiaFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(AquaRegiaFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.AQUA_REGIA_BUCKET).block(() -> (LiquidBlock) RankineBlocks.AQUA_REGIA.get())).setRegistryName(ProjectRankine.MODID,"flowing_aqua_regia"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.CARBON_DISULFIDE, () -> RankineFluids.FLOWING_CARBON_DISULFIDE, FluidAttributes.builder(CarbonDisulfideFluid.FLUID_STILL,CarbonDisulfideFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(CarbonDisulfideFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY).viscosity(6000))
                    .bucket(RankineItems.CARBON_DISULFIDE_BUCKET).block(() -> (LiquidBlock) RankineBlocks.CARBON_DISULFIDE.get())).setRegistryName(ProjectRankine.MODID,"carbon_disulfide"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.CARBON_DISULFIDE, () -> RankineFluids.FLOWING_CARBON_DISULFIDE, FluidAttributes.builder(CarbonDisulfideFluid.FLUID_STILL,CarbonDisulfideFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(CarbonDisulfideFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY).viscosity(6000))
                    .bucket(RankineItems.CARBON_DISULFIDE_BUCKET).block(() -> (LiquidBlock) RankineBlocks.CARBON_DISULFIDE.get())).setRegistryName(ProjectRankine.MODID,"flowing_carbon_disulfide"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.HEXAFLUOROSILICIC_ACID, () -> RankineFluids.FLOWING_HEXAFLUOROSILICIC_ACID, FluidAttributes.builder(HexafluorosilicicAcidFluid.FLUID_STILL,HexafluorosilicicAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(HexafluorosilicicAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.HEXAFLUOROSILICIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.HEXAFLUOROSILICIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"hexafluorosilicic_acid"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.HEXAFLUOROSILICIC_ACID, () -> RankineFluids.FLOWING_HEXAFLUOROSILICIC_ACID, FluidAttributes.builder(HexafluorosilicicAcidFluid.FLUID_STILL,HexafluorosilicicAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(HexafluorosilicicAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.HEXAFLUOROSILICIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.HEXAFLUOROSILICIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"flowing_hexafluorosilicic_acid"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.HYDROBROMIC_ACID, () -> RankineFluids.FLOWING_HYDROBROMIC_ACID, FluidAttributes.builder(HydrobromicAcidFluid.FLUID_STILL,HydrobromicAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(HydrobromicAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.HYDROBROMIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.HYDROBROMIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"hydrobromic_acid"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.HYDROBROMIC_ACID, () -> RankineFluids.FLOWING_HYDROBROMIC_ACID, FluidAttributes.builder(HydrobromicAcidFluid.FLUID_STILL,HydrobromicAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(HydrobromicAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.HYDROBROMIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.HYDROBROMIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"flowing_hydrobromic_acid"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.GRAY_MUD, () -> RankineFluids.FLOWING_GRAY_MUD, FluidAttributes.builder(GrayMudFluid.FLUID_STILL,GrayMudFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(GrayMudFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.GRAY_MUD_BUCKET).block(() -> (LiquidBlock) RankineBlocks.GRAY_MUD.get())).setRegistryName(ProjectRankine.MODID,"gray_mud"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.GRAY_MUD, () -> RankineFluids.FLOWING_GRAY_MUD, FluidAttributes.builder(GrayMudFluid.FLUID_STILL,GrayMudFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(GrayMudFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.GRAY_MUD_BUCKET).block(() -> (LiquidBlock) RankineBlocks.GRAY_MUD.get())).setRegistryName(ProjectRankine.MODID,"flowing_gray_mud"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.RED_MUD, () -> RankineFluids.FLOWING_RED_MUD, FluidAttributes.builder(RedMudFluid.FLUID_STILL,RedMudFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(RedMudFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.RED_MUD_BUCKET).block(() -> (LiquidBlock) RankineBlocks.RED_MUD.get())).setRegistryName(ProjectRankine.MODID,"red_mud"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.RED_MUD, () -> RankineFluids.FLOWING_RED_MUD, FluidAttributes.builder(RedMudFluid.FLUID_STILL,RedMudFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(RedMudFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.RED_MUD_BUCKET).block(() -> (LiquidBlock) RankineBlocks.RED_MUD.get())).setRegistryName(ProjectRankine.MODID,"flowing_red_mud"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.SULFURIC_ACID, () -> RankineFluids.FLOWING_SULFURIC_ACID, FluidAttributes.builder(SulfuricAcidFluid.FLUID_STILL,SulfuricAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(SulfuricAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.SULFURIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.SULFURIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"sulfuric_acid"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.SULFURIC_ACID, () -> RankineFluids.FLOWING_SULFURIC_ACID, FluidAttributes.builder(SulfuricAcidFluid.FLUID_STILL,SulfuricAcidFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(SulfuricAcidFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.SULFURIC_ACID_BUCKET).block(() -> (LiquidBlock) RankineBlocks.SULFURIC_ACID.get())).setRegistryName(ProjectRankine.MODID,"flowing_sulfuric_acid"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.BLACK_LIQUOR, () -> RankineFluids.FLOWING_BLACK_LIQUOR, FluidAttributes.builder(BlackLiquorFluid.FLUID_STILL,BlackLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(BlackLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.BLACK_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.BLACK_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"black_liquor"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.BLACK_LIQUOR, () -> RankineFluids.FLOWING_BLACK_LIQUOR, FluidAttributes.builder(BlackLiquorFluid.FLUID_STILL,BlackLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(BlackLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.BLACK_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.BLACK_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"flowing_black_liquor"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.GREEN_LIQUOR, () -> RankineFluids.FLOWING_GREEN_LIQUOR, FluidAttributes.builder(GreenLiquorFluid.FLUID_STILL,GreenLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(GreenLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.GREEN_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.GREEN_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"green_liquor"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.GREEN_LIQUOR, () -> RankineFluids.FLOWING_GREEN_LIQUOR, FluidAttributes.builder(GreenLiquorFluid.FLUID_STILL,GreenLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(GreenLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.GREEN_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.GREEN_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"flowing_green_liquor"));

            event.getRegistry().register(new ForgeFlowingFluid.Source(new ForgeFlowingFluid.Properties(() -> RankineFluids.WHITE_LIQUOR, () -> RankineFluids.FLOWING_WHITE_LIQUOR, FluidAttributes.builder(WhiteLiquorFluid.FLUID_STILL,WhiteLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(WhiteLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.WHITE_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.WHITE_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"white_liquor"));
            event.getRegistry().register(new ForgeFlowingFluid.Flowing(new ForgeFlowingFluid.Properties(() -> RankineFluids.WHITE_LIQUOR, () -> RankineFluids.FLOWING_WHITE_LIQUOR, FluidAttributes.builder(WhiteLiquorFluid.FLUID_STILL,WhiteLiquorFluid.FLUID_FLOWING).color(0xFFFFFFFF).overlay(WhiteLiquorFluid.OVERLAY).sound(SoundEvents.BUCKET_FILL,SoundEvents.BUCKET_EMPTY))
                    .bucket(RankineItems.WHITE_LIQUOR_BUCKET).block(() -> (LiquidBlock) RankineBlocks.WHITE_LIQUOR.get())).setRegistryName(ProjectRankine.MODID,"flowing_white_liquor"));
        }

        @SubscribeEvent
        public static void registerItemRemappings(final RegistryEvent.MissingMappings<Item> event) {
            Map<ResourceLocation, Item> itemRemappings = RankineRemappings.getItemRemappings();
            ImmutableList<RegistryEvent.MissingMappings.Mapping<Item>> mappings = event.getAllMappings();
            for (RegistryEvent.MissingMappings.Mapping<Item> map : mappings) {
                if (itemRemappings.containsKey(map.key)) {
                    map.remap(itemRemappings.get(map.key));
                }
            }

            Map<ResourceLocation, Block> blockRemappings = RankineRemappings.getBlockRemappings();
            for (RegistryEvent.MissingMappings.Mapping<Item> map : mappings) {
                if (blockRemappings.containsKey(map.key)) {
                    map.remap(blockRemappings.get(map.key).asItem());
                }
            }
        }

        @SubscribeEvent
        public static void registerBlockRemappings(final RegistryEvent.MissingMappings<Block> event) {
            Map<ResourceLocation, Block> blockRemappings = RankineRemappings.getBlockRemappings();
            ImmutableList<RegistryEvent.MissingMappings.Mapping<Block>> mappings = event.getAllMappings();
            for (RegistryEvent.MissingMappings.Mapping<Block> map : mappings) {
                if (blockRemappings.containsKey(map.key)) {
                    map.remap(blockRemappings.get(map.key));
                }
            }
        }


        @SubscribeEvent
        public static void registerEnchantments(final RegistryEvent.Register<Enchantment> event) {
            final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
            final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

            event.getRegistry().register(new PunctureEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"puncture"));
            event.getRegistry().register(new SwingEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"swing"));
            event.getRegistry().register(new DazeEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"daze"));
            event.getRegistry().register(new AtomizeEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"atomize"));
            event.getRegistry().register(new ExcavateEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"excavate"));
            event.getRegistry().register(new ImpactEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"impact"));
            event.getRegistry().register(new QuakeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"quake"));
            event.getRegistry().register(new ForagingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"foraging"));
            event.getRegistry().register(new LightningAspectEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"lightning_aspect"));

            event.getRegistry().register(new DuneWalkerEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET).setRegistryName(ProjectRankine.MODID,"dune_walker"));
            event.getRegistry().register(new SnowDrifterEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET).setRegistryName(ProjectRankine.MODID,"snow_drifter"));
            event.getRegistry().register(new SpeedSkaterEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET).setRegistryName(ProjectRankine.MODID,"speed_skater"));
            event.getRegistry().register(new FlippersEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET).setRegistryName(ProjectRankine.MODID,"flippers"));
            event.getRegistry().register(new GasProtectionEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.FEET).setRegistryName(ProjectRankine.MODID,"gas_protection"));
            event.getRegistry().register(new AntiquatedEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"antiquated"));
            event.getRegistry().register(new CleanseEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"cleanse"));


            event.getRegistry().register(new EndobioticEnchantment(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS).setRegistryName(ProjectRankine.MODID,"endobiotic"));
            event.getRegistry().register(new EndotoxinEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endotoxin"));
            event.getRegistry().register(new EndpointEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endpoint"));
            event.getRegistry().register(new EndosporeEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endospore"));
            event.getRegistry().register(new EndureEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endure"));
            event.getRegistry().register(new EndgameEnchantment(Enchantment.Rarity.VERY_RARE, HAND_SLOTS).setRegistryName(ProjectRankine.MODID,"endgame"));
            event.getRegistry().register(new EndolithicEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endolithic"));
            event.getRegistry().register(new EndlessEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endless"));
            event.getRegistry().register(new EndeavorEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endeavor"));
            event.getRegistry().register(new EndothermicEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endothermic"));
            event.getRegistry().register(new EndplayEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"endplay"));

            event.getRegistry().register(new GhastRegenerationEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"ghast_regeneration"));
            event.getRegistry().register(new WitheringCurseEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"withering_curse"));
            event.getRegistry().register(new ShapeMemoryEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"shape_memory"));
            event.getRegistry().register(new GuardEnchantment(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS).setRegistryName(ProjectRankine.MODID,"guard"));


            event.getRegistry().register(new PreparationEnchantment(Enchantment.Rarity.COMMON, HAND_SLOTS).setRegistryName(ProjectRankine.MODID,"preparation"));
            event.getRegistry().register(new PoisonAspectEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"poison_aspect"));
            event.getRegistry().register(new BackstabEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"backstab"));
            event.getRegistry().register(new GraftingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"grafting"));
            event.getRegistry().register(new RetaliateEnchantment(Enchantment.Rarity.VERY_RARE, HAND_SLOTS).setRegistryName(ProjectRankine.MODID,"retaliate"));
            event.getRegistry().register(new RetreatEnchantment(Enchantment.Rarity.VERY_RARE, HAND_SLOTS).setRegistryName(ProjectRankine.MODID,"retreat"));


            event.getRegistry().register(new LeverageEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"leverage"));
            event.getRegistry().register(new PryingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"prying"));
            event.getRegistry().register(new LiftEnchantment(Enchantment.Rarity.VERY_RARE, HAND_SLOTS).setRegistryName(ProjectRankine.MODID,"lift"));
            event.getRegistry().register(new RetrievalEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"retrieval"));

            event.getRegistry().register(new AccuracyEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND).setRegistryName(ProjectRankine.MODID,"accuracy"));

        }

    }


}

