package com.imk.mall.by.test;

import java.util.Map;
import java.util.HashMap;
import com.sds.anyframe.extension.basic.abstractlayer.AbstractSVO;
import com.sds.anyframe.extension.basic.util.VOUtil;
import com.imk.anyframe.xplatform.XPlatformDataUtil;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.imk.mall.cm.cm.webserviceapp.DateAdapter;


@SuppressWarnings({"serial", "unchecked"})
public class TestVo extends AbstractSVO {

	private String param1;

	private String param2;

	private String param3;

	private String param4;

	private String param5;


    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }


    /**
     * Map의 내용을 VO로 변환한다.
     *
     * @name_ko	Map->VO 변환 메소드
     * @param 	변환될 Map
     */
    public void fromMap(Map map){
        setParam1(VOUtil.getString(map, "param1"));
        setParam2(VOUtil.getString(map, "param2"));
        setParam3(VOUtil.getString(map, "param3"));
        setParam4(VOUtil.getString(map, "param4"));
        setParam5(VOUtil.getString(map, "param5"));
    }

    /**
     * VO의 내용을 Map으로 변환한다.
     *
     * @name_ko VO->Map 변환 메소드
     * @return Map 변환된 Map
     */
    public Map toMap(){
    	Map result = new HashMap();
        result.put("param1",getParam1());
        result.put("param2",getParam2());
        result.put("param3",getParam3());
        result.put("param4",getParam4());
        result.put("param5",getParam5());
        return result;
    }
}
