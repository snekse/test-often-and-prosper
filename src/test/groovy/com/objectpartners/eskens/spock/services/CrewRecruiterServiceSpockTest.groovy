package com.objectpartners.eskens.spock.services

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.objectpartners.eskens.spock.model.CommonCrew
import com.objectpartners.eskens.spock.services.external.CrewRecruiterService
import com.objectpartners.eskens.spock.services.external.PayrollService
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Unroll

import static java.lang.Integer.MAX_VALUE


class CrewRecruiterServiceSpockTest extends Specification implements CommonCrew {

    @Subject CrewRecruiterService crewRecruiterService

    @Collaborator PayrollService payrollService = Mock()

    void "SelectForBudget spends budget in crew priority order and avoids unnecessary calls to payroll service"() {

        when: 'We have enough to pay for Kirk and Spock'
        def crew = crewRecruiterService.selectForBudget(200000, ENTIRE_CREW)

        then: 'We only make our expensive external system salary calls in the order we expect - first add Kirk'
        1 * payrollService.getSalary(KIRK) >> 100000

        then: 'add Spock'
        1 * payrollService.getSalary(SPOCK) >> 100000

        then: 'no more calls to that service since we have depleted our budget'
        0 * payrollService.getSalary(_)

        and: 'our crew consists of just Kirk and Spock'
        crew == [KIRK, SPOCK]
    }

    @SuppressWarnings("GroovyAssignabilityCheck")

    @Unroll("A budget of #salaryCap pays for #expectedCrew")
    void "SelectForBudget adds crew in priority order until budget depleted"() {
        given: 'a flat rate of 100000 per member'
        payrollService.getSalary(_) >> 100000

        when: 'selecting crew at various salary cap levels'
        def crew = crewRecruiterService.selectForBudget(salaryCap, ENTIRE_CREW)

        then:
        crew == expectedCrew

        where:
        salaryCap   || expectedCrew
        0           || []
        (100000-1)  || []
        100000      || [KIRK]
        100001      || [KIRK]
        200000      || [KIRK, SPOCK]
        MAX_VALUE   || ENTIRE_CREW
    }

    @PendingFeature //Test is considered skipped until dev implements it
    void "Crew has a red shirt"() {

        when: 'We have enough to pay for Kirk and Spock'
        def crew = crewRecruiterService.selectForBudget(200000, ENTIRE_CREW).findAll { it != SPOCK }

        then: 'We only make our expensive external system salary calls in the order we expect - first add Kirk'
        1 * payrollService.getSalary(KIRK) >> 100000

        then: 'add Spock'
        1 * payrollService.getSalary(SPOCK) >> 100000

        then: 'no more calls to that service since we have depleted our budget'
        0 * payrollService.getSalary(_)

        and: 'our crew has a red shirt'
        crew.collect { it.fullName }.join(':') == [KIRK, RED_SHIRT].collect { it.fullName }.join(':')
    }

}
