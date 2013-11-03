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

package jkmau5.superpowers.client.render;

import net.minecraft.client.gui.ScaledResolution;

public class HudManager {

    private static boolean hudEnabled = false;

    public static void renderOverlay(float partialTick, ScaledResolution resolution) {
        if(!HudManager.isHudEnabled()) return;
        /*glPushMatrix();
        glClear(GL_DEPTH_BUFFER_BIT);
        glEnable(GL_BLEND);
        glDisable(GL11.GL_TEXTURE_2D);
        glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glBegin(GL_QUADS);
        glColor4f(1f, 0.5f, 0f, 1f);
        glVertex2f(5, 5);
        glVertex2f(10, 5);
        glColor4f(0.5f, 1f, 0f, 1f);
        glVertex2f(10, 10);
        glVertex2f(5, 10);
        glEnd();
        glDisable(GL_BLEND);
        glEnable(GL11.GL_TEXTURE_2D);
        glPopMatrix();*/
    }

    public static boolean isHudEnabled() {
        return hudEnabled;
    }

    public static void setHudEnabled(boolean hudEnabled) {
        HudManager.hudEnabled = hudEnabled;
    }
}
