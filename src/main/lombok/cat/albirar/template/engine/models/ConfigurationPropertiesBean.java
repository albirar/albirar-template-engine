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
package cat.albirar.template.engine.models;

import static cat.albirar.template.engine.configuration.PropertiesTemplate.DEFAULT_CHARSET;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cat.albirar.template.engine.configuration.PropertiesTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The properties of albirar template engine configuration.
 * @author Octavi Forn&eacute;s &lt;<a href="mailto:ofornes@albirar.cat">ofornes@albirar.cat</a>&gt;
 * @since 3.1.0
 */
@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationPropertiesBean {

    /**
     * The default charset for templates and output.
     */
    @Setter(onParam_ = { @NotNull })
    @NotNull
    @Default
    private Charset charset = DEFAULT_CHARSET;
    /**
     * The template directories.
     */
    @Setter(onParam_ = { @NotEmpty })
    @NotEmpty
    @Default
    private List<Path> directories = Stream.of(PropertiesTemplate.DEFAULT_DIR_ARRAY).map(d -> Paths.get(d)).collect(Collectors.toList());
}
