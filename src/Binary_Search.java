//Nth root of an Integer
public int nthRoot(int n, int m) {
    if (m == 0){
        return 0;
    }
    int low = 1,high = m;
    while (low <= high){
        int mid = low + (high - low) / 2;

        int val = 1;
        for (int i = 0; i < n; i++){
            val *= mid;
            if (val > m) break;
        }

        if (val == m) return mid;

        else if (val < m) low = mid + 1;

        else high = mid - 1;
    }
    return -1;
}

//Median in a row wise Sorted Matrix
public int countLessOrEqual(int[] row,int mid){
    int low = 0,high = row.length;
    while (low < high){
        int m = low + (high - low) / 2;
        if (row[m] <= mid){
            low = m + 1;
        }
        else{
            high = m;
        }
    }
    return low;
}

//Median in a row wise sorted matrix
public int median(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int low = mat[0][0],high = mat[0][n - 1];
    for (int i = 0; i < m; i++){
        low = Math.min(low,mat[i][0]);
        high = Math.max(high,mat[i][n - 1]);
    }

    while (low < high){
        int mid = low + (high - low) / 2;

        int count = 0;
        for (int i = 0; i < m; i++){
            count += countLessOrEqual(mat[i],mid);
        }

        if (count < (m * n + 1) / 2){
            low = mid + 1;
        }
        else{
            high = mid;
        }
    }
    return low;
}

//Single element in a sorted array
public int singleNonDuplicate(int[] nums) {
    int low = 0,high = nums.length - 1;
    while (low < high){
        int mid = low + (high - low) / 2;
        if ((mid % 2 == 0 && nums[mid] == nums[mid + 1])
                || (mid % 2 != 0 && nums[mid] == nums[mid - 1])){
            low = mid + 1;
        }
        else{
            high = mid;
        }
    }

    return nums[low];
}

//Search in a Rotated Sorted Array
public int search(int[] nums, int target) {
    int low = 0,high = nums.length - 1;
    while (low <= high){
        int mid = low + (high - low) / 2;

        if (nums[mid] == target){
            return mid;
        }

        else if (nums[low] <= nums[mid]){
            if (nums[low] <= target && target <= nums[mid]){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }

        else{
            if (nums[mid] <= target && target <= nums[high]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
    }
    return -1;
}

//Kth element in 2 sorted arrays
public int kthElement(int a[], int b[], int k) {
    int m = a.length,n = b.length;
    if (m > n){
        return kthElement(b,a,k);
    }

    int low = Math.max(0,k - n),high = Math.min(k,m);

    while (low <= high){
        int cut1 = low + (high - low) / 2;
        int cut2 = k - cut1;
        int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
        int r1 = cut1 == m ? Integer.MAX_VALUE : a[cut1];
        int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
        int r2 = cut2 == n ? Integer.MAX_VALUE : b[cut2];

        if (l1 <= r2 && l2 <= r1){
            return Math.max(l1,l2);
        }

        else if (l1 > r2){
            high = cut1 - 1;
        }

        else{
            low = cut1 + 1;
        }
    }
    return 1;
}

//Allocate Minimum no. of pages
public int findPages(int[] arr, int k) {
    if (k > arr.length) {
        return -1;
    }
    long low = arr[0],high = arr[0];
    for (int i = 1; i < arr.length; i++){
        low = Math.max(low,arr[i]);
        high += arr[i];
    }

    while (low <= high){
        long mid = low + (high - low) / 2;
        if (!check(arr,mid,k)){
            low = mid + 1;
        }
        else{
            high = mid - 1;
        }
    }
    return (int)low;
}

public boolean check(int[] arr,long mid,int k){
    int cnt = 1;
    long curr = 0;
    for (int i = 0; i < arr.length; i++){
        if (curr + arr[i] > mid){
            curr = 0;
            cnt++;
            if (cnt > k){
                return false;
            }
        }
        curr += arr[i];
    }
    return cnt <= k;
}

//Aggressive Cows
public int aggressiveCows(int[] arr, int k) {
    Arrays.sort(arr);
    int low = 0,high = arr[arr.length - 1] - arr[0],ans = 0;
    while (low <= high){
        int mid = low + (high - low) / 2;
        if (!check(arr,mid,k)){
            low = mid + 1;
            ans = mid;
        }
        else{
            high = mid - 1;
        }
    }
    return ans;
}

public boolean check(int[] arr,int mid,int k){
    int cnt = 1;
    int last = arr[0];
    for (int i = 1; i < arr.length; i++){
        if (arr[i] - last >= mid){
            last = arr[i];
            cnt++;
            if (cnt >= k){
                return false;
            }
        }
    }
    return cnt <= k;
}

void main(){
    int n = 3;
    int m = 27;

    int result = nthRoot(n, m);

    System.out.println("Nth Root: " + result);

    int[][] matrix = {
            {1, 3, 5},
            {2, 6, 9},
            {3, 6, 9}
    };

    int result1 = median(matrix);

    System.out.println("Median: " + result1);

    int[] numbers = {1, 1, 2, 3, 3, 4, 4, 8, 8};

    int result3 = singleNonDuplicate(numbers);

    System.out.println("Single Element: " + result3);

    int[] numbers1 = {4, 5, 6, 7, 0, 1, 2};

    int target = 0;

    int result4 = search(numbers1, target);

    System.out.println("Target Index: " + result4);

    int[] firstArray = {2, 3, 6, 7, 9};
    int[] secondArray = {1, 4, 8, 10};

    int k = 5;

    int result5 = kthElement(firstArray, secondArray, k);

    System.out.println("Kth Element: " + result5);

    int[] books = {12, 34, 67, 90};
    int students = 2;

    int result6 = findPages(books, students);

    System.out.println("Minimum Maximum Pages: " + result6);

    int[] stalls = {0, 3, 4, 7, 10, 9};
    int cows = 4;

    int result7 = aggressiveCows(stalls, cows);

    System.out.println("Maximum Minimum Distance: " + result7);
}