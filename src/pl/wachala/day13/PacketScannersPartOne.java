package pl.wachala.day13;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class PacketScannersPartOne {

    public static int getSeverityLevel(String input[]) {
            int max = 0;
            Map<Integer, Integer> layerToRange = new HashMap<>();

            for (String s : input) {
                String[] split = s.replace(" ", "").split(":");
                int layer = Integer.parseInt(split[0]);
                int range = Integer.parseInt(split[1]);

                layerToRange.put(layer, range);

                if (layer > max)
                    max = layer;
            }

            int severity = 0;

            for (int i = 0; i <= max; i++) {
                Integer rangeOfLayer = layerToRange.get(i);

                if (nonNull(rangeOfLayer)) {
                    int scannerPosition = i % (rangeOfLayer + rangeOfLayer-2);

                    if (scannerPosition == 0)
                        severity += i * rangeOfLayer;
                }
            }

            return severity;
    }

}
