package stanic.stticket.bukkit.commands.subcmds;

import org.bukkit.entity.Player;
import stanic.stticket.Main;
import stanic.stticket.factory.model.PlayerInfo;
import stanic.stticket.utils.Messages;

public class AvailableCommand {

    private Messages messages = new Messages();

    public void run(Player player) {
        if (!player.hasPermission("stticket.admin")) player.sendMessage(messages.get("notHasPermission"));
        else {
            PlayerInfo playerInfo = Main.instance.playerInfo.get(player.getName());
            playerInfo.setAvailable(!playerInfo.getAvailable());

            player.sendMessage(messages.get("availableChange").replace("{state}", playerInfo.getAvailable() ? Main.instance.getConfig().getString("Config.availableOn") : Main.instance.getConfig().getString("Config.availableOff")));
        }
    }

}