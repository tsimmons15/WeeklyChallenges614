import java.util.LinkedList;
import java.util.List;

public class Mergek {
    public static List<Integer> mergek(List<Integer>[] lists) {
        List<Integer> merged = new LinkedList<>();
        List<Integer> min = null;
        int items = 0;
        for (List<Integer> list : lists) {
            items += list.size();
        }
        while (items > 0) {
            min = lists[0];
            for (int i = 1; i < lists.length; i++) {
                if (lists[i].size() == 0) continue;

                if (min.size() == 0 || lists[i].get(0) < min.get(0)) {
                    min = lists[i];
                }
            }
            merged.add(min.remove(0));
            items--;
        }

        return merged;
    }
}