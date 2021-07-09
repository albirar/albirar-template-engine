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

import static cat.albirar.template.engine.configuration.PropertiesTemplate.DEFAULT_CHARSET_NAME;

import java.io.Serializable;
import java.nio.charset.Charset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationContext;

import cat.albirar.template.engine.EContentType;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The template definition bean.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0, 2.2.0
 */
@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDefinitionBean implements Serializable {
    private static final long serialVersionUID = 7550617345858079032L;
    
    public static final String DEFAULT_CHARSET = DEFAULT_CHARSET_NAME;
    public static final EContentType DEFAULT_CONTENT_TYPE = EContentType.TEXT_PLAIN;
    

    /**
     * A symbolic name for this template.
     */
    @Setter(onParam_ = { @NotBlank })
    @NotBlank
    private String name;

    /**
     * The template resource path, can be any of the resources resolved by {@link ApplicationContext#getResource(String)}.
     * @return The template resource path
     * @param template The template resource path
     * @see ApplicationContext#getResource(String)
     */
    @Setter(onParam_ = { @NotBlank })
    @NotBlank
    private String template;
    
    /**
     * The template engine language.
     * Identify the engine capable of transform the given template
     * 
     */
    @Setter(onParam_ = { @NotBlank})
    @NotBlank
    private String templateEngineLanguage;
    
    /**
     * The resulting content type of this template.
     */
    @Setter(onParam_ = { @NotNull })
    @NotNull
    @Default
    private EContentType contentType = DEFAULT_CONTENT_TYPE;
    
    /**
     * The charset of resulting content from this template.
     */
    @Setter(onParam_ = { @NotNull })
    @NotNull
    @Default
    private Charset charSet = Charset.forName(DEFAULT_CHARSET);
}
