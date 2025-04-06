enum class RankTypes(val description: String, val rarityValue: Int) {
    Common("A basic card", 1),
    UnCommon("A slightly rarer card", 2),
    Rare("A rare and sought-after card", 3),
    Epic("An epic card with special abilities", 4),
    Mystic("A mystical card with powerful effects", 5),
    Legendary("A legendary card, very rare and powerful", 6),
    Collectors("A rare collectible card", 7),
    Partner("A special partner card", 8),
    Special("A unique special card", 9),
}
