//Implement Stack Using Arrays
class myStack {
    int[] stack;
    int size;
    int top;
    public myStack(int n) {
        top = -1;
        size = n;
        stack = new int[n];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(int x) {
        if (!isFull()){
            stack[++top] = x;
        }
    }

    public void pop() {
        if (!isEmpty()){
            top--;
        }
    }

    public int peek() {
        if (!isEmpty()){
            return stack[top];
        }
        return -1;
    }
}

//Implement Queue using Arrays
class myQueue {

    int[] queue;
    int size;
    int front;
    int back;
    int idx;

    public myQueue(int n) {
        queue = new int[n];
        size = n;
        front = 0;
        back = -1;
        idx = 0;
    }

    public boolean isEmpty() {
        return idx == 0;
    }

    public boolean isFull() {
        return idx == size;
    }

    public void enqueue(int x) {
        if (isFull()) return;

        back = (back + 1) % size;
        queue[back] = x;
        idx++;
    }

    public void dequeue() {
        if (isEmpty()) return;

        front = (front + 1) % size;
        idx--;
    }

    public int getFront() {
        if (!isEmpty()) {
            return queue[front];
        }
        return -1;
    }

    public int getRear() {
        if (!isEmpty()) {
            return queue[back];
        }
        return -1;
    }
}

//Implement Stack Using Queue
class MyStack1 {
    Queue<Integer> q;
    public MyStack1() {
        q = new java.util.LinkedList<>();
    }

    public void push(int x) {
        int size = q.size();
        q.offer(x);
        for (int i = 0; i < size; i++){
            q.offer(q.poll());
        }
    }

    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.size() == 0;
    }
}

//Implement Queue Using Stacks
class MyQueue1 {
    Stack<Integer> st1,st2;
    public MyQueue1() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    public void push(int x) {
        st1.push(x);
    }

    public int pop() {
        if (st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }

        if (st2.isEmpty()){
            return -1;
        }
        return st2.pop();
    }

    public int peek() {
        if (st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }

        if (st2.isEmpty()){
            return -1;
        }
        return st2.peek();
    }

    public boolean empty() {
        return st1.isEmpty() && st2.isEmpty();
    }
}

//Valid Parentheses
public boolean isValid(String s) {
    Stack<Character> st = new Stack<>();
    for (char c : s.toCharArray()){
        if (c == '('){
            st.push(')');
        }

        else if (c == '{'){
            st.push('}');
        }

        else if (c == '['){
            st.push(']');
        }

        else if (st.isEmpty() || st.pop() != c){
            return false;
        }
    }
    return st.isEmpty();
}

//Next Greater Element
public ArrayList<Integer> nextLargerElement(int[] arr) {
    ArrayList<Integer> ans = new ArrayList<>();
    Stack<Integer> st = new Stack<>();
    for (int i = arr.length - 1; i >= 0; i--){
        while (!st.isEmpty() && arr[st.peek()] <= arr[i]){
            st.pop();
        }
        ans.add(0,st.isEmpty() ? -1 : arr[st.peek()]);
        st.push(i);
    }
    return ans;
}

//Sort a Stack
public void sortStack(Stack<Integer> st) {
    if (st.isEmpty()) return;
    int x = st.pop();
    sortStack(st);
    insert(st,x);
}

public void insert(Stack<Integer> st,int x){
    if (st.isEmpty() || st.peek() <= x){
        st.push(x);
        return;
    }

    int temp = st.pop();
    insert(st,x);
    st.push(temp);
}

void main(){
    myStack st = new myStack(5);

    st.push(10);
    st.push(20);
    st.push(30);

    System.out.println("Top: " + st.peek());

    st.pop();

    System.out.println("Top after pop: " + st.peek());

    System.out.println("Is Empty: " + st.isEmpty());
    System.out.println("Is Full: " + st.isFull());

    st.push(40);
    st.push(50);
    st.push(60);

    System.out.println("Top: " + st.peek());
    System.out.println("Is Full: " + st.isFull());

    st.pop();
    System.out.println("Top after pop: " + st.peek());

    myQueue q = new myQueue(3);

    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);

    System.out.println("Front: " + q.getFront());
    System.out.println("Rear: " + q.getRear());

    q.dequeue();

    System.out.println("Front after dequeue: " + q.getFront());
    System.out.println("Rear after dequeue: " + q.getRear());

    q.enqueue(40);

    System.out.println("Front: " + q.getFront());
    System.out.println("Rear: " + q.getRear());

    System.out.println("Is Empty: " + q.isEmpty());
    System.out.println("Is Full: " + q.isFull());

    MyStack1 st1 = new MyStack1();

    st1.push(10);
    st1.push(20);
    st1.push(30);

    System.out.println("Top: " + st1.top());

    System.out.println("Pop: " + st1.pop());

    System.out.println("Top after pop: " + st1.top());

    System.out.println("Is Empty: " + st1.empty());

    System.out.println("Pop: " + st1.pop());
    System.out.println("Pop: " + st1.pop());

    System.out.println("Is Empty: " + st1.empty());

    MyQueue1 q1 = new MyQueue1();

    q1.push(10);
    q1.push(20);
    q1.push(30);

    System.out.println("Front: " + q1.peek());

    System.out.println("Pop: " + q1.pop());

    System.out.println("Front after pop: " + q1.peek());

    q1.push(40);
    q1.push(50);

    System.out.println("Front: " + q1.peek());

    System.out.println("Pop: " + q1.pop());
    System.out.println("Pop: " + q1.pop());

    System.out.println("Front: " + q1.peek());
    System.out.println("Is Empty: " + q1.empty());


    String s = "{[()]}";

    boolean result = isValid(s);

    System.out.println("Valid Parentheses: " + result);

    int[] numbers = {1, 3, 2, 4};

    ArrayList<Integer> result1 = nextLargerElement(numbers);

    System.out.println("Next Greater Elements: " + result1);

    Stack<Integer> st3 = new Stack<>();

    st3.push(3);
    st3.push(1);
    st3.push(4);
    st3.push(2);

    System.out.println("Before sorting: " + st3);

    sortStack(st3);

    System.out.println("After sorting: " + st3);
}