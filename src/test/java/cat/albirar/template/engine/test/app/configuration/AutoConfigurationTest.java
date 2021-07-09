/*
 * This file is part of "albirar-template-engine".
 * 
 * "albirar-template-engine" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "albirar-template-engine" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "albirar-template-engine" source code.  If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2021 Octavi Fornés
 */
package cat.albirar.template.engine.test.app.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import cat.albirar.template.engine.configuration.PropertiesTemplate;
import cat.albirar.template.engine.models.ConfigurationPropertiesBean;

/**
 * Test for application configuration.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 2.0.2
 */
@SpringBootTest(args = "--debug")
public class AutoConfigurationTest {
    @Autowired
    SpringTemplateEngine templateEngine;
    @Autowired
    SpringResourceTemplateResolver templateResolver;
    
    @Autowired
    ConfigurationPropertiesBean configurationPropertiesBean;

    @Test
    public void when_overridingConfigurationIsMade_then_AssertThatIsApplied() {
        assertSame(templateEngine, AutoConfigurationTestConfiguration.templateEngine);
        assertSame(templateResolver, AutoConfigurationTestConfiguration.templateResolver);
    }
    
    @Test
    public void when_configurationIsLoad_then_PropertiesAreDefault () {
        List<Path> dirs;
        
        assertEquals(PropertiesTemplate.DEFAULT_CHARSET, configurationPropertiesBean.getCharset());
        dirs = Stream.of(PropertiesTemplate.DEFAULT_DIR_ARRAY).map(d -> Paths.get(d)).collect(Collectors.toList());
        assertEquals(dirs, configurationPropertiesBean.getDirectories());
    }
}
