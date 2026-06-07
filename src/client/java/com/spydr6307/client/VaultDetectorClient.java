package com.spydr6307.client;

import net.fabricmc.api.ClientModInitializer;
import com.spydr6307.client.event.ClientEvents;

public class VaultDetectorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientEvents.register()
        System.out.println("[VaultDetector] Client Loaded!");
    }
}
