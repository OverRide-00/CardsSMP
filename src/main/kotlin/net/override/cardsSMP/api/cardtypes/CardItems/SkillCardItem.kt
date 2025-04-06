package net.override.cardsSMP.api.cardtypes.CardItems

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.CardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import net.override.cardsSMP.api.cardtypes.Constructors.SC.SCConstructor  // Corrected class name
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

class SkillCardItem(id: UUID, val skill: String, val level: Int) : CardItem(id, CardType.SC) {

    constructor(skill: String, level: Int) : this(UUID.randomUUID(), skill, level)

    override fun toItemStack(): ItemStack {
        return SCConstructor.constructItemStack(id, skill, level)  // Corrected class reference here
    }

    companion object {
        // Custom NBT handling for SkillCardItem
        fun fromItemStack(item: ItemStack?): SkillCardItem? {
            if (item == null || !item.hasItemMeta()) return null

            val meta = item.itemMeta ?: return null
            val container = meta.persistentDataContainer

            val idKey = NamespacedKey(getInstance(), "id")
            val skillKey = NamespacedKey(getInstance(), "skill")
            val levelKey = NamespacedKey(getInstance(), "level")

            // Check if the item contains the required NBT tags
            if (!container.has(idKey, PersistentDataType.STRING)) return null
            if (!container.has(skillKey, PersistentDataType.STRING)) return null
            if (!container.has(levelKey, PersistentDataType.INTEGER)) return null

            // Extract the data
            val id = UUID.fromString(container.get(idKey, PersistentDataType.STRING)!!)
            val skill = container.get(skillKey, PersistentDataType.STRING)!!
            val level = container.get(levelKey, PersistentDataType.INTEGER)!!

            return SkillCardItem(id, skill, level)
        }
    }
}
