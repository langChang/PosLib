package com.nhsoft.poslib.service.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nhsoft.poslib.entity.AccountBank;
import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.entity.AmountPay;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.AttachedScreen;
import com.nhsoft.poslib.entity.BookResource;
import com.nhsoft.poslib.entity.BottomMenu;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.entity.BranchMessage;
import com.nhsoft.poslib.entity.BranchRegion;
import com.nhsoft.poslib.entity.BranchResource;
import com.nhsoft.poslib.entity.BranchsBean;
import com.nhsoft.poslib.entity.CardChange;
import com.nhsoft.poslib.entity.CardDeposit;
import com.nhsoft.poslib.entity.CardDepositFailed;
import com.nhsoft.poslib.entity.CardTypeParam;
import com.nhsoft.poslib.entity.ChangeGoodsMenu;
import com.nhsoft.poslib.entity.ClientPoint;
import com.nhsoft.poslib.entity.CurrentUser;
import com.nhsoft.poslib.entity.CustomerRegister;
import com.nhsoft.poslib.entity.DemoEntity;
import com.nhsoft.poslib.entity.DeskOperatingParameters;
import com.nhsoft.poslib.entity.Employee;
import com.nhsoft.poslib.entity.EmployeeEntity;
import com.nhsoft.poslib.entity.IcCardMessage;
import com.nhsoft.poslib.entity.Inventory;
import com.nhsoft.poslib.entity.ItemBar;
import com.nhsoft.poslib.entity.ItemCategory;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.entity.ManagementTemplate;
import com.nhsoft.poslib.entity.ManagementTemplateDetail;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.MarketActionDetail;
import com.nhsoft.poslib.entity.MeasureUnit;
import com.nhsoft.poslib.entity.MeasureUnitItem;
import com.nhsoft.poslib.entity.order.Payment;
import com.nhsoft.poslib.entity.order.PosOrder;
import com.nhsoft.poslib.entity.order.PosOrderDetail;
import com.nhsoft.poslib.entity.order.PosOrderKitDetail;
import com.nhsoft.poslib.entity.OtherRevenue;
import com.nhsoft.poslib.entity.PayStyleToCashBank;
import com.nhsoft.poslib.entity.PointOrder;
import com.nhsoft.poslib.entity.PointOrderDetial;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PointPolicyDetail;
import com.nhsoft.poslib.entity.PointRule;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyDiscountDetail;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.PolicyMoneyDetail;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.PolicyPresentDetail;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyPromotionDetail;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PolicyQuantityDetail;
import com.nhsoft.poslib.entity.PosCarryLog;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosItemGrade;
import com.nhsoft.poslib.entity.PosItemGradeTerminal;
import com.nhsoft.poslib.entity.PosItemKit;
import com.nhsoft.poslib.entity.PosItemTerminal;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.PrintOrderUsing;
import com.nhsoft.poslib.entity.PrivilegeResourceNew;
import com.nhsoft.poslib.entity.RelatCard;
import com.nhsoft.poslib.entity.ReplaceCard;
import com.nhsoft.poslib.entity.RolePrivilegeNew;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.entity.ShiftTablePayment;
import com.nhsoft.poslib.entity.StoreHouse;
import com.nhsoft.poslib.entity.SystemBook;
import com.nhsoft.poslib.entity.SystemImageQrcode;
import com.nhsoft.poslib.entity.SystemPrint;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.entity.TableMd5;
import com.nhsoft.poslib.entity.TableMd5Entity;
import com.nhsoft.poslib.entity.TicketSendDetail;
import com.nhsoft.poslib.entity.UserRole;
import com.nhsoft.poslib.entity.VipConsume;
import com.nhsoft.poslib.entity.VipCrmAmaLevel;
import com.nhsoft.poslib.entity.VipCrmFee;
import com.nhsoft.poslib.entity.VipCRMLevel;
import com.nhsoft.poslib.entity.VipCRMLevelDetail;
import com.nhsoft.poslib.entity.VipCrmPointRate;
import com.nhsoft.poslib.entity.VipIcInit;
import com.nhsoft.poslib.entity.VipLevelPointRule;
import com.nhsoft.poslib.entity.VipSendCard;
import com.nhsoft.poslib.entity.VipStrangeSuccessSendMoney;
import com.nhsoft.poslib.model.YunServiceDays;

import com.nhsoft.poslib.service.greendao.AccountBankDao;
import com.nhsoft.poslib.service.greendao.AggregationDao;
import com.nhsoft.poslib.service.greendao.AmountPayDao;
import com.nhsoft.poslib.service.greendao.AppUserDao;
import com.nhsoft.poslib.service.greendao.AttachedScreenDao;
import com.nhsoft.poslib.service.greendao.BookResourceDao;
import com.nhsoft.poslib.service.greendao.BottomMenuDao;
import com.nhsoft.poslib.service.greendao.BranchDao;
import com.nhsoft.poslib.service.greendao.BranchGroupDao;
import com.nhsoft.poslib.service.greendao.BranchMessageDao;
import com.nhsoft.poslib.service.greendao.BranchRegionDao;
import com.nhsoft.poslib.service.greendao.BranchResourceDao;
import com.nhsoft.poslib.service.greendao.BranchsBeanDao;
import com.nhsoft.poslib.service.greendao.CardChangeDao;
import com.nhsoft.poslib.service.greendao.CardDepositDao;
import com.nhsoft.poslib.service.greendao.CardDepositFailedDao;
import com.nhsoft.poslib.service.greendao.CardTypeParamDao;
import com.nhsoft.poslib.service.greendao.ChangeGoodsMenuDao;
import com.nhsoft.poslib.service.greendao.ClientPointDao;
import com.nhsoft.poslib.service.greendao.CurrentUserDao;
import com.nhsoft.poslib.service.greendao.CustomerRegisterDao;
import com.nhsoft.poslib.service.greendao.DemoEntityDao;
import com.nhsoft.poslib.service.greendao.DeskOperatingParametersDao;
import com.nhsoft.poslib.service.greendao.EmployeeDao;
import com.nhsoft.poslib.service.greendao.EmployeeEntityDao;
import com.nhsoft.poslib.service.greendao.IcCardMessageDao;
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
import com.nhsoft.poslib.service.greendao.PaymentDao;
import com.nhsoft.poslib.service.greendao.PosOrderDao;
import com.nhsoft.poslib.service.greendao.PosOrderDetailDao;
import com.nhsoft.poslib.service.greendao.PosOrderKitDetailDao;
import com.nhsoft.poslib.service.greendao.OtherRevenueDao;
import com.nhsoft.poslib.service.greendao.PayStyleToCashBankDao;
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
import com.nhsoft.poslib.service.greendao.PolicyPromotionDetailDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDao;
import com.nhsoft.poslib.service.greendao.PolicyQuantityDetailDao;
import com.nhsoft.poslib.service.greendao.PosCarryLogDao;
import com.nhsoft.poslib.service.greendao.PosItemDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeDao;
import com.nhsoft.poslib.service.greendao.PosItemGradeTerminalDao;
import com.nhsoft.poslib.service.greendao.PosItemKitDao;
import com.nhsoft.poslib.service.greendao.PosItemTerminalDao;
import com.nhsoft.poslib.service.greendao.PosMachineDao;
import com.nhsoft.poslib.service.greendao.PrintOrderUsingDao;
import com.nhsoft.poslib.service.greendao.PrivilegeResourceNewDao;
import com.nhsoft.poslib.service.greendao.RelatCardDao;
import com.nhsoft.poslib.service.greendao.ReplaceCardDao;
import com.nhsoft.poslib.service.greendao.RolePrivilegeNewDao;
import com.nhsoft.poslib.service.greendao.ShiftTableDao;
import com.nhsoft.poslib.service.greendao.ShiftTablePaymentDao;
import com.nhsoft.poslib.service.greendao.StoreHouseDao;
import com.nhsoft.poslib.service.greendao.SystemBookDao;
import com.nhsoft.poslib.service.greendao.SystemImageQrcodeDao;
import com.nhsoft.poslib.service.greendao.SystemPrintDao;
import com.nhsoft.poslib.service.greendao.SystemRoleDao;
import com.nhsoft.poslib.service.greendao.TableMd5Dao;
import com.nhsoft.poslib.service.greendao.TableMd5EntityDao;
import com.nhsoft.poslib.service.greendao.TicketSendDetailDao;
import com.nhsoft.poslib.service.greendao.UserRoleDao;
import com.nhsoft.poslib.service.greendao.VipConsumeDao;
import com.nhsoft.poslib.service.greendao.VipCrmAmaLevelDao;
import com.nhsoft.poslib.service.greendao.VipCrmFeeDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDao;
import com.nhsoft.poslib.service.greendao.VipCRMLevelDetailDao;
import com.nhsoft.poslib.service.greendao.VipCrmPointRateDao;
import com.nhsoft.poslib.service.greendao.VipIcInitDao;
import com.nhsoft.poslib.service.greendao.VipLevelPointRuleDao;
import com.nhsoft.poslib.service.greendao.VipSendCardDao;
import com.nhsoft.poslib.service.greendao.VipStrangeSuccessSendMoneyDao;
import com.nhsoft.poslib.service.greendao.YunServiceDaysDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accountBankDaoConfig;
    private final DaoConfig aggregationDaoConfig;
    private final DaoConfig amountPayDaoConfig;
    private final DaoConfig appUserDaoConfig;
    private final DaoConfig attachedScreenDaoConfig;
    private final DaoConfig bookResourceDaoConfig;
    private final DaoConfig bottomMenuDaoConfig;
    private final DaoConfig branchDaoConfig;
    private final DaoConfig branchGroupDaoConfig;
    private final DaoConfig branchMessageDaoConfig;
    private final DaoConfig branchRegionDaoConfig;
    private final DaoConfig branchResourceDaoConfig;
    private final DaoConfig branchsBeanDaoConfig;
    private final DaoConfig cardChangeDaoConfig;
    private final DaoConfig cardDepositDaoConfig;
    private final DaoConfig cardDepositFailedDaoConfig;
    private final DaoConfig cardTypeParamDaoConfig;
    private final DaoConfig changeGoodsMenuDaoConfig;
    private final DaoConfig clientPointDaoConfig;
    private final DaoConfig currentUserDaoConfig;
    private final DaoConfig customerRegisterDaoConfig;
    private final DaoConfig demoEntityDaoConfig;
    private final DaoConfig deskOperatingParametersDaoConfig;
    private final DaoConfig employeeDaoConfig;
    private final DaoConfig employeeEntityDaoConfig;
    private final DaoConfig icCardMessageDaoConfig;
    private final DaoConfig inventoryDaoConfig;
    private final DaoConfig itemBarDaoConfig;
    private final DaoConfig itemCategoryDaoConfig;
    private final DaoConfig keyGeneratorBizdayDaoConfig;
    private final DaoConfig loginDaoConfig;
    private final DaoConfig managementTemplateDaoConfig;
    private final DaoConfig managementTemplateDetailDaoConfig;
    private final DaoConfig marketActionDaoConfig;
    private final DaoConfig marketActionDetailDaoConfig;
    private final DaoConfig measureUnitDaoConfig;
    private final DaoConfig measureUnitItemDaoConfig;
    private final DaoConfig paymentDaoConfig;
    private final DaoConfig posOrderDaoConfig;
    private final DaoConfig posOrderDetailDaoConfig;
    private final DaoConfig posOrderKitDetailDaoConfig;
    private final DaoConfig otherRevenueDaoConfig;
    private final DaoConfig payStyleToCashBankDaoConfig;
    private final DaoConfig pointOrderDaoConfig;
    private final DaoConfig pointOrderDetialDaoConfig;
    private final DaoConfig pointPolicyDaoConfig;
    private final DaoConfig pointPolicyDetailDaoConfig;
    private final DaoConfig pointRuleDaoConfig;
    private final DaoConfig policyDiscountDaoConfig;
    private final DaoConfig policyDiscountDetailDaoConfig;
    private final DaoConfig policyMoneyDaoConfig;
    private final DaoConfig policyMoneyDetailDaoConfig;
    private final DaoConfig policyPresentDaoConfig;
    private final DaoConfig policyPresentDetailDaoConfig;
    private final DaoConfig policyPromotionDaoConfig;
    private final DaoConfig policyPromotionDetailDaoConfig;
    private final DaoConfig policyQuantityDaoConfig;
    private final DaoConfig policyQuantityDetailDaoConfig;
    private final DaoConfig posCarryLogDaoConfig;
    private final DaoConfig posItemDaoConfig;
    private final DaoConfig posItemGradeDaoConfig;
    private final DaoConfig posItemGradeTerminalDaoConfig;
    private final DaoConfig posItemKitDaoConfig;
    private final DaoConfig posItemTerminalDaoConfig;
    private final DaoConfig posMachineDaoConfig;
    private final DaoConfig printOrderUsingDaoConfig;
    private final DaoConfig privilegeResourceNewDaoConfig;
    private final DaoConfig relatCardDaoConfig;
    private final DaoConfig replaceCardDaoConfig;
    private final DaoConfig rolePrivilegeNewDaoConfig;
    private final DaoConfig shiftTableDaoConfig;
    private final DaoConfig shiftTablePaymentDaoConfig;
    private final DaoConfig storeHouseDaoConfig;
    private final DaoConfig systemBookDaoConfig;
    private final DaoConfig systemImageQrcodeDaoConfig;
    private final DaoConfig systemPrintDaoConfig;
    private final DaoConfig systemRoleDaoConfig;
    private final DaoConfig tableMd5DaoConfig;
    private final DaoConfig tableMd5EntityDaoConfig;
    private final DaoConfig ticketSendDetailDaoConfig;
    private final DaoConfig userRoleDaoConfig;
    private final DaoConfig vipConsumeDaoConfig;
    private final DaoConfig vipCrmAmaLevelDaoConfig;
    private final DaoConfig vipCrmFeeDaoConfig;
    private final DaoConfig vipCRMLevelDaoConfig;
    private final DaoConfig vipCRMLevelDetailDaoConfig;
    private final DaoConfig vipCrmPointRateDaoConfig;
    private final DaoConfig vipIcInitDaoConfig;
    private final DaoConfig vipLevelPointRuleDaoConfig;
    private final DaoConfig vipSendCardDaoConfig;
    private final DaoConfig vipStrangeSuccessSendMoneyDaoConfig;
    private final DaoConfig yunServiceDaysDaoConfig;

    private final AccountBankDao accountBankDao;
    private final AggregationDao aggregationDao;
    private final AmountPayDao amountPayDao;
    private final AppUserDao appUserDao;
    private final AttachedScreenDao attachedScreenDao;
    private final BookResourceDao bookResourceDao;
    private final BottomMenuDao bottomMenuDao;
    private final BranchDao branchDao;
    private final BranchGroupDao branchGroupDao;
    private final BranchMessageDao branchMessageDao;
    private final BranchRegionDao branchRegionDao;
    private final BranchResourceDao branchResourceDao;
    private final BranchsBeanDao branchsBeanDao;
    private final CardChangeDao cardChangeDao;
    private final CardDepositDao cardDepositDao;
    private final CardDepositFailedDao cardDepositFailedDao;
    private final CardTypeParamDao cardTypeParamDao;
    private final ChangeGoodsMenuDao changeGoodsMenuDao;
    private final ClientPointDao clientPointDao;
    private final CurrentUserDao currentUserDao;
    private final CustomerRegisterDao customerRegisterDao;
    private final DemoEntityDao demoEntityDao;
    private final DeskOperatingParametersDao deskOperatingParametersDao;
    private final EmployeeDao employeeDao;
    private final EmployeeEntityDao employeeEntityDao;
    private final IcCardMessageDao icCardMessageDao;
    private final InventoryDao inventoryDao;
    private final ItemBarDao itemBarDao;
    private final ItemCategoryDao itemCategoryDao;
    private final KeyGeneratorBizdayDao keyGeneratorBizdayDao;
    private final LoginDao loginDao;
    private final ManagementTemplateDao managementTemplateDao;
    private final ManagementTemplateDetailDao managementTemplateDetailDao;
    private final MarketActionDao marketActionDao;
    private final MarketActionDetailDao marketActionDetailDao;
    private final MeasureUnitDao measureUnitDao;
    private final MeasureUnitItemDao measureUnitItemDao;
    private final PaymentDao paymentDao;
    private final PosOrderDao posOrderDao;
    private final PosOrderDetailDao posOrderDetailDao;
    private final PosOrderKitDetailDao posOrderKitDetailDao;
    private final OtherRevenueDao otherRevenueDao;
    private final PayStyleToCashBankDao payStyleToCashBankDao;
    private final PointOrderDao pointOrderDao;
    private final PointOrderDetialDao pointOrderDetialDao;
    private final PointPolicyDao pointPolicyDao;
    private final PointPolicyDetailDao pointPolicyDetailDao;
    private final PointRuleDao pointRuleDao;
    private final PolicyDiscountDao policyDiscountDao;
    private final PolicyDiscountDetailDao policyDiscountDetailDao;
    private final PolicyMoneyDao policyMoneyDao;
    private final PolicyMoneyDetailDao policyMoneyDetailDao;
    private final PolicyPresentDao policyPresentDao;
    private final PolicyPresentDetailDao policyPresentDetailDao;
    private final PolicyPromotionDao policyPromotionDao;
    private final PolicyPromotionDetailDao policyPromotionDetailDao;
    private final PolicyQuantityDao policyQuantityDao;
    private final PolicyQuantityDetailDao policyQuantityDetailDao;
    private final PosCarryLogDao posCarryLogDao;
    private final PosItemDao posItemDao;
    private final PosItemGradeDao posItemGradeDao;
    private final PosItemGradeTerminalDao posItemGradeTerminalDao;
    private final PosItemKitDao posItemKitDao;
    private final PosItemTerminalDao posItemTerminalDao;
    private final PosMachineDao posMachineDao;
    private final PrintOrderUsingDao printOrderUsingDao;
    private final PrivilegeResourceNewDao privilegeResourceNewDao;
    private final RelatCardDao relatCardDao;
    private final ReplaceCardDao replaceCardDao;
    private final RolePrivilegeNewDao rolePrivilegeNewDao;
    private final ShiftTableDao shiftTableDao;
    private final ShiftTablePaymentDao shiftTablePaymentDao;
    private final StoreHouseDao storeHouseDao;
    private final SystemBookDao systemBookDao;
    private final SystemImageQrcodeDao systemImageQrcodeDao;
    private final SystemPrintDao systemPrintDao;
    private final SystemRoleDao systemRoleDao;
    private final TableMd5Dao tableMd5Dao;
    private final TableMd5EntityDao tableMd5EntityDao;
    private final TicketSendDetailDao ticketSendDetailDao;
    private final UserRoleDao userRoleDao;
    private final VipConsumeDao vipConsumeDao;
    private final VipCrmAmaLevelDao vipCrmAmaLevelDao;
    private final VipCrmFeeDao vipCrmFeeDao;
    private final VipCRMLevelDao vipCRMLevelDao;
    private final VipCRMLevelDetailDao vipCRMLevelDetailDao;
    private final VipCrmPointRateDao vipCrmPointRateDao;
    private final VipIcInitDao vipIcInitDao;
    private final VipLevelPointRuleDao vipLevelPointRuleDao;
    private final VipSendCardDao vipSendCardDao;
    private final VipStrangeSuccessSendMoneyDao vipStrangeSuccessSendMoneyDao;
    private final YunServiceDaysDao yunServiceDaysDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accountBankDaoConfig = daoConfigMap.get(AccountBankDao.class).clone();
        accountBankDaoConfig.initIdentityScope(type);

        aggregationDaoConfig = daoConfigMap.get(AggregationDao.class).clone();
        aggregationDaoConfig.initIdentityScope(type);

        amountPayDaoConfig = daoConfigMap.get(AmountPayDao.class).clone();
        amountPayDaoConfig.initIdentityScope(type);

        appUserDaoConfig = daoConfigMap.get(AppUserDao.class).clone();
        appUserDaoConfig.initIdentityScope(type);

        attachedScreenDaoConfig = daoConfigMap.get(AttachedScreenDao.class).clone();
        attachedScreenDaoConfig.initIdentityScope(type);

        bookResourceDaoConfig = daoConfigMap.get(BookResourceDao.class).clone();
        bookResourceDaoConfig.initIdentityScope(type);

        bottomMenuDaoConfig = daoConfigMap.get(BottomMenuDao.class).clone();
        bottomMenuDaoConfig.initIdentityScope(type);

        branchDaoConfig = daoConfigMap.get(BranchDao.class).clone();
        branchDaoConfig.initIdentityScope(type);

        branchGroupDaoConfig = daoConfigMap.get(BranchGroupDao.class).clone();
        branchGroupDaoConfig.initIdentityScope(type);

        branchMessageDaoConfig = daoConfigMap.get(BranchMessageDao.class).clone();
        branchMessageDaoConfig.initIdentityScope(type);

        branchRegionDaoConfig = daoConfigMap.get(BranchRegionDao.class).clone();
        branchRegionDaoConfig.initIdentityScope(type);

        branchResourceDaoConfig = daoConfigMap.get(BranchResourceDao.class).clone();
        branchResourceDaoConfig.initIdentityScope(type);

        branchsBeanDaoConfig = daoConfigMap.get(BranchsBeanDao.class).clone();
        branchsBeanDaoConfig.initIdentityScope(type);

        cardChangeDaoConfig = daoConfigMap.get(CardChangeDao.class).clone();
        cardChangeDaoConfig.initIdentityScope(type);

        cardDepositDaoConfig = daoConfigMap.get(CardDepositDao.class).clone();
        cardDepositDaoConfig.initIdentityScope(type);

        cardDepositFailedDaoConfig = daoConfigMap.get(CardDepositFailedDao.class).clone();
        cardDepositFailedDaoConfig.initIdentityScope(type);

        cardTypeParamDaoConfig = daoConfigMap.get(CardTypeParamDao.class).clone();
        cardTypeParamDaoConfig.initIdentityScope(type);

        changeGoodsMenuDaoConfig = daoConfigMap.get(ChangeGoodsMenuDao.class).clone();
        changeGoodsMenuDaoConfig.initIdentityScope(type);

        clientPointDaoConfig = daoConfigMap.get(ClientPointDao.class).clone();
        clientPointDaoConfig.initIdentityScope(type);

        currentUserDaoConfig = daoConfigMap.get(CurrentUserDao.class).clone();
        currentUserDaoConfig.initIdentityScope(type);

        customerRegisterDaoConfig = daoConfigMap.get(CustomerRegisterDao.class).clone();
        customerRegisterDaoConfig.initIdentityScope(type);

        demoEntityDaoConfig = daoConfigMap.get(DemoEntityDao.class).clone();
        demoEntityDaoConfig.initIdentityScope(type);

        deskOperatingParametersDaoConfig = daoConfigMap.get(DeskOperatingParametersDao.class).clone();
        deskOperatingParametersDaoConfig.initIdentityScope(type);

        employeeDaoConfig = daoConfigMap.get(EmployeeDao.class).clone();
        employeeDaoConfig.initIdentityScope(type);

        employeeEntityDaoConfig = daoConfigMap.get(EmployeeEntityDao.class).clone();
        employeeEntityDaoConfig.initIdentityScope(type);

        icCardMessageDaoConfig = daoConfigMap.get(IcCardMessageDao.class).clone();
        icCardMessageDaoConfig.initIdentityScope(type);

        inventoryDaoConfig = daoConfigMap.get(InventoryDao.class).clone();
        inventoryDaoConfig.initIdentityScope(type);

        itemBarDaoConfig = daoConfigMap.get(ItemBarDao.class).clone();
        itemBarDaoConfig.initIdentityScope(type);

        itemCategoryDaoConfig = daoConfigMap.get(ItemCategoryDao.class).clone();
        itemCategoryDaoConfig.initIdentityScope(type);

        keyGeneratorBizdayDaoConfig = daoConfigMap.get(KeyGeneratorBizdayDao.class).clone();
        keyGeneratorBizdayDaoConfig.initIdentityScope(type);

        loginDaoConfig = daoConfigMap.get(LoginDao.class).clone();
        loginDaoConfig.initIdentityScope(type);

        managementTemplateDaoConfig = daoConfigMap.get(ManagementTemplateDao.class).clone();
        managementTemplateDaoConfig.initIdentityScope(type);

        managementTemplateDetailDaoConfig = daoConfigMap.get(ManagementTemplateDetailDao.class).clone();
        managementTemplateDetailDaoConfig.initIdentityScope(type);

        marketActionDaoConfig = daoConfigMap.get(MarketActionDao.class).clone();
        marketActionDaoConfig.initIdentityScope(type);

        marketActionDetailDaoConfig = daoConfigMap.get(MarketActionDetailDao.class).clone();
        marketActionDetailDaoConfig.initIdentityScope(type);

        measureUnitDaoConfig = daoConfigMap.get(MeasureUnitDao.class).clone();
        measureUnitDaoConfig.initIdentityScope(type);

        measureUnitItemDaoConfig = daoConfigMap.get(MeasureUnitItemDao.class).clone();
        measureUnitItemDaoConfig.initIdentityScope(type);

        paymentDaoConfig = daoConfigMap.get(PaymentDao.class).clone();
        paymentDaoConfig.initIdentityScope(type);

        posOrderDaoConfig = daoConfigMap.get(PosOrderDao.class).clone();
        posOrderDaoConfig.initIdentityScope(type);

        posOrderDetailDaoConfig = daoConfigMap.get(PosOrderDetailDao.class).clone();
        posOrderDetailDaoConfig.initIdentityScope(type);

        posOrderKitDetailDaoConfig = daoConfigMap.get(PosOrderKitDetailDao.class).clone();
        posOrderKitDetailDaoConfig.initIdentityScope(type);

        otherRevenueDaoConfig = daoConfigMap.get(OtherRevenueDao.class).clone();
        otherRevenueDaoConfig.initIdentityScope(type);

        payStyleToCashBankDaoConfig = daoConfigMap.get(PayStyleToCashBankDao.class).clone();
        payStyleToCashBankDaoConfig.initIdentityScope(type);

        pointOrderDaoConfig = daoConfigMap.get(PointOrderDao.class).clone();
        pointOrderDaoConfig.initIdentityScope(type);

        pointOrderDetialDaoConfig = daoConfigMap.get(PointOrderDetialDao.class).clone();
        pointOrderDetialDaoConfig.initIdentityScope(type);

        pointPolicyDaoConfig = daoConfigMap.get(PointPolicyDao.class).clone();
        pointPolicyDaoConfig.initIdentityScope(type);

        pointPolicyDetailDaoConfig = daoConfigMap.get(PointPolicyDetailDao.class).clone();
        pointPolicyDetailDaoConfig.initIdentityScope(type);

        pointRuleDaoConfig = daoConfigMap.get(PointRuleDao.class).clone();
        pointRuleDaoConfig.initIdentityScope(type);

        policyDiscountDaoConfig = daoConfigMap.get(PolicyDiscountDao.class).clone();
        policyDiscountDaoConfig.initIdentityScope(type);

        policyDiscountDetailDaoConfig = daoConfigMap.get(PolicyDiscountDetailDao.class).clone();
        policyDiscountDetailDaoConfig.initIdentityScope(type);

        policyMoneyDaoConfig = daoConfigMap.get(PolicyMoneyDao.class).clone();
        policyMoneyDaoConfig.initIdentityScope(type);

        policyMoneyDetailDaoConfig = daoConfigMap.get(PolicyMoneyDetailDao.class).clone();
        policyMoneyDetailDaoConfig.initIdentityScope(type);

        policyPresentDaoConfig = daoConfigMap.get(PolicyPresentDao.class).clone();
        policyPresentDaoConfig.initIdentityScope(type);

        policyPresentDetailDaoConfig = daoConfigMap.get(PolicyPresentDetailDao.class).clone();
        policyPresentDetailDaoConfig.initIdentityScope(type);

        policyPromotionDaoConfig = daoConfigMap.get(PolicyPromotionDao.class).clone();
        policyPromotionDaoConfig.initIdentityScope(type);

        policyPromotionDetailDaoConfig = daoConfigMap.get(PolicyPromotionDetailDao.class).clone();
        policyPromotionDetailDaoConfig.initIdentityScope(type);

        policyQuantityDaoConfig = daoConfigMap.get(PolicyQuantityDao.class).clone();
        policyQuantityDaoConfig.initIdentityScope(type);

        policyQuantityDetailDaoConfig = daoConfigMap.get(PolicyQuantityDetailDao.class).clone();
        policyQuantityDetailDaoConfig.initIdentityScope(type);

        posCarryLogDaoConfig = daoConfigMap.get(PosCarryLogDao.class).clone();
        posCarryLogDaoConfig.initIdentityScope(type);

        posItemDaoConfig = daoConfigMap.get(PosItemDao.class).clone();
        posItemDaoConfig.initIdentityScope(type);

        posItemGradeDaoConfig = daoConfigMap.get(PosItemGradeDao.class).clone();
        posItemGradeDaoConfig.initIdentityScope(type);

        posItemGradeTerminalDaoConfig = daoConfigMap.get(PosItemGradeTerminalDao.class).clone();
        posItemGradeTerminalDaoConfig.initIdentityScope(type);

        posItemKitDaoConfig = daoConfigMap.get(PosItemKitDao.class).clone();
        posItemKitDaoConfig.initIdentityScope(type);

        posItemTerminalDaoConfig = daoConfigMap.get(PosItemTerminalDao.class).clone();
        posItemTerminalDaoConfig.initIdentityScope(type);

        posMachineDaoConfig = daoConfigMap.get(PosMachineDao.class).clone();
        posMachineDaoConfig.initIdentityScope(type);

        printOrderUsingDaoConfig = daoConfigMap.get(PrintOrderUsingDao.class).clone();
        printOrderUsingDaoConfig.initIdentityScope(type);

        privilegeResourceNewDaoConfig = daoConfigMap.get(PrivilegeResourceNewDao.class).clone();
        privilegeResourceNewDaoConfig.initIdentityScope(type);

        relatCardDaoConfig = daoConfigMap.get(RelatCardDao.class).clone();
        relatCardDaoConfig.initIdentityScope(type);

        replaceCardDaoConfig = daoConfigMap.get(ReplaceCardDao.class).clone();
        replaceCardDaoConfig.initIdentityScope(type);

        rolePrivilegeNewDaoConfig = daoConfigMap.get(RolePrivilegeNewDao.class).clone();
        rolePrivilegeNewDaoConfig.initIdentityScope(type);

        shiftTableDaoConfig = daoConfigMap.get(ShiftTableDao.class).clone();
        shiftTableDaoConfig.initIdentityScope(type);

        shiftTablePaymentDaoConfig = daoConfigMap.get(ShiftTablePaymentDao.class).clone();
        shiftTablePaymentDaoConfig.initIdentityScope(type);

        storeHouseDaoConfig = daoConfigMap.get(StoreHouseDao.class).clone();
        storeHouseDaoConfig.initIdentityScope(type);

        systemBookDaoConfig = daoConfigMap.get(SystemBookDao.class).clone();
        systemBookDaoConfig.initIdentityScope(type);

        systemImageQrcodeDaoConfig = daoConfigMap.get(SystemImageQrcodeDao.class).clone();
        systemImageQrcodeDaoConfig.initIdentityScope(type);

        systemPrintDaoConfig = daoConfigMap.get(SystemPrintDao.class).clone();
        systemPrintDaoConfig.initIdentityScope(type);

        systemRoleDaoConfig = daoConfigMap.get(SystemRoleDao.class).clone();
        systemRoleDaoConfig.initIdentityScope(type);

        tableMd5DaoConfig = daoConfigMap.get(TableMd5Dao.class).clone();
        tableMd5DaoConfig.initIdentityScope(type);

        tableMd5EntityDaoConfig = daoConfigMap.get(TableMd5EntityDao.class).clone();
        tableMd5EntityDaoConfig.initIdentityScope(type);

        ticketSendDetailDaoConfig = daoConfigMap.get(TicketSendDetailDao.class).clone();
        ticketSendDetailDaoConfig.initIdentityScope(type);

        userRoleDaoConfig = daoConfigMap.get(UserRoleDao.class).clone();
        userRoleDaoConfig.initIdentityScope(type);

        vipConsumeDaoConfig = daoConfigMap.get(VipConsumeDao.class).clone();
        vipConsumeDaoConfig.initIdentityScope(type);

        vipCrmAmaLevelDaoConfig = daoConfigMap.get(VipCrmAmaLevelDao.class).clone();
        vipCrmAmaLevelDaoConfig.initIdentityScope(type);

        vipCrmFeeDaoConfig = daoConfigMap.get(VipCrmFeeDao.class).clone();
        vipCrmFeeDaoConfig.initIdentityScope(type);

        vipCRMLevelDaoConfig = daoConfigMap.get(VipCRMLevelDao.class).clone();
        vipCRMLevelDaoConfig.initIdentityScope(type);

        vipCRMLevelDetailDaoConfig = daoConfigMap.get(VipCRMLevelDetailDao.class).clone();
        vipCRMLevelDetailDaoConfig.initIdentityScope(type);

        vipCrmPointRateDaoConfig = daoConfigMap.get(VipCrmPointRateDao.class).clone();
        vipCrmPointRateDaoConfig.initIdentityScope(type);

        vipIcInitDaoConfig = daoConfigMap.get(VipIcInitDao.class).clone();
        vipIcInitDaoConfig.initIdentityScope(type);

        vipLevelPointRuleDaoConfig = daoConfigMap.get(VipLevelPointRuleDao.class).clone();
        vipLevelPointRuleDaoConfig.initIdentityScope(type);

        vipSendCardDaoConfig = daoConfigMap.get(VipSendCardDao.class).clone();
        vipSendCardDaoConfig.initIdentityScope(type);

        vipStrangeSuccessSendMoneyDaoConfig = daoConfigMap.get(VipStrangeSuccessSendMoneyDao.class).clone();
        vipStrangeSuccessSendMoneyDaoConfig.initIdentityScope(type);

        yunServiceDaysDaoConfig = daoConfigMap.get(YunServiceDaysDao.class).clone();
        yunServiceDaysDaoConfig.initIdentityScope(type);

        accountBankDao = new AccountBankDao(accountBankDaoConfig, this);
        aggregationDao = new AggregationDao(aggregationDaoConfig, this);
        amountPayDao = new AmountPayDao(amountPayDaoConfig, this);
        appUserDao = new AppUserDao(appUserDaoConfig, this);
        attachedScreenDao = new AttachedScreenDao(attachedScreenDaoConfig, this);
        bookResourceDao = new BookResourceDao(bookResourceDaoConfig, this);
        bottomMenuDao = new BottomMenuDao(bottomMenuDaoConfig, this);
        branchDao = new BranchDao(branchDaoConfig, this);
        branchGroupDao = new BranchGroupDao(branchGroupDaoConfig, this);
        branchMessageDao = new BranchMessageDao(branchMessageDaoConfig, this);
        branchRegionDao = new BranchRegionDao(branchRegionDaoConfig, this);
        branchResourceDao = new BranchResourceDao(branchResourceDaoConfig, this);
        branchsBeanDao = new BranchsBeanDao(branchsBeanDaoConfig, this);
        cardChangeDao = new CardChangeDao(cardChangeDaoConfig, this);
        cardDepositDao = new CardDepositDao(cardDepositDaoConfig, this);
        cardDepositFailedDao = new CardDepositFailedDao(cardDepositFailedDaoConfig, this);
        cardTypeParamDao = new CardTypeParamDao(cardTypeParamDaoConfig, this);
        changeGoodsMenuDao = new ChangeGoodsMenuDao(changeGoodsMenuDaoConfig, this);
        clientPointDao = new ClientPointDao(clientPointDaoConfig, this);
        currentUserDao = new CurrentUserDao(currentUserDaoConfig, this);
        customerRegisterDao = new CustomerRegisterDao(customerRegisterDaoConfig, this);
        demoEntityDao = new DemoEntityDao(demoEntityDaoConfig, this);
        deskOperatingParametersDao = new DeskOperatingParametersDao(deskOperatingParametersDaoConfig, this);
        employeeDao = new EmployeeDao(employeeDaoConfig, this);
        employeeEntityDao = new EmployeeEntityDao(employeeEntityDaoConfig, this);
        icCardMessageDao = new IcCardMessageDao(icCardMessageDaoConfig, this);
        inventoryDao = new InventoryDao(inventoryDaoConfig, this);
        itemBarDao = new ItemBarDao(itemBarDaoConfig, this);
        itemCategoryDao = new ItemCategoryDao(itemCategoryDaoConfig, this);
        keyGeneratorBizdayDao = new KeyGeneratorBizdayDao(keyGeneratorBizdayDaoConfig, this);
        loginDao = new LoginDao(loginDaoConfig, this);
        managementTemplateDao = new ManagementTemplateDao(managementTemplateDaoConfig, this);
        managementTemplateDetailDao = new ManagementTemplateDetailDao(managementTemplateDetailDaoConfig, this);
        marketActionDao = new MarketActionDao(marketActionDaoConfig, this);
        marketActionDetailDao = new MarketActionDetailDao(marketActionDetailDaoConfig, this);
        measureUnitDao = new MeasureUnitDao(measureUnitDaoConfig, this);
        measureUnitItemDao = new MeasureUnitItemDao(measureUnitItemDaoConfig, this);
        paymentDao = new PaymentDao(paymentDaoConfig, this);
        posOrderDao = new PosOrderDao(posOrderDaoConfig, this);
        posOrderDetailDao = new PosOrderDetailDao(posOrderDetailDaoConfig, this);
        posOrderKitDetailDao = new PosOrderKitDetailDao(posOrderKitDetailDaoConfig, this);
        otherRevenueDao = new OtherRevenueDao(otherRevenueDaoConfig, this);
        payStyleToCashBankDao = new PayStyleToCashBankDao(payStyleToCashBankDaoConfig, this);
        pointOrderDao = new PointOrderDao(pointOrderDaoConfig, this);
        pointOrderDetialDao = new PointOrderDetialDao(pointOrderDetialDaoConfig, this);
        pointPolicyDao = new PointPolicyDao(pointPolicyDaoConfig, this);
        pointPolicyDetailDao = new PointPolicyDetailDao(pointPolicyDetailDaoConfig, this);
        pointRuleDao = new PointRuleDao(pointRuleDaoConfig, this);
        policyDiscountDao = new PolicyDiscountDao(policyDiscountDaoConfig, this);
        policyDiscountDetailDao = new PolicyDiscountDetailDao(policyDiscountDetailDaoConfig, this);
        policyMoneyDao = new PolicyMoneyDao(policyMoneyDaoConfig, this);
        policyMoneyDetailDao = new PolicyMoneyDetailDao(policyMoneyDetailDaoConfig, this);
        policyPresentDao = new PolicyPresentDao(policyPresentDaoConfig, this);
        policyPresentDetailDao = new PolicyPresentDetailDao(policyPresentDetailDaoConfig, this);
        policyPromotionDao = new PolicyPromotionDao(policyPromotionDaoConfig, this);
        policyPromotionDetailDao = new PolicyPromotionDetailDao(policyPromotionDetailDaoConfig, this);
        policyQuantityDao = new PolicyQuantityDao(policyQuantityDaoConfig, this);
        policyQuantityDetailDao = new PolicyQuantityDetailDao(policyQuantityDetailDaoConfig, this);
        posCarryLogDao = new PosCarryLogDao(posCarryLogDaoConfig, this);
        posItemDao = new PosItemDao(posItemDaoConfig, this);
        posItemGradeDao = new PosItemGradeDao(posItemGradeDaoConfig, this);
        posItemGradeTerminalDao = new PosItemGradeTerminalDao(posItemGradeTerminalDaoConfig, this);
        posItemKitDao = new PosItemKitDao(posItemKitDaoConfig, this);
        posItemTerminalDao = new PosItemTerminalDao(posItemTerminalDaoConfig, this);
        posMachineDao = new PosMachineDao(posMachineDaoConfig, this);
        printOrderUsingDao = new PrintOrderUsingDao(printOrderUsingDaoConfig, this);
        privilegeResourceNewDao = new PrivilegeResourceNewDao(privilegeResourceNewDaoConfig, this);
        relatCardDao = new RelatCardDao(relatCardDaoConfig, this);
        replaceCardDao = new ReplaceCardDao(replaceCardDaoConfig, this);
        rolePrivilegeNewDao = new RolePrivilegeNewDao(rolePrivilegeNewDaoConfig, this);
        shiftTableDao = new ShiftTableDao(shiftTableDaoConfig, this);
        shiftTablePaymentDao = new ShiftTablePaymentDao(shiftTablePaymentDaoConfig, this);
        storeHouseDao = new StoreHouseDao(storeHouseDaoConfig, this);
        systemBookDao = new SystemBookDao(systemBookDaoConfig, this);
        systemImageQrcodeDao = new SystemImageQrcodeDao(systemImageQrcodeDaoConfig, this);
        systemPrintDao = new SystemPrintDao(systemPrintDaoConfig, this);
        systemRoleDao = new SystemRoleDao(systemRoleDaoConfig, this);
        tableMd5Dao = new TableMd5Dao(tableMd5DaoConfig, this);
        tableMd5EntityDao = new TableMd5EntityDao(tableMd5EntityDaoConfig, this);
        ticketSendDetailDao = new TicketSendDetailDao(ticketSendDetailDaoConfig, this);
        userRoleDao = new UserRoleDao(userRoleDaoConfig, this);
        vipConsumeDao = new VipConsumeDao(vipConsumeDaoConfig, this);
        vipCrmAmaLevelDao = new VipCrmAmaLevelDao(vipCrmAmaLevelDaoConfig, this);
        vipCrmFeeDao = new VipCrmFeeDao(vipCrmFeeDaoConfig, this);
        vipCRMLevelDao = new VipCRMLevelDao(vipCRMLevelDaoConfig, this);
        vipCRMLevelDetailDao = new VipCRMLevelDetailDao(vipCRMLevelDetailDaoConfig, this);
        vipCrmPointRateDao = new VipCrmPointRateDao(vipCrmPointRateDaoConfig, this);
        vipIcInitDao = new VipIcInitDao(vipIcInitDaoConfig, this);
        vipLevelPointRuleDao = new VipLevelPointRuleDao(vipLevelPointRuleDaoConfig, this);
        vipSendCardDao = new VipSendCardDao(vipSendCardDaoConfig, this);
        vipStrangeSuccessSendMoneyDao = new VipStrangeSuccessSendMoneyDao(vipStrangeSuccessSendMoneyDaoConfig, this);
        yunServiceDaysDao = new YunServiceDaysDao(yunServiceDaysDaoConfig, this);

        registerDao(AccountBank.class, accountBankDao);
        registerDao(Aggregation.class, aggregationDao);
        registerDao(AmountPay.class, amountPayDao);
        registerDao(AppUser.class, appUserDao);
        registerDao(AttachedScreen.class, attachedScreenDao);
        registerDao(BookResource.class, bookResourceDao);
        registerDao(BottomMenu.class, bottomMenuDao);
        registerDao(Branch.class, branchDao);
        registerDao(BranchGroup.class, branchGroupDao);
        registerDao(BranchMessage.class, branchMessageDao);
        registerDao(BranchRegion.class, branchRegionDao);
        registerDao(BranchResource.class, branchResourceDao);
        registerDao(BranchsBean.class, branchsBeanDao);
        registerDao(CardChange.class, cardChangeDao);
        registerDao(CardDeposit.class, cardDepositDao);
        registerDao(CardDepositFailed.class, cardDepositFailedDao);
        registerDao(CardTypeParam.class, cardTypeParamDao);
        registerDao(ChangeGoodsMenu.class, changeGoodsMenuDao);
        registerDao(ClientPoint.class, clientPointDao);
        registerDao(CurrentUser.class, currentUserDao);
        registerDao(CustomerRegister.class, customerRegisterDao);
        registerDao(DemoEntity.class, demoEntityDao);
        registerDao(DeskOperatingParameters.class, deskOperatingParametersDao);
        registerDao(Employee.class, employeeDao);
        registerDao(EmployeeEntity.class, employeeEntityDao);
        registerDao(IcCardMessage.class, icCardMessageDao);
        registerDao(Inventory.class, inventoryDao);
        registerDao(ItemBar.class, itemBarDao);
        registerDao(ItemCategory.class, itemCategoryDao);
        registerDao(KeyGeneratorBizday.class, keyGeneratorBizdayDao);
        registerDao(Login.class, loginDao);
        registerDao(ManagementTemplate.class, managementTemplateDao);
        registerDao(ManagementTemplateDetail.class, managementTemplateDetailDao);
        registerDao(MarketAction.class, marketActionDao);
        registerDao(MarketActionDetail.class, marketActionDetailDao);
        registerDao(MeasureUnit.class, measureUnitDao);
        registerDao(MeasureUnitItem.class, measureUnitItemDao);
        registerDao(Payment.class, paymentDao);
        registerDao(PosOrder.class, posOrderDao);
        registerDao(PosOrderDetail.class, posOrderDetailDao);
        registerDao(PosOrderKitDetail.class, posOrderKitDetailDao);
        registerDao(OtherRevenue.class, otherRevenueDao);
        registerDao(PayStyleToCashBank.class, payStyleToCashBankDao);
        registerDao(PointOrder.class, pointOrderDao);
        registerDao(PointOrderDetial.class, pointOrderDetialDao);
        registerDao(PointPolicy.class, pointPolicyDao);
        registerDao(PointPolicyDetail.class, pointPolicyDetailDao);
        registerDao(PointRule.class, pointRuleDao);
        registerDao(PolicyDiscount.class, policyDiscountDao);
        registerDao(PolicyDiscountDetail.class, policyDiscountDetailDao);
        registerDao(PolicyMoney.class, policyMoneyDao);
        registerDao(PolicyMoneyDetail.class, policyMoneyDetailDao);
        registerDao(PolicyPresent.class, policyPresentDao);
        registerDao(PolicyPresentDetail.class, policyPresentDetailDao);
        registerDao(PolicyPromotion.class, policyPromotionDao);
        registerDao(PolicyPromotionDetail.class, policyPromotionDetailDao);
        registerDao(PolicyQuantity.class, policyQuantityDao);
        registerDao(PolicyQuantityDetail.class, policyQuantityDetailDao);
        registerDao(PosCarryLog.class, posCarryLogDao);
        registerDao(PosItem.class, posItemDao);
        registerDao(PosItemGrade.class, posItemGradeDao);
        registerDao(PosItemGradeTerminal.class, posItemGradeTerminalDao);
        registerDao(PosItemKit.class, posItemKitDao);
        registerDao(PosItemTerminal.class, posItemTerminalDao);
        registerDao(PosMachine.class, posMachineDao);
        registerDao(PrintOrderUsing.class, printOrderUsingDao);
        registerDao(PrivilegeResourceNew.class, privilegeResourceNewDao);
        registerDao(RelatCard.class, relatCardDao);
        registerDao(ReplaceCard.class, replaceCardDao);
        registerDao(RolePrivilegeNew.class, rolePrivilegeNewDao);
        registerDao(ShiftTable.class, shiftTableDao);
        registerDao(ShiftTablePayment.class, shiftTablePaymentDao);
        registerDao(StoreHouse.class, storeHouseDao);
        registerDao(SystemBook.class, systemBookDao);
        registerDao(SystemImageQrcode.class, systemImageQrcodeDao);
        registerDao(SystemPrint.class, systemPrintDao);
        registerDao(SystemRole.class, systemRoleDao);
        registerDao(TableMd5.class, tableMd5Dao);
        registerDao(TableMd5Entity.class, tableMd5EntityDao);
        registerDao(TicketSendDetail.class, ticketSendDetailDao);
        registerDao(UserRole.class, userRoleDao);
        registerDao(VipConsume.class, vipConsumeDao);
        registerDao(VipCrmAmaLevel.class, vipCrmAmaLevelDao);
        registerDao(VipCrmFee.class, vipCrmFeeDao);
        registerDao(VipCRMLevel.class, vipCRMLevelDao);
        registerDao(VipCRMLevelDetail.class, vipCRMLevelDetailDao);
        registerDao(VipCrmPointRate.class, vipCrmPointRateDao);
        registerDao(VipIcInit.class, vipIcInitDao);
        registerDao(VipLevelPointRule.class, vipLevelPointRuleDao);
        registerDao(VipSendCard.class, vipSendCardDao);
        registerDao(VipStrangeSuccessSendMoney.class, vipStrangeSuccessSendMoneyDao);
        registerDao(YunServiceDays.class, yunServiceDaysDao);
    }
    
    public void clear() {
        accountBankDaoConfig.clearIdentityScope();
        aggregationDaoConfig.clearIdentityScope();
        amountPayDaoConfig.clearIdentityScope();
        appUserDaoConfig.clearIdentityScope();
        attachedScreenDaoConfig.clearIdentityScope();
        bookResourceDaoConfig.clearIdentityScope();
        bottomMenuDaoConfig.clearIdentityScope();
        branchDaoConfig.clearIdentityScope();
        branchGroupDaoConfig.clearIdentityScope();
        branchMessageDaoConfig.clearIdentityScope();
        branchRegionDaoConfig.clearIdentityScope();
        branchResourceDaoConfig.clearIdentityScope();
        branchsBeanDaoConfig.clearIdentityScope();
        cardChangeDaoConfig.clearIdentityScope();
        cardDepositDaoConfig.clearIdentityScope();
        cardDepositFailedDaoConfig.clearIdentityScope();
        cardTypeParamDaoConfig.clearIdentityScope();
        changeGoodsMenuDaoConfig.clearIdentityScope();
        clientPointDaoConfig.clearIdentityScope();
        currentUserDaoConfig.clearIdentityScope();
        customerRegisterDaoConfig.clearIdentityScope();
        demoEntityDaoConfig.clearIdentityScope();
        deskOperatingParametersDaoConfig.clearIdentityScope();
        employeeDaoConfig.clearIdentityScope();
        employeeEntityDaoConfig.clearIdentityScope();
        icCardMessageDaoConfig.clearIdentityScope();
        inventoryDaoConfig.clearIdentityScope();
        itemBarDaoConfig.clearIdentityScope();
        itemCategoryDaoConfig.clearIdentityScope();
        keyGeneratorBizdayDaoConfig.clearIdentityScope();
        loginDaoConfig.clearIdentityScope();
        managementTemplateDaoConfig.clearIdentityScope();
        managementTemplateDetailDaoConfig.clearIdentityScope();
        marketActionDaoConfig.clearIdentityScope();
        marketActionDetailDaoConfig.clearIdentityScope();
        measureUnitDaoConfig.clearIdentityScope();
        measureUnitItemDaoConfig.clearIdentityScope();
        paymentDaoConfig.clearIdentityScope();
        posOrderDaoConfig.clearIdentityScope();
        posOrderDetailDaoConfig.clearIdentityScope();
        posOrderKitDetailDaoConfig.clearIdentityScope();
        otherRevenueDaoConfig.clearIdentityScope();
        payStyleToCashBankDaoConfig.clearIdentityScope();
        pointOrderDaoConfig.clearIdentityScope();
        pointOrderDetialDaoConfig.clearIdentityScope();
        pointPolicyDaoConfig.clearIdentityScope();
        pointPolicyDetailDaoConfig.clearIdentityScope();
        pointRuleDaoConfig.clearIdentityScope();
        policyDiscountDaoConfig.clearIdentityScope();
        policyDiscountDetailDaoConfig.clearIdentityScope();
        policyMoneyDaoConfig.clearIdentityScope();
        policyMoneyDetailDaoConfig.clearIdentityScope();
        policyPresentDaoConfig.clearIdentityScope();
        policyPresentDetailDaoConfig.clearIdentityScope();
        policyPromotionDaoConfig.clearIdentityScope();
        policyPromotionDetailDaoConfig.clearIdentityScope();
        policyQuantityDaoConfig.clearIdentityScope();
        policyQuantityDetailDaoConfig.clearIdentityScope();
        posCarryLogDaoConfig.clearIdentityScope();
        posItemDaoConfig.clearIdentityScope();
        posItemGradeDaoConfig.clearIdentityScope();
        posItemGradeTerminalDaoConfig.clearIdentityScope();
        posItemKitDaoConfig.clearIdentityScope();
        posItemTerminalDaoConfig.clearIdentityScope();
        posMachineDaoConfig.clearIdentityScope();
        printOrderUsingDaoConfig.clearIdentityScope();
        privilegeResourceNewDaoConfig.clearIdentityScope();
        relatCardDaoConfig.clearIdentityScope();
        replaceCardDaoConfig.clearIdentityScope();
        rolePrivilegeNewDaoConfig.clearIdentityScope();
        shiftTableDaoConfig.clearIdentityScope();
        shiftTablePaymentDaoConfig.clearIdentityScope();
        storeHouseDaoConfig.clearIdentityScope();
        systemBookDaoConfig.clearIdentityScope();
        systemImageQrcodeDaoConfig.clearIdentityScope();
        systemPrintDaoConfig.clearIdentityScope();
        systemRoleDaoConfig.clearIdentityScope();
        tableMd5DaoConfig.clearIdentityScope();
        tableMd5EntityDaoConfig.clearIdentityScope();
        ticketSendDetailDaoConfig.clearIdentityScope();
        userRoleDaoConfig.clearIdentityScope();
        vipConsumeDaoConfig.clearIdentityScope();
        vipCrmAmaLevelDaoConfig.clearIdentityScope();
        vipCrmFeeDaoConfig.clearIdentityScope();
        vipCRMLevelDaoConfig.clearIdentityScope();
        vipCRMLevelDetailDaoConfig.clearIdentityScope();
        vipCrmPointRateDaoConfig.clearIdentityScope();
        vipIcInitDaoConfig.clearIdentityScope();
        vipLevelPointRuleDaoConfig.clearIdentityScope();
        vipSendCardDaoConfig.clearIdentityScope();
        vipStrangeSuccessSendMoneyDaoConfig.clearIdentityScope();
        yunServiceDaysDaoConfig.clearIdentityScope();
    }

    public AccountBankDao getAccountBankDao() {
        return accountBankDao;
    }

    public AggregationDao getAggregationDao() {
        return aggregationDao;
    }

    public AmountPayDao getAmountPayDao() {
        return amountPayDao;
    }

    public AppUserDao getAppUserDao() {
        return appUserDao;
    }

    public AttachedScreenDao getAttachedScreenDao() {
        return attachedScreenDao;
    }

    public BookResourceDao getBookResourceDao() {
        return bookResourceDao;
    }

    public BottomMenuDao getBottomMenuDao() {
        return bottomMenuDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public BranchGroupDao getBranchGroupDao() {
        return branchGroupDao;
    }

    public BranchMessageDao getBranchMessageDao() {
        return branchMessageDao;
    }

    public BranchRegionDao getBranchRegionDao() {
        return branchRegionDao;
    }

    public BranchResourceDao getBranchResourceDao() {
        return branchResourceDao;
    }

    public BranchsBeanDao getBranchsBeanDao() {
        return branchsBeanDao;
    }

    public CardChangeDao getCardChangeDao() {
        return cardChangeDao;
    }

    public CardDepositDao getCardDepositDao() {
        return cardDepositDao;
    }

    public CardDepositFailedDao getCardDepositFailedDao() {
        return cardDepositFailedDao;
    }

    public CardTypeParamDao getCardTypeParamDao() {
        return cardTypeParamDao;
    }

    public ChangeGoodsMenuDao getChangeGoodsMenuDao() {
        return changeGoodsMenuDao;
    }

    public ClientPointDao getClientPointDao() {
        return clientPointDao;
    }

    public CurrentUserDao getCurrentUserDao() {
        return currentUserDao;
    }

    public CustomerRegisterDao getCustomerRegisterDao() {
        return customerRegisterDao;
    }

    public DemoEntityDao getDemoEntityDao() {
        return demoEntityDao;
    }

    public DeskOperatingParametersDao getDeskOperatingParametersDao() {
        return deskOperatingParametersDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public EmployeeEntityDao getEmployeeEntityDao() {
        return employeeEntityDao;
    }

    public IcCardMessageDao getIcCardMessageDao() {
        return icCardMessageDao;
    }

    public InventoryDao getInventoryDao() {
        return inventoryDao;
    }

    public ItemBarDao getItemBarDao() {
        return itemBarDao;
    }

    public ItemCategoryDao getItemCategoryDao() {
        return itemCategoryDao;
    }

    public KeyGeneratorBizdayDao getKeyGeneratorBizdayDao() {
        return keyGeneratorBizdayDao;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public ManagementTemplateDao getManagementTemplateDao() {
        return managementTemplateDao;
    }

    public ManagementTemplateDetailDao getManagementTemplateDetailDao() {
        return managementTemplateDetailDao;
    }

    public MarketActionDao getMarketActionDao() {
        return marketActionDao;
    }

    public MarketActionDetailDao getMarketActionDetailDao() {
        return marketActionDetailDao;
    }

    public MeasureUnitDao getMeasureUnitDao() {
        return measureUnitDao;
    }

    public MeasureUnitItemDao getMeasureUnitItemDao() {
        return measureUnitItemDao;
    }

    public PaymentDao getPaymentDao() {
        return paymentDao;
    }

    public PosOrderDao getPosOrderDao() {
        return posOrderDao;
    }

    public PosOrderDetailDao getPosOrderDetailDao() {
        return posOrderDetailDao;
    }

    public PosOrderKitDetailDao getPosOrderKitDetailDao() {
        return posOrderKitDetailDao;
    }

    public OtherRevenueDao getOtherRevenueDao() {
        return otherRevenueDao;
    }

    public PayStyleToCashBankDao getPayStyleToCashBankDao() {
        return payStyleToCashBankDao;
    }

    public PointOrderDao getPointOrderDao() {
        return pointOrderDao;
    }

    public PointOrderDetialDao getPointOrderDetialDao() {
        return pointOrderDetialDao;
    }

    public PointPolicyDao getPointPolicyDao() {
        return pointPolicyDao;
    }

    public PointPolicyDetailDao getPointPolicyDetailDao() {
        return pointPolicyDetailDao;
    }

    public PointRuleDao getPointRuleDao() {
        return pointRuleDao;
    }

    public PolicyDiscountDao getPolicyDiscountDao() {
        return policyDiscountDao;
    }

    public PolicyDiscountDetailDao getPolicyDiscountDetailDao() {
        return policyDiscountDetailDao;
    }

    public PolicyMoneyDao getPolicyMoneyDao() {
        return policyMoneyDao;
    }

    public PolicyMoneyDetailDao getPolicyMoneyDetailDao() {
        return policyMoneyDetailDao;
    }

    public PolicyPresentDao getPolicyPresentDao() {
        return policyPresentDao;
    }

    public PolicyPresentDetailDao getPolicyPresentDetailDao() {
        return policyPresentDetailDao;
    }

    public PolicyPromotionDao getPolicyPromotionDao() {
        return policyPromotionDao;
    }

    public PolicyPromotionDetailDao getPolicyPromotionDetailDao() {
        return policyPromotionDetailDao;
    }

    public PolicyQuantityDao getPolicyQuantityDao() {
        return policyQuantityDao;
    }

    public PolicyQuantityDetailDao getPolicyQuantityDetailDao() {
        return policyQuantityDetailDao;
    }

    public PosCarryLogDao getPosCarryLogDao() {
        return posCarryLogDao;
    }

    public PosItemDao getPosItemDao() {
        return posItemDao;
    }

    public PosItemGradeDao getPosItemGradeDao() {
        return posItemGradeDao;
    }

    public PosItemGradeTerminalDao getPosItemGradeTerminalDao() {
        return posItemGradeTerminalDao;
    }

    public PosItemKitDao getPosItemKitDao() {
        return posItemKitDao;
    }

    public PosItemTerminalDao getPosItemTerminalDao() {
        return posItemTerminalDao;
    }

    public PosMachineDao getPosMachineDao() {
        return posMachineDao;
    }

    public PrintOrderUsingDao getPrintOrderUsingDao() {
        return printOrderUsingDao;
    }

    public PrivilegeResourceNewDao getPrivilegeResourceNewDao() {
        return privilegeResourceNewDao;
    }

    public RelatCardDao getRelatCardDao() {
        return relatCardDao;
    }

    public ReplaceCardDao getReplaceCardDao() {
        return replaceCardDao;
    }

    public RolePrivilegeNewDao getRolePrivilegeNewDao() {
        return rolePrivilegeNewDao;
    }

    public ShiftTableDao getShiftTableDao() {
        return shiftTableDao;
    }

    public ShiftTablePaymentDao getShiftTablePaymentDao() {
        return shiftTablePaymentDao;
    }

    public StoreHouseDao getStoreHouseDao() {
        return storeHouseDao;
    }

    public SystemBookDao getSystemBookDao() {
        return systemBookDao;
    }

    public SystemImageQrcodeDao getSystemImageQrcodeDao() {
        return systemImageQrcodeDao;
    }

    public SystemPrintDao getSystemPrintDao() {
        return systemPrintDao;
    }

    public SystemRoleDao getSystemRoleDao() {
        return systemRoleDao;
    }

    public TableMd5Dao getTableMd5Dao() {
        return tableMd5Dao;
    }

    public TableMd5EntityDao getTableMd5EntityDao() {
        return tableMd5EntityDao;
    }

    public TicketSendDetailDao getTicketSendDetailDao() {
        return ticketSendDetailDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    public VipConsumeDao getVipConsumeDao() {
        return vipConsumeDao;
    }

    public VipCrmAmaLevelDao getVipCrmAmaLevelDao() {
        return vipCrmAmaLevelDao;
    }

    public VipCrmFeeDao getVipCrmFeeDao() {
        return vipCrmFeeDao;
    }

    public VipCRMLevelDao getVipCRMLevelDao() {
        return vipCRMLevelDao;
    }

    public VipCRMLevelDetailDao getVipCRMLevelDetailDao() {
        return vipCRMLevelDetailDao;
    }

    public VipCrmPointRateDao getVipCrmPointRateDao() {
        return vipCrmPointRateDao;
    }

    public VipIcInitDao getVipIcInitDao() {
        return vipIcInitDao;
    }

    public VipLevelPointRuleDao getVipLevelPointRuleDao() {
        return vipLevelPointRuleDao;
    }

    public VipSendCardDao getVipSendCardDao() {
        return vipSendCardDao;
    }

    public VipStrangeSuccessSendMoneyDao getVipStrangeSuccessSendMoneyDao() {
        return vipStrangeSuccessSendMoneyDao;
    }

    public YunServiceDaysDao getYunServiceDaysDao() {
        return yunServiceDaysDao;
    }

}
