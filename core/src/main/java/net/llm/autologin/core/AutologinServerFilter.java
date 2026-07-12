package net.llm.autologin.core;

import java.util.Locale;

public final class AutologinServerFilter {

    private static final String REQUIRED_MARKER = "holyworld";

    private AutologinServerFilter() {
    }

    public static boolean isAllowed(String serverAddress) {
        if (serverAddress == null || serverAddress.isBlank()) {
            return false;
        }
        return serverAddress.toLowerCase(Locale.ROOT).contains(REQUIRED_MARKER);
    }

    public static String serverKey(String serverAddress) {
        if (serverAddress == null || serverAddress.isBlank()) {
            return "";
        }
        String lower = serverAddress.toLowerCase(Locale.ROOT);
        if (lower.contains(REQUIRED_MARKER)) {
            return REQUIRED_MARKER;
        }
        return lower.trim();
    }

    public static boolean serversMatch(String storedAddress, String currentAddress) {
        if (storedAddress == null || storedAddress.isBlank()) {
            return isAllowed(currentAddress);
        }
        if (!isAllowed(currentAddress)) {
            return false;
        }
        String storedKey = serverKey(storedAddress);
        String currentKey = serverKey(currentAddress);
        return !storedKey.isBlank() && storedKey.equals(currentKey);
    }
}
