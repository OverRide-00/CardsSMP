package net.override.cardsSMP

import net.override.cardsSMP.internal.commands.GiveCard
import org.bukkit.plugin.java.JavaPlugin

class CardsSMP : JavaPlugin() {

    companion object {
        // The singleton instance
        private lateinit var instance: CardsSMP

        // Accessor method for the singleton instance
        @JvmStatic // Adding @JvmStatic makes the method static in Java
        fun getInstance(): CardsSMP {
            return instance
        }
    }

    override fun onEnable() {
        // Initialize the singleton instance when the plugin is enabled
        instance = this
        // Additional setup code

        // Register the "givecard" command and its executor
        getCommand("givecard")?.setExecutor(GiveCard())
        getCommand("givecard")?.setTabCompleter(GiveCard())
    }

    // Example method for plugin startup logic
    fun onReloadablesStart() {
        // Handle reload logic if needed
    }
}
