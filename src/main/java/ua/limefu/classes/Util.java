package ua.limefu.classes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class Util {

    public static ItemStack createNamedItem(Material material, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createEnchantItem(Material material, Enchantment enchantment, int lvl) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.addEnchantment(enchantment, lvl);
        return itemStack;
    }


}
