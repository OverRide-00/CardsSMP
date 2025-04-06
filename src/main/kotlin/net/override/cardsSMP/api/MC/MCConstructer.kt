package net.override.cardsSMP.api.MC

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.FormatUtil
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*
import java.util.List

object MCConstructer {
    // Constructing an ItemStack from String UUID
    fun constructItemStack(argid: String, rank: String, entityName: String): ItemStack {
        val item = ItemStack(Material.PAPER, 1)
        val meta = item.itemMeta
            ?: return item // If no meta, return the item as is

        // Set the display name
        meta.setDisplayName(FormatUtil.AdventureFormat("<dark_green><bold>($entityName) \uD835\uDD78\uD835\uDD94\uD835\uDD87 \uD835\uDD6E\uD835\uDD86\uD835\uDD97\uD835\uDD89\uD835\uDD98"))

        // Set lore lines
        meta.lore = List.of(
            FormatUtil.AdventureFormat("<dark_green>$entityName \uD835\uDD78\uD835\uDD94\uD835\uDD87 \uD835\uDD6E\uD835\uDD86\uD835\uDD97\uD835\uDD89\uD835\uDD98"),
            FormatUtil.AdventureFormat("<gray>$rank \uD835\uDD97\uD835\uDD86\uD835\uDD93\uD835\uDD90")
        )

        // Set persistent data with namespace "CardsSMP"
        val plugin = getInstance()
        meta.persistentDataContainer.set(NamespacedKey(plugin, "type"), PersistentDataType.STRING, "MC")
        meta.persistentDataContainer.set(NamespacedKey(plugin, "id"), PersistentDataType.STRING, argid)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "rank"), PersistentDataType.STRING, rank)
        meta.persistentDataContainer.set(NamespacedKey(plugin, "entity"), PersistentDataType.STRING, entityName)

        item.setItemMeta(meta)
        return item
    }

    // Alternative constructor using UUID instead of String
    fun constructItemStack(argid: UUID, rank: String, entityName: String): ItemStack {
        return constructItemStack(argid.toString(), rank, entityName)
    }
}
