package com.nhsoft.poslib.call.impl;

import android.database.Cursor;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ItemBar;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.ManagementTemplateDetail;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.model.ItemSequenceBean;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.ItemBarDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDetailDao;
import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.PosItemKitDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.TimeUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/15 11:29 AM
 * 此类用于：用于操作商品的服务操作
 */
public class PosItemImpl {

    private static PosItemImpl instance;

    public static PosItemImpl getInstance() {
        if (instance == null) {
            instance = new PosItemImpl();
        }
        return instance;
    }

    /**
     * 保存PosItem数据
     * PosItem 集合
     *
     * @param result
     * @return
     */
    public static boolean savePosItemList(final List<PosItem> result) {
        if (result.size() == 0) {
            return true;
        }
        final PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        final ItemBarDao itemBarDao = DaoManager.getInstance().getDaoSession().getItemBarDao();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        final PosItemKitDao posItemKitDao = DaoManager.getInstance().getDaoSession().getPosItemKitDao();

        return MatterUtils.doMatter(pos_itemDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    PosItem posItem = result.get(i);

                    if(posItem.getPos_Images() != null && posItem.getPos_Images().size() >0 ){
                        posItem.setPos_images_json(new Gson().toJson(posItem.getPos_Images()));
                    }
                    pos_itemDao.insertOrReplaceInTx(posItem);
                    List<ItemBar> item_bar_list = posItem.getItem_bar_list();
                    if (item_bar_list != null && item_bar_list.size() > 0) {
                        List<ItemBar> list = itemBarDao.queryBuilder().where(ItemBarDao.Properties.Item_num.eq(posItem.getItem_num())).build().list();
                        if (list != null && list.size() > 0) {
                            itemBarDao.deleteInTx(list);
                        }
                        itemBarDao.insertOrReplaceInTx(item_bar_list);
                    }
                    List<PosItemGrade> pos_item_grade_list = posItem.getPos_item_grade_list();
                    if (pos_item_grade_list != null && pos_item_grade_list.size() > 0) {
                        List<PosItemGrade> list = posItemGradeDao.queryBuilder().where(PosItemGradeDao.Properties.Item_num.eq(posItem.getItem_num())).build().list();
                        if (list != null && list.size() > 0) {
                            posItemGradeDao.deleteInTx(list);
                        }

                        posItemGradeDao.insertOrReplaceInTx(pos_item_grade_list);
                    }
                    List<PosItemKit> pos_item_kit_list = posItem.getPos_item_kit_list();
                    if (pos_item_grade_list != null && pos_item_kit_list.size() > 0) {
                        List<PosItemKit> list = posItemKitDao.queryBuilder().where(PosItemKitDao.Properties.Item_num.eq(posItem.getItem_num())).build().list();
                        if (list != null && list.size() > 0) {
                            posItemKitDao.deleteInTx(list);
                        }
                        posItemKitDao.insertOrReplaceInTx(pos_item_kit_list);
                    }
                }
            }
        });
    }


    /**
     * 保存PosItem数据
     * PosItem 集合
     *
     * @param result
     * @return
     */
    public static boolean saveLocalChangePosItemList(final List<PosItem> result) {
        if (result.size() == 0) {
            return true;
        }
        final PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        return MatterUtils.doMatter(pos_itemDao, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < result.size(); i++) {
                    PosItem posItem = result.get(i);
                    pos_itemDao.insertOrReplaceInTx(posItem);
                }
            }
        });
    }


    public static List<PosItem> getTerminalPosItemByCode(String categoryCode, int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItem> posItems;
        if (branchNum != 0) {
            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()) {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and branch_sale_cease_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and item_sale_cease_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }
        } else {
            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()) {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and branch_sale_cease_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and item_sale_cease_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
            }
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }


    public static List<PosItem> getPosItemByCode(String categoryCode, int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItem> posItems;
        if (branchNum != 0) {
            if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and branch_sale_cease_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and item_sale_cease_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }
        } else {
            if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and branch_sale_cease_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);

            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and item_sale_cease_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);

            }
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        pos_itemDao.detachAll();
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }


    /**
     * 根据item_num查询对应的分级商品
     * @param item_num
     * @return
     */
    public List<PosItemGrade> getAllGradeByItemNum(long item_num){
        List<PosItemGrade> posItemGrades = DaoManager.getInstance().getDaoSession().getPosItemGradeDao().queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(item_num));
        return posItemGrades;
    }

    public static List<PosItem> getAllPosItemByCode(String categoryCode, int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItem> posItems;
        if (branchNum != 0) {
            posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
        } else {
            posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        pos_itemDao.detachAll();
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }

    public static List<PosItem> getAllPosItem( int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItem> posItems;
        if (branchNum != 0) {
            posItems = pos_itemDao.queryRaw("where branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", String.valueOf(branchNum));
        } else {
            posItems = pos_itemDao.queryRaw("where item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num");
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        pos_itemDao.detachAll();
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }

    public static List<PosItem> getAllShoWPosItemAndSonPosItemByCode(String categoryCode, int branchNum) {
        List<PosItem> posItemList=new ArrayList<>();
        List<ItemCategory> itemCategoryList= ItemCategoryImpl.getInstance().getAllSecondSonCategoryList(categoryCode);
        if (itemCategoryList!=null&&itemCategoryList.size()>0){
            for (ItemCategory itemCategory:itemCategoryList){
                posItemList.addAll(getAllShoWPosItemByCode(itemCategory.getCategory_code(),branchNum));
            }
        }
        return posItemList;
    }

    public static List<PosItem> getAllShoWPosItemByCode(String categoryCode, int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        ManagementTemplateDetailDao managementTemplateDetailDao = DaoManager.getInstance().getDaoSession().getManagementTemplateDetailDao();
        List<ManagementTemplateDetail> managementTemplateDetails = ManagementTemplateImpl.getTemplateDetails(LibConfig.activeBranch.getManagement_template_num());
        List<PosItem> posItems;
        if (branchNum != 0) {
            if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and branch_sale_cease_flag != 1 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and (item_sale_cease_flag == 0 or item_sale_cease_flag is null) and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
            }
        } else {
            if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and branch_sale_cease_flag != 1 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
            }else {
                posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0  and (item_sale_cease_flag == 0 or item_sale_cease_flag is null)  and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
            }
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (managementTemplateDetails != null && managementTemplateDetails.size() > 0) {
                if (managementTemplateDetailDao.queryBuilder().where(ManagementTemplateDetailDao.Properties.Item_num.eq(posItem.getItem_num())).build().list().size() == 0)
                    continue;
            }

            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        pos_itemDao.detachAll();
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }


    /**
     * 获取调价单的全部商品
     * @param categoryCode
     * @param branchNum
     * @return
     */
    public static List<PosItem> getAllAdjustPosItemAndSonPosItemByCode(String categoryCode, int branchNum) {
        List<PosItem> posItemList=new ArrayList<>();
        List<ItemCategory> itemCategoryList= ItemCategoryImpl.getInstance().getAllSecondSonCategoryList(categoryCode);
        if (itemCategoryList!=null&&itemCategoryList.size()>0){
            for (ItemCategory itemCategory:itemCategoryList){
                posItemList.addAll(getAllAdjustPosItemByCode(itemCategory.getCategory_code(),branchNum));
            }
        }
        return posItemList;
    }


    public static List<PosItem> getAllAdjustPosItemByCode(String categoryCode, int branchNum) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        ManagementTemplateDetailDao managementTemplateDetailDao = DaoManager.getInstance().getDaoSession().getManagementTemplateDetailDao();
        List<ManagementTemplateDetail> managementTemplateDetails = ManagementTemplateImpl.getTemplateDetails(LibConfig.activeBranch.getManagement_template_num());
        List<PosItem> posItems;

        if (branchNum != 0) {
            posItems = pos_itemDao.queryRaw("where item_category_code=? and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9 order by item_sequence ,item_num", categoryCode, String.valueOf(branchNum));
        } else {
            posItems = pos_itemDao.queryRaw("where item_category_code=? and item_del_tag = 0 and item_eliminative_flag = 0 and item_type != 11 and item_type != 9  order by item_sequence ,item_num", categoryCode);
        }

        List<PosItem> newPosItemList = new ArrayList<>();
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        for (PosItem posItem : posItems) {
            if (managementTemplateDetails != null && managementTemplateDetails.size() > 0) {
                if (managementTemplateDetailDao.queryBuilder().where(ManagementTemplateDetailDao.Properties.Item_num.eq(posItem.getItem_num())).build().list().size() == 0)
                    continue;
            }

            if (posItem.getItem_type() == 10) {
                List<PosItemGrade> posItemGrades = posItemGradeDao.queryRaw("where item_num=? ", String.valueOf(posItem.getItem_num()));
                if (posItemGrades == null || posItemGrades.size() == 0) continue;
                posItem.setPos_item_grade_list(posItemGrades);
            }
            newPosItemList.add(posItem);
        }
        pos_itemDao.detachAll();
        return newPosItemList;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }


    /**
     * 商品排序
     *
     * @param list
     * @return
     */
    public static boolean resetPosItemSequence(final List<ItemSequenceBean> list) {
        final PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();

        return MatterUtils.doMatter(pos_itemDao, new Runnable() {
            @Override
            public void run() {
                PosItem load;
                for (ItemSequenceBean itemSequenceBean : list) {
                    load = pos_itemDao.load(itemSequenceBean.getItemNum());
                    if (load != null) {
                        load.setItem_sequence((int) itemSequenceBean.getItemSequence());
                        pos_itemDao.save(load);
                    }
                }
            }
        });
    }

    /**
     * 通过成分商品的kit_item_num 来找 item_num
     *
     * @param kitNum
     * @return
     */
    public List<PosItem> getPosItemByKitNum(long kitNum) {
        List<PosItem> posItems = new ArrayList<>();
        PosItemKitDao posItemKitDao = DaoManager.getInstance().getDaoSession().getPosItemKitDao();
        PosItemDao posItemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        List<PosItemKit> kitlist = posItemKitDao.queryBuilder().where(PosItemKitDao.Properties.Kit_item_num.eq(kitNum)).build().list();
        if (kitlist != null && kitlist.size() > 0) {
            for (PosItemKit posItemKit : kitlist) {
                PosItem posItem = posItemDao.load(posItemKit.getItem_num());
                if (posItem != null && posItem.getItem_type() != 11 && !posItem.getItem_eliminative_flag()) {
                    posItems.add(posItem);
                }
            }
        }
        return posItems;
    }


    /**
     * 通过成分商品的kit_item_num 来找 item_num
     *
     * @return
     */
    public List<PosItemKit> getGroupPosItemByItemNum(long itemNum) {

        PosItemKitDao posItemKitDao = DaoManager.getInstance().getDaoSession().getPosItemKitDao();
        List<PosItemKit> kitlist = posItemKitDao.queryBuilder().where(PosItemKitDao.Properties.Item_num.eq(itemNum)).build().list();
        return kitlist;
    }


    public PosItem getPosItemByKey(long item_num) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        PosItem posItem = pos_itemDao.load(item_num);
        pos_itemDao.detachAll();
        return posItem;
//        return pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_category_code.eq(categoryCode)).list();
    }

    public PosItemGrade getPosItemGradeByKey(int item_grade_num,long item_num) {
        PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        PosItemGrade posItemGrade = posItemGradeDao.queryBuilder().where(PosItemGradeDao.Properties.Item_grade_num.eq(item_grade_num),PosItemGradeDao.Properties.Item_num.eq(item_num)).build().unique();
        posItemGradeDao.detachAll();
        return posItemGrade;

    }

    public PosItem getPosItemByKey(String name) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        PosItem posItem = pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_name.eq(name)).limit(1).offset(0).unique();
        return posItem;
    }

    public PosItem getPosItemByNmu(String id) {
        //and branch_num=?  and item_del_tag = 0 and item_eliminative_flag = 0 and branch_sale_cease_flag = 0
        // and item_type != 11 and item_type != 9 order by item_sequence
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
//        PosItem posItem = pos_itemDao.queryBuilder().where(
//                PosItemDao.Properties.Item_num.eq(id)
//                ,PosItemDao.Properties.Branch_num.eq(LibConfig.activeShiftTable.getBranch_num()),
//                PosItemDao.Properties.Item_del_tag.eq(0),
//                PosItemDao.Properties.Item_eliminative_flag.eq(0),
//                PosItemDao.Properties.Branch_sale_cease_flag.eq(0),
//                PosItemDao.Properties.Item_type.notEq(11),
//                PosItemDao.Properties.Item_type.notEq(9)
//        ).limit(1).offset(0).unique();

        PosItem posItem1 = pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_num.eq(id)).limit(1).offset(0).unique();
        if (posItem1 == null) {
            return null;
        }
        if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
            if ((posItem1.getBranch_num() == LibConfig.activeShiftTable.getBranch_num() || posItem1.getBranch_num() == 0) &&
                    !posItem1.getItem_del_tag() &&
                    !posItem1.getItem_eliminative_flag() &&
                    !posItem1.getBranch_sale_cease_flag() &&
                    posItem1.getItem_type() != 9 &&
                    posItem1.getItem_type() != 11) {
                return posItem1;
            }
        }else {
            if ((posItem1.getBranch_num() == LibConfig.activeShiftTable.getBranch_num() || posItem1.getBranch_num() == 0) &&
                    !posItem1.getItem_del_tag() &&
                    !posItem1.getItem_eliminative_flag() &&
                    !posItem1.getItem_sale_cease_flag() &&
                    posItem1.getItem_type() != 9 &&
                    posItem1.getItem_type() != 11) {
                return posItem1;
            }
        }
        return null;
    }

    public boolean goodsCanDiscount(long item_num, int item_grade_num) {
        if (item_grade_num == 0) {
            PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
            PosItem posItem = pos_itemDao.load(item_num);
            if (posItem != null) {
                return posItem.getItem_discounted();
            }
        } else {
            PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
            PosItemGrade posItemGrade = posItemGradeDao.queryBuilder().where(PosItemGradeDao.Properties.Item_num.eq(item_num), PosItemGradeDao.Properties.Item_grade_num.eq(item_grade_num)).build().unique();
            if (posItemGrade != null) {
                return posItemGrade.getItem_grade_discounted();
            }
        }
        return false;
    }

    /**
     * 根据商品条码 查询商品
     *
     * @param itemBarCode
     * @return
     */
    public List<PosItem> getPosItemByItemBarCode(int branchNum, String itemBarCode) {
        List<PosItem> posItems = new ArrayList<>();
        ItemBarDao itemBarDao = DaoManager.getInstance().getDaoSession().getItemBarDao();
        List<ItemBar> itemBars = itemBarDao.queryBuilder().where(ItemBarDao.Properties.Item_bar_code.eq(itemBarCode)).list();
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();

        QueryBuilder<PosItem> queryBuilder = pos_itemDao.queryBuilder();
        queryBuilder.join(ItemBar.class, ItemBarDao.Properties.Item_num)
                .where(ItemBarDao.Properties.Item_bar_code.eq(itemBarCode));

            if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
                posItems = queryBuilder.where(PosItemDao.Properties.Item_del_tag.eq(false), PosItemDao.Properties.Item_eliminative_flag.eq(false)).list();
            }else {
                posItems = queryBuilder.where(PosItemDao.Properties.Item_del_tag.notEq(true), PosItemDao.Properties.Item_eliminative_flag.eq(false)).list();

//                posItems = new ArrayList<>();
//                if(getPosItems != null){
//                    for (PosItem posItem : getPosItems){
//                        if(posItem.getItem_sale_cease_flag() == null || !posItem.getItem_sale_cease_flag()){
//                            posItems.add(posItem);
//                        }
//                    }
//                }
            }


//
//        ItemBar itemBar=itemBarDao.queryBuilder().where(ItemBarDao.Properties.Item_bar_code.eq(itemBarCode)).limit(1).offset(0).unique();
//        if (itemBar==null){
//            return posItems;
//        }
//         posItems = pos_itemDao.queryBuilder().where(
//                PosItemDao.Properties.Item_num.eq( itemBar.getItem_num())
//                ,PosItemDao.Properties.Branch_num.eq(branchNum)).list();
        pos_itemDao.detachAll();

        if(itemBars != null && itemBars.size() > 0){
            for (PosItem posItem : posItems){

                for (ItemBar itemBar : itemBars){
                    if(posItem.getItem_num().equals(itemBar.getItem_num())){
                        posItem.setItemBar(itemBar);
                    }
                }
            }
        }
        return posItems;
    }

    /**
     * 根据 store_item_pinyin 模糊查询
     *
     * @param storeItemPinyin
     * @param systemBookCode
     * @param branchNum
     * @return
     */
    public List<PosItem> getPosItemByItemNum(String systemBookCode, int branchNum, String storeItemPinyin) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        List<PosItem> posItems ;
        List<PosItemGrade> posItemGrades ;
        if(LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived()){
            posItems = pos_itemDao.queryBuilder().where(
                    PosItemDao.Properties.Store_item_pinyin.like("%" + storeItemPinyin + "%"), PosItemDao.Properties.Item_type.notEq(9), PosItemDao.Properties.Item_type.notEq(11),PosItemDao.Properties.Item_type.notEq(10),
                    PosItemDao.Properties.Item_del_tag.eq(false), PosItemDao.Properties.Branch_sale_cease_flag.eq(false), PosItemDao.Properties.Item_eliminative_flag.eq(false)
            ).list();

            posItemGrades = posItemGradeDao.queryBuilder().where(
                    PosItemGradeDao.Properties.Item_grade_pinyin.like("%" + storeItemPinyin + "%"), PosItemGradeDao.Properties.Item_grade_sale_cease_flag.eq(false)
            ).list();

        }else {
            List<PosItem> getPosItems = pos_itemDao.queryBuilder().where(
                    PosItemDao.Properties.Store_item_pinyin.like("%" + storeItemPinyin + "%"), PosItemDao.Properties.Item_type.notEq(9), PosItemDao.Properties.Item_type.notEq(11),PosItemDao.Properties.Item_type.notEq(10),
                    PosItemDao.Properties.Item_del_tag.eq(false), PosItemDao.Properties.Item_eliminative_flag.eq(false)
            ).list();

            posItemGrades = posItemGradeDao.queryBuilder().where(
                    PosItemGradeDao.Properties.Item_grade_pinyin.like("%" + storeItemPinyin + "%"), PosItemGradeDao.Properties.Item_grade_sale_cease_flag.eq(false)
            ).list();


            posItems = new ArrayList<>();
            if(getPosItems != null){
                for (PosItem posItem : getPosItems){
                    if(posItem.getItem_sale_cease_flag() == null || !posItem.getItem_sale_cease_flag() && posItem.getItem_type() != 10){
                        posItems.add(posItem);
                    }
                }
            }
        }

        if (posItemGrades != null) {
            for (PosItemGrade posItemGrade : posItemGrades){
                PosItem loadPosItem = pos_itemDao.load(posItemGrade.getItem_num());
                if(loadPosItem != null){
                    PosItem copyPosItem = RetailPosManager.getInstance().copyPosItem(loadPosItem);
                    copyPosItem.setShowPosItemGrade(posItemGrade);
                    posItems.add(copyPosItem);
                }
            }
        }
        return posItems;
    }

    public boolean AddPosItemSameName(String itemName) {
        PosItemDao pos_itemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        PosItem posItem = pos_itemDao.queryBuilder().where(PosItemDao.Properties.Item_name.eq(itemName)).limit(1).offset(0).unique();
        if (posItem == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据item_num来删除item_bar数据
     *
     * @param item_num
     * @return
     */
    public static boolean deleteItemBarByItemNum(final long item_num) {

        final ItemBarDao itemBarDao = DaoManager.getInstance().getDaoSession().getItemBarDao();

        return MatterUtils.doMatter(itemBarDao, new Runnable() {
            @Override
            public void run() {
                List<ItemBar> item_num_list = itemBarDao.queryRaw("where item_num=?", String.valueOf(item_num));
                itemBarDao.deleteInTx(item_num_list);
            }
        });
    }


    /**
     * 根据item_num来删除pos_item_kit数据
     *
     * @param item_num
     * @return
     */
    public static boolean deletePosItemKitByItemNum(final long item_num) {

        final PosItemKitDao posItemKitDao = DaoManager.getInstance().getDaoSession().getPosItemKitDao();

        return MatterUtils.doMatter(posItemKitDao, new Runnable() {
            @Override
            public void run() {
                List<PosItemKit> item_num_list = posItemKitDao.queryRaw("where item_num=?", String.valueOf(item_num));
                posItemKitDao.deleteInTx(item_num_list);
            }
        });
    }

    public boolean deletePostItemByItemNum(final long item_num) {
        final PosItemDao posItemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        return MatterUtils.doMatter(posItemDao, new Runnable() {
            @Override
            public void run() {
                posItemDao.deleteByKey(item_num);
            }
        });
    }


    /**
     * 根据item_num来删除pos_item_grade数据
     *
     * @param item_num
     * @return
     */
    public static boolean deletePosItemGradeByItemNum(final long item_num) {

        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();

        return MatterUtils.doMatter(posItemGradeDao, new Runnable() {
            @Override
            public void run() {
                List<PosItemGrade> item_num_list = posItemGradeDao.queryRaw("where item_num=?", String.valueOf(item_num));
                posItemGradeDao.deleteInTx(item_num_list);
            }
        });
    }

    /**
     * 根据item_num来删除pos_item_grade数据
     *
     * @param item_num
     * @return
     */
    public List<PosItemGrade> getAllItemGrade(final long item_num) {
        final PosItemGradeDao posItemGradeDao = DaoManager.getInstance().getDaoSession().getPosItemGradeDao();
        List<PosItemGrade> pos_item_grade_list = posItemGradeDao.queryRaw("where item_num=? and item_grade_sale_cease_flag = 0", String.valueOf(item_num));
        return pos_item_grade_list;
    }

    public String getPosItemCreateDate() {
        String item_last_edit_time = null;
        try {
            DaoSession session = DaoManager.getInstance().getDaoSession();
            String strSql = "select *  from pos_item  order by ITEM_LAST_EDIT_TIME desc limit 1";
            Cursor c = session.getDatabase().rawQuery(strSql, null);
            if (c.moveToFirst()) {
                item_last_edit_time = c.getString(c.getColumnIndex("ITEM_LAST_EDIT_TIME"));
            }
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (TextUtils.isEmpty(item_last_edit_time)) {
            item_last_edit_time = TimeUtil.getInstance().stampToDate(1);
        }
        return item_last_edit_time;
    }


    public boolean insert(final PosItem mPosItem) {
        final PosItemDao posItemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        return MatterUtils.doMatter(posItemDao, new Runnable() {
            @Override
            public void run() {
                posItemDao.insertOrReplaceInTx(mPosItem);
            }
        });
    }

    /**
     * 积分兑换物，获取商品价格
     *
     * @param itemNum
     * @return
     */
    public float getPosItemPrice(final int itemNum) {
        final PosItemDao posItemDao = DaoManager.getInstance().getDaoSession().getPosItemDao();
        PosItem posItem = posItemDao.queryBuilder().where(PosItemDao.Properties.Item_num.eq(itemNum)).build().unique();
        if (posItem != null) {
            if (LibConfig.activeBranch != null && LibConfig.activeBranch.getBranch_matrix_price_actived() && posItem.getBranch_regular_price() != 0) {
                return posItem.getBranch_regular_price();
            } else {
                return posItem.getItem_regular_price();
            }
        } else {
            return 0;
        }

    }


    public boolean insertItemBar(final ItemBar mItemBar) {
        final ItemBarDao itemBarDao = DaoManager.getInstance().getDaoSession().getItemBarDao();
        return MatterUtils.doMatter(itemBarDao, new Runnable() {
            @Override
            public void run() {
                itemBarDao.insertOrReplaceInTx(mItemBar);
            }
        });
    }

    public ItemBar getItemBarByNum(String num) {
        final ItemBarDao itemBarDao = DaoManager.getInstance().getDaoSession().getItemBarDao();
        ItemBar itemBar = null;
        List<ItemBar> itemBarList = itemBarDao.queryBuilder().where(ItemBarDao.Properties.Item_num.eq(num)).list();
        itemBar = itemBarDao.queryBuilder().where(ItemBarDao.Properties.Item_num.eq(num)).limit(1).offset(0).unique();
        return itemBar;
    }


}
