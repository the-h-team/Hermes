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
package com.github.ms5984.hermes.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Base class for utilities concerning color translation and null-sanitization.
 *
 * @see 1.0.0
 */
public abstract class AbstractColorUtil {
    /**
     * Regex matching hex color codes in the following general forms:
     * <pre>{@literal &}#AABBCC</pre> All uppercase
     * <pre>{@literal &}#aabbcc</pre> All lowercase
     * <pre>{@literal &}#aAbBcC</pre> Mixed casing
     */
    protected static final Pattern HEX_PATTERN = Pattern.compile("&(#(\\d|[A-F]|[a-f]){6})");

    /**
     * Translate {@literal &}-formatted text into Minecraft colors.
     * <p>
     * Supports hex colors in the format {@literal &}#AaBbCc for versions
     * 1.16 and higher.
     *
     * @param text text to translate
     * @return translated text or null if text provided is null
     */
    @Contract("null -> null")
    public @Nullable String translateColors(@Nullable String text) {
        if (text == null) return null;
        return colorTranslate(text);
    }

    /**
     * Sanitize null strings and translate {@literal &}-formatted text
     * into Minecraft colors.
     * <p>
     * Supports hex colors in the format {@literal &}#AaBbCc for versions
     * 1.16 and higher.
     *
     * @param text String to sanitize or translate
     * @return sanitized or translated text
     */
    public @NotNull String sanitizeNullsAndTranslateColors(@Nullable String text) {
        if (text == null) return "null";
        return colorTranslate(text);
    }

    /**
     * A platform-specific color translation function.
     *
     * @param text text to translate
     * @return translated result
     */
    protected abstract @NotNull String colorTranslate(@NotNull String text);
}
