/*
 *  Copyright 2021 ms5984 (Matt) <https://github.com/ms5984>
 *  Copyright 2022 Sanctum <https://github.com/the-h-team>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.sanctum.hermes.impl;

import com.github.sanctum.hermes.model.KeyedDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

/**
 * A Java {@linkplain Properties}-based KeyedDataSource.
 * <p>
 * <i>This class exposes the contained Properties instance as
 * <code>public final</code>; care should be taken to limit the
 * accessibility of these objects to those that may safely
 * mutate the Properties instance.</i>
 *
 * @since 1.0.0
 */
public class PropertiesKeyedDataSource implements KeyedDataSource {
    /**
     * The backing properties instance.
     */
    public final Properties properties;

    /**
     * Create a Properties-based keyed data source.
     *
     * @param properties a Properties instance
     */
    public PropertiesKeyedDataSource(@NotNull Properties properties) {
        this.properties = properties;
    }

    @Override
    public final @Nullable String getString(String key) {
        return properties.getProperty(key);
    }

    @Override
    public void reload() {}
}
