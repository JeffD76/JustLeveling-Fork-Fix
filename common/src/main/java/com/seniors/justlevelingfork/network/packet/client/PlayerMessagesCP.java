package com.seniors.justlevelingfork.network.packet.client;

import com.seniors.justlevelingfork.network.packet.JustLevelingPacket;

import com.seniors.justlevelingfork.client.core.ClientHooks;
import com.seniors.justlevelingfork.network.ServerNetworking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;


public class PlayerMessagesCP implements JustLevelingPacket {
    private final String message;
    private final int amount;

    public PlayerMessagesCP(String skill, int amount) {
        this.message = skill;
        this.amount = amount;
    }

    public PlayerMessagesCP(FriendlyByteBuf buffer) {
        this.message = buffer.readUtf();
        this.amount = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeUtf(this.message);
        buffer.writeInt(this.amount);
    }

    public void handle(ServerPlayer sender) {
            ClientHooks.displayPlayerMessage(this.message, this.amount);
    }

    public static void send(Player player, String message, int amount) {
        ServerNetworking.sendToPlayer(new PlayerMessagesCP(message, amount), (ServerPlayer) player);
    }
}


