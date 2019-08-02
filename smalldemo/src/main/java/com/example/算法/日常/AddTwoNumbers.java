package com.example.算法.日常;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:cuijialei
 * @Date: 2019/7/2
 * @Describe
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = getNumber(l1);
        long num2 = getNumber(l2);
        long sum = num1 + num2;
        System.out.println(num1+"+"+num2+"="+sum);
        ListNode listNode = getNode(sum);
        printNode(listNode);
        return listNode;
    }

    private long getNumber(ListNode l1){
        long num = 0;
        long i = 1;
        while (true){
            num = num + (l1.val*i);
            i = i*10;
            if(null == l1.next){
                break;
            }
            l1 = l1.next;
        }
        return num;
    }


    private ListNode getNode(long num){
        ListNode head = null;
        ListNode p = head;
        while (true){
            Number val = num%10;
            ListNode listNode = new ListNode(val.intValue());
            if(null == head){
                head = listNode;
                head.next = null;
                p = head;
            }else{
                while(p.next!=null){
                    p=p.next;//p结点始终指向最后一个结点
                }
                p.next=listNode;
            }
            if(num/10 == 0){
                break;
            }else{
                num = num/10;
            }
        }


        return head;
    }
    private void  printNode(ListNode l1){
        while (true){
            System.out.println(l1.val);
            l1 = l1.next;
            if(l1 == null){
                break;
            }
        }
    }


    public static void main(String[] args) {

        System.out.println(10%10);

        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);
        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);

//        ListNode listNode1 = new ListNode(9);
//        ListNode listNode2 = new ListNode(1);
//        listNode2.next = new ListNode(9);
//        listNode2.next.next = new ListNode(9);
//        listNode2.next.next.next  = new ListNode(9);
//        listNode2.next.next.next .next  = new ListNode(9);
//        listNode2.next.next.next .next .next  = new ListNode(9);
//        listNode2.next.next.next .next .next .next  = new ListNode(9);
//        listNode2.next.next.next .next .next .next .next  = new ListNode(9);
//        listNode2.next.next.next .next .next .next .next .next  = new ListNode(9);
//        listNode2.next.next.next .next .next .next .next .next .next  = new ListNode(9);
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode sumNode = addTwoNumbers.nextAddTwoNumbers(listNode1,listNode2);

        System.out.println(sumNode);


    }

    public ListNode nextAddTwoNumbers(ListNode l1, ListNode l2) {

        List<ListNode> list1 = new ArrayList();
        List<ListNode> list2 = new ArrayList();
        while (true){
            list1.add(l1);
            if(null == l1.next){
                break;
            }
            l1 = l1.next;
        }
        while (true){
            list2.add(l2);
            if(null == l2.next){
                break;
            }
            l2 = l2.next;
        }

        int length1 = list1.size();
        int length2 = list2.size();

        int bigLength = (length1>length2)?length1:length2;

        int nextAddNum = 0;
        ListNode firstNode = null;
        ListNode finalNode = null;
        for(int i = 0 ; i < bigLength + 1 ; i++){
            if(firstNode == null){
                int list1Num = 0;
                int list2Num = 0;
                if(i < length1){
                    list1Num = list1.get(i).val;
                }
                if(i < length2){
                    list2Num = list2.get(i).val;
                }
                int j  = list1Num + list2Num;
                int val = (j + nextAddNum)%10;
                firstNode = new ListNode(val);
                firstNode.next = null;
                finalNode = firstNode;
                if(j + nextAddNum >= 10){
                    nextAddNum = 1;
                }else {
                    nextAddNum = 0 ;
                }
            }else{
                while(finalNode.next!=null){
                    //p结点始终指向最后一个结点
                    finalNode=finalNode.next;
                }
                int list1Num = 0;
                int list2Num = 0;
                if(i < length1){
                    list1Num = list1.get(i).val;
                }
                if(i < length2){
                    list2Num = list2.get(i).val;
                }
                int j  = list1Num + list2Num;
                int val = (j + nextAddNum)%10;
                if(val == 0 && i >= length1 && i >= length2){
                    break;
                }
                ListNode listNode = new ListNode(val);
                finalNode.next = listNode;
                if(j + nextAddNum >= 10){
                    nextAddNum = 1;
                }else {
                    nextAddNum = 0 ;
                }
            }
        }

        return firstNode;
    }






}
