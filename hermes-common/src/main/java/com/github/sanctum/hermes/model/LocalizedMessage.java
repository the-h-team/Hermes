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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Represents a message that may be localized.
 *
 * @since 1.0.0
 */
public interface LocalizedMessage {

    /**
     * Get the default message.
     *
     * @return message
     */
    @NotNull ConfiguredMessage getDefault();

    /**
     * Get the message under the given locale.
     * <p>
     * Returns null if not localized.
     *
     * @param locale the locale to use
     * @return message if present under the locale or null
     */
    default @Nullable ConfiguredMessage getForLocale(Locale locale) {
        return null;
    }
}
