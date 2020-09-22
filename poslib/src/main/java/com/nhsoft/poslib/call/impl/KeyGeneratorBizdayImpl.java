package com.nhsoft.poslib.call.impl;

import com.nhsoft.poslib.RetailPosManager;
import com.nhsoft.poslib.R;
import com.nhsoft.poslib.db.DaoManager;
import com.nhsoft.poslib.entity.KeyGeneratorBizday;
import com.nhsoft.poslib.entity.PosMachine;
import com.nhsoft.poslib.entity.shift.ShiftTable;
import com.nhsoft.poslib.libconfig.LibConfig ;
import com.nhsoft.poslib.service.greendao.KeyGeneratorBizdayDao;
import com.nhsoft.poslib.utils.MatterUtils;
import com.nhsoft.poslib.utils.StringUtil;
import com.nhsoft.poslib.utils.TimeUtil;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Iverson on 2018/11/30 9:20 AM
 * 此类用于：
 */

@Entity
public class KeyGeneratorBizdayImpl {

    private static KeyGeneratorBizdayImpl instance;

    public static KeyGeneratorBizdayImpl getInstance() {
        if (instance == null) {
            instance = new KeyGeneratorBizdayImpl();
        }
        return instance;
    }


    public KeyGeneratorBizday createPosOrderKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
//        账套+门店+营业日+3位数字终端号+流水号
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getYearCurrentDate(shiftTableBizday) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.detachAll();
        return keyGeneratorBizday;
    }

    public KeyGeneratorBizday createOtherRevenueKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
//        账套+门店+营业日+3位数字终端号+流水号
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getYearCurrentDate(shiftTableBizday) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.detachAll();
        return keyGeneratorBizday;
    }

    /**
     * 外部流水号
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    public String createOutPosOrderNo(int branchNum, String shiftTableBizday,int posMachineSequence) {

        return getCharPre(posMachineSequence) + getBranchNumChar(String.valueOf(branchNum)) + getYearCurrentDate(shiftTableBizday) +
                TimeUtil.getTimeByhsm(System.currentTimeMillis()) + StringUtil.getRandStr(2);
    }

    public KeyGeneratorBizday createPaymentKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
            keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
            keyGeneratorBizdayDao.insertOrReplace(keyGeneratorBizday);
        }
        String keyPaymentCBD = getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getYearCurrentDate(shiftTableBizday) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getKeyGeneratorNum(String.valueOf(keyValue));
        keyGeneratorBizday.setKeyGBString(keyPaymentCBD);
        return keyGeneratorBizday;
    }


    public KeyGeneratorBizday createCardChangeKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
            keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
            keyGeneratorBizdayDao.insertOrReplace(keyGeneratorBizday);
        }
        String keyPaymentCBD = getCharPre(posMachineSequence) + getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getYearCurrentDate(shiftTableBizday) +
                getKeyGeneratorNum(String.valueOf(keyValue)) + StringUtil.getRandStr(2);
        keyGeneratorBizday.setKeyGBString(keyPaymentCBD);
        return keyGeneratorBizday;
    }


    public KeyGeneratorBizday createCouponsKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
        keyGeneratorBizday.setKeyGBString((LibConfig.saleParamsBean == null ? "" : LibConfig.saleParamsBean.getPrefixOfBarCode()) + getBranchNumChar(String.valueOf(branchNum)) + posMachineSequence + getYearCurrentDate(shiftTableBizday) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        keyGeneratorBizdayDao.detachAll();
        return keyGeneratorBizday;
    }

    public String getCouponsPre(String systemCode, int branchNum, String shiftTableBizday, int posMachineSequence) {
        return (LibConfig.saleParamsBean == null ? "" : LibConfig.saleParamsBean.getPrefixOfBarCode()) + getBranchNumChar(String.valueOf(branchNum)) + posMachineSequence + getYearCurrentDate(shiftTableBizday);
    }


    /**
     * systemCode 帐套号
     * branchNum 分店编号
     * shiftTableBizday 营业日
     * keyItem    LibConfig 中String S_CARD_DEPOSIT       = "S_CARD_DEPOSIT"; //存款
     * posMachineSequence 机器编号  int 类型的
     */
    public KeyGeneratorBizday createDepositKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) +
                getKeyMechineStr(String.valueOf(posMachineSequence)) + getYearCurrentDate(shiftTableBizday) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        keyGeneratorBizdayDao.detachAll();
        return keyGeneratorBizday;
    }


    /**
     * systemCode 帐套号
     * branchNum 分店编号
     * shiftTableBizday 营业日
     * keyItem    LibConfig 中String S_RELAT_CARD       = "S_RELAT_CARD"; //续卡
     * posMachineSequence 机器编号  int 类型的
     */
    public KeyGeneratorBizday createRelatCardKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getYearCurrentDate(shiftTableBizday) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        keyGeneratorBizdayDao.detachAll();

        return keyGeneratorBizday;
    }

    /**
     * systemCode 帐套号
     * branchNum 分店编号
     * shiftTableBizday 营业日
     * keyItem    LibConfig 中String S_RELAT_CARD       = "S_RELAT_CARD"; //续卡
     * posMachineSequence 机器编号  int 类型的
     */
    public KeyGeneratorBizday createSendCardCRM(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue()+ 1 ;
            keyGeneratorBizday.setKeyValue(keyValue);
//            keyGeneratorBizdayDao.save(keyGeneratorBizday);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getYearCurrentDate(shiftTableBizday) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        keyGeneratorBizdayDao.detachAll();

        return keyGeneratorBizday;
    }


    /**
     * systemCode 帐套号
     * branchNum 分店编号
     * shiftTableBizday 营业日
     * keyItem    LibConfig 中String REPLACE_CARD       = "REPLACE_CARD"; //换卡
     * posMachineSequence 机器编号  int 类型的
     */
    public KeyGeneratorBizday createReplaceCardKG(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int posMachineSequence) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = keyGeneratorBizdayDao.queryBuilder().where(KeyGeneratorBizdayDao.Properties.SystemBookCode.eq(systemCode),
                KeyGeneratorBizdayDao.Properties.BranchNum.eq(branchNum),
                KeyGeneratorBizdayDao.Properties.ShiftTableBizday.eq(shiftTableBizday),
                KeyGeneratorBizdayDao.Properties.KeyItem.eq(keyItem)).unique();
        int keyValue = 1;
        if (keyGeneratorBizday != null) {
            keyValue = keyGeneratorBizday.getKeyValue() + 1;
            keyGeneratorBizday.setKeyValue(keyValue);
        } else {
            keyGeneratorBizday = new KeyGeneratorBizday();
            keyGeneratorBizday.setSystemBookCode(systemCode);
            keyGeneratorBizday.setBranchNum(branchNum);
            keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
            keyGeneratorBizday.setKeyItem(keyItem);
            keyGeneratorBizday.setKeyValue(keyValue);
        }
        keyGeneratorBizday.setKeyGBString(getSystemCodeChar(systemCode) + getBranchNumChar(String.valueOf(branchNum)) + getKeyMechineStr(String.valueOf(posMachineSequence)) + getYearCurrentDate(shiftTableBizday) + getKeyGeneratorNum(String.valueOf(keyValue)));
        keyGeneratorBizdayDao.insertOrReplaceInTx(keyGeneratorBizday);
        keyGeneratorBizdayDao.detachAll();

        return keyGeneratorBizday;
    }


    public void saveKeyGeneratorBizday(KeyGeneratorBizday keyGeneratorBizday) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        ShiftTable shiftTable = LibConfig.activeShiftTable;
        PosMachine posMachine = LibConfig.activePosMachine;
        KeyGeneratorBizday newKgb = KeyGeneratorBizdayImpl.getInstance().createPosOrderKG(shiftTable.getSystemBookCode(), shiftTable.getBranchNum(), shiftTable.getShiftTableBizday(), LibConfig.S_POS_ORDER_KEY_ITEM, posMachine.getPos_machine_sequence());
        if (newKgb.getKeyGBString().equals(keyGeneratorBizday.getKeyGBString())) {
            keyGeneratorBizdayDao.insertOrReplace(keyGeneratorBizday);
        }
    }

    public List<KeyGeneratorBizday> getAllList(){
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        return keyGeneratorBizdayDao.loadAll();
    }

    public boolean deleteAllList(){
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        return MatterUtils.doMatter(keyGeneratorBizdayDao, new Runnable() {
            @Override
            public void run() {
                keyGeneratorBizdayDao.deleteAll();
            }
        });
    }


    /**
     * 数据库没有的时候设置最大posorder值
     * @param systemCode
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    public boolean setMaxPosOrderAndPaymentValue(String systemCode, int branchNum, String shiftTableBizday, final int poskeyValue, final int paymentkeyValue) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        final KeyGeneratorBizday keyGeneratorPos = new KeyGeneratorBizday();
        final KeyGeneratorBizday keyGeneratorPayment = new KeyGeneratorBizday();

        keyGeneratorPos.setSystemBookCode(systemCode);
        keyGeneratorPos.setBranchNum(branchNum);
        keyGeneratorPos.setShiftTableBizday(shiftTableBizday);

        if(paymentkeyValue != -1){
            keyGeneratorPayment.setSystemBookCode(systemCode);
            keyGeneratorPayment.setBranchNum(branchNum);
            keyGeneratorPayment.setShiftTableBizday(shiftTableBizday);
        }

        return MatterUtils.doMatter(keyGeneratorBizdayDao, new Runnable() {
            @Override
            public void run() {
                keyGeneratorPos.setKeyItem(LibConfig.S_POS_ORDER_KEY_ITEM);
                keyGeneratorPos.setKeyValue(poskeyValue);
                keyGeneratorBizdayDao.insert(keyGeneratorPos);

                if(paymentkeyValue != -1){
                    keyGeneratorPayment.setKeyItem(LibConfig.S_PAYMENT_KEY_ITEM);
                    keyGeneratorPayment.setKeyValue(paymentkeyValue+1000);
                    keyGeneratorBizdayDao.insert(keyGeneratorPayment);
                }
            }
        });
    }

    /**
     * 数据库没有的时候设置最大posorder值
     *
     * @param systemCode
     * @param branchNum
     * @param shiftTableBizday
     * @return
     */
    public boolean setMaxDepositValue(String systemCode, int branchNum, String shiftTableBizday, final int poskeyValue) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        final KeyGeneratorBizday keyGeneratorPos = new KeyGeneratorBizday();
        final KeyGeneratorBizday keyGeneratorPayment = new KeyGeneratorBizday();

        keyGeneratorPos.setSystemBookCode(systemCode);
        keyGeneratorPos.setBranchNum(branchNum);
        keyGeneratorPos.setShiftTableBizday(shiftTableBizday);

        keyGeneratorPayment.setSystemBookCode(systemCode);
        keyGeneratorPayment.setBranchNum(branchNum);
        keyGeneratorPayment.setShiftTableBizday(shiftTableBizday);

        return MatterUtils.doMatter(keyGeneratorBizdayDao, new Runnable() {
            @Override
            public void run() {
                keyGeneratorPos.setKeyItem(LibConfig.S_CARD_DEPOSIT);
                keyGeneratorPos.setKeyValue(poskeyValue);
                keyGeneratorBizdayDao.insert(keyGeneratorPos);
                keyGeneratorBizdayDao.insert(keyGeneratorPayment);
            }
        });
    }

    public boolean saveMaxCouponsPrintNum(String systemCode, int branchNum, String shiftTableBizday, final int maxPrintNum) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        final KeyGeneratorBizday keyGeneratorCoupons = new KeyGeneratorBizday();

        keyGeneratorCoupons.setSystemBookCode(systemCode);
        keyGeneratorCoupons.setBranchNum(branchNum);
        keyGeneratorCoupons.setShiftTableBizday(shiftTableBizday);

        return MatterUtils.doMatter(keyGeneratorBizdayDao, new Runnable() {
            @Override
            public void run() {
                keyGeneratorCoupons.setKeyItem(LibConfig.S_CARD_CONSUME);
                keyGeneratorCoupons.setKeyValue(maxPrintNum);
                keyGeneratorBizdayDao.insert(keyGeneratorCoupons);
            }
        });

    }

    /**
     * 数据库没有的时候设置最大payment值
     *
     * @param systemCode
     * @param branchNum
     * @param shiftTableBizday
     * @param keyItem
     * @param keyValue
     */
    public boolean setMaxPaymentValue(String systemCode, int branchNum, String shiftTableBizday, String keyItem, int keyValue) {
        final KeyGeneratorBizdayDao keyGeneratorBizdayDao = DaoManager.getInstance().getDaoSession().getKeyGeneratorBizdayDao();
        KeyGeneratorBizday keyGeneratorBizday = new KeyGeneratorBizday();
        keyGeneratorBizday.setSystemBookCode(systemCode);
        keyGeneratorBizday.setBranchNum(branchNum);
        keyGeneratorBizday.setShiftTableBizday(shiftTableBizday);
        keyGeneratorBizday.setKeyItem(keyItem);
        keyGeneratorBizday.setKeyValue(keyValue);
        return keyGeneratorBizdayDao.insertOrReplace(keyGeneratorBizday) == 1;
    }


    //获取字母前缀
    public String getCharPre(int posMachineSequence) {
        posMachineSequence--;
        String[] charArray = RetailPosManager.sContext.getResources().getStringArray(R.array.C_CHARCATE);
        StringBuilder charBuilder = new StringBuilder();
        if (posMachineSequence < 25) {
            charBuilder.append(String.valueOf(charArray[posMachineSequence]));
        } else if (posMachineSequence < 25 * 25 * 25) {
            int firstChar = posMachineSequence / (25);
            if (firstChar > 25) {
                int secondChar = firstChar % 25;
                firstChar = firstChar / 25;
                if (secondChar == 0) {
                    secondChar = 25;
                    firstChar--;
                }
                charBuilder.append(charArray[firstChar - 1]).append(charArray[secondChar - 1]);
            } else {
                charBuilder.append(charArray[firstChar - 1]);
            }
            int lastChar = posMachineSequence % 25;
            charBuilder.append(charArray[lastChar]);
        }
        return charBuilder.toString();
    }

    //获取帐套号，不够4位补0
    public String getSystemCodeChar(String systemCode) {
        String converingText = "";
        if (systemCode.length() < 4) {
            for (int i = 4 - systemCode.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + systemCode;
    }

    //获取分店号，不够2位补0
    public String getBranchNumChar(String branchNum) {
        String converingText = "";
        if (branchNum.length() < 2) {
            for (int i = 2 - branchNum.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + branchNum;
    }

    //获取分店号，不够4位补0
    public String getBranchNumLongChar(String branchNum) {
        String converingText = "";
        if (branchNum.length() < 4) {
            for (int i = 4 - branchNum.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + branchNum;
    }

    //获取分店号，不够4位补0
    public String getMerchatNumLongChar(String merchatNum) {
        String converingText = "";
        if (merchatNum.length() < 6) {
            for (int i = 6 - merchatNum.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + merchatNum;
    }

    public String getYearCurrentDate(String shiftTableBizday) {
        Date date = TimeUtil.getInstance().getDateByString(shiftTableBizday);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        String daytext = String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
        if (date != null) {
            if (daytext.length() < 3) {
                String converingText = "";
                for (int i = 3 - daytext.length(); i > 0; i--) {
                    converingText = "0" + converingText;
                }
                daytext = converingText + daytext;
            }
        }
        return String.valueOf(year).substring(2, 4) + daytext;
    }

    public String getTodayShiftBizday() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String monthStr;
        if (month + 1 < 10) {
            monthStr = "0" + String.valueOf(month + 1);
        } else {
            monthStr = String.valueOf(month + 1);
        }
        String dayStr;
        if (day < 10) {
            dayStr = "0" + String.valueOf(day);
        } else {
            dayStr = String.valueOf(day);
        }

        return year + monthStr + dayStr;
    }

    //获取流水号，不够4位补0
    public String getKeyGeneratorNum(String keyValue) {
        String converingText = "";
        if (keyValue.length() < 4) {
            for (int i = 4 - keyValue.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + keyValue;
    }

    //机器编码
    public String getKeyMechineStr(String keyValue) {
        String converingText = "";
        if (keyValue.length() < 3) {
            for (int i = 3 - keyValue.length(); i > 0; i--) {
                converingText = "0" + converingText;
            }
        }
        return converingText + keyValue;
    }


    public String getPayOutId(String systemCode, int branchNum) {
        return getSystemCodeChar(systemCode) + getBranchNumLongChar(String.valueOf(branchNum));
    }
}
