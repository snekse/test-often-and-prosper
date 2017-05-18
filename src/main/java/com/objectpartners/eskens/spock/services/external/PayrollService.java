package com.objectpartners.eskens.spock.services.external;

import com.objectpartners.eskens.spock.model.CrewMember;
import org.springframework.stereotype.Service;

/**
 * External service to get sensitive HR information
 */
@Service
public class PayrollService {

    public long getSalary(CrewMember crewMember) {
        System.out.println("Getting salary for " + crewMember.getFullName());
        long salary = Math.max(((10 - crewMember.getId()) * 10000), 1);
        System.out.println("Salary: " + salary);
        return salary;
    }
}
