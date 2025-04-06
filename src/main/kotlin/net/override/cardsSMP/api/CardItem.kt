package net.override.cardsSMP.api

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.cardtypes.CardItems.ExperienceCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.MobCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.SkillCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.TradeCardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

open class CardItem(val id: UUID, val type: CardType) {

    constructor(type: CardType) : this(UUID.randomUUID(), type)

    open fun toItemStack(): ItemStack? {
        // Base class has no specific behavior, return null
        return null
    }

    companion object {
        // Check if an item is a card item by verifying NBT tags
        fun isCardItem(item: ItemStack?): Boolean {
            if (item == null || !item.hasItemMeta()) return false
            val key = NamespacedKey(getInstance(), "type")
            return item.itemMeta.persistentDataContainer.has(key, PersistentDataType.STRING)
        }

        // Convert an ItemStack to a CardItem based on its NBT data
        fun fromItemStack(item: ItemStack?): CardItem? {
            if (item == null || !item.hasItemMeta()) return null

            val meta = item.itemMeta ?: return null
            val container = meta.persistentDataContainer

            val typeKey = NamespacedKey(getInstance(), "type")
            val idKey = NamespacedKey(getInstance(), "id")

            if (!container.has(typeKey, PersistentDataType.STRING)) return null
            if (!container.has(idKey, PersistentDataType.STRING)) return null

            val type = CardType.valueOf(container.get(typeKey, PersistentDataType.STRING)!!)
            val id = UUID.fromString(container.get(idKey, PersistentDataType.STRING)!!)

            // Delegate to specific card type classes based on the CardType
            return when (type) {
                CardType.MC -> MobCardItem.fromItemStack(item) // Handle MobCardItem specifically
                CardType.SC -> SkillCardItem.fromItemStack(item) // Handle SkillCardItem specifically
                CardType.EC -> ExperienceCardItem.fromItemStack(item) // Handle ExperienceCardItem specifically
                CardType.TC -> TradeCardItem.fromItemStack(item) // Handle TradeCardItem specifically
                else -> CardItem(id, type) // Fallback to base class
            }
        }
    }
}
