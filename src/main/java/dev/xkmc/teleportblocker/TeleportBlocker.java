package dev.xkmc.teleportblocker;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TeleportBlocker.MODID)
public class TeleportBlocker {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "teleportblocker";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();

	public TeleportBlocker() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onTeleport(EntityTravelToDimensionEvent event) {
		if (!(event.getEntity() instanceof LivingEntity)) {
			event.getEntity().setPortalCooldown();
			event.setCanceled(true);
		}
	}

}
