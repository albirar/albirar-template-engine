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

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import cat.albirar.template.engine.ITemplateEngineFactory;
import cat.albirar.template.engine.models.TemplateInstanceBean;
import cat.albirar.template.engine.service.IEngineRender;
import cat.albirar.template.engine.service.ITemplateEngine;
import cat.albirar.template.engine.service.ITemplateEngineRegistry;
import cat.albirar.template.engine.service.TemplateNotAccessibleException;

/**
 * A default registry for {@link ITemplateEngine}.
 * Any template engine should to be registered with {@link #register(ITemplateEngine)} method.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 2.2.0
 */
@Service
public class TemplateEngineRegistryDefaultImpl implements ITemplateEngineRegistry, ITemplateEngineFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateEngineRegistryDefaultImpl.class);
    
    @Autowired
    private ApplicationContext applicationContext;
    
    private Map<String, ITemplateEngine> engines = new HashMap<String, ITemplateEngine>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ITemplateEngine> getTemplateEngine(@NotBlank String templateLanguage) {
        LOGGER.debug("Search template engine for {} template language", templateLanguage);
        return Optional.ofNullable(engines.get(templateLanguage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getRegisteredTemplateLanguages() {
        LOGGER.debug("Compound registered template languages list {}", engines.keySet());
        return Collections.unmodifiableList(engines.keySet().stream().collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(@NotNull @Valid ITemplateEngine templateEngine) {
        LOGGER.debug("Register a new template engine for {} template language", templateEngine.getTemplateLanguage());
        engines.put(templateEngine.getTemplateLanguage(), templateEngine);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String renderTemplate(@NotNull @Valid TemplateInstanceBean template) {
        Optional<ITemplateEngine> te;

        LOGGER.debug("Render template {} of {} template language", template.getName(), template.getTemplateEngineLanguage());
        // Search the correct template engine by language and render
        te = getTemplateEngine(template.getTemplateEngineLanguage());
        // If exists, render can be made...
        if(te.isPresent()) {
            LOGGER.debug("Template engine for {} template language found, check resources...", template.getTemplateEngineLanguage());
            // Check the preconditions of template itself...
            checkResource(template.getTemplate());
            LOGGER.debug("Template engine for {} template language found and resources checked, render it", template.getTemplateEngineLanguage());
            // If all is ok, render it
            return te.get().renderTemplate(template);
        }
        LOGGER.debug("Template engine for {} template language NOT FOUND!", template.getTemplateEngineLanguage());
        // If no template engine was found, a exception should to be thrown
        throw new IllegalStateException("No engine was registerd for the template language '".concat(template.getTemplateEngineLanguage()).concat("'"));
    }
    /**
     * Checks the preconditions for the indicated {@code strResource} as established at {@link IEngineRender#renderTemplate(TemplateInstanceBean)}.
     * @param strResource The resource to check
     */
    private void checkResource(String strResource) {
        Resource resource;
        
        LOGGER.debug("Check resource {}", strResource);
        resource = applicationContext.getResource(strResource);
        if(!resource.exists()) {
            LOGGER.error("Resource {} DOES NOT EXISTS!", strResource);
            throw new TemplateNotAccessibleException(String.format("The resource '%s' does not exists!", strResource));
        }
        try {
            if(!resource.getFile().isFile()) {
                LOGGER.error("Resource {} IS NOT A FILE!", strResource);
                throw new TemplateNotAccessibleException(String.format("The resource '%s' is not a regular file!", strResource));
            }
        }
        catch(IOException e) {
            LOGGER.error(String.format("IOException on get the file for resource %s!", strResource), e);
        }
        if(!resource.isReadable()) {
            LOGGER.error("Resource {} IS NOT READABLE!", strResource);
            throw new TemplateNotAccessibleException(String.format("The resource '%s' is not readable!", strResource));
        }
        LOGGER.debug("Resource {} IS OK!", strResource);
    }
}
