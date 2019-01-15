package com.xuecheng.framework.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum TimeUnitEnum {
    HOUR(0, "hour", "小时"),
    DAY(1, "day", "天"),
    WEEK(2, "week", "周"),
    MONTH(3, "month", "月"),
    QUARTER(4, "quarter", "季度"),
    YEAR(5, "year", "年"),
    MINUTE(6, "minute", "分"),
    SECOND(7, "second", "秒");

    private int code;
    private String name;
    private String desc;

    private TimeUnitEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("code", Integer.valueOf(this.code));
        json.put("name", this.name);
        json.put("desc", this.desc);
        return json;
    }

    public static JSONArray toJsonArr() {
        JSONArray arr = new JSONArray();
        TimeUnitEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TimeUnitEnum type = var1[var3];
            arr.add(type.toJson());
        }

        return arr;
    }

    public static TimeUnitEnum getEnumBycode(int code) {
        TimeUnitEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TimeUnitEnum _enum = var1[var3];
            if(code == _enum.getCode()) {
                return _enum;
            }
        }

        return null;
    }

    public static List<TimeUnitEnum> planTriggerInterval() {
        List<TimeUnitEnum> planTriIn = new ArrayList();
        planTriIn.add(YEAR);
        planTriIn.add(QUARTER);
        planTriIn.add(MONTH);
        planTriIn.add(DAY);
        return planTriIn;
    }

    public static JSONArray toPlanTriggerInterval() {
        List<TimeUnitEnum> planTriIn = planTriggerInterval();
        JSONArray arr = new JSONArray();

        for(int i = values().length - 1; i > -1; --i) {
            TimeUnitEnum _enum = values()[i];
            if(planTriIn.contains(_enum)) {
                arr.add(_enum.toJson());
            }
        }

        return arr;
    }

    public static boolean isIllegalPlanTriggerInterval(int code) {
        List<TimeUnitEnum> planTriIn = planTriggerInterval();
        boolean flag = true;
        Iterator var3 = planTriIn.iterator();

        while(var3.hasNext()) {
            TimeUnitEnum _enum = (TimeUnitEnum)var3.next();
            if(code == _enum.getCode()) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static TimeUnitEnum getEnumCodeByDesc(String desc) {
        TimeUnitEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TimeUnitEnum _enum = var1[var3];
            if(_enum.getDesc().equals(desc)) {
                return _enum;
            }
        }

        return null;
    }
}