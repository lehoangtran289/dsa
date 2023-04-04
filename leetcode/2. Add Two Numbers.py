from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        carry = 0
        head = ListNode()
        res = head
        while (l1 != None or l2 != None):
            if l1 != None:
                carry += l1.val
                l1 = l1.next
            if l2 != None:
                carry += l2.val
                l2 = l2.next
            head.val = carry % 10
            carry = carry // 10
            if (l1 != None or l2 != None):
                head.next = ListNode()
                head = head.next
        if carry != 0:
            head.val = carry
        return res

if __name__ == '__main__':
    print(Solution.addTwoNumbers(Solution(), ListNode(2, ListNode(4, ListNode(3))), ListNode(5, ListNode(6, ListNode(4)))))
    