package interview;

/**
 * @tapan .
 */
/**
 * @tapan .
 */
public class GoogleQuestion {

    public static void main(String args[]) {

        int val = rotate2("zbc", "acd");
        //System.out.println(val);

        val = rotate2("xyz", "cde");
        //System.out.println(val);

        val = rotate("def", "abc");
       System.out.println(val);

        val = rotate2("efg", "abc");
       // System.out.println(val);

        val = rotate3("def", "abc");
        System.out.println(val);
    }


    static int rotate3(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return -1;
        }

        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);

        int diff = c2 - c1;
        if (diff < 0) {
            diff += 26;
        }

        char[] chars1 = s1.toCharArray();
        for (int i = 1; i < chars1.length; i++) {
            c1 = s1.charAt(i);
            c2 = s2.charAt(i);

            if (diff + c1 > 'z') {
                if ( ((diff - ('z' - c1)) + 'a') - 1 != c2) {
                    return -1;
                }
            }else {
                if (c1 + diff != c2) {
                    return -1;
                }
            }
        }

        return diff;

    }

    static int rotate2(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return -1;
        }

        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);

        int diff = c2 - c1;

        if (diff < 0) {
            diff += 26;
        }
        int i = 1;

        while (i<s1.length()) {

            if (s1.charAt(i) +diff > 'z') {

                if (((diff - ('z' - s1.charAt(i))) +'a') - 1 != s2.charAt(i)) {
                    return -1;
                }
            } else {
                if (s1.charAt(i) + diff != s2.charAt(i)) {
                    return -1;
                }
            }
            i++;
        }

        return diff;

    }

    static int rotate(String s1, String s2) {

        if (s1.length() != s2.length())
            return -1;

        int i = 0;
        int c1 = s1.charAt(i);
        int c2 = s2.charAt(i);

        int diff = c2 - c1;

        if (diff < 0) {
            diff += 26;
        }

        i++;
        int z = 'z';
        int a = 'a';
        while (i < s1.length()) {
            c1 = s1.charAt(i);
            c2 = s2.charAt(i);

            if (c1 + diff > z) {
                int tmp = z - c1;

                if ((diff - tmp + a) - 1 != c2) {
                    return -1;
                }
            } else {
                if (c1 + diff != c2) {
                    return -1;
                }
            }

            i++;
        }

        return diff;
    }

}

