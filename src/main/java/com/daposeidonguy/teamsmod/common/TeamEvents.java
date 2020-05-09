package com.daposeidonguy.teamsmod.common;

import com.daposeidonguy.teamsmod.TeamsMod;
import com.daposeidonguy.teamsmod.common.config.TeamConfig;
import com.daposeidonguy.teamsmod.common.storage.StorageHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/* Handles events relating to miscellaneous team features */
@Mod.EventBusSubscriber(modid = TeamsMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TeamEvents {

    /* Syncs advancements with player and their team upon login (depending on config) */
    @SubscribeEvent
    public static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getPlayer().getEntityWorld().isRemote && !TeamConfig.disableAdvancementSync && StorageHandler.uuidToTeamMap.containsKey(event.getPlayer().getUniqueID())) {
            String team = StorageHandler.uuidToTeamMap.get(event.getPlayer().getUniqueID());
            if (!StorageHandler.teamSettingsMap.get(team).get("disableAdvancementSync")) {
                StorageHandler.syncPlayers(team, (ServerPlayerEntity) event.getEntity());
            }
        }
    }

    /* Cancels damage and knockback when friendly fire occurs (depending on config) */
    @SubscribeEvent
    public static void playerHitPlayer(final LivingAttackEvent event) {
        if (!event.getEntityLiving().getEntityWorld().isRemote && !TeamConfig.enableFriendlyFire && event.getSource().getTrueSource() instanceof PlayerEntity && event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
            PlayerEntity target = (PlayerEntity) event.getEntityLiving();
            String targetTeam = StorageHandler.uuidToTeamMap.get(target.getUniqueID());
            String attackerTeam = StorageHandler.uuidToTeamMap.get(attacker.getUniqueID());
            if (targetTeam != null && attackerTeam != null && targetTeam.equals(attackerTeam) && !StorageHandler.teamSettingsMap.get(targetTeam).get("enableFriendlyFire")) {
                event.setCanceled(true);
            }
        }
    }

    /* Syncs advancement among teammates when AdvancementEvent fires */
    @SubscribeEvent
    public static void achievementGet(final AdvancementEvent event) {
        if (!TeamConfig.disableAdvancementSync && !event.getEntity().getEntityWorld().isRemote) {
            String team = StorageHandler.uuidToTeamMap.get(event.getPlayer().getUniqueID());
            if (!StorageHandler.teamSettingsMap.get(team).get("disableAdvancementSync")) {
                StorageHandler.syncPlayers(team, (ServerPlayerEntity) event.getPlayer());
            }
        }
    }

    /* Adds prefix to chat message */
    @SubscribeEvent
    public static void onServerChat(final ServerChatEvent event) {
        if (!TeamConfig.disablePrefix) {
            String teamName = StorageHandler.uuidToTeamMap.get(event.getPlayer().getUniqueID());
            if (teamName != null) {
                StringTextComponent prefix = new StringTextComponent("[" + teamName + "] ");
                event.setComponent(prefix.appendSibling(event.getComponent()));
            }
        }
    }

}
