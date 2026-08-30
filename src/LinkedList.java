//Reverse of a Linked List
public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }

    ListNode next = reverseList(head.next);

    ListNode front = head.next;
    front.next = head;
    head.next = null;

    return next;
}

//Middle of the Linked List
public static ListNode middleNode(ListNode node) {

    ListNode slow = node;
    ListNode fast = node;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    return slow;
}

//Merge Two Sorted Linked Lists
public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode temp = new ListNode(0), ans = temp;
    while (list1 != null && list2 != null) {
        if (list1.val < list2.val) {
            ans.next = list1;
            list1 = list1.next;
        } else {
            ans.next = list2;
            list2 = list2.next;
        }
        ans = ans.next;
    }

    if (list1 != null) {
        ans.next = list1;
    } else {
        ans.next = list2;
    }
    return temp.next;
}

//Remove Nth Node from End
public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) {
        return null;
    }
    ListNode ans = new ListNode(0);
    ans.next = head;
    ListNode slow = ans, fast = ans;
    while (n-- > 0) {
        fast = fast.next;
    }

    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    slow.next = slow.next.next;
    return ans.next;
}

//Add Two Numbers
public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0), ans = dummy;
    int carry = 0;
    while (l1 != null || l2 != null || carry == 1) {
        int sum = 0;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        sum += carry;
        carry = sum / 10;
        ListNode next = new ListNode(sum % 10);
        dummy.next = next;
        dummy = dummy.next;
    }
    return ans.next;
}

//Delete a Node in Linked List
public static void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}

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

void main() {

    // Creating Linked List
    ListNode head = new ListNode(1, null);

    head.next = new ListNode(2, null);
    head.next.next = new ListNode(3, null);
    head.next.next.next = new ListNode(4, null);
    head.next.next.next.next = new ListNode(5, null);

    // Print original list
    IO.println("Original List:");
    printList(head);

    // Reverse the Linked List
    head = reverseList(head);

    // Print reversed list
    IO.println("Reversed List:");
    printList(head);


    ListNode list = new ListNode(1, null);

    list.next = new ListNode(2, null);
    list.next.next = new ListNode(3, null);
    list.next.next.next = new ListNode(4, null);
    list.next.next.next.next = new ListNode(5, null);

    IO.println("Original List:");
    printList(list);

    ListNode middle = middleNode(list);

    IO.println("Middle Node:");
    IO.println(middle.val);

    // First sorted list
    ListNode firstList = new ListNode(1, null);
    firstList.next = new ListNode(3, null);
    firstList.next.next = new ListNode(5, null);

    // Second sorted list
    ListNode secondList = new ListNode(2, null);
    secondList.next = new ListNode(4, null);
    secondList.next.next = new ListNode(6, null);

    IO.println("First List:");
    printList(firstList);

    IO.println("Second List:");
    printList(secondList);

    // Merge both lists
    ListNode mergedList = mergeTwoLists(firstList, secondList);

    IO.println("Merged List:");
    printList(mergedList);

    ListNode numberList = new ListNode(1, null);
    numberList.next = new ListNode(2, null);
    numberList.next.next = new ListNode(3, null);
    numberList.next.next.next = new ListNode(4, null);
    numberList.next.next.next.next = new ListNode(5, null);

    IO.println("Original List:");
    printList(numberList);

    // Remove 2nd node from the end
    int position = 2;

    ListNode resultList = removeNthFromEnd(numberList, position);

    IO.println("After Removing " + position + "nd Node From End:");
    printList(resultList);

    // First number: 342
    // Stored in reverse: 2 -> 4 -> 3
    ListNode firstNumber = new ListNode(2, null);
    firstNumber.next = new ListNode(4, null);
    firstNumber.next.next = new ListNode(3, null);

    // Second number: 465
    // Stored in reverse: 5 -> 6 -> 4
    ListNode secondNumber = new ListNode(5, null);
    secondNumber.next = new ListNode(6, null);
    secondNumber.next.next = new ListNode(4, null);

    IO.println("First Number:");
    printList(firstNumber);

    IO.println("Second Number:");
    printList(secondNumber);

    // Add the two numbers
    ListNode sumList = addTwoNumbers(firstNumber, secondNumber);

    IO.println("Sum:");
    printList(sumList);

    ListNode numberList1 = new ListNode(4, null);
    numberList1.next = new ListNode(5, null);
    numberList1.next.next = new ListNode(1, null);
    numberList1.next.next.next = new ListNode(9, null);

    IO.println("Original List:");
    printList(numberList1);

    // Delete node with value 5
    ListNode deleteTarget = numberList1.next;

    deleteNode(deleteTarget);

    IO.println("After Deletion:");
    printList(numberList1);
}