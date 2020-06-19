package com.daposeidonguy.teamsmod.common.blocks;

import com.daposeidonguy.teamsmod.common.storage.StorageHelper;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTeamChest extends BlockEnderChest {

    protected BlockTeamChest() {
        super();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        InventoryEnderChest inventoryenderchest = StorageHelper.getTeamEnderChest(StorageHelper.getTeam(playerIn.getUniqueID()));
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (inventoryenderchest != null && tileentity instanceof TileEntityEnderChest) {
            if (worldIn.getBlockState(pos.up()).doesSideBlockChestOpening(worldIn, pos.up(), EnumFacing.DOWN)) {
                return true;
            } else if (worldIn.isRemote) {
                return true;
            } else {
                inventoryenderchest.setChestTileEntity((TileEntityEnderChest) tileentity);
                playerIn.displayGUIChest(inventoryenderchest);
                playerIn.addStat(StatList.ENDERCHEST_OPENED);
                return true;
            }
        } else {
            return true;
        }
    }
}
