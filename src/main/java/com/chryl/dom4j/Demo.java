package com.chryl.dom4j;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Chr.yl on 2023/6/2.
 *
 * @author Chr.yl
 */
@Slf4j
public class Demo {
    public static void main(String[] args) throws DocumentException, MalformedURLException {
        /*
         <?xml version="1.0"?>
         <result>
             <interpretation grammar="builtin:grammar/boolean?language=zh-CN;y=1;n=2 builtin" confidence="1.0">
             <instance>广西</instance>
             <input mode="speech">广西</input>
             </interpretation>
         </result>
         */
        String str = "<?xml version=\\\"1.0\\\"?>\\n<result>\\n <interpretation grammar=\\\"builtin:grammar/boolean?language=zh-CN;y=1;n=2 builtin\\\" confidence=\\\"1.0\\\">\\n    <instance>广西</instance>\\n    <input mode=\\\"speech\\\">广西</input>\\n  </interpretation>\\n</result>";
//        String str = "<?xml version=\\\"1.0\\\"?><result><interpretation grammar=\\\"builtin:grammar/boolean?language=zh-CN;y=1;n=2 builtin\\\" confidence=\\\"1.0\\\"><instance>广西</instance><input mode=\\\"speech\\\">广西</input></interpretation></result>";
        str = str.replace("\\n", "");

        List<String> list = new ArrayList<>();
        Document document = DocumentHelper.parseText(str.replace("\\", ""));
//        在dom4j的API中， xml文件下的所有节点都是elment，无论是跟节点还是子元素节点
        Element root = document.getRootElement();  //获取根节点，即DATA
        List<Element> elements = root.elements();  //   //获取根节点下的所有子元素  ，即所有的ENTITY

        // 遍历所有子元素，即遍历出单个的一个个ENTITY
        for (Element element : elements) {
//            遍历出每个ENTITY里面的子元素，.getTextTrim()-- - 这个方法是得到元素的内容，将他们放到list中
            for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
                Element e = it.next();
                //获取标签名
//                String name = e.getName();
//                特别说明，attribute()这个方法是表示某个元素的属性，如<ENTITY  id='aaa' name='aaa'>,id就是第一个属性，name是他的第二个属性，还有attributes()，得到所有的属性集合
                //获取属性
//                Attribute mode = e.attribute("mode");
                //获得属性值
//                String s = mode.getData().toString();
                //获得值
                String textTrim = e.getTextTrim();
                list.add(textTrim);

            }
        }

        System.out.println(list);


    }
}
