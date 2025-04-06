package net.override.cardsSMP.api.cardtypes.Constructors.TC

import net.override.cardsSMP.CardsSMP
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

object TCConstructor {

    // Constructing an ItemStack for TradeCardItem
    fun constructItemStack(id: UUID, value: Int, rank: String): ItemStack {
        val item = ItemStack(Material.PAPER, 1)
        val meta = item.itemMeta ?: return item // If no meta, return item as is

        // Set the display name (you can customize this as needed)
        meta.setDisplayName("Trade Card - $rank")

        // Set the lore lines (you can adjust this as per your needs)
        meta.lore = listOf("Value: $value", "Type: $rank")

        // Set persistent data with namespace "CardsSMP"
        val plugin = CardsSMP.getInstance()
        meta.persistentDataContainer.set(NamespacedKey(plugin, "type"), PersistentDataType.STRING, "TC")
        meta.persistentDataContainer.set(NamespacedKey(plugin, "id"), PersistentDataType.STRING, id.toString())
        meta.persistentDataContainer.set(NamespacedKey(plugin, "value"), PersistentDataType.INTEGER, value)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "type"), PersistentDataType.STRING, rank)

        item.itemMeta = meta
        return item
    }
}
