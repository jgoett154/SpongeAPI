/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.world.gen.feature;

import org.spongepowered.api.world.ProtoWorld;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.gen.FeatureConfig;
import org.spongepowered.api.world.gen.TerrainGenerator;
import org.spongepowered.math.vector.Vector3i;

import java.util.Random;

/**
 * A pre-composed {@link Feature} that has a designated and configured
 * {@link FeaturePlacer} and {@link Feature}. The uses for this is to
 * pre-compose features that would otherwise be found in {@link BiomeType}s.
 */
public interface FeatureComposition<F extends FeatureConfig, P extends PlacementConfig> extends Feature<F> {

    F getFeatureConfig();

    Feature<F> getFeature();

    P getPlacementConfig();

    FeaturePlacer<P> getPlacer();

    @Override
    default boolean generate(ProtoWorld<?> world, TerrainGenerator<?> generator, Random random, Vector3i origin, F config) {
        return this.getPlacer().place(world, generator, random, origin, this.getPlacementConfig(), this.getFeature(), config);
    }
}
