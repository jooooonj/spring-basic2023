package com.ll.basic1.rq;

import com.ll.basic1.resultData.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse res;

    public void setCookie(String name, long value) {
        setCookie(name, value + "");
    }

    public void setCookie(String name, String value) {
        res.addCookie(new Cookie(name, value));
    }

    public String getCookie(String name, String defaultValue) {
        if (req.getCookies() == null) {
            return defaultValue;
        }

        String result = defaultValue;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                result = cookie.getValue();
                break;
            }
        }
        return result;
    }

    public long getCookieAsLong(String name, long defaultValue) {

        String value = getCookie(name, null);

        if (value == null) {
            return defaultValue;
        }

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean removeCookie(String name){
        boolean isRemove = false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                    isRemove = true;
                }
            }
        }

        return isRemove;
    }

    public void setSession(String name, long value) {
        req.getSession().setAttribute(name, value);
    }
    public void setSession(String name, String value) {
        req.getSession().setAttribute(name, value);
    }

    public long getSessionAsLong(String name, long defaultValue) {
        try {
            long value = (long) req.getSession().getAttribute(name);
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private String getSessionAsStr(String name, String defaultValue) {
        try {
            String value = (String) req.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
    public boolean removeSession(String name) {
        boolean isRemove = false;
        Object attribute = req.getSession().getAttribute(name);
        if(attribute!=null){
            req.getSession().removeAttribute(name);
            isRemove = true;
        }
        return isRemove;
    }

   public boolean isLogined(){
       long loginedMemberId = getSessionAsLong("loginedMemberId", 0);

       return loginedMemberId > 0;
   }

   public boolean isLogouted(){
        return !isLogined();
   }
}
