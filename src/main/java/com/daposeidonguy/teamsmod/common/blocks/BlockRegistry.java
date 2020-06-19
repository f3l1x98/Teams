package com.daposeidonguy.teamsmod.common.blocks;

import com.daposeidonguy.teamsmod.TeamsMod;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TeamsMod.MODID)
public class BlockRegistry {

    public static BlockTeamChest enderChest;

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(
                enderChest = (BlockTeamChest) new BlockTeamChest().setRegistryName("minecraft", "ender_chest").setUnlocalizedName("ender_chest"));
    }
}
