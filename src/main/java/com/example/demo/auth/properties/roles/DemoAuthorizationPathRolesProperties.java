package com.example.demo.auth.properties.roles;

import com.example.demo.member.entity.Role;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * packageName    : com.example.demo.auth.property.roles
 * fileName       : DemoAuthorizationPathRolesProperties
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
@ConfigurationPropertiesBinding
public record DemoAuthorizationPathRolesProperties(
        String pattern, // URI 경로 예: "/api/admin/**", "/api/user/*"
        String[] methods, // GET , POST , PUT , DELETE 중
        Role[] roles
) {
}
