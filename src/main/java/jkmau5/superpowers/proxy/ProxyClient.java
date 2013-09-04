package jkmau5.superpowers.proxy;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import jkmau5.superpowers.client.EventHandlerClient;
import jkmau5.superpowers.client.TickHandlerClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * TODO: Edit description
 *
 * @author jk-5
 */
public class ProxyClient implements IProxy {

    public void registerEvents() {
        TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);

        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
    }
}
