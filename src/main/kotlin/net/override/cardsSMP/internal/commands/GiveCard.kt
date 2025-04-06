package net.override.cardsSMP.internal.commands

import net.override.cardsSMP.api.CardItem
import net.override.cardsSMP.api.cardtypes.CardItems.MobCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.SkillCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.ExperienceCardItem
import net.override.cardsSMP.api.cardtypes.CardItems.TradeCardItem
import net.override.cardsSMP.api.cardtypes.Enums.CardType
import net.override.cardsSMP.api.cardtypes.Enums.MobRankTypes
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil
import java.util.*

class GiveCard : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can run this command!")
            return false
        }

        if (args.size < 2) {
            sender.sendMessage("Usage: /givecard <CardType> <Parameter>")
            return false
        }

        val cardType = try {
            CardType.valueOf(args[0].toUpperCase()) // Get the card type (e.g., MC, SC)
        } catch (e: IllegalArgumentException) {
            sender.sendMessage("Invalid card type!")
            return false
        }

        val param = args[1] // The parameter for the card (e.g., Zombie for MobCard)

        val cardItem: CardItem = when (cardType) {
            CardType.MC -> createMobCard(param)
            CardType.SC -> createSkillCard(param)
            CardType.EC -> createExperienceCard(param)
            CardType.TC -> createTradeCard(param)
            // Add other card types handling here if needed
            else -> {
                sender.sendMessage("Card type not implemented.")
                return false
            }
        }

        // Give the card item to the player
        val itemStack = cardItem.toItemStack()
        if (itemStack != null) {
            sender.inventory.addItem(itemStack)
            sender.sendMessage("You have been given a ${cardType.description} card for $param.")
        }

        return true
    }

    // Helper method to create a MobCardItem based on the mob name (param)
    private fun createMobCard(mobName: String): MobCardItem {
        val mobRank = try {
            MobRankTypes.valueOf(mobName.capitalize()) // Get the mob rank from the enum (Zombie -> Common)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Invalid mob name: $mobName")
        }

        // Create the MobCardItem with the correct rank
        val mobCard = MobCardItem(UUID.randomUUID(), mobName, mobRank.rank)
        return mobCard
    }

    // Helper method to create a SkillCardItem based on the skill name and level
    private fun createSkillCard(skillName: String): SkillCardItem {
        val skillCard = SkillCardItem(UUID.randomUUID(), skillName, 1) // Default level to 1 for simplicity
        return skillCard
    }

    // Helper method to create an ExperienceCardItem based on the parameter
    private fun createExperienceCard(density: String): ExperienceCardItem {
        val experienceCard = ExperienceCardItem(UUID.randomUUID(), 10, density) // Default amount to 10 for simplicity
        return experienceCard
    }

    // Helper method to create a TradeCardItem based on the value and rank
    private fun createTradeCard(value: String): TradeCardItem {
        val tradeCard = TradeCardItem(UUID.randomUUID(), value.toInt(), "Common") // Default rank to Common for simplicity
        return tradeCard
    }

    // Implement tab completion for card types and parameters
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<String>): List<String> {
        val results = mutableListOf<String>()

        if (args.size == 1) {
            // Provide tab completion for the card types (e.g., MC, SC, EC, etc.)
            val types = CardType.values().map { it.name }
            StringUtil.copyPartialMatches(args[0], types, results)
        } else if (args.size == 2) {
            // Provide tab completion for card parameters based on the card type
            when (args[0].toUpperCase()) {
                "MC" -> {
                    // Provide tab completion for mobs (you can replace this with actual mob names)
                    val mobs = MobRankTypes.values().map { it.name } // Use MobRankTypes for mob names
                    StringUtil.copyPartialMatches(args[1], mobs, results)
                }
                "SC" -> {
                    // Provide tab completion for skills (you can replace this with actual skills)
                    val skills = listOf("Mining", "Combat", "Farming") // Example skills
                    StringUtil.copyPartialMatches(args[1], skills, results)
                }
                "EC" -> {
                    // Provide tab completion for densities (you can adjust this based on the density values)
                    val densities = listOf("Low", "Medium", "High") // Example densities
                    StringUtil.copyPartialMatches(args[1], densities, results)
                }
                "TC" -> {
                    // Provide tab completion for trade values (you can adjust this based on your values)
                    val values = listOf("1", "10", "100") // Example trade values
                    StringUtil.copyPartialMatches(args[1], values, results)
                }
            }
        }

        return results
    }
}
