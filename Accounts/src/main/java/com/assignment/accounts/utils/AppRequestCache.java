package com.assignment.accounts.utils;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

@Component
public class AppRequestCache extends HttpSessionRequestCache {
	public AppRequestCache() {
        super();
    }
}
