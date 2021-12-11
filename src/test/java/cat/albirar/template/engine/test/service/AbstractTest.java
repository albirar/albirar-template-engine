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

import org.springframework.beans.factory.annotation.Autowired;

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateDefinitionBean;
import cat.albirar.template.engine.service.ITemplateEngineRegistry;

/**
 * Abstract class with some common methods and properties.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
public abstract class AbstractTest {

    protected static final String SIMPLE_HTML_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/simpleTemplate.html";
    protected static final String SIMPLE_TXT_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/simpleTemplate.txt";
    protected static final String SIMPLE_TEST_H1 = "Text";
    protected static final String SIMPLE_TEST_P = "A simple template";
    
    protected static final String SIMPLE_TXT_TEMPLATE_TEST_RESULT = "Hello,\n\nThis is a simple text template without variables nor messages\n\nBye!";
    protected static final String SIMPLE_HTML_TEMPLATE_TEST_RESULT = "<html>\n<body>\n    <h1>Text</h1>\n    <p>A simple template</p>\n</body>\n</html>";
    
    protected static final String TEST_LANGUAGE = "test";
    
    protected static final String [] REGISTERED_TEMPLATES = { TEST_LANGUAGE };
    
    protected static final TemplateDefinitionBean simpleHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test1")
            .contentType(EContentType.HTML)
            .template(SIMPLE_HTML_TEMPLATE_TEST)
            .templateEngineLanguage(TEST_LANGUAGE)
            .build()
            ;
    
    protected static final TemplateDefinitionBean simpleTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test1")
            .template(SIMPLE_TXT_TEMPLATE_TEST)
            .templateEngineLanguage(TEST_LANGUAGE)
            .build()
            ;
    protected static final TemplateDefinitionBean invalidTemplateDefinition = TemplateDefinitionBean.builder()
            .build()
            ;
    
    @Autowired
    protected ITemplateEngineFactory templateEnginefactory;
    
    @Autowired
    protected ITemplateEngineRegistry templateEngineRegistry;
}
