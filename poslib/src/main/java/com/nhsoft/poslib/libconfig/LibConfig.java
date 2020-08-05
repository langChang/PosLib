package com.nhsoft.poslib.libconfig;

import com.nhsoft.poslib.entity.Aggregation;
import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.Branch;
import com.nhsoft.poslib.entity.BranchGroup;
import com.nhsoft.poslib.entity.IcCardMessage;
import com.nhsoft.poslib.entity.Login;
import com.nhsoft.poslib.entity.MarketAction;
import com.nhsoft.poslib.entity.PointPolicy;
import com.nhsoft.poslib.entity.PolicyDiscount;
import com.nhsoft.poslib.entity.PolicyMoney;
import com.nhsoft.poslib.entity.PolicyPresent;
import com.nhsoft.poslib.entity.PolicyPromotion;
import com.nhsoft.poslib.entity.PolicyQuantity;
import com.nhsoft.poslib.entity.PosItem;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.SystemRole;
import com.nhsoft.poslib.model.VipUserInfo;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.model.ClientUserBean;
import com.nhsoft.poslib.model.CouponsXmlModel;
import com.nhsoft.poslib.model.GoodsGradeBean;
import com.nhsoft.poslib.model.PointRuleXmlModel;
import com.nhsoft.poslib.model.PosScaleStyleTypeBean;
import com.nhsoft.poslib.model.PromotionResponsBean;
import com.nhsoft.poslib.model.SaleParamsBean;
import com.nhsoft.poslib.model.VipCardConfig;
import com.nhsoft.poslib.model.VipCardTypeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Iverson on 2019-11-27 11:02
 * 此类用于：
 */
public class LibConfig {

    public static String BOOK_SCOPE_ID = "";
    public static String SYSTEM_BOOK;
    public static int BRANCH_NUM;


    public static String BRANCH_NAME;
    public static String SYSTEM_BOOK_NAME;
    public static int SHIFT_TABLE_NUM = 0;
    public static String SHIFT_OPERATOR = "";
    public static String SHIFT_TABLE_BIZDAY = "";
    public static boolean POLICY_PROMOTION_DISABLE_PAY_DISCOUNT = false;
    public static int POS_MACHINE_SEQUENCE = 0;
    public static String POS_MACHINE_TERMINAL_ID = "";
    public static boolean MATRIX_PRICE_ACTIVED;



    public static       String  activeLocalMac;//当前pos机ID
    public static       String  activeLocalMacEth0;//当前pos机 有线网卡的MacID
    /*****************打印小票************/
    public final static int PRINT_ORDER_NOMAL     = 1;// 正常打印小票
    public final static int PRINT_ORDER_REPLENISH = 2;// 补打小票
    public final static int PRINT_QUIT_ORDER      = 3;// 退货小票
    public final static int PRINT_QUERY_ORDER     = 4;// 查询订单页面打印



    public static boolean isOpenSettle = false;
    public static float mScreenWidthScale = 1f;


    public static ShiftTable       activeShiftTable;//营业日和班次信息
    public static PosMachine       activePosMachine;//pos机信息
    public static SaleParamsBean   saleParamsBean;//pos机信息
    public static Login            activeLoginBean;
    public static AppUser          activeAppUser;// 当前登陆用户
    public static Branch           activeBranch;//分店信息
    public static Aggregation      activeAggregation;//仓库
    public static SystemRole       systemRole;//当前用户所属角色
    public static List<SystemRole> systemRoleList;//所有角色SaleParamsBean
    public static List<AppUser>    appUsers;//所有用户
    public static List<Branch>     branches;//所有门店
    public static VipCardTypeBean  activeVipCardTypeBean;//会员卡类型


    public static List<PosItem>           activityDisplayShowGoods    = new ArrayList<>();
    public static List<GoodsGradeBean>    activityShowGoods           = new ArrayList<>();
    public static Map<Long, Float>     sInventoryList              = new HashMap<>();
    public static boolean                 isTerminalGoods;//是否是终端商品设置状态
    public static int                     SHOW_GOODS_TYPE             = 1; // 1主界面展示 2调价单
    public static VipUserInfo             activeVipMember;//全局会员
    public static VipUserInfo             discountVipMember;//全局会员
    public static ClientUserBean          activeClientUser;//全局客户
    public static VipCardConfig           sVipCardParams; //会员卡参数控制
    public static IcCardMessage           activeIcCardMessage;//IC卡的key的bean
//    public static List<VipCRMLevel>    activeVipCRMLevelList =new ArrayList<>();//付费会员价格


    public static List<BranchGroup> branchGroupList = new ArrayList<>();//跟本店同组的所有店

    public static final String S_POS_ORDER_KEY_ITEM   = "POS_ORDER";
    public static final String S_PAYMENT_KEY_ITEM     = "PAYMENT";
    public static final String S_CARD_CHANGE_KEY_ITEM = "CARD_CHANGE";


    /*******************卡操作*******************/
    public static final String S_CARD_CONSUME  = "CARD_CONSUME"; //消费劵
    public static final String S_CARD_DEPOSIT  = "CARD_DEPOSIT"; //存款
    public static final String S_RELAT_CARD    = "RELAT_CARD"; //续卡
    public static final String S_REPLACE_CARD  = "REPLACE_CARD"; //换卡
    public static final String S_SEND_CARD_CRM = "S_SEND_CARD_CRM"; //crm发卡

    /***************************赠券方式**********************************/
    public static String MARKET_ACTION_TYPE_DEPOSIT   = "卡存款赠券";
    public static String MARKET_ACTION_TYPE_CONSUME   = "POS消费赠券";
    public static String MARKET_ACTION_TYPE_OPEN_CARD = "开卡赠券";


    /*******************计算精度常量*******************/
    public static final String S_ROUND_UP        = "舍入";
    public static final String S_ROUND_DOWN      = "舍零";
    public static final String S_ROUND_HALF      = "四舍五入";
    public static final String S_ROUND_HALF_FIVE = "四舍五入到5角";

    public static final String S_PRECISION_YUAN = "元";
    public static final String S_PRECISION_JIAO = "角";
    public static final String S_PRECISION_FEN  = "分";


    /*******************订单明细常量*******************/

    public static final String C_ORDER_DETAIL_TYPE_ITEM    = "商品项目";
    public static final String C_ORDER_DETAIL_TYPE_COUPON  = "消费券";
    //PosOrderDetail 状态
    public final static int    S_ORDER_DETAIL_SALE         = 1;
    public final static String S_ORDER_DETAIL_SALE_NAME    = "销售";
    public final static int    S_ORDER_DETAIL_PRESENT      = 2;
    public final static String S_ORDER_DETAIL_PRESENT_NAME = "赠送";
    public final static int    S_ORDER_DETAIL_RETURN       = 4;
    public final static String S_ORDER_DETAIL_RETURN_NAME  = "退货";
    public final static int    S_ORDER_DETAIL_CANCEL       = 8;
    public final static String S_ORDER_DETAIL_CANCEL_NAME  = "取消";
    public final static int    S_ORDER_DETAIL_ONORDER      = 16;
    public final static String S_ORDER_DETAIL_ONORDER_NAME = "订购";

    /***************************积分上传类型***********************************/
    public static String C_CLIENT_POINT_POS        = "POS消费";
    public static String C_CLIENT_POINT_CHANGE     = "积分兑换";
    public static String C_CLIENT_POINT_DEPOSIT    = "卡存款";
    public static String C_CLIENT_POINT_TO_DEPOSIT = "积分转储值";
    public static String C_CLIENT_POINT_ORI        = "老会员转卡";

    /***************************积分赠送规则***********************************/
    public static String PRESENT_BY_GOODS_POINT          = "按照单品积分计算";
    public static String PRESENT_BY_GOODS_POINT_SIMPLE   = "单品积分";
    public static String PRESENT_BY_COUPONS_MONEY        = "按照消费金额计算";
    public static String PRESENT_BY_COUPONS_MONEY_SIMPLE = "消费积分";
    public static String PRESENT_BY_COUPONS_TIMES        = "按照消费次数计算";
    public static String PRESENT_BY_CARD_STORE           = "按照会员卡储值消费计算";
    public static String PRESENT_BY_CARD_STORE_SIMPLE    = "卡储值积分";
    public static String PRESENT_BY_CARD_CONSUME         = "按照卡类型消费金额计算";
    public static String PRESENT_BY_CARD_CONSUME_SIMPLE  = "卡消费积分";


    public static String POINT_POLICY_TYPE_CONSUME = "储值消费积分促销";
    public static String POINT_POLICY_TYPE_GOODS   = "商品积分促销";


    /***************************POSORDER的状态***********************************/
    public final static int    S_ORDER_INIT          = 1;
    public final static String S_ORDER_INIT_NAME     = "制单";
    public final static int    S_ORDER_ONORDER       = 2;
    public final static String S_ORDER_ONORDER_NAME  = "订购";
    public final static int    S_ORDER_COMPLETE      = 4;
    public final static String S_ORDER_COMPLETE_NAME = "完成";
    public final static int    S_ORDER_CANCEL        = 16;
    public final static String S_ORDER_CANCEL_NAME   = "取消";


    /*******************默认常见支付方式*******************/
    public static final String C_PAYMENT_TYPE_CASH_NAME          = "现金";
    public static final String C_PAYMENT_TYPE_UNIONPAY_NAME      = "银联卡";
    public static final String C_PAYMENT_TYPE_INTEGRAL_NAME      = "积分消费";
    public static final String C_PAYMENT_TYPE_PETCARD_NAME       = "储值卡";
    public static final String C_PAYMENT_TYPE_SIGNBILL_NAME      = "签单";
    public static final String C_PAYMENT_TYPE_COINPURSE_NAME     = "零钱包";
    public static final String C_PAYMENT_TYPE_ALIPAY_NAME        = "支付宝";
    public static final String C_PAYMENT_TYPE_WECHATPAY_NAME     = "微信支付";
    public static final String C_PAYMENT_TYPE_CLOUDFLASHPAY_NAME = "云闪付";
    public static final String C_PAYMENT_TYPE_MOBILEPET_NAME     = "移动和包支付";
    public static final String C_PAYMENT_TYPE_YIPAY_NAME         = "翼支付";
    public static final String C_PAYMENT_TYPE_ONLINE             = "在线支付";
    public static final String C_PAYMENT_TYPE_THIRD_PARTY_ONLINE = "第三方在线支付";
    public static final String C_PAYMENT_TYPE_NONG_XIN_NAME      = "农信支付";
    public static final String C_PAYMENT_TYPE_ALIFACEPAY_NAME    = "支付宝扫脸支付";

    public static List<String> openDrawPayment = new ArrayList<>();


    /***************************商品标记**********************************/
    public static String GOODS_CHANGE_TAG = "手改"; //商品手改标记
    public static String GOODS_VIP_TAG    = "VIP"; //商品手改标记
    public static String GOODS_PROMO_TAG  = "促销"; //商品手改标记
    public static String TICKET_TAG       = "优惠券"; //商品手改标记
    public static String MGR_TAG          = "经理折扣"; //商品手改标记


    public static List<MarketAction>  allOnceMarketAction     = new ArrayList<>(); //所有的增劵活动
    public static List<MarketAction>  allMarketAction;
    public static Map<String, String> sVipEnjoyPromotion      = new HashMap<>();
    public static Map<String, String> sVipEnjoyStallPromotion = new HashMap<>();
    public static Map<String, String> sVipEnjoyMarketAction   = new HashMap<>();


    public static List<PolicyPromotion> allPolicyPromotionList        = new ArrayList<>(); //活动
    public static List<PolicyPromotion> allVipOncePolicyPromotionList = new ArrayList<>(); //活动
    public static List<PolicyPromotion> allVipCardPolicyPromotionList = new ArrayList<>(); //

    public static List<String>               allPromotionLimit                  = new ArrayList<>(); //有活动限制
    public static List<PromotionResponsBean> allPromotionFoundLimit             = new ArrayList<>(); //有活动限制
    public static List<PolicyDiscount>       allPolicyDiscountList              = new ArrayList<>(); //活动
    public static List<PolicyQuantity>       allPolicyQuantityList              = new ArrayList<>(); //超量特价活动
    public static List<PolicyMoney>          allPolicyMoneyList                 = new ArrayList<>(); //超额奖励活动
    public static List<PolicyPresent>        allPolicyPresentList               = new ArrayList<>(); //赠品促销活动
    public static List<CouponsXmlModel>      sCouponsXmlModels; //优惠卷
    public static List<PointRuleXmlModel>    allPointRuleList                   = new ArrayList<>();
    ; //所有的积分规则
    public static List<PointPolicy> allPointPolicyList = new ArrayList<>();
    ; //所有的积分政策活动
    public static List<PosScaleStyleTypeBean> allPosScaleTypeList = new ArrayList<>(); //所有的积分政策活动ArrayList<PosScaleStyleTypeBean>


    /**************************下载下来的资源资料对应的key****************************************/
    public static final String S_PAY_SCALE_STYLE     = "零售支付方式参数"; //取出支付方式
    public static final String S_LOCAL_SCALE_STYLE   = "零售前台销售参数"; //取出前台销售参数D
    public static final String S_LOCAL_CLIENT_STYLE  = "零售客户参数"; //取出基本参数里面的客户
    public static final String S_LOCAL_VIP_TYPE      = "消费卡类型"; //取出消费卡类型
    public static final String S_LOCAL_VIP_STYPE     = "消费卡参数"; //取出消费卡参数
    public static final String S_LOCAL_COUPONS_STYLE = "消费券参数"; //取出消费卡类型
    public static final String S_LOCAL_OTHER_PAYMENT = "费用项目"; //费用项目
    public static final String S_LOCAL_POINT_RULE    = "积分规则"; //取出消费卡类型
    public static final String S_LOCAL_POINT_GOODS   = "积分兑换物"; //取出积分兑换物

    /***********************************收银员的权限***********************************************/
    public static final String PERMISSION_SEND_CARD                   = "发卡";//发卡权限
    public static final String PERMISSION_RENEW_CARD                  = "续卡";//续卡权限
    public static final String PERMISSION_CHANGE_CARD                 = "POS换卡";//换卡权限
    public static final String PERMISSION_LOSS_CARD                   = "POS挂失/解挂";//挂失权限
    public static final String PERMISSION_STRANGE_CARD                = "消费卡存款";//存款权限
    public static final String PERMISSION_EDIT_CARD                   = "POS修改卡信息";//修改权限
    public static final String PERMISSION_AGAINST_DEPOSIT_CARD        = "消费卡反存款";//消费卡反存款权限
    public static final String PERMISSION_POINT_EXCHANGE              = "积分兑换";//积分兑换权限
    public static final String PERMISSION_SELECT_ORDER                = "交易查询";//交易查询权限
    public static final String PERMISSION_COLLECT_ORDER               = "挂单";//交易查询权限
    public static final String PERMISSION_CARD_COUNSE                 = "消费卡消费";//交易查询权限
    public static final String PERMISSION_PRINT_OLD_ORDER             = "重打帐单";//交易查询权限
    public static final String PERMISSION_MANAGET_DISCOUNT            = "经理折扣";//交易查询权限
    public static final String PERMISSION_FOREGOUND_SELL              = "前台销售";//前台销售
    public static final String PERMISSION_CANCEL_GOODS                = "删除";//删除
    public static final String PERMISSION_CANCEL_ALL_GOODS            = "整单删除";//删除
    public static final String PERMISSION_QUIT_GOODS                  = "退货";//退货权限
    public static final String PERMISSION_EXIT_GOODS                  = "反结帐";//反结账权限
    public static final String PERMISSION_CHANGE_GOODS_AMOUNT         = "修改数量";
    public static final String PERMISSION_CHANGE_GOODS_PRICE          = "修改单价";
    public static final String PERMISSION_OPEN_DRAW                   = "打开钱箱";
    public static final String PERMISSION_RECEIVE_AND_CHECK           = "收银对帐";
    public static final String PERMISSION_PRINT_ORDER                 = "打印对账单";
    public static final String PERMISSION_CHECH_SHIFT_TABLE_PAYMENT   = "审核收银缴款单";
    public static final String PERMISSION_CHECH_SHIFT_TABLE_PAYMENT_1 = "查看缴款单系统金额";
    public static final String PERMISSION_SHIFT_TABLE_SUMMARY         = "打印交班汇总报表";
    public static final String PERMISSION_TARANGE_TO_TICKET           = "兑换充值券";
    public static final String PERMISSION_SETTING_GOODS_SHOW          = "终端商品设置";
    public static final String PERMISSION_ADJUST_GOODS                = "商品调价";
    public static final String PERMISSION_PERSON_GOODS               = "赠送";

    public static final String ACTION_SELECT    = "查询";//查询动作
    public static final String ACTION_EDIT      = "编辑";//编辑动作
    public static final String ACTION_DELETE    = "删除";//删除动作
    public static final String ACTION_INVALID   = "无效";//删除动作
    public static final String ACTION_AUDIT     = "审核";//删除动作
    public static final String ACTION_PRINT     = "打印";//删除动作
    public static final String ACTION_EXPORT    = "导出";//删除动作
    public static final String ACTION_RE_EXPORT = "重新审核";//删除动作


    /***************************当前主界面 是收银模式还是退货模式**********************************/
    public static       int S_HOME_SHOW_BY_CURRENT_MODE = 1;//当前默认为收银模式
    public static final int S_HOME_SHOW_BY_CASH         = 1;//收银模式
    public static final int S_HOME_SHOW_BY_RETURN       = 2; //退货模式

}
