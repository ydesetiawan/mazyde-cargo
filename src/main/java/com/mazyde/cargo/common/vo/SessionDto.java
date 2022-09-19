package com.mazyde.cargo.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@JsonDeserialize(builder = SessionDto.SessionDtoBuilder.class)
public class SessionDto {
	
	public static final String KEY = "sessionData";
	
	String userName;
	
	UserId userId;
	
	String tenantName;
	
	TenantId tenantId;
	
	String remoteAddress;
	
	String applicationName;

	public static SessionDto guessSession(String ip) {
		return new SessionDto(AppConstant.DEFAULT_USER, new UserId(AppConstant.DEFAULT_USER), AppConstant.DEFAULT_TENANT,
			new TenantId(AppConstant.DEFAULT_TENANT), ip, AppConstant.DEFAULT_APP);
	}
	
	@JsonPOJOBuilder(withPrefix = "")
    public static final class SessionDtoBuilder {
    }
}
