package edu.config.session;

import org.apache.commons.lang3.StringUtils;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Both cookies and headers are supported
 *
 * @author wqs
 * @date 15:39
 */
public class HeaderCookieHttpSessionIdResolver implements HttpSessionIdResolver {

    private static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";

    private static final String WRITTEN_SESSION_ID_ATTR = CookieHttpSessionIdResolver.class.getName().concat(".WRITTEN_SESSION_ID_ATTR");

    /**
     * cookie resolver
     */
    private final CookieSerializer cookieSerializer = new DefaultCookieSerializer();


    /**
     * Resolve the session ids associated with the provided {@link HttpServletRequest}.
     * For example, the session id might come from a cookie or a request header.
     *
     * @param request the current request
     * @return the session ids
     */
    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        // get header
        String headerValue = request.getHeader(HEADER_X_AUTH_TOKEN);
        if (!StringUtils.isEmpty(headerValue)) {
            return Collections.singletonList(headerValue);
        }
        // cookie resolve
        return this.cookieSerializer.readCookieValues(request);
    }

    /**
     * Send the given session id to the client. This method is invoked when a new session
     * is created and should inform a client what the new session id is. For example, it
     * might create a new cookie with the session id in it or set an HTTP response header
     * with the value of the new session id.
     *
     * @param request   the current request
     * @param response  the current response
     * @param sessionId the session id
     */
    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        response.setHeader(HEADER_X_AUTH_TOKEN, sessionId);
        if (!sessionId.equals(request.getAttribute(WRITTEN_SESSION_ID_ATTR))) {
            request.setAttribute(WRITTEN_SESSION_ID_ATTR, sessionId);
            this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, sessionId));
        }
    }

    /**
     * Instruct the client to end the current session. This method is invoked when a
     * session is invalidated and should inform a client that the session id is no longer
     * valid. For example, it might remove a cookie with the session id in it or set an
     * HTTP response header with an empty value indicating to the client to no longer
     * submit that session id.
     *
     * @param request  the current request
     * @param response the current response
     */
    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(HEADER_X_AUTH_TOKEN, "");
        this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, ""));
    }
}
