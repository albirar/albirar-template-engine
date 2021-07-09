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

import java.nio.charset.Charset;

/**
 * The property name configuration constants.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
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
     * The global wide default charset name.
     */
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    /**
     * The global wide default charset.
     */
    public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
    /**
     * The default array of directories.
     */
    public static final String [] DEFAULT_DIR_ARRAY = { "/tmp" }; 
    /**
     * Directory or directories of templates.
     * Can be only one o more than one (array).
     */
    public static final String DIR_TEMPLATES_PROPERTY_NAME = ROOT_TEMPLATE_PROPERTIES + ".directories";
}
