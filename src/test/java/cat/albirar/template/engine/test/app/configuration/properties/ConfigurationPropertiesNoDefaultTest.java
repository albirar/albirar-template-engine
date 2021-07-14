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
package cat.albirar.template.engine.test.app.configuration.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cat.albirar.template.engine.models.ConfigurationPropertiesBean;

/**
 * Test change defaults of properties.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 3.1.0
 */
@SpringBootTest(args = "--debug", properties = {"albirar.templates.charset=ISO-8859-1", "albirar.templates.directories=/temporal,/anotherDir"})
public class ConfigurationPropertiesNoDefaultTest {
    @Autowired
    private ConfigurationPropertiesBean configurationPropertiesBean;
    
    @Test
    public void when_overridingDefaultProperties_then_AllPropertiesAreOk() {
        List<Path> dirs;
        
        assertEquals(Charset.forName("ISO-8859-1"), configurationPropertiesBean.getCharset());
        dirs = Stream.of("/temporal", "/anotherDir").map(d -> Paths.get(d)).collect(Collectors.toList());
        assertEquals(dirs, configurationPropertiesBean.getDirectories());
    }

}
