package com.spydr6307;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import com.mojang.brigadier.CommandDispatcher;
import static net.minecraft.server.command.CommandManager.*;

public class VaultDetector implements ModInitializer {
    public static final String MOD_ID = "vaultdetector";

    @Override
    public void onInitialize() {
        System.out.println("[VaultDetector] Initialized!");

        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerCommands(dispatcher);
        });
    }

    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("vaultdetector")
            .executes(context -> {
                context.getSource().sendFeedback(() -> 
                    Text.literal("§a[VaultDetector] Use /vaultdetector scan"), false);
                return 1;
            })
            .then(literal("scan")
                .executes(context -> {
                    context.getSource().sendFeedback(() -> 
                        Text.literal("§6Scanning for nearby vaults..."), false);
                    // TODO: Implement server-side scan if needed
                    return 1;
                }))
        );
    }
}
