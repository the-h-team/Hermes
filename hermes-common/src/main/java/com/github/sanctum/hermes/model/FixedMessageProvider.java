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

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * An abstract message provider based on a fixed keyset.
 *
 * @since 1.0.0
 */
public abstract class FixedMessageProvider extends AbstractMessageProvider {
    private final Map<String, LocalizedMessage> messages;

    /**
     * Create a fixed-keyset message provider using
     * a set of strings and a keyed data source.
     *
     * @param keys a set of string keys
     * @param defSource a keyed data source
     */
    protected FixedMessageProvider(@NotNull Set<String> keys, @NotNull KeyedDataSource defSource) {
        super(defSource);
        final ImmutableMap.Builder<String, LocalizedMessage> builder = ImmutableMap.builder();
        for (String key : keys) {
            builder.put(key, new LocalizedMessage() {
                final ConfiguredMessage defaultMessage = new ConfiguredMessage() {
                    @Override
                    public @Nullable String raw() {
                        final String stringFromSource = FixedMessageProvider.this.dataSource.getString(key);
                        if (mapFunction == null) return stringFromSource;
                        return mapFunction.apply(stringFromSource);
                    }
                };

                @Override
                public @NotNull ConfiguredMessage getDefault() {
                    return defaultMessage;
                }

                @Override
                public @Nullable ConfiguredMessage getForLocale(Locale locale) {
                    return Optional.ofNullable(localizedDataSources.get(locale))
                            .map(WeakReference::new)
                            .map(kds -> new ConfiguredMessage() {
                                @Override
                                public @Nullable String raw() {
                                    final KeyedDataSource dataSource = kds.get();
                                    if (dataSource == null) return null;
                                    final String stringFromSource = dataSource.getString(key);
                                    if (mapFunction == null) return stringFromSource;
                                    return mapFunction.apply(stringFromSource);
                                }
                            })
                            .orElse(null);
                }
            });
        }
        this.messages = builder.build();
    }

    @Override
    public final @NotNull Map<String, LocalizedMessage> getMessages() {
        return messages;
    }

    /**
     * Create a fixed-keyset message provider using an enum implementing
     * {@link KeySource} and a keyed data source.
     *
     * @param enumClass an enum class implementing {@link KeySource}
     * @param defSource a keyed data source
     * @param <T> the enum
     * @return a new fixed-keyed message provider
     */
    public static <T extends Enum<? extends KeySource>> EnumKeySourceMessageProvider<T> enumAsKeySource(@NotNull Class<T> enumClass, @NotNull KeyedDataSource defSource) {
        return new EnumKeySourceMessageProvider<>(enumClass, defSource);
    }

    /**
     * Create a fixed-keyset message provider using an enum's constants'
     * names as its key source and a keyed data source.
     *
     * @param enumClass an enum class
     * @param defSource a keyed data source
     * @param <T> the enum
     * @return a new fixed-keyset message provider
     */
    public static <T extends Enum<?>> EnumNameMessageProvider<T> enumConstantNamesAsKey(@NotNull Class<T> enumClass, @NotNull KeyedDataSource defSource) {
        return new EnumNameMessageProvider<>(enumClass, defSource);
    }

    /**
     * An enum and key source-based message provider.
     *
     * @since 1.0.0
     */
    public static class EnumKeySourceMessageProvider<T extends Enum<? extends KeySource>> extends FixedMessageProvider {
        EnumKeySourceMessageProvider(@NotNull Class<T> keyClass, @NotNull KeyedDataSource defSource) {
            super(getKeySourceKeys(keyClass), defSource);
        }

        private static <T extends Enum<? extends KeySource>> HashSet<String> getKeySourceKeys(Class<T> messageClass) {
            final HashSet<String> set = new HashSet<>();
            for (T enumConstant : messageClass.getEnumConstants()) {
                set.add(((KeySource) enumConstant).getKey());
            }
            return set;
        }
    }

    /**
     * An enum constant-based message provider.
     *
     * @since 1.0.0
     */
    public static class EnumNameMessageProvider<T extends Enum<?>> extends FixedMessageProvider {
        EnumNameMessageProvider(@NotNull Class<T> keyClass, @NotNull KeyedDataSource defSource) {
            super(getConstantKeys(keyClass), defSource);
        }

        private static <T extends Enum<?>> HashSet<String> getConstantKeys(Class<T> messageClass) {
            final HashSet<String> set = new HashSet<>();
            for (T enumConstant : messageClass.getEnumConstants()) {
                set.add(enumConstant.name());
            }
            return set;
        }
    }

}
