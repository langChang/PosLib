package com.nhsoft.poslib.service.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nhsoft.poslib.entity.ShiftTablePayment;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PointOrder;
import com.nhsoft.poslib.entity.EmployeeEntity;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.SystemPrint;
import com.nhsoft.poslib.entity.CurrentUser;
import com.nhsoft.poslib.entity.IcCardMessage;
import com.nhsoft.poslib.entity.DeskOperatingParameters;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.entity.PolicyPresentDetail;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.BranchResource;
import com.nhsoft.poslib.entity.PosCarryLog;
import com.nhsoft.poslib.entity.UserRole;
import com.nhsoft.poslib.entity.PolicyDiscountDetail;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.PosItemTerminal;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.BottomMenu;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.PayStyleToCashBank;
import com.nhsoft.poslib.entity.VipSendCard;
import com.nhsoft.poslib.entity.RelatCard;
import com.nhsoft.poslib.entity.TableMd5Entity;
import com.nhsoft.poslib.entity.PointOrderDetial;
import com.nhsoft.poslib.entity.Inventory;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.entity.MeasureUnitItem;
import com.nhsoft.poslib.entity.FmPosOrder;
import com.nhsoft.poslib.entity.RolePrivilegeNew;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.PointPolicyDetail;
import com.nhsoft.poslib.entity.ReplaceCard;
import com.nhsoft.poslib.entity.FmPosOrderDetail;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.entity.new_nong_mao.PosItemNewNongMao;
import com.nhsoft.poslib.entity.new_nong_mao.BranchMerchant;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.CardChange;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.entity.ChangeGoodsMenu;
import com.nhsoft.poslib.entity.BranchRegion;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.entity.AccountBank;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.ManagementTemplate;
import com.nhsoft.poslib.entity.VipCRMLevelDetail;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.entity.PrivilegeResourceNew;
import com.nhsoft.poslib.entity.CardDepositFailed;
import com.nhsoft.poslib.entity.BranchsBean;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.PointRule;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.ItemBar;
import com.nhsoft.poslib.entity.StoreHouse;
import com.nhsoft.poslib.entity.DemoEntity;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.entity.SystemImageQrcode;
import com.nhsoft.poslib.entity.TableMd5;
import com.nhsoft.poslib.entity.CustomerRegister;
import com.nhsoft.poslib.entity.VipStrangeSuccessSendMoney;
import com.nhsoft.poslib.entity.OtherRevenue;
import com.nhsoft.poslib.entity.VipConsume;
import com.nhsoft.poslib.entity.nongmao.StallDiscount;
import com.nhsoft.poslib.entity.nongmao.StallDiscountDetail;
import com.nhsoft.poslib.entity.nongmao.Stall;
import com.nhsoft.poslib.entity.nongmao.StallPromotion;
import com.nhsoft.poslib.entity.nongmao.StallPromotionDetail;
import com.nhsoft.poslib.entity.nongmao.Merchant;
import com.nhsoft.poslib.entity.nongmao.StallMatrix;
import com.nhsoft.poslib.entity.nongmao.CategoryFind;
import com.nhsoft.poslib.entity.VipIcInit;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.entity.CardDeposit;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.VipLevelPointRule;
import com.nhsoft.poslib.entity.MarketActionDetail;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PrintOrderUsing;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.entity.VipCrmPointRate;
import com.nhsoft.poslib.entity.PolicyMoneyDetail;
import com.nhsoft.poslib.entity.MeasureUnit;
import com.nhsoft.poslib.entity.AttachedScreen;
import com.nhsoft.poslib.entity.SystemBook;
import com.nhsoft.poslib.entity.FmPayment;
import com.nhsoft.poslib.entity.PosItemGradeTerminal;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.BranchMessage;
import com.nhsoft.poslib.entity.Employee;
import com.nhsoft.poslib.entity.VipCrmFee;
import com.nhsoft.poslib.entity.ManagementTemplateDetail;
import com.nhsoft.poslib.entity.VipCRMLevel;
import com.nhsoft.poslib.model.YunServiceDays;

import com.nhsoft.poslib.service.greendao.ShiftTablePaymentDao;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDao;
import com.nhsoft.poslib.service.greendao.PointOrderDao;
import com.nhsoft.poslib.service.greendao.EmployeeEntityDao;
import com.nhsoft.poslib.service.greendao.PosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.PosOrderKitDetailDao;
import com.nhsoft.poslib.service.greendao.SystemPrintDao;
import com.nhsoft.poslib.service.greendao.CurrentUserDao;
import com.nhsoft.poslib.service.greendao.IcCardMessageDao;
import com.nhsoft.poslib.service.greendao.DeskOperatingParametersDao;
import com.nhsoft.poslib.service.greendao.ItemCategoryDao;
import com.nhsoft.poslib.service.greendao.PosItemKitDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDetailDao;
import com.nhsoft.poslib.service.greendao.ClientPointDao;
import com.nhsoft.poslib.service.greendao.BranchResourceDao;
import com.nhsoft.poslib.service.greendao.PosCarryLogDao;
import com.nhsoft.poslib.service.greendao.UserRoleDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDetailDao;
import com.nhsoft.poslib.service.greendao.BookResourceDao;
import com.nhsoft.poslib.service.greendao.PosItemTerminalDao;
import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.BottomMenuDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.PayStyleToCashBankDao;
import com.nhsoft.poslib.service.greendao.VipSendCardDao;
import com.nhsoft.poslib.service.greendao.RelatCardDao;
import com.nhsoft.poslib.service.greendao.TableMd5EntityDao;
import com.nhsoft.poslib.service.greendao.PointOrderDetialDao;
import com.nhsoft.poslib.service.greendao.InventoryDao;
import com.nhsoft.poslib.service.greendao.BranchGroupDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitItemDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDao;
import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.AmountPayDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDetailDao;
import com.nhsoft.poslib.service.greendao.ReplaceCardDao;
import com.nhsoft.poslib.service.greendao.FmPosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.MarketActionDao;
import com.nhsoft.poslib.service.greendao.PolicyPromotionDetailDao;
import com.nhsoft.poslib.service.greendao.PosItemNewNongMaoDao;
import com.nhsoft.poslib.service.greendao.BranchMerchantDao;
import com.nhsoft.poslib.service.greendao.PolicyPresentDao;
import com.nhsoft.poslib.service.greendao.CardChangeDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.ShiftTableDao;
import com.nhsoft.poslib.service.greendao.ChangeGoodsMenuDao;
import com.nhsoft.poslib.service.greendao.BranchRegionDao;
import com.nhsoft.poslib.service.greendao.TicketSendDetailDao;
import com.nhsoft.poslib.service.greendao.AccountBankDao;
import com.nhsoft.poslib.service.greendao.KeyGeneratorBizdayDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDetailDao;
import com.nhsoft.poslib.service.greendao.LoginDao;
import com.nhsoft.poslib.service.greendao.PrivilegeResourceNewDao;
import com.nhsoft.poslib.service.greendao.CardDepositFailedDao;
import com.nhsoft.poslib.service.greendao.BranchsBeanDao;
import com.nhsoft.poslib.service.greendao.VipCrmAmaLevelDao;
import com.nhsoft.poslib.service.greendao.PointRuleDao;
import com.nhsoft.poslib.service.greendao.PolicyDiscountDao;
import com.nhsoft.poslib.service.greendao.BranchDao;
import com.nhsoft.poslib.service.greendao.ItemBarDao;
import com.nhsoft.poslib.service.greendao.StoreHouseDao;
import com.nhsoft.poslib.service.greendao.DemoEntityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;
import com.nhsoft.poslib.service.greendao.SystemImageQrcodeDao;
import com.nhsoft.poslib.service.greendao.TableMd5Dao;
import com.nhsoft.poslib.service.greendao.CustomerRegisterDao;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.service.greendao.OtherRevenueDao;
import com.nhsoft.poslib.service.greendao.VipConsumeDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDao;
import com.nhsoft.poslib.service.greendao.StallDiscountDetailDao;
import com.nhsoft.poslib.service.greendao.StallDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDao;
import com.nhsoft.poslib.service.greendao.StallPromotionDetailDao;
import com.nhsoft.poslib.service.greendao.MerchantDao;
import com.nhsoft.poslib.service.greendao.StallMatrixDao;
import com.nhsoft.poslib.service.greendao.CategoryFindDao;
import com.nhsoft.poslib.service.greendao.VipIcInitDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.service.greendao.CardDepositDao;
import com.nhsoft.poslib.service.greendao.CardTypeParamDao;
import com.nhsoft.poslib.service.greendao.VipLevelPointRuleDao;
import com.nhsoft.poslib.service.greendao.MarketActionDetailDao;
import com.nhsoft.poslib.service.greendao.PointPolicyDao;
import com.nhsoft.poslib.service.greendao.PrintOrderUsingDao;
import com.nhsoft.poslib.service.greendao.PosMachineDao;
import com.nhsoft.poslib.service.greendao.AggregationDao;
import com.nhsoft.poslib.service.greendao.VipCrmPointRateDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDetailDao;
import com.nhsoft.poslib.service.greendao.MeasureUnitDao;
import com.nhsoft.poslib.service.greendao.AttachedScreenDao;
import com.nhsoft.poslib.service.greendao.SystemBookDao;
import com.nhsoft.poslib.service.greendao.FmPaymentDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeTerminalDao;
import com.nhsoft.poslib.service.greendao.PolicyMoneyDao;
import com.nhsoft.poslib.service.greendao.BranchMessageDao;
import com.nhsoft.poslib.service.greendao.EmployeeDao;
import com.nhsoft.poslib.service.greendao.VipCrmFeeDao;
import com.nhsoft.poslib.service.greendao.ManagementTemplateDetailDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDao;
import com.nhsoft.poslib.service.greendao.YunServiceDaysDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig shiftTablePaymentDaoConfig;
    private final DaoConfig policyPromotionDaoConfig;
    private final DaoConfig pointOrderDaoConfig;
    private final DaoConfig employeeEntityDaoConfig;
    private final DaoConfig posOrderDetailDaoConfig;
    private final DaoConfig paymentDaoConfig;
    private final DaoConfig posOrderDaoConfig;
    private final DaoConfig posOrderKitDetailDaoConfig;
    private final DaoConfig systemPrintDaoConfig;
    private final DaoConfig currentUserDaoConfig;
    private final DaoConfig icCardMessageDaoConfig;
    private final DaoConfig deskOperatingParametersDaoConfig;
    private final DaoConfig itemCategoryDaoConfig;
    private final DaoConfig posItemKitDaoConfig;
    private final DaoConfig policyPresentDetailDaoConfig;
    private final DaoConfig clientPointDaoConfig;
    private final DaoConfig branchResourceDaoConfig;
    private final DaoConfig posCarryLogDaoConfig;
    private final DaoConfig userRoleDaoConfig;
    private final DaoConfig policyDiscountDetailDaoConfig;
    private final DaoConfig bookResourceDaoConfig;
    private final DaoConfig posItemTerminalDaoConfig;
    private final DaoConfig posItemDaoConfig;
    private final DaoConfig bottomMenuDaoConfig;
    private final DaoConfig policyQuantityDaoConfig;
    private final DaoConfig appUserDaoConfig;
    private final DaoConfig payStyleToCashBankDaoConfig;
    private final DaoConfig vipSendCardDaoConfig;
    private final DaoConfig relatCardDaoConfig;
    private final DaoConfig tableMd5EntityDaoConfig;
    private final DaoConfig pointOrderDetialDaoConfig;
    private final DaoConfig inventoryDaoConfig;
    private final DaoConfig branchGroupDaoConfig;
    private final DaoConfig measureUnitItemDaoConfig;
    private final DaoConfig fmPosOrderDaoConfig;
    private final DaoConfig rolePrivilegeNewDaoConfig;
    private final DaoConfig amountPayDaoConfig;
    private final DaoConfig pointPolicyDetailDaoConfig;
    private final DaoConfig replaceCardDaoConfig;
    private final DaoConfig fmPosOrderDetailDaoConfig;
    private final DaoConfig marketActionDaoConfig;
    private final DaoConfig policyPromotionDetailDaoConfig;
    private final DaoConfig posItemNewNongMaoDaoConfig;
    private final DaoConfig branchMerchantDaoConfig;
    private final DaoConfig policyPresentDaoConfig;
    private final DaoConfig cardChangeDaoConfig;
    private final DaoConfig posItemGradeDaoConfig;
    private final DaoConfig shiftTableDaoConfig;
    private final DaoConfig changeGoodsMenuDaoConfig;
    private final DaoConfig branchRegionDaoConfig;
    private final DaoConfig ticketSendDetailDaoConfig;
    private final DaoConfig accountBankDaoConfig;
    private final DaoConfig keyGeneratorBizdayDaoConfig;
    private final DaoConfig managementTemplateDaoConfig;
    private final DaoConfig vipCRMLevelDetailDaoConfig;
    private final DaoConfig loginDaoConfig;
    private final DaoConfig privilegeResourceNewDaoConfig;
    private final DaoConfig cardDepositFailedDaoConfig;
    private final DaoConfig branchsBeanDaoConfig;
    private final DaoConfig vipCrmAmaLevelDaoConfig;
    private final DaoConfig pointRuleDaoConfig;
    private final DaoConfig policyDiscountDaoConfig;
    private final DaoConfig branchDaoConfig;
    private final DaoConfig itemBarDaoConfig;
    private final DaoConfig storeHouseDaoConfig;
    private final DaoConfig demoEntityDaoConfig;
    private final DaoConfig policyQuantityDetailDaoConfig;
    private final DaoConfig systemImageQrcodeDaoConfig;
    private final DaoConfig tableMd5DaoConfig;
    private final DaoConfig customerRegisterDaoConfig;
    private final DaoConfig vipStrangeSuccessSendMoneyDaoConfig;
    private final DaoConfig otherRevenueDaoConfig;
    private final DaoConfig vipConsumeDaoConfig;
    private final DaoConfig stallDiscountDaoConfig;
    private final DaoConfig stallDiscountDetailDaoConfig;
    private final DaoConfig stallDaoConfig;
    private final DaoConfig stallPromotionDaoConfig;
    private final DaoConfig stallPromotionDetailDaoConfig;
    private final DaoConfig merchantDaoConfig;
    private final DaoConfig stallMatrixDaoConfig;
    private final DaoConfig categoryFindDaoConfig;
    private final DaoConfig vipIcInitDaoConfig;
    private final DaoConfig systemRoleDaoConfig;
    private final DaoConfig cardDepositDaoConfig;
    private final DaoConfig cardTypeParamDaoConfig;
    private final DaoConfig vipLevelPointRuleDaoConfig;
    private final DaoConfig marketActionDetailDaoConfig;
    private final DaoConfig pointPolicyDaoConfig;
    private final DaoConfig printOrderUsingDaoConfig;
    private final DaoConfig posMachineDaoConfig;
    private final DaoConfig aggregationDaoConfig;
    private final DaoConfig vipCrmPointRateDaoConfig;
    private final DaoConfig policyMoneyDetailDaoConfig;
    private final DaoConfig measureUnitDaoConfig;
    private final DaoConfig attachedScreenDaoConfig;
    private final DaoConfig systemBookDaoConfig;
    private final DaoConfig fmPaymentDaoConfig;
    private final DaoConfig posItemGradeTerminalDaoConfig;
    private final DaoConfig policyMoneyDaoConfig;
    private final DaoConfig branchMessageDaoConfig;
    private final DaoConfig employeeDaoConfig;
    private final DaoConfig vipCrmFeeDaoConfig;
    private final DaoConfig managementTemplateDetailDaoConfig;
    private final DaoConfig vipCRMLevelDaoConfig;
    private final DaoConfig yunServiceDaysDaoConfig;

    private final ShiftTablePaymentDao shiftTablePaymentDao;
    private final PolicyPromotionDao policyPromotionDao;
    private final PointOrderDao pointOrderDao;
    private final EmployeeEntityDao employeeEntityDao;
    private final PosOrderDetailDao posOrderDetailDao;
    private final PaymentDao paymentDao;
    private final PosOrderDao posOrderDao;
    private final PosOrderKitDetailDao posOrderKitDetailDao;
    private final SystemPrintDao systemPrintDao;
    private final CurrentUserDao currentUserDao;
    private final IcCardMessageDao icCardMessageDao;
    private final DeskOperatingParametersDao deskOperatingParametersDao;
    private final ItemCategoryDao itemCategoryDao;
    private final PosItemKitDao posItemKitDao;
    private final PolicyPresentDetailDao policyPresentDetailDao;
    private final ClientPointDao clientPointDao;
    private final BranchResourceDao branchResourceDao;
    private final PosCarryLogDao posCarryLogDao;
    private final UserRoleDao userRoleDao;
    private final PolicyDiscountDetailDao policyDiscountDetailDao;
    private final BookResourceDao bookResourceDao;
    private final PosItemTerminalDao posItemTerminalDao;
    private final PosItemDao posItemDao;
    private final BottomMenuDao bottomMenuDao;
    private final PolicyQuantityDao policyQuantityDao;
    private final AppUserDao appUserDao;
    private final PayStyleToCashBankDao payStyleToCashBankDao;
    private final VipSendCardDao vipSendCardDao;
    private final RelatCardDao relatCardDao;
    private final TableMd5EntityDao tableMd5EntityDao;
    private final PointOrderDetialDao pointOrderDetialDao;
    private final InventoryDao inventoryDao;
    private final BranchGroupDao branchGroupDao;
    private final MeasureUnitItemDao measureUnitItemDao;
    private final FmPosOrderDao fmPosOrderDao;
    private final RolePrivilegeNewDao rolePrivilegeNewDao;
    private final AmountPayDao amountPayDao;
    private final PointPolicyDetailDao pointPolicyDetailDao;
    private final ReplaceCardDao replaceCardDao;
    private final FmPosOrderDetailDao fmPosOrderDetailDao;
    private final MarketActionDao marketActionDao;
    private final PolicyPromotionDetailDao policyPromotionDetailDao;
    private final PosItemNewNongMaoDao posItemNewNongMaoDao;
    private final BranchMerchantDao branchMerchantDao;
    private final PolicyPresentDao policyPresentDao;
    private final CardChangeDao cardChangeDao;
    private final PosItemGradeDao posItemGradeDao;
    private final ShiftTableDao shiftTableDao;
    private final ChangeGoodsMenuDao changeGoodsMenuDao;
    private final BranchRegionDao branchRegionDao;
    private final TicketSendDetailDao ticketSendDetailDao;
    private final AccountBankDao accountBankDao;
    private final KeyGeneratorBizdayDao keyGeneratorBizdayDao;
    private final ManagementTemplateDao managementTemplateDao;
    private final VipCRMLevelDetailDao vipCRMLevelDetailDao;
    private final LoginDao loginDao;
    private final PrivilegeResourceNewDao privilegeResourceNewDao;
    private final CardDepositFailedDao cardDepositFailedDao;
    private final BranchsBeanDao branchsBeanDao;
    private final VipCrmAmaLevelDao vipCrmAmaLevelDao;
    private final PointRuleDao pointRuleDao;
    private final PolicyDiscountDao policyDiscountDao;
    private final BranchDao branchDao;
    private final ItemBarDao itemBarDao;
    private final StoreHouseDao storeHouseDao;
    private final DemoEntityDao demoEntityDao;
    private final PolicyQuantityDetailDao policyQuantityDetailDao;
    private final SystemImageQrcodeDao systemImageQrcodeDao;
    private final TableMd5Dao tableMd5Dao;
    private final CustomerRegisterDao customerRegisterDao;
    private final VipStrangeSuccessSendMoneyDao vipStrangeSuccessSendMoneyDao;
    private final OtherRevenueDao otherRevenueDao;
    private final VipConsumeDao vipConsumeDao;
    private final StallDiscountDao stallDiscountDao;
    private final StallDiscountDetailDao stallDiscountDetailDao;
    private final StallDao stallDao;
    private final StallPromotionDao stallPromotionDao;
    private final StallPromotionDetailDao stallPromotionDetailDao;
    private final MerchantDao merchantDao;
    private final StallMatrixDao stallMatrixDao;
    private final CategoryFindDao categoryFindDao;
    private final VipIcInitDao vipIcInitDao;
    private final SystemRoleDao systemRoleDao;
    private final CardDepositDao cardDepositDao;
    private final CardTypeParamDao cardTypeParamDao;
    private final VipLevelPointRuleDao vipLevelPointRuleDao;
    private final MarketActionDetailDao marketActionDetailDao;
    private final PointPolicyDao pointPolicyDao;
    private final PrintOrderUsingDao printOrderUsingDao;
    private final PosMachineDao posMachineDao;
    private final AggregationDao aggregationDao;
    private final VipCrmPointRateDao vipCrmPointRateDao;
    private final PolicyMoneyDetailDao policyMoneyDetailDao;
    private final MeasureUnitDao measureUnitDao;
    private final AttachedScreenDao attachedScreenDao;
    private final SystemBookDao systemBookDao;
    private final FmPaymentDao fmPaymentDao;
    private final PosItemGradeTerminalDao posItemGradeTerminalDao;
    private final PolicyMoneyDao policyMoneyDao;
    private final BranchMessageDao branchMessageDao;
    private final EmployeeDao employeeDao;
    private final VipCrmFeeDao vipCrmFeeDao;
    private final ManagementTemplateDetailDao managementTemplateDetailDao;
    private final VipCRMLevelDao vipCRMLevelDao;
    private final YunServiceDaysDao yunServiceDaysDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        shiftTablePaymentDaoConfig = daoConfigMap.get(ShiftTablePaymentDao.class).clone();
        shiftTablePaymentDaoConfig.initIdentityScope(type);

        policyPromotionDaoConfig = daoConfigMap.get(PolicyPromotionDao.class).clone();
        policyPromotionDaoConfig.initIdentityScope(type);

        pointOrderDaoConfig = daoConfigMap.get(PointOrderDao.class).clone();
        pointOrderDaoConfig.initIdentityScope(type);

        employeeEntityDaoConfig = daoConfigMap.get(EmployeeEntityDao.class).clone();
        employeeEntityDaoConfig.initIdentityScope(type);

        posOrderDetailDaoConfig = daoConfigMap.get(PosOrderDetailDao.class).clone();
        posOrderDetailDaoConfig.initIdentityScope(type);

        paymentDaoConfig = daoConfigMap.get(PaymentDao.class).clone();
        paymentDaoConfig.initIdentityScope(type);

        posOrderDaoConfig = daoConfigMap.get(PosOrderDao.class).clone();
        posOrderDaoConfig.initIdentityScope(type);

        posOrderKitDetailDaoConfig = daoConfigMap.get(PosOrderKitDetailDao.class).clone();
        posOrderKitDetailDaoConfig.initIdentityScope(type);

        systemPrintDaoConfig = daoConfigMap.get(SystemPrintDao.class).clone();
        systemPrintDaoConfig.initIdentityScope(type);

        currentUserDaoConfig = daoConfigMap.get(CurrentUserDao.class).clone();
        currentUserDaoConfig.initIdentityScope(type);

        icCardMessageDaoConfig = daoConfigMap.get(IcCardMessageDao.class).clone();
        icCardMessageDaoConfig.initIdentityScope(type);

        deskOperatingParametersDaoConfig = daoConfigMap.get(DeskOperatingParametersDao.class).clone();
        deskOperatingParametersDaoConfig.initIdentityScope(type);

        itemCategoryDaoConfig = daoConfigMap.get(ItemCategoryDao.class).clone();
        itemCategoryDaoConfig.initIdentityScope(type);

        posItemKitDaoConfig = daoConfigMap.get(PosItemKitDao.class).clone();
        posItemKitDaoConfig.initIdentityScope(type);

        policyPresentDetailDaoConfig = daoConfigMap.get(PolicyPresentDetailDao.class).clone();
        policyPresentDetailDaoConfig.initIdentityScope(type);

        clientPointDaoConfig = daoConfigMap.get(ClientPointDao.class).clone();
        clientPointDaoConfig.initIdentityScope(type);

        branchResourceDaoConfig = daoConfigMap.get(BranchResourceDao.class).clone();
        branchResourceDaoConfig.initIdentityScope(type);

        posCarryLogDaoConfig = daoConfigMap.get(PosCarryLogDao.class).clone();
        posCarryLogDaoConfig.initIdentityScope(type);

        userRoleDaoConfig = daoConfigMap.get(UserRoleDao.class).clone();
        userRoleDaoConfig.initIdentityScope(type);

        policyDiscountDetailDaoConfig = daoConfigMap.get(PolicyDiscountDetailDao.class).clone();
        policyDiscountDetailDaoConfig.initIdentityScope(type);

        bookResourceDaoConfig = daoConfigMap.get(BookResourceDao.class).clone();
        bookResourceDaoConfig.initIdentityScope(type);

        posItemTerminalDaoConfig = daoConfigMap.get(PosItemTerminalDao.class).clone();
        posItemTerminalDaoConfig.initIdentityScope(type);

        posItemDaoConfig = daoConfigMap.get(PosItemDao.class).clone();
        posItemDaoConfig.initIdentityScope(type);

        bottomMenuDaoConfig = daoConfigMap.get(BottomMenuDao.class).clone();
        bottomMenuDaoConfig.initIdentityScope(type);

        policyQuantityDaoConfig = daoConfigMap.get(PolicyQuantityDao.class).clone();
        policyQuantityDaoConfig.initIdentityScope(type);

        appUserDaoConfig = daoConfigMap.get(AppUserDao.class).clone();
        appUserDaoConfig.initIdentityScope(type);

        payStyleToCashBankDaoConfig = daoConfigMap.get(PayStyleToCashBankDao.class).clone();
        payStyleToCashBankDaoConfig.initIdentityScope(type);

        vipSendCardDaoConfig = daoConfigMap.get(VipSendCardDao.class).clone();
        vipSendCardDaoConfig.initIdentityScope(type);

        relatCardDaoConfig = daoConfigMap.get(RelatCardDao.class).clone();
        relatCardDaoConfig.initIdentityScope(type);

        tableMd5EntityDaoConfig = daoConfigMap.get(TableMd5EntityDao.class).clone();
        tableMd5EntityDaoConfig.initIdentityScope(type);

        pointOrderDetialDaoConfig = daoConfigMap.get(PointOrderDetialDao.class).clone();
        pointOrderDetialDaoConfig.initIdentityScope(type);

        inventoryDaoConfig = daoConfigMap.get(InventoryDao.class).clone();
        inventoryDaoConfig.initIdentityScope(type);

        branchGroupDaoConfig = daoConfigMap.get(BranchGroupDao.class).clone();
        branchGroupDaoConfig.initIdentityScope(type);

        measureUnitItemDaoConfig = daoConfigMap.get(MeasureUnitItemDao.class).clone();
        measureUnitItemDaoConfig.initIdentityScope(type);

        fmPosOrderDaoConfig = daoConfigMap.get(FmPosOrderDao.class).clone();
        fmPosOrderDaoConfig.initIdentityScope(type);

        rolePrivilegeNewDaoConfig = daoConfigMap.get(RolePrivilegeNewDao.class).clone();
        rolePrivilegeNewDaoConfig.initIdentityScope(type);

        amountPayDaoConfig = daoConfigMap.get(AmountPayDao.class).clone();
        amountPayDaoConfig.initIdentityScope(type);

        pointPolicyDetailDaoConfig = daoConfigMap.get(PointPolicyDetailDao.class).clone();
        pointPolicyDetailDaoConfig.initIdentityScope(type);

        replaceCardDaoConfig = daoConfigMap.get(ReplaceCardDao.class).clone();
        replaceCardDaoConfig.initIdentityScope(type);

        fmPosOrderDetailDaoConfig = daoConfigMap.get(FmPosOrderDetailDao.class).clone();
        fmPosOrderDetailDaoConfig.initIdentityScope(type);

        marketActionDaoConfig = daoConfigMap.get(MarketActionDao.class).clone();
        marketActionDaoConfig.initIdentityScope(type);

        policyPromotionDetailDaoConfig = daoConfigMap.get(PolicyPromotionDetailDao.class).clone();
        policyPromotionDetailDaoConfig.initIdentityScope(type);

        posItemNewNongMaoDaoConfig = daoConfigMap.get(PosItemNewNongMaoDao.class).clone();
        posItemNewNongMaoDaoConfig.initIdentityScope(type);

        branchMerchantDaoConfig = daoConfigMap.get(BranchMerchantDao.class).clone();
        branchMerchantDaoConfig.initIdentityScope(type);

        policyPresentDaoConfig = daoConfigMap.get(PolicyPresentDao.class).clone();
        policyPresentDaoConfig.initIdentityScope(type);

        cardChangeDaoConfig = daoConfigMap.get(CardChangeDao.class).clone();
        cardChangeDaoConfig.initIdentityScope(type);

        posItemGradeDaoConfig = daoConfigMap.get(PosItemGradeDao.class).clone();
        posItemGradeDaoConfig.initIdentityScope(type);

        shiftTableDaoConfig = daoConfigMap.get(ShiftTableDao.class).clone();
        shiftTableDaoConfig.initIdentityScope(type);

        changeGoodsMenuDaoConfig = daoConfigMap.get(ChangeGoodsMenuDao.class).clone();
        changeGoodsMenuDaoConfig.initIdentityScope(type);

        branchRegionDaoConfig = daoConfigMap.get(BranchRegionDao.class).clone();
        branchRegionDaoConfig.initIdentityScope(type);

        ticketSendDetailDaoConfig = daoConfigMap.get(TicketSendDetailDao.class).clone();
        ticketSendDetailDaoConfig.initIdentityScope(type);

        accountBankDaoConfig = daoConfigMap.get(AccountBankDao.class).clone();
        accountBankDaoConfig.initIdentityScope(type);

        keyGeneratorBizdayDaoConfig = daoConfigMap.get(KeyGeneratorBizdayDao.class).clone();
        keyGeneratorBizdayDaoConfig.initIdentityScope(type);

        managementTemplateDaoConfig = daoConfigMap.get(ManagementTemplateDao.class).clone();
        managementTemplateDaoConfig.initIdentityScope(type);

        vipCRMLevelDetailDaoConfig = daoConfigMap.get(VipCRMLevelDetailDao.class).clone();
        vipCRMLevelDetailDaoConfig.initIdentityScope(type);

        loginDaoConfig = daoConfigMap.get(LoginDao.class).clone();
        loginDaoConfig.initIdentityScope(type);

        privilegeResourceNewDaoConfig = daoConfigMap.get(PrivilegeResourceNewDao.class).clone();
        privilegeResourceNewDaoConfig.initIdentityScope(type);

        cardDepositFailedDaoConfig = daoConfigMap.get(CardDepositFailedDao.class).clone();
        cardDepositFailedDaoConfig.initIdentityScope(type);

        branchsBeanDaoConfig = daoConfigMap.get(BranchsBeanDao.class).clone();
        branchsBeanDaoConfig.initIdentityScope(type);

        vipCrmAmaLevelDaoConfig = daoConfigMap.get(VipCrmAmaLevelDao.class).clone();
        vipCrmAmaLevelDaoConfig.initIdentityScope(type);

        pointRuleDaoConfig = daoConfigMap.get(PointRuleDao.class).clone();
        pointRuleDaoConfig.initIdentityScope(type);

        policyDiscountDaoConfig = daoConfigMap.get(PolicyDiscountDao.class).clone();
        policyDiscountDaoConfig.initIdentityScope(type);

        branchDaoConfig = daoConfigMap.get(BranchDao.class).clone();
        branchDaoConfig.initIdentityScope(type);

        itemBarDaoConfig = daoConfigMap.get(ItemBarDao.class).clone();
        itemBarDaoConfig.initIdentityScope(type);

        storeHouseDaoConfig = daoConfigMap.get(StoreHouseDao.class).clone();
        storeHouseDaoConfig.initIdentityScope(type);

        demoEntityDaoConfig = daoConfigMap.get(DemoEntityDao.class).clone();
        demoEntityDaoConfig.initIdentityScope(type);

        policyQuantityDetailDaoConfig = daoConfigMap.get(PolicyQuantityDetailDao.class).clone();
        policyQuantityDetailDaoConfig.initIdentityScope(type);

        systemImageQrcodeDaoConfig = daoConfigMap.get(SystemImageQrcodeDao.class).clone();
        systemImageQrcodeDaoConfig.initIdentityScope(type);

        tableMd5DaoConfig = daoConfigMap.get(TableMd5Dao.class).clone();
        tableMd5DaoConfig.initIdentityScope(type);

        customerRegisterDaoConfig = daoConfigMap.get(CustomerRegisterDao.class).clone();
        customerRegisterDaoConfig.initIdentityScope(type);

        vipStrangeSuccessSendMoneyDaoConfig = daoConfigMap.get(VipStrangeSuccessSendMoneyDao.class).clone();
        vipStrangeSuccessSendMoneyDaoConfig.initIdentityScope(type);

        otherRevenueDaoConfig = daoConfigMap.get(OtherRevenueDao.class).clone();
        otherRevenueDaoConfig.initIdentityScope(type);

        vipConsumeDaoConfig = daoConfigMap.get(VipConsumeDao.class).clone();
        vipConsumeDaoConfig.initIdentityScope(type);

        stallDiscountDaoConfig = daoConfigMap.get(StallDiscountDao.class).clone();
        stallDiscountDaoConfig.initIdentityScope(type);

        stallDiscountDetailDaoConfig = daoConfigMap.get(StallDiscountDetailDao.class).clone();
        stallDiscountDetailDaoConfig.initIdentityScope(type);

        stallDaoConfig = daoConfigMap.get(StallDao.class).clone();
        stallDaoConfig.initIdentityScope(type);

        stallPromotionDaoConfig = daoConfigMap.get(StallPromotionDao.class).clone();
        stallPromotionDaoConfig.initIdentityScope(type);

        stallPromotionDetailDaoConfig = daoConfigMap.get(StallPromotionDetailDao.class).clone();
        stallPromotionDetailDaoConfig.initIdentityScope(type);

        merchantDaoConfig = daoConfigMap.get(MerchantDao.class).clone();
        merchantDaoConfig.initIdentityScope(type);

        stallMatrixDaoConfig = daoConfigMap.get(StallMatrixDao.class).clone();
        stallMatrixDaoConfig.initIdentityScope(type);

        categoryFindDaoConfig = daoConfigMap.get(CategoryFindDao.class).clone();
        categoryFindDaoConfig.initIdentityScope(type);

        vipIcInitDaoConfig = daoConfigMap.get(VipIcInitDao.class).clone();
        vipIcInitDaoConfig.initIdentityScope(type);

        systemRoleDaoConfig = daoConfigMap.get(SystemRoleDao.class).clone();
        systemRoleDaoConfig.initIdentityScope(type);

        cardDepositDaoConfig = daoConfigMap.get(CardDepositDao.class).clone();
        cardDepositDaoConfig.initIdentityScope(type);

        cardTypeParamDaoConfig = daoConfigMap.get(CardTypeParamDao.class).clone();
        cardTypeParamDaoConfig.initIdentityScope(type);

        vipLevelPointRuleDaoConfig = daoConfigMap.get(VipLevelPointRuleDao.class).clone();
        vipLevelPointRuleDaoConfig.initIdentityScope(type);

        marketActionDetailDaoConfig = daoConfigMap.get(MarketActionDetailDao.class).clone();
        marketActionDetailDaoConfig.initIdentityScope(type);

        pointPolicyDaoConfig = daoConfigMap.get(PointPolicyDao.class).clone();
        pointPolicyDaoConfig.initIdentityScope(type);

        printOrderUsingDaoConfig = daoConfigMap.get(PrintOrderUsingDao.class).clone();
        printOrderUsingDaoConfig.initIdentityScope(type);

        posMachineDaoConfig = daoConfigMap.get(PosMachineDao.class).clone();
        posMachineDaoConfig.initIdentityScope(type);

        aggregationDaoConfig = daoConfigMap.get(AggregationDao.class).clone();
        aggregationDaoConfig.initIdentityScope(type);

        vipCrmPointRateDaoConfig = daoConfigMap.get(VipCrmPointRateDao.class).clone();
        vipCrmPointRateDaoConfig.initIdentityScope(type);

        policyMoneyDetailDaoConfig = daoConfigMap.get(PolicyMoneyDetailDao.class).clone();
        policyMoneyDetailDaoConfig.initIdentityScope(type);

        measureUnitDaoConfig = daoConfigMap.get(MeasureUnitDao.class).clone();
        measureUnitDaoConfig.initIdentityScope(type);

        attachedScreenDaoConfig = daoConfigMap.get(AttachedScreenDao.class).clone();
        attachedScreenDaoConfig.initIdentityScope(type);

        systemBookDaoConfig = daoConfigMap.get(SystemBookDao.class).clone();
        systemBookDaoConfig.initIdentityScope(type);

        fmPaymentDaoConfig = daoConfigMap.get(FmPaymentDao.class).clone();
        fmPaymentDaoConfig.initIdentityScope(type);

        posItemGradeTerminalDaoConfig = daoConfigMap.get(PosItemGradeTerminalDao.class).clone();
        posItemGradeTerminalDaoConfig.initIdentityScope(type);

        policyMoneyDaoConfig = daoConfigMap.get(PolicyMoneyDao.class).clone();
        policyMoneyDaoConfig.initIdentityScope(type);

        branchMessageDaoConfig = daoConfigMap.get(BranchMessageDao.class).clone();
        branchMessageDaoConfig.initIdentityScope(type);

        employeeDaoConfig = daoConfigMap.get(EmployeeDao.class).clone();
        employeeDaoConfig.initIdentityScope(type);

        vipCrmFeeDaoConfig = daoConfigMap.get(VipCrmFeeDao.class).clone();
        vipCrmFeeDaoConfig.initIdentityScope(type);

        managementTemplateDetailDaoConfig = daoConfigMap.get(ManagementTemplateDetailDao.class).clone();
        managementTemplateDetailDaoConfig.initIdentityScope(type);

        vipCRMLevelDaoConfig = daoConfigMap.get(VipCRMLevelDao.class).clone();
        vipCRMLevelDaoConfig.initIdentityScope(type);

        yunServiceDaysDaoConfig = daoConfigMap.get(YunServiceDaysDao.class).clone();
        yunServiceDaysDaoConfig.initIdentityScope(type);

        shiftTablePaymentDao = new ShiftTablePaymentDao(shiftTablePaymentDaoConfig, this);
        policyPromotionDao = new PolicyPromotionDao(policyPromotionDaoConfig, this);
        pointOrderDao = new PointOrderDao(pointOrderDaoConfig, this);
        employeeEntityDao = new EmployeeEntityDao(employeeEntityDaoConfig, this);
        posOrderDetailDao = new PosOrderDetailDao(posOrderDetailDaoConfig, this);
        paymentDao = new PaymentDao(paymentDaoConfig, this);
        posOrderDao = new PosOrderDao(posOrderDaoConfig, this);
        posOrderKitDetailDao = new PosOrderKitDetailDao(posOrderKitDetailDaoConfig, this);
        systemPrintDao = new SystemPrintDao(systemPrintDaoConfig, this);
        currentUserDao = new CurrentUserDao(currentUserDaoConfig, this);
        icCardMessageDao = new IcCardMessageDao(icCardMessageDaoConfig, this);
        deskOperatingParametersDao = new DeskOperatingParametersDao(deskOperatingParametersDaoConfig, this);
        itemCategoryDao = new ItemCategoryDao(itemCategoryDaoConfig, this);
        posItemKitDao = new PosItemKitDao(posItemKitDaoConfig, this);
        policyPresentDetailDao = new PolicyPresentDetailDao(policyPresentDetailDaoConfig, this);
        clientPointDao = new ClientPointDao(clientPointDaoConfig, this);
        branchResourceDao = new BranchResourceDao(branchResourceDaoConfig, this);
        posCarryLogDao = new PosCarryLogDao(posCarryLogDaoConfig, this);
        userRoleDao = new UserRoleDao(userRoleDaoConfig, this);
        policyDiscountDetailDao = new PolicyDiscountDetailDao(policyDiscountDetailDaoConfig, this);
        bookResourceDao = new BookResourceDao(bookResourceDaoConfig, this);
        posItemTerminalDao = new PosItemTerminalDao(posItemTerminalDaoConfig, this);
        posItemDao = new PosItemDao(posItemDaoConfig, this);
        bottomMenuDao = new BottomMenuDao(bottomMenuDaoConfig, this);
        policyQuantityDao = new PolicyQuantityDao(policyQuantityDaoConfig, this);
        appUserDao = new AppUserDao(appUserDaoConfig, this);
        payStyleToCashBankDao = new PayStyleToCashBankDao(payStyleToCashBankDaoConfig, this);
        vipSendCardDao = new VipSendCardDao(vipSendCardDaoConfig, this);
        relatCardDao = new RelatCardDao(relatCardDaoConfig, this);
        tableMd5EntityDao = new TableMd5EntityDao(tableMd5EntityDaoConfig, this);
        pointOrderDetialDao = new PointOrderDetialDao(pointOrderDetialDaoConfig, this);
        inventoryDao = new InventoryDao(inventoryDaoConfig, this);
        branchGroupDao = new BranchGroupDao(branchGroupDaoConfig, this);
        measureUnitItemDao = new MeasureUnitItemDao(measureUnitItemDaoConfig, this);
        fmPosOrderDao = new FmPosOrderDao(fmPosOrderDaoConfig, this);
        rolePrivilegeNewDao = new RolePrivilegeNewDao(rolePrivilegeNewDaoConfig, this);
        amountPayDao = new AmountPayDao(amountPayDaoConfig, this);
        pointPolicyDetailDao = new PointPolicyDetailDao(pointPolicyDetailDaoConfig, this);
        replaceCardDao = new ReplaceCardDao(replaceCardDaoConfig, this);
        fmPosOrderDetailDao = new FmPosOrderDetailDao(fmPosOrderDetailDaoConfig, this);
        marketActionDao = new MarketActionDao(marketActionDaoConfig, this);
        policyPromotionDetailDao = new PolicyPromotionDetailDao(policyPromotionDetailDaoConfig, this);
        posItemNewNongMaoDao = new PosItemNewNongMaoDao(posItemNewNongMaoDaoConfig, this);
        branchMerchantDao = new BranchMerchantDao(branchMerchantDaoConfig, this);
        policyPresentDao = new PolicyPresentDao(policyPresentDaoConfig, this);
        cardChangeDao = new CardChangeDao(cardChangeDaoConfig, this);
        posItemGradeDao = new PosItemGradeDao(posItemGradeDaoConfig, this);
        shiftTableDao = new ShiftTableDao(shiftTableDaoConfig, this);
        changeGoodsMenuDao = new ChangeGoodsMenuDao(changeGoodsMenuDaoConfig, this);
        branchRegionDao = new BranchRegionDao(branchRegionDaoConfig, this);
        ticketSendDetailDao = new TicketSendDetailDao(ticketSendDetailDaoConfig, this);
        accountBankDao = new AccountBankDao(accountBankDaoConfig, this);
        keyGeneratorBizdayDao = new KeyGeneratorBizdayDao(keyGeneratorBizdayDaoConfig, this);
        managementTemplateDao = new ManagementTemplateDao(managementTemplateDaoConfig, this);
        vipCRMLevelDetailDao = new VipCRMLevelDetailDao(vipCRMLevelDetailDaoConfig, this);
        loginDao = new LoginDao(loginDaoConfig, this);
        privilegeResourceNewDao = new PrivilegeResourceNewDao(privilegeResourceNewDaoConfig, this);
        cardDepositFailedDao = new CardDepositFailedDao(cardDepositFailedDaoConfig, this);
        branchsBeanDao = new BranchsBeanDao(branchsBeanDaoConfig, this);
        vipCrmAmaLevelDao = new VipCrmAmaLevelDao(vipCrmAmaLevelDaoConfig, this);
        pointRuleDao = new PointRuleDao(pointRuleDaoConfig, this);
        policyDiscountDao = new PolicyDiscountDao(policyDiscountDaoConfig, this);
        branchDao = new BranchDao(branchDaoConfig, this);
        itemBarDao = new ItemBarDao(itemBarDaoConfig, this);
        storeHouseDao = new StoreHouseDao(storeHouseDaoConfig, this);
        demoEntityDao = new DemoEntityDao(demoEntityDaoConfig, this);
        policyQuantityDetailDao = new PolicyQuantityDetailDao(policyQuantityDetailDaoConfig, this);
        systemImageQrcodeDao = new SystemImageQrcodeDao(systemImageQrcodeDaoConfig, this);
        tableMd5Dao = new TableMd5Dao(tableMd5DaoConfig, this);
        customerRegisterDao = new CustomerRegisterDao(customerRegisterDaoConfig, this);
        vipStrangeSuccessSendMoneyDao = new VipStrangeSuccessSendMoneyDao(vipStrangeSuccessSendMoneyDaoConfig, this);
        otherRevenueDao = new OtherRevenueDao(otherRevenueDaoConfig, this);
        vipConsumeDao = new VipConsumeDao(vipConsumeDaoConfig, this);
        stallDiscountDao = new StallDiscountDao(stallDiscountDaoConfig, this);
        stallDiscountDetailDao = new StallDiscountDetailDao(stallDiscountDetailDaoConfig, this);
        stallDao = new StallDao(stallDaoConfig, this);
        stallPromotionDao = new StallPromotionDao(stallPromotionDaoConfig, this);
        stallPromotionDetailDao = new StallPromotionDetailDao(stallPromotionDetailDaoConfig, this);
        merchantDao = new MerchantDao(merchantDaoConfig, this);
        stallMatrixDao = new StallMatrixDao(stallMatrixDaoConfig, this);
        categoryFindDao = new CategoryFindDao(categoryFindDaoConfig, this);
        vipIcInitDao = new VipIcInitDao(vipIcInitDaoConfig, this);
        systemRoleDao = new SystemRoleDao(systemRoleDaoConfig, this);
        cardDepositDao = new CardDepositDao(cardDepositDaoConfig, this);
        cardTypeParamDao = new CardTypeParamDao(cardTypeParamDaoConfig, this);
        vipLevelPointRuleDao = new VipLevelPointRuleDao(vipLevelPointRuleDaoConfig, this);
        marketActionDetailDao = new MarketActionDetailDao(marketActionDetailDaoConfig, this);
        pointPolicyDao = new PointPolicyDao(pointPolicyDaoConfig, this);
        printOrderUsingDao = new PrintOrderUsingDao(printOrderUsingDaoConfig, this);
        posMachineDao = new PosMachineDao(posMachineDaoConfig, this);
        aggregationDao = new AggregationDao(aggregationDaoConfig, this);
        vipCrmPointRateDao = new VipCrmPointRateDao(vipCrmPointRateDaoConfig, this);
        policyMoneyDetailDao = new PolicyMoneyDetailDao(policyMoneyDetailDaoConfig, this);
        measureUnitDao = new MeasureUnitDao(measureUnitDaoConfig, this);
        attachedScreenDao = new AttachedScreenDao(attachedScreenDaoConfig, this);
        systemBookDao = new SystemBookDao(systemBookDaoConfig, this);
        fmPaymentDao = new FmPaymentDao(fmPaymentDaoConfig, this);
        posItemGradeTerminalDao = new PosItemGradeTerminalDao(posItemGradeTerminalDaoConfig, this);
        policyMoneyDao = new PolicyMoneyDao(policyMoneyDaoConfig, this);
        branchMessageDao = new BranchMessageDao(branchMessageDaoConfig, this);
        employeeDao = new EmployeeDao(employeeDaoConfig, this);
        vipCrmFeeDao = new VipCrmFeeDao(vipCrmFeeDaoConfig, this);
        managementTemplateDetailDao = new ManagementTemplateDetailDao(managementTemplateDetailDaoConfig, this);
        vipCRMLevelDao = new VipCRMLevelDao(vipCRMLevelDaoConfig, this);
        yunServiceDaysDao = new YunServiceDaysDao(yunServiceDaysDaoConfig, this);

        registerDao(ShiftTablePayment.class, shiftTablePaymentDao);
        registerDao(PolicyPromotion.class, policyPromotionDao);
        registerDao(PointOrder.class, pointOrderDao);
        registerDao(EmployeeEntity.class, employeeEntityDao);
        registerDao(PosOrderDetail.class, posOrderDetailDao);
        registerDao(Payment.class, paymentDao);
        registerDao(PosOrder.class, posOrderDao);
        registerDao(PosOrderKitDetail.class, posOrderKitDetailDao);
        registerDao(SystemPrint.class, systemPrintDao);
        registerDao(CurrentUser.class, currentUserDao);
        registerDao(IcCardMessage.class, icCardMessageDao);
        registerDao(DeskOperatingParameters.class, deskOperatingParametersDao);
        registerDao(ItemCategory.class, itemCategoryDao);
        registerDao(PosItemKit.class, posItemKitDao);
        registerDao(PolicyPresentDetail.class, policyPresentDetailDao);
        registerDao(ClientPoint.class, clientPointDao);
        registerDao(BranchResource.class, branchResourceDao);
        registerDao(PosCarryLog.class, posCarryLogDao);
        registerDao(UserRole.class, userRoleDao);
        registerDao(PolicyDiscountDetail.class, policyDiscountDetailDao);
        registerDao(BookResource.class, bookResourceDao);
        registerDao(PosItemTerminal.class, posItemTerminalDao);
        registerDao(PosItem.class, posItemDao);
        registerDao(BottomMenu.class, bottomMenuDao);
        registerDao(PolicyQuantity.class, policyQuantityDao);
        registerDao(AppUser.class, appUserDao);
        registerDao(PayStyleToCashBank.class, payStyleToCashBankDao);
        registerDao(VipSendCard.class, vipSendCardDao);
        registerDao(RelatCard.class, relatCardDao);
        registerDao(TableMd5Entity.class, tableMd5EntityDao);
        registerDao(PointOrderDetial.class, pointOrderDetialDao);
        registerDao(Inventory.class, inventoryDao);
        registerDao(BranchGroup.class, branchGroupDao);
        registerDao(MeasureUnitItem.class, measureUnitItemDao);
        registerDao(FmPosOrder.class, fmPosOrderDao);
        registerDao(RolePrivilegeNew.class, rolePrivilegeNewDao);
        registerDao(AmountPay.class, amountPayDao);
        registerDao(PointPolicyDetail.class, pointPolicyDetailDao);
        registerDao(ReplaceCard.class, replaceCardDao);
        registerDao(FmPosOrderDetail.class, fmPosOrderDetailDao);
        registerDao(MarketAction.class, marketActionDao);
        registerDao(PolicyPromotionDetail.class, policyPromotionDetailDao);
        registerDao(PosItemNewNongMao.class, posItemNewNongMaoDao);
        registerDao(BranchMerchant.class, branchMerchantDao);
        registerDao(PolicyPresent.class, policyPresentDao);
        registerDao(CardChange.class, cardChangeDao);
        registerDao(PosItemGrade.class, posItemGradeDao);
        registerDao(ShiftTable.class, shiftTableDao);
        registerDao(ChangeGoodsMenu.class, changeGoodsMenuDao);
        registerDao(BranchRegion.class, branchRegionDao);
        registerDao(TicketSendDetail.class, ticketSendDetailDao);
        registerDao(AccountBank.class, accountBankDao);
        registerDao(KeyGeneratorBizday.class, keyGeneratorBizdayDao);
        registerDao(ManagementTemplate.class, managementTemplateDao);
        registerDao(VipCRMLevelDetail.class, vipCRMLevelDetailDao);
        registerDao(Login.class, loginDao);
        registerDao(PrivilegeResourceNew.class, privilegeResourceNewDao);
        registerDao(CardDepositFailed.class, cardDepositFailedDao);
        registerDao(BranchsBean.class, branchsBeanDao);
        registerDao(VipCrmAmaLevel.class, vipCrmAmaLevelDao);
        registerDao(PointRule.class, pointRuleDao);
        registerDao(PolicyDiscount.class, policyDiscountDao);
        registerDao(Branch.class, branchDao);
        registerDao(ItemBar.class, itemBarDao);
        registerDao(StoreHouse.class, storeHouseDao);
        registerDao(DemoEntity.class, demoEntityDao);
        registerDao(PolicyQuantityDetail.class, policyQuantityDetailDao);
        registerDao(SystemImageQrcode.class, systemImageQrcodeDao);
        registerDao(TableMd5.class, tableMd5Dao);
        registerDao(CustomerRegister.class, customerRegisterDao);
        registerDao(VipStrangeSuccessSendMoney.class, vipStrangeSuccessSendMoneyDao);
        registerDao(OtherRevenue.class, otherRevenueDao);
        registerDao(VipConsume.class, vipConsumeDao);
        registerDao(StallDiscount.class, stallDiscountDao);
        registerDao(StallDiscountDetail.class, stallDiscountDetailDao);
        registerDao(Stall.class, stallDao);
        registerDao(StallPromotion.class, stallPromotionDao);
        registerDao(StallPromotionDetail.class, stallPromotionDetailDao);
        registerDao(Merchant.class, merchantDao);
        registerDao(StallMatrix.class, stallMatrixDao);
        registerDao(CategoryFind.class, categoryFindDao);
        registerDao(VipIcInit.class, vipIcInitDao);
        registerDao(SystemRole.class, systemRoleDao);
        registerDao(CardDeposit.class, cardDepositDao);
        registerDao(CardTypeParam.class, cardTypeParamDao);
        registerDao(VipLevelPointRule.class, vipLevelPointRuleDao);
        registerDao(MarketActionDetail.class, marketActionDetailDao);
        registerDao(PointPolicy.class, pointPolicyDao);
        registerDao(PrintOrderUsing.class, printOrderUsingDao);
        registerDao(PosMachine.class, posMachineDao);
        registerDao(Aggregation.class, aggregationDao);
        registerDao(VipCrmPointRate.class, vipCrmPointRateDao);
        registerDao(PolicyMoneyDetail.class, policyMoneyDetailDao);
        registerDao(MeasureUnit.class, measureUnitDao);
        registerDao(AttachedScreen.class, attachedScreenDao);
        registerDao(SystemBook.class, systemBookDao);
        registerDao(FmPayment.class, fmPaymentDao);
        registerDao(PosItemGradeTerminal.class, posItemGradeTerminalDao);
        registerDao(PolicyMoney.class, policyMoneyDao);
        registerDao(BranchMessage.class, branchMessageDao);
        registerDao(Employee.class, employeeDao);
        registerDao(VipCrmFee.class, vipCrmFeeDao);
        registerDao(ManagementTemplateDetail.class, managementTemplateDetailDao);
        registerDao(VipCRMLevel.class, vipCRMLevelDao);
        registerDao(YunServiceDays.class, yunServiceDaysDao);
    }
    
    public void clear() {
        shiftTablePaymentDaoConfig.clearIdentityScope();
        policyPromotionDaoConfig.clearIdentityScope();
        pointOrderDaoConfig.clearIdentityScope();
        employeeEntityDaoConfig.clearIdentityScope();
        posOrderDetailDaoConfig.clearIdentityScope();
        paymentDaoConfig.clearIdentityScope();
        posOrderDaoConfig.clearIdentityScope();
        posOrderKitDetailDaoConfig.clearIdentityScope();
        systemPrintDaoConfig.clearIdentityScope();
        currentUserDaoConfig.clearIdentityScope();
        icCardMessageDaoConfig.clearIdentityScope();
        deskOperatingParametersDaoConfig.clearIdentityScope();
        itemCategoryDaoConfig.clearIdentityScope();
        posItemKitDaoConfig.clearIdentityScope();
        policyPresentDetailDaoConfig.clearIdentityScope();
        clientPointDaoConfig.clearIdentityScope();
        branchResourceDaoConfig.clearIdentityScope();
        posCarryLogDaoConfig.clearIdentityScope();
        userRoleDaoConfig.clearIdentityScope();
        policyDiscountDetailDaoConfig.clearIdentityScope();
        bookResourceDaoConfig.clearIdentityScope();
        posItemTerminalDaoConfig.clearIdentityScope();
        posItemDaoConfig.clearIdentityScope();
        bottomMenuDaoConfig.clearIdentityScope();
        policyQuantityDaoConfig.clearIdentityScope();
        appUserDaoConfig.clearIdentityScope();
        payStyleToCashBankDaoConfig.clearIdentityScope();
        vipSendCardDaoConfig.clearIdentityScope();
        relatCardDaoConfig.clearIdentityScope();
        tableMd5EntityDaoConfig.clearIdentityScope();
        pointOrderDetialDaoConfig.clearIdentityScope();
        inventoryDaoConfig.clearIdentityScope();
        branchGroupDaoConfig.clearIdentityScope();
        measureUnitItemDaoConfig.clearIdentityScope();
        fmPosOrderDaoConfig.clearIdentityScope();
        rolePrivilegeNewDaoConfig.clearIdentityScope();
        amountPayDaoConfig.clearIdentityScope();
        pointPolicyDetailDaoConfig.clearIdentityScope();
        replaceCardDaoConfig.clearIdentityScope();
        fmPosOrderDetailDaoConfig.clearIdentityScope();
        marketActionDaoConfig.clearIdentityScope();
        policyPromotionDetailDaoConfig.clearIdentityScope();
        posItemNewNongMaoDaoConfig.clearIdentityScope();
        branchMerchantDaoConfig.clearIdentityScope();
        policyPresentDaoConfig.clearIdentityScope();
        cardChangeDaoConfig.clearIdentityScope();
        posItemGradeDaoConfig.clearIdentityScope();
        shiftTableDaoConfig.clearIdentityScope();
        changeGoodsMenuDaoConfig.clearIdentityScope();
        branchRegionDaoConfig.clearIdentityScope();
        ticketSendDetailDaoConfig.clearIdentityScope();
        accountBankDaoConfig.clearIdentityScope();
        keyGeneratorBizdayDaoConfig.clearIdentityScope();
        managementTemplateDaoConfig.clearIdentityScope();
        vipCRMLevelDetailDaoConfig.clearIdentityScope();
        loginDaoConfig.clearIdentityScope();
        privilegeResourceNewDaoConfig.clearIdentityScope();
        cardDepositFailedDaoConfig.clearIdentityScope();
        branchsBeanDaoConfig.clearIdentityScope();
        vipCrmAmaLevelDaoConfig.clearIdentityScope();
        pointRuleDaoConfig.clearIdentityScope();
        policyDiscountDaoConfig.clearIdentityScope();
        branchDaoConfig.clearIdentityScope();
        itemBarDaoConfig.clearIdentityScope();
        storeHouseDaoConfig.clearIdentityScope();
        demoEntityDaoConfig.clearIdentityScope();
        policyQuantityDetailDaoConfig.clearIdentityScope();
        systemImageQrcodeDaoConfig.clearIdentityScope();
        tableMd5DaoConfig.clearIdentityScope();
        customerRegisterDaoConfig.clearIdentityScope();
        vipStrangeSuccessSendMoneyDaoConfig.clearIdentityScope();
        otherRevenueDaoConfig.clearIdentityScope();
        vipConsumeDaoConfig.clearIdentityScope();
        stallDiscountDaoConfig.clearIdentityScope();
        stallDiscountDetailDaoConfig.clearIdentityScope();
        stallDaoConfig.clearIdentityScope();
        stallPromotionDaoConfig.clearIdentityScope();
        stallPromotionDetailDaoConfig.clearIdentityScope();
        merchantDaoConfig.clearIdentityScope();
        stallMatrixDaoConfig.clearIdentityScope();
        categoryFindDaoConfig.clearIdentityScope();
        vipIcInitDaoConfig.clearIdentityScope();
        systemRoleDaoConfig.clearIdentityScope();
        cardDepositDaoConfig.clearIdentityScope();
        cardTypeParamDaoConfig.clearIdentityScope();
        vipLevelPointRuleDaoConfig.clearIdentityScope();
        marketActionDetailDaoConfig.clearIdentityScope();
        pointPolicyDaoConfig.clearIdentityScope();
        printOrderUsingDaoConfig.clearIdentityScope();
        posMachineDaoConfig.clearIdentityScope();
        aggregationDaoConfig.clearIdentityScope();
        vipCrmPointRateDaoConfig.clearIdentityScope();
        policyMoneyDetailDaoConfig.clearIdentityScope();
        measureUnitDaoConfig.clearIdentityScope();
        attachedScreenDaoConfig.clearIdentityScope();
        systemBookDaoConfig.clearIdentityScope();
        fmPaymentDaoConfig.clearIdentityScope();
        posItemGradeTerminalDaoConfig.clearIdentityScope();
        policyMoneyDaoConfig.clearIdentityScope();
        branchMessageDaoConfig.clearIdentityScope();
        employeeDaoConfig.clearIdentityScope();
        vipCrmFeeDaoConfig.clearIdentityScope();
        managementTemplateDetailDaoConfig.clearIdentityScope();
        vipCRMLevelDaoConfig.clearIdentityScope();
        yunServiceDaysDaoConfig.clearIdentityScope();
    }

    public ShiftTablePaymentDao getShiftTablePaymentDao() {
        return shiftTablePaymentDao;
    }

    public PolicyPromotionDao getPolicyPromotionDao() {
        return policyPromotionDao;
    }

    public PointOrderDao getPointOrderDao() {
        return pointOrderDao;
    }

    public EmployeeEntityDao getEmployeeEntityDao() {
        return employeeEntityDao;
    }

    public PosOrderDetailDao getPosOrderDetailDao() {
        return posOrderDetailDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public PosOrderDao getPosOrderDao() {
        return posOrderDao;
    }

    public PosOrderKitDetailDao getPosOrderKitDetailDao() {
        return posOrderKitDetailDao;
    }

    public SystemPrintDao getSystemPrintDao() {
        return systemPrintDao;
    }

    public CurrentUserDao getCurrentUserDao() {
        return currentUserDao;
    }

    public IcCardMessageDao getIcCardMessageDao() {
        return icCardMessageDao;
    }

    public DeskOperatingParametersDao getDeskOperatingParametersDao() {
        return deskOperatingParametersDao;
    }

    public ItemCategoryDao getItemCategoryDao() {
        return itemCategoryDao;
    }

    public PosItemKitDao getPosItemKitDao() {
        return posItemKitDao;
    }

    public PolicyPresentDetailDao getPolicyPresentDetailDao() {
        return policyPresentDetailDao;
    }

    public ClientPointDao getClientPointDao() {
        return clientPointDao;
    }

    public BranchResourceDao getBranchResourceDao() {
        return branchResourceDao;
    }

    public PosCarryLogDao getPosCarryLogDao() {
        return posCarryLogDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public PolicyDiscountDetailDao getPolicyDiscountDetailDao() {
        return policyDiscountDetailDao;
    }

    public BookResourceDao getBookResourceDao() {
        return bookResourceDao;
    }

    public PosItemTerminalDao getPosItemTerminalDao() {
        return posItemTerminalDao;
    }

    public PosItemDao getPosItemDao() {
        return posItemDao;
    }

    public BottomMenuDao getBottomMenuDao() {
        return bottomMenuDao;
    }

    public PolicyQuantityDao getPolicyQuantityDao() {
        return policyQuantityDao;
    }

    public AppUserDao getAppUserDao() {
        return appUserDao;
    }

    public PayStyleToCashBankDao getPayStyleToCashBankDao() {
        return payStyleToCashBankDao;
    }

    public VipSendCardDao getVipSendCardDao() {
        return vipSendCardDao;
    }

    public RelatCardDao getRelatCardDao() {
        return relatCardDao;
    }

    public TableMd5EntityDao getTableMd5EntityDao() {
        return tableMd5EntityDao;
    }

    public PointOrderDetialDao getPointOrderDetialDao() {
        return pointOrderDetialDao;
    }

    public InventoryDao getInventoryDao() {
        return inventoryDao;
    }

    public BranchGroupDao getBranchGroupDao() {
        return branchGroupDao;
    }

    public MeasureUnitItemDao getMeasureUnitItemDao() {
        return measureUnitItemDao;
    }

    public FmPosOrderDao getFmPosOrderDao() {
        return fmPosOrderDao;
    }

    public RolePrivilegeNewDao getRolePrivilegeNewDao() {
        return rolePrivilegeNewDao;
    }

    public AmountPayDao getAmountPayDao() {
        return amountPayDao;
    }

    public PointPolicyDetailDao getPointPolicyDetailDao() {
        return pointPolicyDetailDao;
    }

    public ReplaceCardDao getReplaceCardDao() {
        return replaceCardDao;
    }

    public FmPosOrderDetailDao getFmPosOrderDetailDao() {
        return fmPosOrderDetailDao;
    }

    public MarketActionDao getMarketActionDao() {
        return marketActionDao;
    }

    public PolicyPromotionDetailDao getPolicyPromotionDetailDao() {
        return policyPromotionDetailDao;
    }

    public PosItemNewNongMaoDao getPosItemNewNongMaoDao() {
        return posItemNewNongMaoDao;
    }

    public BranchMerchantDao getBranchMerchantDao() {
        return branchMerchantDao;
    }

    public PolicyPresentDao getPolicyPresentDao() {
        return policyPresentDao;
    }

    public CardChangeDao getCardChangeDao() {
        return cardChangeDao;
    }

    public PosItemGradeDao getPosItemGradeDao() {
        return posItemGradeDao;
    }

    public ShiftTableDao getShiftTableDao() {
        return shiftTableDao;
    }

    public ChangeGoodsMenuDao getChangeGoodsMenuDao() {
        return changeGoodsMenuDao;
    }

    public BranchRegionDao getBranchRegionDao() {
        return branchRegionDao;
    }

    public TicketSendDetailDao getTicketSendDetailDao() {
        return ticketSendDetailDao;
    }

    public AccountBankDao getAccountBankDao() {
        return accountBankDao;
    }

    public KeyGeneratorBizdayDao getKeyGeneratorBizdayDao() {
        return keyGeneratorBizdayDao;
    }

    public ManagementTemplateDao getManagementTemplateDao() {
        return managementTemplateDao;
    }

    public VipCRMLevelDetailDao getVipCRMLevelDetailDao() {
        return vipCRMLevelDetailDao;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public PrivilegeResourceNewDao getPrivilegeResourceNewDao() {
        return privilegeResourceNewDao;
    }

    public CardDepositFailedDao getCardDepositFailedDao() {
        return cardDepositFailedDao;
    }

    public BranchsBeanDao getBranchsBeanDao() {
        return branchsBeanDao;
    }

    public VipCrmAmaLevelDao getVipCrmAmaLevelDao() {
        return vipCrmAmaLevelDao;
    }

    public PointRuleDao getPointRuleDao() {
        return pointRuleDao;
    }

    public PolicyDiscountDao getPolicyDiscountDao() {
        return policyDiscountDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public ItemBarDao getItemBarDao() {
        return itemBarDao;
    }

    public StoreHouseDao getStoreHouseDao() {
        return storeHouseDao;
    }

    public DemoEntityDao getDemoEntityDao() {
        return demoEntityDao;
    }

    public PolicyQuantityDetailDao getPolicyQuantityDetailDao() {
        return policyQuantityDetailDao;
    }

    public SystemImageQrcodeDao getSystemImageQrcodeDao() {
        return systemImageQrcodeDao;
    }

    public TableMd5Dao getTableMd5Dao() {
        return tableMd5Dao;
    }

    public CustomerRegisterDao getCustomerRegisterDao() {
        return customerRegisterDao;
    }

    public VipStrangeSuccessSendMoneyDao getVipStrangeSuccessSendMoneyDao() {
        return vipStrangeSuccessSendMoneyDao;
    }

    public OtherRevenueDao getOtherRevenueDao() {
        return otherRevenueDao;
    }

    public VipConsumeDao getVipConsumeDao() {
        return vipConsumeDao;
    }

    public StallDiscountDao getStallDiscountDao() {
        return stallDiscountDao;
    }

    public StallDiscountDetailDao getStallDiscountDetailDao() {
        return stallDiscountDetailDao;
    }

    public StallDao getStallDao() {
        return stallDao;
    }

    public StallPromotionDao getStallPromotionDao() {
        return stallPromotionDao;
    }

    public StallPromotionDetailDao getStallPromotionDetailDao() {
        return stallPromotionDetailDao;
    }

    public MerchantDao getMerchantDao() {
        return merchantDao;
    }

    public StallMatrixDao getStallMatrixDao() {
        return stallMatrixDao;
    }

    public CategoryFindDao getCategoryFindDao() {
        return categoryFindDao;
    }

    public VipIcInitDao getVipIcInitDao() {
        return vipIcInitDao;
    }

    public SystemRoleDao getSystemRoleDao() {
        return systemRoleDao;
    }

    public CardDepositDao getCardDepositDao() {
        return cardDepositDao;
    }

    public CardTypeParamDao getCardTypeParamDao() {
        return cardTypeParamDao;
    }

    public VipLevelPointRuleDao getVipLevelPointRuleDao() {
        return vipLevelPointRuleDao;
    }

    public MarketActionDetailDao getMarketActionDetailDao() {
        return marketActionDetailDao;
    }

    public PointPolicyDao getPointPolicyDao() {
        return pointPolicyDao;
    }

    public PrintOrderUsingDao getPrintOrderUsingDao() {
        return printOrderUsingDao;
    }

    public PosMachineDao getPosMachineDao() {
        return posMachineDao;
    }

    public AggregationDao getAggregationDao() {
        return aggregationDao;
    }

    public VipCrmPointRateDao getVipCrmPointRateDao() {
        return vipCrmPointRateDao;
    }

    public PolicyMoneyDetailDao getPolicyMoneyDetailDao() {
        return policyMoneyDetailDao;
    }

    public MeasureUnitDao getMeasureUnitDao() {
        return measureUnitDao;
    }

    public AttachedScreenDao getAttachedScreenDao() {
        return attachedScreenDao;
    }

    public SystemBookDao getSystemBookDao() {
        return systemBookDao;
    }

    public FmPaymentDao getFmPaymentDao() {
        return fmPaymentDao;
    }

    public PosItemGradeTerminalDao getPosItemGradeTerminalDao() {
        return posItemGradeTerminalDao;
    }

    public PolicyMoneyDao getPolicyMoneyDao() {
        return policyMoneyDao;
    }

    public BranchMessageDao getBranchMessageDao() {
        return branchMessageDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public VipCrmFeeDao getVipCrmFeeDao() {
        return vipCrmFeeDao;
    }

    public ManagementTemplateDetailDao getManagementTemplateDetailDao() {
        return managementTemplateDetailDao;
    }

    public VipCRMLevelDao getVipCRMLevelDao() {
        return vipCRMLevelDao;
    }

    public YunServiceDaysDao getYunServiceDaysDao() {
        return yunServiceDaysDao;
    }

}
