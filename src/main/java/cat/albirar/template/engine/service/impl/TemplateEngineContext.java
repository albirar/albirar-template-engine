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
package cat.albirar.template.engine.service.impl;

import java.util.Locale;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.util.StringUtils;
import org.thymeleaf.context.IContext;

import cat.albirar.template.engine.models.TemplateInstanceBean;

/**
 * The context for template instance rendering.
 * Set the context for render a {@link TemplateInstanceBean template instance}, like {@link Locale}, parameters, etc.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
public class TemplateEngineContext implements IContext {
    private TemplateInstanceBean templateInstance;
    /**
     * Constructor.
     */
    public TemplateEngineContext(TemplateInstanceBean t) {
        if(t == null) {
            throw new ValidationException();
        }
        this.templateInstance = t;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return templateInstance.getLocale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsVariable(String name) {
        if(!StringUtils.hasText(name)) {
            throw new ValidationException();
        }
        return templateInstance.getVariables().containsKey(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getVariableNames() {
        return templateInstance.getVariables().keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getVariable(String name) {
        if(!StringUtils.hasText(name)) {
            throw new ValidationException();
        }
        return templateInstance.getVariables().get(name);
    }
    /**
     * The original template instance.
     * @return The template instance
     */
    public TemplateInstanceBean getTemplateInstance() {
        return templateInstance;
    }
}
