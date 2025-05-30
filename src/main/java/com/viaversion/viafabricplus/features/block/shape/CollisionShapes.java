/*
 * This file is part of ViaFabricPlus - https://github.com/ViaVersion/ViaFabricPlus
 * Copyright (C) 2021-2025 the original authors
 *                         - FlorianMichael/EnZaXD <florian.michael07@gmail.com>
 *                         - RK_01/RaphiMC
 * Copyright (C) 2023-2025 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.viaversion.viafabricplus.features.block.shape;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonHeadBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;

public final class CollisionShapes {

    public static void reloadBlockShapes() {
        for (Block block : Registries.BLOCK) {
            if (block instanceof AnvilBlock || block instanceof BedBlock || block instanceof BrewingStandBlock
                || block instanceof CarpetBlock || block instanceof CauldronBlock || block instanceof ChestBlock
                || block instanceof EnderChestBlock || block instanceof EndPortalBlock || block instanceof EndPortalFrameBlock
                || block instanceof FarmlandBlock || block instanceof FenceBlock || block instanceof FenceGateBlock
                || block instanceof HopperBlock || block instanceof LadderBlock || block instanceof LeavesBlock
                || block instanceof LilyPadBlock || block instanceof PaneBlock || block instanceof PistonBlock
                || block instanceof PistonHeadBlock || block instanceof SnowBlock || block instanceof WallBlock
                || block instanceof CropBlock || block instanceof FlowerbedBlock
            ) {
                for (BlockState state : block.getStateManager().getStates()) {
                    state.initShapeCache();
                }
            }
        }
    }

}
