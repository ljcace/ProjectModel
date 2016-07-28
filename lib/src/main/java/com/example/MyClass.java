package com.example;

public class MyClass {
    static float ppi_320_480 = 1.0f;
    static float ppi_480_800 = 1.5f;
    static float ppi_720_1280 = 2.0f;
    static float ppi_1080_1920 = 3.0f;
    static float ppi_1440_2560 = 4.0f;

    public static void main(String[] args) {
        showDp();
    }

    /**
     * 240
     * 320
     * 460
     * 480
     * 540
     * 640
     * 720
     * 800
     * 1080
     * 1440
     */
    private static void showDp() {
        for (int i = 0; i < 1921; i++) {
            System.out.println(String.format("<dimen name=\"" + "dimen" + i + "\">%.1fpx</dimen>", i * 1600f / 1080f));
        }
    }
}
