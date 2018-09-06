package com.sherum.neo.writer.graphwriter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="PRESENCE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Presence {
    @Id@GeneratedValue
    private Long id;

    @JsonProperty("location")
    @StartNode private Location location;

    @JsonProperty("person")
    @EndNode private Person person;

    @JsonProperty("arrivalTime")
    @Property
    private String arrivalTime;

    @JsonProperty("departureTime")
    @Property
    private String departureTime;



}
