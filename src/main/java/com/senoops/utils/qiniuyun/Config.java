package com.senoops.utils.qiniuyun;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理中遇到的一些常量
 * @author Axin
 * @date 2018-09-13 13:36:00
 *	
 */
public class Config {
	

	public static final String SMALL_ENTERPRISE = "1";//小企业会计制度
	public static final String ENTERPRISE = "2"; //企业会计制度
	public static final String NPO = "3"; //非营利组织
	
	public static final Map<String,String> STANDARD = new HashMap<>();
	static {
		STANDARD.put(SMALL_ENTERPRISE, SMALL_ENTERPRISE);
		STANDARD.put(ENTERPRISE, ENTERPRISE);
		STANDARD.put(NPO, NPO);
	}
	
	/**
	 * 科目编码默认长度规则
	 */
	public static final String ACCOUNT_DEFAULT_LENGTH = "4322";
	
	/**
	 * 科目方向
	 */
	public static final String JIE = "1"; //借方
	public static final String DAI = "2";//贷方

	public static final Map<String,String> ACCOUNT_DIRECT = new HashMap<>();
	static{
		ACCOUNT_DIRECT.put(JIE, JIE);
		ACCOUNT_DIRECT.put(DAI, DAI);
		
	}
	
	/**
	 * 科目类型
	 */
	public static final String ZI_CHAN = "1";//资产
	public static final String FU_ZHAI = "2";//负债
	public static final String GONG_TONG = "3";//共同
	public static final String QUAN_YI = "4";//权益
	public static final String CHENG_BEN = "5";//成本
	public static final String SUN_YI = "6";//损益
	public static final String JING_ZI_CHAN = "7";//净资产
	public static final String SHOU_RU = "8";//收入
	public static final String FEI_YONG = "9";//费用
	
	public static final Map<String,String> ACCOUNT_TYPE = new HashMap<>();
	static{
		ACCOUNT_TYPE.put(ZI_CHAN, ZI_CHAN);
		ACCOUNT_TYPE.put(FU_ZHAI, FU_ZHAI);
		ACCOUNT_TYPE.put(GONG_TONG, GONG_TONG);
		ACCOUNT_TYPE.put(QUAN_YI, QUAN_YI);
		ACCOUNT_TYPE.put(CHENG_BEN, CHENG_BEN);
		ACCOUNT_TYPE.put(SUN_YI, SUN_YI);
		ACCOUNT_TYPE.put(JING_ZI_CHAN, JING_ZI_CHAN);
		ACCOUNT_TYPE.put(SHOU_RU, SHOU_RU);
		ACCOUNT_TYPE.put(FEI_YONG, FEI_YONG);
		
	}
	
	
	
	
	/**
	 * 本年利润科目编码 <期末结转模块>
	 */
	public static final Map<String,String> PROFIT_ACCOUNT_CODE = new HashMap<>();
	static{
		PROFIT_ACCOUNT_CODE.put(SMALL_ENTERPRISE, "3103");
		PROFIT_ACCOUNT_CODE.put(ENTERPRISE, "4103");
		
	}
	
	/**
	 * 主营业务成本编码<期末结转模块>
	 */
	public static final Map<String,String> MAIN_COST_CODE = new HashMap<>();
	static{
		MAIN_COST_CODE.put(SMALL_ENTERPRISE, "5401");
		MAIN_COST_CODE.put(ENTERPRISE, "6401");
		
	}
	
	
	/**
	 * 主营业务收入编码<期末结转模块>
	 */
	public static final Map<String,String> MAIN_INCOME_CODE = new HashMap<>();
	static{
		MAIN_INCOME_CODE.put(SMALL_ENTERPRISE, "5001");
		MAIN_INCOME_CODE.put(ENTERPRISE, "6001");
		
	}
	
	/**
	 * 库存商品编码<期末结转模块>
	 */
	public static final Map<String,String> STOCK_CODE = new HashMap<>();
	static{
		STOCK_CODE.put(SMALL_ENTERPRISE, "1405");
		STOCK_CODE.put(ENTERPRISE, "1405");
		
	}
	
	/**
	 * 应付职工薪酬编码<期末结转模块>
	 */
	public static final Map<String,String> SALARY_CODE = new HashMap<>();
	static{
		SALARY_CODE.put(SMALL_ENTERPRISE, "2211");
		SALARY_CODE.put(ENTERPRISE, "2211");
		
	}
	
	/**
	 * 累计折旧编码<期末结转模块>
	 */
	public static final Map<String,String> ZHE_JIU_CODE = new HashMap<>();
	static{
		ZHE_JIU_CODE.put(SMALL_ENTERPRISE, "1602");
		ZHE_JIU_CODE.put(ENTERPRISE, "1602");
		
	}
	
	
	/**
	 * 长期待摊费用科目编码<期末结转模块>
	 */
	public static final Map<String,String> DAI_TAN_FEE_CODE = new HashMap<>();
	static{
		DAI_TAN_FEE_CODE.put(SMALL_ENTERPRISE, "1801");
		DAI_TAN_FEE_CODE.put(ENTERPRISE, "1801");
		
	}
	
	
	/**
	 * 税金及附加科目编码 <期末结转模块>
	 */
	public static final Map<String,String> TAX_ADD_CODE = new HashMap<>();
	static{
		TAX_ADD_CODE.put(SMALL_ENTERPRISE, "5403");
		TAX_ADD_CODE.put(ENTERPRISE, "6403");
	}
	
	/**
	 * 应交税费-应交增值税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> ZENG_ZHI_TAX_CODE = new HashMap<>();
	static{
		ZENG_ZHI_TAX_CODE.put(SMALL_ENTERPRISE, "2221001"); 
		ZENG_ZHI_TAX_CODE.put(ENTERPRISE, "2221001");
	}
	
	/**
	 * 应交税费-未交增值税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> UNPAID_ZENG_ZHI_TAX_CODE = new HashMap<>();
	static{
		UNPAID_ZENG_ZHI_TAX_CODE.put(SMALL_ENTERPRISE, "2221002"); 
		UNPAID_ZENG_ZHI_TAX_CODE.put(ENTERPRISE, "2221002");
	}
	
	/**
	 * 应交税费-应交消费税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> XIAO_FEI_TAX_CODE = new HashMap<>();
	static{
		XIAO_FEI_TAX_CODE.put(SMALL_ENTERPRISE, "2221003"); 
		XIAO_FEI_TAX_CODE.put(ENTERPRISE, "2221003");
	}
	
	
	/**
	 * 应交税费-应交营业税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> YING_YE_TAX_CODE = new HashMap<>();
	static{
		YING_YE_TAX_CODE.put(SMALL_ENTERPRISE, "2221004"); 
		YING_YE_TAX_CODE.put(ENTERPRISE, "2221004");
	}
	
	/**
	 * 应交税费-应交所得税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> INCOME_TAX_CODE = new HashMap<>();
	static{
		INCOME_TAX_CODE.put(SMALL_ENTERPRISE, "2221006"); 
		INCOME_TAX_CODE.put(ENTERPRISE, "2221006");
	}
	
	/**
	 * 应交税费-应交城市维护建设税科目编码 <期末结转模块>
	 */
	public static final Map<String,String> CITY_BUILD_TAX_CODE = new HashMap<>();
	static{
		CITY_BUILD_TAX_CODE.put(SMALL_ENTERPRISE, "2221008"); 
		CITY_BUILD_TAX_CODE.put(ENTERPRISE, "2221008");
	}
	
	
	/**
	 * 应交税费-教育费附加科目编码 <期末结转模块>
	 */
	public static final Map<String,String> EDU_FEE_ADD_CODE = new HashMap<>();
	static{
		EDU_FEE_ADD_CODE.put(SMALL_ENTERPRISE, "2221013"); 
		EDU_FEE_ADD_CODE.put(ENTERPRISE, "2221013");
	}
	

	/**
	 * 应交税费-地方教育费附加科目编码 <期末结转模块>
	 */
	public static final Map<String,String> LOCAL_EDU_FEE_ADD_CODE = new HashMap<>();
	static{
		LOCAL_EDU_FEE_ADD_CODE.put(SMALL_ENTERPRISE, "2221014"); 
		LOCAL_EDU_FEE_ADD_CODE.put(ENTERPRISE, "2221014");
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 应交税费-应交增值税-进项税额科目编码 <主页模块-财务指标>
	 */
	public static final Map<String,String> JIN_XIANG_TAX = new HashMap<>();
	static{
		JIN_XIANG_TAX.put(SMALL_ENTERPRISE, "222100101"); 
		JIN_XIANG_TAX.put(ENTERPRISE, "222100101");
	}
	
	/**
	 * 应交税费-应交增值税-销项税额科目编码  <主页模块-财务指标>
	 */
	public static final Map<String,String> XIAO_XIANG_TAX = new HashMap<>();
	static{
		XIAO_XIANG_TAX.put(SMALL_ENTERPRISE, "222100105"); 
		XIAO_XIANG_TAX.put(ENTERPRISE, "222100105");
	}
	
	/**
	 * 应交税费-应交增值税-进项税额转出科目编码  <主页模块-财务指标>
	 */
	public static final Map<String,String> JIN_XIANG_TAX_ZHUAN_CHU = new HashMap<>();
	static{
		JIN_XIANG_TAX_ZHUAN_CHU.put(SMALL_ENTERPRISE, "222100107"); 
		JIN_XIANG_TAX_ZHUAN_CHU.put(ENTERPRISE, "222100107");
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 货币资金<资产负债表项目行号>
	 */
	public static final Map<String,String> HUO_BI_ZI_JIN_LINENUM = new HashMap<>();
	static{
		HUO_BI_ZI_JIN_LINENUM.put(SMALL_ENTERPRISE, "1"); 
		HUO_BI_ZI_JIN_LINENUM.put(ENTERPRISE, "1");
	}
	/**
	 * 应收账款<资产负债表项目行号>
	 */
	public static final Map<String,String> YING_SHOU_LINENUM = new HashMap<>();
	static{
		YING_SHOU_LINENUM.put(SMALL_ENTERPRISE, "4"); 
		YING_SHOU_LINENUM.put(ENTERPRISE, "4");
	}
	/**
	 * 存货<资产负债表项目行号>
	 */
	public static final Map<String,String> CUN_HUO_LINENUM = new HashMap<>();
	static{
		CUN_HUO_LINENUM.put(SMALL_ENTERPRISE, "9"); 
		CUN_HUO_LINENUM.put(ENTERPRISE, "9");
	}
	/**
	 * 固定资产<资产负债表项目行号>
	 */
	public static final Map<String,String> FIXED_ASSETS_LINENUM = new HashMap<>();
	static{
		FIXED_ASSETS_LINENUM.put(SMALL_ENTERPRISE, "20"); 
		FIXED_ASSETS_LINENUM.put(ENTERPRISE, "18");
	}
	
	/**
	 * 应付账款<资产负债表项目行号>
	 */
	public static final Map<String,String> YING_FU_LINENUM = new HashMap<>();
	static{
		YING_FU_LINENUM.put(SMALL_ENTERPRISE, "35"); 
		YING_FU_LINENUM.put(ENTERPRISE, "35");
	}
	
	/**
	 * 应交税费<资产负债表项目行号>
	 */
	public static final Map<String,String> YING_JIAO_TAX_LINENUM = new HashMap<>();
	static{
		YING_JIAO_TAX_LINENUM.put(SMALL_ENTERPRISE, "36"); 
		YING_JIAO_TAX_LINENUM.put(ENTERPRISE, "38");
	}
	
	
	
	
	
	/**
	 * 营业收入<利润表项目行号>
	 */
	public static final Map<String,String> YING_YE_SHOU_RU_LINENUM = new HashMap<>();
	static{
		YING_YE_SHOU_RU_LINENUM.put(SMALL_ENTERPRISE, "1"); 
		YING_YE_SHOU_RU_LINENUM.put(ENTERPRISE, "1");
	}
	
	/**
	 * 利润额（利润总额）<利润表项目行号>
	 */
	public static final Map<String,String> AMOUNT_OF_PROFIT_LINENUM = new HashMap<>();
	static{
		AMOUNT_OF_PROFIT_LINENUM.put(SMALL_ENTERPRISE, "30"); 
		AMOUNT_OF_PROFIT_LINENUM.put(ENTERPRISE, "16");
	}
	
	
	/**
	 * 净利润<利润表项目行号>
	 */
	public static final Map<String,String> PROFIT_LINENUM = new HashMap<>();
	static{
		PROFIT_LINENUM.put(SMALL_ENTERPRISE, "32"); 
		PROFIT_LINENUM.put(ENTERPRISE, "18");
	}
	
	
	/**
	 * 账期状态
	 */
	public static final String NORMAL = "1";//进行中
	public static final String CARRIED_FORWARD = "2";//已结转
	public static final String CLOSED = "3";//已结账
	public static final Map<String,String> PERIOD_STATUS = new HashMap<>();
	static{ 
		PERIOD_STATUS.put(NORMAL, NORMAL);
		PERIOD_STATUS.put(CARRIED_FORWARD, CARRIED_FORWARD);
		PERIOD_STATUS.put(CLOSED, CLOSED);
	}
	
	
	/**
	 * 凭证类型
	 * 
	 */
	
	public static final String JI_TI_ZHE_JIU = "1";//计提 折旧
	public static final String JIE_ZHUAN_CHENG_BEN = "2";//结转销售成本
	public static final String JI_TI_XIN_CHOU = "3";//计提职工薪酬
	public static final String TAN_XIAO_FEI_YONG = "4";//摊销待摊费用
	public static final String JI_TI_SHUI_JIN = "5";//计提税金及附加
	public static final String JIE_ZHUAN_ZENG_ZHI_TAX = "6";//结转未交增值税
	public static final String JI_TI_SUO_DE_TAX = "7";//计提所得税
	public static final String JI_TI_HUAI_ZHANG = "8";//计提坏账
	public static final String JIE_ZHUAN_SUN_YI = "9";//结转损益
	
	public static final Map<String,String> VOUCHER_TYPE = new HashMap<>();
	
	static{
		VOUCHER_TYPE.put(JI_TI_ZHE_JIU, JI_TI_ZHE_JIU);
		VOUCHER_TYPE.put(JIE_ZHUAN_CHENG_BEN, JIE_ZHUAN_CHENG_BEN);
		VOUCHER_TYPE.put(JI_TI_XIN_CHOU, JI_TI_XIN_CHOU);
		VOUCHER_TYPE.put(TAN_XIAO_FEI_YONG, TAN_XIAO_FEI_YONG);
		VOUCHER_TYPE.put(JI_TI_SHUI_JIN, JI_TI_SHUI_JIN);
		VOUCHER_TYPE.put(JIE_ZHUAN_ZENG_ZHI_TAX, JIE_ZHUAN_ZENG_ZHI_TAX);
		VOUCHER_TYPE.put(JI_TI_SUO_DE_TAX, JI_TI_SUO_DE_TAX);
		VOUCHER_TYPE.put(JI_TI_HUAI_ZHANG, JI_TI_HUAI_ZHANG);
		VOUCHER_TYPE.put(JIE_ZHUAN_SUN_YI, JIE_ZHUAN_SUN_YI);
	}
	
	
	
	/**
	 * 账套默认结转百分比，用于账套初始化时初始化 期末结转模块的 《结转比率表》
	 */
	
	public static final Double COST = 80.00;//成本结转百分比(%)
	public static final Double CITY_CONSTRUC = 7.00;//城市维护建设税税率(%)
	public static final Double EDU = 3.00;//教育费附加税率(%)
	public static final Double LOCAL_EDU = 2.00;//地方教育费附加税率(%)
	public static final Double INCOME_TAX = 25.00;//所得税税率(%)
	
	
	/**
	 * 账套角色
	 */
	public static final String ADMIN = "1";//账套管理员
	public static final String ZHU_GUAN = "2";//主管
	public static final String ZHI_DAN = "3";//制单人
	public static final String CHU_NA = "4";//出纳
	public static final String CHA_KAN = "5";//查看人
	public static final String ZHUAN_JIA = "6";//财税专家
	public static final Map<String,String> ROLES = new HashMap<>();
	static{
		ROLES.put(ADMIN, ADMIN); 
		ROLES.put(ZHU_GUAN, ZHU_GUAN); 
		ROLES.put(ZHI_DAN, ZHI_DAN); 
		ROLES.put(CHU_NA, CHU_NA); 
		ROLES.put(CHA_KAN, CHA_KAN); 
		ROLES.put(ZHUAN_JIA, ZHUAN_JIA);
	}
	
	
}
