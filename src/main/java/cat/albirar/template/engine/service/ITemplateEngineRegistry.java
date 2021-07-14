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

import org.springframework.validation.annotation.Validated;

import cat.albirar.template.engine.ITemplateEngineFactory;

/**
 * A template engine registry that collaborate with {@link ITemplateEngineFactory} for internal use of template engine classes.
 * The implemented template engines should to register itself with {@link #register(ITemplateEngine)} method in order to be available on {@link ITemplateEngineFactory}.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 2.2.0
 */
@Validated
public interface ITemplateEngineRegistry {
    /**
     * Register a new {@code templateEngine} for use.
     * **ATENTION** If another template engine was configured with same {@link ITemplateEngine#getTemplateLanguage() template language}, this one overwrites the previously registered.
     * @param templateEngine The template engine
     */
    public void register(@NotNull @Valid ITemplateEngine templateEngine);
}
