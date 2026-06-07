package com.spydr6307.client;

import com.spydr6307.client.event.ClientEvents;
import com.spydr6307.client.hud.VaultHud;
import com.spydr6307.client.keybind.KeybindManager;
import net.fabricmc.api.ClientModInitializer;

public class VaultDetectorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeybindManager.register();
        ClientEvents.register();
        VaultHud.register();
    }
}