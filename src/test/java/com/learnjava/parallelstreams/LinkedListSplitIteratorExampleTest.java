package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListSplitIteratorExampleTest {

    LinkedListSplitIteratorExample LinkedListSplitIteratorExample = new LinkedListSplitIteratorExample();

    @RepeatedTest(5)
    void multiplyEachValueSerially() {
        int size = 1000000;
        LinkedList<Integer> integerLinkedList = DataSet.generateIntegerLinkedList(size);
        List<Integer> result = LinkedListSplitIteratorExample.multiplyEachValue(integerLinkedList, 2, false);

        assertEquals(result.size(), size);
    }

    @RepeatedTest(5)
    void multiplyEachValueParallely() {
        int size = 1000000;
        LinkedList<Integer> integerLinkedList = DataSet.generateIntegerLinkedList(size);
        List<Integer> result = LinkedListSplitIteratorExample.multiplyEachValue(integerLinkedList, 2, true);

        assertEquals(result.size(), size);
    }
}