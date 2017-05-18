package com.objectpartners.eskens.spock.model;

import java.util.ArrayList;
import java.util.List;

import static com.objectpartners.eskens.spock.model.Role.*;
import static java.util.Arrays.asList;

/**
 * Help bring in common crew as properties
 * Created by derek on 4/13/17.
 */
public interface CommonCrew {

    CrewMember KIRK = new CrewMember(1, "James", "Kirk", CAPTAIN);

    CrewMember SPOCK = new CrewMember(2, "S'chn T'gai", "Spock", FIRST_OFFICER);

    CrewMember SCOTTY = new CrewMember(3, "Montgomery", "Scott", ENGINEER);

    CrewMember MCCOY = new CrewMember(4, "Leonard", "McCoy", ENGINEER);

    CrewMember RED_SHIRT = new CrewMember(1000, "John", "Doe", ENSIGN);

    List<CrewMember> ENTIRE_CREW = new ArrayList<>(asList(KIRK, SPOCK, SCOTTY, MCCOY, RED_SHIRT));
}