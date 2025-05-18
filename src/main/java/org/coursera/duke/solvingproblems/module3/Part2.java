package org.coursera.duke.solvingproblems.module3;

public class Part2 {

    public int howMany(String stringa, String stringb) {
        int count = 0;
        int index = 0;

        while (true) {
            index = stringb.indexOf(stringa, index);
            if (index == -1) {
                break;
            }
            count++;
            index += stringa.length();
        }

        return count;
    }

    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // 3
        System.out.println(howMany("AA", "ATAAAA"));            // 2
        System.out.println(howMany("A", "AAAA"));               // 4
        System.out.println(howMany("AT", "GATATATGCATATACTT")); // 4
        System.out.println(howMany("XYZ", "XYZXYZXYZ"));        // 3
    }
}
