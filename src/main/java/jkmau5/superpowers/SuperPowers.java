/*
 * Copyright 2013 jk-5 and Lordmau5
 *
 * jk-5 and Lordmau5 License this file to you under the LGPL v3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License
 */

package jkmau5.superpowers;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import jkmau5.superpowers.config.ConfigFile;
import jkmau5.superpowers.server.ProxyCommon;
import jkmau5.superpowers.utils.SPLogger;
import jkmau5.superpowers.version.VersionChecker;
import lombok.Getter;

@Mod(modid = "SuperPowers", useMetadata = true, dependencies = "required-after:Forge@[9.11.1.942,)")
@NetworkMod(clientSideRequired = true)
public class SuperPowers {

    @Getter
    @Mod.Instance("SuperPowers")
    private static SuperPowers instance;

    @SidedProxy(modId = "SuperPowers", clientSide = "jkmau5.superpowers.client.ProxyClient", serverSide = "jkmau5.superpowers.server.ProxyCommon")
    public static ProxyCommon proxy;

    @Getter private ConfigFile config;
    @Getter private VersionChecker versionChecker;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.config = new ConfigFile(event.getSuggestedConfigurationFile()).setComment("Main SuperPowers config file");
        this.versionChecker = new VersionChecker();
        this.versionChecker.init();
        SPLogger.info("Starting up SuperPowers, version %s build %d", this.versionChecker.getCurrentVersion().version, this.versionChecker.getCurrentVersion().build);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerEvents();
        
        this.versionChecker.checkRemoteVersion();
    }
}
