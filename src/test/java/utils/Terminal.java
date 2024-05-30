package utils;


public final class Terminal {
    private Terminal() {}

    public static void runCommand(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            Log.fail("Command execution failed: " + e.getMessage());
        }
    }
}