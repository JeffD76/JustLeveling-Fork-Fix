package com.seniors.justlevelingfork.client.core;

import com.seniors.justlevelingfork.common.capability.AptitudeCapability;
import com.seniors.justlevelingfork.client.gui.OverlayAptitudeGui;
import com.seniors.justlevelingfork.client.gui.OverlayTitleGui;
import com.seniors.justlevelingfork.handler.HandlerConfigClient;
import com.seniors.justlevelingfork.registry.RegistryTitles;
import com.seniors.justlevelingfork.registry.title.Title;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

public final class ClientHooks {
    private ClientHooks() {
    }

    public static AptitudeCapability getClientAptitudeCapability() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return null;
        }
        return AptitudeCapability.get(player);
    }

    public static boolean hasClientPlayer() {
        return Minecraft.getInstance().player != null;
    }

    public static void showAptitudeWarning(String resource) {
        OverlayAptitudeGui.showWarning(resource);
    }

    public static void showTitleWarning(String titleName) {
        Title title = RegistryTitles.getTitle(titleName);
        OverlayTitleGui.list.enqueue(title);
        OverlayTitleGui.showWarning();
    }

    public static void displayPlayerMessage(String message, int amount) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        if (message.equals("overlay.skill.justlevelingfork.lucky_drop") && HandlerConfigClient.showLuckyDropSkillOverlay.get()) {
            player.displayClientMessage(Component.translatable(message, amount), true);
        } else if ((message.equals("overlay.skill.justlevelingfork.critical_roll_1") || message.equals("overlay.skill.justlevelingfork.critical_roll_6")) && HandlerConfigClient.showCriticalRollSkillOverlay.get()) {
            player.displayClientMessage(Component.translatable(message, amount), true);
        }
    }
}
