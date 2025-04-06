package net.override.cardsSMP.api.cardtypes.CardItems

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.CardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import net.override.cardsSMP.api.cardtypes.Constructors.MC.MCConstructer
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*


class MobCardItem(id: UUID, val rank: String, val entityName: String) : CardItem(id, CardType.MC) {

    constructor(rank: String, entityName: String) : this(UUID.randomUUID(), rank, entityName)

    override fun toItemStack(): ItemStack {
        return MCConstructer.constructItemStack(id, rank, entityName)
    }

    companion object {
        fun fromItemStack(item: ItemStack?): MobCardItem? {
            if (item == null || !item.hasItemMeta()) return null

            val meta = item.itemMeta ?: return null
            val container = meta.persistentDataContainer

            val idKey = NamespacedKey(getInstance(), "id")
            val rankKey = NamespacedKey(getInstance(), "rank")
            val entityKey = NamespacedKey(getInstance(), "entity")

            if (!container.has(idKey, PersistentDataType.STRING)) return null
            if (!container.has(rankKey, PersistentDataType.STRING)) return null
            if (!container.has(entityKey, PersistentDataType.STRING)) return null

            val id = UUID.fromString(container.get(idKey, PersistentDataType.STRING)!!)
            val rank = container.get(rankKey, PersistentDataType.STRING)!!
            val entity = container.get(entityKey, PersistentDataType.STRING)!!

            return MobCardItem(id, rank, entity)
        }
    }
}
