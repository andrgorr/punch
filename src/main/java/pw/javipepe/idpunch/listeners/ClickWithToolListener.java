package pw.javipepe.idpunch.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pw.javipepe.idpunch.IDPunch;

/**
 * Listens for PlayerInteractEvent and checks for the item in hand.
 */
public class ClickWithToolListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getPlayer().getItemInHand().getType() == IDPunch.TOOL_MATERIAL) {
            Action a = event.getAction();
            boolean isPhysic = a == Action.LEFT_CLICK_BLOCK || a == Action.RIGHT_CLICK_BLOCK;

            if(isPhysic) {
                String mname = event.getClickedBlock().getType().toString().toLowerCase() + ":" + event.getClickedBlock().getData();
                event.getPlayer().sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + IDPunch.PLUGIN_NAME + ChatColor.RESET + "  " + mname.replace(":", ChatColor.RED + "" + ChatColor.BOLD + ":" + ChatColor.RESET));
            } else {
                String mname = event.getPlayer().getTargetBlock(null, 100).getType().toString().toLowerCase() + ":" + event.getPlayer().getTargetBlock(null, 100).getData();
                event.getPlayer().sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + IDPunch.PLUGIN_NAME + ChatColor.RESET + "  " + mname.replace(":", ChatColor.RED + "" + ChatColor.BOLD + ":" + ChatColor.RESET));
            }
        }
    }
}
