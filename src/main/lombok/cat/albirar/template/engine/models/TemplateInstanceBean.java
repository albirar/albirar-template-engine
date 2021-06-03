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
package cat.albirar.template.engine.models;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.constraints.NotNull;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * The template definition with variables, messages and locale ready to render.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@Data
@SuperBuilder(toBuilder = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TemplateInstanceBean extends TemplateDefinitionBean {
    private static final long serialVersionUID = -5629519913709919500L;

    @Setter(onParam_ = { @NotNull })
    @NotNull
    private MessageSource messages;
    
    @Default
    @Setter(onParam_ = { @NotNull })
    @NotNull
    private Map<String, Object> variables = new TreeMap<>();
    
    @Setter(onParam_ = { @NotNull })
    @NotNull
    private Locale locale;
    
    /**
     * Build an instance with {@link Locale#getDefault()} and without {@link TemplateInstanceBean#getMessages()} and an empty {@link TemplateInstanceBean#getVariables()}. 
     * @param definition The template definition
     * @param locale The locale
     * @return The instance configured ready to parse
     */
    @SuppressWarnings("rawtypes")
    public static TemplateInstanceBeanBuilder buildInstance(TemplateDefinitionBean definition) {
        return buildBuilder(definition)
                ;
    }
    @SuppressWarnings({"rawtypes"})
    private static TemplateInstanceBeanBuilder buildBuilder(TemplateDefinitionBean definition) {
        return builder()
                .name(definition.getName())
                .charSet(definition.getCharSet())
                .contentType(definition.getContentType())
                .template(definition.getTemplate())
                .templateEngineLanguage(definition.getTemplateEngineLanguage())
                .locale(new Locale(""))
                .messages(new ResourceBundleMessageSource())
                ;
    }
    /**
     * Build an instance with {@code locale} and without {@link TemplateInstanceBean#getMessages()} and an empty {@link TemplateInstanceBean#getVariables()}. 
     * @param definition The template definition
     * @param locale The locale
     * @return The instance configured ready to parse
     */
    @SuppressWarnings("rawtypes")
    public static TemplateInstanceBeanBuilder buildInstance(TemplateDefinitionBean definition, Locale locale) {
        return buildBuilder(definition)
                .locale(locale)
                ;
    }
    /**
     * Build an instance with default {@code locale} and a {@link TemplateInstanceBean#getMessages() resource messages} from {@code baseNames} and an empty {@link TemplateInstanceBean#getVariables()}. 
     * @param definition The template definition
     * @param baseNames The base names or messages for search for
     * @return The instance configured ready to parse
     */
    @SuppressWarnings("rawtypes")
    public static TemplateInstanceBeanBuilder buildInstance(TemplateDefinitionBean definition, String baseNames) {
        ResourceBundleMessageSource rb;
        
        rb = new ResourceBundleMessageSource();
        rb.addBasenames(baseNames);
        return buildBuilder(definition)
                .messages(rb)
                ;
    }
    /**
     * Build an instance with {@code locale} and a {@link TemplateInstanceBean#getMessages() resource messages} from {@code baseNames} and an empty {@link TemplateInstanceBean#getVariables()}. 
     * @param definition The template definition
     * @param locale The locale
     * @param baseNames The base names or messages for search for
     * @return The instance configured ready to parse
     */
    @SuppressWarnings("rawtypes")
    public static TemplateInstanceBeanBuilder buildInstance(TemplateDefinitionBean definition, Locale locale, String baseNames) {
        ResourceBundleMessageSource rb;
        
        rb = new ResourceBundleMessageSource();
        rb.addBasenames(baseNames);
        return buildBuilder(definition)
                .locale(locale)
                .messages(rb)
                ;
    }
}
