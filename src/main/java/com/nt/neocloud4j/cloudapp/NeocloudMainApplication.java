
package com.nt.neocloud4j.cloudapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.nt.neocloud4j.core.Neoclou4JCoreRootConfiguration;
import com.nt.neocloud4j.security.conf.Neocloud4jJWTSecurityConfiguration;

@SpringBootApplication
@PropertySource(value = {
        "classpath:/application.properties",
        "classpath:/env/${env:uat}/application.properties",
        "classpath:override.properties"}, ignoreResourceNotFound = true
)
@Import({
        Neoclou4JCoreRootConfiguration.class,
        Neocloud4jJWTSecurityConfiguration.class
})
@ComponentScan(basePackages = {"com.nt", "io.github"})
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class NeocloudMainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NeocloudMainApplication.class);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(NeocloudMainApplication.class)
                .logStartupInfo(false)
                .run(args);
    }
}
