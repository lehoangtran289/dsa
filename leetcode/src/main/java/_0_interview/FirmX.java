package _0_interview;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirmX {

    public record DataRecord(Instant timestamp, int score) {
    }

    /**
     * Idea:
     * - neighbors DataRecord within same threshold would be compress
     * - traverse original list, check for any neighbor elements if they are within the threshold -> compress, take the first element
     * ---
     * Edge cases:
     * - null input list, null list elements
     * - negative threshold
     * - overflow diff
     * ---
     * Time: O(N)
     * Space: O(1)
     */
    public static List<DataRecord> compact(final List<DataRecord> original, final int threshold) {
        if (original == null || original.isEmpty() || threshold < 0)
            return new ArrayList<>();

        List<DataRecord> result = new ArrayList<>();

        for (DataRecord dataRecord : original) {
            if (dataRecord == null) continue;

            if (result.isEmpty() || Math.abs((long) result.getLast().score - dataRecord.score) > threshold) {
                result.add(dataRecord);
            }
        }

        return result;
    }

    static void main() {
        int threshold = 2;
        List<DataRecord> original = Arrays.asList(
                new DataRecord(Instant.ofEpochSecond(1600000000L), 1),
                new DataRecord(Instant.ofEpochSecond(1600000001L), 5),
                new DataRecord(Instant.ofEpochSecond(1600000002L), 6),
                new DataRecord(Instant.ofEpochSecond(1600000003L), 7),
                null,
                new DataRecord(Instant.ofEpochSecond(1600000004L), 2),
                new DataRecord(Instant.ofEpochSecond(1600000005L), 3),
                new DataRecord(Instant.ofEpochSecond(1600000006L), 5)
        ); // 1 5 2 5

        System.out.println(compact(original, threshold));
    }
}
