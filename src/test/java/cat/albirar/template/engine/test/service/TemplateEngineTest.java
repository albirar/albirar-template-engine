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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.ValidationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for configured {@link ITemplateEngine}.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateEngineTest extends AbstractTest {
    
    private static final TestUser [] VARS_USERS = {
            TestUser.builder()
                .name("Username 1")
                .var1(11L)
                .var2(LocalDateTime.now().minusDays(1).withNano(0))
                .var3("User 1 - var 3")
                .build()
            ,TestUser.builder()
                .name("Username 2")
                .var1(22L)
                .var2(LocalDateTime.now().minusDays(2).withNano(0))
                .var3("User 2 - var 3")
                .build()
            ,TestUser.builder()
                .name("Username 3")
                .var1(33L)
                .var2(LocalDateTime.now().minusDays(3).withNano(0))
                .var3("User 3 - var 3")
                .build()
            ,TestUser.builder()
                .name("Username 4")
                .var1(44L)
                .var2(LocalDateTime.now().minusDays(4).withNano(0))
                .var3("User 4 - var 3")
                .build()
    };
    @Autowired
    private ITemplateEngineFactory templateEnginefactory;
    
    /**
     * Test for validation on engine.
     */
    @Test
    public void testNullsInvalids() {
        assertThrows(ValidationException.class, () -> templateEnginefactory.renderTemplate(null));
        assertThrows(ValidationException.class, () -> templateEnginefactory.renderTemplate(TemplateInstanceBean.builder().build()));
    }
    /**
     * Test create html without messages nor variables.
     */
    @Test
    public void testHtmlRenderWithoutMessagesNorVariables() {
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
     * Test create html with variable but no messages.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHtmlRenderWithVariablesNotMessages() {
        TemplateInstanceBean tinst;
        Map<String, Object> vars;
        String r;
        Document parsed;
        Elements vu;
        int n;
        
        vars = new TreeMap<>();
        vars.put("title", VARS_TEST_H1);
        vars.put("users", Arrays.asList(VARS_USERS));
        tinst = TemplateInstanceBean.buildInstance(varHtmlTemplateDefinition.toBuilder().build())
                .variables(vars)
                .build()
                ;
        r = templateEnginefactory.renderTemplate(tinst);
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        assertEquals(1, parsed.select("h1").size());
        assertEquals(VARS_TEST_H1, parsed.select("h1").first().text());
        
        vu = parsed.select("li.user");
        assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, null);
            n++;
        }
    }
    /**
     * Test create html with messages and variables.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHtmlRenderWithVariablesAndMessages() {
        TemplateInstanceBean tinst;
        Map<String, Object> vars;
        String r;
        Document parsed;
        Elements vu;
        int n;
        
        vars = new TreeMap<>();
        vars.put("title", VARS_MSG_TEST_H1);
        vars.put("users", Arrays.asList(VARS_USERS));

        // TEST DEFAULT (EN)
        tinst = TemplateInstanceBean.buildInstance(varMsgHtmlTemplateDefinition, VARS_MSG_TEST_MSG_RESOURCE)
                .variables(vars)
                .build()
                ;
        
        r = templateEnginefactory.renderTemplate(tinst);
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        assertEquals(1, parsed.select("h1").size());
        assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        assertEquals(1, parsed.select("p").size());
        assertEquals(VARS_MSG_TEST_P_EN, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_EN);
            n++;
        }
        
        
        // TEST CA
        r = templateEnginefactory.renderTemplate(tinst.toBuilder().locale(new Locale("ca")).build());
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        assertEquals(1, parsed.select("h1").size());
        assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        assertEquals(1, parsed.select("p").size());
        assertEquals(VARS_MSG_TEST_P_CA, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_CA);
            n++;
        }
        
        // TEST FR
        r = templateEnginefactory.renderTemplate(tinst.toBuilder().locale(new Locale("fr")).build());
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        assertEquals(1, parsed.select("h1").size());
        assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        assertEquals(1, parsed.select("p").size());
        assertEquals(VARS_MSG_TEST_P_FR, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_FR);
            n++;
        }
    }
    /**
     * Test create text without messages nor variables.
     */
    @Test
    public void testTxtRenderWithoutMessagesNorVariables() {
        String tx;
        
        tx = templateEnginefactory.renderTemplate(TemplateInstanceBean.buildInstance(simpleTxtTemplateDefinition.toBuilder().build()).build());
        assertNotNull(tx);
        assertTrue(StringUtils.hasText(tx));
        assertEquals(SIMPLE_TXT_TEMPLATE_TEST_RESULT, tx);
    }
    /**
     * Test create text with variable but no messages.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testTxtRenderWithVariablesNotMessages() {
        TemplateInstanceBean tinst;
        Map<String, Object> vars;
        String r;
        StringBuilder stb;
        
        vars = new TreeMap<>();
        vars.put("title", VARS_TEST_H1);
        vars.put("users", Arrays.asList(VARS_USERS));
        tinst = TemplateInstanceBean.buildInstance(varTxtTemplateDefinition.toBuilder().build())
                .variables(vars)
                .build()
                ;
        r = templateEnginefactory.renderTemplate(tinst);
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));
        // Prepare expected...
        stb = new StringBuilder(VARS_TEST_H1)
                .append("\nThis is a template text for test with variables\n\nUsers:\n")
                ;
        for(TestUser usr : VARS_USERS) {
            stb.append(usr.getName())
                .append("\n   ")
                .append(usr.getVar1())
                .append("\n   ")
                .append(usr.getVar2())
                .append("\n   ")
                .append(usr.getVar3())
                .append("\n\n")
                ;
        }
        assertEquals(stb.toString(), r);
    }
    /**
     * Test create text with messages and variables.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testTxtRenderWithVariablesAndMessages() {
        TemplateInstanceBean tinst;
        Map<String, Object> vars;
        String r;
        StringBuilder stb;
        
        vars = new TreeMap<>();
        vars.put("title", VARS_MSG_TEST_TXT_H1);
        vars.put("users", Arrays.asList(VARS_USERS));

        // TEST DEFAULT (EN)
        tinst = TemplateInstanceBean.buildInstance(varMsgTxtTemplateDefinition, VARS_MSG_TEST_MSG_RESOURCE)
                .variables(vars)
                .build()
                ;
        
        r = templateEnginefactory.renderTemplate(tinst);
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));
        
        // Prepare expected...
        stb = new StringBuilder(VARS_MSG_TEST_H1)
                .append("\n\n")
                .append(VARS_MSG_TEST_P_EN).append("\n\n")
                ;

        for(TestUser usr : VARS_USERS) {
            stb.append(VARS_MSG_TEST_TXT_LBL_EN)
                .append(usr.getName())
                .append("\n   ")
                .append(usr.getVar1())
                .append("\n   ")
                .append(usr.getVar2())
                .append("\n   ")
                .append(usr.getVar3())
                .append("\n\n")
                ;
        }
        assertEquals(stb.toString(), r);
        
        // TEST CA
        r = templateEnginefactory.renderTemplate(tinst.toBuilder().locale(new Locale("ca")).build());
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        // Prepare expected...
        stb = new StringBuilder(VARS_MSG_TEST_H1)
                .append("\n\n")
                .append(VARS_MSG_TEST_P_CA).append("\n\n")
                ;

        for(TestUser usr : VARS_USERS) {
            stb.append(VARS_MSG_TEST_TXT_LBL_CA)
                .append(usr.getName())
                .append("\n   ")
                .append(usr.getVar1())
                .append("\n   ")
                .append(usr.getVar2())
                .append("\n   ")
                .append(usr.getVar3())
                .append("\n\n")
                ;
        }
        assertEquals(stb.toString(), r);
        
        // TEST FR
        r = templateEnginefactory.renderTemplate(tinst.toBuilder().locale(new Locale("fr")).build());
        assertNotNull(r);
        assertTrue(StringUtils.hasText(r));

        // Prepare expected...
        stb = new StringBuilder(VARS_MSG_TEST_H1)
                .append("\n\n")
                .append(VARS_MSG_TEST_P_FR).append("\n\n")
                ;

        for(TestUser usr : VARS_USERS) {
            stb.append(VARS_MSG_TEST_TXT_LBL_FR)
                .append(usr.getName())
                .append("\n   ")
                .append(usr.getVar1())
                .append("\n   ")
                .append(usr.getVar2())
                .append("\n   ")
                .append(usr.getVar3())
                .append("\n\n")
                ;
        }
        assertEquals(stb.toString(), r);
    }
    /**
     * Assert that indicated {@code user} is contained on indicated {@code element}.
     * @param user The user to assert is contained in {@code element}
     * @param element The HTML element that should to contain the {@code user}
     * @param label An optional text label that should to appear on a span at {@code element} 
     */
    private void assertUser(TestUser user, Element element, String label) {
        Elements vuVars;
        
        if(StringUtils.hasText(label)) {
            assertEquals(label, element.select("span").text());
        }
        assertEquals(user.getName(), element.select("strong").text());
        vuVars = element.select("ol > li"); 
        assertEquals(3, vuVars.size());
        assertEquals(Long.valueOf(user.getVar1()).toString(), vuVars.get(0).text());
        assertEquals(user.getVar2().toString(), vuVars.get(1).text());
        assertEquals(user.getVar3(), vuVars.get(2).text());
    }
}
