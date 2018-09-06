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

@NodeEntity(label="Location")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Location {

    @Id
    @GeneratedValue
    Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("landmark")
    private String landmark;

    @Relationship(type = "LOCATED_AT", direction = Relationship.INCOMING)
    private List<Thing> property;

    @Relationship(type = "OCCURRED_AT", direction = Relationship.INCOMING)
    private List<Action> actionsPerformed;

    @Relationship(type = "PRESENT")
    private Set<Presence> presentAt;



}
