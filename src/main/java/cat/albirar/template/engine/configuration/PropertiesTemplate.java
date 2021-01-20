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
 * Copyright (C) 2020 Octavi Fornés
 */
package cat.albirar.template.engine.configuration;

import org.springframework.beans.factory.annotation.Value;

/**
 * The property name configuration constants.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 1.0.0
 */
public interface PropertiesTemplate {
    /**
     * Root for all configuration properties of template engine.
     */
    public static final String ROOT_TEMPLATE_PROPERTIES = "albirar.templates";
    /**
     * The charset to be used on template engine.
     */
    public static final String CHARSET_PROPERTY_NAME = ROOT_TEMPLATE_PROPERTIES + ".charset";
    /**
     * The {@link Value} expression for charset configuration, default {@code UTF-8}.
     */
    public static final String CHARSET_VALUE = "${" + CHARSET_PROPERTY_NAME + ":UTF-8}";
}
