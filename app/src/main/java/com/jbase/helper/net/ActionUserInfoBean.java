package com.jbase.helper.net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aaa on 2017/1/2.
 */

public class ActionUserInfoBean implements Serializable {


    /**
     * total : 936
     * commentcount : 0
     * infoMsg : 获取信息成功
     * mineDynInfo : [{"type":11,"iszan":null,"zan":0,"id":452,"actId":0,"imgfile1":"other/null","title":"","buildTime":"2016-11-14 17:37","name":"绯雨","wechat":"人生没有回头路，若梦如此，也就如此了。","shareText":"","sharecount":0,"detailweb":null,"remark":"null","collectcount":0,"assonname":"绯雨","shareImg":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=null","facefile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/active/2016-11-21-03-51-37728586.jpg","iscollect":null,"content":"如果最后一丝希望，也将被现实打败。","commentcount":0,"backgroundfile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-09-30-09-31-18284397.png","skipcount":2,"url":"http://www.mefengyun.com/sq/personcenter/onesDynamic-detail.jsp?phone=1&id=452","imgfile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-11-14-05-35-41074344.png","account":"13641024094","text":"如果最后一丝希望，也将被现实打败。","userId":"null","model":null},{"text":"一天一天，一天推翻一天。追逐自己的信仰。","type":11,"shareText":"","imgfile1":"other/null","userId":"null","sharecount":0,"name":"绯雨","zan":1,"account":"13641024094","model":null,"skipcount":10,"buildTime":"2016-11-08 09:47","shareImg":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=null","detailweb":null,"assonname":"绯雨","actId":0,"backgroundfile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-09-30-09-31-18284397.png","wechat":"人生没有回头路，若梦如此，也就如此了。","facefile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/active/2016-11-21-03-51-37728586.jpg","id":451,"iscollect":null,"commentcount":0,"imgfile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-11-08-09-46-00569717.png","remark":"null","url":"http://www.mefengyun.com/sq/personcenter/onesDynamic-detail.jsp?phone=1&id=451","collectcount":0,"iszan":null,"title":"","content":"一天一天，一天推翻一天。追逐自己的信仰。"}]
     * status : 1
     * userInfo : {"account":"18310070528","name":"xiao-yin","facefile":"http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/active/2016-11-23-02-35-38428989.jpg","backgroundfile":"null","shareText":null,"shareImg":null,"wechat":"纯爷们"}
     * commentList : []
     */

    private int total;
    private int commentcount;
    private String infoMsg;
    private String status;
    private UserInfoBean userInfo;
    private List<MineDynInfoBean> mineDynInfo;
    private List<?> commentList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public String getInfoMsg() {
        return infoMsg;
    }

    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<MineDynInfoBean> getMineDynInfo() {
        return mineDynInfo;
    }

    public void setMineDynInfo(List<MineDynInfoBean> mineDynInfo) {
        this.mineDynInfo = mineDynInfo;
    }

    public List<?> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<?> commentList) {
        this.commentList = commentList;
    }

    public static class UserInfoBean implements Serializable {
        /**
         * account : 18310070528
         * name : xiao-yin
         * facefile : http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/active/2016-11-23-02-35-38428989.jpg
         * backgroundfile : null
         * shareText : null
         * shareImg : null
         * wechat : 纯爷们
         */

        private String account;
        private String name;
        private String facefile;
        private String backgroundfile;
        private Object shareText;
        private Object shareImg;
        private String wechat;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFacefile() {
            return facefile;
        }

        public void setFacefile(String facefile) {
            this.facefile = facefile;
        }

        public String getBackgroundfile() {
            return backgroundfile;
        }

        public void setBackgroundfile(String backgroundfile) {
            this.backgroundfile = backgroundfile;
        }

        public Object getShareText() {
            return shareText;
        }

        public void setShareText(Object shareText) {
            this.shareText = shareText;
        }

        public Object getShareImg() {
            return shareImg;
        }

        public void setShareImg(Object shareImg) {
            this.shareImg = shareImg;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }

    public static class MineDynInfoBean implements Serializable {
        /**
         * type : 11
         * iszan : null
         * zan : 0
         * id : 452
         * actId : 0
         * imgfile1 : other/null
         * title :
         * buildTime : 2016-11-14 17:37
         * name : 绯雨
         * wechat : 人生没有回头路，若梦如此，也就如此了。
         * shareText :
         * sharecount : 0
         * detailweb : null
         * remark : null
         * collectcount : 0
         * assonname : 绯雨
         * shareImg : http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=null
         * facefile : http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/active/2016-11-21-03-51-37728586.jpg
         * iscollect : null
         * content : 如果最后一丝希望，也将被现实打败。
         * commentcount : 0
         * backgroundfile : http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-09-30-09-31-18284397.png
         * skipcount : 2
         * url : http://www.mefengyun.com/sq/personcenter/onesDynamic-detail.jsp?phone=1&id=452
         * imgfile : http://192.168.1.4:8080/sq/assonFileController/fileWay.do?fileName=upload_file/other/2016-11-14-05-35-41074344.png
         * account : 13641024094
         * text : 如果最后一丝希望，也将被现实打败。
         * userId : null
         * model : null
         */

        private int type;
        //0 是赞过  0是 1否
        private int iszan;
        private int zan;
        private int id;
        private int actId;
        private String imgfile1;
        private String title;
        private String buildTime;
        private String name;
        private String wechat;
        private String shareText;
        private int sharecount;
        private Object detailweb;
        private String remark;
        private int collectcount;
        private String assonname;
        private String shareImg;
        private String facefile;
        private int iscollect;
        private String content;
        private int commentcount;
        private String backgroundfile;
        private int skipcount;
        private String url;
        private String imgfile;
        private String account;
        private String text;
        private String userId;
        private Object model;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIszan() {
            return iszan;
        }

        public void setIszan(int iszan) {
            this.iszan = iszan;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getActId() {
            return actId;
        }

        public void setActId(int actId) {
            this.actId = actId;
        }

        public String getImgfile1() {
            return imgfile1;
        }

        public void setImgfile1(String imgfile1) {
            this.imgfile1 = imgfile1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBuildTime() {
            return buildTime;
        }

        public void setBuildTime(String buildTime) {
            this.buildTime = buildTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getShareText() {
            return shareText;
        }

        public void setShareText(String shareText) {
            this.shareText = shareText;
        }

        public int getSharecount() {
            return sharecount;
        }

        public void setSharecount(int sharecount) {
            this.sharecount = sharecount;
        }

        public Object getDetailweb() {
            return detailweb;
        }

        public void setDetailweb(Object detailweb) {
            this.detailweb = detailweb;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCollectcount() {
            return collectcount;
        }

        public void setCollectcount(int collectcount) {
            this.collectcount = collectcount;
        }

        public String getAssonname() {
            return assonname;
        }

        public void setAssonname(String assonname) {
            this.assonname = assonname;
        }

        public String getShareImg() {
            return shareImg;
        }

        public void setShareImg(String shareImg) {
            this.shareImg = shareImg;
        }

        public String getFacefile() {
            return facefile;
        }

        public void setFacefile(String facefile) {
            this.facefile = facefile;
        }

        public int getIscollect() {
            return iscollect;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public String getBackgroundfile() {
            return backgroundfile;
        }

        public void setBackgroundfile(String backgroundfile) {
            this.backgroundfile = backgroundfile;
        }

        public int getSkipcount() {
            return skipcount;
        }

        public void setSkipcount(int skipcount) {
            this.skipcount = skipcount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgfile() {
            return imgfile;
        }

        public void setImgfile(String imgfile) {
            this.imgfile = imgfile;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
            this.model = model;
        }


        @Override
        public String toString() {
            return "MineDynInfoBean{" +
                    "type=" + type +
                    ", iszan=" + iszan +
                    ", zan=" + zan +
                    ", id=" + id +
                    ", actId=" + actId +
                    ", imgfile1='" + imgfile1 + '\'' +
                    ", title='" + title + '\'' +
                    ", buildTime='" + buildTime + '\'' +
                    ", name='" + name + '\'' +
                    ", wechat='" + wechat + '\'' +
                    ", shareText='" + shareText + '\'' +
                    ", sharecount=" + sharecount +
                    ", detailweb=" + detailweb +
                    ", remark='" + remark + '\'' +
                    ", collectcount=" + collectcount +
                    ", assonname='" + assonname + '\'' +
                    ", shareImg='" + shareImg + '\'' +
                    ", facefile='" + facefile + '\'' +
                    ", iscollect=" + iscollect +
                    ", content='" + content + '\'' +
                    ", commentcount=" + commentcount +
                    ", backgroundfile='" + backgroundfile + '\'' +
                    ", skipcount=" + skipcount +
                    ", url='" + url + '\'' +
                    ", imgfile='" + imgfile + '\'' +
                    ", account='" + account + '\'' +
                    ", text='" + text + '\'' +
                    ", userId='" + userId + '\'' +
                    ", model=" + model +
                    '}';
        }
    }
}
