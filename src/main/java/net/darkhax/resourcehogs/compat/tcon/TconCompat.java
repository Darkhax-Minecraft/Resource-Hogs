package net.darkhax.resourcehogs.compat.tcon;

import net.darkhax.resourcehogs.ModConfiguration;
import net.darkhax.resourcehogs.ResourceHogs;
import net.darkhax.resourcehogs.registry.IResourceType;
import net.darkhax.resourcehogs.registry.ResourceRegistry;
import net.minecraft.item.ItemStack;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

public class TconCompat {
    
    public static void handleTconSupport() {
        
        for (final IResourceType type : ResourceRegistry.RESOURCE_TYPES.values()) {
            
            final MeltingRecipe recipe = TinkerRegistry.getMelting(type.getOutput());
            
            if (recipe != null) {
                
                final int basicAmount = recipe.output.amount;
                final int baseTemp = recipe.getTemperature();
                
                if (ModConfiguration.smelteryBacon) {
                	
                    final ItemStack meltBacon = new ItemStack(ResourceHogs.bacon);
                    ResourceHogs.setResource(meltBacon, type);
                    TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.ofNBT(meltBacon, (int) (basicAmount * ModConfiguration.modifierBacon)), recipe.output, baseTemp));
                }
                
                if (ModConfiguration.smelteryTruffle) {
                	
                    final ItemStack meltTruffle = new ItemStack(ResourceHogs.truffle);
                    ResourceHogs.setResource(meltTruffle, type);
                    TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.ofNBT(meltTruffle, (int) (basicAmount * ModConfiguration.modifierTruffle)), recipe.output, baseTemp));    
                }            
            }
        }
    }
}
