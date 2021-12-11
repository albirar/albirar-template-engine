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
package cat.albirar.template.engine.test.service.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.IEngineRender;
import cat.albirar.template.engine.service.TemplateNotAccessibleException;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;
import cat.albirar.template.engine.test.service.AbstractTemplateEngineFactoryTest;

/**
 * Test for throws by {@link IEngineRender#renderTemplate(TemplateInstanceBean)}.
 * 
 * Test for following rules:
 * 
 * - IllegalStateException
 *    - not registered engine is available for this template language
 * - TemplateNotAccessibleException If the indicated template is not accessible, ex: does'nt exists or is not a file or there are'nt authorization to read them
 *    - Does'nt exists
 *    - Is not a file 
 *    - There are'nt authorization to read them
 * - RenderingException
 *    - Syntax error
 *    - Variable is not of expected type
 *    - ...
 *  
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 3.1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateEngineExceptionsTest extends AbstractTemplateEngineFactoryTest {
    
    @Test
    public void when_templateDoesntExists_then_aTemplateNotAccessibleExceptionIsThrown() {
        TemplateInstanceBean tInstance;
        
        tInstance = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder()
                .template("/test-template.html")    // not found template
                .build())
                .build()
                ;
        
        assertThrows(TemplateNotAccessibleException.class, () -> render.renderTemplate(tInstance));
    }
    
    @Test
    public void when_templateIsNotAFile_then_aTemplateNotAccessibleExceptionIsThrown() throws Exception {
        TemplateInstanceBean tInstance;
        Resource r;
        
        r = applicationContext.getResource("classpath:" + this.getClass().getPackage().getName().replace(".", File.separator));
        assertTrue(r.exists());
        assertTrue(!r.getFile().isFile());
        tInstance = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder()
                .template("/")    // template is a directory
                .build())
                .build()
                ;
        
        assertThrows(TemplateNotAccessibleException.class, () -> render.renderTemplate(tInstance));
    }
    
    @Test
    public void when_templateThereAreNotAuthorizationToReadThem_then_aTemplateNotAccessibleExceptionIsThrown() throws Exception {
        TemplateInstanceBean tInstance;
        File file;
        
        file = applicationContext.getResource(simpleHtmlTemplateDefinition.getTemplate()).getFile();
        file.setReadable(false);
        tInstance = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder()
                .build())
                .build()
                ;
        
        assertThrows(TemplateNotAccessibleException.class, () -> render.renderTemplate(tInstance));
        file.setReadable(true);
    }
}
