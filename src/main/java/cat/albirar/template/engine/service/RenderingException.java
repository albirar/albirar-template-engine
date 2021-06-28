/*
 * This file is part of "albirar-template-engine".
 * 
 * "albirar-template-engine" is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * "albirar-template-engine" is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with "albirar-template-engine" source code.
 * If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 *
 * Copyright (C) 2021 Octavi Fornés
 */
package cat.albirar.template.engine.service;

/**
 * Root exception for rendering exception cases.
 * 
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 3.1.0
 */
public class RenderingException extends RuntimeException {
    private static final long serialVersionUID = -3224744698039688925L;

    /**
     * See {@link RuntimeException#RuntimeException(String, Throwable)}.
     * @param message The exception message
     * @param cause The exception cause
     */
    public RenderingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * See {@link RuntimeException#RuntimeException(String)}.
     * @param message The exception message
     */
    public RenderingException(String message) {
        super(message);
    }

    /**
     * See {@link RuntimeException#RuntimeException(Throwable)}.
     * @param cause The exception cause
     */
    public RenderingException(Throwable cause) {
        super(cause);
    }
}
