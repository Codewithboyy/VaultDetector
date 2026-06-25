package com.spydr6307.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.VaultBlockEntity;  // Corrected import
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class VaultDetectorClient implements ClientModInitializer {
    private static final int MAX_RAYCAST_DISTANCE = 6;
    private static boolean featureEnabled = true;
    private static final Set<String> TARGET_ITEMS = new HashSet<>();

    private static KeyBinding toggleKey;

    static {
        TARGET_ITEMS.add("minecraft:heavy_core");
    }

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.vaultdetector.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.vaultdetector.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(this::onClientTick);

        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            // Placeholder for future highlights
        });
    }

    private void onClientTick(MinecraftClient client) {
        if (client.world == null || client.player == null || !featureEnabled) return;

        while (toggleKey.wasPressed()) {
            featureEnabled = !featureEnabled;
            client.player.sendMessage(Text.literal("§6[VaultDetector] " + (featureEnabled ? "§aENABLED" : "§cDISABLED")), false);
        }

        if (client.player.age % 2 != 0) return;

        HitResult hit = client.crosshairTarget;
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockPos pos = blockHit.getBlockPos();

        BlockEntity blockEntity = client.world.getBlockEntity(pos);
        if (!(blockEntity instanceof VaultBlockEntity vaultBE)) return;

        ItemStack displayItem = vaultBE.getClientData().getDisplayItem();
        if (displayItem.isEmpty()) return;

        String itemId = Registries.ITEM.getId(displayItem.getItem()).toString();

        if (TARGET_ITEMS.contains(itemId)) {
            client.player.sendMessage(Text.literal("§a[VaultDetector] Target detected! Interacting..."), false);
            if (client.interactionManager != null) {
                client.doItemUse();
            }
        }
    }

    public static void addTargetItem(String itemId) {
        TARGET_ITEMS.add(itemId);
    }
}