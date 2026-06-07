package com.spydr6307.client;

import net.fabricmc.api.ClientModInitializer;

public class VaultDetectorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[VaultDetector] Client Loaded!");
    }
}
