package yeelp.sufferingfromsuccess.mixin;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("SufferingFromSuccess-MixinLoader")
@IFMLLoadingPlugin.SortingIndex(-5000)
public final class SFSMixinLoader implements IFMLLoadingPlugin {

	private static final String MIXIN_FILE = "mixin.sfs.json";
	
	public SFSMixinLoader() {
		MixinBootstrap.init();
		MixinExtrasBootstrap.init();
		Mixins.addConfiguration(MIXIN_FILE);
	}
	
	@Override
	public String[] getASMTransformerClass() {
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		return;
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
