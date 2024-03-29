/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023-2024, Articular-ES, The AvrSandbox Project
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package articular.util;

import articular.core.Entity;
import articular.core.MemoryMap;
import articular.core.component.Component;
import articular.core.system.ComponentUpdater;
import articular.core.system.SystemController;
import articular.core.system.manager.CacheManager;
import articular.core.system.manager.EntityComponentManager;

/**
 * TODO
 *
 * @param <I> TODO
 * @author pavl_g
 */
@SuppressWarnings("unchecked")
public class ArticularManager<I> extends EntityComponentManager<I> {

    /**
     * TODO
     */
    protected CacheManager cacheManager = new CacheManager();

    /**
     * TODO
     */
    protected boolean enableCaching = true;

    @Override
    public <T extends Component> T allocateComponent(Entity entity, SystemController systemController, Component.Id id) {
        final Component component = () -> id;
        register(entity, component, systemController);

        if (!isEnableCaching()) {
            return (T) component;
        }
        // cache to the [entity][system](component) layout
        cacheManager.register(entity, component, systemController);
        return (T) component;
    }

    @Override
    public void register(Entity entity, Component component, SystemController systemController) {
        super.register(entity, component, systemController);

        if (!isEnableCaching()) {
            return;
        }
        // cache to the [entity][system](component) layout
        cacheManager.register(entity, component, systemController);
    }

    @Override
    public void register(SystemController systemController, MemoryMap.EntityComponentMap memoryMap) {
        super.register(systemController, memoryMap);

        if (!isEnableCaching()) {
            return;
        }
        // cache to the [entity][system](component) layout is not supported by this method!
        memoryMap.forEach((number, component) -> {
            // 1) build a memory map
            MemoryMap.SystemComponentMap systemComponentMap;
            if (cacheManager.getMemoryMap().get(number) == null) {
                systemComponentMap = new MemoryMap.SystemComponentMap();
                cacheManager.getMemoryMap().put(number, systemComponentMap);
            } else {
                systemComponentMap = cacheManager.getMemoryMap().get(number);
            }
            // 2) copy data
            systemComponentMap.put(systemController.getId().getId(),
                    component);
        });
    }

    @Override
    public void updateEntityComponents(ComponentUpdater<I> updater, Entity entity, I input) {
        if (!isEnableCaching()) {
            super.updateEntityComponents(updater, entity, input);
            return;
        }
        Validator.validate(updater, Validator.Message.INVALID_ASSOCIATED_SYSTEM);
        Validator.validate(entity, Validator.Message.INVALID_ENTITY);
        // do a manipulation from the cache, constant omega notation, single CPU clock cycles
        // manipulate cache of the [entity][system](component) layout
        updater.update(cacheManager.getMemoryMap(entity), entity, this, input);
    }

    /**
     * Tests whether the caching is enabled.
     *
     * @return true if enabled, false otherwise.
     */
    public boolean isEnableCaching() {
        return enableCaching;
    }

    /**
     * Enables/disables the caching system.
     *
     * @param enableCaching true to enable caching.
     */
    public void setEnableCaching(boolean enableCaching) {
        this.enableCaching = enableCaching;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public CacheManager getCacheManager() {
        return cacheManager;
    }
}