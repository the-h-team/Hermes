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
package com.github.sanctum.hermes.bungeecord.impl;

import com.github.sanctum.hermes.model.FileKeyedDataSource;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * A KeyedDataSource implementation backed by a subclass of
 * Bungeecord's ConfigurationProvider.
 *
 * @since 1.0.0
 * @param <T> the type of ConfigurationProvider
 */
public final class BungeecordKeyedDataSource<T extends ConfigurationProvider> extends FileKeyedDataSource {
    private final ConfigurationProvider provider;
    private Configuration configuration;

    public BungeecordKeyedDataSource(@Nullable File file, @NotNull Class<T> configurationType) {
        super(file);
        this.provider = ConfigurationProvider.getProvider(configurationType);
        try {
            reload();
        } catch (IllegalStateException e) {
            configuration = new Configuration();
        }
    }

    @Override
    public @Nullable String getString(String key) {
        return configuration.getString(key);
    }

    @Override
    public void reload() {
        if (file == null) return;
        try {
            configuration = provider.load(file);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to reload config!");
        }
    }
}
