package com.wc.cybermen.data;

import com.wc.cybermen.Cybermen;
import com.wc.cybermen.common.init.CBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLang extends LanguageProvider {

    public EnglishLang(DataGenerator gen) {
        super(gen, Cybermen.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(CBlocks.CONTROLLER_BLOCK.get(), "Controller");
    }
}
