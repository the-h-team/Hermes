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

import com.github.sanctum.hermes.api.HermesRegistry;
import com.github.sanctum.hermes.api.RegisterAs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

/**
 * Represents a message provider.
 *
 * @since 1.0.0
 */
public abstract class MessageProvider {
    protected Function<@Nullable String, @Nullable String> mapFunction;

    {
        analyzeRegistrationAnnotation(this);
    }

    /**
     * Set the mapping function to be performed on all messages
     * as they are requested.
     * <p>
     * <b>Useful for color translation.</b>
     * <p>
     * The operation should be null-safe; it can both accept
     * and return null.
     * <p>
     * A null value for the argument will clear the existing function.
     *
     * @param mapFunction a mapping function (null to clear)
     */
    public final void setMapFunction(@Nullable Function<@Nullable String, @Nullable String> mapFunction) {
        this.mapFunction = mapFunction;
    }

    /**
     * Get all the messages from this provider.
     *
     * @return message from this provider
     */
    public abstract @NotNull Map<String, LocalizedMessage> getMessages();

    /**
     * Register this provider with {@link HermesRegistry} at the provided key.
     *
     * @param key a unique key
     */
    protected void registerProvider(@NotNull String key) {
        HermesRegistry.register(key, this);
    }

    // Checks for annotation + registers at key if present
    private static void analyzeRegistrationAnnotation(MessageProvider provider) {
        final RegisterAs a = provider.getClass().getDeclaredAnnotation(RegisterAs.class);
        if (a != null) {
            provider.registerProvider(a.value());
        }
    }
}
