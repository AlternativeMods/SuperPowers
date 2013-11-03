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

package jkmau5.superpowers.api;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * No description given
 *
 * @author jk-5
 */
public class PlayerPowerState {

    private final WeakReference<EntityPlayer> player;
    private final List<PowerBase> activePowers = Lists.newArrayList();

    public PlayerPowerState(EntityPlayer player){
        this.player = new WeakReference<EntityPlayer>(player);
    }

    public EntityPlayer getPlayer(){
        return this.player.get();
    }

    public void onPreTick(Side side){
        if(this.getPlayer() == null) return; //WeakReference is GC'd. Ignore the call
        for(PowerBase power : this.activePowers){
            power.beforePlayerTick(side, this.getPlayer());
        }
    }

    public void onPostTick(Side side){
        if(this.getPlayer() == null) return; //WeakReference is GC'd. Ignore the call
        for(PowerBase power : this.activePowers){
            power.afterPlayerTick(side, this.getPlayer());
        }
    }
}
