package com.github.ms5984.hermes.bungeecord.impl;

import com.github.ms5984.hermes.model.FileKeyedDataSource;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

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
