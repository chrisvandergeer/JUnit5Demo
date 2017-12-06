package nl.cge.utils;

public class Preconditions {

    public static void checkArgument(boolean check) {
        if (!check) {
            throw new IllegalArgumentException();
        }
    }
}
