package com.concordusa.unittestdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MutableIntegerTest {

    private MutableInteger tested;

    @Test
    public void whenMutableIntegerConstructed_CurrentValueIsEqualToConstructorParameter(){
        //arrange
        Integer expected = 5;
        tested = new MutableInteger(expected);

        //act
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenAdd_CurrentValueIsIncremented(){
        //arrange
        Integer expected = 10;
        tested = new MutableInteger(5);

        //act
        tested.add(5);
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenSubtract_CurrentValueIsDecrementedParameter(){
        //arrange
        Integer expected = 0;
        tested = new MutableInteger(5);

        //act
        tested.subtract(5);
        Integer actual = tested.getCurrentValue();

        //assert
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("additionArguments")
    public void whenAdd_CurrentValueIncrementsByExpectedAmount(Integer initialValue, Integer increment, Integer expectation){
        //arrange
        tested = new MutableInteger(initialValue);

        //act
        tested.add(increment);

        //assert
        assertThat(tested.getCurrentValue()).isEqualTo(expectation);
    }

    public static List<Arguments> additionArguments(){
        return List.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 2, 4),
                Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE, -1),
                Arguments.of(0, 0, 0)
        );
    }
}