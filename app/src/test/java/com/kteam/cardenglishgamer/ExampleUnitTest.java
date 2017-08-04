package com.kteam.cardenglishgamer;

        import com.kteam.cardenglishgamer.util.Http.HttpUtil;

        import org.junit.Test;

        import java.util.HashMap;
        import java.util.Map;

        import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        final String api_head = "https://api.seniverse.com/v3/weather/now.json";
        final String api_bottom = "?key=0siqafiadmpsuutm&location=beijing&language=zh-Hans&unit=c";

        //完整地址get
        String result =  HttpUtil.get(api_head+api_bottom);

        System.out.println("first:"+result);
        //通过地址+参数列表get
        Map<String,String> params = new HashMap<String,String>(){
            {
                put("key","0siqafiadmpsuutm");
                put("location","beijing");
                put("language","zh-Hans");
                put("unit","c");
            }
        };
        result =  HttpUtil.get(api_head,params);
        System.out.println("first:"+result);


        //通过地址+参数表+头字段get
        result = HttpUtil.get(api_head,params,null);
        System.out.println("first:"+result);

        //完整地址post
        result =  HttpUtil.post(api_head,params);
        System.out.println("first:"+result);
        assertEquals(4, 2 + 2);
    }
}