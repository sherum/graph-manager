package com.sherum.neo.writer.graphwriter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@NodeEntity(label="Action")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Action {
    @Id
    @GeneratedValue
    Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("starttime")
    private String starttime;

    @JsonProperty("endtime")
    private String endtime;


}


