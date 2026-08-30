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
}