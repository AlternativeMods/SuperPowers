package jkmau5.superpowers.client;

import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

/**
 * TODO: Edit description
 *
 * @author jk-5
 */
public class HudRenderer {

    public static void renderOverlay(float partialTick, ScaledResolution resolution) {
        glPushMatrix();
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
        glPopMatrix();
    }
}
