package com.mazyde.cargo.common.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstant {

	public static final String SPRING_PROFILE_PRODUCTION = "prod";
	public static final String SPRING_PROFILE_FAST = "fast";

	public static final String DEFAULT_APP = "web";
    public static final String DEFAULT_TENANT = "JOURNAL.ID";
    public static final String DEFAULT_USER = "GUEST";

}
