package com.mazyde.cargo.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class CommandTraceId extends BaseIdentifier {

    public static final CommandTraceId EMPTY_TRACEID = new CommandTraceId("EMPTY");

    @JsonCreator
    public CommandTraceId(@JsonPropertyDescription(value = "id") String id) {
        super(id);
    }

    public CommandTraceId() {
        super();
    }
}
