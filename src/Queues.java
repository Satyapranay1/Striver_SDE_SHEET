//Next Smaller Element
static ArrayList<Integer> nextSmallerEle(int[] arr) {
    Stack<Integer> st = new Stack<>();
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = arr.length - 1; i >= 0; i--){
        while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
        ans.add(0,st.isEmpty() ? -1 : arr[st.peek()]);
        st.push(i);
    }
    return ans;
}

//LRU Cache

class LRUCache {
    class Node{
        int key,val;
        Node prev,next;
        Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1,-1);
    Node tail = new Node(-1,-1);
    HashMap<Integer,Node> map;
    int cap;

    public void addNode(Node newNode){
        Node temp = head.next;
        newNode.next = temp;
        newNode.prev = head;
        head.next = newNode;
        temp.prev = newNode;
    }

    public void deleNode(Node delNode){
        Node delPrev = delNode.prev;
        Node delNext = delNode.next;
        delPrev.next = delNext;
        delNext.prev = delPrev;
    }
    public LRUCache(int capacity) {
        map  = new HashMap<>();
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            Node currNode = map.get(key);
            int val = currNode.val;
            map.remove(key);
            deleNode(currNode);
            addNode(currNode);
            map.put(key,head.next);
            return val;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node currNode = map.get(key);
            map.remove(key);
            deleNode(currNode);
        }

        if (map.size() == cap){
            Node lru = tail.prev;
            deleNode(lru);
            map.remove(lru.key);
        }

        addNode(new Node(key,value));
        map.put(key,head.next);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//Largest Rectangle in Histogram
public int largestRectangleArea(int[] heights) {
    if (heights.length == 1) return heights[0];
    int[] nse = new int[heights.length];
    int[] pse = new int[heights.length];
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < heights.length; i++){
        while (!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
        pse[i] = (st.isEmpty() ? 0 : st.peek() + 1);
        st.push(i);
    }
    st.clear();
    for (int i = heights.length - 1; i >= 0; i--){
        while (!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
        nse[i] = (st.isEmpty() ? heights.length - 1 : st.peek() - 1);
        st.push(i);
    }
    int max = 0;
    for (int i = 0; i < heights.length; i++) max = Math.max(max, (nse[i] - pse[i] + 1) * heights[i]);
    return max;
}

//Sliding Window Maximum
public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> dq = new java.util.LinkedList<>();
    int[] ans = new int[nums.length - k + 1];
    for (int i = 0; i < nums.length; i++){
        //Remove elements that are out of window
        while (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();
        //Remove elements that are lower than curr element
        while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
        dq.offer(i);
        if (i >= k - 1) ans[i - k + 1] = nums[dq.peekFirst()];
    }
    return ans;
}

//Min Stack
class MinStack {
    Stack<Integer> st;
    int mini;
    public MinStack() {
        st = new Stack<>();
        mini = Integer.MAX_VALUE;
    }

    public void push(int value) {
        if (value <= mini){
            st.push(mini);
            mini = value;
        }
        st.push(value);
    }

    public void pop() {
        if (mini == st.pop()){
            mini = st.pop();
        }
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return mini;
    }
}

//Rotten Oranges
public int orangesRotting(int[][] grid) {
    int fresh = 0,count = -1;
    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    Queue<int[]> q = new java.util.LinkedList<>();
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) fresh++;
            if (grid[i][j] == 2) q.offer(new int[] { i, j });
        }
    }
    if (fresh == 0) return 0;
    if (q.isEmpty()) return -1;
    while (!q.isEmpty()) {
        int size = q.size();
        while (size-- > 0) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 1) {
                    fresh--;
                    grid[nx][ny] = 2;
                    q.offer(new int[] { nx, ny });
                }
            }
        }
        count++;
    }
    return fresh > 0 ? -1 : count;
}

//Stock Span Problem
public ArrayList<Integer> calculateSpan(int[] arr) {
    ArrayList<Integer> ans = new ArrayList<>();
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < arr.length; i++){
        while (!st.isEmpty() && arr[st.peek()] <= arr[i]){
            st.pop();
        }

        ans.add((st.isEmpty()) ? i + 1 : i - st.peek());
        st.push(i);
    }
    return ans;

}

//Celebrity Problem
public int celebrity(int mat[][]) {
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < mat.length; i++){
        st.push(i);
    }

    while (st.size() > 1){
        int a = st.pop();
        int b = st.pop();

        if (mat[a][b] == 1){
            st.push(b);
        }
        else{
            st.push(a);
        }
    }

    if (st.isEmpty()){
        return -1;
    }

    int el = st.pop();

    for (int i = 0; i < mat.length; i++){
        if (i != el && (mat[i][el] == 0 || mat[el][i] == 1)){
            return -1;
        }
    }
    return el;
}
void main(){
    int[] numbers = {4, 8, 5, 2, 25};

    ArrayList<Integer> result = nextSmallerEle(numbers);

    System.out.println("Next Smaller Elements: " + result);

    LRUCache cache = new LRUCache(2);

    cache.put(1, 10);
    cache.put(2, 20);

    System.out.println("Get 1: " + cache.get(1));

    cache.put(3, 30);

    System.out.println("Get 2: " + cache.get(2));
    System.out.println("Get 3: " + cache.get(3));

    cache.put(4, 40);

    System.out.println("Get 1: " + cache.get(1));
    System.out.println("Get 3: " + cache.get(3));
    System.out.println("Get 4: " + cache.get(4));

    int[] heights = {2, 1, 5, 6, 2, 3};

    int result1 = largestRectangleArea(heights);

    System.out.println("Largest Rectangle Area: " + result1);

    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;

    int[] result2 = maxSlidingWindow(nums, k);

    System.out.println("Maximums in Sliding Window: " + Arrays.toString(result2));

    MinStack stack = new MinStack();

    stack.push(5);
    stack.push(3);
    stack.push(7);
    stack.push(2);

    System.out.println("Top: " + stack.top());
    System.out.println("Minimum: " + stack.getMin());

    stack.pop();
    System.out.println("After pop:");
    System.out.println("Top: " + stack.top());
    System.out.println("Minimum: " + stack.getMin());

    stack.pop();
    System.out.println("After another pop:");
    System.out.println("Top: " + stack.top());
    System.out.println("Minimum: " + stack.getMin());

    int[][] grid = {
            {2, 1, 1, 0, 1},
            {1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 1, 2, 1},
            {1, 1, 0, 1, 1}
    };

    int result3 = orangesRotting(grid);

    System.out.println("Minimum Minutes: " + result3);

    int[] arr = {100, 80, 60, 70, 60, 75, 85};

    ArrayList<Integer> result4 = calculateSpan(arr);

    System.out.println("Stock Span: " + result4);

    int[][] mat = {
            {0, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 1, 0, 1},
            {0, 1, 1, 0}
    };

    int result5 = celebrity(mat);

    System.out.println("Celebrity: " + result5);
}