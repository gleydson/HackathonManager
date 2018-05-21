package com.greenmile.challenger.util;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstantsUtil {
	public static final String API_HACKATHON = "/api/hackathon";
	public static final String API_TEAM = "/api/team";
	public static final String API_MEMBER = "/api/member";
	
	public static final String EXCEPTION_HACKATHON_NOT_FOUND = "The Hackathon reporting id is not associated with any events registered to our system";
	public static final String EXCEPTION_NUMBER_OF_MEMBERS_GREATER_THAN_ALLOWED = "Number of members greater than allowed by team";
	public static final String EXCEPTION_MEMBER_IS_ALREADY_PARTICIPATING_IN_THIS_HACKATHON = "You have registered a member who is already participating in this hackathon in another team";
	public static final String EXCEPTION_NAME_OF_THIS_TEAM_IS_ALREADY_IN_USE = "The name of this team is already being used in this hackathon";
	public static final String EXCEPTION_A_USER_WITH_THIS_EMAIL_ALREADY_EXISTS = "A user with this email already exists on the system";
	public static final String EXCEPTION_MEMBER_IS_NOT_REGISTERED_IN_THE_SYSTEM = "This member is not registered in the system";
}
