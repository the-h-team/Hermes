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
package com.github.sanctum.hermes.spigot.impl;

import com.github.sanctum.hermes.util.AbstractColorUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

/**
 * Spigot-specific utilities for color translation and null-sanitization.
 * <p>
 * Supports Hex colors for versions 1.16 and higher.
 *
 * @since 1.0.0
 * @see AbstractColorUtil
 */
public class HermesSpigotUtil extends AbstractColorUtil {
    private static HermesSpigotUtil instance;
    private final boolean hasHexSupport;

    private HermesSpigotUtil() {
        hasHexSupport = Bukkit.getVersion().matches("1\\.(1[6-9]|[2-9][0-9]).*");
    }

    @Override
    protected @NotNull String colorTranslate(@NotNull String text) {
        if (!text.contains("&")) return text;
        if (getSingleton().hasHexSupport && HEX_PATTERN.matcher(text).find()) {
            final Matcher matcher = HEX_PATTERN.matcher(text);
            final StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, ChatColor.of(matcher.group(1)).toString());
            }
            matcher.appendTail(sb);
            text = sb.toString();
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private synchronized static HermesSpigotUtil getSingleton() {
        if (instance == null) instance = new HermesSpigotUtil();
        return instance;
    }

    /**
     * Get the singleton instance of this utility.
     *
     * @return the singleton instance
     */
    public static HermesSpigotUtil getInstance() {
        return getSingleton();
    }
}
