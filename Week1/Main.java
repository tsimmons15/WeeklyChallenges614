import java.io.*;
import java.util.*;
import java.lang.Math;

class Main{
    public static void main(String[] args) {
        try {
            testMergeK();
        } catch (Exception ex) {
            System.out.println("Test aborted");
            ex.printStackTrace();
        }
    }

    public static void testMedian() throws IOException {
        int[] num1 = null;
        int[] num2 = null;
        int[] combined = null;
        double med = -1;
        double combinedMed = -1;
        Writer writer = new FileWriter("testMedian.log");
        PrintWriter pw = new PrintWriter(writer);
        for (int runs = 0; runs < 10000; runs++) {
            try {
                int len1 = (int) Math.floor(Math.random() * 1001);
                int len2 = (int) Math.floor(Math.random() * 1001);

                if (len1 + len2 < 1 || len1 + len2 > 2000) {
                    runs--;
                    continue;
                }

                num1 = new int[len1];
                for (int i = 0; i < num1.length; i++) {
                    num1[i] = (int)Math.floor((Math.random() * 213) - 106);
                }
                num2 = new int[len2];
                for (int i = 0; i < num2.length; i++) {
                    num2[i] = (int)Math.floor((Math.random() * 213) - 106);
                }

                combined = new int[num1.length + num2.length];
                for (int i = 0; i < combined.length; i++) {
                    if (i < num1.length) {
                        combined[i] = num1[i];
                    } else {
                        combined[i] = num2[i - num1.length];
                    }
                }
                Arrays.sort(num1);
                Arrays.sort(num2);
                Arrays.sort(combined);

                int mid = (combined.length - 1) / 2;
                if (combined.length % 2 == 0) {
                    combinedMed = (combined[mid] + combined[mid+1]) / 2.0;
                } else {
                    combinedMed = combined[mid];
                }

                med = Median.median(num1, num2);
                if (med != combinedMed) {
                    throw new Exception("Difference: " + med + " : " + combinedMed);
                }
            } catch (Exception ex) {
                pw.write("Test failed: #" + runs + "\n");
                pw.write("Exception: \n");
                ex.printStackTrace(pw);
                if (num1 != null && num1.length > 0) {
                    pw.write("num1: ");
                    pw.write(num1[0] +"");
                    for (int i = 1; i < num1.length; i++) {
                        pw.write(", " + num1[i]);
                    }
                    pw.write("\n");
                } else if (num1 == null){
                    pw.write("num1 was null\n");
                }

                if (num2 != null && num2.length > 0) {
                    pw.write("num2: ");
                    pw.write(num2[0]+"");
                    for (int i = 1; i < num2.length; i++) {
                        pw.write(", " + num2[i]);
                    }
                    pw.write("\n");
                } else if (num2 == null){
                    pw.write("num2 was null\n");
                }

                if (combined != null && combined.length > 0) {
                    pw.write("combined: ");
                    pw.write(combined[0] + "");
                    for (int i = 1; i < combined.length; i++) {
                        pw.write(", "+ combined[i]);
                    }
                    pw.write("\n");
                } else if (combined == null) {
                    pw.write("combined was null\n");
                }
            }
        }
        pw.close();
    }

    public static void testMergeK() throws IOException {
        Writer writer = new FileWriter("testMergek.log");
        PrintWriter pw = new PrintWriter(writer);
        List<Integer> merged = null;
        List<Integer> combined = null;
        for (int runs = 0; runs < 1000; runs++) {
            try {
                int k = (int)Math.floor(Math.random() * 105);
                List<Integer>[] lists = new LinkedList[k];
                for(int i = 0; i < k; i++) {
                    int length = (int)Math.floor(Math.random() * 501);
                    lists[i] = new LinkedList<Integer>();
                    for(int j = 0; j < length; j++) {
                        lists[i].add((int)Math.floor(Math.random() * 209) - 104);
                    }
                    lists[i].sort((a,b) -> a - b);
                }

                combined = new LinkedList<Integer>();
                for (List<Integer> l : lists) {
                    combined.addAll(l);
                }
                combined.sort((a, b) -> a - b);
                merged = Mergek.mergek(lists);

                if (!merged.equals(combined)) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                pw.write("Test failed: #" + runs + "\n");
                pw.write("Exception: \n");
                ex.printStackTrace(pw);
            }
        }
        pw.close();
    }
}