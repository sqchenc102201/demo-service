package com.it.demo;

import cn.hutool.core.date.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.it.demo.vo.ExcelImportVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/6/9
 */
@SpringBootTest
public class HutoolTest {
    @Test
    void TestIdUtil() {
        // 16050cc9e4d04b3e8a565bf4295e1b39
        System.out.println(IdUtil.simpleUUID());
        // fe77d959-68b9-429d-aafb-1b3ecb286242
        System.out.println(IdUtil.randomUUID());
        // 50f56f1ba1b84a56a30c2aa8f0d03f3b
        System.out.println(IdUtil.fastSimpleUUID());
        // a8f765ca-f8f6-4c5c-ad43-aac12079d75e
        System.out.println(IdUtil.fastUUID());
        // 5edefc39660d8cdb0fba2ffc
        System.out.println(IdUtil.objectId());
        // 1270190431321329664
        System.out.println(IdUtil.createSnowflake(1, 1).nextId());
        // 1270191749549133824
        System.out.println(IdUtil.getSnowflake(1,1).nextIdStr());
    }

    @Test
    void TestStrUtil() {
        // [a, b, cde, f]
        System.out.println(StrUtil.split("a:b:cde:f", StrUtil.C_COLON));
        // ab
        System.out.println(StrUtil.sub("abcdefg", 0, 2));
        // str -> 我爱你，就像老鼠爱大米
        System.out.println(StrUtil.format("{}爱{}，就像老鼠爱大米", "我", "你"));
        // StrUtil.isBlank() StrUtil.hasBlank() StrUtil.isEmpty()  StrUtil.hasEmpty()
    }

    @Test
    void TestDateUtil() {
        // 当前时间
        Date date = DateUtil.date();
        System.out.println(date);
        // 当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        System.out.println(date2);
        // 当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date3);
        // 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println(now);
        // 当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
        System.out.println(today);

        Date date4 = DateUtil.parse("2017-03-01");
        System.out.println(date4);
        Date date5 = DateUtil.parse("2017-03-01", "yyyy-MM-dd");
        System.out.println(date5);

        String dateStr = "2017-03-01";
        Date date6 = DateUtil.parse(dateStr);
        //结果 2017/03/01
        String format = DateUtil.format(date6, "yyyy/MM/dd");
        System.out.println(format);
        // 常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date6);
        System.out.println(formatDate);
        // 结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date6);
        System.out.println(formatDateTime);
        // 结果：00:00:00
        String formatTime = DateUtil.formatTime(date6);
        System.out.println(formatTime);

        Date date7 = DateUtil.date();
        // 获得年的部分
        System.out.println(DateUtil.year(date7));
        // 获得月份，从0开始计数
        System.out.println(DateUtil.month(date7));
        // 获得月份枚举
        System.out.println(DateUtil.monthEnum(date7));
        // 一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date7);
        System.out.println(beginOfDay);
        // 一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date7);
        System.out.println(endOfDay);
        // 昨天
        System.out.println(DateUtil.yesterday());
        // 明天
        System.out.println(DateUtil.tomorrow());
        // 上周
        System.out.println(DateUtil.lastWeek());
        // 下周
        System.out.println(DateUtil.nextWeek());
        // 上个月
        System.out.println(DateUtil.lastMonth());
        // 下个月
        System.out.println(DateUtil.nextMonth());

        // 日期时间差
        String dateStr1 = "2017-03-01 22:33:23";
        Date date8 = DateUtil.parse(dateStr1);
        String dateStr2 = "2017-04-01 23:33:23";
        Date date9 = DateUtil.parse(dateStr2);
        //相差一个月，31天
        long betweenDay = DateUtil.between(date8, date9, DateUnit.DAY);
        System.out.println(betweenDay);
        // 格式化时间差比如XX天XX小时XX分XX秒，此时使用DateUtil.formatBetween方法
        System.out.println(DateUtil.formatBetween(betweenDay, BetweenFormater.Level.SECOND));

        // 计时器
        TimeInterval timer = DateUtil.timer();
        //---------------------------------
        //-------这是执行过程
        //---------------------------------
        timer.interval();//花费毫秒数
        timer.intervalRestart();//返回花费时间，并重置开始时间
        timer.intervalMinute();//花费分钟数

        //年龄
        System.out.println(DateUtil.ageOfNow("1990-01-30"));
        // 是否闰年
        System.out.println(DateUtil.isLeapYear(2017));

    }

    @Test
    void TestDate() {
        // 日期时间偏移
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        System.out.println(newDate);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        System.out.println(newDate2);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
        System.out.println(newDate3);
    }


    @Test
    void TestExcelRead() {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\temp\\testData.xlsx"));
        reader.addHeaderAlias("性别", "gender");
        reader.addHeaderAlias("编号", "code");
        List<ExcelImportVo> all = reader.read(0, 1, ExcelImportVo.class);
        for (ExcelImportVo person : all) {
            System.out.println(person);
        }



//        List<List<Object>> read = reader.read();
//        read.forEach(x -> System.out.println(x));
//
//        System.out.println("===============list map================");
//        List<Map<String,Object>> readAll = reader.readAll();
//        readAll.forEach(x -> System.out.println(x));
//
//        System.out.println("===============list Bean================");
//        List<ExcelImportVo> all = reader.readAll(ExcelImportVo.class);
//        all.forEach(x -> System.out.println(x));

    }


    @Test
    public void test() {
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("user.dir"));

    }


}
