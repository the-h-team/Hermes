/*
 *  Copyright 2021 ms5984 (Matt) <https://github.com/ms5984>
 *
 *  This file is part of Hermes.
 *
 *  Hermes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  Hermes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may use this file in compliance with the License or aforementioned.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.ms5984.hermes.impl;

import com.github.ms5984.hermes.model.KeyedDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Properties;

/**
 * A Java {@linkplain Properties}-based KeyedDataSource.
 *
 * @since 1.0.0
 */
public abstract class PropertiesKeyedDataSource implements KeyedDataSource {
    public final Properties properties;

    public PropertiesKeyedDataSource(@NotNull Properties properties) {
        this.properties = properties;
    }

    @Override
    public final @Nullable String getString(String key) {
        return properties.getProperty(key);
    }
}
