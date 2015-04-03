/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehcache.internal.tier;

import org.ehcache.config.EvictionPrioritizer;
import org.ehcache.config.EvictionVeto;
import org.ehcache.expiry.Expiry;
import org.ehcache.internal.TimeSource;
import org.ehcache.spi.cache.tiering.AuthoritativeTier;

/**
 * @author Aurelien Broszniowski
 */
public interface AuthoritativeTierFactory<K, V> {

  AuthoritativeTier<K, V> newTier(AuthoritativeTier.Configuration<K, V> config);

  AuthoritativeTier<K, V> newTier(AuthoritativeTier.Configuration<K, V> config, TimeSource timeSource);

  AuthoritativeTier.Configuration<K, V> newConfiguration(
      Class<K> keyType, Class<V> valueType, Comparable<Long> capacityConstraint,
      EvictionVeto<? super K, ? super V> evictionVeto, EvictionPrioritizer<? super K, ? super V> evictionPrioritizer);

  AuthoritativeTier.Configuration<K, V> newConfiguration(
      Class<K> keyType, Class<V> valueType, Comparable<Long> capacityConstraint,
      EvictionVeto<? super K, ? super V> evictionVeto, EvictionPrioritizer<? super K, ? super V> evictionPrioritizer,
      Expiry<? super K, ? super V> expiry);

  Class<K> getKeyType();

  Class<V> getValueType();

  K createKey(long seed);

  V createValue(long seed);

}
