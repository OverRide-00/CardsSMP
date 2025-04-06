package net.override.cardsSMP.api.SC

import net.override.cardsSMP.CardsSMP
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import net.override.cardsSMP.api.FormatUtil.AdventureFormat
import java.util.*

object SCConstructor {

    // Constructing an ItemStack from UUID, skill name, and level
    fun constructItemStack(id: String, skill: String, level: Int): ItemStack {
        val item = ItemStack(Material.PAPER, 1)  // Using PAPER as the base item for the card
        val meta = item.itemMeta ?: return item  // If no meta, return the item as is

        // Set the display name with yellow color and bold text
        meta.setDisplayName(AdventureFormat("<yellow><bold>Skill Card: $skill"))

        // Set lore lines with skill name and level
        meta.lore = listOf(
            AdventureFormat("<yellow>Skill: $skill"),
            AdventureFormat("<gray>Level: $level")
        )

        // Set persistent data with namespace "CardsSMP"
        val plugin = CardsSMP.getInstance()
        meta.persistentDataContainer.set(NamespacedKey(plugin, "type"), PersistentDataType.STRING, "SC")
        meta.persistentDataContainer.set(NamespacedKey(plugin, "id"), PersistentDataType.STRING, id)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "skill"), PersistentDataType.STRING, skill)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "level"), PersistentDataType.INTEGER, level)

        item.itemMeta = meta
        return item
    }

    // Alternative constructor using UUID instead of String
    fun constructItemStack(id: UUID, skill: String, level: Int): ItemStack {
        return constructItemStack(id.toString(), skill, level)
    }
}
