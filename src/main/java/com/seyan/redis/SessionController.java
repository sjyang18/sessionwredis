package com.seyan.redis;

import com.seyan.redis.model.MyMessage;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private static final String MESSAGE = "/message";
    @Value("${HOSTNAME:NOTKNOWN}")
    private String hostname;

    @GetMapping("/")
    public Map<String, Object> uid(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("hostname", hostname);
        return map;
        // return session.getId();
    }

    @GetMapping("/username")
    public String currentUserName(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String userName = principal.getName();
        request.getSession().setAttribute("username", userName);
        return userName;
    }

    @PostMapping(MESSAGE)
    public Map<String, Object> echo(HttpSession session, @RequestBody MyMessage message) {
        String data = message.getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("hostname", hostname);
        map.put("content", data);
        return map;
        //return data;
        //return session.getId() + ":" + data;
    }

    @GetMapping("/session/{key}")
    public Map<String, Object> searchFromSession(HttpSession session, @PathVariable("key") String key) {
        Object result = Optional.ofNullable(session.getAttribute(key));
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("hostname", hostname);
        map.put("content", result);
        return map;
    }

    @PutMapping("/session/{key}")
    public Map<String, Object> putToSession(
        HttpSession session,
        @PathVariable("key") String key,
        @RequestBody MyMessage rbody) {
        
        session.setAttribute(key, rbody.getContent());
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("key", key);
        map.put("hostname", hostname);
        map.put("content", rbody.getContent());
        return map;
    }



}
