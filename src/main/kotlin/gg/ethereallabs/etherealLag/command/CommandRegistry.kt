// CommandRegistry.kt
package gg.ethereallabs.etherealLag.command

import gg.ethereallabs.etherealLag.EtherealLag
import gg.ethereallabs.etherealLag.command.abstract.BaseCommand
import gg.ethereallabs.etherealLag.command.abstract.CommandHandler
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class CommandRegistry : CommandExecutor, TabCompleter {
    private val commands = mutableMapOf<String, CommandHandler>()

    init {
    }

    private fun registerCommand(handler: BaseCommand) {
        commands[handler.name] = handler
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (!sender.hasPermission("blockchess.use")) {
                EtherealLag.instance.sendMessage("<red>You don't have permission to use this command!", sender)
                return true
            }

            if(sender is Player) {

            }
            else{

            }
            return true
        }

        val subCommand = args[0].lowercase()
        val handler = commands[subCommand]

        return handler?.execute(sender, args.copyOfRange(1, args.size)) ?: run {
            EtherealLag.instance.sendMessage("<red>Unknown command! Write /chess help to have a list of all commands.", sender)
            true
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        if (args.isEmpty()) {
            return emptyList()
        }

        if (args.size == 1) {
            return commands.keys.filter { it.startsWith(args[0].lowercase()) }
        }

        val subCommand = args[0].lowercase()
        val handler = commands[subCommand] ?: return emptyList()

        return handler.tabComplete(sender, args.copyOfRange(1, args.size))
    }
}