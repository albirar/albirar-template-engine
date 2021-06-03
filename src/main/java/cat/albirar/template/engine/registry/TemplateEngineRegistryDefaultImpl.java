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
package cat.albirar.template.engine.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.service.ITemplateEngineRegistry;

/**
 * A default registry for {@link ITemplateEngine}.
 * Any template engine should to be registered with {@link #register(ITemplateEngine)} method.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 2.2.0
 */
@Service
public class TemplateEngineRegistryDefaultImpl implements ITemplateEngineRegistry, ITemplateEngineFactory {
    private Map<String, ITemplateEngine> engines = new HashMap<String, ITemplateEngine>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ITemplateEngine> getTemplateEngine(@NotBlank String templateLanguage) {
        return Optional.ofNullable(engines.get(templateLanguage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRegisteredTemplateLanguages() {
        return Collections.unmodifiableList(engines.keySet().stream().collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(@NotNull @Valid ITemplateEngine templateEngine) {
        engines.put(templateEngine.getTemplateLanguage(), templateEngine);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String renderTemplate(@NotNull @Valid TemplateInstanceBean template) {
        // Search the correct template engine by language and render
        Optional<ITemplateEngine> te;
        
        te = getTemplateEngine(template.getTemplateEngineLanguage());
        if(te.isPresent()) {
            return te.get().renderTemplate(template);
        }
        // If no template engine was found, return the template without any transformation
        return template.getTemplate();
    }
}
