package com.moodshop.express;

import com.moodshop.comm.utils.ExpressUtils;

public class ExpressTest {

	public static void main(String[] args) {
		String rspjson=ExpressUtils.getExpressInfo("EMS", "9892203740666");
		System.out.println(rspjson);
	}

}
