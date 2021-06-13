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
package cat.albirar.template.engine.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cat.albirar.template.engine.models.TemplateInstanceBean;

/**
 * A specific operational part of {@link ITemplateEngine} that render the template.
 * For use on factory.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 2.2.0
 */
public interface IEngineRender {
    /**
     * Render the indicated {@code template} without variables to generate output.
     * @param template The template definition
     * @return The resulting template rendered
     * @throws IllegalStateException If the indicated {@code template} language is unknown (not registered engine is available for this template language)
     */
    public String renderTemplate(@NotNull @Valid TemplateInstanceBean template);
}
