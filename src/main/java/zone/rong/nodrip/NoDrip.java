package zone.rong.nodrip;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod("nodrip")
public class NoDrip {

    public NoDrip() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        Logger logger = LogManager.getLogger("NoDrip");
        logger.info("NoDrip is initializing...");
        try {
            // isRandomlyTicking
            Field isRandomlyTickingField = ObfuscationReflectionHelper.findField(BlockBehaviour.class, "f_60445_");
            isRandomlyTickingField.setAccessible(true);
            isRandomlyTickingField.setBoolean(Blocks.POINTED_DRIPSTONE, false);
            isRandomlyTickingField.setAccessible(false);
        } catch (Throwable t) {
            logger.error("Unable to setup NoDrip! Report to mod author!", t);
        }
    }

}
