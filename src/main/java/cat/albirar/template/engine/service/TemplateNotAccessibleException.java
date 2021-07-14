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

/**
 * Exception to indicate that the indicated template is not accessible, that is, doesn't exists OR is not a file OR is not readable.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 3.1.0
 */
public class TemplateNotAccessibleException extends RenderingException {
    private static final long serialVersionUID = 271657350537092329L;

    /**
     * See {@link RenderingException#RenderingException(String, Throwable)}.
     * @param message The exception message
     * @param cause The exception cause
     */
    public TemplateNotAccessibleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * See {@link RenderingException#RenderingException(String)}.
     * @param message The exception message
     */
    public TemplateNotAccessibleException(String message) {
        super(message);
    }

    /**
     * See {@link RenderingException#RenderingException(Throwable)}.
     * @param cause The exception cause
     */
    public TemplateNotAccessibleException(Throwable cause) {
        super(cause);
    }

}
