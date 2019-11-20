package apps.sensitive;

import java.util.*;

/**
 * 敏感词过滤
 * todo: 未完成 参考资料：https://my.oschina.net/magicalSam/blog/1528428
 */
public class SensitiveWordApp {
    public static void main(String[] args) {
        HashSet<String> sensitiveWordSet = new HashSet<String>();
        sensitiveWordSet.add("太多");
        sensitiveWordSet.add("爱恋");
        sensitiveWordSet.add("静静");
        sensitiveWordSet.add("哈哈");
        sensitiveWordSet.add("啦啦");
        SensitiveWordApp.initSensitiveWordMap(sensitiveWordSet);

        String content = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节。"
                + "然后我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个贱人一杯红酒一部电影在夜 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + content.length());
        System.out.println("是否含有敏感字符：" + contains(content));
    }



    private static HashMap sensitiveWordMap;
    private static Integer MinMatchType = 0;
    private static Integer MaxMatchType = 1;

    public static synchronized void init(Set<String> sensitiveWordSet) {
        initSensitiveWordMap(sensitiveWordSet);
    }

    // 初始化敏感词库，构建DFA模型
    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        sensitiveWordMap = new HashMap(sensitiveWordSet.size());
        String key;
        Map nowMap;
        Map<String, String> newWordMap;

        Iterator<String> iterator = sensitiveWordSet.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i); // 转成char类型，也就是“中国人”的话，做成： {"中"：{...}}
                Object wordMap = nowMap.get(keyChar);
                if (wordMap != null) { // 存在这个key，则直接使用这个key
                    nowMap = (Map) wordMap;
                } else {
                    // 构建一个新的map
                    newWordMap = new HashMap<String, String>();
                    newWordMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWordMap);
                    nowMap = newWordMap;
                }
                if (i == key.length() -1 ) {
                    nowMap.put("isEnd", "1"); // 最后一个
                }
            }
        }
    }

    public static boolean contains(String txt) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveWord(txt, i, MinMatchType); // 最小模式匹配
            if (matchFlag > 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 检查是否存在敏感字符
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return 如果存在则返回敏感字符的长度，不存在则返回0
     */
    private static int checkSensitiveWord(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag ++;
                if (nowMap.get("isEnd").equals("1")) {
                    flag = true;
                    if (MinMatchType == matchType) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (matchFlag < 2 || !flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }
}
