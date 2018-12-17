package packet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Handlers implements Listener {

    @EventHandler
    public  void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addEnchantment(new TestEnchantment(101), 1);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.AQUA + "INFO SWORD IN YOUR INVENTORY");

    }
}
