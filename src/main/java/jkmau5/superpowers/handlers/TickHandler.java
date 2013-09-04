package handlers;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.EnumSet;

/**
 * Author: Lordmau5
 * Date: 03.09.13
 * Time: 22:46
 * You are not allowed to change this code,
 * nor publish it without my permission.
 */
public class TickHandler implements ITickHandler {

    EntityPlayer player;
    int tickCount = 0;

    double runSpeed = 0;
    double oldMotionX = 0;
    double oldMotionZ = 0;

    double jumpModifier = 0;
    boolean shortPress = false;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        if(tickCount++ >= 1)
            tickCount = 0;
        else
            return;

        if(player == null)
            player = Minecraft.getMinecraft().thePlayer;


        superSprint();
        superJump();
    }

    public void superSprint() {
        if(player.isSprinting()) {
            runSpeed += 0.075;
            if(runSpeed > 3)
                runSpeed = 3;
        }
        else {
            runSpeed = 0.98D;
        }

        player.motionX *= runSpeed;
        player.motionZ *= runSpeed;

        System.out.println(player.motionX + " _ " + player.motionZ);

        if(player.motionX > 3 || player.motionX < -3)
            player.motionX = oldMotionX;
        if(player.motionZ > 3 || player.motionZ < -3)
            player.motionZ = oldMotionZ;

        oldMotionX = player.motionX;
        oldMotionZ = player.motionZ;
    }

    public void superJump() {
        if(Minecraft.getMinecraft().gameSettings.keyBindJump.pressed) {
            shortPress = true;

            jumpModifier += 0.075;
            if(jumpModifier > 2.25)
                jumpModifier = 2.25;
        }
        else {
            if(shortPress == true && jumpModifier < 0.5)
                jumpModifier = 0.5;
            shortPress = false;
            player.motionY += jumpModifier;
            jumpModifier = 0;
        }
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "superPowersTHandler";
    }
}