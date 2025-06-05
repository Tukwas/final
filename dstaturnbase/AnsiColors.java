package com.bosakon.dstaturnbase;

public class AnsiColors {
    public static final boolean ENABLE = isAnsiSupported();

    public static final String RESET = ENABLE ? "\u001B[0m" : "";
    public static final String CYAN = ENABLE ? "\u001B[36m" : "";
    public static final String BLUE = ENABLE ? "\u001B[34m" : "";
    public static final String YELLOW = ENABLE ? "\u001B[33m" : "";
    public static final String GREEN = ENABLE ? "\u001B[32m" : "";
    public static final String PURPLE = ENABLE ? "\u001B[35m" : "";
    public static final String RED = ENABLE ? "\u001B[31m" : "";
    public static final String WHITE = ENABLE ? "\u001B[37m" : "";
    public static final String BRIGHT_BLACK = ENABLE ? "\u001B[90m" : "";

    // Simple check: On Windows, Java >= 9 enables ANSI colors by default in most terminals
    // You can add more sophisticated detection if needed
    private static boolean isAnsiSupported() {
        String os = System.getProperty("os.name").toLowerCase();
        String env = System.getenv("TERM");
        // If running in Windows terminal, Linux, or Mac terminal, likely supports ANSI
        return !os.contains("win") || (env != null && env.contains("xterm"));
    }
}
