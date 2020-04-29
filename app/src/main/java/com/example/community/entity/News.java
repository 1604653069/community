package com.example.community.entity;

import java.util.List;

/*新闻类*/
public class News {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"8e1ee3eb836185243d763cfb1ac432aa","title":"唐探3：王宝强在头等舱吃飞机餐，看到价格，贫穷限制了想象","date":"2019-11-10 10:47","category":"头条","author_name":"小朱信息传播","url":"http://mini.eastday.com/mobile/191110104709647.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_1_mwpm_03200403.jpg"},{"uniquekey":"b52cfc8898f37c2d050452b8e92d22c7","title":"Ai芯天下丨三星晶圆代工产品被曝出现瑕疵，损失或远超数十亿韩元","date":"2019-11-10 10:37","category":"头条","author_name":"AI芯天下","url":"http://mini.eastday.com/mobile/191110103720016.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110103720_1f78a3a345ef1265ca973db0adc83dfc_1_mwpm_03200403.jpg"},{"uniquekey":"69e079807ae130474a067a0cd6d0e6b1","title":"20年后人类可以在火星建立独立城市？马斯克高调表露雄心","date":"2019-11-10 10:29","category":"头条","author_name":"东方头条 军中三剑客","url":"http://mini.eastday.com/mobile/191110102924310.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191110/2019111010_a02343d060854c33ae6bf50f7773c833_9144_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191110/2019111010_996d19b078bf4059b26a0ba8731e8ff9_7314_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191110/2019111010_69e07a108904408594bbe982ad481668_7707_mwpm_03200403.jpg"},{"uniquekey":"7b0a8deb170bd90fa8dab5702bdd07dd","title":"搞笑GIF趣图150期：妹子，这招真的好用吗？","date":"2019-11-10 10:29","category":"头条","author_name":"搞笑大喇叭","url":"http://mini.eastday.com/mobile/191110102919518.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_5_mwpm_03200403.jpg"},{"uniquekey":"03e5cfa80d592d63a4addea453eaa8b8","title":"男子酒后驾车遇查，却甩锅给准女婿，不料其称自己并未喝酒","date":"2019-11-10 10:27","category":"头条","author_name":"爱上趣亊","url":"http://mini.eastday.com/mobile/191110102720554.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/2019111010_d0fa1284c93a437e90de27de9d50e796_9342_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/2019111010_93b9c2f8e7df48ec85961dd2d4038d14_2679_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/2019111010_0d98172a91fe4157826f086aee79c0a8_6396_mwpm_03200403.jpg"},{"uniquekey":"8f757b0c7aab8b1e44a8ed4b8886f6a6","title":"盘点影视剧里的讨喜女配角，晴儿善良大方，流朱忠心护主","date":"2019-11-10 10:18","category":"头条","author_name":"明月侃娱乐","url":"http://mini.eastday.com/mobile/191110101833302.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_5_mwpm_03200403.jpg"},{"uniquekey":"332678ddcc55814f791200950682f80f","title":"搞笑GIF：女生只要一出门，走到哪拍到哪，真是够了","date":"2019-11-10 10:17","category":"头条","author_name":"笑看穿帮","url":"http://mini.eastday.com/mobile/191110101732218.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_3_mwpm_03200403.jpg"},{"uniquekey":"72e149cb7d03088157c56d07c97f5a5c","title":"王者荣耀：盘点那些你空过的大招，他的大你空过那就是大神意识！","date":"2019-11-10 10:17","category":"头条","author_name":"大视角","url":"http://mini.eastday.com/mobile/191110101709788.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_2_mwpm_03200403.jpg"},{"uniquekey":"2068aafeb4198d43131d0e01ed03a926","title":"专访塞尔维亚舒马迪亚省长一行：塞中老铁合作，未来精彩可期","date":"2019-11-10 10:10","category":"头条","author_name":"纵相新闻","url":"http://mini.eastday.com/mobile/191110101037855.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019111010_52377d62a6ab4e8e999d05191598ae6f_4426_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/2019111010_4e3a0061b2cc448482bd0ddcc5327ffb_9685_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/2019111010_14b8394e9e33450cb3a869d7d38530db_7927_cover_mwpm_03200403.jpg"},{"uniquekey":"4b9c3b50b6e911dff4c9d8f1e4fab873","title":"王者荣耀：模拟战新天赋！菜鸡才全都要，大佬会选最好的","date":"2019-11-10 10:10","category":"头条","author_name":"好六网","url":"http://mini.eastday.com/mobile/191110101028188.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_2_mwpm_03200403.jpg"},{"uniquekey":"a30a8a3279f40492bfe70977e8aae87a","title":"山东发放首张货车ETC卡 明年起货车也可不停车收费","date":"2019-11-10 10:09","category":"头条","author_name":"山东商报","url":"http://mini.eastday.com/mobile/191110100921388.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110100921_918e31b640f425c1f6726aae1f7fcdb8_1_mwpm_03200403.jpg"},{"uniquekey":"ca4296ea3a7929884cb5d0d6865e70b4","title":"重头戏！广东对阵巨人杀手，宏远难逃一劫？今晚只有网络直播","date":"2019-11-10 10:08","category":"头条","author_name":"南海浪花","url":"http://mini.eastday.com/mobile/191110100825562.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/2019111010_ebb02dfaede64dd3a4141b1c8ee4e2ae_8549_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/2019111010_c2885cd1400045118fe56d6dd90da969_4009_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/2019111010_a43e5a9c2e7c4cf680746e87f6a2695b_3110_cover_mwpm_03200403.jpg"},{"uniquekey":"a67f47ba7165164e578107f81a2424e4","title":"方舟子：将来无人认识邓稼先，只会记得杨振宁 网友：数典忘祖？","date":"2019-11-10 10:07","category":"头条","author_name":"当代师说","url":"http://mini.eastday.com/mobile/191110100753525.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_4_mwpm_03200403.jpg"},{"uniquekey":"27aea6f48b3d22f4ac7bf9067e96490b","title":"八一女排不敌北京 袁心玥遭雪藏是保护措施 下次亮相恐在世俱杯","date":"2019-11-10 10:04","category":"头条","author_name":"排球黄金眼","url":"http://mini.eastday.com/mobile/191110100430483.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019111010_e70768806365407fa54a2cb6fbaa3b40_4316_cover_mwpm_03200403.jpg"},{"uniquekey":"7f239ad25b2b88f0a90490ab45defc16","title":"越南19岁女孩身上纹30处纹身走红网络，膝盖上还纹了一句中文","date":"2019-11-10 10:02","category":"头条","author_name":"云宝宝育儿","url":"http://mini.eastday.com/mobile/191110100203443.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_1_mwpm_03200403.jpg"},{"uniquekey":"bd0380443c235ea666c60b3514ebb101","title":"\u200b唐嫣迪丽热巴古力娜扎佟丽娅，古装抿口红最美女星谁最为惊艳？","date":"2019-11-10 09:58","category":"头条","author_name":"舌尖上的军情","url":"http://mini.eastday.com/mobile/191110095848967.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/2019111009_8bfd317899f541919b0b199325455a32_3435_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/2019111009_fbba0fb44d0c4324b53db77580d790a2_6364_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/2019111009_c66b7605fa2e4ea5b1438facdb1747d9_6450_mwpm_03200403.jpg"},{"uniquekey":"627949fcc0dd08bb5ff0989792723111","title":"从欠12万到年赚300万，他开了全球唯一的手绘地球仪作坊","date":"2019-11-10 09:56","category":"头条","author_name":"齐鲁晚报网","url":"http://mini.eastday.com/mobile/191110095633970.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_6_mwpm_03200403.jpg"},{"uniquekey":"38ad58c589a521be7965e8ea913c43bc","title":"iphone xr手机WiFi总是掉线该怎么办？","date":"2019-11-10 09:54","category":"头条","author_name":"浅忆初夏","url":"http://mini.eastday.com/mobile/191110095407205.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_3_mwpm_03200403.jpg"},{"uniquekey":"af4458aaba85e2b137de89d81dfd24ad","title":"11月中旬起，3生肖被好运圈住，横财发不停，处处逢贵人","date":"2019-11-10 09:53","category":"头条","author_name":"月亮弯之上","url":"http://mini.eastday.com/mobile/191110095303979.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_1_mwpm_03200403.jpg"},{"uniquekey":"615f452c1f958f18add0e2cba2c4fdc7","title":"迪丽热巴刘诗诗陈紫函唐嫣景甜，红衣古装造型谁美的不可方物？","date":"2019-11-10 09:52","category":"头条","author_name":"舌尖上的军情","url":"http://mini.eastday.com/mobile/191110095234603.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191110/2019111009_2e9f9dc915c144dfac056c4bd5ad20d8_3724_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20191110/2019111009_e36861e654dd4d0f8513be49c791b7dc_7815_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20191110/2019111009_db94ecf0c1664f19b890aef6208bdda0_0241_mwpm_03200403.jpg"},{"uniquekey":"b635f495a0941a88133ed82805928615","title":"他考了第一名却被乾隆换成第三，只因他是江苏人！","date":"2019-11-10 09:52","category":"头条","author_name":"大小海说娱乐","url":"http://mini.eastday.com/mobile/191110095226476.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_4_mwpm_03200403.jpg"},{"uniquekey":"837bc0a8eea51c029ff7f3307c7f860b","title":"此人是袁绍麾下顶级大将，他被杀之后，颜良文丑张郃才开始扬名","date":"2019-11-10 09:52","category":"头条","author_name":"雅雯的小奶狗","url":"http://mini.eastday.com/mobile/191110095206292.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_3_mwpm_03200403.jpg"},{"uniquekey":"e26976df91e799ec67c1fa5401f2c093","title":"每天不妨多吃3种食物，排毒养颜、延缓衰老，早吃早好！","date":"2019-11-10 09:51","category":"头条","author_name":"情感的菜鸟","url":"http://mini.eastday.com/mobile/191110095143728.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019110917_941f0a7999b14c939d3f6b72e79de801_8001_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/2019110917_94b0fc77834e4d9b95a81a150030d2f9_6560_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/2019110917_69cd4dd7b68d432aa606478581ef08c9_8795_mwpm_03200403.jpg"},{"uniquekey":"f9cd6084022efa20c145874844da814c","title":"十二星座的性格，狮子座重义气，双子座翻脸比翻书还快","date":"2019-11-10 09:50","category":"头条","author_name":"摩丽星座","url":"http://mini.eastday.com/mobile/191110095013461.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20191110/2019111009_20de197a98624c6bb320445f9f5d50fe_0643_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20191110/2019111009_af20037bbd0c44f6a86485cb99afd330_8340_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20191110/2019111009_be9f639155b54c12b5d927de83bd59aa_0953_cover_mwpm_03200403.jpg"},{"uniquekey":"15463e19cac6856c93c9de222f88e74c","title":"气象台刚刚发布！安徽多地风险极高！","date":"2019-11-10 09:49","category":"头条","author_name":"合肥視点","url":"http://mini.eastday.com/mobile/191110094942516.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/20191110094942_80ba3227ed2f3f2da02d006831ba288d_1_mwpm_03200403.jpg"},{"uniquekey":"9cd5e26958ed64e08046205cc8cbf00a","title":"武警某部特战三支队从严纠治不实考风引发观念冲击","date":"2019-11-10 09:49","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/191110094905848.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110094905_077c3a3bd7113da1a09f76eb1c3217b7_1_mwpm_03200403.jpg"},{"uniquekey":"4bf3070fef8d4c7827eb1356055509b6","title":"走进鹰嘴村的兵亲戚","date":"2019-11-10 09:49","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/191110094905495.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_2_mwpm_03200403.jpg"},{"uniquekey":"396de447e727f0a17e91a352fe663660","title":"女性想要身体好，推荐3种食物，润肠通便，排毒消脂，增强体质","date":"2019-11-10 09:46","category":"头条","author_name":"养易心动","url":"http://mini.eastday.com/mobile/191110094655397.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/2019111009_f3ff60d2a9ae4df7bfb7386df777aba1_9350_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/2019111009_d3f73874872641a28a8095875f9d34ba_6205_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/2019111009_7dc37be6061040d18195b326480595a8_5672_mwpm_03200403.jpg"},{"uniquekey":"f780afa20afa2e7ba6ba6ea5fb3d029e","title":"有些配置只是摆设，哪些黑科技能预防灾难的发生","date":"2019-11-10 09:46","category":"头条","author_name":"车早茶","url":"http://mini.eastday.com/mobile/191110094602911.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191110/2019111009_243a207e71614e56b61774b56da538a5_5084_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191110/2019111009_d2b8fe9bd46d4f9faaca19126a8563cb_4405_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191110/2019111009_0c5ab588acbc4e00ae19cf4111bda183_2726_mwpm_03200403.jpg"},{"uniquekey":"d23693aa901d2d7d5beba2ab8a1b536c","title":"提新车后，别做这几件事，否则会损耗汽车寿命","date":"2019-11-10 09:45","category":"头条","author_name":"藏车坊","url":"http://mini.eastday.com/mobile/191110094553692.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_4_mwpm_03200403.jpg"}]}
     * error_code : 0
     */
    private String reason;
    private ResultBean result;
    private int error_code;
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"8e1ee3eb836185243d763cfb1ac432aa","title":"唐探3：王宝强在头等舱吃飞机餐，看到价格，贫穷限制了想象","date":"2019-11-10 10:47","category":"头条","author_name":"小朱信息传播","url":"http://mini.eastday.com/mobile/191110104709647.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_1_mwpm_03200403.jpg"},{"uniquekey":"b52cfc8898f37c2d050452b8e92d22c7","title":"Ai芯天下丨三星晶圆代工产品被曝出现瑕疵，损失或远超数十亿韩元","date":"2019-11-10 10:37","category":"头条","author_name":"AI芯天下","url":"http://mini.eastday.com/mobile/191110103720016.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110103720_1f78a3a345ef1265ca973db0adc83dfc_1_mwpm_03200403.jpg"},{"uniquekey":"69e079807ae130474a067a0cd6d0e6b1","title":"20年后人类可以在火星建立独立城市？马斯克高调表露雄心","date":"2019-11-10 10:29","category":"头条","author_name":"东方头条 军中三剑客","url":"http://mini.eastday.com/mobile/191110102924310.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191110/2019111010_a02343d060854c33ae6bf50f7773c833_9144_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191110/2019111010_996d19b078bf4059b26a0ba8731e8ff9_7314_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191110/2019111010_69e07a108904408594bbe982ad481668_7707_mwpm_03200403.jpg"},{"uniquekey":"7b0a8deb170bd90fa8dab5702bdd07dd","title":"搞笑GIF趣图150期：妹子，这招真的好用吗？","date":"2019-11-10 10:29","category":"头条","author_name":"搞笑大喇叭","url":"http://mini.eastday.com/mobile/191110102919518.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/20191110102919_82c7c5bfb32223b5f7fc00a12fedbdbe_5_mwpm_03200403.jpg"},{"uniquekey":"03e5cfa80d592d63a4addea453eaa8b8","title":"男子酒后驾车遇查，却甩锅给准女婿，不料其称自己并未喝酒","date":"2019-11-10 10:27","category":"头条","author_name":"爱上趣亊","url":"http://mini.eastday.com/mobile/191110102720554.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/2019111010_d0fa1284c93a437e90de27de9d50e796_9342_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/2019111010_93b9c2f8e7df48ec85961dd2d4038d14_2679_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/2019111010_0d98172a91fe4157826f086aee79c0a8_6396_mwpm_03200403.jpg"},{"uniquekey":"8f757b0c7aab8b1e44a8ed4b8886f6a6","title":"盘点影视剧里的讨喜女配角，晴儿善良大方，流朱忠心护主","date":"2019-11-10 10:18","category":"头条","author_name":"明月侃娱乐","url":"http://mini.eastday.com/mobile/191110101833302.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/20191110101833_90e864634507b6ce260ce44f7c65648a_5_mwpm_03200403.jpg"},{"uniquekey":"332678ddcc55814f791200950682f80f","title":"搞笑GIF：女生只要一出门，走到哪拍到哪，真是够了","date":"2019-11-10 10:17","category":"头条","author_name":"笑看穿帮","url":"http://mini.eastday.com/mobile/191110101732218.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110101732_a0af5a67d55f7f6716c79c2090a330dc_3_mwpm_03200403.jpg"},{"uniquekey":"72e149cb7d03088157c56d07c97f5a5c","title":"王者荣耀：盘点那些你空过的大招，他的大你空过那就是大神意识！","date":"2019-11-10 10:17","category":"头条","author_name":"大视角","url":"http://mini.eastday.com/mobile/191110101709788.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/20191110101709_50701f7fde778d50d5783783810bbe7c_2_mwpm_03200403.jpg"},{"uniquekey":"2068aafeb4198d43131d0e01ed03a926","title":"专访塞尔维亚舒马迪亚省长一行：塞中老铁合作，未来精彩可期","date":"2019-11-10 10:10","category":"头条","author_name":"纵相新闻","url":"http://mini.eastday.com/mobile/191110101037855.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019111010_52377d62a6ab4e8e999d05191598ae6f_4426_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/2019111010_4e3a0061b2cc448482bd0ddcc5327ffb_9685_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/2019111010_14b8394e9e33450cb3a869d7d38530db_7927_cover_mwpm_03200403.jpg"},{"uniquekey":"4b9c3b50b6e911dff4c9d8f1e4fab873","title":"王者荣耀：模拟战新天赋！菜鸡才全都要，大佬会选最好的","date":"2019-11-10 10:10","category":"头条","author_name":"好六网","url":"http://mini.eastday.com/mobile/191110101028188.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110101028_0e00f67e3ae74fdfb72d731e675dbccb_2_mwpm_03200403.jpg"},{"uniquekey":"a30a8a3279f40492bfe70977e8aae87a","title":"山东发放首张货车ETC卡 明年起货车也可不停车收费","date":"2019-11-10 10:09","category":"头条","author_name":"山东商报","url":"http://mini.eastday.com/mobile/191110100921388.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110100921_918e31b640f425c1f6726aae1f7fcdb8_1_mwpm_03200403.jpg"},{"uniquekey":"ca4296ea3a7929884cb5d0d6865e70b4","title":"重头戏！广东对阵巨人杀手，宏远难逃一劫？今晚只有网络直播","date":"2019-11-10 10:08","category":"头条","author_name":"南海浪花","url":"http://mini.eastday.com/mobile/191110100825562.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/2019111010_ebb02dfaede64dd3a4141b1c8ee4e2ae_8549_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/2019111010_c2885cd1400045118fe56d6dd90da969_4009_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/2019111010_a43e5a9c2e7c4cf680746e87f6a2695b_3110_cover_mwpm_03200403.jpg"},{"uniquekey":"a67f47ba7165164e578107f81a2424e4","title":"方舟子：将来无人认识邓稼先，只会记得杨振宁 网友：数典忘祖？","date":"2019-11-10 10:07","category":"头条","author_name":"当代师说","url":"http://mini.eastday.com/mobile/191110100753525.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/20191110100753_6bffa7ea21da6235b078d470a3021aec_4_mwpm_03200403.jpg"},{"uniquekey":"27aea6f48b3d22f4ac7bf9067e96490b","title":"八一女排不敌北京 袁心玥遭雪藏是保护措施 下次亮相恐在世俱杯","date":"2019-11-10 10:04","category":"头条","author_name":"排球黄金眼","url":"http://mini.eastday.com/mobile/191110100430483.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019111010_e70768806365407fa54a2cb6fbaa3b40_4316_cover_mwpm_03200403.jpg"},{"uniquekey":"7f239ad25b2b88f0a90490ab45defc16","title":"越南19岁女孩身上纹30处纹身走红网络，膝盖上还纹了一句中文","date":"2019-11-10 10:02","category":"头条","author_name":"云宝宝育儿","url":"http://mini.eastday.com/mobile/191110100203443.html","thumbnail_pic_s":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04imgmini.eastday.com/mobile/20191110/20191110100203_5c3cfd680ae7e5d47136db80a31a0f68_1_mwpm_03200403.jpg"},{"uniquekey":"bd0380443c235ea666c60b3514ebb101","title":"\u200b唐嫣迪丽热巴古力娜扎佟丽娅，古装抿口红最美女星谁最为惊艳？","date":"2019-11-10 09:58","category":"头条","author_name":"舌尖上的军情","url":"http://mini.eastday.com/mobile/191110095848967.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/2019111009_8bfd317899f541919b0b199325455a32_3435_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/2019111009_fbba0fb44d0c4324b53db77580d790a2_6364_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/2019111009_c66b7605fa2e4ea5b1438facdb1747d9_6450_mwpm_03200403.jpg"},{"uniquekey":"627949fcc0dd08bb5ff0989792723111","title":"从欠12万到年赚300万，他开了全球唯一的手绘地球仪作坊","date":"2019-11-10 09:56","category":"头条","author_name":"齐鲁晚报网","url":"http://mini.eastday.com/mobile/191110095633970.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/20191110095633_cbb960ef4b2368a38f0d04ba8f251eba_6_mwpm_03200403.jpg"},{"uniquekey":"38ad58c589a521be7965e8ea913c43bc","title":"iphone xr手机WiFi总是掉线该怎么办？","date":"2019-11-10 09:54","category":"头条","author_name":"浅忆初夏","url":"http://mini.eastday.com/mobile/191110095407205.html","thumbnail_pic_s":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00imgmini.eastday.com/mobile/20191110/20191110095407_b7bfcb4eb14754bfc617d637ffb73a67_3_mwpm_03200403.jpg"},{"uniquekey":"af4458aaba85e2b137de89d81dfd24ad","title":"11月中旬起，3生肖被好运圈住，横财发不停，处处逢贵人","date":"2019-11-10 09:53","category":"头条","author_name":"月亮弯之上","url":"http://mini.eastday.com/mobile/191110095303979.html","thumbnail_pic_s":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07imgmini.eastday.com/mobile/20191110/20191110095303_cd3e8b89618e70da1f7d34511b1f17d7_1_mwpm_03200403.jpg"},{"uniquekey":"615f452c1f958f18add0e2cba2c4fdc7","title":"迪丽热巴刘诗诗陈紫函唐嫣景甜，红衣古装造型谁美的不可方物？","date":"2019-11-10 09:52","category":"头条","author_name":"舌尖上的军情","url":"http://mini.eastday.com/mobile/191110095234603.html","thumbnail_pic_s":"http://09imgmini.eastday.com/mobile/20191110/2019111009_2e9f9dc915c144dfac056c4bd5ad20d8_3724_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09imgmini.eastday.com/mobile/20191110/2019111009_e36861e654dd4d0f8513be49c791b7dc_7815_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09imgmini.eastday.com/mobile/20191110/2019111009_db94ecf0c1664f19b890aef6208bdda0_0241_mwpm_03200403.jpg"},{"uniquekey":"b635f495a0941a88133ed82805928615","title":"他考了第一名却被乾隆换成第三，只因他是江苏人！","date":"2019-11-10 09:52","category":"头条","author_name":"大小海说娱乐","url":"http://mini.eastday.com/mobile/191110095226476.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08imgmini.eastday.com/mobile/20191110/20191110095226_c17088458e4b10d9749b53b38565e140_4_mwpm_03200403.jpg"},{"uniquekey":"837bc0a8eea51c029ff7f3307c7f860b","title":"此人是袁绍麾下顶级大将，他被杀之后，颜良文丑张郃才开始扬名","date":"2019-11-10 09:52","category":"头条","author_name":"雅雯的小奶狗","url":"http://mini.eastday.com/mobile/191110095206292.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20191110/20191110095206_ac635ef2147b66f8b8ebacc3b7376664_3_mwpm_03200403.jpg"},{"uniquekey":"e26976df91e799ec67c1fa5401f2c093","title":"每天不妨多吃3种食物，排毒养颜、延缓衰老，早吃早好！","date":"2019-11-10 09:51","category":"头条","author_name":"情感的菜鸟","url":"http://mini.eastday.com/mobile/191110095143728.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/2019110917_941f0a7999b14c939d3f6b72e79de801_8001_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01imgmini.eastday.com/mobile/20191110/2019110917_94b0fc77834e4d9b95a81a150030d2f9_6560_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01imgmini.eastday.com/mobile/20191110/2019110917_69cd4dd7b68d432aa606478581ef08c9_8795_mwpm_03200403.jpg"},{"uniquekey":"f9cd6084022efa20c145874844da814c","title":"十二星座的性格，狮子座重义气，双子座翻脸比翻书还快","date":"2019-11-10 09:50","category":"头条","author_name":"摩丽星座","url":"http://mini.eastday.com/mobile/191110095013461.html","thumbnail_pic_s":"http://05imgmini.eastday.com/mobile/20191110/2019111009_20de197a98624c6bb320445f9f5d50fe_0643_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05imgmini.eastday.com/mobile/20191110/2019111009_af20037bbd0c44f6a86485cb99afd330_8340_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05imgmini.eastday.com/mobile/20191110/2019111009_be9f639155b54c12b5d927de83bd59aa_0953_cover_mwpm_03200403.jpg"},{"uniquekey":"15463e19cac6856c93c9de222f88e74c","title":"气象台刚刚发布！安徽多地风险极高！","date":"2019-11-10 09:49","category":"头条","author_name":"合肥視点","url":"http://mini.eastday.com/mobile/191110094942516.html","thumbnail_pic_s":"http://01imgmini.eastday.com/mobile/20191110/20191110094942_80ba3227ed2f3f2da02d006831ba288d_1_mwpm_03200403.jpg"},{"uniquekey":"9cd5e26958ed64e08046205cc8cbf00a","title":"武警某部特战三支队从严纠治不实考风引发观念冲击","date":"2019-11-10 09:49","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/191110094905848.html","thumbnail_pic_s":"http://08imgmini.eastday.com/mobile/20191110/20191110094905_077c3a3bd7113da1a09f76eb1c3217b7_1_mwpm_03200403.jpg"},{"uniquekey":"4bf3070fef8d4c7827eb1356055509b6","title":"走进鹰嘴村的兵亲戚","date":"2019-11-10 09:49","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/191110094905495.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191110/20191110094905_9fd43637ec7a7fed3e7faa8d0465d38d_2_mwpm_03200403.jpg"},{"uniquekey":"396de447e727f0a17e91a352fe663660","title":"女性想要身体好，推荐3种食物，润肠通便，排毒消脂，增强体质","date":"2019-11-10 09:46","category":"头条","author_name":"养易心动","url":"http://mini.eastday.com/mobile/191110094655397.html","thumbnail_pic_s":"http://03imgmini.eastday.com/mobile/20191110/2019111009_f3ff60d2a9ae4df7bfb7386df777aba1_9350_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03imgmini.eastday.com/mobile/20191110/2019111009_d3f73874872641a28a8095875f9d34ba_6205_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03imgmini.eastday.com/mobile/20191110/2019111009_7dc37be6061040d18195b326480595a8_5672_mwpm_03200403.jpg"},{"uniquekey":"f780afa20afa2e7ba6ba6ea5fb3d029e","title":"有些配置只是摆设，哪些黑科技能预防灾难的发生","date":"2019-11-10 09:46","category":"头条","author_name":"车早茶","url":"http://mini.eastday.com/mobile/191110094602911.html","thumbnail_pic_s":"http://06imgmini.eastday.com/mobile/20191110/2019111009_243a207e71614e56b61774b56da538a5_5084_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06imgmini.eastday.com/mobile/20191110/2019111009_d2b8fe9bd46d4f9faaca19126a8563cb_4405_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06imgmini.eastday.com/mobile/20191110/2019111009_0c5ab588acbc4e00ae19cf4111bda183_2726_mwpm_03200403.jpg"},{"uniquekey":"d23693aa901d2d7d5beba2ab8a1b536c","title":"提新车后，别做这几件事，否则会损耗汽车寿命","date":"2019-11-10 09:45","category":"头条","author_name":"藏车坊","url":"http://mini.eastday.com/mobile/191110094553692.html","thumbnail_pic_s":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02imgmini.eastday.com/mobile/20191110/20191110094553_267a600d93046e6eba51cdab256987e9_4_mwpm_03200403.jpg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }
        public static class DataBean {
            /**
             * uniquekey : 8e1ee3eb836185243d763cfb1ac432aa
             * title : 唐探3：王宝强在头等舱吃飞机餐，看到价格，贫穷限制了想象
             * date : 2019-11-10 10:47
             * category : 头条
             * author_name : 小朱信息传播
             * url : http://mini.eastday.com/mobile/191110104709647.html
             * thumbnail_pic_s : http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_3_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_2_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://00imgmini.eastday.com/mobile/20191110/20191110104709_39b8120404bddd0bbb1686a44434f4b3_1_mwpm_03200403.jpg
             */
            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
