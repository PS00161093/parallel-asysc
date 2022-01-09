package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static com.learnjava.forkjoin.StringTransformExample.addNameLengthTransform;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    protected List<String> compute() {

        if (inputList.size() <= 1) {
            List<String> resultList = new ArrayList<>();
            inputList.forEach(name -> resultList.add(addNameLengthTransform(name)));
            return resultList;
        }

        int mid = inputList.size() / 2;
        ForkJoinTask<List<String>> leftInputList = new ForkJoinUsingRecursion(inputList.subList(0, mid)).fork();
        inputList = inputList.subList(mid, inputList.size());
        List<String> rightResult = compute();
        List<String> leftResult = leftInputList.join();
        leftResult.addAll(rightResult);

        return leftResult;
    }

    public static void main(String[] args) {
        stopWatch.start();

        List<String> resultList;
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        resultList = forkJoinPool.invoke(forkJoinUsingRecursion);

        stopWatch.stop();
        log("Final Result : " + resultList);
        log("Total Time Taken : " + stopWatch.getTime());
    }
}
