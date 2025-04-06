package net.override.cardsSMP.api.cardtypes

import net.override.cardsSMP.CardsSMP
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*

object ECConstructor {

    // Constructing an ItemStack for ExperienceCardItem
    fun constructItemStack(id: UUID, amount: Int, density: String): ItemStack {
        val item = ItemStack(Material.PAPER, 1)
        val meta = item.itemMeta ?: return item // If no meta, return item as is

        // Set the display name (just an example, adjust the format as needed)
        meta.setDisplayName("Experience Card - $density")

        // Set the lore lines (you can adjust this as per your needs)
        meta.lore = listOf("Amount: $amount", "Density: $density")

        // Set persistent data with namespace "CardsSMP"
        val plugin = CardsSMP.getInstance()
        meta.persistentDataContainer.set(NamespacedKey(plugin, "type"), PersistentDataType.STRING, "EC")
        meta.persistentDataContainer.set(NamespacedKey(plugin, "id"), PersistentDataType.STRING, id.toString())
        meta.persistentDataContainer.set(NamespacedKey(plugin, "amount"), PersistentDataType.INTEGER, amount)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "density"), PersistentDataType.STRING, density)

        item.itemMeta = meta
        return item
    }
}
