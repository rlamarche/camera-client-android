package com.rlamarche.cameraclient;

/**
 * Created by romain on 18/12/16.
 */

public enum CaptureMode {
    PHOTO(0),
    VIDEO(1);

    private final int value;

    CaptureMode(int value) {
        this.value = value;
    }

    public static CaptureMode fromValue(int value) {
        for (CaptureMode captureMode : CaptureMode.values()) {
            if (captureMode.value == value) {
                return captureMode;
            }
        }
        return null;
    }
}
