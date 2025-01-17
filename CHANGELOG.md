
# Changelog
All notable changes to the mod, Project Rankine, will be kept in this file.

## Project Rankine Version 1.3.5 Changelog [22Aug2022]
### Overview
- Treetapping fluids now use cauldrons with heat sources to produce outputs
- Building Tool can now be used to construct/upgrade the Beehive Oven when right-clicking the Beehive Oven Pit if any tier of Refractory Bricks are in the offhand 
- Beehive Oven now requires the full structure to begin cooking and cooks all blocks at once (based on the combined cook times of all blocks inside the oven)
- Beehive Oven and Foraging JEI pages have been updated and give more indications about cook times, related biomes, and other important information
- Cobalt, Niobium, Tantalum, Tungsten, and Vanadium now are obtained primarily through the Fusion Furnace and respective ores can no longer be blasted or cooked on the Beehive Oven
    - Materials related to these elements have also been added or had their relevant recipes changed, such as Vanadium Pentoxide and Tungsten Carbide
- Gases no longer disappear when replaced by a block and some have individually defined properties, such as growing nearby Rankine Soil blocks with Nitrogen or expanding fires with Oxygen
- Mineral wool can now be made through Fusion Furnace recipes, allowing for the creation of RTGs that currently act as longer-term batteries
- All alloys now have sheetmetals; Vertical slabs and Sheetmetal slabs have been removed
- Updated Simplified Chinese (zh_cn) translation (Mies97)
----

### BLOCKS
- Gas blocks now move to the nearest empty air block if they are replaced
- Removed all Vertical Slabs (use a companion mod that adds them in)
- Removed Sheetmetal Slabs
- Removed Gold, Silver, and Platinum Sheetmetal
- Added a sheetmetal for all alloys
- Alloy Blocks now store composition properly and can be pick-blocked in creative
- Blood Obsidian and Snowflake Obsidian can now be used to make Nether Portals
- Added cauldron variants for tree tapping fluids
- Altered Tree Tap and Tap Line functionality to work with new cauldron system
- Removed ability for muds to convert back to soils
- Beehive Oven requires full structure and will procss all blocks at once

### ITEMS
- Spears and Knives now give/reduce Attack Range rather than Reach Distance
- Crowbars and the Building Tool now provide Reach Distance
- Building tool can be used to place blocks for Beehive Oven

### TAGS
- Added rankine:gold_ore to #minecraft:guarded_by_piglins
- Added #forge:nuggets/niobium_alloy
- Added #forge:ingots/niobium_alloy
- Added #forge:storage_blocks/niobium_alloy
- Added #forge:nuggets/zirconium_alloy
- Added #forge:ingots/zirconium_alloy
- Added #forge:storage_blocks/zirconium_alloy

### RECIPES
- Beehive Cooking recipe json now includes a minimum and maximum cook time
- Alloy outputs are now based on 9 material rather than 10 and are rounded down to the nearest whole number
- Sheetmetal recipes now use tags
- Updated JEI page for Beehive Oven recipes, adding buttons to see cook time for each tier
- Updated JEI page for Foraging recipes, adding a hover-over icon to see applicable biomes and biome tags
- Added Perovskite to several crushing recipes
- Added Vanadinite to Pyrolusite Ore crushing
- Removed tree tapping fluid recipes from evaporation
- Removed Cryolite, Petalite, Pyrochlore, Pollucite, Scheelite, Vanadinite, Wolframite blasting recipes
- Added mixing recipe for Fire Clay Balls
- Added fusion furnace recipes for Mineral Wool and Phosphorite
- Ported recipes from 1.3.2 Hotfix 4
- Removed recipes for Piston Crusher and Gyratory Crusher

### FIXES
- Fixed bug where Tree Taps could sometimes stop indefinitely
- Fixed the creation of fire blocks from using flint
- Fixed furnace-type tile entities emitting light when they are not on
- Fixed bug where blocks with Foraging recipes were not removed on successful foraging


## Project Rankine Version 1.3.4 Changelog [15Jul2022]
### Hotfix 1 [24Jul2022]
- Composition previews will now change colors and give the item that can be made by its current inventory
- Alloy Furnace and Induction Furnace now show composition previews on servers
- Updated shift-click logic for Alloy Furnace and Induction Furnace for servers
- Improved logic for determining if an inventory can make an alloy recipe
- Batteries will now be extracted from machines if they do not have enough power
- Batteries can be smelted in a Blast Furnace to recycle materials
- Changed bounds for Carbon and Iron in Cast Iron alloying
- Increased tin max bound in Bronze alloying
- Changed Induction Furnace GUI to use new battery texture in battery slot
- Changed how items are inserted into Alloy Furnace, Induction Furnace, Fusion Furnace, and Gyratory Crusher
- Fixed bug where interacting with certain blocks resulted in a crash

### BLOCKS
- Cobbles can be picked up by right clicking
- Fixed loottable of crops in inmature stages of growth
- Removed Piston Crusher and Gyratory Crusher Functionality
- Changed hardness value of some machines
- Fixed leaf litters floating on non-solid blocks
- Leaf litters no longer drop when landing
- Fixed a crash with leaf litter spawning
- Added Scheelite Block, Nepheline Block, Pyrochlore Block
- Added Episyenite, Nepheline Syenite, Phonolite as stones with variants. 
- Blocks under stumps take longer to mine unless using an alloy shovel
- On grassy soils, short grass can grow to grass, and grass to tall grass
- Fixed crash with grassy soils in modded biomes
- Added dust color for Desert Sand particles
- Sugarcane and cactus can be placed on Silt
- Added Slate Stepping Stones
- Fixed the bonemealing of wall mushrooms
- Updated wall mushroom block textures
- Obsidian variants are no longer pushable by a piston
- Changed the texture of Alluvium
- Fixed saplings not growing into their respective trees
- Added Tiered Crushing Heads which are used on a piston to crush blocks in world

### ITEMS
- Added Nepheline, Scheelite, Pyrochlore
- Changed text for Alloy Harvest Level to Tier
- Fixed stacks of Fruit Jam being consumed at once
- Only alloy pickaxes can be used to obtain random nuggets from nearby ores
- Only alloy shovels can forage
- Foraging enchantment can only be applied to alloy shovels
- Fortune can now be applied to Hammers (increases the number of potential outputs from crushing; see below)
- Fixed Rankine Journal
- Removed Crushing Heads 0-5
- Added textures for missing nuggets and other placeholder textures

### WORLDGEN
- Added Nepheline Syenite and Episyenite intrusion to beach biomes
- Added Phonolite stone layer to Badlands biomes
- Alluvium spawns as a soil variant in rivers
- Sand in oceans is replaced with silt

### TAGS
- Added new stones to relevant tags
- Renamed #rankine:crowbar_effective to #rankine:mineable/crowbar
- Added Stump to #rankine:mineable/crowbar
- Added Charcoal Pit to #minecraft:minable_with_axe
- Added Snowflake Obsidian and Blood Obsidian to #forge:obsidian and #minecraft:requires_diamond_tool and #minecraft:dragon_immune
- Added #forge:mushrooms

### RECIPES
- Changed format and functionality for Crushing recipes (see below)
- White Gold Alloy now gives Fortune to Hammers if enchantability requirements are met
- Fixed inversion of Saddle Tree recipe
- Added rock generator recipes for new stones
- Added blasting recipe for Rhodochrosite to Manganeese Ingot
- Added/changed Fusion Furnace recipe for Chalcopyrite and Molybdenite smelting
- Removed Ilmenite to Titanium blasting recipe
- Removed Cobblestone from cobbles recipe
- Fixed Foraging and Evaporation biome based recipes
- Adjusted outputs of Alluvium and Black Sand sluicing
- Added evaporation recipes for water in desert/badlands biomes and in dripstone/lush caves biomes
- Added rock generator recipe for Meteorite, Frozen Meteorite and Enstatite

### CRUSHING
- Crushing results are now handled by a weighted system similar to Slucing
- Crushing at the exact tier an output is available no longer gives an additional chance of obtaining said output
- Each tier level increases the total number of potential outputs by +1
- Each level of Fortune increases the total number of potential outputs by +1
- Atomize enchantment now turns all non-guaranteed outputs from crushing into experience and cannot be used with Fortune
- Certain items for each crushing recipe are guaranteed, meaning that every time the input is crushed that output will always drop and not count toward total number of potential outputs
- Some outputs can be obtained multiple times or only once per crushed input
- Recipes have a failure rate, which indicate the chance that a potential output is skipped (weighted as an empty item, meaning that outputs only available in higher tiers will offset chance of failure compared to crushing at lower tiers)
- Piston Crusher and Gyratory Crusher have been replaced by in-world crushing using the piston and a crushing head. Place the crushing head on the face of the piston and power
- The in-world crushing system is work-in-progress and likely to change in a future update
- All crushing recipes have been changed to accomodate the new system
- Crushing JEI has also changed to accomodate the new system, and shows the number of potential rolls and the failure rate at max tier (by default, Netherite)



## Project Rankine Version 1.3.3 (1.18.2 Port) Changelog [14Jun2022]
This update is the initial port to 1.18.2 from 1.16.5. Many minor changes may have occured during the update, but the significant ones are recorded below.
### Hotfix 1 [22Jun2022]
- Removed the ability to dye various blocks in-world (may be reimplemented)
- Changed textures for compound gas blocks
- Added alloy crafting recipes for Cupronickel and Duralumin Arrows
- Changed texture for Alloy Rod
- Added Foraging json recipe type
- Added foraging recipes for grass blocks, podzols and myceliums in certain biomes
- Adjusted sluicing recipes of soil variants 
- Fixed campfire cooking recipe for bone ash and dried bamboo
- Fixed pentlandite_block_to_nickel_block beehive oven recipe
- Added forge:podzol and forge:mycelium item tags
- Added config to enable/disable FOV effects on movement modifier blocks, set to false by default
- Fixed Fusion Furnace recipes not working when an item was not included in the gas slot
- Fixed alloy arrows and related alloy crafting recipes
- Fixed alloy crafting recipes for alloy gears
- Fixed recipe names for alloy gears and rods
- Fixed fluid calculations for Mixing recipes
- Fixed a server crash due to entity rendering
- Fixed wall mushroom related crashes
- Fixed crash relating to walking into gas blocks
- Fixed crash upon opening/using Element Indexer
- Fixed missing tag alloy crafting recipes
- General event code restructuring to reduce lag
- Added the ability for flint to light campfires and soul capfires
- Fixed GUIs using fluids having no graphical representation of fluid in tanks

### BLOCKS
- Added dripstone and pointed dripstone variants (ferric, magnesitic, gypsic, zirconic, nitric, boracitic)
- Mineral Deposits no longer generate Gwihabaite
- Added Rankine Copper Ore equivalent
- Added Banded Iron Formations
- Removed Evaporite
- Removed Copper Block
- Removed Calcite Block
- Updated loottables of vanilla ore equivalents to match vanilla
- Adjusted xp drop from ores
- Unlit campfires can be lit using flint

### ITEMS
- Removed Copper Ingot

### WORLDGEN
- Added a retrogen system to handle block replacements. Config option available. This should improve continuity of replacements across chunk borders
- Larger patches of white sand
- White sandstone generates under white sand
- Desert sand and desert sandstone replace default sand in badlands biomes
- Adjusted biome placement of trees and plants
- Adjusted ore placements

### TAGS
- Added blocks to respective needs_tool tags
- Added blocks to respective minable tags
- Added Rankine blocks to animal spawn on tags

### MISC
- Changed misc item textures
- Removed AnimalSpawning mixin
- Fixed Fusion Furnace recipes not working when an item was not included in the gas slot
- Fixed shonkinite crushing recipe
- Adjusted output of alternative gunpowder recipes from 4 to 2
- Added conversion crafting recipes for vanilla based ores

## Project Rankine Version 1.3.2 Changelog [28Apr2022]
### Hotfix 4 [02Aug2022]
- (Backport) Changed the texture of compound gases (Carbon Dioxide, Hydrogen Sulfide, etc.)
- (Backport) Alloy Blocks now give the proper composition when picked in Creative mode
- (Backport) Updated textures for Block of Goethite
- (Backport) Obsidian variants can now be used to make Nether Portals
- Added alternative Mortar recipe using Perlite
- Added Fusion Furnace recipe for Asbestos
- Added Fire Clay crafting recipe
- Added Dry Ice crafting recipe
- Added crafting recipe for Checkered Marble
- Fixed recipe error for backported Chalcopyrite Fusion Furnace recipe
- Updated textures for Block of Zirconium Alloy, Block of Niobium Alloy, Block of Bridgmanite, and Block of Forsterite
- Updated Simplified Chinese (zh_cn) translation (Mies97)

### Hotfix 3 [20Jul2022]
- Alloy Template Table now shows recipes that can be made from ingredients in the user's inventory first
- Changed how the Alloy Template Table loads recipes to improve performance
- Composition previews will now change colors and give the item that can be made by its current inventory
- Alloy Furnace and Induction Furnace now show composition previews on servers
- Updated shift-click logic for Alloy Furnace and Induction Furnace for servers
- Improved logic for determining if an inventory can make an alloy recipe
- Batteries will now be extracted from machines if they do not have enough power
- Batteries can be smelted in a Blast Furnace to recycle materials
- Changed bounds for Carbon and Iron in Cast Iron alloying
- Increased tin max bound in Bronze alloying
- Backported nugget textures from V1.18.2-1.3.4
- Cobbles can be picked up by right clicking
- Removed Cobblestone from cobbles recipe
- Added Alluvium to #minecraft:bamboo_plantable_on
- Added obsidian variants to #forge:obsidian
- Added Stump to #rankine:crowbar_effective
- Alluvium now spawns in place of soil in rivers
- Silt additionally spawns in place of sand in oceans
- Only alloy pickaxes can mine nuggets from nearby ores
- Added rhodochrosite to manganese ingot blasting recipe
- Removed ilmenite to titanium ingot blasting recipe
- Added slag to iron nugget blasting recipe
- Short grass slowly grows into tall grass
- Updated path creation on grassy soils
- Added chalcopyrite fusion furnace recipe
- Changed molybdenite fusion furnace recipe
- Changed Induction Furnace GUI to use new battery texture in battery slot
- Changed how items are inserted into Alloy Furnace, Induction Furnace, Fusion Furnace, and Gyratory Crusher

#### Bug Fixes
- Fixed bug where fuels other than batteries could be used for Gyratory Crusher, Fusion Furnace, and Induction Furnace
- Fixed bug where batteries that did not have enough energy could be used
- Fixed missing loot table for Block of Bone Char
- Fixed Rankine Ore configs not changing harvest level
- Fixed issue where alloy output strings in Alloy Furnace, Template Table, and Induction Furnace were not shown server-side (TODO: CHECK ON SERVER)
- Fixed obsidian variants being pushed by piston
- Fixed placing sugarcane and cactus on silt and alluvium
- Fixed eating more than one fruit jam at once
- Rankine vanilla ore variants will now drop xp
- Fixed Saddle recipe
- Fixed backwards campfire recipes
- Fixed crash with leaf litters spawning from modded trees
- Fixed crash with grassy soils in modded biomes
- Fixed loottable issue with non-mature crops
- Fixed server crash while using prospecting stick


### Hotfix 2 [17Jun2022]
- Added config to enable/disable FOV effects on movement modifier blocks, set to false by default
- Added alloy crafting recipes for Cupronickel and Duralumin Arrows
- Changed texture for Alloy Rod
- Fixed bug related to block placement of wall mushrooms
- Fixed Fusion Furnace recipes not working when an item was not included in the gas slot
- Fixed alloy arrows and related alloy crafting recipes
- Fixed alloy crafting recipes for alloy gears
- Fixed recipe names for alloy gears and rods
- Fixed fluid calculations for Mixing recipes
- Fixed charcoal pit client-side behavior
- Fixed ability to light fires with two flint
- Fixed shonkinite crushing recipe

### Hotfix 1 [29Apr2022]
- Added Apatite fusion furnace recipe
- Added pulp recipe involving talc
- Added alternative methods to get certain potions
- Added special item that has a chance to drop when breaking clover blocks
- Fixed lang for Conductivity potions
- Fixed connection issue to multiplayer servers
- Fixed tag errors for added recipes
- Fixed penning trap recipe 

### BLOCKS
- Added model variants for stumps
- Blocks under stumps take longer to mine unless using a shovel
- Stumps break if floating
- Leaf Litters will only convert grass blocks to podzol under light level 10
- Leaf Litters can convert cobblestone and stone bricks to their mossy equivalents
- Introduced an age blockstate to leaf litters to control growth
- Added signs for all wood types
- Added Bales for grain crops
- Charred Logs can be obtained from an improperly built charcoal pit
- Shrunk collision box of mud blocks to be 15 pixels tall
- Updated some wood textures (primarily doors and trapdoors)

### ITEMS
- Updated Boat and Door item textures
- Added/updated miscellaneous textures

### RECIPES
- Optimized and changed format of Sluicing Recipe JSON (now uses a JSON array for outputs rather than numbered outputs)
- Added Fusion Furnace recipes to obtain previously unobtainable elements (Note: These recipes will be removed in the next major update)
- Changed Steel Wire recipe to Duralumin Wire
- Fixed Alloy Crafting recipes not linking to the right ingredients
- Changed all crafting recipes involving Alloy Wire to use specific types
- Added Terbium Nugget to Xenotime Crushing
- Removed Geode sluicing recipe (changed to be an innate feature of hammers)

### ALLOYING
- Added alloy stats to gaseous elements
- Added gaseous elements to various alloys

### CONFIG
- Removed unused configs
- Added option to disable fire clay generation under coal veins
- Added option to disable soil replacement independently of the biomeSettings config
- Added option to disable mud block generation near water. Mud placing can be a slightly laggy process
- Added option to disable Stone and Wooden Hammers similar to other vanilla tools
- Added leafLitterDecay config to adjust rate of Leaf Litter decay.

### MISC
- Added recipe for plant fiber from willow branchlet
- Added tag #rankine:knife_shearable to dictate which blocks knives can harvest, effectively silk touching them
- Added tag #forge:breedables/llama and added associated crops and bales for breeding llamas
- Added clovers to #minecraft:small_flowers
- Added sounds to miscellaneous actions
- Added applicable blocks to #twilight_forest:portal/edge and #twilight_forest:portal/decoration

### Bug Fixes
- Fixed crash with Ore Detector and related devices on server
- Alloy furnace will now drop inventory when broken
- Fixed flowers and other plants not being placable on rankine podzols and mycelliums
- Added missing lootables

## Project Rankine Version 1.3.1 Changelog [9Mar2022]

### Hotfix 1 [11Mar2022]
- Corrected generated recipe names for alloy tools
- Fixed issue where generated alloy tool recipes did not have the inheritRecipe variable when using multiple alloy recipes, which made certain tools uncraftable
- Fix crash related to specific instances of incorrect ore blockstates
- Added missing lang entries
- Added a crafting recipe and use for the Totem of Blazing
- Added element stats to Neptunium

### BLOCKS
- Blocks under a stone cobble or stump take twice as long to mine
- Added colored versions of cement
- Cement remapped to light gray cement


### ITEMS
- Removed Soy Milk
- Added hunger value to Tofu
- Added Cooked Tofu and Tofu Curry as foods
- Added Inner Bark as a food item dropped from stripping certain logs
- Prospecting Stick has a chance to detect an ore sample from stone cobbles
- Prospecting Stick message now displays ore name and harvest level
- Ore Detector functionality changed to return the name and position of the closest ore
- While held, the Magnetometer reads a magnetic field strength based on surrounding blocks
- Hammers now also deal 1.5x damage to Guardians
- Totem of Promising increase luck attribute

### ENCHANTING
- Changed enchantment requirements to be based on alloy recipe rather than item to allow alloy-specific enchantments to be applied at Enchanting Tables
- Added Endospore, an enchantment for an Ender Amalgam Hoe which can cause a mature crop block to grow near a harvested crop block if space is available
- Added Endure, an enchantment for an Ender Amalgam Sword which heals the user when standing nearby End Crystals
- Added Endolithic, an enchantment for an Ender Amalgam Shovel which allows right-clicking stone blocks to teleport to the nearest empty space in the direction of the block you are facing
- Added Endless, an enchantment for an Ender Amalgam Pickaxe which applies or extends a random beneficial potion effect when mining ores
- Added Endeavor, an enchantment for an Ender Amalgam Hammer which further increases the damage dealt to elemental-type enemies and adds a chance for those enemies to drop items on hit
- Added Endothermic, an enchantment for an Ender Amalgam Axe which causes harvested logs to turn into charcoal
- Added Endplay, an enchantment for an Ender Amalgam Blundberbuss which converts normal cannonballs into Enderballs that have increased veolcity and spawns Endermites which attack targets in the impact area

### ALLOYING
- Added Damascus Steel as a special steel variant available from Villagers
- Added new enchantments to Ender Amalgam
- Added Depth Strider enchantment to Brass
- Added bonus corrosion resistance to Stainless Steel and slightly modified bonus stats
- Added bonus stats to Niobium Alloy
- Modified mining speed, harvest level, and enchantability formulas for Zirconium
- Zirconium Alloy can now be made in the Alloy Furnace
- Modified enchantability formula for Endositum

### CONFIG
- Config added to disable pumice soap right click ability
- Implemented replaceVanillaOres config

### TAGS
- Added blocks to Terraforged tags
- Added new toolsets to appropriate tags

### RECIPES
- Added "stripping" json recipe type for spawning extra items when an axe right clicks a block
- Fixed recipe name typos
- Decreased the number of spins for most mixing barrel recipes
- Fixed recipe errors that caused incompatibilities with certain mods
- Added missing recipes for blocks and items
- Adjusted unaged_cheese_mixing recipe
- Added smelting/blasting recipes for rankine vanilla ores

### MISC
- Added textures for miscellaneous items with placeholder textures
- Added additional details to JEI recipes
- Added chance for sediments in the sedimentary generator to be removed
- Added various status messages as Lang entries
- Fixed Mixing Barrel GUI not showing progress
- Fixed incorrect Mixing Barrel fluid requirements
- Fixed tile entities using old conditions for checking for elements
- Fixed blunderbusses being included in the "forge:knives" tag
- Fixed out of bounds error for Packaged Tool and Packaged Armor
- Fixed unregistered placements
- Fixed worldgen crash regarding stone columns
- Fixed issue with stone generators always removing accesory mineral blocks
- Fixed issues with tree chopping
- Fixed tree tap not working past the first day
- Fixed Crucible Steel to use composition modifiers in Crucible recipe
- Updated Rankine Journal
- Removed depreciated/unused code


## Project Rankine Version 1.3 Changelog [27Feb2022]
A new world is highly recomended/needed to support new/changed features. This changelog has been simplified to account for 8 months of changes and multiple alpha versions.

### REGISTRY CHANGES
- Some blocks/items have changed registry names. These should all be remapped to the appropriate new block/item

### BLOCKS
#### --- Removals ---
- Removed all variants of Pumice and Scoria
- Reduced the variants of Skarn and Breccia to just slab, vertical slab, stairs and walls
- Removed Checkered Dacite and Checkered Porphyry and all variants
- Removed Columbite Ore, Salt Ore, Pink Salt Ore, Tantalite Ore, Aquamarine Ore, Majorite Ore, Opal Ore, Vanadinite Ore, Native Aluminum Ore, Native Copper Ore, Moissanite Ore
- Removed Mud (replaced by soil variants)
- Removed Etched Glasses (replaced with other variants - WIP)
- Removed Rope Coil (functionality was migrated to rope block)
- Removed Aluminum Bars, Nickel Bars, and Magnesium Bars (replaced by alloy bars - WIP)
- Removed Tap Barrel, Fluid Drain, Charred Wood, Laser Quarry, Laser Pylon Top, Laser Pylon Base, High Refractory Beehive Oven, Dacitic Tuff, Bamboo Wall and Banboo Culms Wall
#### --- Additions ---
- Added the following as new stones with variants: Rose Marble, Gray Marble, Serpetinite, Marlstone, Norite, Shonkinite, Pyroxenite, Soul Sandstone, Blueschist, Greenschist, Diabase, Eclogite, Honeystone, Whiteschist, Soapstone, Graywacke, Harzburgite, Lherzolite, Wehrlite
- Added Infested Stone, Column and Cobble variant for Rankine stones
- Added Clay Loam, Sandy Loam, Silty Loam, Loamy Sand, Sandy Clay Loam, Silty Clay Loam, Sandy Clay, Silty Clay, Loam, Humus as soil blocks
- Added Graasy, Grass Path, Mycellium, Podzol, Mud, Coarse variants of Rankine soil blocks
- Added Weeping Willow, Honey Locust, Red Birch, Western Hemlock, Erythrina as tree and wood variants
- Added Petrified Chorus and Charred wood variants
- Added Building Mode states to Rankine Stone Bricks, Bricks, Planks
- Added Leaf Litters for all Rankine and Vanilla leaves
- Added Hollow Logs for all Rankine and Vanilla Logs
- Added Sulfur Dioxide Fumarole, Carbon Dioxide Fumarole, Hydrogen Chloride Fumarole, Hydrogen Sulfide Fumarole
- Added Sulfur Dioxide Gas Block, Carbon Dioxide Gas Block, Hydrogen Chloride Gas Block, Hydrogen Sulfide Gas Block, Hydrogen Fluoride Gas Block, Ammonia Gas Block, and elemental gas blocks
- Added Rheniite Ore, Coltan Ore, Chalcocite Ore, Hematite Ore and associated mineral and block
- Added Tilled Soil (farmland block for all soils)
- Added Goldenrod, Red Lily, Orange Lily, White Lily, Purple Morning Glory, Black Morning Glory, Blue Morning Glory as double block flowers
- Added White Clover, Red Clover, Crimson Clover, and Yellow Clover as ground flowers
- Added Green, Brown, Gray, and Black Tektite
- Added garland blockstate to metal poles
- Added an Ornament block (acess variants with building tool)
- Added snowy variants for rankine Leaves
- Added Cob and Refined Cob
- Added Snoflake and Blood Obsidian
- Added slab, stairs, vertical slab, and wall variant for various blocks without a complete set
- Added Komatiitic Tuff, Kimberlitic Tuff
- Added Grassy Soil version of each soil (grass blocks with additional functionality)
- Added Cement, Fire Clay Bricks and Kaolin Bricks with variants
- Added Light Gravel and Dark Gravel
- Added Sod Block, White Sand and Silt
- Added Flood Gate, Metal Pipe, Ground Tap
- Added Pokeberry Bush
- Added Short Grass, Stinging Nettle
- Added Stump block
- Added Mixing Barrel, a new tile entity used to make products similiar to the Crucible
- Added Desert Sand
- Added White Sandstone, Black Sandstone, Desert Sandstone and associated variants
- Added Iron Sheetmetal and slab variants
- Added Gas Bottler (bottles gas blocks)
- Added Gas Vent (moves gas blocks)
- Added Fulgurite, Lightning Glass, Red Lightning Glass, Soul Lightning Glass, Black Lightning Glass, White Lightning Glass
- Added Rankine variant of the vanilla ores for texture adapting 
- Added Bog Iron, Porphyry Copper, Kimberlitic Diamond Ore
- Added Pedestal variants for Galinstan Alloy and Sodium Potassium Alloy
- Added Frozen Meteorite and Frozen Meteorite Bricks
- Added Meteoric Ice (more slippery than blue ice)
- Added Metal Bars and Pole variants for all alloys
- Added slab, stairs, vertical slab and wall variants for Fiber Block
- Added the Fusion Furnace, a new tile entity that uses battery power for chemical reactions involving fluids, gases, and items
- Added Invar Ladder
- Added Distillation Tower and Air Distillation Packing
- Added Borosilicate and CVD Glass
- 
#### --- Changes ---
- Harvest level, hardness and resistance values adjusted for many blocks
- Ores now drop their ore-related item by default and are affected by the Fortune enchantment (1.17 parity)
- Added flamability to many blocks
- Rankine Ore blocks will take the texture of the clicked block when placed
- Tree Taps will place fluid in the world using a Flood Gate through a line of Tap Lines
- Charcoal Pit produces Charcoal Blocks instead of Charred Wood
- Beehive Oven has randomized cook times based off how upgraded the structure is
- Berry bush lootables adjusted to match Sweet Berries


### ITEMS
#### --- Removals ---
- Removed Metal Scraper
- Removed Stone Hammer, Iron Hammer, Diamond Hammer, and Netherite Hammer
- Removed Iron Spear, Diamond Spear and Netherite Spear
- Removed Titanium Crowbar and changed Steel Crowbar to an alloy tool
- Removed Steel Rod, Cast Iron Rod, YAG Rod
- Removed Dry Mortar
- Removed Element Transmuters

#### --- Additions ---
- Added toolsets for Osmiridium, Niobium Alloy, Zirconium Alloy, Ender Amalgam
- Added Bismanol and Permalloy as alternative recipes to Rose Metal and Invar respectively
- Added Gas Bottles
- Added Cement Mix
- Added Wooden Mallet 
- Added Wooden Gold Pan and Pewter Gold Pan
- Added nuggets for alloys
- Added Alloy Knives to all existing Alloy toolsets
- Added Alloy Crowbars to all existing Alloy toolsets
- Added Rope Coil Arrow, which can deploy rope held in the off-hand
- Minerals: Added bauxite, ringwoodite, wadsleyite, forsterite, mellite, bridgmanite, ferropericlase, apatite, laurite, realgar, gypsum, rutile, chlorite, diopside, azurmalachite, goethite, monazite (Gd), monazite (Y), pyrite, spodumene, cooperite, sanidine, hedenbergite, talc, stishovite, asbestos, serpentine, potash
- Added Kaolinite
- Added Pokeberries
- Added Jams for each of the berries
- Building Tool: hold in offhand to place vertical planks and bricks
- Added Lorandite (Thallium ore mineral), Pollucite (Caesium/Rubidium ore mineral), Boron Trioxide, Sodium Fluorosilicate, Iron Chloride, Magnesium Chloride, Hafnia
- Added Buckets for all added fluids
- Added Ammonia Gas Bottle
- Added Bleach
- Added a Meteoric Iron-type item for natural Osmiridium
- Added Silver-Zinc Battery
- Implemented Vanadium Pentoxide, Gwihabaite, Stishovite, and Zirconia 
- Added Osmiridium Toolset (Pickaxe, Axe, Shovel, Sword, Hoe, Hammer, Knife, Crowbar)
- Added Americium RTG, Curium RTG, Plutonium RTG, Polonium RTG, and Strontium RTG
- Added Totem of Enduring, Totem of Hastening, Totem of Levitating, Totem of Promising, Totem of Repulsing, and Totem of Timesaving
- Added buckets for Gray Mud, Hexaflurosilicic Acid, Carbon Disulfide
- Added Silicon-Germanium Thermocouple
- Added Cannonball
- Added Hematite related items
- Implemented Building Tool
- Reduced Jams to a single item
- Added Rye, Oats, Barley, Millet, Sorghum, Soybean (not implemented yet)
- Added Rye Seeds, Oat  Seeds, Barley Seeds, Millet Seeds, Sorghum Seeds, Soybean Seeds (not implemented yet)
- Added Rye Grain, Oat  Grain, Barley Grain, Millet Grain, Sorghum Grain (not implemented yet)
- Added Tofu and Soy Milk (not implemented yet)
- Added the Alloy Blunderbuss, a new ranged weapon that uses cannonball-type items as ammo
- Added the Alloy Surf Rod, a metallic fishing rod that uses different loot tables
- Added Lithium Hydroxide, Potassium Hydroxide, Rubidium Hydroxide, Cesium Hydroxide, Francium Hydroxide
- Added Yellowcake
- Added new cannonball types (Carcass)
- Added Emergency Flotation Device
- Added Magnetometer
- Added Ice Melt
- Bandages can now heal other entities on right-click
- Added Garland

### FLUIDS
- Added Sulfuric Acid
- Added Hydrobromic Acid
- Added Aqua Regia
- Added Red Mud
- Added Gray Mud
- Added Hexafluorosilicic Acid
- Added Carbon Disulfide

### RECIPES
- Added json recipe support for Elements
- Added json recipe support for Tree Tapping
- Added json recipe support for Air Distillation
- Added json recipe support for Mixing Barrel
- Added json recipe support for Fusion Furnace
- Added json recipe support for Stone Generation
- Overhauled Alloying and Alloy Crafting json recipes
- Modifications to other json recipe types
- Overhauled the ingredient requirements for various recipes

### WORLDGEN
- Major changes in generation with both block pallet and features
- Smaller and more frequent intrusions
- Wavier layers
- Multiple types of ore veins

### ADVANCEMENTS
- Overhauled advancements

### ENCHANTMENTS
- Added many new enchantments. Use JEI / Enchantment Descriptions for info. Will be updated in journal and on wiki

### CONFIG
- Overhauled config file. Some options are still WIP
- Major changes include merging into a single file and the addition of worldgen settings

### TAGS
- Added and removed many tags to better suit the needs of the mod. A full tag list and description will be available on the wiki soon

### MISC
- Journal overhaul, still WIP along with all other documentation
- Added custom stone generators using a mix of vanilla an custom methods
- Updated many textures
- Russian lang (ru_ru) added by liottan
- Added custom sound events for subtitles (more to come)
- Updated Forge version to 36.2.20
- Updated Patchouli version to 1.16.4-53.2
- Updated JEI version to 1.16.5:7.7.1.150
- Hammers now deal 1.5x damage to skeletons and golem-type mobs
- Brass can now be used to make tools

### COMPAT
- Added Biomes O' Plenty, Biomes You Go, Botania, Immersive Engineering, Mekanism, Quark, Thermal Series, and Tinker's Construct as optional dependencies 
- Added crushing recipes for Quark stones and cobblestones
- Added alloying recipes for Thermal Series alloys
- Added crucible recipes for Thermal Series glass
- Added alloying recipes for Tinker's Construct alloys
- Added alloying recipe for Andesite Alloy from Create
- Added relevant elements for compatibility with certain alloys


## Project Rankine Version 1.3 Alpha Changelog [2021-10-26]

### Alpha 3 [2021-11-30]

### BLOCKS
- Added Mixing Barrel, a new tile entity used to make products similiar to the Crucible
- Added Hydrogen Fluoride Gas Block
- Added Hollow Logs for all logs
- Added Leaf Litters for all leaves
- Added Desert Sand
- Added White Sandstone, Black Sandstone, Desert Sandstone and associated variants
- Added Iron Sheetmetal and slab variants
- Added Gas Condenser (bottles gas blocks)
- Added Gas Vent (moves gas blocks)
- Added Red Lightning Glass, Soul Lightning Glass, Black Lightning Glass, White Lightning Glass
- Added Hematite Ore and Block
- Converted Material Testing Table to a TileEntity and added slots for tools
- Harvest Level of Sperrylite Ore and Phosphorite changed to HL1
- Removed Polished Soul Sandstone and Soul Sandstone Bricks and variants
- Mud blocks are hoeable
- Dead Grass Blocks cannot be bonemealed
- Both vanilla and rankine plants can be placed on appropriate ground blocks
- Added snowy variants for rankine Leaves
- Added Cob
- Building Tool appropritely affects blocks with modes; Planks, Stone Bricks, Bricks
- Removed Tap Barrel
- Converted Tree Tap to a tile entity with json recipe support

### ITEMS
#### --- Additions ---
- Added Osmiridium Toolset (Pickaxe, Axe, Shovel, Sword, Hoe, Hammer, Knife, Crowbar)
- Added Americium RTG, Curium RTG, Plutonium RTG, Polonium RTG, and Strontium RTG
- Added Totem of Enduring, Totem of Hastening, Totem of Levitating, Totem of Promising, Totem of Repulsing, and Totem of Timesaving
- Added buckets for Gray Mud, Hexaflurosilicic Acid, Carbon Disulfide
- Added Silicon-Germanium Thermocouple
- Added Cannonball
- Added Hematite related items
- Implemented Building Tool
- Reduced Jams to a single item
- Added Rye, Oats, Barley, Millet, Sorghum, Soybean (not implemented yet)
- Added Rye Seeds, Oat  Seeds, Barley Seeds, Millet Seeds, Sorghum Seeds, Soybean Seeds (not implemented yet)
- Added Rye Grain, Oat  Grain, Barley Grain, Millet Grain, Sorghum Grain (not implemented yet)
- Added Tofu and Soy Milk (not implemented yet)
#### --- Changes ---
- Alloy tools can now be repairable in an anvil by ingots that have the same alloy recipe and alloy composition
- Crowbars now have adjusted stats
- Packaged Tools can now be forced to give a certain tool by providing NBT (ex. forceTool: "pickaxe")
- Jams are now using recipe type similar to fireworks (1x Glass Bottle + 2x Sugar + 6x Any Berry)
- Drink-type food items now use the drink ActionType
#### --- Removals ---
- Removed Pendants (replaced by Totems)

### ENCHANTMENTS
#### ---Additions---
- Added Accuracy, which decreases the spread of all projectiles fired from the Alloy Blunderbuss
#### --- Changes ---


### RECIPES
#### --- Additions ---
- Added Mixing and TreeTapping json recipe types
- Added Fusion Furnace recipe to convert Ilmenite into Rutile
- Added an alternative Shulker Gas Vacuum Recipe that uses rubidium
- Added alternative recipes for Silver-Zinc Battery, Magnesium Battery, and Lead-Acid Battery that uses vulcanized rubber
- Added crushing recipes to Beryl Ore
- Added Sodium Fluorosilicate to Fumarole Deposit crushing
- Added Fusion Furnace recipes relating to the Kraft Process
- Added Fusion Furnace recipes for producing Alkali Cellulose, Carbon Nugget, Carbon Disulfide, Cryolite, Fertilzer, Hydrogen Fluoride, Lithium Cobalt Oxide, Phantom Membrane, Slimeball, Sodium Fluorosilicate, Sodium Sulfide, Sponge 
- Added Fusion Furnace smelting recipes for Celestine, Greenockite, Lautarite, Thortveitite, Xenotime
- Added crushing recipes for new sandstones
- Added sedimentary stone generator recipes for new sandstones
- Added alternative recipe for End Rod and Brewing Stand using Ferrocerium
- Added alternative Flint and Steel recipe using Steel Nuggets
- Added Muds, Myceliums, Podzols and Path blocks to Pan Sluicing
- Added alloy crafting recipes for all Alloy Nuggets and Alloy Blocks (composition is now inherited)
#### --- Changes ---
- Added forceNBT arguement to Alloy Recipe JSON, which if set to true (false by default) causes the item to be given alloyData even if it is not an AlloyItem
- Adjusted some outputs for Fusion Furnace recipes
- Interspinifex ore now gives Iron Nuggets for HL1 Crushing
- White dye recipe for Ilmenite changed to use Rutile instead
- Modified recipes related with Hafnia and Zirconia
- Sediment Fan now uses Brass instead of Aluminum
- Fixed broken recipes
#### --- Removals ---
- Removed Blasting recipes for all Monazites, Thortveitite, Xenotime
- Removed Beehive Oven recipes for all Monazite Blocks, Thortveitite Block, Xenotime Block
- Removed Crushing recipes for Lepidolite, all Monazites
- Removed crafting recipe for Slimeballs

### FLUIDS
- Added Gray Mud
- Added Hexafluorosilicic Acid
- Added Carbon Disulfide

### TAGS
- Added fluid tags
- Added missing tool tags
- Added #rankine:construction_sand (includes Desert Sand for building purposes)
- Added blocks to Vanilla tags; mushroom_grow_blocks, bee_growables, enderman_holdable
- Removed tree tapping log tags (replaced by recipes)

### MISC
- Added custom SoundEvents (for subtitles only currently)
- Overhaul of advancements
#### --- Changes ---
- Added config options to disable various features
- Added new config options for ore vein generation
- Adjusted ore generation 
- Gas Blocks now render an overlay when within them
- Ores appropriately replace blocks with different texture names from the block (basalt and sandstones)
- Tree felling makes lwess noise
- Leaves in fast graphics mode render appropriately
- Coke now has proper forge tags
#### --- Config ---
- Condensed settings into fewer categories
- Added option to replace vanilla ores with Rankine versions for texturing (enabled by default)
- Decreased chances for plant events to occur
- Added client config, currently contains option to color grass based off elevation
#### --- Fixes ---
- Remade block localizations to be more consistent with Vanilla
- Fixed ore harvest level configs
- Grass Paths are appropriately replaced
- Grass Blocks spreading to Dirt grow vanilla grass blocks
- Packaged tool now ignores alloy recipes with no inputs (ex. Crucible Steel) and no longer produces debug info
- Fixed issue where alloy armor items were not able to inherit stats from elements properly
- Fixed Osmium and Iridium Enchantment formula
- Fixed incompatibility with Quark Automatic Recipe Unlock Module
- Machines with inventories drop items when broken
#### --- Alloying ---
##### Alloys
- Alloys now use the same tool material, meaning that base stats for every alloy tool will now be the same before considering elements and bonus alloy stats
- All gold alloys and Osmiridium now have an extra 50 durability
- Amalgam now gives a random enchantment if enchantability conditions are met
- Invar now confers +0.5 bonus damage
- All Steels now have unique lang identifiers (Crucible Steel, Maraging Steel, Tool Steel)
- Unified Maraging Steel into a single recipe and adjusted compositions
- Potential compositions for Osmiridium have been adjusted
- Added additional elements to Nitinol
- Phosphorus and Bismuth can now be used in Brass
- Iron and Manganese can now be used in Nickel Silver
- Germanium can now be used in all Gold alloys
- Gallium and Indium can be used in Pewter
- Cesium can now be used in Galinstan and Pewter
- Rubidium can now be used in all Gold alloys and Sodium-Potassium alloy
- Removed post-actinoid elements from Amalgam and Ender Amalgam
- Changed default enchantability bounds for alloys
##### Elements
- Added formulas for Cesium, Gallium, Indium, Rhodium, Rubidium, Ruthenium, Scandium
- Manganese now has an adjusted harvest level formula
- Significantly changed stats or added new stats for Osmium, Iridium
- Iron now gives attack damage (max 2) as composition percentage increases
- Copper now gives attack damage (max 1.2) as composition percentage increases
- Netherite has a new attack damage max of 4 (from 2)
- Vanadium has a new attack damage max of 2 (from 1)
- Fixed Barium damage and attack speed formulas

### COMPAT
- Added Biomes O' Plenty, Biomes You Go, Botania, Immersive Engineering, Mekanism, Quark, Thermal Series, and Tinker's Construct as optional dependencies 
- Added crushing recipes for Quark stones and cobblestones
- Added alloying recipes for Thermal Series alloys
- Added crucible recipes for Thermal Series glass
- Added alloying recipes for Tinker's Construct alloys
- Added alloying recipe for Andesite Alloy from Create
- Added relevant elements for compatibility with certain alloys

### COMMUNITY CONTRIBUTIONS
- Added Russian (ru_ru) translation (liottan)

### Alpha 2 [2021-10-26]

### BLOCKS
#### --- Additions ---
- Added the Fusion Furnace, a new tile entity that uses battery power for chemical reactions involving fluids, gases, and items
- Added Ammonia Gas Block
- Added Invar Ladder
- Added Mud, Podzol and Mycellium variants for new soil types
- Added Distillation Tower and Air Distillation Packing
- Implemented Borosilicate Glass
- Implemented Ground Tap
- Texture updates
#### --- Changes ---
- Ground Tap: Model exists, Requires a pipe network to a flood gate
- Removed "Wet" blockstate from soils. Soil blocks no longer tick
- Renamed Grassy Soils to Soil Grass Blocks
- Added missing lootables

### ENCHANTMENTS
#### ---Additions---
- Added Enderbiotic, an Ender Amalgam Armor enchantment which teleports the user out of danger when they are about to be hit by a projectile and negates damage, but causes extra damage to durability if corrosion resistance fails
#### --- Changes ---
- Changed Endpoint to an enchantment that can only be applied to Ender Amalgam Spears
- Fixed Ghastly Regeneration and Shape Memory enchantment restrictions

### FLUIDS
- Added Aqua Regia
- Added Hydrobromic Acid
- Added Red Mud

### ITEMS
- Added Lorandite (Thallium ore mineral), Pollucite (Caesium/Rubidium ore mineral), Boron Trioxide, Sodium Fluorosilicate, Iron Chloride, Magnesium Chloride, Hafnia
- Added Buckets for all added fluids
- Added Ammonia Gas Bottle
- Added Bleach
- Added a Meteoric Iron-type item for natural Osmiridium
- Added Silver-Zinc Battery
- Implemented Vanadium Pentoxide, Gwihabaite, Stishovite, and Zirconia 

### RECIPES
#### --- Fusion Furnace Recipes ---
- Added Fusion Furnace JSON recipe type, which takes two item inputs, a gas bottle input, and a fluid input to produce up to two item outputs, a gas bottle output, and a fluid output (all inputs and outputs are optional)
#### ---Additions---
- Added Fusion Furnace recipes for Boron Trioxide, Calcium Chloride, Calcium Silicate, Enstatite, Iron Chloride, Quicklime, Silicon Carbide, Sodium Carbonate, Sodium Hydroxide, Sodium Sulfide, Vanadium Pentoxide
- Added Fusion Furnace "smelting" recipes for Acanthite, Barite, Bauxite, Borax, Boron Trioxide, Calcite, Chalcocite, Cinnabar, Emerald, Galena, Hafnia, Malachite, Molybdenite, Lepidolite, Pentlandite, Potash, Pyrite, Quicklime, Realgar, Sphalerite, Stibnite, Zirconia
- Added Fusion Furnace recipe to produce Aqua Regia, Hydrogen Chloride, Hydrobromic Acid, Sulfuric Acid, and Sulfur Dioxide
- Added Fusion Furnace recipe for Endositum
- Added bone ash to the campfire
- Added recipes for dowsing rod, ground tap, flood gate
#### ---Changes---
- Evaporation recipe no longer requires a "bucket" parameter, changed input to Fluid parameter
- Changed HL2 of Evaporite crushing from Gypsum to Magnesium Chloride
- Moved Spodumene to HL4 and added Pollucite to HL5 for Pegmatite crushing
- Moved Opal to HL4 and added Lorandite to HL5 for Cinnabar Ore crushing
- Added "cooldown" variable to Sluicing JSON, which determines the cooldown given to the item after sluicing
- Sodium Sulfur Battery and Zinc Bromine Battery now use Alumina instead of Bauxite
- Ultra High Refractory Bricks now require Zirconia or Hafnia
- Fixed Sediment Fan recipe
#### ---Removals---
- Removed Indium Tin Oxide crafting recipe
- Removed blasting and beehive oven recipes for Azurite, Barite, Cinnabar, Magnesite, Malachite, and Zircon
### MISC
#### --- Changes ---
- Added more blocks/items to the remapping event
- Increased energy capacity for Magnesium Battery from 64 to 96
- Mud, Podzols and Myceliums added to worldgen
- Added sounds to all fluids
- Changed the formula for calculating the water table height
- Batteries are no longer enchantable or repairable
- Some additions to the journal
#### --- Alloying ---
- Condensed Nickel Superalloy into a single alloy recipe
- Added Enderbiotic to Ender Amalgam as an Enchantment
#### --- Config ---
- Merged configs into one file
- Removed unused config settings (alloy bonus stats, duplicate worldgen parameters)
- Added config for fumarole generation
### FIXES
- Fixed incorrect tagging for Endositum items
- Fixed Armor Stand not working in the Material Testing Table
- Fixed bug where crushing alloy item outputs had mismatched nbt data with alloy items in inventory
- Fixed recipes for tools that used Steel Rods (Nickel/Cobalt Superalloy, Tungsten Heavy Alloy, Stainless Steel, Ender Amalgam)
- Fixed custom alloy components for JEI
- Fixed recipe type for Tree Tap and Glass Cutter
- Fixed alloy tools pointing to the wrong alloy recipes
- Fixed incorrect formula for max-type variables in alloys (harvest level, attack damage, attack speed)
- Added tags for Netherite Nugget and fixed missing crafting


### Alpha 1 [2021-10-18]
### REGISTRY CHANGES
- All *_alloy to *_ingot
- feldspar to orthoclase_feldspar
- feldspar_block to orthoclase_feldspar_block
- meteorite_paver to meteorite_bricks
- enstatite_paver to enstatite_bricks
- uncolored_concrete_* to plain_concrete_*
- pyroxene_gabbro to gabbro
- tufalimestone to limestone
- arkose_sandstone to arkose
- quart_sandstone to itacolumite
- carbonaceous_shale to shale
- ringwoodite to ringwoodine
- wadsleyite to wadsleyone
- perovskite to post-perovskite
- ferropericlase to sommanite
- bridgmandine to bridgmanham
- salt to sodium_chloride
- pozzolan to pozzolana
- kaolinite to kaolin
- All metalloids, reactive nonmetals (that are not gases), and mercury have been changed from *_ingot to only *


### BLOCKS
#### --- Removals ---
- Removed all variants of Pumice and Scoria
- Reduced the variants of Skarn and Breccia to just slab, vertical slab, stairs and walls
- Removed Checkered Dacite and Checkered Porphyry and all variants
- Removed Columbite Ore, Pink Salt Ore, Tantalite Ore, Aquamarine Ore, Majorite Ore, Opal Ore, Vanadinite Ore, Native ALuminum Ore, Native Copper Ore, Moissanite Ore
- Removed Mud (replaced by soil variants)
- Removed Etched Glasses (replaced with other variants - WIP)
- Removed Rope Coil (functionality was migrated to rope block)
- Removed Aluminum Bars, Nickel Bars, and Magnesium Bars (replaced by alloy bars - WIP)
- Removed Tap Barrel, Fluid Drain, Charred Wood, Laser Quarry, Laser Pylon Top, Laser Pylon Base, High Refractory Beehive Oven, Dacitic Tuff, Bamboo Wall and Banboo Culms Wall
#### --- Additions ---
- Added slab, stairs, vertical slab, and wall variant for various blocks without a complete set
- Added Rose Marble, Gray Marble, Serpetinite, Marlstone, Norite, Shonkinite, Pyroxenite, Soul Sandstone, Blueschist, Greenschist, Diabase, Eclogite, Honeystone as stones with all associated variants
- Added Komatiitic Tuff, Kimberlitic Tuff
- Added Clay Loam, Sandy Loam, Silty Loam, Loamy Sand, Sandy Clay Loam, Silty Clay Loam, Sandy Clay, Silty Clay, Loam, Humus as soil varieties
- Added Grassy Soil version of each soil (grass blocks with additional functionality)
- Added Grassy Paths for each Grass Soil
- Added Tilled Soil (farmland block for all soils)
- Added Goldenrod, Red Lily, Orange Lily, White Lily, Purple Morning Glory, Black Morning Glory, Blue Morning Glory as double block flowers
- Added White Clover, Red Clover, Crimson Clover, and Yellow Clover as ground flowers
- Added Green, Brown, Gray, and Black Tektite
- Added Rheniite Ore, Coltan Ore, Chalcocite Ore as Rankine Ores
- Added Rankine variant of the vanilla ores for texture adapting 
- Added Bog Iron, Porphyry Copper, Kimberlitic Diamond Ore
- Added Pedestal variants for Galinstan Alloy and Sodium Potassium Alloy
- Added Frozen Meteorite and Frozen Meteorite Bricks
- Added Meteoric Ice (more slippery than blue ice)
- Added Metal Bars variants for all alloys (WIP)
- Added slab, stairs, vertical slab and wall variants for Fiber Block
- Added Asphalt (full block, 3/4 block, 1/2 block, 1/4 block)
- Added Erythrina as a new tree type with all variants
- Added Petrified Chorus and Charred variants of wooden blocks
- Added Lightning Glass and Fulgurite (obtained from lightning stikes)
- Added Sod Block, Cement, Fire Clay Bricks and Kaolin Bricks with variants
- Added Light Gravel and Dark Gravel
- Added Charcoal Block and Pozzolana as layer blocks
- Added White Sand and Silt
- Added Flood Gate, Metal Pipe, Ground Tap
- Added Pokeberry Bush
- Added Short Grass, Stinging Nettle
- Added Ender Shiro
- Added Stump block
- Added Sulfur Dioxide Fumarole, Carbon Dioxide Fumarole, Hydrogen Chloride Fumarole, Hydrogen Sulfide Fumarole
- Added Sulfur Dioxide Gas Block, Carbon Dioxide Gas Block, Hydrogen Chloride Gas Block, Hydrogen Sulfide Gas Block
#### --- Changes ---
- Ores now drop their ore-related item by default and are affected by the Fortune enchantment (1.17 parity)
- Rankine Ore blocks will take the texture of the clicked block when placed
- Harvest level, hardness and resistance values adjusted for many stones and ores
- Stone Bricks, Planks, and Polished Stones have additional textures (Build Modes - WIP)
- Tree Taps will place fluid in the world using a Flood Gate through a line of Tap Lines
- Charcoal Pit produces Charcoal Blocks instead of Charred Wood
- Beehive Oven has randomized cook times based off how upgraded the structure is
- Berry bush lootables adjusted to match Sweet Berries
- Updated many block textures
- Added flamability to many blocks

### ITEMS
#### ---Removals---
- Removed Metal Scraper (functionality migrated to pumice soap)
- Removed Stone Hammer, Iron Hammer, Diamond Hammer, and Netherite Hammer
- Removed Iron Spear, Diamond Spear and Netherite Spear
- Removed Titanium Crowbar and changed Steel Crowbar to an alloy tool
- Removed Steel Rod, Cast Iron Rod, YAG Rod
- Removed Dry Mortar
- Removed Element Transmuters
#### ---Additions---
- Added Gas Bottles
- Added Cement Mix
- Added Wooden Mallet 
- Added Wooden Gold Pan and Pewter Gold Pan
- Added nuggets for alloys
- Added Alloy Knives to all existing Alloy toolsets
- Added Alloy Crowbars to all existing Alloy toolsets
- Added Rope Coil Arrow, which can deploy rope held in the off-hand
- Minerals: Added bauxite, ringwoodite, wadsleyite, forsterite, mellite, bridgmanite, ferropericlase, apatite, laurite, realgar, gypsum, rutile, chlorite, diopside, azurmalachite, goethite, monazite (Gd), monazite (Y), pyrite, spodumene, cooperite, sanidine, hedenbergite, talc, stishovite, asbestos, serpentine, potash
- Added Kaolinite
- Added Pokeberries
- Added Jams for each of the berries
- Building Tool: hold in offhand to place vertical planks and bricks
#### ---Changes---
- Wear mechanic has been removed from Alloy tools
- Corrosion Resistance and Heat Resistance now works properly on alloy armor
- Herbicide: removed ablity to create coarse dirt, increased default range, prevents Grassy Soils from growing new plants
- Functionality of Solidified Gas Ingots has been transferred over to Gas Bottles and now act as normal ingots
- Right-click functionality has been added to knives in the offhand to Parry, which causes a brief window where damage cannot be taken
- Right-click functionality has been added to crowbars, which can now be used to pull blocks towards the user based on the face of the block that they right-click
- Gold Pans (and other sluicing tools) now have a cooldown on use
- Shulker Gas Vaccuum can now make Gas Bottles if an empty bottle is held in the off-hand
- Shulker Gas Vaccum places Gas Blocks similar to Gas Bottles
- Modified alloy item NBT format significantly
- Items with tooltips now have lang text components
- Updated many item textures


### FLUIDS
- Added Sulfuric Acid

### WORLDGEN
- Reverted under water generation of sand, clay, and gravel pathches to vanilla features
- Improved config options for defining worldgen per biome (wiki page coming soon)
    - Grass / Dirt replacer (replaces 2 additional block of stone underneath grassy areas)
    - Gravel Replacer
    - Stone Layers
    - Intrusions
    - Vegetation growth
- Stone generation changed significantly
    - Default layer count reduced
    - Location of stones changed
    - Vanilla Stone intrusions spawn in all biomes of the Overworld
- Ore generation updated and config options improved (wiki page coming soon)
    - Slightly increased Native Ore generation
    - Balanced other ores around location, vein size, and vein count frequency
- Adhusted meteorite generation in the overworld and added floating meteors in the End
- Fire Clay generates under coal ores
- Kaolin, Bog Iron spawn as ore blobs
- New tall flowers spawn in world
- Fumaroles spawn in world


### ENCHANTMENTS
#### ---Additions---
- Added Backstab, a knife enchantment which deals a multiplier of the current knife's damage when attacking an enemy's back
- Added Grafting, a knife enchantment which causes one sapling to always drop from a leaf block
- Added Poison Aspect, a knife enchantment which inflicts Poison or Weak on hit depending on the type of enemy for a short duration
- Added Preparation, a knife enchantment which increases the window for a successful parry
- Added Retreat, a knife enchantment which turns you invisible for a short duration when you parry damage
- Added Retaliate, a knife enchantment which on parry returns the damage you would've taken to the attacker
- Added Leverage, a crowbar enchantment which increases the damage of the tool based on the size of the target you are attacking
- Added Lift, a crowbar enchantment which allows you to right-click a block in the air to bring the user to the top of the block if there is space
- Added Retrieval, a crowbar enchantment that allows harvesting any block provided its harvest level is less than or equal to the crowbar's harvest level and the block is not in the tag #rankine:crowbar_resistant
- Added Shape Memory


### TAGS
- Many tags switched over to #forge from #rankine
- Added Skarn and Breccia to #forge:cobblestone
- Added #rankine:crowbar_resistant block tag, for blocks which can not be harvested at all by a crowbar (with or without enchantments)
- Added #rankine:sluicing_tools, which allows an item to be used as a "tool" in a sluicing recipe
- Added missing #forge:storage_blocks/*
- Added/updated many other tags. A list will be available on the wiki soon

### RECIPES
####  --- Crafting Recipes ---
- Many recipes changed; notable ones below
- Bricks are 2x2 recipes
- Alloy Furnace uses a campfire instead of a furnace
- Sheetmetals use 3 ingots and 6 nuggets for 8
- Some recipes changed to alloy_crafting to utilize Alloy Components
- Some recipes moved to the Fusion Furnace - WIP
####  --- Crushing Recipes ---
- All ores and stones have had their crushing outputs overhauled
- Ores now have their primary crushing component at Harvest Level 0 (crushing at any level yields the ore's primary mineral)
- Mafic Igneous stones that crushed into Cobblestone now crush into Blackstone
- Sedimentary stones that crushed into Cobblestone now crush into Breccia
- Metamorphic stones that crushed into Cobblestone now crush into Skarn
- Native ores can now be crushed
####  --- Sluicing Recipes ---
- Added an ingredient arguement tool which defines the item that must be right-clicked on a block in order to sluice the block (the item must also have the tag #rankine:sluicing_tools)
- Added sluicing recipes to gravel and soil types
#### --- Alloying Recipes ---
- Removed int argument "required"
- Added boolean argument "required" to any input variable, which defines an ingredient as required to make the output
- Alloy Furnace and Induction Furnace recipes are now separated in JEI
#### --- Alloy Crafting Recipes ---
- Added inheritRecipe, an optional string argument which defines a specific recipe that will be used to define the output's composition and recipe variables
- Added additional arguements to key definitions, alloyComp and alloyRecipe, which define specific compositions and alloy recipes that can be used for that ingredient
#### --- Element Recipes ---
- Added Element JSON recipe type, to now define stats of elements in a more dynamic matter
- Added JEI support
#### --- Rock Generator Recipes ---
- Added Rock Generator JSON recipe type, to define custom generators for Cobblestone, Basalt, Stone, Obsidian, and Sediment Fan generation types
- Contains five "types": intrusive_igneous, extrusive_igneous, sedimentary, metamorphic, volcanic
- Generators no longer produce random rock outputs, each generator uses the following as its default:
    - Intrusive Igneous (cobblestone generator): Cobblestone
    - Extrusive Igneous (basalt generator): Blackstone
    - Sedimentary (sediment fan generator): Breccia
    - Metamorphic (stone generator): Skarn
    - Volcanic (obsidian generator): Obsidian
- Added JEI support


### MISC
#### --- Changes ---
- Journal updates
- Removed unecesary/broken config options and combined files into one
- Added a config to disable infinite water
- Hoeing vanilla blocks will convert them into Tilled Soil rather than farmland
- Vanilla tools are now enabled by default
- Base harvest levels adjusted to accomodate vanilla integration
- Added mixin which changes AnimalEntity spawn conditions to #forge:grass rather than only vanilla grass blocks
#### --- Alloying ---
##### Alloys
- Lead, Zinc, Arsenic, Cadmium, can now be used in Pewter
- Beryllium can now be used in all Bronze recipes
- Potential compositions of White Gold Alloy have been adjusted
- Copper can now be used in White Gold Alloy
- Cadmium and Lithium can now be used in Duralumin Alloy
- Iridium can now be used in Titanium Alloy, Blue Gold Alloy, and White Gold Alloy
- Osmium can now be used in Blue Gold Alloy and White Gold Alloy
- Tellurium can now be used in Bronze, Cupronickel, Cast Iron, Steel, and Stainless Steel Alloy
- Nitinol can be used to craft Titanium Alloy tools
- Alloys now have bonus stats again (which can be modified in Alloying JSON format)
- Element stats are now determined by the Element recipe type instead of being internally defined
##### Elements
- Element enchantments now work by associating a factor per each percent of an element for a specific enchantment, and can now be considered between multiple enchantments
- Elements can now enchant armor
- Netherite now gives Toughness as percentage increases, has rescaled harvest level formula, loses Corrosion Resistance at a quicker rate as percentage increases, and no longer modifies Attack Speed
- Cobalt, Tungsten now provide up to Harvest Level 4
- Carbon now provides toughness at small percentages
- Lithium increases in Heat Resistance as percentage increases (originally decreased), has a rescaled toughness stat, and no longer provides Unbreaking (enchantment)
- Silver, Beryllium, now receive enchantments at 10% or higher
- Nickel durability and mining speed increased at lower percentages, now gives toughness
- Modified Titanium Mining Level formula to be better at smaller percentages
- Bismuth and Lead now grant a harvest level at 12% and 15% respectively
- Boron grants a harvest level at 6%
- Copper now gives heat resistance 
- Aluminum grants harvest level at 10% and 20% and gives attack speed
- Arsenic and Cadmium now can give Poison Aspect to Knives
- Lead and Bismuth now give up to Harvest Level 2 and give corrosion resistance
- Iron now has negative corrosion resistance
- Mercury now reduces harvest level by 1, but has other stats improved
- Molybdenum, Osmium, Iridium now increases harvest level by 1
- Zinc and Calcium now give knockback resistance
- Significantly changed or newly added stats for: Calcium, Cadmium, Arsenic, Palladium, Phosphorus, Zirconium
- All other element formulas simplified and slightly modified


### FIXES
- Fixed crash with water bucket and rope interaction
- Fixed crash relating to the rendering of Bamboo Boat
- Fixed and improved advancement triggers
- Fixed flower seeds infinite use

### COMMUNITY CONTRIBUTIONS
- Fixed Alloy Template crash (Yuesha-yc)
- Added deobf and sources jar artifacts (Yuesha-yc)
