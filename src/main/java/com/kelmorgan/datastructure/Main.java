package com.kelmorgan.datastructure;


import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        AddTwoNumbers.ListNode node1 = new AddTwoNumbers.ListNode(1);
        node1.next = new AddTwoNumbers.ListNode(2);
        node1.next.next = new AddTwoNumbers.ListNode(3);
        AddTwoNumbers.ListNode node2 = new AddTwoNumbers.ListNode(1);
        node2.next = new AddTwoNumbers.ListNode(2);
        node2.next.next = new AddTwoNumbers.ListNode(3);

        AddTwoNumbers.ListNode listNode = AddTwoNumbers.addTwoNumbers(node1, node2);

        System.out.println(listNode);

    }


}
