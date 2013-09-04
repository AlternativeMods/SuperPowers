package jkmau5.superpowers.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * TODO: Edit description
 *
 * @author jk-5
 */
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

    @ForgeSubscribe
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            HudRenderer.renderOverlay(event.partialTicks, event.resolution);
        }
    }
}
