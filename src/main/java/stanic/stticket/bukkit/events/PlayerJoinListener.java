package stanic.stticket.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import stanic.stticket.Main;
import stanic.stticket.factory.model.PlayerInfo;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Main.instance.notify.containsKey(player.getName())) {
            player.sendMessage(Main.instance.notify.get(player.getName()));
            Main.instance.notify.remove(player.getName());
        }

        if (!Main.instance.staff.containsKey(player.getName()) && player.hasPermission("stticket.admin")) {
            PlayerInfo playerInfo = new PlayerInfo(player.getName(), 0, true);
            Main.instance.playerInfo.put(player.getName(), playerInfo);
        }
    }

}