/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.WorldGenRates;

/**
 * WorldGenElementiumOre
 *
 * Generates Elementium Ore in the world, randomizing metadata and adding clusters of
 * Elemental protector blocks around.
 *
 * @author Vazkii
 */
public class WorldGenElementiumOre extends WorldGenMinable {

	public static final WorldGenElementiumOre INSTANCE = new WorldGenElementiumOre();

	private WorldGenElementiumOre() {
		super(BlockIDs.elementiumOre, WorldGenRates.ELEMENTIUM_ORE_MAX_VEIN_SIZE);
	}

	@Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		if(par2Random == null)
			par2Random = new Random(); // Dirty fix for Mystcraft

        float var6 = par2Random.nextFloat() * (float)Math.PI;
        int numberOfBlocks = WorldGenRates.ELEMENTIUM_ORE_MAX_VEIN_SIZE;
        double var7 = par3 + 8 + MathHelper.sin(var6) * numberOfBlocks / 8.0F;
        double var9 = par3 + 8 - MathHelper.sin(var6) * numberOfBlocks / 8.0F;
        double var11 = par5 + 8 + MathHelper.cos(var6) * numberOfBlocks / 8.0F;
        double var13 = par5 + 8 - MathHelper.cos(var6) * numberOfBlocks / 8.0F;
        double var15 = par4 + par2Random.nextInt(3) - 2;
        double var17 = par4 + par2Random.nextInt(3) - 2;

        for (int var19 = 0; var19 <= numberOfBlocks; ++var19) {
            double var20 = var7 + (var9 - var7) * var19 / numberOfBlocks;
            double var22 = var15 + (var17 - var15) * var19 / numberOfBlocks;
            double var24 = var11 + (var13 - var11) * var19 / numberOfBlocks;
            double var26 = par2Random.nextDouble() * numberOfBlocks / 16.0D;
            double var28 = (MathHelper.sin(var19 * (float)Math.PI / numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (MathHelper.sin(var19 * (float)Math.PI / numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38) {
                double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);
                if (var39 * var39 < 1.0D) {
                    for (int var41 = var33; var41 <= var36; ++var41) {
                        double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);
                        if (var39 * var39 + var42 * var42 < 1.0D) {
                            for (int var44 = var34; var44 <= var37; ++var44) {
                                double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);
                                Block block = Block.blocksList[par1World.getBlockId(var38, var41, var44)];
                                int blockID = par2Random.nextInt(100) < WorldGenRates.ELEMENTIUM_ORE_GUARDIAN_CHANCE
                      			      ? BlockIDs.elementiumOreSpawner : BlockIDs.elementiumOre;
                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && block != null &&
                                		block.isGenMineableReplaceable(par1World, var38, var41, var44, Block.stone.blockID)) {
                                    par1World.setBlock(var38, var41, var44, blockID, par2Random.nextInt(16), 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
