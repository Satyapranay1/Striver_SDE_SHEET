import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int data){
        this.val = data;
    }

    ListNode(int data, ListNode next) {
        this.val = data;
        this.next = next;
    }
}
class ArraysI{
    // Set Matrix Zeroes
    public static void setZeroes(int[][] matrix) {

        boolean is = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == 0) {

                    if (j == 0) {
                        is = true;
                    } else {
                        matrix[0][j] = 0;
                    }

                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

        if (is) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // Print Matrix
    public static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Pascal's Triangle
    public static List<List<Integer>> pascal(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {

            List<Integer> inner = new ArrayList<>();

            if (i == 0) {
                inner.add(1);
            }

            else if (i == 1) {
                inner.add(1);
                inner.add(1);
            }

            else {
                inner.add(1);

                for (int j = 1; j < ans.size(); j++) {
                    inner.add(
                            ans.get(i - 1).get(j - 1)
                                    + ans.get(i - 1).get(j)
                    );
                }

                inner.add(1);
            }

            ans.add(inner);
        }

        return ans;
    }

    //Maximum Sum Subarray
    public int maxSubArray(int[] nums) {
        int curr = nums[0],max = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (curr < 0){
                curr = 0;
            }
            curr += nums[i];
            if (max < curr){
                max = curr;
            }
        }
        return max;
    }

    //Sort colors
    public void sortColors(int[] nums) {
        int low = 0,mid = 0,high = nums.length - 1;
        while (mid <= high){
            if (nums[mid] == 0){
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            }

            else if (nums[mid] == 1){
                mid++;
            }

            else{
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    //Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int ans = Integer.MIN_VALUE,min = prices[0];
        for (int i = 1; i < prices.length; i++){
            if (min > prices[i]){
                min = prices[i];
            }
            ans = Math.max(prices[i] - min,ans);
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }
}

class ArraysII{
    //Rotate Image
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0;  j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length - j - 1];
                matrix[i][matrix[0].length - j - 1] = temp;
            }
        }
    }

    //Merge Overlapping Intervals
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> ans = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 0; i < intervals.length; i++){
            if (curr[1] >= intervals[i][0]){
                curr[1] = Math.max(curr[1],intervals[i][1]);
            }
            else{
                ans.add(curr);
                curr = intervals[i];
            }
        }

        ans.add(curr);
        return ans.toArray(new int[0][]);
    }

    //Merge 2 sorted arrays without extra space
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1,j = n - 1,k = m + n - 1;
        while (i >= 0 && j >= 0){
            if (nums1[i] <= nums2[j]){
                nums1[k--] = nums2[j--];
            }
            else{
                nums1[k--] = nums1[i--];
            }
        }

        while (j >= 0){
            nums1[k--] = nums2[j--];
        }
    }

    //Find the Duplicated Number
    public int findDuplicate(int[] nums) {
        int slow = nums[0],fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        fast = nums[0];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    //Find Missing and Repeated Number
    public static int[] findMissingRepeatingNumbers(int[] nums) {

        int n = nums.length;

        int xor = 0;

        // XOR array elements
        for (int num : nums) {
            xor ^= num;
        }

        // XOR numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        // Find rightmost set bit
        int bit = xor & -xor;

        int x = 0;
        int y = 0;

        // Divide array elements into two groups
        for (int num : nums) {
            if ((num & bit) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }

        // Divide numbers 1 to n into two groups
        for (int i = 1; i <= n; i++) {
            if ((i & bit) != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }

        // Determine which is repeating and which is missing
        for (int num : nums) {
            if (num == x) {
                return new int[]{x, y};
            }
        }

        return new int[]{y, x};
    }
}

class ArraysIII{
    //Search in a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int left = 0,right = m * n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int row = mid / n,col = mid % n;
            if (matrix[row][col] < target){
                left = mid + 1;
            }
            else if (matrix[row][col] > target){
                right = mid - 1;
            }

            else{
                return true;
            }
        }
        return false;
    }

    //Pow(x,n)
    public double myPow(double x, int n) {
        long power = n;
        if (power < 0) {
            return 1.0 / fact(x, -power);
        }
        return fact(x,power);
    }

    public double fact(double x,long n){
        if (n == 0){
            return 1;
        }

        if (n == 1){
            return x;
        }

        return n % 2 == 0 ? fact(x * x,n / 2) : x * fact(x,n - 1);
    }

    //Majority Element
    public int majorityElement(int[] nums) {
        int curr = 0,cnt = 0;
        for (int i = 0; i < nums.length; i++){
            if (cnt == 0){
                curr = nums[i];
                cnt = 1;
            }

            else{
                if (curr == nums[i]){
                    cnt++;
                }
                else{
                    cnt--;
                }
            }
        }

        cnt = 0;
        for (int i = 0; i < nums.length; i++){
            if (curr == nums[i]){
                cnt++;
            }
        }
        return cnt > nums.length / 2 ? curr : -1;
    }

    //Majority Element - II
    public List<Integer> majorityElementII(int[] nums) {
        int el1 = Integer.MIN_VALUE,el2 = Integer.MIN_VALUE,cnt1 = 0,cnt2 = 0;
        // [0,0,0] it fails so we have to put integer.min_value
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            if (cnt1 == 0 && el2 != nums[i]){
                el1 = nums[i];
                cnt1 = 1;
            }

            else if (cnt2 == 0 && el1 != nums[i]){
                el2 = nums[i];
                cnt2 = 1;
            }

            else if (nums[i] == el1){
                cnt1++;
            }

            else if (nums[i] == el2){
                cnt2++;
            }

            else{
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == el1){
                cnt1++;
            }
            if (nums[i] == el2){
                cnt2++;
            }
        }

        if (cnt1 > nums.length / 3){
            ans.add(el1);
        }

        if (cnt2 > nums.length / 3){
            ans.add(el2);
        }
        return ans;


    }

    //Unique Paths
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 || j == 0){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //Count Inversions
    public int merge(int[] arr,int low,int mid,int high){
        int cnt = 0;

        int n1 = mid - low + 1,n2 = high - mid;
        //N1 = left side boundary,N2 = right side boundary
        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++){
            left[i] = arr[low + i];//Copy the content
        }

        for (int i = 0; i < n2; i++){
            right[i] = arr[mid + i + 1];//Copy the content
        }

        int i = 0,j = 0,k = low;//I,J to track the no.of elements and K for modifying the data from low
        while (i < n1 && j < n2){
            if (left[i] <= right[j]){
                arr[k++] = left[i++];
            }
            else{
                arr[k++] = right[j++];
                cnt += (n1 - i);
            }
        }

        while (i < n1){
            arr[k++] = left[i++];
        }

        while (j < n2){
            arr[k++] = right[j++];
        }
        return cnt;
    }
    public int mergesort(int[] arr,int low,int high){
        int cnt = 0;
        if (low >= high){
            return cnt;
        }
        int mid = low + (high - low) / 2;
        cnt += mergesort(arr,low,mid);
        cnt += mergesort(arr,mid + 1,high);
        cnt += merge(arr,low,mid,high);
        return cnt;
    }
    public int inversionCount(int arr[]) {
        return mergesort(arr,0,arr.length - 1);

    }


    //Reverse Pairs
    public int reversePairs(int[] nums) {
        return mergesort1(nums,0,nums.length - 1);
    }

    public int mergesort1(int[] nums,int low,int high){
        int cnt = 0;
        if (low >= high){
            return cnt;
        }

        int mid = low + (high - low) / 2;
        cnt += mergesort1(nums,low,mid);
        cnt += mergesort1(nums,mid + 1,high);
        cnt += countPairs(nums,low,mid,high);
        merge1(nums,low,mid,high);
        // System.out.println(Arrays.toString(nums));
        return cnt;
    }

    public int countPairs(int[] nums,int low,int mid,int high){
        int ans = 0,right = mid + 1;
        for (int i = low; i <= mid; i++){
            while (right <= high && (long)nums[i] > 2L * nums[right]){
                right++;
            }
            ans += (right - (mid + 1));
        }
        return ans;
    }

    public void merge1(int[] nums,int low,int mid,int high){
        int n1 = mid - low + 1,n2 = high - mid;
        int[] l1 = new int[n1];
        int[] l2 = new int[n2];

        for (int i = 0; i < n1; i++){
            l1[i] = nums[low + i];
        }

        for (int i = 0; i < n2; i++){
            l2[i] = nums[mid + i + 1];
        }

        int i = 0,j = 0,k = low;
        while (i < n1 && j < n2){
            if (l1[i] <= l2[j]){
                nums[k++] = l1[i++];
            }
            else{
                nums[k++] = l2[j++];
            }
        }

        while (i < n1){
            nums[k++] = l1[i++];
        }

        while (j < n2){
            nums[k++] = l2[j++];
        }
    }
}

class ArraysIV{
    //Two sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                return new int[]{i,map.get(target - nums[i])};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }

    //Fourth Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int x = j + 1,y = nums.length - 1;
                while (x < y){
                    long curr = (long)nums[i] + nums[j] + nums[x] + nums[y];
                    if (curr == target){
                        ans.add(Arrays.asList(nums[i],nums[j],nums[x],nums[y]));
                        x++;
                        y--;
                        while (x < y && nums[x] == nums[x - 1]) x++;
                        while (x < y && nums[y] == nums[y + 1]) y--;

                    }
                    else if (curr > target){
                        y--;
                    }
                    else{
                        x++;
                    }
                }
            }
        }
        return ans;
    }

    //Longest Consecutive Sequence in an Array
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int longest = 1;
        Set<Integer> st = new HashSet<>();
        for (int i = 0; i < n; i++) {
            st.add(nums[i]);
        }
        for (int it : st) {
            if (!st.contains(it - 1)) {
                int cnt = 1;
                int x = it;
                while (st.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }

    //Longest Subarray with 0 sum
    public int maxLength(int arr[]) {
        // code here
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int value = 0;
        for (int i = 0; i < arr.length; i++){
            value += arr[i];
            if (value == 0){
                max = i + 1;
            }
            else{
                if (map.containsKey(value)){
                    max = Math.max(max,i - map.get(value));
                }
                else{
                    map.put(value,i);
                }
            }

        }
        return max;
    }

    //Longest Substring without repeating characters
    public int lengthOfLongestSubstring(String s) {
        int max = 0,left = 0,right = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        while (right < s.length()){
            char ch = s.charAt(right);
            if (map.containsKey(ch)){
                while (map.get(ch) > 0){
                    map.put(s.charAt(left),map.get(s.charAt(left)) - 1);
                    left++;
                }
            }
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            max = Math.max(max,right - left + 1);
            right++;
        }
        return max;
    }

}


public class Main {

    public static void printList(ListNode head) {
        while (head != null) {
            IO.print(head.val);

            if (head.next != null) {
                IO.print(" -> ");
            }

            head = head.next;
        }
        IO.println();
    }


    static void main() {
        ArraysI first = new ArraysI();
        // Set Matrix Zeroes
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        ArraysI.setZeroes(matrix);

        System.out.println("Set Matrix Zeroes:");
        ArraysI.printMatrix(matrix);


        // Pascal's Triangle
        int numRows = 5;

        List<List<Integer>> result = first.pascal(numRows);

        System.out.println("\nPascal's Triangle:");

        for (List<Integer> row : result) {
            System.out.println(row);
        }

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int result1 = first.maxSubArray(nums);

        System.out.println("Maximum Subarray Sum: " + result1);

        int[] colors = {2, 0, 2, 1, 1, 0};

        first.sortColors(colors);
        System.out.println(Arrays.toString(colors));

        int[] prices = {7, 1, 5, 3, 6, 4};

        int result2 = first.maxProfit(prices);

        System.out.println("Maximum Profit: " + result2);



        //Arrays - II

        ArraysII second = new ArraysII();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        second.rotate(matrix1);

        System.out.println("Rotated Matrix:");

        ArraysI.printMatrix(matrix1);

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] result3 = second.merge(intervals);

        System.out.println("Merged Intervals:");

        for (int[] interval : result3) {
            System.out.println(Arrays.toString(interval));
        }

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = {2, 5, 6};
        int n = 3;

        second.merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));

        int[] nums3 = {1, 3, 4, 2, 2};

        int result4 = second.findDuplicate(nums3);

        System.out.println("Duplicate Number: " + result4);

        int[] nums4 = {4, 3, 6, 2, 1, 1};

        int[] result5 = second.findMissingRepeatingNumbers(nums4);

        System.out.println("Repeating Number: " + result5[0]);
        System.out.println("Missing Number: " + result5[1]);




        //Arrays - III

        ArraysIII third = new ArraysIII();
        int[][] matrix2 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        int target = 3;

        boolean result6 = third.searchMatrix(matrix2, target);

        System.out.println("Target " + target + " found: " + result6);

        double x = 2.0;
        int n5 = 10;

        double result7 = third.myPow(x, n5);

        System.out.println(x + "^" + n + " = " + result7);

        int[] nums5 = {2, 2, 1, 1, 1, 2, 2};

        int result8 = third.majorityElement(nums5);

        System.out.println("Majority Element: " + result8);

        int[] nums7 = {3, 2, 3};

        List<Integer> result9 = third.majorityElementII(nums7);

        System.out.println("Majority Elements: " + result9);

        int m1 = 3;
        int n1 = 7;

        int result11 = third.uniquePaths(m1, n1);

        System.out.println("Number of Unique Paths: " + result11);

        int[] arr = {5, 3, 2, 4, 1};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        int result12 = third.inversionCount(arr);

        System.out.println("Inversion Count: " + result12);

        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(arr));

        int[] nums8 = {1, 3, 2, 3, 1};

        int result123 = third.reversePairs(nums8);

        System.out.println("Reverse Pairs: " + result123);
        System.out.println("Sorted Array: " + Arrays.toString(nums8));


        //Arrays - IV
        ArraysIV fourth = new ArraysIV();
        int[] nums15 = {2, 7, 11, 15};
        int target7 = 9;

        int[] result15 = fourth.twoSum(nums15, target7);

        System.out.println("Indices: " + Arrays.toString(result15));


        int[] nums17 = {1, 0, -1, 0, -2, 2};
        int target6 = 0;

        List<List<Integer>> result124 = fourth.fourSum(nums17,target6);

        System.out.println("Four Sum Result:");

        for (List<Integer> row : result124) {
            System.out.println(row);
        }

        int[] nums21 = {100, 4, 200, 1, 3, 2};

        int result78 = fourth.longestConsecutive(nums21);

        System.out.println("Longest Consecutive Sequence: " + result78);

        int[] arr1 = {15, -2, 2, -8, 1, 7, 10, 23};

        int result89 = fourth.maxLength(arr1);

        System.out.println("Longest Subarray Length: " + result89);

        String s = "abcabcbb";

        int result148 = fourth.lengthOfLongestSubstring(s);

        System.out.println("Longest Substring Length: " + result148);

    }
}