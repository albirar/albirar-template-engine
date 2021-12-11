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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;

/**
 * TODO Document pending!.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 3.1.0
 */
public abstract class AbstractTemplateEngineFactoryTest extends AbstractTest {

    @Autowired
    protected ITemplateEngineFactory render;
    @Autowired
    protected ApplicationContext applicationContext;
    
    @BeforeEach
    public void prepareTests() {
        ITemplateEngine te;
        
        for(String tl : REGISTERED_TEMPLATES) {
            te = mock(ITemplateEngine.class);
            when(te.getTemplateLanguage()).thenReturn(tl);
            // Prepare for all template render
            when(te.renderTemplate(eq(TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder().build()).build()))).thenReturn(SIMPLE_HTML_TEMPLATE_TEST_RESULT);
            when(te.renderTemplate(eq(TemplateInstanceBean.buildInstance(simpleTxtTemplateDefinition.toBuilder().build()).build()))).thenReturn(SIMPLE_TXT_TEMPLATE_TEST_RESULT);
            templateEngineRegistry.register(te);
        }        
    }
}
