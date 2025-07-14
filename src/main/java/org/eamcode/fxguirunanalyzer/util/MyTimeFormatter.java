package org.eamcode.fxguirunanalyzer.util;

public class MyTimeFormatter {

    public String formatDuration(Integer hr, Integer min, Integer sec) {
        return String.format("%02d:%02d:%02d",
                hr,
                min,
                sec);
    }
}
