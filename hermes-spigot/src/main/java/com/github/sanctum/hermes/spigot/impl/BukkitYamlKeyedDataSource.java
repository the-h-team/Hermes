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

import com.github.sanctum.hermes.model.FileKeyedDataSource;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * A KeyedDataSource implementation backed by a YamlConfiguration.
 *
 * @since 1.0.0
 */
public final class BukkitYamlKeyedDataSource extends FileKeyedDataSource {
    private final YamlConfiguration yamlConfiguration;
    private final ConfigurationSection section;

    public BukkitYamlKeyedDataSource(@Nullable File file, @NotNull YamlConfiguration yamlConfiguration) {
        super(file);
        this.yamlConfiguration = yamlConfiguration;
        this.section = yamlConfiguration;
    }
    public BukkitYamlKeyedDataSource(@Nullable File file, @NotNull YamlConfiguration yamlConfiguration, String prefix) {
        super(file);
        this.yamlConfiguration = yamlConfiguration;
        if (prefix != null && !prefix.isEmpty()) {
            this.section = yamlConfiguration.getConfigurationSection("prefix");
            if (section == null) throw new IllegalArgumentException("Prefix does not map to valid section!");
        } else {
            this.section = yamlConfiguration;
        }
    }

    @Override
    public @Nullable String getString(String key) {
        return section.getString(key);
    }

    @Override
    public void reload() {
        if (file == null) return;
        try {
            yamlConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            JavaPlugin.getProvidingPlugin(getClass()).getLogger().warning(() -> "Unable to reload message file! " + e.getMessage());
        }
    }
}
