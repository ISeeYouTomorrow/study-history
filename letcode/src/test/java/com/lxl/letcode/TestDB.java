package com.lxl.letcode;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lukas
 * @since 2019/6/15 14:59
 **/
public class TestDB {

    @Test
    public void testMatch() {
        String input = "`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',";
        String reg = "`.*`";
        String reg2 = "COMMENT '.*'";
        System.out.println(getMatch(reg, input));
        System.out.println(getMatch(reg2, input));

        System.out.println(getMatch(input));
    }


    public static String getMatch(String reg, String line) {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            result.append(matcher.group()).append("     ");
        }
        return result.toString();
    }

    static Pattern p1 = Pattern.compile("`.*`");

    static Pattern p2 = Pattern.compile("COMMENT '.*'");

    public static String getMatch(String input) {
        StringBuilder result = new StringBuilder();
        Matcher m1 = p1.matcher(input);
        while (m1.find()) {
            result.append(m1.group()).append("     ");
        }

        Matcher m2 = p2.matcher(input);

        while (m2.find()) {
            result.append(m2.group()).append("\t\t\t\t\t");
        }
        String s = result.toString();
        if (StringUtils.contains(s,"COMMENT ")) {
//            s.replace("COMMENT "," ");
            s = StringUtils.remove(s,"COMMENT");
        }
        s = StringUtils.remove(s,"`");
        s = StringUtils.remove(s,"'");
        return s;
    }

    @Test
    public void getNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println(calendar.getTime().toString());
    }


    public static void main(String[] args) {
        String fileName = "F:\\工作内容\\储易智慧\\sql\\guilin-test.sql";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            File outFile = new File("F:\\工作内容\\储易智慧\\sql\\guilin-test-output.txt");

            String line;
            String temp = "";
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (StringUtils.contains(line,"DROP TABLE")
                        || StringUtils.contains(line,"PRIMARY KEY")
                        || StringUtils.contains(line,"INDEX")
                        || StringUtils.contains(line,"ENGINE = InnoDB"))
                    continue;
                temp = getMatch(line);
                if (StringUtils.contains(line, "CREATE TABLE")) {
                    lines.add("\r\n");
                    lines.add(temp);
                } else {
                  if (temp != null && !temp.isEmpty()) {
                      lines.add(temp);
                  }
                }
            }

            FileUtils.writeLines(outFile, lines);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
