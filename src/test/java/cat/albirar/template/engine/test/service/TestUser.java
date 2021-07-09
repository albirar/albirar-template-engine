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
package cat.albirar.template.engine.test.service;

import java.time.LocalDateTime;

/**
 * A simple mode for test purposes.
 * @author Octavi Forn&eacute;s <mailto:ofornes@albirar.cat[]>
 * @since 1.0.0
 */
public class TestUser {
    private String name;
    private long var1;
    private LocalDateTime var2;
    private String var3;
    
    public static TestUserBuilder builder() {
        return new TestUserBuilder();
    }
    public static class TestUserBuilder {
        private TestUser tu = new TestUser();
        public TestUserBuilder name(String name) {
            tu.setName(name);
            return this;
        }
        public TestUserBuilder var1(long var1) {
            tu.setVar1(var1);
            return this;
        }
        public TestUserBuilder var2(LocalDateTime var2) {
            tu.setVar2(var2);
            return this;
        }
        public TestUserBuilder var3(String var3) {
            tu.setVar3(var3);
            return this;
        }
        public TestUser build() {
            return tu;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getVar1() {
        return var1;
    }
    public void setVar1(long var1) {
        this.var1 = var1;
    }
    public LocalDateTime getVar2() {
        return var2;
    }
    public void setVar2(LocalDateTime var2) {
        this.var2 = var2;
    }
    public String getVar3() {
        return var3;
    }
    public void setVar3(String var3) {
        this.var3 = var3;
    }
}
