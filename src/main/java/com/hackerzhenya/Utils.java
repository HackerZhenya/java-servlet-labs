package com.hackerzhenya;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {
    private static final DecimalFormat df = new DecimalFormat("0.##");
    private static final double sizeKb = 1 << 10;
    private static final double sizeMb = sizeKb * sizeKb;
    private static final double sizeGb = sizeMb * sizeKb;
    private static final double sizeTb = sizeGb * sizeKb;

    public static String formatTimestamp(Long timestamp, String format) {
        return new SimpleDateFormat(format).format(new Date(timestamp));
    }

    public static String formatFileSize(Long size) {
        if (size < sizeKb)
            return df.format(size) + " B";

        if (size < sizeMb)
            return df.format(size / sizeKb) + " KB";

        if (size < sizeGb)
            return df.format(size / sizeMb) + " MB";

        if (size < sizeTb)
            return df.format(size / sizeGb) + " GB";

        return df.format(size / sizeTb) + " TB";
    }
}
