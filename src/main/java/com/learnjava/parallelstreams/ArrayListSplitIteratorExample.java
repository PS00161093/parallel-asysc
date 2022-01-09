package com.learnjava.parallelstreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.stopWatchReset;
import static com.learnjava.util.CommonUtil.timeTaken;

public class ArrayListSplitIteratorExample {

    public List<Integer> multiplyEachValue(ArrayList<Integer> integerList, int multiplier, boolean isParallel) {
        startTimer();
        Stream<Integer> integerStream = integerList.stream();
        if(isParallel) integerList.parallelStream();
        List<Integer> resultList = integerStream
                .map(integer -> integer * multiplier)
                .collect(Collectors.toList());
        timeTaken();
        stopWatchReset();
        return resultList;
    }
}
