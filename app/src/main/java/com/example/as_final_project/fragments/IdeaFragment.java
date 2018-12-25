package com.example.as_final_project.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.IdeaItemAdapter;
import com.example.as_final_project.adapters.recyclerViewadapter;
import com.example.as_final_project.entities.Idea;
import com.example.as_final_project.entities.Movie;
import com.example.as_final_project.imageCarousel.ImageCarousel;
import com.example.as_final_project.imageCarousel.ImageInfo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class IdeaFragment extends BaseFragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_idea, container, false);

        //卡片
        setHasOptionsMenu(true);
        initView_card();
        initData_card();
        LinearLayoutManager m=new LinearLayoutManager(getContext());
        m.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(m);
        recyclerViewadapter adapter=new recyclerViewadapter(lists,getContext());
        recycler_view.setAdapter(adapter);

        //轮播图
        Fresco.initialize(this.getActivity());

        //列表
        initView_list();
        initData_list();
        LinearLayoutManager m_list=new LinearLayoutManager(getContext());
        m_list.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view_list.setLayoutManager(m_list);
        IdeaItemAdapter adapter_list=new IdeaItemAdapter(lists_idea,getContext());
        recycler_view_list.setAdapter(adapter_list);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //开始图片轮播
        //Fresco.initialize(this.getActivity());
        initView();
        initEvent();
        imageStart();

    }


    //轮播图
    // 图片轮播控件
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;
    private LinearLayout mLineLayoutDot;
    private ImageCarousel imageCarousel;
    private List<View> dots;//小点

    // 图片数据，包括图片标题、图片链接、数据、点击要打开的网站（点击打开的网页或一些提示指令）
    private List<ImageInfo> imageInfoList;

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        imageInfoList = new ArrayList<>();
        imageInfoList.add(new ImageInfo(1, "海王：英雄不问出身，大爱无畏疆界", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546276936&di=8af8a1df04066b516ca43795265e77fc&imgtype=jpg&er=1&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FwtzDZX3cwBSTV5ug%3DQoPLx8KN%3D1mVmt6wrd4WGsgvUfjL1538901879737.jpg", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "活着：一只蝼蚁的史诗", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546276983&di=63930853efba4b4f06d613a9a01500f8&imgtype=jpg&er=1&src=http%3A%2F%2Fs1.sinaimg.cn%2Flarge%2F001pG1Czzy7cU1HcEBye8", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "唐人街探案：看天才少年与二货亲戚上演破案传奇", "", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545682457224&di=703a3299596d7301fc79ed67644aa743&imgtype=0&src=http%3A%2F%2Fimage.uczzd.cn%2F5202523055764161161.jpg%3Fid%3D0", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "白夜行：有些人活在看不到日光的白夜中", "仅展示", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546277306&di=d221d05084323932674622b84279acf7&imgtype=jpg&er=1&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0f54144c9806465ab1ec7455fbc76c058a8f0696.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "哈尔的移动城堡：帅就完事了！", "仅展示", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545682663294&di=04629bbc36531d89ae4fd0e0f59de248&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F151106%2F48-151106115H9.jpg", ""));


    }

    /**
     * 初始化控件
     */
    private void initView() {

        mViewPager = this.getActivity().findViewById(R.id.viewPager);
        mTvPagerTitle = this.getActivity().findViewById(R.id.tv_pager_title);
        mLineLayoutDot = this.getActivity().findViewById(R.id.lineLayout_dot);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void imageStart() {
        //设置图片轮播
        int[] imgaeIds = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4,
                R.id.pager_image5, R.id.pager_image6, R.id.pager_image7, R.id.pager_image8};
        String[] titles = new String[imageInfoList.size()];
        List<SimpleDraweeView> simpleDraweeViewList = new ArrayList<>();

        for (int i = 0; i < imageInfoList.size(); i++) {
            titles[i] = "     " + imageInfoList.get(i).getTitle();
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.getActivity());
            simpleDraweeView.setAspectRatio(1.78f);
            // 设置一张默认的图片
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(this.getResources())
                    .setPlaceholderImage(ContextCompat.getDrawable(this.getActivity(), R.drawable.defult), ScalingUtils.ScaleType.CENTER_CROP).build();
            simpleDraweeView.setHierarchy(hierarchy);
            simpleDraweeView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

            //加载高分辨率图片;
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageInfoList.get(i).getImage()))
                    .setResizeOptions(new ResizeOptions(1280, 720))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    //.setLowResImageRequest(ImageRequest.fromUri(Uri.parse(listItemBean.test_pic_low))) //在加载高分辨率图片之前加载低分辨率图片
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);

            simpleDraweeView.setId(imgaeIds[i]);//给view设置id
            simpleDraweeView.setTag(imageInfoList.get(i));
            simpleDraweeView.setOnClickListener(this);
            titles[i] = imageInfoList.get(i).getTitle();
            simpleDraweeViewList.add(simpleDraweeView);

        }

        dots = addDots(mLineLayoutDot, fromResToDrawable(this.getActivity(), R.drawable.ic_dot_focused), simpleDraweeViewList.size());
        imageCarousel = new ImageCarousel(this.getActivity(), mViewPager, mTvPagerTitle, dots, 5000);
        imageCarousel.init(simpleDraweeViewList, titles)
                .startAutoPlay();
        imageCarousel.start();

    }


    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return 小点的Id
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this.getActivity());
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(android.view.View.generateViewId());
        (this.getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.addView(dot);
            }
        });

        return dot.getId();
    }


    /**
     * 资源图片转Drawable
     *
     * @param context 上下文
     * @param resId   资源ID
     * @return 返回Drawable图像
     */
    public static Drawable fromResToDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
        //return context.getResources().getDrawable(resId);
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout 线性横向布局
     * @param backgount    小点资源图标
     * @param number       数量
     * @return 返回小点View集合
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout, backgount);
            dots.add(this.getActivity().findViewById(dotId));

        }
        return dots;
    }

    //横向滑动卡片
    private RecyclerView recycler_view;
    private TextView tv1,tv2;
    private View view;
    private List<Movie> lists;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    private void initData_card() {
        lists=new ArrayList<>();
        lists.add(new Movie(12345,"海王","大爱无疆界","https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1545672873&di=08f15098f87e6bf4e13f369fdd9d48bf&src=http://i0.hdslb.com/bfs/article/e13fae81ec45ddaea7ba1fe7092d6949535f9a79.jpg"));
        lists.add(new Movie(54321,"活着","一只蝼蚁的史诗","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546277715&di=b949d65f1be7cb5d29e211d88bb0e713&imgtype=jpg&er=1&src=http%3A%2F%2Fs13.sinaimg.cn%2Fbmiddle%2F001DLqSEzy7bWaU7CRC9c%26amp%3B690"));
        lists.add(new Movie(67890,"龙猫","那个平凡却非凡的美妙夏天","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545683038455&di=6c610e5a1a20f3df8622ebe329411457&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201607%2F25%2F20160725150936_tEskA.jpeg"));
        lists.add(new Movie(98761,"哈尔的移动城堡","帅就完事了！","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545683132173&di=2f132b751b48cf0f72de2aaa1558913e&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201408%2F07%2F20140807180945_tMEwv.thumb.700_0.jpeg"));
        lists.add(new Movie(11111,"白夜行","有些人活在看不到日光的白夜中","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546277922&di=33872a6907c8f924be2af6bcf6b4305e&imgtype=jpg&er=1&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201803%2F22%2F20180322175446_UaS8T.jpeg"));
        lists.add(new Movie(22222,"唐人街探案","看天才少年与二货亲戚上演破案传奇","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545683325827&di=7066e3da91d873d3319a0b958d668641&imgtype=0&src=http%3A%2F%2Fimage14.m1905.cn%2Fuploadfile%2F2015%2F1217%2F20151217111038346908.jpg"));
    }
    private void initView_card() {
        recycler_view= (RecyclerView) view.findViewById(R.id.recycler_View);
        tv1= (TextView) view.findViewById(R.id.tv1);
        tv2= (TextView) view.findViewById(R.id.tv2);

    }

    //想法列表
    private RecyclerView recycler_view_list;
    private TextView idea_title,idea_context,idea_num;
    private List<Idea> lists_idea;


    private void initData_list() {
        lists_idea=new ArrayList<>();
        lists_idea.add(new Idea(12345,"电影《海王》中有哪些菜单和不易发现的细节？","米饭大帝;我来说一个冷门的吧，海王他爹。他爹正经是个等他守护人。电影一开头我们就可以清楚...",1879,234,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546342243&di=d7745b393f0aa423a00d04e01ec1acbb&imgtype=jpg&er=1&src=http%3A%2F%2Fn.sinaimg.cn%2Fent%2Fcrawl%2F92%2Fw550h342%2F20181130%2FU50m-hphsupx6160332.jpg"));
        lists_idea.add(new Idea(54321,"如何评价《人间失格》？","童山濯濯，如同徘徊在山巅，抬头一望对峰还站着另一个人，遥远但是清晰，触发共情。是在有易于倾向的时候捡起来读...",2348,234,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545747589790&di=27c600ba2a302abcc7e71ee54288de39&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20140316%2FImg396681849.jpg"));
        lists_idea.add(new Idea(67890,"宫崎骏的《千与千寻》好看在哪里？","无色方糖：（多图）千与千寻，真的是一部很好看的电影。因为它有着极...",5623,345,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545747635157&di=82c5ea6083ad56e91614e68b7ef1cb69&imgtype=0&src=http%3A%2F%2Fwww.pkusky.com%2Fupfile%2Fimages%2FCHUAN%2520YUE%2520QIAN%2520YU%2520QIAN%2520XUN.png"));
        lists_idea.add(new Idea(98761,"给大学生拟一份通识教育书单，哪些书可入围？","张兆杰：一直就想写个书单，一直在打腹稿，一直在想怎么才能把这个书单概括...",9264,439,"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3305772431,2603488576&fm=26&gp=0.jpg"));
        lists_idea.add(new Idea(11111,"怎么评价《大江大河》里的宋运萍？","虽然用今天的眼光看，宋运萍几乎有点过于完美了。但在那个年代，在那样的环境里...",4218,425,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546342455&di=e986a73baf5abd6b1a2bb4d247ed1b8f&imgtype=jpg&er=1&src=http%3A%2F%2Fn.sinaimg.cn%2Ftranslate%2F783%2Fw950h633%2F20181213%2FZcrC-hqackaa9890648.jpg"));
        lists_idea.add(new Idea(22222,"撒贝宁对《明星大侦探》有多重要？","念忘之间：泻药 我觉得对于《明星大侦探》来说，撒贝宁是奠基石、灵魂一般的存在。先说最重要的...",7123,213,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545747825529&di=cb961124eb2775234953fb1aa5232d65&imgtype=0&src=http%3A%2F%2Fi.shangc.net%2Farticle%2F20181110%2F4d179506445368a66c0085d8d9bf5358.jpg"));
    }
    private void initView_list() {
        recycler_view_list= (RecyclerView) view.findViewById(R.id.recycler_View_list);
        idea_title= (TextView) view.findViewById(R.id.idea_title);
        idea_context= (TextView) view.findViewById(R.id.idea_context);
        idea_num = (TextView)view.findViewById(R.id.idea_num);

    }



}


