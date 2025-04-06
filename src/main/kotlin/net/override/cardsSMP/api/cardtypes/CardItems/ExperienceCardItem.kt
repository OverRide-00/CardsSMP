package net.override.cardsSMP.api.cardtypes.CardItems

import net.override.cardsSMP.CardsSMP.Companion.getInstance
import net.override.cardsSMP.api.CardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import net.override.cardsSMP.api.cardtypes.Constructors.EC.ECConstructor
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import java.util.*

class ExperienceCardItem(id: UUID, val amount: Int, val density: String) : CardItem(id, CardType.EC) {

    constructor(amount: Int, density: String) : this(UUID.randomUUID(), amount, density)

    // Convert the card item to an ItemStack
    override fun toItemStack(): ItemStack {
        return ECConstructor.constructItemStack(id, amount, density)
    }

    companion object {
        // Custom NBT handling for ExperienceCardItem
        fun fromItemStack(item: ItemStack?): ExperienceCardItem? {
            if (item == null || !item.hasItemMeta()) return null

            val meta = item.itemMeta ?: return null
            val container = meta.persistentDataContainer

            val idKey = NamespacedKey(getInstance(), "id")
            val amountKey = NamespacedKey(getInstance(), "amount")
            val densityKey = NamespacedKey(getInstance(), "density")

            // Check if the item contains the required NBT tags
            if (!container.has(idKey, PersistentDataType.STRING)) return null
            if (!container.has(amountKey, PersistentDataType.INTEGER)) return null
            if (!container.has(densityKey, PersistentDataType.STRING)) return null

            // Extract the data
            val id = UUID.fromString(container.get(idKey, PersistentDataType.STRING)!!)
            val amount = container.get(amountKey, PersistentDataType.INTEGER)!!
            val density = container.get(densityKey, PersistentDataType.STRING)!!

            return ExperienceCardItem(id, amount, density)
        }
    }
}
