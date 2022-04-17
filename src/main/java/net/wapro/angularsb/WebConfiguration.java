package net.wapro.angularsb;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

/**
 * Konfiguration von Spring Web MVC, damit alle Requests, die kein Servergegenstück haben,
 * in der Angular-Anwendung landen. Dies ist notwendig, weil Angular mit HTML 5 URLs arbeitet und ein
 * {@code /overview} sonst auf dem Server verarbeitet werden würde.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(final String resourcePath, final Resource location) throws
//                            IOException {
//                        final Resource requestedResource = location.createRelative(resourcePath);
//
//                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource :
//                                new ClassPathResource("/static/index.html");
//                    }
//                });
//    }
}
