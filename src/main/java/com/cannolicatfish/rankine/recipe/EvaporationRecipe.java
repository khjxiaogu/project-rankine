package com.cannolicatfish.rankine.recipe;

import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.recipe.helper.AlloyIngredientHelper;
import com.cannolicatfish.rankine.util.WeightedCollection;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;

import java.util.ArrayList;
import java.util.List;

public class EvaporationRecipe implements IRecipe<IInventory> {

    private final int total;
    private final Ingredient ingredient;
    private final List<String> biomes;
    private final NonNullList<ItemStack> recipeOutputs;
    private final ResourceLocation id;
    private final NonNullList<Float> weights;
    private final NonNullList<Integer> mins;
    private final NonNullList<Integer> maxes;

    public static final EvaporationRecipe.Serializer SERIALIZER = new EvaporationRecipe.Serializer();

    public EvaporationRecipe(ResourceLocation idIn, int totalIn, Ingredient ingredientIn, List<String> biomesIn, NonNullList<ItemStack> recipeOutputsIn, NonNullList<Float> weightsIn, NonNullList<Integer> minsIn, NonNullList<Integer> maxesIn) {
        this.total = totalIn;
        this.id = idIn;
        this.ingredient = ingredientIn;
        this.biomes = biomesIn;
        this.recipeOutputs = recipeOutputsIn;
        this.weights = weightsIn;
        this.mins = minsIn;
        this.maxes = maxesIn;
    }


    public String getGroup() {
        return "";
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.withSize(1,this.ingredient);
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public List<String> getBiomes() {
        return this.biomes;
    }

    public Float getChance(int index) {
        float in = getWeights().get(index);
        return (in/getWeights().stream().reduce(0f, Float::sum));
    }

    public NonNullList<Float> getWeights() {
        return weights;
    }

    public NonNullList<ItemStack> getOutputs() {
        return this.recipeOutputs;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.getIngredient().test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public ItemStack getEvaporationResult(World worldIn) {
        WeightedCollection<ItemStack> col = new WeightedCollection<>();
        for (int i = 0; i < this.recipeOutputs.size(); i++) {
            col.add(this.weights.get(i),new ItemStack(this.recipeOutputs.get(i).getItem(), this.maxes.get(i).equals(this.mins.get(i)) ? this.maxes.get(i) : worldIn.getRandom().nextInt(this.maxes.get(i) - this.mins.get(i)) + this.mins.get(i)));
        }
        return col.getRandomElement().copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static ItemStack deserializeItem(JsonObject object) {
        String s = JSONUtils.getString(object, "item");
        Item item = Registry.ITEM.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
            return new JsonSyntaxException("Unknown item '" + s + "'");
        });

        if (object.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            int i = JSONUtils.getInt(object, "count", 1);
            return AlloyIngredientHelper.getItemStack(object, true);
        }
    }

    @Override
    public IRecipeType<?> getType() {
        return RankineRecipeTypes.EVAPORATION;
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<EvaporationRecipe> {
        private static final ResourceLocation NAME = new ResourceLocation("rankine", "evaporation");
        public EvaporationRecipe read(ResourceLocation recipeId, JsonObject json) {
            int t = json.get("total").getAsInt();
            Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

            JsonArray b = JSONUtils.getJsonArray(json, "biomes");
            List<String> biomes = new ArrayList<String>();
            for (int i = 0; i < b.size(); i++) {
                biomes.add(b.get(i).getAsString());
            }
            NonNullList<ItemStack> stacks = NonNullList.withSize(t, ItemStack.EMPTY);
            NonNullList<Float> weights = NonNullList.withSize(t, 0f);
            NonNullList<Integer> mins = NonNullList.withSize(t, 1);
            NonNullList<Integer> maxes = NonNullList.withSize(t, 1);
            for (int i = 0; i < t; i++) {
                String output = "output" + (i+1);
                if (json.has(output)) {
                    JsonObject object = JSONUtils.getJsonObject(json, output);
                    stacks.set(i, EvaporationRecipe.deserializeItem(object));
                    if (object.has("weight")){
                        weights.set(i,object.get("weight").getAsFloat());
                    } else {
                        weights.set(i,0f);
                    }

                    if (object.has("min")){
                        mins.set(i,object.get("min").getAsInt());
                    } else {
                        mins.set(i,1);
                    }

                    if (object.has("max")){
                        maxes.set(i,object.get("max").getAsInt());
                    } else {
                        maxes.set(i,1);
                    }


                }
            }

            return new EvaporationRecipe(recipeId, t, ingredient, biomes, stacks, weights, mins,maxes);
        }

        public EvaporationRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            List<String> biomes = new ArrayList<String>();

            int t = buffer.readInt();
            Ingredient input = Ingredient.read(buffer);

            int whatever = buffer.readInt();
            for (int i = 0; i < whatever; i++) {
                biomes.add(i, buffer.readString());
            }

            NonNullList<ItemStack> stacks = NonNullList.withSize(t, ItemStack.EMPTY);
            for(int k = 0; k < stacks.size(); ++k) {
                stacks.set(k, buffer.readItemStack());
            }

            NonNullList<Float> weights = NonNullList.withSize(t, 0f);
            for(int k = 0; k < weights.size(); ++k) {
                weights.set(k, buffer.readFloat());
            }

            NonNullList<Integer> mins = NonNullList.withSize(t,1);
            for(int k = 0; k < mins.size(); ++k) {
                mins.set(k, buffer.readInt());
            }

            NonNullList<Integer> maxes = NonNullList.withSize(t,1);
            for(int k = 0; k < maxes.size(); ++k) {
                maxes.set(k, buffer.readInt());
            }

            return new EvaporationRecipe(recipeId, t, input, biomes, stacks, weights, mins, maxes);
        }

        public void write(PacketBuffer buffer, EvaporationRecipe recipe) {
            buffer.writeInt(recipe.total);
            recipe.getIngredient().write(buffer);

            buffer.writeInt(recipe.getBiomes().size());
            for (int i = 0; i < recipe.getBiomes().size(); i++) {
                buffer.writeString(recipe.getBiomes().get(i));
            }

            int count = 0;
            for(ItemStack stack : recipe.recipeOutputs) {
                buffer.writeItemStack(stack);
                count++;
            }
            while (count < recipe.total) {
                buffer.writeItemStack(ItemStack.EMPTY);
                count++;
            }

            count = 0;
            for (float chance : recipe.weights) {
                buffer.writeFloat(chance);
                count++;
            }
            while (count < recipe.total) {
                buffer.writeFloat(0f);
                count++;
            }

            count = 0;
            for (int add : recipe.mins) {
                buffer.writeInt(add);
                count++;
            }
            while (count < recipe.total) {
                buffer.writeInt(1);
                count++;
            }

            count = 0;
            for (int add : recipe.maxes) {
                buffer.writeInt(add);
                count++;
            }
            while (count < recipe.total) {
                buffer.writeInt(1);
                count++;
            }
        }
    }

}
