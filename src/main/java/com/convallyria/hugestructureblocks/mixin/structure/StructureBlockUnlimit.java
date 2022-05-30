package com.convallyria.hugestructureblocks.mixin.structure;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = StructureBlockEntity.class, priority = 999)
public class StructureBlockUnlimit {

    @ModifyConstant(method = "load", constant = @Constant(intValue = 48), require = 0)
    public int readNbtUpper(int value) {
        return 4096;
    }

    @ModifyConstant(method = "load", constant = @Constant(intValue = -48), require = 0)
    public int readNbtLower(int value) {
        return -4096;
    }

    @ModifyConstant(method = "detectSize", constant = @Constant(intValue = 80), require = 0)
    public int detectSize(int value) {
        return 2048;
    }

    @ModifyArg(method = "saveStructure(Z)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate;fillFromWorld(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Vec3i;ZLnet/minecraft/world/level/block/Block;)V"), index = 4)
    public Block excludeBlock(Block block) {
        return Blocks.AIR;
    }
}
