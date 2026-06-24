public class ListNode {
     int val;
     ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

 class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null;
        while (curr != null){
         ListNode nextTemp = curr.next;
         curr.next = prev;
         prev = curr;
         curr = nextTemp;
        }

        ListNode first = head;
        ListNode second = prev;
        while (second != null){
         ListNode temp1 = first.next;
         ListNode temp2 = second.next;

         first.next = second;
         second.next = temp1;

         first = temp1;
         second = temp2;
        }
    }

    public static void main(String[] args) {
      int[] arr = {2,4,6,8};

      ListNode head = new ListNode(arr[0]);
      ListNode current = head;
      for (int i=0; i<arr.length; i++){
         current.next = new ListNode(arr[i]);
         current = current.next;
      }

      Solution obj = new Solution();
      obj.reorderList(head);

      ListNode printNode = head;
      while (printNode != null){
         System.out.println(printNode.val + " ");
         printNode = printNode.next;
      }
    }
}

