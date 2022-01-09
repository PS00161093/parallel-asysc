package com.learnjava.parallelstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListSplitIteratorExampleTest {

    ArrayListSplitIteratorExample arrayListSplitIteratorExample = new ArrayListSplitIteratorExample();

    @RepeatedTest(5)
    void multiplyEachValueSerially() {
        int size = 1000000;
        ArrayList<Integer> integerArrayList = DataSet.generateArrayList(size);
        List<Integer> result = arrayListSplitIteratorExample.multiplyEachValue(integerArrayList, 2, false);

        assertEquals(result.size(), size);
    }

    @RepeatedTest(5)
    void multiplyEachValueParallely() {
        int size = 1000000;
        ArrayList<Integer> integerArrayList = DataSet.generateArrayList(size);
        List<Integer> result = arrayListSplitIteratorExample.multiplyEachValue(integerArrayList, 2, true);

        assertEquals(result.size(), size);
    }
}