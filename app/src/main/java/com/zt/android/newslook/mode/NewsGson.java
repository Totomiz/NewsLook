package com.zt.android.newslook.mode;

import java.util.List;

/**
 * Created by Tony on 16/10/28.
 */

public class NewsGson {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-10-20","title":"中国已是赫赫大国，中国人却还不是现代公民","description":"水木然","picUrl":"http://mmbiz.qpic.cn/mmbiz_png/xibK7xmJlMl6mIGZTE3wrp77zicn2wMYM96oFLMMRheLiaHRGH5fhtPSLhJjkWZ3FHB9tXjt56TxVtMw7gezaKgIw/0?wx_fmt=png","url":"http://mp.weixin.qq.com/s?__biz=MzA5NzY4NTQyMw==&mid=2651267428&idx=1&sn=ec8473c928d74c16a75e52665bc33803&chksm=8b6e9ba4bc1912b2955a0f0f24587978682fab"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-10-20
     * title : 中国已是赫赫大国，中国人却还不是现代公民
     * description : 水木然
     * picUrl : http://mmbiz.qpic.cn/mmbiz_png/xibK7xmJlMl6mIGZTE3wrp77zicn2wMYM96oFLMMRheLiaHRGH5fhtPSLhJjkWZ3FHB9tXjt56TxVtMw7gezaKgIw/0?wx_fmt=png
     * url : http://mp.weixin.qq.com/s?__biz=MzA5NzY4NTQyMw==&mid=2651267428&idx=1&sn=ec8473c928d74c16a75e52665bc33803&chksm=8b6e9ba4bc1912b2955a0f0f24587978682fab
     */

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
