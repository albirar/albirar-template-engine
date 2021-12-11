/*
 * This file is part of "albirar albirar-template-engine".
 * 
 * "albirar albirar-template-engine" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * "albirar albirar-template-engine" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with "albirar albirar-template-engine" source code.  If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.validation.ValidationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for configured {@link ITemplateEngine}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateEngineFactoryTest extends AbstractTemplateEngineFactoryTest {

    @Test
    public void when_templateIsOfUnkownLanguage_then_anIllegalStateExceptionIsThrown() {
        TemplateInstanceBean tInstance;
        
        tInstance = TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder()
                .templateEngineLanguage("XXX")
                .build())
                .build()
                ;
        
        assertThrows(IllegalStateException.class, () -> render.renderTemplate(tInstance));
    }
    
    /**
     * Test for validation on engine.
     */
    @Test
    public void when_argumentsToRenderAreNullOrInvalid_then_aValidationExceptionIsThrown() {
        assertThrows(ValidationException.class, () -> templateEnginefactory.renderTemplate(null));
        assertThrows(ValidationException.class, () -> templateEnginefactory.renderTemplate(TemplateInstanceBean.builder().build()));
    }
    /**
     * Test for unknown template language.
     */
    @Test
    public void when_anUnknownTemplateLanguageInstanceIsRender_then_anIllegalStateExceptionIsThrown() {
        assertThrows(IllegalStateException.class, () -> templateEnginefactory.renderTemplate(TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder().templateEngineLanguage("xxx").build()).build()));
    }
    /**
     * Test create html without messages nor variables.
     */
    @Test
    public void when_anHtmlTemplateInstanceWithoutMessagesNorVariablesIsRender_then_aCorrectResultIsReturn() {
        String tx;
        Document parsed;
        
        tx = templateEnginefactory.renderTemplate(TemplateInstanceBean.buildInstance(simpleHtmlTemplateDefinition.toBuilder().build()).build());
        assertNotNull(tx);
        assertTrue(StringUtils.hasText(tx));
        
        parsed = Jsoup.parse(tx);
        assertEquals(1, parsed.select("h1").size());
        assertEquals(SIMPLE_TEST_H1, parsed.select("h1").first().text());
        assertEquals(1, parsed.select("p").size());
        assertEquals(SIMPLE_TEST_P, parsed.select("p").first().text());
    }
    /**
     * Test create text without messages nor variables.
     */
    @Test
    public void when_aTxtTemplateInstanceWithoutMessagesNorVariablesIsRender_then_aCorrectResultIsReturn() {
        String tx;
        
        tx = templateEnginefactory.renderTemplate(TemplateInstanceBean.buildInstance(simpleTxtTemplateDefinition.toBuilder().build()).build());
        assertNotNull(tx);
        assertTrue(StringUtils.hasText(tx));
        assertEquals(SIMPLE_TXT_TEMPLATE_TEST_RESULT, tx);
    }
    /**
     * Test the {@link ITemplateEngineFactory#getRegisteredTemplateLanguages()} method.
     */
    @Test
    public void when_theTemplateLanguageListIsRequested_then_aNotEmptyListIsGetWithAlmostTheCorrectElement() {
        List<String> l1, lr;
        
        l1 = Arrays.asList(REGISTERED_TEMPLATES);
        
        lr = templateEnginefactory.getRegisteredTemplateLanguages();
        assertNotNull(lr);
        assertFalse(lr.isEmpty());
        assertEquals(l1, lr, "If a new language are testing, please, add them to the testing register at 'cat.albirar.template.engine.test.service.AbstractTest.addRegisteredLanguage'");
    }
}
