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
package cat.albirar.template.engine;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import cat.albirar.template.engine.service.IEngineRender;
import cat.albirar.template.engine.service.ITemplateEngine;

/**
 * The factory for template engine instances.
 * 
 * Exposes access to available template engines.
 * 
 * Can be used as:
 * [source,java]
 * --
 * @Autowired private ITemplateEngineFactory factory;
 * 
 * // instantiate template definition...
 * factory.render(templateInstance);
 * --
 * 
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 2.2.0
 */
public interface ITemplateEngineFactory extends IEngineRender {
    /**
     * Get the {@link ITemplateEngine template engine} associated with the indicated {@code templateLanguage}.
     * @param templateLanguage The template language, is a text literal that identify uniquely a template engine, by example: _thymeleaf_, _velocity_, _mustache_, etc.
     * @return The template engine or {@link Optional#empty()} if no template engine was found for the indicated {@code templateLanguage}
     */
    public Optional<ITemplateEngine> getTemplateEngine(@NotBlank String templateLanguage);
    
    /**
     * Return an immutable list of registered template engine languages.
     * @return A list, not null but can be empty, with the registered template engine languages
     */
    public List<String> getRegisteredTemplateLanguages();
}
