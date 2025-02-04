/*
 * Copyright (c) 2018-2020 C4
 *
 * This file is part of Curios, a mod made for Minecraft.
 *
 * Curios is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Curios is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Curios.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.curios.api;

import java.util.Map;
import java.util.Optional;
import net.minecraft.world.entity.EntityType;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.api.type.util.IIconHelper;
import top.theillusivec4.curios.api.type.util.ISlotHelper;
import top.theillusivec4.curios.common.data.CuriosEntityManager;
import top.theillusivec4.curios.common.data.CuriosSlotManager;

public final class CuriosApi {

  public static final String MODID = "curios";

  private static IIconHelper iconHelper;
  private static ISlotHelper slotHelper;
  private static ICuriosHelper curiosHelper;

  public static IIconHelper getIconHelper() {
    return iconHelper;
  }

  public static ISlotHelper getSlotHelper() {
    return slotHelper;
  }

  public static ICuriosHelper getCuriosHelper() {
    return curiosHelper;
  }

  public static void setIconHelper(IIconHelper helper) {

    if (iconHelper == null) {
      iconHelper = helper;
    }
  }

  public static void setSlotHelper(ISlotHelper helper) {
    slotHelper = helper;
  }

  public static void setCuriosHelper(ICuriosHelper helper) {

    if (curiosHelper == null) {
      curiosHelper = helper;
    }
  }

  public static Optional<ISlotType> getSlot(String id) {
    return Optional.ofNullable(getSlots().get(id));
  }

  public static Map<String, ISlotType> getSlots() {
    return CuriosSlotManager.INSTANCE.getSlots();
  }

  /**
   * @return The slot types provided to player entities
   */
  public static Map<String, ISlotType> getPlayerSlots() {
    return getEntitySlots(EntityType.PLAYER);
  }

  /**
   * @return The slot types provided to the given entity type
   */
  public static Map<String, ISlotType> getEntitySlots(EntityType<?> type) {
    return CuriosEntityManager.INSTANCE.getEntitySlots(type);
  }
}
