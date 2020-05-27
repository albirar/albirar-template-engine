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

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.context.ApplicationContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The template definition bean.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDefinitionBean implements Serializable {
    private static final long serialVersionUID = 7550617345858079032L;

    /**
     * A simbolic name for this template.
     */
    @Setter(onParam_ = { @NotBlank })
    @NotBlank
    private String name;

    /**
     * The template resource path, can be any of the resources resolved by {@link ApplicationContext#getResource(String)}.
     * @see ApplicationContext#getResource(String)
     */
    @Setter(onParam_ = { @NotBlank })
    @NotBlank
    private String template;
}
