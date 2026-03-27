package com.week9lab;

import com.google.gson.Gson;

public class LabExample {

    private static String dataFilePath = "/Users/tomasrodriguez/Documents/college/SJCC/Spring 2026/CIS-055 Data Structures & Algorithms/Module 9/lab/src/week9lab/src/main/java/com/week9lab/mapTestData.json";

    public static SinglyLinkedList process(SinglyLinkedList input) {
        SinglyLinkedList answer = new SinglyLinkedList();
        SinglyLinkedList reversed = input.deepClone();

        /*
         * STUDENTS NEED TO ADD/EDIT CODE STARTING HERE
         */

        // This is example code that can be deleted to help you get started.
        // Here I am simply traversing the list and copying the input list to the output
        // answer list.
        // Students code needs to do something different.

        // Count nodes in linked list
        LinkedListNode curr = reversed.head;
        Integer nodeCounter = 0;
        while (curr != null) {
            curr = curr.next;
            nodeCounter++;
        }

        // Calculate midpoint and reset curr pointer
        Integer midpoint = nodeCounter / 2;
        curr = reversed.head;

        // Reverse half of Linked List
        LinkedListNode next = null;
        LinkedListNode prev = null;
        while (midpoint > 0) {
            next = curr.next;
            curr.next = prev;
            curr.turnRight = !curr.turnRight;
            prev = curr;
            curr = next;
            midpoint--;
        }
        curr = prev;

        // Insert reversed half of linked list
        while (curr != null) {
            answer.insert(curr.turnRight, curr.street, curr.distance);
            curr = curr.next;
        }

        /*
         * STUDENTS DO NOT NEED TO EDIT ANY CODE AFTER THIS
         */

        // return the answer
        return answer;
    }

    public static void main(String[] args) {
        LabTestData td = new LabTestData();

        // read data
        String jsonStr = td.readJSON(dataFilePath);

        // convert data to obj
        Gson gson = new Gson();
        LabTestData testData = gson.fromJson(jsonStr, LabTestData.class);

        // run each test
        for (int i = 0; i < testData.tests.length; i += 1) {
            SinglyLinkedList input = testData.tests[i].input;
            SinglyLinkedList answer = process(input);
            SinglyLinkedList correctAnswer = testData.tests[i].output;

            System.out.printf("--------------------------------------\n");

            // if answer is correct, report results
            if (answer.equals(correctAnswer)) {
                System.out.printf("PASSED test %d :\n", i);
                // if answer is incorrect, report the error
            } else {
                System.out.printf("FAILED test %d\n", i);
            }

            System.out.printf("\ninput:\n");
            input.print();
            System.out.printf("\noutput:\n");
            answer.print();

            System.out.printf("--------------------------------------\n\n");
        }
    }
}
