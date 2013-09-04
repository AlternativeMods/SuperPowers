package jkmau5.superpowers.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Author: Lordmau5
 * Date: 04.09.13
 * Time: 11:29
 * You are not allowed to change this code,
 * nor publish it without my permission.
 */
public class SPEventHandler {

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