package org.eamcode.fxguirunanalyzer.util;

import java.time.Duration;

public class MyTimeFormatter {

    public String formatDuration(Integer hr, Integer min, Integer sec) {
        return String.format("%02d:%02d:%02d",
                hr,
                min,
                sec);
    }

    public String formatDuration(Duration duration) {
        if (duration == null) {
            return "";
        }

        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        long hours = absSeconds / 3600;
        long minutes = (absSeconds % 3600) / 60;
        long secs = absSeconds % 60;

        String formatted = String.format("%02d:%02d:%02d", hours, minutes, secs);
        return seconds < 0 ? "-" + formatted : formatted;
    }

}
