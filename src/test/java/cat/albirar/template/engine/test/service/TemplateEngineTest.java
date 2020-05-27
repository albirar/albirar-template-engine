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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import cat.albirar.template.engine.models.TemplateDefinitionBean;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.test.configuration.DefaultTestConfiguration;

/**
 * Test for {@link ITemplateEngine}.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultTestConfiguration.class)
public class TemplateEngineTest {
    private static final String SIMPLE_TEST = "classpath:/cat/albirar/template/engine/test/templates/simpleTemplate.html";
    private static final String SIMPLE_TEST_H1 = "Text";
    private static final String SIMPLE_TEST_P = "A simple template";
    
    private static final String VARS_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsTemplate.html";
    private static final String VARS_TEST_H1 = "Vars test title";
    
    private static final String VARS_MSG_TEST_MSG_RESOURCE = "cat/albirar/template/engine/test/messages/testMessages";
    private static final String VARS_MSG_TEST = "classpath:/cat/albirar/template/engine/test/templates/varsMessagesTemplate.html";
    private static final String VARS_MSG_TEST_H1 = "Vars &amp; Msg test title";
    private static final String VARS_MSG_TEST_P_EN = "This is a template for test with variables";
    private static final String VARS_MSG_TEST_P_CA = "Aquesta és una plantilla de prova amb variables";
    private static final String VARS_MSG_TEST_P_FR = "Ceci est un modèle de test avec des variables";
    private static final String VARS_MSG_TEST_LBL_EN = "User name:&nbsp;";
    private static final String VARS_MSG_TEST_LBL_CA = "Nom usuari:&nbsp;";
    private static final String VARS_MSG_TEST_LBL_FR = "Nom d'utilisateur:&nbsp;";
    
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
    private ITemplateEngine templateEngine;
    
    @Test
    public void testNullsInvalids() {
        Assertions.assertThrows(ValidationException.class, () -> templateEngine.renderTemplate(null));
        Assertions.assertThrows(ValidationException.class, () -> templateEngine.renderTemplate(TemplateInstanceBean.builder().build()));
    }
    
    @Test
    public void testRenderWithoutMessagesNorVariables() {
        String tx;
        Document parsed;
        
        tx = templateEngine.renderTemplate(TemplateInstanceBean.buildInstance(TemplateDefinitionBean.builder()
                .name("Test1")
                .template(SIMPLE_TEST)
                .build()).build());
        Assertions.assertNotNull(tx);
        Assertions.assertTrue(StringUtils.hasText(tx));
        
        parsed = Jsoup.parse(tx);
        Assertions.assertEquals(1, parsed.select("h1").size());
        Assertions.assertEquals(SIMPLE_TEST_H1, parsed.select("h1").first().text());
        Assertions.assertEquals(1, parsed.select("p").size());
        Assertions.assertEquals(SIMPLE_TEST_P, parsed.select("p").first().text());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testRenderWithVariablesNotMessages() {
        TemplateInstanceBean tinst;
        Map<String, Object> vars;
        String r;
        Document parsed;
        Elements vu;
        int n;
        
        vars = new TreeMap<>();
        vars.put("title", VARS_TEST_H1);
        vars.put("users", Arrays.asList(VARS_USERS));
        tinst = TemplateInstanceBean.buildInstance(TemplateDefinitionBean.builder()
                    .name("Test 2")
                    .template(VARS_TEST).build())
                .variables(vars)
                .build()
                ;
        r = templateEngine.renderTemplate(tinst);
        Assertions.assertNotNull(r);
        Assertions.assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        Assertions.assertEquals(1, parsed.select("h1").size());
        Assertions.assertEquals(VARS_TEST_H1, parsed.select("h1").first().text());
        
        vu = parsed.select("li.user");
        Assertions.assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, null);
            n++;
        }
    }

    
    @Test
    @SuppressWarnings("unchecked")
    public void testRenderWithVariablesAndMessages() {
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
        tinst = TemplateInstanceBean.buildInstance(TemplateDefinitionBean.builder()
                    .name("Test 2")
                    .template(VARS_MSG_TEST).build(), VARS_MSG_TEST_MSG_RESOURCE)
                .variables(vars)
                .build()
                ;
        
        r = templateEngine.renderTemplate(tinst);
        Assertions.assertNotNull(r);
        Assertions.assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        Assertions.assertEquals(1, parsed.select("h1").size());
        Assertions.assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        Assertions.assertEquals(1, parsed.select("p").size());
        Assertions.assertEquals(VARS_MSG_TEST_P_EN, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        Assertions.assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_EN);
            n++;
        }
        
        
        // TEST CA
        r = templateEngine.renderTemplate(tinst.toBuilder().locale(new Locale("ca")).build());
        Assertions.assertNotNull(r);
        Assertions.assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        Assertions.assertEquals(1, parsed.select("h1").size());
        Assertions.assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        Assertions.assertEquals(1, parsed.select("p").size());
        Assertions.assertEquals(VARS_MSG_TEST_P_CA, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        Assertions.assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_CA);
            n++;
        }
        
        // TEST FR
        r = templateEngine.renderTemplate(tinst.toBuilder().locale(new Locale("fr")).build());
        Assertions.assertNotNull(r);
        Assertions.assertTrue(StringUtils.hasText(r));

        parsed = Jsoup.parse(r);
        
        Assertions.assertEquals(1, parsed.select("h1").size());
        Assertions.assertEquals(VARS_MSG_TEST_H1, parsed.select("h1").first().text());
        
        Assertions.assertEquals(1, parsed.select("p").size());
        Assertions.assertEquals(VARS_MSG_TEST_P_FR, parsed.select("p").text());
        
        vu = parsed.select("li.user");
        Assertions.assertEquals(VARS_USERS.length, vu.size());
        n = 0;
        for(Element element : vu) {
            assertUser(VARS_USERS[n], element, VARS_MSG_TEST_LBL_FR);
            n++;
        }
    }
    
    private void assertUser(TestUser user, Element element, String label) {
        Elements vuVars;
        
        if(StringUtils.hasText(label)) {
            Assertions.assertEquals(label, element.select("span").text());
        }
        Assertions.assertEquals(user.getName(), element.select("strong").text());
        vuVars = element.select("ol > li"); 
        Assertions.assertEquals(3, vuVars.size());
        Assertions.assertEquals(Long.valueOf(user.getVar1()).toString(), vuVars.get(0).text());
        Assertions.assertEquals(user.getVar2().toString(), vuVars.get(1).text());
        Assertions.assertEquals(user.getVar3(), vuVars.get(2).text());
    }
}
