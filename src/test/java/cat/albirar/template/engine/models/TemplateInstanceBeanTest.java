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
package cat.albirar.template.engine.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;
import cat.albirar.template.engine.test.service.AbstractTest;

/**
 * Test for {@link TemplateInstanceBean}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 3.1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateInstanceBeanTest extends AbstractTest {

    @Test
    public void testModels() {
        TemplateInstanceBean te1, te2;
        
        te1 = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder().build()).build();
        te2 = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder().build()).build();
        
        assertEquals(te1, te2);
    }
}
