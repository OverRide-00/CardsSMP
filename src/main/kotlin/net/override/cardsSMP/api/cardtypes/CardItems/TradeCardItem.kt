package net.override.cardsSMP.api.cardtypes.CardItems

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.CardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import net.override.cardsSMP.api.cardtypes.Constructors.TC.TCConstructor
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

class TradeCardItem(id: UUID, val value: Int, val rank: String) : CardItem(id, CardType.TC) {

    constructor(value: Int, type: String) : this(UUID.randomUUID(), value, type)

    // Convert the card item to an ItemStack
    override fun toItemStack(): ItemStack {
        return TCConstructor.constructItemStack(id, value, rank)
    }

    companion object {
        // Custom NBT handling for TradeCardItem
        fun fromItemStack(item: ItemStack?): TradeCardItem? {
            if (item == null || !item.hasItemMeta()) return null

            val meta = item.itemMeta ?: return null
            val container = meta.persistentDataContainer

            val idKey = NamespacedKey(getInstance(), "id")
            val valueKey = NamespacedKey(getInstance(), "value")
            val typeKey = NamespacedKey(getInstance(), "type")

            // Check if the item contains the required NBT tags
            if (!container.has(idKey, PersistentDataType.STRING)) return null
            if (!container.has(valueKey, PersistentDataType.INTEGER)) return null
            if (!container.has(typeKey, PersistentDataType.STRING)) return null

            // Extract the data
            val id = UUID.fromString(container.get(idKey, PersistentDataType.STRING)!!)
            val value = container.get(valueKey, PersistentDataType.INTEGER)!!
            val type = container.get(typeKey, PersistentDataType.STRING)!!

            return TradeCardItem(id, value, type)
        }
    }
}
