package com.duru.socialpaper.Interceptor;

import com.duru.socialpaper.exception.UnAuthorization;
import com.duru.socialpaper.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = authorizationToken(request);

        if(token != null && jwtService.isUsable(token)){
            return true;
        }else{
            throw new UnAuthorization("계정 권한이 유효하지 않습니다.\n다시 로그인을 해주세요.");
        }
    }

    public String authorizationToken(HttpServletRequest request){
        String authorization =  Optional.ofNullable(request.getHeader(HEADER_AUTH)).orElse("");
        log.debug(authorization);
        if(authorization==""){
            return null;
        }
        return authorization.substring(5).trim();
    }


}
