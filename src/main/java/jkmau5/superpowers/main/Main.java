package main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import handlers.SPEventHandler;
import handlers.TickHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Author: Lordmau5
 * Date: 03.09.13
 * Time: 22:44
 * You are not allowed to change this code,
 * nor publish it without my permission.
 */
@Mod(modid="SuperPowers", name="Super Powers", version="ALPHA 0.1")
public class Main {


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        initClient();
    }

    private void initClient() {
        if(FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
            return;
        TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(new SPEventHandler());
    }
}