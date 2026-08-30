
//Get Intersection of 2 Nodes
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
        return null;
    }
    ListNode temp1 = headA, temp2 = headB;
    while (temp1 != temp2) {
        temp1 = temp1.next;
        temp2 = temp2.next;

        if (temp1 == temp2) {
            return temp1;
        }

        if (temp1 == null) {
            temp1 = headB;
        }
        if (temp2 == null) {
            temp2 = headA;
        }
    }
    return temp1;
}

//Linked List Cycle
public boolean hasCycle(ListNode head) {
    ListNode slow = head,fast = head;
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast){
            return true;
        }
    }
    return false;
}

//Palindrome Linked List
public ListNode reverse(ListNode head){
    ListNode temp = head,prev = null;
    while (temp != null){
        ListNode next = temp.next;
        temp.next = prev;
        prev = temp;
        temp = next;
    }
    return prev;
}
public void print(ListNode head){
    ListNode temp = head;
    while (temp != null){
        System.out.print(temp.val + " ");
        temp = temp.next;
    }
    System.out.println();
}
public boolean isPalindrome(ListNode head) {
    ListNode slow = head,fast = head;
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
    }

    ListNode rev = reverse(slow);
    // slow.next = null;
    ListNode first = head;
    ListNode second = rev;
    while (second != null){
        if (first.val != second.val){
            reverse(rev);//To restore the original linked list
            return false;
        }
        first = first.next;
        second = second.next;
    }
    reverse(rev);//To restore the original linked list
    return true;
}

//Linked List Cycle - II
public ListNode detectCycle(ListNode head) {
    ListNode slow = head,fast = head;
    while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast){
            slow = head;
            while (slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    return null;
}
void main() {
    ListNode commonPart = new ListNode(8, null);
    commonPart.next = new ListNode(4, null);
    commonPart.next.next = new ListNode(5, null);

    ListNode firstList = new ListNode(4, null);
    firstList.next = new ListNode(1, null);
    firstList.next.next = commonPart;

    ListNode secondList = new ListNode(5, null);
    secondList.next = new ListNode(6, null);
    secondList.next.next = commonPart;

    ListNode intersection = getIntersectionNode(firstList, secondList);

    if (intersection != null) {
        System.out.println("Intersection Node: " + intersection.val);
    } else {
        System.out.println("No Intersection");
    }

    ListNode cycleList = new ListNode(1, null);
    cycleList.next = new ListNode(2, null);
    cycleList.next.next = new ListNode(3, null);
    cycleList.next.next.next = new ListNode(4, null);

    // Create cycle: 4 -> 3
    cycleList.next.next.next.next = cycleList.next.next;

    boolean result = hasCycle(cycleList);

    System.out.println("Has Cycle: " + result);

    ListNode palindromeList = new ListNode(1, null);
    palindromeList.next = new ListNode(2, null);
    palindromeList.next.next = new ListNode(3, null);
    palindromeList.next.next.next = new ListNode(2, null);
    palindromeList.next.next.next.next = new ListNode(1, null);

    System.out.println("Original List:");
    print(palindromeList);

    boolean result1 = isPalindrome(palindromeList);

    System.out.println("Is Palindrome: " + result1);

    System.out.println("List After Checking:");
    print(palindromeList);

    ListNode cycleList3 = new ListNode(1, null);

    cycleList3.next = new ListNode(2, null);
    cycleList3.next.next = new ListNode(3, null);
    cycleList3.next.next.next = new ListNode(4, null);
    cycleList3.next.next.next.next = new ListNode(5, null);

    // Create cycle:
    // 5 -> 3
    ListNode cycleStart = cycleList3.next.next;

    cycleList3.next.next.next.next.next = cycleStart;

    // Detect cycle
    ListNode result2 = detectCycle(cycleList3);

    if (result2 != null) {
        System.out.println("Cycle starts at node: " + result2.val);
    } else {
        System.out.println("No cycle");
    }
}
