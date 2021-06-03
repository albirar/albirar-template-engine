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

import cat.albirar.template.engine.EContentType;
import cat.albirar.template.engine.models.TemplateDefinitionBean;

/**
 * Abstract class with some common methods and properties.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
public abstract class AbstractTest {

    /** The _thymeleaf_ template language identifier */
    private static final String TL_THYMELEAF = "thymeleaf";
    
    protected static final String SIMPLE_HTML_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/simpleTemplate.html";
    protected static final String SIMPLE_TXT_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/simpleTemplate.txt";
    protected static final String SIMPLE_TEST_H1 = "Text";
    protected static final String SIMPLE_TEST_P = "A simple template";
    
    protected static final String SIMPLE_TXT_TEMPLATE_TEST_RESULT = "Hello,\n\nThis is a simple text template without variables nor messages\n\nBye!";
    
    protected static final String VARS_HTML_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsTemplate.html";
    protected static final String VARS_TXT_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsTemplate.txt";
    protected static final String VARS_TEST_H1 = "Vars test title";
    
    protected static final String VARS_MSG_TEST_MSG_RESOURCE = "cat/albirar/template/engine/test/messages/testMessages";
    protected static final String VARS_HTML_MSG_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsMessagesTemplate.html";
    protected static final String VARS_TXT_MSG_TEMPLATE_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsMessagesTemplate.txt";
    protected static final String VARS_MSG_TEST_H1 = "Vars &amp; Msg test title";
    protected static final String VARS_MSG_TEST_P_EN = "This is a template for test with variables";
    protected static final String VARS_MSG_TEST_P_CA = "Aquesta és una plantilla de prova amb variables";
    protected static final String VARS_MSG_TEST_P_FR = "Ceci est un modèle de test avec des variables";
    protected static final String VARS_MSG_TEST_LBL_EN = "User name:&nbsp;";
    protected static final String VARS_MSG_TEST_LBL_CA = "Nom d'usuari:&nbsp;";
    protected static final String VARS_MSG_TEST_LBL_FR = "Nom d'utilisateur:&nbsp;";
    
    protected static final String VARS_MSG_TEST_TXT_H1 = "Vars & Msg test title";
    protected static final String VARS_MSG_TEST_TXT_LBL_EN = "User name: ";
    protected static final String VARS_MSG_TEST_TXT_LBL_CA = "Nom d'usuari: ";
    protected static final String VARS_MSG_TEST_TXT_LBL_FR = "Nom d'utilisateur: ";
    
    protected static final TemplateDefinitionBean simpleHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test1")
            .contentType(EContentType.HTML)
            .template(SIMPLE_HTML_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;
    
    protected static final TemplateDefinitionBean varHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.HTML)
            .template(VARS_HTML_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;
    
    protected static final TemplateDefinitionBean varMsgHtmlTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .contentType(EContentType.HTML)
            .template(VARS_HTML_MSG_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;
    
    protected static final TemplateDefinitionBean simpleTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test1")
            .template(SIMPLE_TXT_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;
    
    protected static final TemplateDefinitionBean varTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .template(VARS_TXT_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;

    protected static final TemplateDefinitionBean varMsgTxtTemplateDefinition = TemplateDefinitionBean.builder()
            .name("Test 2")
            .template(VARS_TXT_MSG_TEMPLATE_TEST)
            .templateEngineLanguage(TL_THYMELEAF)
            .build()
            ;
}
