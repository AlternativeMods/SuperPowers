package jkmau5.superpowers.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.world.World;

/**
 * Utilities for a easy way of getting the side that the code is running on
 *
 * @author jk-5
 */
public class SideHelper {

    public static Side getSide() {
        return FMLCommonHandler.instance().getSide();
    }

    public static Side getEffectiveSide() {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    public static boolean isClient(World world) {
        return world.isRemote;
    }

    public static boolean isServer(World world) {
        return !world.isRemote;
    }

    public static boolean isClient() {
        return getSide() == Side.CLIENT;
    }

    public static boolean isServer() {
        return getSide() == Side.SERVER;
    }
}
