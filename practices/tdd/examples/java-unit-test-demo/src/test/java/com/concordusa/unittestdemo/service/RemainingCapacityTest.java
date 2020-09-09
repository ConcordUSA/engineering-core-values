package com.concordusa.unittestdemo.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RemainingCapacityTest {

    private RemainingCapacity tested;
    private int partySize;

    @Test
    public void whenAdmitParty_CapacityReducedByPartySize(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;

        //act
        tested.admitParty(partySize);

        //assert
        Mockito.verify(mockMutableInteger, Mockito.times(1)).subtract(eq(12));
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_PartySizesExceedingCapacityRejected(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(0);

        boolean actual = tested.admitPartyIfAllowedEntry(12);

        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_RejectedPartiesDoNotAffectRemainingCapacity(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(0);

        //act
        tested.admitPartyIfAllowedEntry(12);

        //assert
        verify(mockMutableInteger, times(0)).subtract(any());
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_PartiesWithinCapacityAllowedEntry(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(15);

        boolean actual = tested.admitPartyIfAllowedEntry(12);

        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void whenAdmitPartyIfAllowedEntry_AcceptedPartiesDoAffectRemainingCapacity(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        partySize = 12;
        when(mockMutableInteger.getCurrentValue()).thenReturn(15);

        //act
        tested.admitPartyIfAllowedEntry(12);

        //assert
        verify(mockMutableInteger, times(1)).subtract(eq(12));
    }

    @Test
    public void whenEnforceCapacityRequiresSpaceItIsAdded_a(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        when(mockMutableInteger.getCurrentValue()).thenReturn(-12);

        tested.enforceCapacity_a();

        verify(mockMutableInteger, times(1)).subtract(-12);
    }

    @Test
    public void whenEnforceCapacityRequiresSpaceItIsAdded_b(){
        //arrange
        MutableInteger mockMutableInteger = Mockito.mock(MutableInteger.class);
        tested = new RemainingCapacity(mockMutableInteger);
        when(mockMutableInteger.getCurrentValue()).thenReturn(-12);

        tested.enforceCapacity_b();

        verify(mockMutableInteger, times(1)).add(12);
    }

    @Test
    public void whenEnforceCapacityRequiresSpaceItIsAdded_either(){
        //arrange
        tested = new RemainingCapacity(new MutableInteger(-12));

        //act
        tested.enforceCapacity_a();

        //assert
        assertThat(tested.getRemainingCapacity()).isEqualTo(0);
    }
}