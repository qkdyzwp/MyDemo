package com.fir.wp.mydemo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.fir.wp.mydemo.R;
import com.fir.wp.mydemo.adapter.PicAdapter;
import com.fir.wp.mydemo.decoration.PicDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fir on 16/2/17 16:11
 * 邮箱：446893043@qq.com
 * 公司：北京微宝
 */
public class PicActivity extends BaseActivity{

    private RecyclerView picList;


    @Override
    public int getLayout() {
        return R.layout.pic_list_layout;
    }

    @Override
    public void initView() {
        picList= (RecyclerView) findViewById(R.id.picList);
        picList.setLayoutManager(new LinearLayoutManager(this));
        picList.addItemDecoration(new PicDecoration(this,R.drawable.pic_shap,10));
        picList.setAdapter(new PicAdapter(URL_LIST,this));
    }

    private static final List<String> URL_LIST;

    static {
        URL_LIST = new ArrayList<>();
        URL_LIST.add("http://a3.qpic.cn/psb?/V10Ktqjw0ic4gs/Nlvz00Wr3MkxiQHqIdj.sT.tm*zPFxf8nYqOuFJjyr4!/b/dHIBAAAAAAAA&bo=wQOAAgAAAAAFB2Q!&rf=viewer_4");
        URL_LIST.add("http://a3.qpic.cn/psb?/V10Ktqjw0ic4gs/G4hkD2PsmFlySQT89WPz0MD5VzTPnTTrwS6CjOn3IY8!/b/dIsBAAAAAAAA&bo=wQOAAgAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/2qFF.abH*5sVVZkbVza7zfnGgJfqwlz97nhfy.GPRYQ!/b/dHIBAAAAAAAA&bo=gALBAwAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/vIuK6rYvvMOIRjU71F*Xo8PyxNiLSWs6WBNT*WS9EHY!/b/dH4BAAAAAAAA&bo=gALBAwAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/nuv5OONakDIXedTX4iHqQ7.FRtjn6G0Eq8Czz2qm3Ng!/b/dHIBAAAAAAAA&bo=wQOAAgAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a1.qpic.cn/psb?/V10Ktqjw0ic4gs/U0mO.ypdng4O90DqT7AkO0b2BOHOWQOqWAhCm5WE.4Y!/b/dH0BAAAAAAAA&bo=wQOAAgAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/6mavvi6N4aWUOL7E1sCU3jr02iD3IAJzMmqn6y.wAFY!/b/dHIBAAAAAAAA&bo=gALBAwAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/hU1OhonE*fT.nyvuIVxYp81CznEtRc7eVW.zNqHM.W0!/b/dH4BAAAAAAAA&bo=wQOAAgAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a1.qpic.cn/psb?/V10Ktqjw0ic4gs/MT4wzka34xDedHwFG4unDOVNoKx58F3escDs0xbTENc!/b/dIwBAAAAAAAA&bo=gALBAwAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a3.qpic.cn/psb?/V10Ktqjw0ic4gs/i9mQHBgRQORFb5FJPj2WpMYZH6OP*ya4EPlnDY1Y9F4!/b/dIsBAAAAAAAA&bo=gALBAwAAAAAFAGM!&rf=viewer_4");
        URL_LIST.add("http://a2.qpic.cn/psb?/V10Ktqjw0ic4gs/yXWPxH2wpVAoP3eMa8J.zmQ9JE1s9c.5Ez*C3xqfOl8!/b/dHIBAAAAAAAA&bo=wQOAAgAAAAAFAGM!&rf=viewer_4");
    }
}
