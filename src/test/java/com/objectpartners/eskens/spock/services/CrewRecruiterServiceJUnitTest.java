package com.objectpartners.eskens.spock.services;

import com.objectpartners.eskens.spock.model.CommonCrew;
import com.objectpartners.eskens.spock.model.CrewMember;
import com.objectpartners.eskens.spock.services.external.CrewRecruiterService;
import com.objectpartners.eskens.spock.services.external.PayrollService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CrewRecruiterServiceJUnitTest implements CommonCrew {

    @InjectMocks
    private CrewRecruiterService crewRecruiterService = new CrewRecruiterService();

    @Mock
    PayrollService payrollService;


    @Test
    public void selectForBudget() throws Exception {
        when(payrollService.getSalary(KIRK)).thenReturn(100000L);
        when(payrollService.getSalary(SPOCK)).thenReturn(100000L);

        List<CrewMember> crew = crewRecruiterService.selectForBudget(200000L, ENTIRE_CREW);
        Assert.assertEquals(new ArrayList<>(asList(KIRK, SPOCK)), crew);

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = inOrder(payrollService);
        inOrder.verify(payrollService).getSalary(argThat(arg -> arg == CommonCrew.KIRK));
        inOrder.verify(payrollService).getSalary(argThat(arg -> arg == CommonCrew.SPOCK));
        inOrder.verifyNoMoreInteractions();
    }

}