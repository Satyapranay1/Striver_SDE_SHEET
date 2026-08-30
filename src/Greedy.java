//N meetings in a room
public ArrayList<Integer> maxMeetings(int[] s, int[] f) {
    // code here
    ArrayList<Integer> ans = new ArrayList<>();
    List<int[]> meet = new ArrayList<>();
    for (int i = 0; i < s.length; i++){
        meet.add(new int[]{f[i],s[i],i + 1});
    }

    meet.sort(Comparator.comparingInt(a -> a[0]));

    int last = -1;
    for (int[] m : meet){
        if (m[1] > last){
            last = m[0];
            ans.add(m[2]);
        }
    }

    Collections.sort(ans);
    return ans;

}

//Minimum Platforms Needed
public int minPlatform(int arr[], int dep[]) {
    Arrays.sort(arr);
    Arrays.sort(dep);
    int ans = 1,res = 0,a = 0,d = 0,n = arr.length;
    while (a < n && d < n){
        if (arr[a] <= dep[d]){
            res++;
            a++;
        }
        else{
            res--;
            d++;
        }
        // System.out.print(res + " ");
        ans = Math.max(ans,res);
    }
    return ans;
}

//Job Sequencing Problem
public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {

    int maxd = deadline[0];

    List<Job> job = new ArrayList<>();

    for (int i = 0; i < deadline.length; i++) {
        maxd = Math.max(maxd, deadline[i]);
        job.add(new Job(i, profit[i], deadline[i]));
    }

    job.sort((a, b) -> b.profit - a.profit);

    boolean[] check = new boolean[maxd];

    int count = 0;
    int profit1 = 0;

    for (Job curr : job) {

        for (int j = curr.last - 1; j >= 0; j--) {

            if (!check[j]) {
                check[j] = true;
                count++;
                profit1 += curr.profit;
                break;
            }
        }
    }

    ArrayList<Integer> result = new ArrayList<>();
    result.add(count);
    result.add(profit1);

    return result;
}

class Job {
    int id;
    int profit;
    int last;

    Job(int id, int profit, int last) {
        this.id = id;
        this.profit = profit;
        this.last = last;
    }
}

class Item {

    int value;
    int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}
double fractionalKnapsack(int W, Item[] arr, int n) {

    Arrays.sort(arr, (a, b) -> {
        double r1 = (double) a.value / a.weight;
        double r2 = (double) b.value / b.weight;
        return Double.compare(r2, r1);
    });

    int curWeight = 0;
    double finalvalue = 0.0;

    for (int i = 0; i < n; i++) {

        if (curWeight + arr[i].weight <= W) {
            curWeight += arr[i].weight;
            finalvalue += arr[i].value;
        } else {
            int remain = W - curWeight;
            finalvalue += (arr[i].value / (double) arr[i].weight) * remain;
            break;
        }
    }

    return finalvalue;
}

//Coin Change
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp,amount + 1);
    dp[0] = 0;
    for (int coin : coins){
        for (int j = coin; j <= amount; j++){
            dp[j] = Math.min(dp[j],1 + dp[j - coin]);
        }
    }

    return dp[amount] == amount + 1 ? -1 : dp[amount];
}

//Assign Cookies
public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);

    int i = 0,j = 0,ans = 0;
    while (j < s.length && i < g.length){
        if (g[i] <= s[j]){
            ans++;
            i++;
        }
        j++;
    }
    return ans;
}
void main(){
    int[] s = {39, 50, 6, 15, 2};
    int[] f = {62, 73, 33, 43, 9};
    ArrayList<Integer> res = maxMeetings(s, f);

    for (int idx : res) {
        System.out.print(idx + " ");
    }

    IO.println();

    int[] arr = {900, 940, 950, 1100, 1500, 1800};
    int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

    int result = minPlatform(arr, dep);

    System.out.println("Minimum Platforms Required: " + result);

    int[] deadline = {4, 1, 1, 1};
    int[] profit = {20, 10, 40, 30};

    ArrayList<Integer> result1 = jobSequencing(deadline, profit);

    System.out.println(result1);

    int n = 3;
    int weight = 50;

    Item[] arr2 = {
            new Item(100, 20),
            new Item(60, 10),
            new Item(120, 30)
    };

    double ans = fractionalKnapsack(weight, arr2, n);

    System.out.println("The maximum value is: " + String.format("%.2f", ans));

    int[] coins = {1, 2, 5};
    int amount = 11;

    int result3 = coinChange(coins, amount);

    System.out.println("Minimum coins required: " + result3);

    int[] g = {1, 2, 3};
    int[] s2 = {1, 1};

    int result4 = findContentChildren(g, s2);

    System.out.println("Maximum Content Children: " + result4);
}