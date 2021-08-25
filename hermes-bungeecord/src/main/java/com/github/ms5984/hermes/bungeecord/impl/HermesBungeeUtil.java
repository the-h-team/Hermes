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
package com.github.ms5984.hermes.bungeecord.impl;

import com.github.ms5984.hermes.util.AbstractColorUtil;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

/**
 * Bungee-specific utilities for color translation and null-sanitization.
 *
 * @since 1.0.0
 * @see com.github.ms5984.hermes.util.AbstractColorUtil
 */
public class HermesBungeeUtil extends AbstractColorUtil {
    private static final HermesBungeeUtil instance = new HermesBungeeUtil();
    private boolean enableHex = true;

    private HermesBungeeUtil() {}

    @Override
    protected @NotNull String colorTranslate(@NotNull String text) {
        if (enableHex) {
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

    /**
     * Whether hex color translation will be attempted.
     * <p>
     * <b>Defaults to true.</b>
     *
     * @return true if hex colors will be translated
     */
    public final boolean isHexEnabled() {
        return enableHex;
    }

    /**
     * Set whether hex color translation should be attempted.
     * <p>
     * <b>Defaults to true.</b>
     *
     * @param enableHex whether hex color translation should be attempted
     */
    public final void setHexEnabled(boolean enableHex) {
        this.enableHex = enableHex;
    }

    /**
     * Get the singleton instance of this utility.
     *
     * @return the singleton instance
     */
    public static HermesBungeeUtil getInstance() {
        return instance;
    }
}
