package com.nhsoft.poslib.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nhsoft.poslib.service.greendao.AccountBankDao;
import com.nhsoft.poslib.service.greendao.AggregationDao;
import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.AttachedScreenDao;
import com.nhsoft.poslib.service.greendao.BookResourceDao;
import com.nhsoft.poslib.service.greendao.BottomMenuDao;
import com.nhsoft.poslib.service.greendao.BranchDao;
import com.nhsoft.poslib.service.greendao.BranchGroupDao;
import com.nhsoft.poslib.service.greendao.BranchMerchantDao;
import com.nhsoft.poslib.service.greendao.BranchMessageDao;
import com.nhsoft.poslib.service.greendao.BranchRegionDao;
import com.nhsoft.poslib.service.greendao.BranchsBeanDao;
import com.nhsoft.poslib.service.greendao.CardChangeDao;
import com.nhsoft.poslib.service.greendao.CardDepositDao;
import com.nhsoft.poslib.service.greendao.CardDepositFailedDao;
import com.nhsoft.poslib.service.greendao.CardTypeParamDao;
import com.nhsoft.poslib.service.greendao.CategoryFindDao;
import com.nhsoft.poslib.service.greendao.ChangeGoodsMenuDao;
import com.nhsoft.poslib.service.greendao.ClientPointDao;
import com.nhsoft.poslib.service.greendao.CustomerRegisterDao;
import com.nhsoft.poslib.service.greendao.DaoMaster;
import com.nhsoft.poslib.service.greendao.DemoEntityDao;
import com.nhsoft.poslib.service.greendao.EmployeeDao;
import com.nhsoft.poslib.service.greendao.FmPaymentDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.InventoryDao;
import com.nhsoft.poslib.service.greendao.ItemBarDao;
import com.nhsoft.poslib.service.greendao.ItemCategoryDao;
import com.nhsoft.poslib.service.greendao.KeyGeneratorBizdayDao;
import com.nhsoft.poslib.service.greendao.LoginDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDetailDao;
import com.nhsoft.poslib.service.greendao.MarketActionDao;
import com.nhsoft.poslib.service.greendao.MarketActionDetailDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitItemDao;
import com.nhsoft.poslib.service.greendao.MerchantDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.service.greendao.PointOrderDao;
import com.nhsoft.poslib.service.greendao.PointOrderDetialDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDetailDao;
import com.nhsoft.poslib.service.greendao.PointRuleDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDetailDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDetailDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDetailDao;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;
import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeTerminalDao;
import com.nhsoft.poslib.service.greendao.PosItemKitDao;
import com.nhsoft.poslib.service.greendao.PosItemNewNongMaoDao;
import com.nhsoft.poslib.service.greendao.PosItemTerminalDao;
import com.nhsoft.poslib.service.greendao.PosMachineDao;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.PosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PosOrderKitDetailDao;
import com.nhsoft.poslib.service.greendao.PrintOrderUsingDao;
import com.nhsoft.poslib.service.greendao.PrivilegeResourceNewDao;
import com.nhsoft.poslib.service.greendao.RelatCardDao;
import com.nhsoft.poslib.service.greendao.ReplaceCardDao;
import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.ShiftTableDao;
import com.nhsoft.poslib.service.greendao.ShiftTablePaymentDao;
import com.nhsoft.poslib.service.greendao.StallDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDetailDao;
import com.nhsoft.poslib.service.greendao.StallMatrixDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDetailDao;
import com.nhsoft.poslib.service.greendao.StoreHouseDao;
import com.nhsoft.poslib.service.greendao.SystemBookDao;
import com.nhsoft.poslib.service.greendao.SystemImageQrcodeDao;
import com.nhsoft.poslib.service.greendao.SystemPrintDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.service.greendao.TableMd5Dao;
import com.nhsoft.poslib.service.greendao.TicketSendDetailDao;
import com.nhsoft.poslib.service.greendao.UserRoleDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDetailDao;
import com.nhsoft.poslib.service.greendao.VipConsumeDao;
import com.nhsoft.poslib.service.greendao.VipCrmAmaLevelDao;
import com.nhsoft.poslib.service.greendao.VipCrmFeeDao;
import com.nhsoft.poslib.service.greendao.VipCrmPointRateDao;
import com.nhsoft.poslib.service.greendao.VipIcInitDao;
import com.nhsoft.poslib.service.greendao.VipLevelPointRuleDao;
import com.nhsoft.poslib.service.greendao.VipSendCardDao;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.service.greendao.YunServiceDaysDao;
import com.nhsoft.poslib.utils.EvtLog;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Iverson on 2018/11/15 5:47 PM
 * 此类用于：
 */
public class MySqlLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySqlLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        EvtLog.e("MySqlLiteOpenHelper", "oldVersion:" + oldVersion + ",newVersion" + newVersion);

        new UpgradeHelper().migrate(db, ItemBarDao.class, PosItemDao.class, PosItemGradeDao.class, PosItemKitDao.class
                , AccountBankDao.class, AggregationDao.class, AppUserDao.class, AttachedScreenDao.class, BookResourceDao.class,
                BranchDao.class, BranchGroupDao.class, BranchRegionDao.class, CardDepositDao.class,
                CardDepositFailedDao.class, ClientPointDao.class, EmployeeDao.class, DemoEntityDao.class, ItemCategoryDao.class, KeyGeneratorBizdayDao.class
                , LoginDao.class, ManagementTemplateDao.class, ManagementTemplateDetailDao.class, MarketActionDao.class, MarketActionDetailDao.class
                , MeasureUnitDao.class, MeasureUnitItemDao.class, PaymentDao.class, PointPolicyDao.class, PointPolicyDetailDao.class, PolicyPromotionDao.class, PointPolicyDetailDao.class,
                PosMachineDao.class, PosOrderDao.class, PosOrderDetailDao.class,
                PosOrderKitDetailDao.class, PrivilegeResourceNewDao.class, RelatCardDao.class, ReplaceCardDao.class, RolePrivilegeNewDao.class,
                ShiftTableDao.class, StoreHouseDao.class, SystemRoleDao.class, SystemBookDao.class, TableMd5Dao.class, TicketSendDetailDao.class, UserRoleDao.class,
                VipConsumeDao.class, VipSendCardDao.class, VipStrangeSuccessSendMoneyDao.class, SystemPrintDao.class, SystemImageQrcodeDao.class, PointOrderDao.class, PointOrderDetialDao.class,
                ShiftTablePaymentDao.class, PolicyDiscountDao.class, PolicyDiscountDetailDao.class, PolicyQuantityDao.class, PolicyQuantityDetailDao.class, PolicyMoneyDao.class, PolicyMoneyDetailDao.class,
                PolicyPresentDao.class, PolicyPresentDetailDao.class, CardChangeDao.class, YunServiceDaysDao.class, CategoryFindDao.class
                , MerchantDao.class, StallDao.class, StallDiscountDao.class, StallDiscountDetailDao.class,
                StallMatrixDao.class, StallPromotionDao.class, StallPromotionDetailDao.class, PosItemTerminalDao.class, PosItemGradeTerminalDao.class, BottomMenuDao.class, PrintOrderUsingDao.class,
                PosItemNewNongMaoDao.class,CustomerRegisterDao.class,FmPosOrderDao.class,FmPosOrderDetailDao.class,FmPaymentDao.class, VipIcInitDao.class, VipCrmPointRateDao.class, VipCRMLevelDetailDao.class,
                BranchMerchantDao.class,ChangeGoodsMenuDao.class, CardTypeParamDao.class, PointRuleDao.class,PointRuleDao.class, VipCrmAmaLevelDao.class, VipLevelPointRuleDao.class, VipCRMLevelDao.class, VipCrmFeeDao.class
                , BranchMessageDao.class, BranchsBeanDao.class, InventoryDao.class);
    }

}

