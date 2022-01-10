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
package com.github.sanctum.hermes.model;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a String datasource that accepts String keys.
 *
 * @since 1.0.0
 */
public interface KeyedDataSource {
    /**
     * Get the message for the key.
     *
     * @param key String key to get message for
     * @return message or null
     */
    @Nullable String getString(String key);

    /**
     * Reload the datasource, if applicable.
     */
    void reload();
}
