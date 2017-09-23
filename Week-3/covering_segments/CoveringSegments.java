import java.lang.Integer;
import java.util.*;

public class CoveringSegments {
    private static class Segment {
        int start, end;
        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }

    private static int[] optimalPoints(Segment[] segments) {
        ArrayList<Integer> optimalPointsList = new ArrayList<>();
        Map<Integer, Boolean> coveredSegments = new HashMap<>(); // Segments covered by the point
        sortSegments(segments);
        for (int i = 0 ; i < segments.length; i++) {
            if (!coveredSegments.containsKey(i)) {
                int endPoint = segments[i].end;
                optimalPointsList.add(endPoint);
                coveredSegments.put(i, true);
                for (int j = i+1; j < segments.length; j++) {
                    if (!coveredSegments.containsKey(j) && isPointPresentOnSegment(endPoint, segments[j])) {
                        coveredSegments.put(j, true);
                    }
                }
            }
        }
        int [] optimalPointsArray = new int[optimalPointsList.size()];
        for (int k=0; k < optimalPointsList.size(); k++) {
            optimalPointsArray[k] = optimalPointsList.get(k);
        }
        return optimalPointsArray;
    }

    // Sort the segments in ascending order of end points
    private static void sortSegments(Segment[] segments) {
        Arrays.sort(segments, (o1, o2) -> {
            if (o1.end < o2.end) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    // Is the given point present on the given segment
    private static boolean isPointPresentOnSegment(int endPoint , Segment segment) {
        return (segment.start <= endPoint && segment.end >= endPoint);
    }
}

