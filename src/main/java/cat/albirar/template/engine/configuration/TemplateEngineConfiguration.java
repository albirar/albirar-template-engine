/*
 * This file is part of "albirar albirar-template-engine".
 * 
 * "albirar albirar-template-engine" is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * "albirar albirar-template-engine" is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with "albirar albirar-template-engine" source
 * code. If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.configuration;

import static cat.albirar.template.engine.configuration.PropertiesTemplate.CHARSET_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import cat.albirar.template.engine.registry.TemplateEngineRegistryDefaultImpl;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.service.impl.ThymeleafSpringTemplateEngineImpl;

/**
 * The configuration for template engine.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@Configuration
@AutoConfigureOrder(Integer.MAX_VALUE)
@ComponentScan(basePackageClasses = {ITemplateEngine.class, ThymeleafSpringTemplateEngineImpl.class, TemplateEngineRegistryDefaultImpl.class})
public class TemplateEngineConfiguration {
    /**
     * The {@link SpringTemplateEngine} to use on rendering.
     * @param resolver The resolver for resources
     * @return The {@link SpringTemplateEngine} configured to use them
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringTemplateEngine templateEngine(@Autowired SpringResourceTemplateResolver resolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }
    /**
     * The template resolver for thymeleaf
     * @param charset
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(@Value(CHARSET_VALUE) String charset) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding(charset);
        return templateResolver;
    }
}
