package com.fis.drzewiecki.wojciech.lbd.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Requires basic authentication for every filter
@WebFilter(
		filterName = "AuthenticationFilter",
        urlPatterns = { "/*" },
        initParams = {
                @WebInitParam(name = "username", value = "user"),
                @WebInitParam(name = "password", value = "pass")
                }
        )
public class AuthenticationFilter implements Filter {

    private String username = "";

    private String password = "";

    private String realm = "LBD";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        username = filterConfig.getInitParameter("username");
        password = filterConfig.getInitParameter("password");
        String paramRealm = filterConfig.getInitParameter("realm");
        if (paramRealm != null && paramRealm.length() > 0) {
            realm = paramRealm;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
    	
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");
        
        // when client didn't send authorization header
        if (authHeader == null) {
        	unauthorized(response);
        	return;
        }
        
        StringTokenizer st = new StringTokenizer(authHeader);
        
        // When authorization field is empty (?)
        if (!st.hasMoreTokens()) {
        	unauthorized(response);
        	return;
        }
        String basic = st.nextToken();

        // not basic auth
        if (!basic.equalsIgnoreCase("Basic")) {
        	// TODO: pass to different authenticator ?
        	unauthorized(response);
        	return;
        }
        	
        
        String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
        int p = credentials.indexOf(":");
            
        // No username
        if (p == -1) {
            unauthorized(response);
        	return;
        }
            
        // obtain username and password
        String _username = credentials.substring(0, p).trim();
        String _password = credentials.substring(p + 1).trim();

        // find user and compare
        if (!username.equals(_username) || !password.equals(_password)) {
            unauthorized(response, "Bad credentials");
        	return;
        }

        // user authorized
            
        filterChain.doFilter(servletRequest, servletResponse);  
    }

    @Override
    public void destroy() {
    }

    // set response to unauthorized and send necessary info
    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }}
