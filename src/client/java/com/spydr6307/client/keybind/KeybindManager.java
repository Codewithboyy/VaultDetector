package com.spydr6307.client.keybind;

import com.spydr6307.client.config.ModState;
import com.spydr6307.client.vault.VaultMemory;
import com.spydr6307.client.vault.VaultSelector;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    private static KeyBinding toggleKey;
    private static KeyBinding clearVaultsKey;
    private static KeyBinding nextVaultKey;
    private static KeyBinding previousVaultKey;

    public static void register() {

        toggleKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultdetector.toggle",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        KeyBinding.Category.MISC
                )
        );

        clearVaultsKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultdetector.clear",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_DELETE,
                        KeyBinding.Category.MISC
                )
        );

        nextVaultKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultdetector.next",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_N,
                        KeyBinding.Category.MISC
                )
        );

        previousVaultKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.vaultdetector.previous",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        KeyBinding.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (toggleKey.wasPressed()) {

                ModState.toggle();

                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal(
                                    "VaultDetector: " +
                                    (ModState.isEnabled() ? "ON" : "OFF")
                            ),
                            true
                    );
                }
            }

            while (clearVaultsKey.wasPressed()) {

                VaultMemory.clear();
                VaultSelector.reset();

                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal("All tracked vaults cleared."),
                            true
                    );
                }
            }

            while (nextVaultKey.wasPressed()) {

                VaultSelector.next();

                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal(
                                    "Selected Vault: " +
                                    (VaultSelector.getSelectedIndex() + 1) +
                                    "/" +
                                    VaultSelector.getTotalVaults()
                            ),
                            true
                    );
                }
            }

            while (previousVaultKey.wasPressed()) {

                VaultSelector.previous();

                if (client.player != null) {
                    client.player.sendMessage(
                            Text.literal(
                                    "Selected Vault: " +
                                    (VaultSelector.getSelectedIndex() + 1) +
                                    "/" +
                                    VaultSelector.getTotalVaults()
                            ),
                            true
                    );
                }
            }
        });
    }
}