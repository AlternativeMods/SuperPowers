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

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

import java.util.EnumSet;

/**
 * No description given
 *
 * @author jk-5
 */
public class CleanupTickHandler implements IScheduledTickHandler {

    public static final CleanupTickHandler instance = new CleanupTickHandler();

    @Override
    public int nextTickSpacing() {
        return 1200;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        PowerRegistry.getInstance().cleanup();
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT, TickType.SERVER);
    }

    @Override
    public String getLabel() {
        return "SuperPowers|PlayerCleanup";
    }
}
