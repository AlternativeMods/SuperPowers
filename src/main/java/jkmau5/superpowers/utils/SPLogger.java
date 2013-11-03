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

package jkmau5.superpowers.utils;

import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;

public class SPLogger {

    private static final String name = String.format("SuperPowers-%s", SideHelper.getEffectiveSide().name());

    public static void finest(String message, Object... data) {
        FMLLog.log(name, Level.FINEST, message, data);
    }

    public static void finer(String message, Object... data) {
        FMLLog.log(name, Level.FINER, message, data);
    }

    public static void fine(String message, Object... data) {
        FMLLog.log(name, Level.FINE, message, data);
    }

    public static void info(String message, Object... data) {
        FMLLog.log(name, Level.FINE, message, data);
    }

    public static void warn(String message, Object... data) {
        FMLLog.log(name, Level.WARNING, message, data);
    }

    public static void severe(String message, Object... data) {
        FMLLog.log(name, Level.SEVERE, message, data);
    }

    public static void warn(String message, Throwable t, Object... data) {
        FMLLog.log(name, Level.WARNING, t, message, data);
    }

    public static void severe(String message, Throwable t, Object... data) {
        FMLLog.log(name, Level.SEVERE, t, message, data);
    }
}
