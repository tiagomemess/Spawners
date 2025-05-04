package pt.starfall.spawners.listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import pt.starfall.spawners.Spawners;

public class SpawnerPlace implements Listener {
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        if (block.getType() == Material.SPAWNER) {
            ItemStack spawnerItem = event.getItemInHand();
            ItemMeta meta = spawnerItem.getItemMeta();

            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(Spawners.getPlugin(), "spawnerType"), PersistentDataType.STRING)) {
                String mobType = meta.getPersistentDataContainer().get(new NamespacedKey(Spawners.getPlugin(), "spawnerType"), PersistentDataType.STRING);
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                spawner.setSpawnedType(EntityType.valueOf(mobType));
                spawner.update();
            }
        }
    }
}
