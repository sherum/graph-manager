package com.sherum.neo.writer.graphwriter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label="Plotpoint")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class Plotpoint extends Action {

    @Relationship(type = "CAUSED_BY", direction = Relationship.INCOMING)
    private Plotpoint causedby;
}
