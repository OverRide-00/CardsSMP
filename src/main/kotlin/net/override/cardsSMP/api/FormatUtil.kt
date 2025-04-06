package net.override.cardsSMP.api

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object FormatUtil {
    fun AdventureFormat(input: String): String {
        val component = MiniMessage.miniMessage().deserialize(input)
        return LegacyComponentSerializer.legacySection().serialize(component)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val raw = "<bold><gold>Hello</gold> <gray>World</gray>"
        val formatted = AdventureFormat(raw)
        println(formatted) // Will print §l§6Hello §7World
    }
}
