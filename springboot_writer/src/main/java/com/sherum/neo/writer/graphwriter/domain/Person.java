package com.sherum.neo.writer.graphwriter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
import java.util.Set;

@NodeEntity(label="Person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @JsonProperty("fname")
    private String fname;

    @JsonProperty("lname")
    private String lname;

    @JsonProperty("age")
    private String age;

    @JsonProperty("gender")
    private String gender;

    @Relationship(type = "OWNS_A", direction = Relationship.OUTGOING)
    private List<Thing> property;

    @Relationship(type = "OWNS_A", direction = Relationship.OUTGOING)
    private List<Action> actionsPerformed;

    @Relationship(type = "PRESENT")
    private Set<Presence> presentAt;

}

