package com.spydr6307.client.vault;

public class VaultTracker {

    private static boolean lookingAtVault = false;

    public static boolean isLookingAtVault() {
        return lookingAtVault;
    }

    public static void setLookingAtVault(boolean value) {
        lookingAtVault = value;
    }
}
