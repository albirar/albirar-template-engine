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
package cat.albirar.template.engine.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.TemplateEngineContext;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for {@link TemplateEngineContext}.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateEngineContextTest extends AbstractTest {
    /**
     * Test the validation conditions to methods, like null, empty string, etc.
     */
    @Test
    public void testValidations() {
        TemplateEngineContext tec;
        
        assertThrows(ValidationException.class, () -> new TemplateEngineContext(null));
        
        tec = new TemplateEngineContext(TemplateInstanceBean.buildInstance(varMsgHtmlTemplateDefinition).build());
        
        assertThrows(ValidationException.class, () -> tec.containsVariable(null));
        assertThrows(ValidationException.class, () -> tec.containsVariable(""));
        assertThrows(ValidationException.class, () -> tec.containsVariable("   "));
        
        assertThrows(ValidationException.class, () -> tec.getVariable(null));
        assertThrows(ValidationException.class, () -> tec.getVariable(""));
        assertThrows(ValidationException.class, () -> tec.getVariable("   "));
    }
    
    /**
     * Test the methods that returns anything.
     */
    @Test
    public void testGetters() {
        TemplateEngineContext tec;
        TemplateInstanceBean tib;
        
        tib = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition).build();
        tec = new TemplateEngineContext(tib);
        
        assertNotNull(tec.getLocale());
        assertEquals(tib.getLocale(), tec.getLocale());
        assertTrue(tec.getVariableNames().isEmpty());
        assertNotNull(tec.getTemplateInstance());
        assertFalse(tec.containsVariable("XYZ"));
        assertNull(tec.getVariable("XYZ"));
    }
}
