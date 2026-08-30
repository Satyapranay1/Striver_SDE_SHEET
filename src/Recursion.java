
public ArrayList<Integer> subsetSums(int[] arr) {
    ArrayList<Integer> ans = new ArrayList<>();
    Recur(arr,0,ans,0);
    return ans;

}
public void Recur(int[] arr,int index,ArrayList<Integer> ans,int val){
    if (index == arr.length){
        ans.add(val);
        return;
    }

    Recur(arr,index + 1,ans,val + arr[index]);
    Recur(arr,index + 1,ans,val);
}


//Subsets - II
List<List<Integer>> subsetsWithDup(int[] nums) {

    List<List<Integer>> ans = new ArrayList<>();

    Arrays.sort(nums);

    recur(new ArrayList<>(), 0, nums, ans);

    return ans;
}

void recur(List<Integer> temp, int start, int[] nums, List<List<Integer>> ans) {

    ans.add(new ArrayList<>(temp));

    for (int i = start; i < nums.length; i++) {

        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }

        temp.add(nums[i]);

        recur(temp, i + 1, nums, ans);

        temp.removeLast();
    }
}

//Combination Sum
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Recur(ans,new ArrayList<>(),0,candidates,target);
    return ans;
}

public void Recur(List<List<Integer>> ans,List<Integer> temp,int idx,int[] nums,int target){
    if (target == 0){
        ans.add(new ArrayList<>(temp));
        return;
    }

    if (target < 0){
        return;
    }

    for (int i = idx; i < nums.length; i++){
        temp.add(nums[i]);
        Recur(ans,temp,i,nums,target - nums[i]);
        temp.removeLast();
    }
}

//Combination Sum - II
List<List<Integer>> combinationSum2(int[] nums, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    List<Integer> temp = new ArrayList<>();
    backtrack(nums, 0, target, temp, ans);
    return ans;
}

void backtrack(int[] nums, int start, int target,
               List<Integer> temp, List<List<Integer>> ans) {
    if (target == 0) {
        ans.add(new ArrayList<>(temp));
        return;
    }
    if (target < 0) {
        return;
    }
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        temp.add(nums[i]);
        backtrack(nums, i + 1, target - nums[i], temp, ans);
        temp.remove(temp.size() - 1);
    }
}

//Palindrome Partitioning
public List<List<String>> partition(String s) {
    List<List<String>> ans = new ArrayList<>();
    backtrack(s,new ArrayList<>(),ans,0);
    return ans;
}

public void backtrack(String s,List<String> temp,List<List<String>> ans,int idx){
    if (idx == s.length()){
        ans.add(new ArrayList<>(temp));
        return;
    }

    for (int i = idx; i < s.length(); i++){
        if (check(s,idx,i)){
            temp.add(s.substring(idx,i + 1));
            backtrack(s,temp,ans,i + 1);
            temp.removeLast();
        }
    }
}

public boolean check(String s,int left,int right){
    while (left <= right){
        if (s.charAt(left) != s.charAt(right)){
            return false;
        }
        left++;
        right--;
    }
    return true;
}

public String getPermutation(int n, int k) {
    List<Integer> arr = new ArrayList<>();
    int fact = 1;
    for (int i = 1; i < n; i++){
        fact = fact * i;
        arr.add(i);
    }

    arr.add(n);
    k--;
    StringBuilder ans = new StringBuilder();
    while (true){
        ans.append(arr.get(k / fact));
        arr.remove(k / fact);

        if (arr.size() == 0){
            break;
        }

        k = k % fact;
        fact = fact / arr.size();
    }

    return ans.toString();
}
void main(){
    int[] numbers = {2,3};
    ArrayList<Integer> result = subsetSums(numbers);

    System.out.println(result);

    int[] numbers2 = {1, 2, 2};

    List<List<Integer>> result2 = subsetsWithDup(numbers2);

    System.out.println(result2);

    int[] candidates = {2, 3, 6, 7};
    int target = 7;

    List<List<Integer>> result3 = combinationSum(candidates, target);

    System.out.println(result3);

    int[] nums = {10, 1, 2, 7, 6, 1, 5};
    int target2 = 8;

    List<List<Integer>> result4 = combinationSum2(nums, target2);

    System.out.println(result4);

    String input = "abaamitabh";

    List<List<String>> result5 = partition(input);

    System.out.println(result5);

    int n = 5;
    int k = 25;

    String result6 = getPermutation(n, k);

    System.out.println("Kth Permutation: " + result6);
}