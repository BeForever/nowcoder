package com.beforever.controller;

import com.beforever.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by BeForever on 17/5/10.
 */
@Controller
public class IndexController {
    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(HttpSession session) {
        return "Hello Nowcoder!" + session.getAttribute("msg");
    }


    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", required = false) String key) {
        return String.format("Profile Page of %s / %d ,t:%d k:%s", groupId, userId, type, key);
    }

    @RequestMapping(path = ("/vm"), method = {RequestMethod.GET})
    public String template(Model model) {
        model.addAttribute("value1", "vvvvvvv1");
        List colors = Arrays.asList(new String[]{"Red", "Green", "Blue"});
        model.addAttribute("colors", colors);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("map", map);
        model.addAttribute("user", new User("LEE"));
        return "home";
    }

    @RequestMapping(path = ("/request"), method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletRequest request, HttpServletResponse response,
                          HttpSession session, @CookieValue("JSESSIONID") String sessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("CookieValue" + sessionId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br/>");
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie:" + cookie.getName() + "Value" + cookie.getValue());
            }
        }
        sb.append(request.getMethod() + "<br/>");
        sb.append(request.getRequestURI() + "<br/>");
        sb.append(request.getPathInfo() + "<br/>");
        sb.append(request.getQueryString() + "<br/>");

        response.addHeader("nowcoderId", "hello~");//不显示在页面上不代表值没有加成功，在Header中可以查看的到
        response.addCookie(new Cookie("username", "nowcoder"));//同上一条注释，在cookie中可以看得到
        return sb.toString();
    }

    @RequestMapping(path = ("/redirect/{code}"), method = RequestMethod.GET)
    public RedirectView redirect(@PathVariable("code") int code, HttpSession session) {
        session.setAttribute("msg", "send from redirect!");
        RedirectView red = new RedirectView("/",true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;
    }
}
