package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hz19084340 小崔
 * @create: 2020-03-19 11:19
 * @Description:
 */
public class MD5Test {

//    public static void main(String[] args) {
//        String key = "TNZDRkNjczNDZkZWZ1046916";
//        String parmJson = "{\n" + "\"transNo\":\"huize20171027100140000003\",\n" + "\"partnerId\":\"1046916\",\n" + "\"startDate\":\"2020-03-29\",\n" + "\"caseCode\":\"QX000000128582\",\n" + "\"newRestrictGenes\":[{\"sort\":8,\"protectItemId\":9119,\"key\":\"\",\"value\":\"10万元\"},{\"sort\":1,\"protectItemId\":\"\",\"key\":\"vesterAge\",\"value\":\"2002-03-12\"},{\"sort\":2,\"protectItemId\":\"\",\"key\":\"vesterSex\",\"value\":\"男\"},{\"sort\":3,\"protectItemId\":\"\",\"key\":\"insureForSelf\",\"value\":\"否\"},{\"sort\":4,\"protectItemId\":\"\",\"key\":\"insurantDate\",\"value\":\"2000-01-01\"},{\"sort\":5,\"protectItemId\":\"\",\"key\":\"sex\",\"value\":\"男\"},{\"sort\":6,\"protectItemId\":\"\",\"key\":\"insurantJob\",\"value\":\"1-6类（剔除高危）\"},{\"sort\":7,\"protectItemId\":\"\",\"key\":\"coverageAreaName\",\"value\":\"重庆市\"},{\"sort\":9,\"protectItemId\":\"\",\"key\":\"insurantDateLimit\",\"value\":\"至70岁\"},{\"sort\":10,\"protectItemId\":\"\",\"key\":\"paymentType\",\"value\":\"年交\"},{\"sort\":11,\"protectItemId\":\"\",\"key\":\"insureAgeLimit\",\"value\":\"5年\"},{\"sort\":12,\"protectItemId\":\"\",\"key\":\"isInsure\",\"value\":\"含\"},{\"sort\":13,\"protectItemId\":\"\",\"key\":\"isInsureTwo\",\"value\":\"含\"},{\"sort\":14,\"protectItemId\":\"\",\"key\":\"isInsureThree\",\"value\":\"含\"},{\"sort\":15,\"protectItemId\":\"\",\"key\":\"premiumExemption\",\"value\":\"含\"},{\"sort\":16,\"protectItemId\":\"\",\"key\":\"additionalInsureAgeLimit\",\"value\":\"4年\"}],\n" + "\"oldRestrictGene\":{\"sort\":4,\"protectItemId\":\"\",\"key\":\"insurantDate\",\"value\":\"2019-03-29\"}\n" + "}";
//	String reSign = Md5Utils.getMD5String(key + parmJson);
//	System.out.println(reSign);
//    }
    public static void print(List<Integer> list) {
	list.add(2);
	list = new ArrayList<Integer>();
	list.add(3);
	list.add(4);
    }

    public static void main(String[] args) {
	List<Integer> list = new ArrayList<Integer>();
	list.add(1);
	print(list);
	System.out.println(list.get(0));
    }
}
