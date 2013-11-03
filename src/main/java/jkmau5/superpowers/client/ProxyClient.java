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

package jkmau5.superpowers.client;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import jkmau5.superpowers.client.render.RenderEventHandler;
import jkmau5.superpowers.power.CleanupTickHandler;
import jkmau5.superpowers.power.PowerRegistry;
import jkmau5.superpowers.server.ProxyCommon;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient extends ProxyCommon {

    public static final Minecraft mc = Minecraft.getMinecraft();

    public void registerEvents() {
        super.registerEvents();

        TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT); //TODO: remove this one
        TickRegistry.registerScheduledTickHandler(CleanupTickHandler.instance, Side.CLIENT);
        TickRegistry.registerTickHandler(PowerRegistry.getInstance(), Side.SERVER);

        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
        MinecraftForge.EVENT_BUS.register(new RenderEventHandler());
    }
}
