package pt.starfall.spawners.listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import pt.starfall.spawners.Spawners;

public class SpawnerBreak implements Listener {
    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.SPAWNER & player.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            CreatureSpawner spawner = (CreatureSpawner) block.getState();
            String mobType = spawner.getSpawnedType().name();

            ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
            ItemMeta meta = spawnerItem.getItemMeta();
            meta.getPersistentDataContainer().set(new NamespacedKey(Spawners.getPlugin(), "spawnerType"), PersistentDataType.STRING, mobType);
            spawnerItem.setItemMeta(meta);

            event.setDropItems(false);
            block.getWorld().dropItemNaturally(block.getLocation(), spawnerItem);
        }
    }
}
