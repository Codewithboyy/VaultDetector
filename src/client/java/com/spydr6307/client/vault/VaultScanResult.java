package com.spydr6307.client.vault;

public class VaultScanResult {

    private final boolean vaultFound;
    private final String blockEntityName;

    public VaultScanResult(boolean vaultFound, String blockEntityName) {
        this.vaultFound = vaultFound;
        this.blockEntityName = blockEntityName;
    }

    public boolean isVaultFound() {
        return vaultFound;
    }

    public String getBlockEntityName() {
        return blockEntityName;
    }
}
