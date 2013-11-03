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

package jkmau5.superpowers.power;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import jkmau5.superpowers.api.PlayerPowerState;
import jkmau5.superpowers.api.PowerAPI;
import jkmau5.superpowers.api.PowerBase;
import jkmau5.superpowers.power.powers.PowerSprint;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * No description given
 *
 * @author jk-5
 */
public class PowerRegistry implements ITickHandler, PowerAPI {

    @Getter
    private static final PowerRegistry instance = new PowerRegistry();

    private final Map<String, Class<? extends PowerBase>> powers = Maps.newHashMap();
    private final List<PlayerPowerState> playerStates = Lists.newArrayList();

    public PowerRegistry(){
        this.powers.put("SuperPowers:Sprint", PowerSprint.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends PowerBase> T createPower(String modid, String name, EntityPlayer player){
        try{
            T power = (T) this.powers.get(modid + ":" + name).newInstance();
            PlayerPowerState state = this.getPlayerPowerState(player);
            if(state == null) state = new PlayerPowerState(player);
            power.setPlayerState(state);
            return power;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void registerPower(String modid, String name, Class<? extends PowerBase> powerClass) {
        this.powers.put(modid + ":" + name, powerClass);
    }

    public void addPlayer(EntityPlayer player){
        this.playerStates.add(new PlayerPowerState(player));
    }

    public void removePlayer(EntityPlayer player){
        for(PlayerPowerState state : this.playerStates){
            if(state.getPlayer() == player){
                this.playerStates.remove(state);
            }
        }
    }

    public PlayerPowerState getPlayerPowerState(EntityPlayer player){
        for(PlayerPowerState state : this.playerStates){
            if(state.getPlayer() == player){
                return state;
            }
        }
        return null;
    }

    public void cleanup(){
        for(PlayerPowerState state : this.playerStates){
            if(state.getPlayer() == null){
                this.playerStates.remove(state);
            }
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        EntityPlayer player = (EntityPlayer) tickData[0];
        PlayerPowerState state = this.getPlayerPowerState(player);
        if(state == null) return;
        state.onPreTick(player.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        EntityPlayer player = (EntityPlayer) tickData[0];
        PlayerPowerState state = this.getPlayerPowerState(player);
        if(state == null) return;
        state.onPostTick(player.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "SuperPowers|PlayerTicker";
    }
}
