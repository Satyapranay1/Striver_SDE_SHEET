//Reverse words in a String
public String reverseWords(String s) {
    StringBuilder ans = new StringBuilder();
    int i = s.length() - 1;
    while (i >= 0){
        while (i >= 0 && s.charAt(i) == ' ') i--;
        if (i < 0) break;
        int end = i;
        while (i >= 0 && s.charAt(i) != ' ') i--;
        if (!ans.isEmpty()) ans.append(" ");
        ans.append(s, i + 1, end + 1);
    }
    return ans.toString();
}

//Longest Palindromic Substring
public String longestPalindrome(String s) {
    int left = 0,right = 0;
    for (int i = 0; i < s.length(); i++){
        //Odd length
        int odd = Long(s,i,i);
        //Even length
        int even = Long(s,i,i + 1);
        int max = Math.max(odd,even);
        if (max > right - left){
            left = i - (max - 1) / 2;
            right = i + max / 2;
        }
    }
    return s.substring(left,right + 1);
}

public int Long(String s,int l,int r){
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        l--;
        r++;
    }
    return r - l - 1;
}

//Roman to Integer
public int romanToInt(String s) {
    HashMap<Character,Integer> map = new HashMap<>();
    map.put('I',1);
    map.put('V',5);
    map.put('X',10);
    map.put('L',50);
    map.put('C',100);
    map.put('D',500);
    map.put('M',1000);
    int ans = 0;
    for (int i = 0; i < s.length(); i++) ans = (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) ? ans - map.get(s.charAt(i)) : ans + map.get(s.charAt(i));
    return ans;
}

//String to Integer
public int myAtoi(String s) {
    int i = 0,n = s.length();
    if (s.isEmpty()) return 0;
    //Whitespaces
    while (i < n && s.charAt(i) == ' ') i++;

    int sign = 1;
    //Sign
    if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')){
        if (s.charAt(i) == '-') sign = -1;
        i++;
    }


    //Remove leading zeros
    while (i < n && s.charAt(i) == '0') i++;
    int ans = 0;
    while (i < n && Character.isDigit(s.charAt(i))){
        int dig = s.charAt(i) - '0';
        if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && dig > 7)) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        ans = ans * 10 + dig;
        i++;
    }
    return ans * sign;
}

//Longest Common Prefix
public String longestCommonPrefix(String[] strs) {
    String ans = strs[0];
    for (int i = 1; i < strs.length; i++){
        int j = 0;
        while (j < ans.length() && j < strs[i].length() && strs[i].charAt(j) == ans.charAt(j)) j++;
        ans = ans.substring(0,j);
        if (ans.equals(" ")) return ans;
    }
    return ans;
}

void main(){
    String s = "  the sky is blue  ";

    String result = reverseWords(s);

    System.out.println("Reversed Words: " + result);

    String s1 = "babad";

    String result1 = longestPalindrome(s1);

    System.out.println("Longest Palindromic Substring: " + result1);

    String s2 = "MCMIV";

    int result2 = romanToInt(s2);

    System.out.println("Integer Value: " + result2);

    String s3 = "   -00042abc";

    int result3 = myAtoi(s3);

    System.out.println("Integer Value: " + result3);

    String[] strs = {"flower", "flow", "flight"};

    String result4 = longestCommonPrefix(strs);

    System.out.println("Longest Common Prefix: " + result4);
}