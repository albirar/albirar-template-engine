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
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.templatemode.TemplateMode;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for {@link EContentType} methods.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class EContentTypeTest {
    @Test
    public void testMediaTypeHtml() {
        EContentType ct;
        
        ct = EContentType.HTML;
        Assertions.assertEquals("text/html", ct.getMediaType());
        Assertions.assertEquals(TemplateMode.HTML, ct.getTemplateMode());
    }
    @Test
    public void testMediaTypeText() {
        EContentType ct;
        
        ct = EContentType.TEXT_PLAIN;
        Assertions.assertEquals("text/plain", ct.getMediaType());
        Assertions.assertEquals(TemplateMode.TEXT, ct.getTemplateMode());
    }
}
