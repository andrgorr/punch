package pw.javipepe.idpunch;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pw.javipepe.idpunch.commands.IDPCmd;
import pw.javipepe.idpunch.listeners.ClickWithToolListener;

/**
 * @author Javi
 */
public class IDPunch extends JavaPlugin {

    /**
     * This plugin contains a command by which you get a tool that will enable you
     * to get the block you are looking at's id in chat.
     *
     */

    private CommandsManager<CommandSender> commands;
    public static String PLUGIN_NAME = "IDPunch";
    public static Material TOOL_MATERIAL = Material.VINE;


    public void onEnable() {
        listeners();
        commands();
    }

    /**
     * listeners() registers the click listener
     */
    private void listeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new ClickWithToolListener(), this);
    }


    /**
     * commands() registers the /ipd command to obtain the tool
     */
    private void commands() {
        this.commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender sender, String perm) {
                return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
            }
        };
        CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);

        cmdRegister.register(IDPCmd.class);
    }

    /**
     * sq command framework core
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform that command.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "Number expected, text received instead.");
            } else {
                sender.sendMessage(ChatColor.RED + "There was an internal error while performing this command.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }
}
