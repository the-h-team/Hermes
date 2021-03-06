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
package com.github.sanctum.hermes.api;

import com.github.sanctum.hermes.model.MessageProvider;
import com.google.common.collect.MapMaker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Get all registered message providers.
 * <p>
 * Instances are held via WeakReference; thus, care should be taken to
 * ensure that implementing assemblies hold their providers with strong
 * references to prevent their premature demise via garbage collection.
 *
 * @since 1.0.0
 */
public class HermesRegistry {
    private static final Map<String, MessageProvider> PROVIDERS = new MapMaker().weakValues().makeMap();

    private HermesRegistry() {}

    /**
     * Registers a provider at the given key.
     *
     * @param providerKey a unique key
     * @param provider a provider instance
     */
    public static void register(@NotNull String providerKey, MessageProvider provider) {
        synchronized (PROVIDERS) {
            PROVIDERS.put(providerKey, provider);
        }
    }

    /**
     * Get a provider by its registration key.
     *
     * @param providerKey the registration key of the provider
     * @return the provider or null
     */
    public static @Nullable MessageProvider getProvider(String providerKey) {
        return PROVIDERS.get(providerKey);
    }

    /**
     * Unregister a provider by its registration key.
     *
     * @param providerKey the registration key of the provider
     * @return true only if a provider was removed
     */
    public static boolean unregister(@NotNull String providerKey) {
        synchronized (PROVIDERS) {
            return PROVIDERS.remove(providerKey) != null;
        }
    }

    /**
     * Unregister a provider by its instance in the registry.
     *
     * @param provider the provider instance in the registry
     * @return true only if the provider was removed
     */
    public static boolean unregister(@NotNull MessageProvider provider) {
        synchronized (PROVIDERS) {
            for (String key : PROVIDERS.keySet()) {
                if (PROVIDERS.remove(key, provider)) {
                    return true;
                }
            }
        }
        return false;
    }
}
