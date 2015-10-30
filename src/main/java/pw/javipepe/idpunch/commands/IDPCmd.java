package pw.javipepe.idpunch.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.javipepe.idpunch.IDPunch;

/**
 * The command that will grant you the tool.
 */

public class IDPCmd {

    @Command(aliases = {"idpunch", "idp"}, usage = "/idp", desc = "Execute to receive your vine", max = 0)
    @CommandPermissions("idp.command")
    public static void idp(final CommandContext cmd, final CommandSender sender) throws CommandException {

        if (sender instanceof Player) {
            Player s = (Player) sender;

            s.getInventory().addItem(new ItemStack(IDPunch.TOOL_MATERIAL));
            s.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + IDPunch.PLUGIN_NAME + ChatColor.RESET + "  " + ChatColor.GOLD + "You were successfully granted a " + ChatColor.GREEN + IDPunch.TOOL_MATERIAL.toString().toLowerCase());

        }else throw new CommandException("Console cannot use /idp");
    }
}
