public class Main {
    public static void main(String[] args) {
        //testWalkLength();
        testNameMatch();
    }

    public static void testWalkLength() {
        for (int i = 0; i < 15; i++) {
            System.out.println(i + "-> " + Axis.walkLength(i));
        }
    }

    public static void testNameMatch() {
        String[] first = {"john", "ira", "jayla", "tim", "samuel"};
        String[] second = {"johanna", "ira", "kayla", "trimn", "asdaemduieolp"};

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second.length; j++) {
                System.out.println("{" + first[i] + ", " + second[j] + "} -> " + Name.isMatch(first[i], second[j]));
                System.out.println("{" + second[j] + ", " + first[i] + "} -> " + Name.isMatch(second[j], first[i]));
            }
        }
    }
}
