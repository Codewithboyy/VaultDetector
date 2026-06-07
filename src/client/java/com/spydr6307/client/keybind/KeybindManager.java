package com.spydr6307.client.keybind;

import com.spydr6307.client.config.ModState;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {

    private static KeyBinding toggleKey;

    public static void register() {

        toggleKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.vaultdetector.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
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
        });
    }
}
