package com.example.demo.auth.properties;

import com.example.demo.auth.properties.roles.DemoAuthorizationPathRolesProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * packageName    : com.example.demo.auth.property
 * fileName       : DemoAuthorizationPathProperties
 * author         : JAEIK
 * date           : 10/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/14/24        JAEIK       최초 생성
 */
@ConfigurationProperties("demo.authorization")
@ConfigurationPropertiesBinding
public record DemoAuthorizationPathProperties(
        @NestedConfigurationProperty
        DemoAuthorizationPathRolesProperties[] uris
) {
}
