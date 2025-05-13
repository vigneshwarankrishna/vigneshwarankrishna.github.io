package com.interview.questions.practise;

public class SimpleClass {

    public void setSumService(SumService sumService) {
        this.sumService = sumService;
    }

    private SumService sumService;

    public int sumOfTwoNumbers(){
        int[] numbers = sumService.getNumbers();
        int sum = 0;
        for(int n:numbers){
            sum+=n;
        }
        return sum;
    }

}
