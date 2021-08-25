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
package com.github.ms5984.hermes.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a message that can be customized.
 *
 * @since 1.0.0
 */
public abstract class ConfiguredMessage {

    /**
     * Get the message from config.
     * <p>
     * Nullable as the message may be undefined.
     *
     * @return raw message text
     */
    public abstract @Nullable String get();

    /**
     * Replace placeholders with objects.
     *
     * @param replacements varargs of replacement
     * @return message with replacements
     */
    public final @NotNull String replace(Object... replacements) {
        String string = toString();
        int i = 0;
        for (Object object : replacements) {
            string = string.replace("{" + i++ + "}", String.valueOf(object));
        }
        return string;
    }

    /**
     * Get the message text, returning "null" if missing.
     *
     * @return message text or "null" String
     */
    @Override
    public final @NotNull String toString() {
        return String.valueOf(get());
    }

}
