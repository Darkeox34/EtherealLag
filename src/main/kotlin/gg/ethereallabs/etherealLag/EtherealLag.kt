package gg.ethereallabs.etherealLag

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class EtherealLag : JavaPlugin() {

    companion object{
        lateinit var instance : EtherealLag
    }

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
    }

    override fun onDisable() {
    }

    fun sendMessage(message: String, vararg senders: CommandSender?) {
        for (sender in senders) {
            sender?.sendMessage("§dEtherealLag §3> $message")
        }
    }
}
