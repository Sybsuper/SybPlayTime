package me.sybsuper.SybPlayTime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
	}
	public int f(float num) {
		return (int) Math.floor(num);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("sybplaytime.get")) {
			Player p = null;
			if (args.length > 0) {
				p = Bukkit.getServer().getPlayer(args[0]);
			} else {
				if (sender instanceof Player) {
					p = (Player) sender;
				} else {
					sender.sendMessage(ChatColor.RED + "Only players can execute the command on themselves.");
					return true;
				}
			}
			if (p != null) {
				if (sender.hasPermission("sybplaytime.get.others")) {
					float ticks = p.getStatistic(Statistic.PLAY_ONE_TICK);
					float seconds = ticks / 20;
					float minutes = seconds / 60;
					float hours = minutes / 60;
					float days = hours / 24;
					String timeString = f(seconds) + "s";
					if (days > 1) {
						timeString = f(days) + "d " + f(hours % 24) + "h " + f(minutes % 60) +"m " + f(seconds % 60) + "s";
					} else if (hours > 1) {
						timeString = f(hours) + "h " + f(minutes % 60) +"m " + f(seconds % 60) + "s";
					} else if (minutes > 1) {
						timeString = f(minutes) + "m " + f(seconds % 60) + "s";
					}
					sender.sendMessage(p.getName() + "'s playtime is: " + ChatColor.BOLD + timeString);
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission to see other players playtime.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Player not found.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
		}
		return true;
	}
}
