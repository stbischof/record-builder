/*
 * Copyright 2019 The original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.soabase.recordbuilder.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestVariousOptions {

    @Test
    public void builderGetsCustomSetterAndGetterNames() {
        var obj = CustomMethodNamesBuilder.builder().setTheValue(1).setTheList(List.of(2)).setTheBoolean(true);
        assertEquals(1, obj.getTheValue());
        assertEquals(List.of(2), obj.getTheList());
        assertTrue(obj.isTheBoolean());
        assertEquals(new CustomMethodNames(1, List.of(2), true), obj.build());
    }

    @Test
    public void withBuilderGetsCustomSetterAndGetterNames() {
        var obj = CustomMethodNamesBuilder.from(
                CustomMethodNamesBuilder.builder().setTheValue(1).setTheList(List.of(2)).setTheBoolean(true).build());
        assertEquals(1, obj.getTheValue());
        assertEquals(List.of(2), obj.getTheList());
        assertTrue(obj.isTheBoolean());
    }

    @Test
    public void recordHasPrefixedGetters() {
        var obj = new CustomMethodNames(1, List.of(2), true);
        assertEquals(1, obj.getTheValue());
        assertEquals(List.of(2), obj.getTheList());
        assertTrue(obj.isTheBoolean());
    }

    @Test
    public void noStaticBuilder() {
        boolean hasStaticBuilder = Stream.of(NoStaticBuilderBuilder.class.getDeclaredMethods())
                .anyMatch(method -> method.getName().equals("NoStaticBuilder"));
        assertFalse(hasStaticBuilder);

        hasStaticBuilder = Stream.of(SimpleRecordBuilder.class.getDeclaredMethods())
                .anyMatch(method -> method.getName().equals("SimpleRecord"));
        assertTrue(hasStaticBuilder);
    }
}
