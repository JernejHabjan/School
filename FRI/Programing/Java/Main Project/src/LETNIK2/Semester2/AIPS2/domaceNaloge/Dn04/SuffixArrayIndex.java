package LETNIK2.Semester2.AIPS2.domaceNaloge.Dn04;

import java.util.HashSet;
import java.util.Set;

public class SuffixArrayIndex {
    private int text_len;
    private String text; // input string
    private int[] SA;    // suffix array
    private String[] suffix;

    SuffixArrayIndex(String text) {
        this.text = text;
        this.SA = new int[text.length()];
        construct();
    }

    public int[] getSuffixArray() {
        return SA;
    }

    /**
     * Constructs the suffix array corresponding to the text in expected
     * O(n log n) suffix comparisons.
     */

    private void construct() {
        text_len = text.length();
        suffix = new String[text_len];
        for (int i = 0; i < text_len; i++) {
            suffix[i] = text.substring(i, text.length());
        }


        for (int i = 1; i < text_len; i++) {
            String str = suffix[i];
            int back;
            for (back = i - 1; back >= 0; back--) {
                if (suffix[back].compareTo(str) > 0) {
                    suffix[back + 1] = suffix[back];
                    SA[back + 1] = SA[back];
                } else {
                    break;
                }
            }
            suffix[back + 1] = str;
            SA[back + 1] = i;
        }


    }

    /**
     * Returns True, if the suffix at pos1 is lexicographically before
     * the suffix at pos2.
     *
     * @param pos1
     * @param pos2
     * @return boolean
     */
    public boolean suffixSuffixCompare(int pos1, int pos2) {
        return pos1 < pos2;
    }

    /**
     * Return True, if the query string is lexicographically lesser or
     * equal to the suffix string at pos1.
     *
     * @param query The query string
     * @param pos2  Position of the suffix
     * @return boolean
     */
    public boolean stringSuffixCompare(String query, int pos2) {
        return query.compareTo(this.suffix[pos2]) <=0;
    }



    private Set<Integer> binSearch(String query, int left, int right){
        Set<Integer> locations = new HashSet();

        int sredina = (left + right)/2;
        System.out.println("Root obtained: " + suffix[sredina]);


        if(query.charAt(0) == suffix[sredina].charAt(0)){
            System.out.println("LOOOOO");
            locations.add(text_len - suffix[sredina].length());
            return locations;
        }

        if (query.charAt(0) < suffix[sredina].charAt(0)){ //go left
            //positions.addAll(binSearch (query, sredina, right));
            System.out.println("LOOOOO1");
            locations.addAll(binSearch(query, left, sredina));
        }
        if (query.charAt(0) > suffix[sredina].charAt(0)){ //go left

            System.out.println("LOOOOO2");
            locations.addAll(binSearch(query, sredina, right));


        }

        return locations;
    }

    /**
     * Returns the positions of the given substring in the text using binary
     * search. The empty query is located at all positions in the text.
     *
     * @param query The query substring
     * @return A set of positions where the query is located in the text
     */
    public Set<Integer> locate(String query) {
        Set<Integer> positions = new HashSet();
        if(query.length() == 0){

            for (int i = 0;i < text_len; i++){
                positions.add(i);
            }
            return positions;
        }

        for (int i = 0;i < text_len; i++){
            if(query.charAt(0) == suffix[i].charAt(0)){
                positions.add(SA[i]);
            }
        }
        return positions;

        //return binSearch(query,  0, suffix.length);
    }

    /**
     * Returns the longest substring in the text which repeats at least 2 times
     * by examining the suffix array.
     *
     * @return The longest repeated substring in the text
     */

    private String lcp(String str1, String str2) {
        int n = Math.min(str1.length(), str2.length());
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return str1.substring(0, i);
        }
        return str1.substring(0, n);
    }

    public String longestRepeatedSubstring() {

        String longest = "";

        for(int i = 0; i < text_len -1; i++){
            String str1 = suffix[i];
            String str2 = suffix[i+1];
            String x = lcp(str1,str2);
            if (x.length() > longest.length())
                longest = x;
        }
        return longest;
    }

    /**
     * Calculates the length of the longest common prefix of two suffixes.
     *
     * @param pos1 Position of the first suffix in the text
     * @param pos2 Position of the second suffix in the text
     * @return The number of characters in the common prefix of the first and the second suffix
     */
    public int longestCommonPrefixLen(int pos1, int pos2) {
        String str1 = text.substring(pos1,text_len);
        String str2 = text.substring(pos2,text_len);

        return lcp(str1, str2).length();
    }
}

