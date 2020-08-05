package com.nhsoft.poslib.call.impl;

import android.text.TextUtils;

import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.MeasureUnitItem;
import com.nhsoft.poslib.service.greendao.DaoSession;
import com.nhsoft.poslib.service.greendao.ItemCategoryDao;
import com.nhsoft.poslib.utils.MatterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iverson on 2018/11/19 5:34 PM
 * 此类用于：
 */
public class ItemCategoryImpl {

    private static ItemCategoryImpl instance;
    public static ItemCategoryImpl getInstance(){
        if (instance==null){
            instance=new ItemCategoryImpl();
        }
        return instance;
    }
    public boolean saveItemCategoryList(final List<ItemCategory> dataLis){
        final ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        mItemCategoryDao.deleteAll();
        if(dataLis.size() == 0)return true;
        boolean isSuccess = MatterUtils.doMatter(mItemCategoryDao, new Runnable() {
            @Override
            public void run() {
                mItemCategoryDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    /**
     * 更新数据
     * @param dataLis
     * @return
     */
    public static boolean updateItemCategory(final List<ItemCategory> dataLis){
        final ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        boolean isSuccess = MatterUtils.doMatter(mItemCategoryDao, new Runnable() {
            @Override
            public void run() {
                mItemCategoryDao.insertOrReplaceInTx(dataLis);
            }
        });
        return isSuccess;
    }

    public void setHierarchyCategory(){
        List<ItemCategory> itemCategoryList=getAllFirstCategoryList();
        for (ItemCategory itemCategory:itemCategoryList){
            itemCategory.setHierarchy(1);
        }
        updateItemCategory(itemCategoryList);
        getSonCategoryList(itemCategoryList);
    }

    /**
     * 获取全部一级分类
     * @return
     */
    public static List<ItemCategory> getAllFirstCategoryList(){
        return  DaoManager.getInstance().getDaoSession().getItemCategoryDao().queryBuilder()
                .where(ItemCategoryDao.Properties.Parent_category_code.isNull())
                .list();
    }

    /**
     * 设置子类层级
     * @param itemCategory
     */
    public static void setItemCategoryHierarchy(ItemCategory itemCategory){
        for (ItemCategory category:getSonCategoryList(itemCategory.getCategory_code())){
            category.setHierarchy(itemCategory.getHierarchy()+1);
            if (category.getHierarchy()==2){
                category.setSecond_category_code(category.getCategory_code());
            }else if (itemCategory.getSecond_category_code()!=null){
                category.setSecond_category_code(itemCategory.getSecond_category_code());
            }
        }
    }

    /**
     * 获取子分类
     */
    public static List<ItemCategory> getSonCategoryList(String category_code){
        return  DaoManager.getInstance().getDaoSession().getItemCategoryDao().queryBuilder()
                .where(ItemCategoryDao.Properties.Parent_category_code.eq(category_code))
                .list();
    }

    /**
     * 获取子分类
     */
    public static List<ItemCategory> getSonCategoryList(List<ItemCategory> itemCategories){
        List<ItemCategory> itemCategoryList=new ArrayList<>();
        for (ItemCategory itemCategory:itemCategories){
            setItemCategoryHierarchy(itemCategory);
            itemCategoryList.addAll(getSonCategoryList(itemCategory.getCategory_code()));
        }
        updateItemCategory(itemCategoryList);
        if (itemCategoryList!=null&&itemCategoryList.size()>0){
            return getSonCategoryList(itemCategoryList);
        }else {
            return null;
        }
    }

    /**
     * 获取全部子分类包括自身
     * @param category_code
     * @return
     */
    public static List<ItemCategory> getAllSecondSonCategoryList(String category_code){
        return DaoManager.getInstance().getDaoSession().getItemCategoryDao().queryBuilder()
                .where(ItemCategoryDao.Properties.Second_category_code.eq(category_code))
                .orderAsc(ItemCategoryDao.Properties.Hierarchy)
                .list();
    }


    public static List<ItemCategory> getAllItemGategory(){
        final ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        return mItemCategoryDao.loadAll();
    }

    /**
     * 寻找顶级分类的categoryCode
     * @param categoryCode
     * @return
     */
    public static ItemCategory findTopCode(String categoryCode){
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        ItemCategory loadItemCategory = mItemCategoryDao.load(categoryCode);
        if(!TextUtils.isEmpty(loadItemCategory.getParent_category_code())){
            ItemCategory parentCateGory = mItemCategoryDao.load(loadItemCategory.getParent_category_code());
            loadItemCategory.setParent_category_name(parentCateGory.getCategory_name());
        }
        return loadItemCategory;
    }

    /**
     * 寻找本商品的categoryCode
     * @param categoryCode
     * @return
     */
    public static ItemCategory findCategoryCode(String categoryCode){
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        ItemCategory loadItemCategory = mItemCategoryDao.load(categoryCode);
        return loadItemCategory;
    }


    /**
     * 寻找本商品的父类categoryCode
     * @param categoryCode
     * @return
     */
    public static ItemCategory findParentCategoryCode(String categoryCode){
        if(TextUtils.isEmpty(categoryCode)){
            return null;
        }
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        ItemCategory loadItemCategory = mItemCategoryDao.load(categoryCode);
        return loadItemCategory;
    }




    /**
     * 寻找二级分类的categoryCode
     * @param categoryCode
     * @return
     */
    public static String findSecondCode(String categoryCode){
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        ItemCategory loadItemCategory = mItemCategoryDao.load(categoryCode);
        if(!TextUtils.isEmpty(loadItemCategory.getSecond_category_code())){
            return loadItemCategory.getSecond_category_code();
        }
        return loadItemCategory.getCategory_code();
    }

    /**
     * 获取一级分类
     * @return
     */
    public List<ItemCategory> getAllFirstItemGategory(){
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        List<ItemCategory>itemList=mItemCategoryDao.queryBuilder().where(ItemCategoryDao.Properties.Parent_category_code.isNull()).list();
        return itemList;
    }



    /**
     * 获取一级分类 Category_code 最大值
     * @return
     */
    public int getFirstMaxCode(){
        int max=0;
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        ItemCategory itemList=mItemCategoryDao.queryBuilder()
                .where(ItemCategoryDao.Properties.Parent_category_code.isNull()
                ).orderDesc(ItemCategoryDao.Properties.Category_code).limit(1).offset(0).unique();
        if (itemList!=null){
            max=(Integer.parseInt(itemList.getCategory_code())+1);
        }
        return max;
    }

    //获取选中商品分类下标
    public int getIndexOfItemCateGory(String categoryName){
        int index=0;
        ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        List<ItemCategory>itemList=mItemCategoryDao.queryBuilder().where(ItemCategoryDao.Properties.Parent_category_code.isNull()).list();
        for (int i=0;i<itemList.size();i++){
            ItemCategory itemCategory=itemList.get(i);
            if (itemCategory.getCategory_name().equals(categoryName)){
                index=i;
                return index;
            }
        }
        return index;
    }

    //获取选中商品单位下标
    public int getIndexOfItemSpecification(String categoryName){
        int index=0;
        List<MeasureUnitItem>itemList= MeasureUnitImpl.getInstance().getMeasureUnitList("系统默认组");;
        for (int i=0;i<itemList.size();i++){
            MeasureUnitItem itemCategory=itemList.get(i);
            if (itemCategory.getItem_unit_name().equals(categoryName)){
                index=i;
                return index;
            }
        }
        return index;
    }

    /**
     * 新建分类命名
     * @param mItemCategoryLis
     * @return
     */
    private int max=0;
    public String getItemCategoryName(final List <ItemCategory> mItemCategoryLis){

         String name="";
         String str="";
         String strName="";
        final ItemCategoryDao mItemCategoryDao = DaoManager.getInstance().getDaoSession().getItemCategoryDao();
        List<ItemCategory>itemList=mItemCategoryDao.queryBuilder().where(ItemCategoryDao.Properties.Category_name.like("新建分类")).list();
        for (ItemCategory itemCategory:itemList){
            strName=itemCategory.getCategory_name();
            str=strName.substring(3,strName.length());
            if (!TextUtils.isEmpty(str)){
                if (max<Integer.parseInt(str)){
                    max=Integer.parseInt(str);
                }
            }
        }
        max=max+1;
        name="新建分类"+(max);
         return name;
    }

    public boolean insertItemCategory(final List <ItemCategory> mItemCategoryLis){
        final DaoSession mDaoSession = DaoManager.getInstance().getDaoSession();
         return MatterUtils.doMatter(mDaoSession.getItemCategoryDao(), new Runnable() {
            @Override
            public void run() {
                ItemCategoryDao mItemCategoryDao=mDaoSession.getItemCategoryDao();
                for (ItemCategory mItemCategory:mItemCategoryLis){
                    mItemCategoryDao.insertOrReplace(mItemCategory);
                }
            }
        });
    }
}
