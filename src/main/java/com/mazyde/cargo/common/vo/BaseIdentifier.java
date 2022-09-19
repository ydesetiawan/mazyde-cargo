package com.mazyde.cargo.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mazyde.cargo.common.util.PresentUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@JsonSerialize(using = ToStringSerializer.class)
public abstract class BaseIdentifier implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String identifier;

    public BaseIdentifier(String id) {

        this.identifier = PresentUtil.isPresent(id) ? id :  "anonymous";
    }

    public BaseIdentifier() {
        this.identifier = "anonymous";
    }

    @Override
    public String toString() {
        return this.getIdentifier();
    }
}
