import java.util.ArrayList;

//Implement Max Heap
class maxHeap {

    ArrayList<Integer> heap;
    public maxHeap() {
        heap = new ArrayList<>();
    }

    public void push(int x) {
        heap.add(x);
        int idx = heap.size() - 1;

        while (idx > 0){
            int parent = (idx - 1) / 2;
            if (heap.get(parent) >= heap.get(idx)) break;
            int temp = heap.get(idx);
            heap.set(idx,heap.get(parent));
            heap.set(parent,temp);
            idx = parent;
        }
    }

    public void pop() {
        if (heap.isEmpty()) return;
        int last = heap.remove(heap.size() - 1);
        if (heap.isEmpty()) return;
        heap.set(0,last);
        int idx = 0;
        while (true){
            int left = 2 * idx + 1,right = 2 * idx + 2;
            int largest = idx;
            if (left < heap.size() && heap.get(largest) < heap.get(left)) largest = left;
            if (right < heap.size() && heap.get(largest) < heap.get(right)) largest = right;
            if (largest == idx) return;
            int temp = heap.get(idx);
            heap.set(idx,heap.get(largest));
            heap.set(largest,temp);
            idx = largest;
        }
    }

    public int peek() {
        if (heap.isEmpty()) return -1;
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }
}

//Kth Largest in an Array
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++){
        if (pq.size() < k){
            pq.offer(nums[i]);
        }
        else{
            if (pq.peek() < nums[i]){
                pq.poll();
                pq.offer(nums[i]);
            }
        }
    }
    return pq.peek();
}

//Top K Sum Pairs
public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
    int n = a.length;
    ArrayList<Integer> ans = new ArrayList<>();
    Arrays.sort(a);
    Arrays.sort(b);
    PriorityQueue<int[]> pq = new PriorityQueue<>((a1,b1) -> b1[0] - a1[0]);
    Set<String> vis = new HashSet<>();
    pq.offer(new int[]{a[n - 1] + b[n - 1],n - 1,n - 1});
    vis.add((n - 1) + "," + (n - 1));
    while (k-- > 0 && !pq.isEmpty()){
        int[] curr = pq.poll();
        int val = curr[0],i = curr[1],j = curr[2];
        ans.add(val);

        if (i - 1 >= 0){
            if (!vis.contains((i - 1) + "," + j)){
                pq.offer(new int[]{a[i - 1] + b[j],i - 1,j});
                vis.add((i - 1) + "," + j);
            }
        }

        if (j - 1 >= 0){
            if (!vis.contains(i + "," + (j - 1))){
                pq.offer(new int[]{a[i] + b[j - 1],i,j - 1});
                vis.add(i + "," + (j - 1));
            }
        }
    }
    return ans;
}

//Top K Frequent Elements
public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for (int curr : nums) map.put(curr,map.getOrDefault(curr,0) + 1);
    int[] ans = new int[k];
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
    for (int key:map.keySet()){
        pq.add(key);
        if (pq.size() > k) pq.poll();
    }
    for (int i = 0; i < k; i++) ans[i] = pq.poll();
    return ans;
}

void main(){
    maxHeap heap = new maxHeap();

    heap.push(10);
    heap.push(20);
    heap.push(5);
    heap.push(30);
    heap.push(15);

    System.out.println("Max: " + heap.peek());
    System.out.println("Size: " + heap.size());

    heap.pop();

    System.out.println("Max after pop: " + heap.peek());
    System.out.println("Size after pop: " + heap.size());

    int[] numbers = {3, 2, 1, 5, 6, 4};
    int k = 2;

    int result = findKthLargest(numbers, k);

    System.out.println("Kth Largest Element: " + result);

    int[] a = {1, 4, 2, 3};
    int[] b = {2, 5, 1, 6};

    int k1 = 4;

    ArrayList<Integer> result1 = topKSumPairs(a, b, k1);

    System.out.println(result1);

    int[] nums = {1, 1, 1, 2, 2, 3};

    int k2 = 2;

    int[] result2 = topKFrequent(nums, k2);

    System.out.println(Arrays.toString(result2));
}