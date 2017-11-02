package com.travix.medusa.busyflights.converter;

public enum Suppliers {

    CRAZY_AIR ("CrazyAir"),
    TOUGH_JET ("ToughJet");

    private String supplier;

    Suppliers(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplier() {
        return supplier;
    }
}
