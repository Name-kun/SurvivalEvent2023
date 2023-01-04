package xyz.masa3mc.survivalevent.reward;

import static java.util.UUID.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.tr7zw.nbtapi.NBTItem;

public class RewardLists {

	public static ItemStack habutae() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l羽二〇餅");
		meta.setLore(Arrays.asList("§7〇井県のお土産品として有名なお餅。", "§7なんで運営陣に福〇県民がいないのに", "§7景品に追加されたのかは謎。"));
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack angel() {
		ItemStack item = new ItemStack(Material.FEATHER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§lてんしのおもち");
		meta.setLore(Arrays.asList("§7手に持っているだけで", "§7体が羽根のように軽くなる。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack stickMochi() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l棒餅");
		meta.setLore(Collections.singletonList("§7糸餅と合わせて釣り竿を作ると...？"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack mochiIngot() {
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l餅インゴット");
		meta.setLore(Arrays.asList("§7リアルでも七草がゆを", "§7食べる頃には生成されている。"));
		meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
		meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack boneMochi() {
		ItemStack item = new ItemStack(Material.BONE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l骨餅");
		meta.setLore(Arrays.asList("§7なぜか舐めさせるだけで狼を", "§7手懐けることができる。", "§a(使用しても無くならない)"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack stringMochi() {
		ItemStack item = new ItemStack(Material.STRING);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l糸餅");
		meta.setLore(Collections.singletonList("§7棒餅と合わせて釣り竿を釣ると...？"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack mochiPotion() {
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta) item.getItemMeta();
		meta.setColor(Color.WHITE);
		meta.setDisplayName("§f§l餅ポーション");
		meta.setLore(Arrays.asList("§7お餅で作ったポーション。", "§7飲むと体が伸びたように感じる。"));
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack mochiFish() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString("344cdc0d-757c-4162-87cd-2fd7e58ad914"));
		meta.setOwningPlayer(offlinePlayer);
		meta.setDisplayName("§e§l餅を喉に詰まらせた魚");
		meta.setLore(Arrays.asList("§7餅を喉に詰まらせて死んだ", "§7かわいそうな魚類の頭。", "§7装備すると呪われる。"));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(randomUUID(), "attr", -1.0,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setString("event", "mochiFish");
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack saltMochi() {
		ItemStack item = new ItemStack(Material.SUGAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l塩餅");
		meta.setLore(Arrays.asList("§7塩でできたお餅。", "§7近くになめくじがいる時に", "§7手に持つと...？"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack liquidMochi() {
		ItemStack item = new ItemStack(Material.MILK_BUCKET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l無限液体餅");
		meta.setLore(Arrays.asList("§7なぜか無限にバケツに入っている", "§7液体状のお餅。", "§7飲むと付いているエフェクトを全消去する。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack shiningMochi() {
		ItemStack item = new ItemStack(Material.RAW_GOLD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l光り輝くお餅");
		meta.setLore(Arrays.asList("§7光り輝く金ピカのお餅。", "§7オフハンドに持っている間", "§7発光、暗視を付与。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack mochiHoe() {
		ItemStack item = new ItemStack(Material.IRON_HOE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§l餅のクワ");
		meta.setLore(Collections.singletonList("§7餅は鉄より堅い。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack happyMochi() {
		ItemStack item = new ItemStack(Material.QUARTZ);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§f§lHAPPY☆NEW☆YEAR☆MOCHI☆IN2023");
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(randomUUID(), "attr", 0.05,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(randomUUID(), "attr", 5,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(randomUUID(), "attr", 3,
				AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack happyRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§l謹賀新年§f☆§a§lHappyFishingRod!!!");
		meta.setLore(Arrays.asList("餅だけでできた釣り竿。", "やたら魚が釣れるらしい。"));
		meta.addEnchant(Enchantment.LURE, 20, true);
		meta.addEnchant(Enchantment.DURABILITY, 2, true);
		meta.addEnchant(Enchantment.LUCK, 3, true);
		meta.addEnchant(Enchantment.MENDING, 1, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack mugenLavaBuckets() {
		ItemStack item = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§l無限溶岩バケツ");
		meta.setLore(Arrays.asList("「無限液体餅の要領で溶岩を入れたらできちゃったよ」", "そう言った餅屋のじいちゃんは錬金術師にでも", "なった方がいいんじゃないか？",
				"§4※燃料として使用すると§e消滅§4します。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack spyGlass() {
		ItemStack item = new ItemStack(Material.SPYGLASS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§lStalker's SpyGlass");
		meta.setLore(Arrays.asList("これで覗かれると寒気がする。","クリックで半径10m内のMOBに移動速度低下3を10秒付与する。", "※警視庁により検閲済みの為、対人性能は削除されている。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack tankenSet() {
		ItemStack item = new ItemStack(Material.RECOVERY_COMPASS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§lたんけんセット");
		meta.setLore(Arrays.asList("某ポ〇モンで見たことのある便利ツール。", "スニーク+右クリックで地下の空間へ", "右クリックで地上の空間へ移動できる。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack mugenSponge(int amount) {
		ItemStack item = new ItemStack(Material.SPONGE);
		item.setAmount(amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§l無限スポンジ");
		meta.setLore(Arrays.asList("製造の過程で偶然速乾性に優れたスポンジ。", "あまりに乾きすぎるので水分補給は忘れずに。","名前の通り無限にスポンジが使える。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack mugentsukitate() {
		ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§l無限つきたてお餅");
		meta.setLore(Arrays.asList("あれだけイベントで見たらもう食べなくても...", "エリトラ飛行時オフハンドに持つと", "満腹度回復が付与される。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack fanElytra() {
		ItemStack item = new ItemStack(Material.ELYTRA);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§e§lクーラーファン付きエリトラ");
		meta.setLore(Arrays.asList("工事現場のおっちゃんがよく着ている", "空調服の改造品 v1.1.2-SNAPSHOT", "装備時、炎ダメージを無効化する。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack superPickaxe6() {
		ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§b§lSuperPickaxe(5x5)");
		meta.setLore(Arrays.asList("このピッケルは5x5で掘れます。"));
		meta.addEnchant(Enchantment.DIG_SPEED, 6, true);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.setInteger("Range", 2);
		nbtItem.applyNBT(item);
		return item;
	}

	public static ItemStack boots2023() {
		ItemStack item = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6韋駄天の靴");
		meta.setLore(Arrays.asList("「イベント完走お疲れ様です！」", "--NameKun", "2023/1/1 年末ジャンボ宝釣り--","装備時、移動速度上昇3・跳躍力上昇4が付与される。"));
		meta.addEnchant(Enchantment.MENDING, 0, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		NBTItem nbtItem = new NBTItem(item);
		nbtItem.setBoolean("年末ジャンボ2022", true);
		nbtItem.applyNBT(item);
		return item;
	}

}
