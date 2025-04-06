package net.override.cardsSMP

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
    }

    // Example method for plugin startup logic
    fun onReloadablesStart() {
        // Handle reload logic if needed
    }
}
