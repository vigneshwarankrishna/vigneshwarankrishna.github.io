package com.interview.questions.practise;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SimpleClassTest {

    @InjectMocks
    SimpleClass test;

    @Mock
    SumService sumService;

    @Test
    public void sumOfTwoNumbers(){
        int[] input = new int[]{1,2,3};
        int result = 6;
        when(sumService.getNumbers()).thenReturn(input);
        int classResponse = test.sumOfTwoNumbers();
        assertEquals(classResponse,result);
    }


}
