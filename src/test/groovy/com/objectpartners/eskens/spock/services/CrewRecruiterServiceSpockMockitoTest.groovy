package com.objectpartners.eskens.spock.services

import com.objectpartners.eskens.spock.model.CommonCrew
import com.objectpartners.eskens.spock.model.CrewMember
import com.objectpartners.eskens.spock.services.external.CrewRecruiterService
import com.objectpartners.eskens.spock.services.external.PayrollService
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.ArgumentMatchers.any

class CrewRecruiterServiceSpockMockitoTest extends Specification implements CommonCrew {

    @InjectMocks CrewRecruiterService crewRecruiterService

    @Mock PayrollService payrollService

    def setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Unroll("A budget of #salaryCap pays for #expectedCrew")
    void "SelectForBudget can be tested with Mockito mocks"() {
        Mockito.when(payrollService.getSalary(any(CrewMember))).thenReturn(100000L)

        when: 'selecting crew at various salary cap levels'
        def crew = crewRecruiterService.selectForBudget(200000, ENTIRE_CREW)

        then:
        crew == [KIRK, SPOCK]
    }
}
