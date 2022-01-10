/*
 *  Copyright 2022 ms5984 (Matt) <https://github.com/ms5984>
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

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstract message provider based on default
 * and locale-specific keyed data sources.
 *
 * @since 1.0.0
 */
public abstract class AbstractMessageProvider extends MessageProvider {
    protected final KeyedDataSource dataSource;
    protected final Map<Locale, KeyedDataSource> localizedDataSources = new ConcurrentHashMap<>();

    /**
     * Create a message provider with
     * a data source as its default.
     *
     * @param defSource a default data source
     */
    protected AbstractMessageProvider(@NotNull KeyedDataSource defSource) {
        this.dataSource = defSource;
    }

    /**
     * Get the default data source.
     *
     * @return the default data source
     */
    public final @NotNull KeyedDataSource getDefaultDataSource() {
        return dataSource;
    }

    /**
     * Get the data source set for a particular locale.
     *
     * @param locale a locale
     * @return an Optional describing the localized data source, if set
     */
    public final Optional<KeyedDataSource> getLocaleDataSource(Locale locale) {
        if (locale == null) return Optional.of(dataSource);
        return Optional.ofNullable(localizedDataSources.get(locale));
    }

    /**
     * Set a locale-specific data source.
     *
     * @param locale a locale
     * @param localeDataSource a localized data source
     */
    public final void setLocaleDataSource(@NotNull Locale locale, KeyedDataSource localeDataSource) {
        localizedDataSources.put(locale, localeDataSource);
    }
}
