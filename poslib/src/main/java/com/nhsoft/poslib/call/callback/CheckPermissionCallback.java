package com.nhsoft.poslib.call.callback;

import com.nhsoft.poslib.entity.AppUser;
import com.nhsoft.poslib.entity.SystemRole;

import java.util.List;

/**
 * Created by Iverson on 2019-11-28 14:13
 * 此类用于：
 */
public interface CheckPermissionCallback {

    int checkPermission(AppUser appUser, String privilageName, String operatorName, List<SystemRole> roleList);

}
