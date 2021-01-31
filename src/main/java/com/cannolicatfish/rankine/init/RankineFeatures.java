package com.cannolicatfish.rankine.init;

import com.cannolicatfish.rankine.Config;
import com.cannolicatfish.rankine.blocks.RankineBerryBushBlock;
import com.cannolicatfish.rankine.world.gen.feature.*;
import com.cannolicatfish.rankine.world.gen.placement.IntrusionPlacement;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.*;

import java.util.OptionalInt;

public class RankineFeatures {

    // FEATURE CONFIGS
    public static final BaseTreeFeatureConfig CEDAR_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.CEDAR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.CEDAR_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(7, 4, 1),
            new TwoLayerFeature(2, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig BALSAM_FIR_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(1, 0), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(8, 5, 0),
            new TwoLayerFeature(2, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig TALL_BALSAM_FIR_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(10, 4, 0),
            new TwoLayerFeature(2, 0, 0)))
            .setDecorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState()))))
            .build();

    public static final BaseTreeFeatureConfig LARGE_BALSAM_FIR_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LEAVES.get().getDefaultState()),
            new MegaPineFoliagePlacer(FeatureSpread.func_242253_a(0, 0), FeatureSpread.func_242253_a(0, 0), FeatureSpread.func_242253_a(3, 6)),
            new GiantTrunkPlacer(9, 4, 12),
            new TwoLayerFeature(1, 1, 2)))
            .setDecorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(Blocks.PODZOL.getDefaultState()))))
            .build();

    public static final BaseTreeFeatureConfig EASTERN_HEMLOCK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(4, 4, 1),
            new TwoLayerFeature(2, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig SMALL_EASTERN_HEMLOCK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LEAVES.get().getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 0), FeatureSpread.func_242253_a(0, 0), FeatureSpread.func_242253_a(0, 0)),
            new StraightTrunkPlacer(2, 1, 0),
            new TwoLayerFeature(2, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig LARGE_EASTERN_HEMLOCK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.EASTERN_HEMLOCK_LEAVES.get().getDefaultState()),
            new MegaPineFoliagePlacer(FeatureSpread.func_242253_a(0, 0), FeatureSpread.func_242253_a(0, 0), FeatureSpread.func_242253_a(5, 8)),
            new GiantTrunkPlacer(8, 2, 6),
            new TwoLayerFeature(1, 1, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig MAGNOLIA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.MAGNOLIA_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.MAGNOLIA_LEAVES.get().getDefaultState()),
            new AcaciaFoliagePlacer(FeatureSpread.func_242253_a(1, 0), FeatureSpread.func_242253_a(0, 0)),
            new ForkyTrunkPlacer(3, 2, 2),
            new TwoLayerFeature(1, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig PINYON_PINE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.PINYON_PINE_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.PINYON_PINE_LEAVES.get().getDefaultState()),
            new DarkOakFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0)),
            new DarkOakTrunkPlacer(5, 2, 2),
            new ThreeLayerFeature(1, 2, 1, 1, 2, OptionalInt.empty())))
            .func_236702_a_(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig JUNIPER_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.JUNIPER_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.JUNIPER_LEAVES.get().getDefaultState()),
            new AcaciaFoliagePlacer(FeatureSpread.func_242253_a(1, 0), FeatureSpread.func_242253_a(1, 0)),
            new ForkyTrunkPlacer(1, 1, 1),
            new TwoLayerFeature(1, 1, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig COCONUT_PALM_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.COCONUT_PALM_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.COCONUT_PALM_LEAVES.get().getDefaultState()),
            new AcaciaFoliagePlacer(FeatureSpread.func_242253_a(1, 0), FeatureSpread.func_242253_a(0, 0)),
            new ForkyTrunkPlacer(10, 2, 2),
            new TwoLayerFeature(1, 0, 2)))
            .setIgnoreVines()
            .build();


    //OTHER TREE CONFIGS
    public static final BaseTreeFeatureConfig DEAD_BALSAM_FIR_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.BALSAM_FIR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
            new SpruceFoliagePlacer(FeatureSpread.func_242253_a(1, 0), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
            new StraightTrunkPlacer(5, 2, 1),
            new TwoLayerFeature(2, 0, 2)))
            .setIgnoreVines()
            .build();

    public static final BaseTreeFeatureConfig MAPLE_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.MAPLE_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.MAPLE_LEAVES.get().getDefaultState()),
            new FancyFoliagePlacer(FeatureSpread.func_242253_a(3, 0), FeatureSpread.func_242253_a(4, 0), 4),
            new FancyTrunkPlacer(6, 11, 0),
            new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))))
            .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
            .build();

    public static final BaseTreeFeatureConfig YELLOW_BIRCH_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.YELLOW_BIRCH_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.YELLOW_BIRCH_LEAVES.get().getDefaultState()),
            new FancyFoliagePlacer(FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242253_a(4, 0), 4),
            new FancyTrunkPlacer(6, 3, 5),
            new TwoLayerFeature(2, 0, 0, OptionalInt.of(4))))
            .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
            .build();

    public static final BaseTreeFeatureConfig BLACK_BIRCH_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(RankineBlocks.BLACK_BIRCH_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(RankineBlocks.BLACK_BIRCH_LEAVES.get().getDefaultState()),
            new FancyFoliagePlacer(FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242253_a(4, 0), 4),
            new FancyTrunkPlacer(6, 3, 5),
            new TwoLayerFeature(2, 0, 0, OptionalInt.of(4))))
            .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
            .build();

    public static final BlockClusterFeatureConfig ELDERBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.ELDERBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig SNOWBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.SNOWBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLUEBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.BLUEBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig RASPBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.RASPBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BLACKBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.BLACKBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig CRANBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.CRANBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig STRAWBERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.STRAWBERRY_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig PINEAPPLE_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.PINEAPPLE_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.RED_SAND)).func_227317_b_().build();
    public static final BlockClusterFeatureConfig BANANA_YUCCA_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RankineBlocks.BANANA_YUCCA_BUSH.get().getDefaultState().with(RankineBerryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.RED_SAND)).func_227317_b_().build();


    // LOCAL_MODIFICATIONS
    public static final ConfiguredFeature<?, ?> METEORITE = new MeteoriteFeature(MeteoriteFeatureConfig.CODEC).withConfiguration(new MeteoriteFeatureConfig(RankineBlocks.METEORITE.get().getDefaultState(), 1))
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(Config.METEORITE_CHANCE.get())));
    public static final ConfiguredFeature<?, ?> VOLCANO = new VolcanoFeature(NoFeatureConfig.field_236558_a_).withConfiguration(new NoFeatureConfig())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30)));
    public static final ConfiguredFeature<?, ?> FIRE_CLAY = new FireClayDiskFeature(NoFeatureConfig.field_236558_a_).withConfiguration(new NoFeatureConfig())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));


    // VEGETAL_DECORATION
    public static final ConfiguredFeature<?, ?> ELDERBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(ELDERBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> SNOWBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(SNOWBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> BLUEBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(BLUEBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> RASPBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(RASPBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> BLACKBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(BLACKBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> CRANBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(CRANBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> STRAWBERRY_BUSH = Feature.RANDOM_PATCH.withConfiguration(STRAWBERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> PINEAPPLE_BUSH = Feature.RANDOM_PATCH.withConfiguration(PINEAPPLE_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> BANANA_YUCCA_BUSH = Feature.RANDOM_PATCH.withConfiguration(BANANA_YUCCA_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);


    public static final ConfiguredFeature<?, ?> YELLOW_BIRCH_TREE = Feature.TREE.withConfiguration(YELLOW_BIRCH_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> BLACK_BIRCH_TREE = Feature.TREE.withConfiguration(BLACK_BIRCH_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> EASTERN_HEMLOCK_TREE = Feature.TREE.withConfiguration(LARGE_EASTERN_HEMLOCK_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(1)));
    public static final ConfiguredFeature<?, ?> CEDAR_TREE = Feature.TREE.withConfiguration(CEDAR_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> COCONUT_PALM_TREE = Feature.TREE.withConfiguration(COCONUT_PALM_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> PINYON_PINE_TREE = Feature.TREE.withConfiguration(PINYON_PINE_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> BALSAM_FIR_TREE = Feature.TREE.withConfiguration(BALSAM_FIR_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> DEAD_BALSAM_FIR_TREE = Feature.TREE.withConfiguration(DEAD_BALSAM_FIR_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(1)));
    public static final ConfiguredFeature<?, ?> MAGNOLIA_TREE = Feature.TREE.withConfiguration(MAGNOLIA_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(3)));
    public static final ConfiguredFeature<?, ?> JUNIPER_TREE = Feature.TREE.withConfiguration(JUNIPER_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> MAPLE_TREE = Feature.TREE.withConfiguration(MAPLE_TREE_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.CHANCE.configure(new ChanceConfig(9)));

    // UNDERGROUND_ORES
    public static final Feature<RankineOreFeatureConfig> RANKINE_ORE = new RankineOreFeature(RankineOreFeatureConfig.CODEC);
    public static final Feature<RankineMultiOreFeatureConfig> MULTI_RANKINE_ORE = new RankineMultiOreFeature(RankineMultiOreFeatureConfig.CODEC);

    public static final ConfiguredFeature<?, ?> FLAT_BEDROCK = new FlatBedrockFeature(ReplacerFeatureConfig.CODEC).withConfiguration(
            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), Blocks.BEDROCK.getDefaultState(), 0, Config.BEDROCK_LAYERS.get())).withPlacement(new ReplacerPlacement(NoPlacementConfig.CODEC).configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
    public static final ConfiguredFeature<?, ?> FLAT_BEDROCK_NETHER = new FlatBedrockFeature(ReplacerFeatureConfig.CODEC).withConfiguration(
            new ReplacerFeatureConfig(Blocks.NETHERRACK.getDefaultState(), Blocks.BEDROCK.getDefaultState(), 0, Config.BEDROCK_LAYERS.get())).withPlacement(new ReplacerPlacement(NoPlacementConfig.CODEC).configure(IPlacementConfig.NO_PLACEMENT_CONFIG));

    public static final ConfiguredFeature<?, ?> GRAVEL_DISKS = Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), FeatureSpread.func_242253_a(2, 5), 2,
            Lists.newArrayList(Blocks.DIRT.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT);
    public static final ConfiguredFeature<?, ?> SAND_DISKS = Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.SAND.getDefaultState(), FeatureSpread.func_242253_a(2, 3), 2,
            Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT);
    public static final ConfiguredFeature<?, ?> CLAY_DISKS = Feature.DISK.withConfiguration(new SphereReplaceConfig(Blocks.CLAY.getDefaultState(), FeatureSpread.func_242253_a(2, 2), 1,
            Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT);
    public static final ConfiguredFeature<?, ?> ORE_ALLUVIUM = Feature.DISK.withConfiguration(new SphereReplaceConfig(RankineBlocks.ALLUVIUM.get().getDefaultState(), FeatureSpread.func_242253_a(1, 2), 1,
            Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT);
    public static final ConfiguredFeature<?, ?> ORE_EVAPORITE = Feature.DISK.withConfiguration(new SphereReplaceConfig(RankineBlocks.EVAPORITE.get().getDefaultState(), FeatureSpread.func_242253_a(1, 1), 1,
            Lists.newArrayList(Blocks.DIRT.getDefaultState(), Blocks.CLAY.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState()))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT);

    public static final ConfiguredFeature<?, ?> ORE_INTRUSION = new IntrusionFeature(ReplacerFeatureConfig.CODEC).withConfiguration(
            new ReplacerFeatureConfig(Blocks.STONE.getDefaultState(), Blocks.AIR.getDefaultState(), 1, 256)).withPlacement(new IntrusionPlacement(ChanceConfig.CODEC).configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> NETHER_ORE_INTRUSION = new NetherIntrusionFeature(ReplacerFeatureConfig.CODEC).withConfiguration(
            new ReplacerFeatureConfig(Blocks.NETHERRACK.getDefaultState(), Blocks.AIR.getDefaultState(), 1, 256)).withPlacement(new IntrusionPlacement(ChanceConfig.CODEC).configure(new ChanceConfig(2)));
    public static final ConfiguredFeature<?, ?> DEFAULT_STONE_GEN = new StoneReplacerFeature(StoneReplacerFeatureConfig.CODEC).withConfiguration(
            new StoneReplacerFeatureConfig(Blocks.STONE.getDefaultState(), Blocks.AIR.getDefaultState(), 0, 0)).withPlacement(new ReplacerPlacement(NoPlacementConfig.CODEC).configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
    public static final ConfiguredFeature<?, ?> ANDESITIC_TUFF = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.HORNBLENDE_ANDESITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.ANDESITIC_TUFF.get().getDefaultState(), 80))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(60, 60, 128))).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> RHYOLITIC_TUFF = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.RHYOLITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.RHYOLITIC_TUFF.get().getDefaultState(), 80))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60))).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> THOLEIITIC_BASALTIC_TUFF = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.THOLEIITIC_BASALT.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.BASALTIC_TUFF.get().getDefaultState(), 80))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60))).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> DACITIC_TUFF = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.DACITES, RankineBlocks.DACITIC_TUFF.get().getStateContainer().getBaseState(), 60))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 60))).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> ANDESITE_VAR = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.HORNBLENDE_ANDESITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Blocks.ANDESITE.getDefaultState(), 120))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(64, 64, 150))).square().func_242731_b(2);
    public static final ConfiguredFeature<?, ?> ORE_PHOSPHORITE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SEDIMENTARY, RankineBlocks.PHOSPHORITE.get().getStateContainer().getBaseState(), 20))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(40, 40, 90))).square().func_242731_b(2);
    public static final ConfiguredFeature<?, ?> ORE_IRONSTONE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SANDSTONES, RankineBlocks.IRONSTONE.get().getStateContainer().getBaseState(), 30))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(40, 40, 90))).square().func_242731_b(1);
    public static final ConfiguredFeature<?, ?> ORE_NODULE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.TUFA_LIMESTONE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.LIMESTONE_NODULE.get().getDefaultState(), 6))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(31, 31, 90))).square().func_242731_b(20);
    public static final ConfiguredFeature<?, ?> ORE_CHALK = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SEDIMENTARY, RankineBlocks.CHALK.get().getStateContainer().getBaseState(), 80))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(40, 40, 80))).square().func_242731_b(3);
    public static final ConfiguredFeature<?, ?> END_STONE_GEN = new EndStoneReplacerFeature(StoneReplacerFeatureConfig.CODEC).withConfiguration(
            new StoneReplacerFeatureConfig(Blocks.END_STONE.getDefaultState(), Blocks.AIR.getDefaultState(), 0, 0)).withPlacement(new ReplacerPlacement(NoPlacementConfig.CODEC).configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
    public static final ConfiguredFeature<?, ?> NETHER_STONE_GEN = new NetherStoneReplacerFeature(StoneReplacerFeatureConfig.CODEC).withConfiguration(
            new StoneReplacerFeatureConfig(Blocks.NETHERRACK.getDefaultState(), Blocks.AIR.getDefaultState(), 0, 0)).withPlacement(new ReplacerPlacement(NoPlacementConfig.CODEC).configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
    public static final ConfiguredFeature<?, ?> END_TAENITE_ORE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.METEORITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.TAENITE.get().getDefaultState(), 10))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 40))).square().func_242731_b(6);
    public static final ConfiguredFeature<?, ?> END_ANTITAENITE_ORE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.METEORITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.ANTITAENITE.get().getDefaultState(), 10))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 40))).square().func_242731_b(6);
    public static final ConfiguredFeature<?, ?> END_KAMACITE_ORE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.METEORITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.KAMACITE.get().getDefaultState(), 10))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 40))).square().func_242731_b(6);
    public static final ConfiguredFeature<?, ?> END_TETRATAENITE_ORE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.METEORITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.TETRATAENITE.get().getDefaultState(), 10))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 40))).square().func_242731_b(6);
    public static final ConfiguredFeature<?, ?> END_LONSDALEITE_ORE = new ModularOreFeature(OreFeatureConfig.CODEC, RankineBlocks.METEORITE.get().getDefaultState()).withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, RankineBlocks.LONSDALEITE.get().getDefaultState(), 6))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 0, 40))).square().func_242731_b(5);


    //ORES
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_COPPER_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_COPPER, RankineBlocks.NATIVE_COPPER_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_COPPER_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_COPPER_ORE_MIN_HEIGHT.get(), Config.NATIVE_COPPER_ORE_MIN_HEIGHT.get(), Config.NATIVE_COPPER_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_COPPER_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_COPPER_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_COPPER, RankineBlocks.NATIVE_COPPER_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_COPPER_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_COPPER_ORE_MIN_HEIGHT.get(), Config.NATIVE_COPPER_ORE_MIN_HEIGHT.get(), Config.NATIVE_COPPER_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_COPPER_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_TIN_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_TIN, RankineBlocks.NATIVE_TIN_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_TIN_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_TIN_ORE_MIN_HEIGHT.get(), Config.NATIVE_TIN_ORE_MIN_HEIGHT.get(), Config.NATIVE_TIN_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_TIN_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_TIN_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_TIN, RankineBlocks.NATIVE_TIN_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_TIN_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_TIN_ORE_MIN_HEIGHT.get(), Config.NATIVE_TIN_ORE_MIN_HEIGHT.get(), Config.NATIVE_TIN_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_TIN_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_GOLD_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_GOLD, RankineBlocks.NATIVE_GOLD_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_GOLD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_GOLD_ORE_MIN_HEIGHT.get(), Config.NATIVE_GOLD_ORE_MIN_HEIGHT.get(), Config.NATIVE_GOLD_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_GOLD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_GOLD_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_GOLD, RankineBlocks.NATIVE_GOLD_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_GOLD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_GOLD_ORE_MIN_HEIGHT.get(), Config.NATIVE_GOLD_ORE_MIN_HEIGHT.get(), Config.NATIVE_GOLD_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_GOLD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_ALUMINUM_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_ALUMINUM, RankineBlocks.NATIVE_ALUMINUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_ALUMINUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_ALUMINUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_ALUMINUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_ALUMINUM_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_ALUMINUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_ALUMINUM_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_ALUMINUM, RankineBlocks.NATIVE_ALUMINUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_ALUMINUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_ALUMINUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_ALUMINUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_ALUMINUM_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_ALUMINUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_LEAD_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_LEAD, RankineBlocks.NATIVE_LEAD_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_LEAD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_LEAD_ORE_MIN_HEIGHT.get(), Config.NATIVE_LEAD_ORE_MIN_HEIGHT.get(), Config.NATIVE_LEAD_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_LEAD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_LEAD_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_LEAD, RankineBlocks.NATIVE_LEAD_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_LEAD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_LEAD_ORE_MIN_HEIGHT.get(), Config.NATIVE_LEAD_ORE_MIN_HEIGHT.get(), Config.NATIVE_LEAD_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_LEAD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SILVER_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SILVER, RankineBlocks.NATIVE_SILVER_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SILVER_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SILVER_ORE_MIN_HEIGHT.get(), Config.NATIVE_SILVER_ORE_MIN_HEIGHT.get(), Config.NATIVE_SILVER_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_SILVER_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SILVER_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SILVER, RankineBlocks.NATIVE_SILVER_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SILVER_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SILVER_ORE_MIN_HEIGHT.get(), Config.NATIVE_SILVER_ORE_MIN_HEIGHT.get(), Config.NATIVE_SILVER_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_SILVER_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_ARSENIC_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_ARSENIC, RankineBlocks.NATIVE_ARSENIC_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_ARSENIC_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_ARSENIC_ORE_MIN_HEIGHT.get(), Config.NATIVE_ARSENIC_ORE_MIN_HEIGHT.get(), Config.NATIVE_ARSENIC_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_ARSENIC_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_ARSENIC_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_ARSENIC, RankineBlocks.NATIVE_ARSENIC_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_ARSENIC_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_ARSENIC_ORE_MIN_HEIGHT.get(), Config.NATIVE_ARSENIC_ORE_MIN_HEIGHT.get(), Config.NATIVE_ARSENIC_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_ARSENIC_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_BISMUTH_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_BISMUTH, RankineBlocks.NATIVE_BISMUTH_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_BISMUTH_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_BISMUTH_ORE_MIN_HEIGHT.get(), Config.NATIVE_BISMUTH_ORE_MIN_HEIGHT.get(), Config.NATIVE_BISMUTH_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_BISMUTH_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_BISMUTH_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_BISMUTH, RankineBlocks.NATIVE_BISMUTH_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_BISMUTH_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_BISMUTH_ORE_MIN_HEIGHT.get(), Config.NATIVE_BISMUTH_ORE_MIN_HEIGHT.get(), Config.NATIVE_BISMUTH_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_BISMUTH_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SULFUR_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SULFUR, RankineBlocks.NATIVE_SULFUR_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SULFUR_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SULFUR_ORE_MIN_HEIGHT.get(), Config.NATIVE_SULFUR_ORE_MIN_HEIGHT.get(), Config.NATIVE_SULFUR_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_SULFUR_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SULFUR_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SULFUR, RankineBlocks.NATIVE_SULFUR_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SULFUR_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SULFUR_ORE_MIN_HEIGHT.get(), Config.NATIVE_SULFUR_ORE_MIN_HEIGHT.get(), Config.NATIVE_SULFUR_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_SULFUR_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_GALLIUM_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_GALLIUM, RankineBlocks.NATIVE_GALLIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_GALLIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_GALLIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_GALLIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_GALLIUM_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_GALLIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_GALLIUM_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_GALLIUM, RankineBlocks.NATIVE_GALLIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_GALLIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_GALLIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_GALLIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_GALLIUM_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_GALLIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_INDIUM_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_INDIUM, RankineBlocks.NATIVE_INDIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_INDIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_INDIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_INDIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_INDIUM_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_INDIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_INDIUM_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_INDIUM, RankineBlocks.NATIVE_INDIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_INDIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_INDIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_INDIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_INDIUM_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_INDIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_TELLURIUM_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_TELLURIUM, RankineBlocks.NATIVE_TELLURIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_TELLURIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_TELLURIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_TELLURIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_TELLURIUM_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_TELLURIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_TELLURIUM_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_TELLURIUM, RankineBlocks.NATIVE_TELLURIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_TELLURIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_TELLURIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_TELLURIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_TELLURIUM_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_TELLURIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SELENIUM_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SELENIUM, RankineBlocks.NATIVE_SELENIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SELENIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SELENIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_SELENIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_SELENIUM_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.NATIVE_SELENIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_NATIVE_SELENIUM_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.NATIVE_SELENIUM, RankineBlocks.NATIVE_SELENIUM_ORE.get().getStateContainer().getBaseState(), Config.NATIVE_SELENIUM_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.NATIVE_SELENIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_SELENIUM_ORE_MIN_HEIGHT.get(), Config.NATIVE_SELENIUM_ORE_MAX_HEIGHT.get()))).square().chance(Config.NATIVE_SELENIUM_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MALACHITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MALACHITE, RankineBlocks.MALACHITE_ORE.get().getStateContainer().getBaseState(), Config.MALACHITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MALACHITE_ORE_MIN_HEIGHT.get(), Config.MALACHITE_ORE_MIN_HEIGHT.get(), Config.MALACHITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MALACHITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MALACHITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MALACHITE, RankineBlocks.MALACHITE_ORE.get().getStateContainer().getBaseState(), Config.MALACHITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MALACHITE_ORE_MIN_HEIGHT.get(), Config.MALACHITE_ORE_MIN_HEIGHT.get(), Config.MALACHITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MALACHITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CASSITERITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CASSITERITE, RankineBlocks.CASSITERITE_ORE.get().getStateContainer().getBaseState(), Config.CASSITERITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CASSITERITE_ORE_MIN_HEIGHT.get(), Config.CASSITERITE_ORE_MIN_HEIGHT.get(), Config.CASSITERITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.CASSITERITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CASSITERITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CASSITERITE, RankineBlocks.CASSITERITE_ORE.get().getStateContainer().getBaseState(), Config.CASSITERITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CASSITERITE_ORE_MIN_HEIGHT.get(), Config.CASSITERITE_ORE_MIN_HEIGHT.get(), Config.CASSITERITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.CASSITERITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BAUXITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BAUXITE, RankineBlocks.BAUXITE_ORE.get().getStateContainer().getBaseState(), Config.BAUXITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BAUXITE_ORE_MIN_HEIGHT.get(), Config.BAUXITE_ORE_MIN_HEIGHT.get(), Config.BAUXITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.BAUXITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BAUXITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BAUXITE, RankineBlocks.BAUXITE_ORE.get().getStateContainer().getBaseState(), Config.BAUXITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BAUXITE_ORE_MIN_HEIGHT.get(), Config.BAUXITE_ORE_MIN_HEIGHT.get(), Config.BAUXITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.BAUXITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SPHALERITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SPHALERITE, RankineBlocks.SPHALERITE_ORE.get().getStateContainer().getBaseState(), Config.SPHALERITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SPHALERITE_ORE_MIN_HEIGHT.get(), Config.SPHALERITE_ORE_MIN_HEIGHT.get(), Config.SPHALERITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.SPHALERITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SPHALERITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SPHALERITE, RankineBlocks.SPHALERITE_ORE.get().getStateContainer().getBaseState(), Config.SPHALERITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SPHALERITE_ORE_MIN_HEIGHT.get(), Config.SPHALERITE_ORE_MIN_HEIGHT.get(), Config.SPHALERITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.SPHALERITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CINNABAR_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CINNABAR, RankineBlocks.CINNABAR_ORE.get().getStateContainer().getBaseState(), Config.CINNABAR_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CINNABAR_ORE_MIN_HEIGHT.get(), Config.CINNABAR_ORE_MIN_HEIGHT.get(), Config.CINNABAR_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.CINNABAR_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CINNABAR_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CINNABAR, RankineBlocks.CINNABAR_ORE.get().getStateContainer().getBaseState(), Config.CINNABAR_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CINNABAR_ORE_MIN_HEIGHT.get(), Config.CINNABAR_ORE_MIN_HEIGHT.get(), Config.CINNABAR_ORE_MAX_HEIGHT.get()))).square().chance(Config.CINNABAR_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAGNETITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAGNETITE, RankineBlocks.MAGNETITE_ORE.get().getStateContainer().getBaseState(), Config.MAGNETITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAGNETITE_ORE_MIN_HEIGHT.get(), Config.MAGNETITE_ORE_MIN_HEIGHT.get(), Config.MAGNETITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MAGNETITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAGNETITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAGNETITE, RankineBlocks.MAGNETITE_ORE.get().getStateContainer().getBaseState(), Config.MAGNETITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAGNETITE_ORE_MIN_HEIGHT.get(), Config.MAGNETITE_ORE_MIN_HEIGHT.get(), Config.MAGNETITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MAGNETITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PENTLANDITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PENTLANDITE, RankineBlocks.PENTLANDITE_ORE.get().getStateContainer().getBaseState(), Config.PENTLANDITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PENTLANDITE_ORE_MIN_HEIGHT.get(), Config.PENTLANDITE_ORE_MIN_HEIGHT.get(), Config.PENTLANDITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PENTLANDITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PENTLANDITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PENTLANDITE, RankineBlocks.PENTLANDITE_ORE.get().getStateContainer().getBaseState(), Config.PENTLANDITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PENTLANDITE_ORE_MIN_HEIGHT.get(), Config.PENTLANDITE_ORE_MIN_HEIGHT.get(), Config.PENTLANDITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.PENTLANDITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAGNESITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAGNESITE, RankineBlocks.MAGNESITE_ORE.get().getStateContainer().getBaseState(), Config.MAGNESITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAGNESITE_ORE_MIN_HEIGHT.get(), Config.MAGNESITE_ORE_MIN_HEIGHT.get(), Config.MAGNESITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MAGNESITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAGNESITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAGNESITE, RankineBlocks.MAGNESITE_ORE.get().getStateContainer().getBaseState(), Config.MAGNESITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAGNESITE_ORE_MIN_HEIGHT.get(), Config.MAGNESITE_ORE_MIN_HEIGHT.get(), Config.MAGNESITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MAGNESITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_GALENA_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.GALENA, RankineBlocks.GALENA_ORE.get().getStateContainer().getBaseState(), Config.GALENA_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.GALENA_ORE_MIN_HEIGHT.get(), Config.GALENA_ORE_MIN_HEIGHT.get(), Config.GALENA_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.GALENA_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_GALENA_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.GALENA, RankineBlocks.GALENA_ORE.get().getStateContainer().getBaseState(), Config.GALENA_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.GALENA_ORE_MIN_HEIGHT.get(), Config.GALENA_ORE_MIN_HEIGHT.get(), Config.GALENA_ORE_MAX_HEIGHT.get()))).square().chance(Config.GALENA_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_VANADINITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.VANADINITE, RankineBlocks.VANADINITE_ORE.get().getStateContainer().getBaseState(), Config.VANADINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.VANADINITE_ORE_MIN_HEIGHT.get(), Config.VANADINITE_ORE_MIN_HEIGHT.get(), Config.VANADINITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.VANADINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_VANADINITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.VANADINITE, RankineBlocks.VANADINITE_ORE.get().getStateContainer().getBaseState(), Config.VANADINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.VANADINITE_ORE_MIN_HEIGHT.get(), Config.VANADINITE_ORE_MIN_HEIGHT.get(), Config.VANADINITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.VANADINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BISMUTHINITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BISMUTHINITE, RankineBlocks.BISMUTHINITE_ORE.get().getStateContainer().getBaseState(), Config.BISMUTHINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BISMUTHINITE_ORE_MIN_HEIGHT.get(), Config.BISMUTHINITE_ORE_MIN_HEIGHT.get(), Config.BISMUTHINITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.BISMUTHINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BISMUTHINITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BISMUTHINITE, RankineBlocks.BISMUTHINITE_ORE.get().getStateContainer().getBaseState(), Config.BISMUTHINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BISMUTHINITE_ORE_MIN_HEIGHT.get(), Config.BISMUTHINITE_ORE_MIN_HEIGHT.get(), Config.BISMUTHINITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.BISMUTHINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ACANTHITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ACANTHITE, RankineBlocks.ACANTHITE_ORE.get().getStateContainer().getBaseState(), Config.ACANTHITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ACANTHITE_ORE_MIN_HEIGHT.get(), Config.ACANTHITE_ORE_MIN_HEIGHT.get(), Config.ACANTHITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.ACANTHITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ACANTHITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ACANTHITE, RankineBlocks.ACANTHITE_ORE.get().getStateContainer().getBaseState(), Config.ACANTHITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ACANTHITE_ORE_MIN_HEIGHT.get(), Config.ACANTHITE_ORE_MIN_HEIGHT.get(), Config.ACANTHITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.ACANTHITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PYROLUSITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PYROLUSITE, RankineBlocks.PYROLUSITE_ORE.get().getStateContainer().getBaseState(), Config.PYROLUSITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PYROLUSITE_ORE_MIN_HEIGHT.get(), Config.PYROLUSITE_ORE_MIN_HEIGHT.get(), Config.PYROLUSITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PYROLUSITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PYROLUSITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PYROLUSITE, RankineBlocks.PYROLUSITE_ORE.get().getStateContainer().getBaseState(), Config.PYROLUSITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PYROLUSITE_ORE_MIN_HEIGHT.get(), Config.PYROLUSITE_ORE_MIN_HEIGHT.get(), Config.PYROLUSITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.PYROLUSITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CHROMITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CHROMITE, RankineBlocks.CHROMITE_ORE.get().getStateContainer().getBaseState(), Config.CHROMITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CHROMITE_ORE_MIN_HEIGHT.get(), Config.CHROMITE_ORE_MIN_HEIGHT.get(), Config.CHROMITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.CHROMITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CHROMITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CHROMITE, RankineBlocks.CHROMITE_ORE.get().getStateContainer().getBaseState(), Config.CHROMITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CHROMITE_ORE_MIN_HEIGHT.get(), Config.CHROMITE_ORE_MIN_HEIGHT.get(), Config.CHROMITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.CHROMITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MOLYBDENITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MOLYBDENITE, RankineBlocks.MOLYBDENITE_ORE.get().getStateContainer().getBaseState(), Config.MOLYBDENITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MOLYBDENITE_ORE_MIN_HEIGHT.get(), Config.MOLYBDENITE_ORE_MIN_HEIGHT.get(), Config.MOLYBDENITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MOLYBDENITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MOLYBDENITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MOLYBDENITE, RankineBlocks.MOLYBDENITE_ORE.get().getStateContainer().getBaseState(), Config.MOLYBDENITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MOLYBDENITE_ORE_MIN_HEIGHT.get(), Config.MOLYBDENITE_ORE_MIN_HEIGHT.get(), Config.MOLYBDENITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MOLYBDENITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ILMENITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ILMENITE, RankineBlocks.ILMENITE_ORE.get().getStateContainer().getBaseState(), Config.ILMENITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ILMENITE_ORE_MIN_HEIGHT.get(), Config.ILMENITE_ORE_MIN_HEIGHT.get(), Config.ILMENITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.ILMENITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ILMENITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ILMENITE, RankineBlocks.ILMENITE_ORE.get().getStateContainer().getBaseState(), Config.ILMENITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ILMENITE_ORE_MIN_HEIGHT.get(), Config.ILMENITE_ORE_MIN_HEIGHT.get(), Config.ILMENITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.ILMENITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_COLUMBITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.COLUMBITE, RankineBlocks.COLUMBITE_ORE.get().getStateContainer().getBaseState(), Config.COLUMBITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.COLUMBITE_ORE_MIN_HEIGHT.get(), Config.COLUMBITE_ORE_MIN_HEIGHT.get(), Config.COLUMBITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.COLUMBITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_COLUMBITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.COLUMBITE, RankineBlocks.COLUMBITE_ORE.get().getStateContainer().getBaseState(), Config.COLUMBITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.COLUMBITE_ORE_MIN_HEIGHT.get(), Config.COLUMBITE_ORE_MIN_HEIGHT.get(), Config.COLUMBITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.COLUMBITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_WOLFRAMITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.WOLFRAMITE, RankineBlocks.WOLFRAMITE_ORE.get().getStateContainer().getBaseState(), Config.WOLFRAMITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.WOLFRAMITE_ORE_MIN_HEIGHT.get(), Config.WOLFRAMITE_ORE_MIN_HEIGHT.get(), Config.WOLFRAMITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.WOLFRAMITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_WOLFRAMITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.WOLFRAMITE, RankineBlocks.WOLFRAMITE_ORE.get().getStateContainer().getBaseState(), Config.WOLFRAMITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.WOLFRAMITE_ORE_MIN_HEIGHT.get(), Config.WOLFRAMITE_ORE_MIN_HEIGHT.get(), Config.WOLFRAMITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.WOLFRAMITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_TANTALITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.TANTALITE, RankineBlocks.TANTALITE_ORE.get().getStateContainer().getBaseState(), Config.TANTALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.TANTALITE_ORE_MIN_HEIGHT.get(), Config.TANTALITE_ORE_MIN_HEIGHT.get(), Config.TANTALITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.TANTALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_TANTALITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.TANTALITE, RankineBlocks.TANTALITE_ORE.get().getStateContainer().getBaseState(), Config.TANTALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.TANTALITE_ORE_MIN_HEIGHT.get(), Config.TANTALITE_ORE_MIN_HEIGHT.get(), Config.TANTALITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.TANTALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PLUMBAGO_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PLUMBAGO, RankineBlocks.PLUMBAGO_ORE.get().getStateContainer().getBaseState(), Config.PLUMBAGO_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PLUMBAGO_ORE_MIN_HEIGHT.get(), Config.PLUMBAGO_ORE_MIN_HEIGHT.get(), Config.PLUMBAGO_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PLUMBAGO_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PLUMBAGO_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PLUMBAGO, RankineBlocks.PLUMBAGO_ORE.get().getStateContainer().getBaseState(), Config.PLUMBAGO_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PLUMBAGO_ORE_MIN_HEIGHT.get(), Config.PLUMBAGO_ORE_MIN_HEIGHT.get(), Config.PLUMBAGO_ORE_MAX_HEIGHT.get()))).square().chance(Config.PLUMBAGO_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MOISSANITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MOISSANITE, RankineBlocks.MOISSANITE_ORE.get().getStateContainer().getBaseState(), Config.MOISSANITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MOISSANITE_ORE_MIN_HEIGHT.get(), Config.MOISSANITE_ORE_MIN_HEIGHT.get(), Config.MOISSANITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MOISSANITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MOISSANITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MOISSANITE, RankineBlocks.MOISSANITE_ORE.get().getStateContainer().getBaseState(), Config.MOISSANITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MOISSANITE_ORE_MIN_HEIGHT.get(), Config.MOISSANITE_ORE_MIN_HEIGHT.get(), Config.MOISSANITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MOISSANITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SPERRYLITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SPERRYLITE, RankineBlocks.SPERRYLITE_ORE.get().getStateContainer().getBaseState(), Config.SPERRYLITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SPERRYLITE_ORE_MIN_HEIGHT.get(), Config.SPERRYLITE_ORE_MIN_HEIGHT.get(), Config.SPERRYLITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.SPERRYLITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SPERRYLITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SPERRYLITE, RankineBlocks.SPERRYLITE_ORE.get().getStateContainer().getBaseState(), Config.SPERRYLITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SPERRYLITE_ORE_MIN_HEIGHT.get(), Config.SPERRYLITE_ORE_MIN_HEIGHT.get(), Config.SPERRYLITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.SPERRYLITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_LIGNITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.LIGNITE, RankineBlocks.LIGNITE_ORE.get().getStateContainer().getBaseState(), Config.LIGNITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.LIGNITE_ORE_MIN_HEIGHT.get(), Config.LIGNITE_ORE_MIN_HEIGHT.get(), Config.LIGNITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.LIGNITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_LIGNITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.LIGNITE, RankineBlocks.LIGNITE_ORE.get().getStateContainer().getBaseState(), Config.LIGNITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.LIGNITE_ORE_MIN_HEIGHT.get(), Config.LIGNITE_ORE_MIN_HEIGHT.get(), Config.LIGNITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.LIGNITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SUBBITUMINOUS_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SUBBITUMINOUS, RankineBlocks.SUBBITUMINOUS_ORE.get().getStateContainer().getBaseState(), Config.SUBBITUMINOUS_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SUBBITUMINOUS_ORE_MIN_HEIGHT.get(), Config.SUBBITUMINOUS_ORE_MIN_HEIGHT.get(), Config.SUBBITUMINOUS_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.SUBBITUMINOUS_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_SUBBITUMINOUS_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.SUBBITUMINOUS, RankineBlocks.SUBBITUMINOUS_ORE.get().getStateContainer().getBaseState(), Config.SUBBITUMINOUS_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.SUBBITUMINOUS_ORE_MIN_HEIGHT.get(), Config.SUBBITUMINOUS_ORE_MIN_HEIGHT.get(), Config.SUBBITUMINOUS_ORE_MAX_HEIGHT.get()))).square().chance(Config.SUBBITUMINOUS_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BITUMINOUS_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BITUMINOUS, RankineBlocks.BITUMINOUS_ORE.get().getStateContainer().getBaseState(), Config.BITUMINOUS_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BITUMINOUS_ORE_MIN_HEIGHT.get(), Config.BITUMINOUS_ORE_MIN_HEIGHT.get(), Config.BITUMINOUS_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.BITUMINOUS_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_BITUMINOUS_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.BITUMINOUS, RankineBlocks.BITUMINOUS_ORE.get().getStateContainer().getBaseState(), Config.BITUMINOUS_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.BITUMINOUS_ORE_MIN_HEIGHT.get(), Config.BITUMINOUS_ORE_MIN_HEIGHT.get(), Config.BITUMINOUS_ORE_MAX_HEIGHT.get()))).square().chance(Config.BITUMINOUS_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ANTHRACITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ANTHRACITE, RankineBlocks.ANTHRACITE_ORE.get().getStateContainer().getBaseState(), Config.ANTHRACITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ANTHRACITE_ORE_MIN_HEIGHT.get(), Config.ANTHRACITE_ORE_MIN_HEIGHT.get(), Config.ANTHRACITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.ANTHRACITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_ANTHRACITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.ANTHRACITE, RankineBlocks.ANTHRACITE_ORE.get().getStateContainer().getBaseState(), Config.ANTHRACITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.ANTHRACITE_ORE_MIN_HEIGHT.get(), Config.ANTHRACITE_ORE_MIN_HEIGHT.get(), Config.ANTHRACITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.ANTHRACITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_LAZURITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.LAZURITE, RankineBlocks.LAZURITE_ORE.get().getStateContainer().getBaseState(), Config.LAZURITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.LAZURITE_ORE_MIN_HEIGHT.get(), Config.LAZURITE_ORE_MIN_HEIGHT.get(), Config.LAZURITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.LAZURITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_LAZURITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.LAZURITE, RankineBlocks.LAZURITE_ORE.get().getStateContainer().getBaseState(), Config.LAZURITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.LAZURITE_ORE_MIN_HEIGHT.get(), Config.LAZURITE_ORE_MIN_HEIGHT.get(), Config.LAZURITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.LAZURITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_DIAMOND_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.DIAMOND, RankineBlocks.DIAMOND_ORE.get().getStateContainer().getBaseState(), Config.DIAMOND_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.DIAMOND_ORE_MIN_HEIGHT.get(), Config.DIAMOND_ORE_MIN_HEIGHT.get(), Config.DIAMOND_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.DIAMOND_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_DIAMOND_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.DIAMOND, RankineBlocks.DIAMOND_ORE.get().getStateContainer().getBaseState(), Config.DIAMOND_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.DIAMOND_ORE_MIN_HEIGHT.get(), Config.DIAMOND_ORE_MIN_HEIGHT.get(), Config.DIAMOND_ORE_MAX_HEIGHT.get()))).square().chance(Config.DIAMOND_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_GREENOCKITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.GREENOCKITE, RankineBlocks.GREENOCKITE_ORE.get().getStateContainer().getBaseState(), Config.GREENOCKITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.GREENOCKITE_ORE_MIN_HEIGHT.get(), Config.GREENOCKITE_ORE_MIN_HEIGHT.get(), Config.GREENOCKITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.GREENOCKITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_GREENOCKITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.GREENOCKITE, RankineBlocks.GREENOCKITE_ORE.get().getStateContainer().getBaseState(), Config.GREENOCKITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.GREENOCKITE_ORE_MIN_HEIGHT.get(), Config.GREENOCKITE_ORE_MIN_HEIGHT.get(), Config.GREENOCKITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.GREENOCKITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_EMERALD_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.EMERALD, RankineBlocks.EMERALD_ORE.get().getStateContainer().getBaseState(), Config.EMERALD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.EMERALD_ORE_MIN_HEIGHT.get(), Config.EMERALD_ORE_MIN_HEIGHT.get(), Config.EMERALD_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.EMERALD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_EMERALD_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.EMERALD, RankineBlocks.EMERALD_ORE.get().getStateContainer().getBaseState(), Config.EMERALD_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.EMERALD_ORE_MIN_HEIGHT.get(), Config.EMERALD_ORE_MIN_HEIGHT.get(), Config.EMERALD_ORE_MAX_HEIGHT.get()))).square().chance(Config.EMERALD_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_AQUAMARINE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.AQUAMARINE, RankineBlocks.AQUAMARINE_ORE.get().getStateContainer().getBaseState(), Config.AQUAMARINE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.AQUAMARINE_ORE_MIN_HEIGHT.get(), Config.AQUAMARINE_ORE_MIN_HEIGHT.get(), Config.AQUAMARINE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.AQUAMARINE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_AQUAMARINE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.AQUAMARINE, RankineBlocks.AQUAMARINE_ORE.get().getStateContainer().getBaseState(), Config.AQUAMARINE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.AQUAMARINE_ORE_MIN_HEIGHT.get(), Config.AQUAMARINE_ORE_MIN_HEIGHT.get(), Config.AQUAMARINE_ORE_MAX_HEIGHT.get()))).square().chance(Config.AQUAMARINE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_QUARTZ_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.QUARTZ, RankineBlocks.QUARTZ_ORE.get().getStateContainer().getBaseState(), Config.QUARTZ_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.QUARTZ_ORE_MIN_HEIGHT.get(), Config.QUARTZ_ORE_MIN_HEIGHT.get(), Config.QUARTZ_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.QUARTZ_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_QUARTZ_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.QUARTZ, RankineBlocks.QUARTZ_ORE.get().getStateContainer().getBaseState(), Config.QUARTZ_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.QUARTZ_ORE_MIN_HEIGHT.get(), Config.QUARTZ_ORE_MIN_HEIGHT.get(), Config.QUARTZ_ORE_MAX_HEIGHT.get()))).square().chance(Config.QUARTZ_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_OPAL_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.OPAL, RankineBlocks.OPAL_ORE.get().getStateContainer().getBaseState(), Config.OPAL_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.OPAL_ORE_MIN_HEIGHT.get(), Config.OPAL_ORE_MIN_HEIGHT.get(), Config.OPAL_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.OPAL_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_OPAL_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.OPAL, RankineBlocks.OPAL_ORE.get().getStateContainer().getBaseState(), Config.OPAL_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.OPAL_ORE_MIN_HEIGHT.get(), Config.OPAL_ORE_MIN_HEIGHT.get(), Config.OPAL_ORE_MAX_HEIGHT.get()))).square().chance(Config.OPAL_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAJORITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAJORITE, RankineBlocks.MAJORITE_ORE.get().getStateContainer().getBaseState(), Config.MAJORITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAJORITE_ORE_MIN_HEIGHT.get(), Config.MAJORITE_ORE_MIN_HEIGHT.get(), Config.MAJORITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.MAJORITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_MAJORITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.MAJORITE, RankineBlocks.MAJORITE_ORE.get().getStateContainer().getBaseState(), Config.MAJORITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.MAJORITE_ORE_MIN_HEIGHT.get(), Config.MAJORITE_ORE_MIN_HEIGHT.get(), Config.MAJORITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.MAJORITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_FLUORITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.FLUORITE, RankineBlocks.FLUORITE_ORE.get().getStateContainer().getBaseState(), Config.FLUORITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.FLUORITE_ORE_MIN_HEIGHT.get(), Config.FLUORITE_ORE_MIN_HEIGHT.get(), Config.FLUORITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.FLUORITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_FLUORITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.FLUORITE, RankineBlocks.FLUORITE_ORE.get().getStateContainer().getBaseState(), Config.FLUORITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.FLUORITE_ORE_MIN_HEIGHT.get(), Config.FLUORITE_ORE_MIN_HEIGHT.get(), Config.FLUORITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.FLUORITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_URANINITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.URANINITE, RankineBlocks.URANINITE_ORE.get().getStateContainer().getBaseState(), Config.URANINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.URANINITE_ORE_MIN_HEIGHT.get(), Config.URANINITE_ORE_MIN_HEIGHT.get(), Config.URANINITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.URANINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_URANINITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.URANINITE, RankineBlocks.URANINITE_ORE.get().getStateContainer().getBaseState(), Config.URANINITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.URANINITE_ORE_MIN_HEIGHT.get(), Config.URANINITE_ORE_MIN_HEIGHT.get(), Config.URANINITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.URANINITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_STIBNITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.STIBNITE, RankineBlocks.STIBNITE_ORE.get().getStateContainer().getBaseState(), Config.STIBNITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.STIBNITE_ORE_MIN_HEIGHT.get(), Config.STIBNITE_ORE_MIN_HEIGHT.get(), Config.STIBNITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.STIBNITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_STIBNITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.STIBNITE, RankineBlocks.STIBNITE_ORE.get().getStateContainer().getBaseState(), Config.STIBNITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.STIBNITE_ORE_MIN_HEIGHT.get(), Config.STIBNITE_ORE_MIN_HEIGHT.get(), Config.STIBNITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.STIBNITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_XENOTIME_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.XENOTIME, RankineBlocks.XENOTIME_ORE.get().getStateContainer().getBaseState(), Config.XENOTIME_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.XENOTIME_ORE_MIN_HEIGHT.get(), Config.XENOTIME_ORE_MIN_HEIGHT.get(), Config.XENOTIME_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.XENOTIME_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_XENOTIME_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.XENOTIME, RankineBlocks.XENOTIME_ORE.get().getStateContainer().getBaseState(), Config.XENOTIME_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.XENOTIME_ORE_MIN_HEIGHT.get(), Config.XENOTIME_ORE_MIN_HEIGHT.get(), Config.XENOTIME_ORE_MAX_HEIGHT.get()))).square().chance(Config.XENOTIME_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_HALITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.HALITE, RankineBlocks.HALITE_ORE.get().getStateContainer().getBaseState(), Config.HALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.HALITE_ORE_MIN_HEIGHT.get(), Config.HALITE_ORE_MIN_HEIGHT.get(), Config.HALITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.HALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_HALITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.HALITE, RankineBlocks.HALITE_ORE.get().getStateContainer().getBaseState(), Config.HALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.HALITE_ORE_MIN_HEIGHT.get(), Config.HALITE_ORE_MIN_HEIGHT.get(), Config.HALITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.HALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PINK_HALITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PINK_HALITE, RankineBlocks.PINK_HALITE_ORE.get().getStateContainer().getBaseState(), Config.PINK_HALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PINK_HALITE_ORE_MIN_HEIGHT.get(), Config.PINK_HALITE_ORE_MIN_HEIGHT.get(), Config.PINK_HALITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PINK_HALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PINK_HALITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PINK_HALITE, RankineBlocks.PINK_HALITE_ORE.get().getStateContainer().getBaseState(), Config.PINK_HALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PINK_HALITE_ORE_MIN_HEIGHT.get(), Config.PINK_HALITE_ORE_MIN_HEIGHT.get(), Config.PINK_HALITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.PINK_HALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_INTERSPINIFEX_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.INTERSPINIFEX, RankineBlocks.INTERSPINIFEX_ORE.get().getStateContainer().getBaseState(), Config.INTERSPINIFEX_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.INTERSPINIFEX_ORE_MIN_HEIGHT.get(), Config.INTERSPINIFEX_ORE_MIN_HEIGHT.get(), Config.INTERSPINIFEX_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.INTERSPINIFEX_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_INTERSPINIFEX_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.INTERSPINIFEX, RankineBlocks.INTERSPINIFEX_ORE.get().getStateContainer().getBaseState(), Config.INTERSPINIFEX_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.INTERSPINIFEX_ORE_MIN_HEIGHT.get(), Config.INTERSPINIFEX_ORE_MIN_HEIGHT.get(), Config.INTERSPINIFEX_ORE_MAX_HEIGHT.get()))).square().chance(Config.INTERSPINIFEX_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PETALITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PETALITE, RankineBlocks.PETALITE_ORE.get().getStateContainer().getBaseState(), Config.PETALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PETALITE_ORE_MIN_HEIGHT.get(), Config.PETALITE_ORE_MIN_HEIGHT.get(), Config.PETALITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PETALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PETALITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PETALITE, RankineBlocks.PETALITE_ORE.get().getStateContainer().getBaseState(), Config.PETALITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PETALITE_ORE_MIN_HEIGHT.get(), Config.PETALITE_ORE_MIN_HEIGHT.get(), Config.PETALITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.PETALITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_COBALTITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.COBALTITE, RankineBlocks.COBALTITE_ORE.get().getStateContainer().getBaseState(), Config.COBALTITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.COBALTITE_ORE_MIN_HEIGHT.get(), Config.COBALTITE_ORE_MIN_HEIGHT.get(), Config.COBALTITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.COBALTITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_COBALTITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.COBALTITE, RankineBlocks.COBALTITE_ORE.get().getStateContainer().getBaseState(), Config.COBALTITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.COBALTITE_ORE_MIN_HEIGHT.get(), Config.COBALTITE_ORE_MIN_HEIGHT.get(), Config.COBALTITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.COBALTITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CRYOLITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CRYOLITE, RankineBlocks.CRYOLITE_ORE.get().getStateContainer().getBaseState(), Config.CRYOLITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CRYOLITE_ORE_MIN_HEIGHT.get(), Config.CRYOLITE_ORE_MIN_HEIGHT.get(), Config.CRYOLITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.CRYOLITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CRYOLITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CRYOLITE, RankineBlocks.CRYOLITE_ORE.get().getStateContainer().getBaseState(), Config.CRYOLITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CRYOLITE_ORE_MIN_HEIGHT.get(), Config.CRYOLITE_ORE_MIN_HEIGHT.get(), Config.CRYOLITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.CRYOLITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PYRITE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PYRITE, RankineBlocks.PYRITE_ORE.get().getStateContainer().getBaseState(), Config.PYRITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PYRITE_ORE_MIN_HEIGHT.get(), Config.PYRITE_ORE_MIN_HEIGHT.get(), Config.PYRITE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.PYRITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_PYRITE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.PYRITE, RankineBlocks.PYRITE_ORE.get().getStateContainer().getBaseState(), Config.PYRITE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.PYRITE_ORE_MIN_HEIGHT.get(), Config.PYRITE_ORE_MIN_HEIGHT.get(), Config.PYRITE_ORE_MAX_HEIGHT.get()))).square().chance(Config.PYRITE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CELESTINE_COUNT = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CELESTINE, RankineBlocks.CELESTINE_ORE.get().getStateContainer().getBaseState(), Config.CELESTINE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CELESTINE_ORE_MIN_HEIGHT.get(), Config.CELESTINE_ORE_MIN_HEIGHT.get(), Config.CELESTINE_ORE_MAX_HEIGHT.get()))).square().func_242731_b(Config.CELESTINE_ORE_COUNT.get());
    public static final ConfiguredFeature<?, ?> ORE_CELESTINE_CHANCE = RANKINE_ORE.withConfiguration(new RankineOreFeatureConfig(RankineOreFeatureConfig.RankineFillerBlockType.CELESTINE, RankineBlocks.CELESTINE_ORE.get().getStateContainer().getBaseState(), Config.CELESTINE_ORE_SIZE.get()))
            .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(Config.CELESTINE_ORE_MIN_HEIGHT.get(), Config.CELESTINE_ORE_MIN_HEIGHT.get(), Config.CELESTINE_ORE_MAX_HEIGHT.get()))).square().chance(Config.CELESTINE_ORE_COUNT.get());


}

