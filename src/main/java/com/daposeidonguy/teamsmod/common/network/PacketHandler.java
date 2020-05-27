package com.daposeidonguy.teamsmod.common.network;

import com.daposeidonguy.teamsmod.TeamsMod;
import com.daposeidonguy.teamsmod.common.network.messages.*;
import com.daposeidonguy.teamsmod.common.storage.StorageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.UUID;

/* Registers network messages */
public class PacketHandler {

    static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(TeamsMod.MODID);

    public static void register(Side side) {
        int id = 0;

        INSTANCE.registerMessage(MessageDeath.MessageHandler.class, MessageDeath.class, ++id, side);
        INSTANCE.registerMessage(MessageGuiTransfer.MessageHandler.class, MessageGuiTransfer.class, ++id, side);
        INSTANCE.registerMessage(MessageHealth.MessageHandler.class, MessageHealth.class, ++id, side);
        INSTANCE.registerMessage(MessageHunger.MessageHandler.class, MessageHunger.class, ++id, side);
        INSTANCE.registerMessage(MessageSaveData.MessageHandler.class, MessageSaveData.class, ++id, side);
        INSTANCE.registerMessage(MessageInvite.MessageHandler.class, MessageInvite.class, ++id, side);
        INSTANCE.registerMessage(MessageNewChat.MessageHandler.class, MessageNewChat.class, ++id, side);
        INSTANCE.registerMessage(MessageTeamChat.MessageHandler.class, MessageTeamChat.class, ++id, side);
        INSTANCE.registerMessage(MessageConfig.MessageHandler.class, MessageConfig.class, ++id, side);
        INSTANCE.registerMessage(MessagePos.MessageHandler.class, MessagePos.class, ++id, side);
    }

}
