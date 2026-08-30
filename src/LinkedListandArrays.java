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

//Rotate Linked List
public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
        return head;
    }

    int len = 1;
    ListNode temp = head;
    while (temp.next != null) {
        len++;
        temp = temp.next;
    }
    k = k % len;
    if (k == 0) {
        return head;
    }

    temp.next = head;
    ListNode node1 = head;
    int rev = len - k;
    while (rev-- > 1) {
        node1 = node1.next;
    }
    head = node1.next;
    node1.next = null;
    return head;
}

//3 Sum
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++){
        if (i > 0 && nums[i] == nums[i - 1]){
            continue;
        }
        int j = i + 1,k = nums.length - 1;
        while (j < k){
            int curr = nums[i] + nums[j] + nums[k];
            if (curr == 0){
                List<Integer> in = new ArrayList<>();
                in.add(nums[i]);
                in.add(nums[j]);
                in.add(nums[k]);
                ans.add(in);
                j++;
                k--;
                while (j < k && nums[j] == nums[j - 1]) j++;
                while (j < k && nums[k] == nums[k + 1]) k--;
            }
            else if (curr < 0){
                j++;
            }
            else{
                k--;
            }
        }
    }
    return ans;
}

//Trapping Rain Water
public int trap(int[] height) {
    int ans = 0;
    int left = 0,right = height.length - 1,leftMax = 0,rightMax = 0;
    while (left <= right){
        if (height[left] <= height[right]){
            leftMax = Math.max(leftMax,height[left]);
            ans += (leftMax - height[left++]);
        }
        else{
            rightMax = Math.max(rightMax,height[right]);
            ans += (rightMax - height[right--]);
        }
    }
    return ans;
}

//Remove Duplicates from Sorted Array
public int removeDuplicates(int[] nums) {
    int idx = 1;
    for (int i = 1; i < nums.length; i++){
        if (nums[i] != nums[i - 1]){
            nums[idx++] = nums[i];
        }
    }
    return idx;
}

//Max Consecutive Ones
public int findMaxConsecutiveOnes(int[] nums) {
    int cnt = 0,max = 0;
    for (int i = 0; i < nums.length; i++){
        if (nums[i] == 1){
            cnt++;
        }
        else{
            if (max < cnt){
                max = cnt;
            }
            cnt = 0;
        }
    }
    if (max < cnt){
        return cnt;
    }
    return max;
}
void main(){
    ListNode rotateList = new ListNode(1, null);
    rotateList.next = new ListNode(2, null);
    rotateList.next.next = new ListNode(3, null);
    rotateList.next.next.next = new ListNode(4, null);
    rotateList.next.next.next.next = new ListNode(5, null);

    int rotations = 2;

    System.out.println("Original List:");
    printList(rotateList);

    ListNode rotatedList = rotateRight(rotateList, rotations);

    System.out.println("After " + rotations + " Rotations:");
    printList(rotatedList);

    int[] numbers = {-1, 0, 1, 2, -1, -4};

    List<List<Integer>> result = threeSum(numbers);

    System.out.println("Three Sum Results:");

    for (List<Integer> combination : result) {
        System.out.println(combination);
    }

    int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

    int result3 = trap(heights);

    System.out.println("Water Trapped: " + result3);

    int[] numbers3 = {1, 1, 2, 2, 3, 4, 4, 5};

    System.out.println("Original Array:");
    System.out.println(Arrays.toString(numbers3));

    int uniqueCount = removeDuplicates(numbers3);

    System.out.println("Number of Unique Elements: " + uniqueCount);

    System.out.println("Array After Removing Duplicates:");

    for (int i = 0; i < uniqueCount; i++) {
        System.out.print(numbers3[i] + " ");
    }

    System.out.println();

    int[] numbers4 = {1, 1, 0, 1, 1, 1, 0, 1};

    int result4 = findMaxConsecutiveOnes(numbers4);

    System.out.println("Maximum Consecutive Ones: " + result4);
}
