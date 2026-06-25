package com.spydr6307;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.spydr6307.client.VaultDetectorClient;
import static net.minecraft.server.command.CommandManager.*;

public class VaultDetector implements ModInitializer {
    public static final String MOD_ID = "vaultdetector";

    @Override
    public void onInitialize() {
        System.out.println("[VaultDetector] Initialized!");

        // Register commands (works in singleplayer too)
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerCommands(dispatcher);
        });
    }

    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("vaultdetector")
            .executes(context -> {
                context.getSource().sendFeedback(() -> 
                    Text.literal("§a[VaultDetector] §7Use /vaultdetector add <item_id>"), false);
                return 1;
            })
            .then(literal("add")
                .then(argument("item", StringArgumentType.string())
                    .executes(context -> {
                        String itemId = StringArgumentType.getString(context, "item");
                        VaultDetectorClient.addTargetItem(itemId);
                        context.getSource().sendFeedback(() -> 
                            Text.literal("§aAdded target: " + itemId), false);
                        return 1;
                    })))
            .then(literal("scan")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> 
                        Text.literal("§6Vault scanning active (client-side)"), false);
                    return 1;
                }))
        );
    }
}
