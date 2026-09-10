//Z - function
public int[] zfunc(String text){
    int r = 0;
    int[] z = new int[text.length()];
    for (int i = 1; i < text.length(); i++){
        if (i <= r) z[i] = Math.min(r - i + 1,z[i - 1]);
        while (i + z[i] < text.length() && text.charAt(z[i]) == text.charAt(i + z[i])) z[i]++;
        if (i + z[i] - 1 > r){
            r = i + z[i] - 1;
        }
    }
    return z;
}

//KMP Algorithm
public int strStr(String haystack, String needle) {
    int[] lps = compute(needle);
    int i = 0,j = 0;
    while (i < haystack.length()){
        if (haystack.charAt(i) == needle.charAt(j)){
            i++;
            j++;
        }
        if (j == needle.length()) return i - j;
        if (i < haystack.length() && haystack.charAt(i) != needle.charAt(j)){
            if (j != 0) j = lps[j - 1];
            else i++;
        }
    }
    return -1;
}

public int[] compute(String pattern){
    int[] lps = new int[pattern.length()];
    int len = 0,i = 1;
    while (i < pattern.length()){
        if (pattern.charAt(i) == pattern.charAt(len)){
            len++;
            lps[i] = len;
            i++;
        }
        else{
            if (len != 0) len = lps[len - 1];
            else{
                lps[i] = 0;
                i++;
            }
        }
    }
    return lps;
}

//Minimum Insertions to make Palindrome
public int minInsertions(String s) {
    int n = s.length();
    String rev = new StringBuilder(s).reverse().toString();
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 1; i <= n; i++){
        for (int j = 1; j <= n; j++) dp[i][j] = (s.charAt(i - 1) == rev.charAt(j - 1)) ?  1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j],dp[i][j - 1]);
    }
    return n - dp[n][n];
}

//Valid Anagram
public boolean isAnagram(String s, String t) {
    int[] a = new int[26];
    for (int i = 0; i < s.length(); i++) a[s.charAt(i) - 'a']++;

    for (int i = 0; i < t.length(); i++) a[t.charAt(i) - 'a']--;

    for (int i = 0; i < 26; i++){
        if (a[i] != 0) return false;
    }
    return true;
}

//Count and Say
public String countAndSay(int n) {
    String res = "1";
    for (int i = 1; i < n; i++) {
        StringBuilder curr = new StringBuilder();
        int count = 1;
        for (int j = 1; j < res.length(); j++) {
            if (res.charAt(j) == res.charAt(j - 1)) count++;
            else {
                curr.append(count).append(res.charAt(j - 1));
                count = 1;
            }
        }
        curr.append(count).append(res.charAt(res.length() - 1));
        res = curr.toString();
    }
    return res;
}

//Compare Version Numbers
public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int len = Math.max(v1.length,v2.length);
    for (int i = 0; i < len; i++){
        int curr1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
        int curr2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
        if (curr1 < curr2) return -1;
        if (curr1 > curr2) return 1;
    }
    return 0;
}


void main(){
    String text = "aabcaab";
    int[] res = zfunc(text);
    System.out.println(Arrays.toString(res));

    String haystack3 = "ababcabcabababd";
    String needle3 = "ababd";
    System.out.println("Pattern found at: " + strStr(haystack3, needle3));

    String s2 = "mbadm";
    System.out.println("Minimum insertions to make palindrome: " + minInsertions(s2));

    String s1 = "anagram";
    String t1 = "nagaram";
    System.out.println("Is Valid Anagram : " + isAnagram(s1, t1));

    int n6 = 6;
    System.out.println(countAndSay(n6));

    String version1 = "7.5.2.4";
    String version2 = "7.5.3";
    int x = compareVersion(version1, version2);
    if (x == -1) System.out.println("First version is bigger");
    else if (x == 1) System.out.println("Second version is bigger : ");
    else System.out.println("Both versions are equal");

}