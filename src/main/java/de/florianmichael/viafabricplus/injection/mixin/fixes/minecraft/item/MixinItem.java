/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD
 * Copyright (C) 2023      RK_01/RaphiMC and contributors
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

package de.florianmichael.viafabricplus.injection.mixin.fixes.minecraft.item;

import de.florianmichael.viafabricplus.protocolhack.ProtocolHack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.raphimc.vialoader.util.VersionEnum;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class MixinItem {

    @Shadow
    @Final
    private int maxDamage;

    @Shadow
    public abstract boolean isFood();

    @Redirect(method = {"getMaxDamage", "isDamageable", "getItemBarStep", "getItemBarColor"}, at = @At(value = "FIELD", target = "Lnet/minecraft/item/Item;maxDamage:I"))
    private int changeCrossbowDamage(Item instance) {
        if (instance instanceof CrossbowItem && ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.r1_17_1)) {
            return 326;
        }
        return maxDamage;
    }

    @Inject(method = "getMaxCount", at = @At("HEAD"), cancellable = true)
    private void dontStackFood(CallbackInfoReturnable<Integer> cir) {
        if (this.isFood() && ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.b1_7tob1_7_3)) {
            cir.setReturnValue(1);
        }
    }

    @Redirect(method = {"use", "finishUsing", "getUseAction", "getMaxUseTime"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;isFood()Z"))
    private boolean makeFoodInstantConsumable(Item instance) {
        if (ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.b1_7tob1_7_3)) {
            return false;
        }
        return instance.isFood();
    }

}