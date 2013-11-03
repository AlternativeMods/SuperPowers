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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandlerClient {

    @ForgeSubscribe
    public void onPlayerJump(LivingEvent.LivingJumpEvent j) {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
            return;

        //System.out.println("MEEP");

        if (j.entity instanceof EntityPlayer && j.entity == Minecraft.getMinecraft().thePlayer)
            ((EntityPlayer) j.entity).motionY = 0;
        //((EntityPlayer) j.entity).setJumping(false);
        //j.setCanceled(true);
    }

    @ForgeSubscribe
    public void onPlayerFallDamage(LivingAttackEvent a) {
        if (a.source != DamageSource.fall)
            return;
        if (a.entity instanceof EntityPlayer)
            a.setCanceled(true);
    }
}
