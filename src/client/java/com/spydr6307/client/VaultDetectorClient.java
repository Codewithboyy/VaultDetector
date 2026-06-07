package com.spydr6307.client;

import com.spydr6307.client.event.ClientEvents;
import net.fabricmc.api.ClientModInitializer;

public class VaultDetectorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientEvents.register();
    }
}