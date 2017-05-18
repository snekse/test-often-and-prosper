package com.objectpartners.eskens.spock.services.external;

import com.objectpartners.eskens.spock.model.CrewMember;
import com.objectpartners.eskens.spock.services.external.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Help select your crew and stay inside of constraints
 * Created by derek on 4/13/17.
 */
@Service
public class CrewRecruiterService {

    @Autowired protected PayrollService payrollService;

    public List<CrewMember> selectForBudget(long salaryCap, List<CrewMember> prospects) {
        long remainingBudget = salaryCap;
        List<CrewMember> crew = new ArrayList<>(prospects.size());

        for (CrewMember crewMember : prospects) {
            //Add the crew member if we can afford them
            if (remainingBudget > 0) {
                long salary = payrollService.getSalary(crewMember);
                if (salary <= remainingBudget) {
                    System.out.println("Remaining budget is " + remainingBudget);
                    remainingBudget -= salary;
                    crew.add(crewMember);
                }
            }
        }

        return crew;
    }
}
