package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cellargalaxy on 18-4-24.
 */
public class TextUtil {
	private static final Pattern pattern = Pattern.compile("[\\d.]+");
	private static final String[] provinces = {"特别行政区", "自治区", "省", "市"};
	private static final String[] citys = {"自治州", "自治县", "市", "县"};
	private static final Map<String, String[]> locationMap;
	private static final DateFormat holidayMonthDdateFormat;
	private static final Set<Date> holidayMonthSet;
	
	static {
		locationMap = new HashMap<String, String[]>();
		locationMap.put("阿坝", new String[]{"四川省", "阿坝藏族羌族自治州"});//20
		locationMap.put("阿克苏", new String[]{"新疆维吾尔自治区", "阿克苏地区"});
		locationMap.put("阿拉善盟", new String[]{"内蒙古自治区", "阿拉善盟"});
		locationMap.put("阿勒泰", new String[]{"新疆维吾尔自治区", "阿勒泰地区"});
		locationMap.put("阿里", new String[]{"西藏自治区", "阿里地区"});
		locationMap.put("安康", new String[]{"陕西省", "安康市"});
		locationMap.put("安庆", new String[]{"安徽省", "安庆市"});
		locationMap.put("安顺", new String[]{"贵州省", "安顺市"});
		locationMap.put("安阳", new String[]{"河南省", "安阳市"});
		locationMap.put("鞍山", new String[]{"辽宁省", "鞍山市"});
		locationMap.put("巴彦淖尔", new String[]{"内蒙古自治区", "巴彦淖尔市"});
		locationMap.put("巴音郭楞", new String[]{"新疆维吾尔自治区", "巴音郭楞蒙古自治州"});
		locationMap.put("巴中", new String[]{"四川省", "巴中市"});
		locationMap.put("白城", new String[]{"吉林省", "白城市"});
		locationMap.put("白山", new String[]{"吉林省", "白山市"});
		locationMap.put("白银", new String[]{"甘肃省", "白银市"});
		locationMap.put("百色", new String[]{"广西壮族自治区", "百色市"});
		locationMap.put("蚌埠", new String[]{"安徽省", "蚌埠市"});
		locationMap.put("包头", new String[]{"内蒙古自治区", "包头市"});
		locationMap.put("宝鸡", new String[]{"陕西省", "宝鸡市"});
		locationMap.put("保定", new String[]{"河北省", "保定市"});
		locationMap.put("保山", new String[]{"云南省", "保山市"});
		locationMap.put("北海", new String[]{"广西壮族自治区", "北海市"});
		locationMap.put("本溪", new String[]{"辽宁省", "本溪市"});
		locationMap.put("毕节", new String[]{"贵州省", "毕节地区"});
		locationMap.put("滨州", new String[]{"山东省", "滨州市"});
		locationMap.put("博尔塔拉", new String[]{"新疆维吾尔自治区", "博尔塔拉蒙古自治州"});
		locationMap.put("沧州", new String[]{"河北省", "沧州市"});
		locationMap.put("昌都", new String[]{"西藏自治区", "昌都地区"});
		locationMap.put("昌吉", new String[]{"新疆维吾尔自治区", "昌吉回族自治州"});
		locationMap.put("长春", new String[]{"吉林省", "长春市"});
		locationMap.put("长沙", new String[]{"湖南省", "长沙市"});
		locationMap.put("长治", new String[]{"山西省", "长治市"});
		locationMap.put("常德", new String[]{"湖南省", "常德市"});
		locationMap.put("常州", new String[]{"江苏省", "常州市"});
		locationMap.put("巢湖", new String[]{"安徽省", "巢湖市"});
		locationMap.put("朝阳", new String[]{"辽宁省", "朝阳市"});
		locationMap.put("潮州", new String[]{"广东省", "潮州市"});
		locationMap.put("郴州", new String[]{"湖南省", "郴州市"});
		locationMap.put("成都", new String[]{"四川省", "成都市"});
		locationMap.put("承德", new String[]{"河北省", "承德市"});
		locationMap.put("池州", new String[]{"安徽省", "池州市"});
		locationMap.put("赤峰", new String[]{"内蒙古自治区", "赤峰市"});
		locationMap.put("崇左", new String[]{"广西壮族自治区", "崇左市"});
		locationMap.put("滁州", new String[]{"安徽省", "滁州市"});
		locationMap.put("楚雄", new String[]{"云南省", "楚雄彝族自治州"});
		locationMap.put("达州", new String[]{"四川省", "达州市"});
		locationMap.put("大理", new String[]{"云南省", "大理白族自治州"});
		locationMap.put("大连", new String[]{"辽宁省", "大连市"});
		locationMap.put("大庆", new String[]{"黑龙江省", "大庆市"});
		locationMap.put("大同", new String[]{"山西省", "大同市"});
		locationMap.put("大兴安岭", new String[]{"黑龙江省", "大兴安岭地区"});
		locationMap.put("丹东", new String[]{"辽宁省", "丹东市"});
		locationMap.put("德宏", new String[]{"云南省", "德宏傣族景颇族自治州"});
		locationMap.put("德阳", new String[]{"四川省", "德阳市"});
		locationMap.put("德州", new String[]{"山东省", "德州市"});
		locationMap.put("迪庆", new String[]{"云南省", "迪庆藏族自治州"});
		locationMap.put("定西", new String[]{"甘肃省", "定西市"});
		locationMap.put("东莞", new String[]{"广东省", "东莞市"});
		locationMap.put("东营", new String[]{"山东省", "东营市"});
		locationMap.put("鄂尔多斯", new String[]{"内蒙古自治区", "鄂尔多斯市"});
		locationMap.put("鄂州", new String[]{"湖北省", "鄂州市"});
		locationMap.put("恩施", new String[]{"湖北省", "恩施土家族苗族自治州"});
		locationMap.put("防城港", new String[]{"广西壮族自治区", "防城港市"});
		locationMap.put("佛山", new String[]{"广东省", "佛山市"});
		locationMap.put("福州", new String[]{"福建省", "福州市"});
		locationMap.put("抚顺", new String[]{"辽宁省", "抚顺市"});
		locationMap.put("抚州", new String[]{"江西省", "抚州市"});
		locationMap.put("阜新", new String[]{"辽宁省", "阜新市"});
		locationMap.put("阜阳", new String[]{"安徽省", "阜阳市"});
		locationMap.put("甘南州", new String[]{"甘肃省", "甘南州"});
		locationMap.put("甘孜", new String[]{"四川省", "甘孜藏族自治州"});
		locationMap.put("赣州", new String[]{"江西省", "赣州市"});
		locationMap.put("固原", new String[]{"宁夏回族自治区", "固原市"});
		locationMap.put("广安", new String[]{"四川省", "广安市"});
		locationMap.put("广元", new String[]{"四川省", "广元市"});
		locationMap.put("广州", new String[]{"广东省", "广州市"});
		locationMap.put("贵港", new String[]{"广西壮族自治区", "贵港市"});
		locationMap.put("贵阳", new String[]{"贵州省", "贵阳市"});
		locationMap.put("桂林", new String[]{"广西壮族自治区", "桂林市"});
		locationMap.put("果洛", new String[]{"青海省", "果洛藏族自治州"});
		locationMap.put("哈尔滨", new String[]{"黑龙江省", "哈尔滨市"});
		locationMap.put("哈密", new String[]{"新疆维吾尔自治区", "哈密地区"});
		locationMap.put("海北", new String[]{"青海省", "海北藏族自治州"});
		locationMap.put("海东", new String[]{"青海省", "海东地区"});
		locationMap.put("海口", new String[]{"海南省", "海口市"});
		locationMap.put("海南", new String[]{"青海省", "海南藏族自治州"});
		locationMap.put("海西", new String[]{"青海省", "海西蒙古族藏族自治州"});
		locationMap.put("邯郸", new String[]{"河北省", "邯郸市"});
		locationMap.put("汉中", new String[]{"陕西省", "汉中市"});
		locationMap.put("杭州", new String[]{"浙江省", "杭州市"});
		locationMap.put("毫州", new String[]{"安徽省", "毫州市"});
		locationMap.put("合肥", new String[]{"安徽省", "合肥市"});
		locationMap.put("和田", new String[]{"新疆维吾尔自治区", "和田地区"});
		locationMap.put("河池", new String[]{"广西壮族自治区", "河池市"});
		locationMap.put("河源", new String[]{"广东省", "河源市"});
		locationMap.put("菏泽", new String[]{"山东省", "菏泽市"});
		locationMap.put("贺州", new String[]{"广西壮族自治区", "贺州市"});
		locationMap.put("鹤壁", new String[]{"河南省", "鹤壁市"});
		locationMap.put("鹤岗", new String[]{"黑龙江省", "鹤岗市"});
		locationMap.put("黑河", new String[]{"黑龙江省", "黑河市"});
		locationMap.put("衡水", new String[]{"河北省", "衡水市"});
		locationMap.put("衡阳", new String[]{"湖南省", "衡阳市"});
		locationMap.put("红河", new String[]{"云南省", "红河哈尼族彝族自治州"});
		locationMap.put("呼和浩特", new String[]{"内蒙古自治区", "呼和浩特市"});
		locationMap.put("呼伦贝尔", new String[]{"内蒙古自治区", "呼伦贝尔市"});
		locationMap.put("湖州", new String[]{"浙江省", "湖州市"});
		locationMap.put("葫芦岛", new String[]{"辽宁省", "葫芦岛市"});
		locationMap.put("怀化", new String[]{"湖南省", "怀化市"});
		locationMap.put("淮安", new String[]{"江苏省", "淮安市"});
		locationMap.put("淮北", new String[]{"安徽省", "淮北市"});
		locationMap.put("淮南", new String[]{"安徽省", "淮南市"});
		locationMap.put("黄冈", new String[]{"湖北省", "黄冈市"});
		locationMap.put("黄南", new String[]{"青海省", "黄南藏族自治州"});
		locationMap.put("黄山", new String[]{"安徽省", "黄山市"});
		locationMap.put("黄石", new String[]{"湖北省", "黄石市"});
		locationMap.put("惠州", new String[]{"广东省", "惠州市"});
		locationMap.put("鸡西", new String[]{"黑龙江省", "鸡西市"});
		locationMap.put("吉安", new String[]{"江西省", "吉安市"});
		locationMap.put("吉林", new String[]{"吉林省", "吉林市"});
		locationMap.put("济南", new String[]{"山东省", "济南市"});
		locationMap.put("济宁", new String[]{"山东省", "济宁市"});
		locationMap.put("佳木斯", new String[]{"黑龙江省", "佳木斯市"});
		locationMap.put("嘉兴", new String[]{"浙江省", "嘉兴市"});
		locationMap.put("嘉峪关", new String[]{"甘肃省", "嘉峪关市"});
		locationMap.put("江门", new String[]{"广东省", "江门市"});
		locationMap.put("焦作", new String[]{"河南省", "焦作市"});
		locationMap.put("揭阳", new String[]{"广东省", "揭阳市"});
		locationMap.put("金昌", new String[]{"甘肃省", "金昌市"});
		locationMap.put("金华", new String[]{"浙江省", "金华市"});
		locationMap.put("锦州", new String[]{"辽宁省", "锦州市"});
		locationMap.put("晋城", new String[]{"山西省", "晋城市"});
		locationMap.put("晋中", new String[]{"山西省", "晋中市"});
		locationMap.put("荆门", new String[]{"湖北省", "荆门市"});
		locationMap.put("荆州", new String[]{"湖北省", "荆州市"});
		locationMap.put("景德镇", new String[]{"江西省", "景德镇市"});
		locationMap.put("九江", new String[]{"江西省", "九江市"});
		locationMap.put("酒泉", new String[]{"甘肃省", "酒泉市"});
		locationMap.put("喀什", new String[]{"新疆维吾尔自治区", "喀什地区"});
		locationMap.put("开封", new String[]{"河南省", "开封市"});
		locationMap.put("克拉玛依", new String[]{"新疆维吾尔自治区", "克拉玛依市"});
		locationMap.put("克孜勒苏柯尔克孜", new String[]{"新疆维吾尔自治区", "克孜勒苏柯尔克孜自治州"});
		locationMap.put("昆明", new String[]{"云南省", "昆明市"});
		locationMap.put("拉萨", new String[]{"西藏自治区", "拉萨市"});
		locationMap.put("来宾", new String[]{"广西壮族自治区", "来宾市"});
		locationMap.put("莱芜", new String[]{"山东省", "莱芜市"});
		locationMap.put("兰州", new String[]{"甘肃省", "兰州市"});
		locationMap.put("廊坊", new String[]{"河北省", "廊坊市"});
		locationMap.put("乐山", new String[]{"四川省", "乐山市"});
		locationMap.put("丽江", new String[]{"云南省", "丽江市"});
		locationMap.put("丽水", new String[]{"浙江省", "丽水市"});
		locationMap.put("连云港", new String[]{"江苏省", "连云港市"});
		locationMap.put("凉山", new String[]{"四川省", "凉山彝族自治州"});
		locationMap.put("辽阳", new String[]{"辽宁省", "辽阳市"});
		locationMap.put("辽源", new String[]{"吉林省", "辽源市"});
		locationMap.put("聊城", new String[]{"山东省", "聊城市"});
		locationMap.put("林芝", new String[]{"西藏自治区", "林芝地区"});
		locationMap.put("临沧", new String[]{"云南省", "临沧市"});
		locationMap.put("临汾", new String[]{"山西省", "临汾市"});
		locationMap.put("临夏州", new String[]{"甘肃省", "临夏州"});
		locationMap.put("临沂", new String[]{"山东省", "临沂市"});
		locationMap.put("柳州", new String[]{"广西壮族自治区", "柳州市"});
		locationMap.put("六安", new String[]{"安徽省", "六安市"});
		locationMap.put("六盘水", new String[]{"贵州省", "六盘水市"});
		locationMap.put("龙岩", new String[]{"福建省", "龙岩市"});
		locationMap.put("陇南", new String[]{"甘肃省", "陇南市"});
		locationMap.put("娄底", new String[]{"湖南省", "娄底市"});
		locationMap.put("泸州", new String[]{"四川省", "泸州市"});
		locationMap.put("吕梁", new String[]{"山西省", "吕梁市"});
		locationMap.put("洛阳", new String[]{"河南省", "洛阳市"});
		locationMap.put("漯河", new String[]{"河南省", "漯河市"});
		locationMap.put("马鞍山", new String[]{"安徽省", "马鞍山市"});
		locationMap.put("茂名", new String[]{"广东省", "茂名市"});
		locationMap.put("眉山", new String[]{"四川省", "眉山市"});
		locationMap.put("梅州", new String[]{"广东省", "梅州市"});
		locationMap.put("绵阳", new String[]{"四川省", "绵阳市"});
		locationMap.put("牡丹江", new String[]{"黑龙江省", "牡丹江市"});
		locationMap.put("内江", new String[]{"四川省", "内江市"});
		locationMap.put("那曲", new String[]{"西藏自治区", "那曲地区"});
		locationMap.put("南昌", new String[]{"江西省", "南昌市"});
		locationMap.put("南充", new String[]{"四川省", "南充市"});
		locationMap.put("南京", new String[]{"江苏省", "南京市"});
		locationMap.put("南宁", new String[]{"广西壮族自治区", "南宁市"});
		locationMap.put("南平", new String[]{"福建省", "南平市"});
		locationMap.put("南通", new String[]{"江苏省", "南通市"});
		locationMap.put("南阳", new String[]{"河南省", "南阳市"});
		locationMap.put("宁波", new String[]{"浙江省", "宁波市"});
		locationMap.put("宁德", new String[]{"福建省", "宁德市"});
		locationMap.put("怒江", new String[]{"云南省", "怒江傈僳族自治州"});
		locationMap.put("攀枝花", new String[]{"四川省", "攀枝花市"});
		locationMap.put("盘锦", new String[]{"辽宁省", "盘锦市"});
		locationMap.put("平顶山", new String[]{"河南省", "平顶山市"});
		locationMap.put("平凉", new String[]{"甘肃省", "平凉市"});
		locationMap.put("萍乡", new String[]{"江西省", "萍乡市"});
		locationMap.put("莆田", new String[]{"福建省", "莆田市"});
		locationMap.put("濮阳", new String[]{"河南省", "濮阳市"});
		locationMap.put("普洱", new String[]{"云南省", "普洱市"});
		locationMap.put("七台河", new String[]{"黑龙江省", "七台河市"});
		locationMap.put("齐齐哈尔", new String[]{"黑龙江省", "齐齐哈尔市"});
		locationMap.put("黔东", new String[]{"贵州省", "黔东南苗族侗族自治州"});
		locationMap.put("黔南", new String[]{"贵州省", "黔南布依族苗族自治州"});
		locationMap.put("黔西", new String[]{"贵州省", "黔西南布依族苗族自治州"});
		locationMap.put("钦州", new String[]{"广西壮族自治区", "钦州市"});
		locationMap.put("秦皇岛", new String[]{"河北省", "秦皇岛市"});
		locationMap.put("青岛", new String[]{"山东省", "青岛市"});
		locationMap.put("清远", new String[]{"广东省", "清远市"});
		locationMap.put("庆阳", new String[]{"甘肃省", "庆阳市"});
		locationMap.put("曲靖", new String[]{"云南省", "曲靖市"});
		locationMap.put("衢州", new String[]{"浙江省", "衢州市"});
		locationMap.put("泉州", new String[]{"福建省", "泉州市"});
		locationMap.put("日喀则", new String[]{"西藏自治区", "日喀则地区"});
		locationMap.put("日照", new String[]{"山东省", "日照市"});
		locationMap.put("三门峡", new String[]{"河南省", "三门峡市"});
		locationMap.put("三明", new String[]{"福建省", "三明市"});
		locationMap.put("三亚", new String[]{"海南省", "三亚市"});
		locationMap.put("山南", new String[]{"西藏自治区", "山南地区"});
		locationMap.put("汕头", new String[]{"广东省", "汕头市"});
		locationMap.put("汕尾", new String[]{"广东省", "汕尾市"});
		locationMap.put("商洛", new String[]{"陕西省", "商洛市"});
		locationMap.put("商丘", new String[]{"河南省", "商丘市"});
		locationMap.put("上饶", new String[]{"江西省", "上饶市"});
		locationMap.put("韶关", new String[]{"广东省", "韶关市"});
		locationMap.put("邵阳", new String[]{"湖南省", "邵阳市"});
		locationMap.put("绍兴", new String[]{"浙江省", "绍兴市"});
		locationMap.put("深圳", new String[]{"广东省", "深圳市"});
		locationMap.put("沈阳", new String[]{"辽宁省", "沈阳市"});
		locationMap.put("十堰", new String[]{"湖北省", "十堰市"});
		locationMap.put("石家庄", new String[]{"河北省", "石家庄市"});
		locationMap.put("石嘴山", new String[]{"宁夏回族自治区", "石嘴山市"});
		locationMap.put("双鸭山", new String[]{"黑龙江省", "双鸭山市"});
		locationMap.put("朔州", new String[]{"山西省", "朔州市"});
		locationMap.put("四平", new String[]{"吉林省", "四平市"});
		locationMap.put("松原", new String[]{"吉林省", "松原市"});
		locationMap.put("苏州", new String[]{"江苏省", "苏州市"});
		locationMap.put("宿迁", new String[]{"江苏省", "宿迁市"});
		locationMap.put("宿州", new String[]{"安徽省", "宿州市"});
		locationMap.put("绥化", new String[]{"黑龙江省", "绥化市"});
		locationMap.put("随州", new String[]{"湖北省", "随州市"});
		locationMap.put("遂宁", new String[]{"四川省", "遂宁市"});
		locationMap.put("塔城", new String[]{"新疆维吾尔自治区", "塔城地区"});
		locationMap.put("台州", new String[]{"浙江省", "台州市"});
		locationMap.put("太原", new String[]{"山西省", "太原市"});
		locationMap.put("泰安", new String[]{"山东省", "泰安市"});
		locationMap.put("泰州", new String[]{"江苏省", "泰州市"});
		locationMap.put("唐山", new String[]{"河北省", "唐山市"});
		locationMap.put("天水", new String[]{"甘肃省", "天水市"});
		locationMap.put("铁岭", new String[]{"辽宁省", "铁岭市"});
		locationMap.put("通化", new String[]{"吉林省", "通化市"});
		locationMap.put("通辽", new String[]{"内蒙古自治区", "通辽市"});
		locationMap.put("铜川", new String[]{"陕西省", "铜川市"});
		locationMap.put("铜陵", new String[]{"安徽省", "铜陵市"});
		locationMap.put("铜仁", new String[]{"贵州省", "铜仁市"});
		locationMap.put("吐鲁番", new String[]{"新疆维吾尔自治区", "吐鲁番地区"});
		locationMap.put("威海", new String[]{"山东省", "威海市"});
		locationMap.put("潍坊", new String[]{"山东省", "潍坊市"});
		locationMap.put("渭南", new String[]{"陕西省", "渭南市"});
		locationMap.put("温州", new String[]{"浙江省", "温州市"});
		locationMap.put("文山", new String[]{"云南省", "文山壮族苗族自治州"});
		locationMap.put("乌海", new String[]{"内蒙古自治区", "乌海市"});
		locationMap.put("乌兰察布", new String[]{"内蒙古自治区", "乌兰察布市"});
		locationMap.put("乌鲁木齐", new String[]{"新疆维吾尔自治区", "乌鲁木齐市"});
		locationMap.put("无锡", new String[]{"江苏省", "无锡市"});
		locationMap.put("吴忠", new String[]{"宁夏回族自治区", "吴忠市"});
		locationMap.put("芜湖", new String[]{"安徽省", "芜湖市"});
		locationMap.put("梧州", new String[]{"广西壮族自治区", "梧州市"});
		locationMap.put("武汉", new String[]{"湖北省", "武汉市"});
		locationMap.put("武威", new String[]{"甘肃省", "武威市"});
		locationMap.put("西安", new String[]{"陕西省", "西安市"});
		locationMap.put("西宁", new String[]{"青海省", "西宁市"});
		locationMap.put("西双版纳", new String[]{"云南省", "西双版纳傣族自治州"});
		locationMap.put("锡林郭勒盟", new String[]{"内蒙古自治区", "锡林郭勒盟"});
		locationMap.put("厦门", new String[]{"福建省", "厦门市"});
		locationMap.put("咸宁", new String[]{"湖北省", "咸宁市"});
		locationMap.put("咸阳", new String[]{"陕西省", "咸阳市"});
		locationMap.put("湘潭", new String[]{"湖南省", "湘潭市"});
		locationMap.put("湘西", new String[]{"湖南省", "湘西土家族苗族自治州"});
		locationMap.put("襄樊", new String[]{"湖北省", "襄樊市"});
		locationMap.put("孝感", new String[]{"湖北省", "孝感市"});
		locationMap.put("忻州", new String[]{"山西省", "忻州市"});
		locationMap.put("新乡", new String[]{"河南省", "新乡市"});
		locationMap.put("新余", new String[]{"江西省", "新余市"});
		locationMap.put("信阳", new String[]{"河南省", "信阳市"});
		locationMap.put("兴安盟", new String[]{"内蒙古自治区", "兴安盟"});
		locationMap.put("邢台", new String[]{"河北省", "邢台市"});
		locationMap.put("徐州", new String[]{"江苏省", "徐州市"});
		locationMap.put("许昌", new String[]{"河南省", "许昌市"});
		locationMap.put("宣城", new String[]{"安徽省", "宣城市"});
		locationMap.put("雅安", new String[]{"四川省", "雅安市"});
		locationMap.put("烟台", new String[]{"山东省", "烟台市"});
		locationMap.put("延安", new String[]{"陕西省", "延安市"});
		locationMap.put("延边", new String[]{"吉林省", "延边朝鲜族自治州"});
		locationMap.put("盐城", new String[]{"江苏省", "盐城市"});
		locationMap.put("扬州", new String[]{"江苏省", "扬州市"});
		locationMap.put("阳江", new String[]{"广东省", "阳江市"});
		locationMap.put("阳泉", new String[]{"山西省", "阳泉市"});
		locationMap.put("伊春", new String[]{"黑龙江省", "伊春市"});
		locationMap.put("伊犁哈萨克", new String[]{"新疆维吾尔自治区", "伊犁哈萨克自治州"});
		locationMap.put("宜宾", new String[]{"四川省", "宜宾市"});
		locationMap.put("宜昌", new String[]{"湖北省", "宜昌市"});
		locationMap.put("宜春", new String[]{"江西省", "宜春市"});
		locationMap.put("益阳", new String[]{"湖南省", "益阳市"});
		locationMap.put("银川", new String[]{"宁夏回族自治区", "银川市"});
		locationMap.put("鹰潭", new String[]{"江西省", "鹰潭市"});
		locationMap.put("营口", new String[]{"辽宁省", "营口市"});
		locationMap.put("永州", new String[]{"湖南省", "永州市"});
		locationMap.put("榆林", new String[]{"陕西省", "榆林市"});
		locationMap.put("玉林", new String[]{"广西壮族自治区", "玉林市"});
		locationMap.put("玉树", new String[]{"青海省", "玉树藏族自治州"});
		locationMap.put("玉溪", new String[]{"云南省", "玉溪市"});
		locationMap.put("岳阳", new String[]{"湖南省", "岳阳市"});
		locationMap.put("云浮", new String[]{"广东省", "云浮市"});
		locationMap.put("运城", new String[]{"山西省", "运城市"});
		locationMap.put("枣庄", new String[]{"山东省", "枣庄市"});
		locationMap.put("湛江", new String[]{"广东省", "湛江市"});
		locationMap.put("张家界", new String[]{"湖南省", "张家界市"});
		locationMap.put("张家口", new String[]{"河北省", "张家口市"});
		locationMap.put("张掖", new String[]{"甘肃省", "张掖市"});
		locationMap.put("漳州", new String[]{"福建省", "漳州市"});
		locationMap.put("昭通", new String[]{"云南省", "昭通市"});
		locationMap.put("肇庆", new String[]{"广东省", "肇庆市"});
		locationMap.put("镇江", new String[]{"江苏省", "镇江市"});
		locationMap.put("郑州", new String[]{"河南省", "郑州市"});
		locationMap.put("中山", new String[]{"广东省", "中山市"});
		locationMap.put("中卫", new String[]{"宁夏回族自治区", "中卫市"});
		locationMap.put("舟山", new String[]{"浙江省", "舟山市"});
		locationMap.put("周口", new String[]{"河南省", "周口市"});
		locationMap.put("株洲", new String[]{"湖南省", "株洲市"});
		locationMap.put("珠海", new String[]{"广东省", "珠海市"});
		locationMap.put("驻马店", new String[]{"河南省", "驻马店市"});
		locationMap.put("资阳", new String[]{"四川省", "资阳市"});
		locationMap.put("淄博", new String[]{"山东省", "淄博市"});
		locationMap.put("自贡", new String[]{"四川省", "自贡市"});
		locationMap.put("遵义", new String[]{"贵州省", "遵义市"});
		locationMap.put("台北", new String[]{"台湾省", "台北市"});
		locationMap.put("新北", new String[]{"台湾省", "新北市"});
		locationMap.put("桃园", new String[]{"台湾省", "桃园市"});
		locationMap.put("台中", new String[]{"台湾省", "台中市"});
		locationMap.put("台南", new String[]{"台湾省", "台南市"});
		locationMap.put("高雄", new String[]{"台湾省", "高雄市"});
		locationMap.put("基隆", new String[]{"台湾省", "基隆市"});
		locationMap.put("新竹市", new String[]{"台湾省", "新竹市"});
		locationMap.put("嘉义市", new String[]{"台湾省", "嘉义市"});
		locationMap.put("新竹县", new String[]{"台湾省", "新竹县"});
		locationMap.put("苗栗", new String[]{"台湾省", "苗栗县"});
		locationMap.put("彰化", new String[]{"台湾省", "彰化县"});
		locationMap.put("南投", new String[]{"台湾省", "南投县"});
		locationMap.put("云林", new String[]{"台湾省", "云林县"});
		locationMap.put("嘉义县", new String[]{"台湾省", "嘉义县"});
		locationMap.put("屏东", new String[]{"台湾省", "屏东县"});
		locationMap.put("宜兰", new String[]{"台湾省", "宜兰县"});
		locationMap.put("花莲", new String[]{"台湾省", "花莲县"});
		locationMap.put("台东", new String[]{"台湾省", "台东县"});
		locationMap.put("澎湖", new String[]{"台湾省", "澎湖县"});
		locationMap.put("北京", new String[]{"北京市", "北京市"});
		locationMap.put("上海", new String[]{"上海市", "上海市"});
		locationMap.put("天津", new String[]{"天津市", "天津市"});
		locationMap.put("重庆", new String[]{"重庆市", "重庆市"});
		locationMap.put("香港", new String[]{"香港特别行政区", "香港特别行政区"});
		locationMap.put("澳门", new String[]{"澳门特别行政区", "澳门特别行政区"});//378-20+1
	}
	
	static {
		holidayMonthDdateFormat = new SimpleDateFormat("yyyy/MM");
		holidayMonthSet = new HashSet<Date>();
		try {
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2012/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2013/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2014/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2015/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2016/12"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2017/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2017/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2017/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2017/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2017/10"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/01"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/02"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/04"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/05"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/06"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/09"));
			holidayMonthSet.add(holidayMonthDdateFormat.parse("2018/10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, ParseException {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
//		BufferedReader reader = new BufferedReader(new FileReader("/home/cellargalaxy/IdeaProjects/dachuang2/src/main/resources/holiday.json"));
//		String s;
//		while ((s = reader.readLine()) != null) {
//			String[] strings = s.split(",");
//			for (String string : strings) {
//				Date date = dateFormat.parse(string);
//				holidayMonthSet.add(holidayMonthDdateFormat.parse(holidayMonthDdateFormat.format(date)));
//			}
//		}
//		List<Date> dates=new LinkedList<Date>();
//		for (Date date : holidayMonthSet) {
//			dates.add(date);
//		}
//		Collections.sort(dates);
//		for (Date date : dates) {
//			System.out.println("holidayMonthSet.add(holidayMonthDdateFormat.parse(\"" + holidayMonthDdateFormat.format(date) + "\"));");
//		}
	}
	
	public static final int isHoliday(Date date) {
		return holidayMonthSet.contains(date) ? 1 : 2;
	}
	
	public static final int getMonthFromDate(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static final String[] getLocationFromText(String string) {
		if (string == null) {
			return null;
		}
		String[] strings = new String[]{null, null};
		int index = -1;
		for (Map.Entry<String, String[]> entry : locationMap.entrySet()) {
			int i = string.indexOf(entry.getKey());
			if (index == -1 || (i > -1 && i < index)) {
				index = i;
				strings = entry.getValue();
			}
		}
		return strings.clone();
	}
	
	public static final int getIntFromText(String string, int defaultValue) {
		if (string == null) {
			return defaultValue;
		}
		try {
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				return Integer.valueOf(matcher.group());
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	public static final double getDoubleFromText(String string, double defaultValue) {
		if (string == null) {
			return defaultValue;
		}
		try {
			Matcher matcher = pattern.matcher(string);
			if (matcher.find()) {
				return Double.valueOf(matcher.group());
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
}
