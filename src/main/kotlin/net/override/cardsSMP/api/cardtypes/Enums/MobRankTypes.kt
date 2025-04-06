package net.override.cardsSMP.api.cardtypes.Enums

enum class MobRankTypes(val rank: RankTypes) {
    // Basic mobs
    Zombie(RankTypes.Common),
    Skeleton(RankTypes.Common),
    Creeper(RankTypes.Common),
    Spider(RankTypes.Common),
    Enderman(RankTypes.Common),

    // Stronger mobs
    Blaze(RankTypes.UnCommon),
    Witch(RankTypes.UnCommon),
    Slime(RankTypes.UnCommon),
    MagmaCube(RankTypes.UnCommon),
    Ghast(RankTypes.UnCommon),

    // Tough mobs
    Drowned(RankTypes.Rare),
    Husks(RankTypes.Rare),
    Ravager(RankTypes.Rare),
    Pillager(RankTypes.Rare),
    Vindicator(RankTypes.Rare),
    Evoker(RankTypes.Rare),
    Shulker(RankTypes.Rare),

    // Very strong mobs
    Wither(RankTypes.Special),
    EnderDragon(RankTypes.Special),
}
