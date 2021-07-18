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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for {@link EContentType} methods.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class EContentTypeTest {
    @Test
    public void when_mediaTypeIsHtml_then_PropertiesAreOk() {
        EContentType ct;
        
        ct = EContentType.HTML;
        assertEquals("text/html", ct.getMediaType());
    }
    @Test
    public void when_mediaTypeIsText_then_PropertiesAreOk() {
        EContentType ct;
        
        ct = EContentType.TEXT_PLAIN;
        assertEquals("text/plain", ct.getMediaType());
    }
}
