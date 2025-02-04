package top.theillusivec4.curios.common.network.server.sync;

import java.util.function.Supplier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.common.data.CuriosEntityManager;

public class SPacketSyncData {

  private final ListTag data;

  public SPacketSyncData(ListTag data) {
    this.data = data;
  }

  public static void encode(SPacketSyncData msg, FriendlyByteBuf buf) {
    CompoundTag tag = new CompoundTag();
    tag.put("Data", msg.data);
    buf.writeNbt(tag);
  }

  public static SPacketSyncData decode(FriendlyByteBuf buf) {
    CompoundTag tag = buf.readNbt();

    if (tag != null) {
      return new SPacketSyncData(tag.getList("Data", Tag.TAG_COMPOUND));
    }
    return new SPacketSyncData(new ListTag());
  }

  public static void handle(SPacketSyncData msg, Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> CuriosEntityManager.applySyncPacket(msg.data));
    ctx.get().setPacketHandled(true);
  }
}
