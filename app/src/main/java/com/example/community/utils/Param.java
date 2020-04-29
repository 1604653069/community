package com.example.community.utils;

import android.net.Uri;
import android.os.Environment;

import com.example.community.entity.Address;
import com.example.community.entity.Auth;
import com.example.community.entity.ShopCart;
import com.example.community.entity.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

/*全局参数类型*/
public class Param {
    /*当前登录的用户*/
    public static User user;
    /*当前用户是否通过认证*/
    public static Auth auth;
    /*购物车中选中的商品*/
    public static ShopCart shopCart;
    /*用户默认的收货地址*/
    public static Address address;
    /*图片选择代码*/
    public static final int REQUEST_CODE_CHOOSE=1;
    /*JSON数据的传输格式*/
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /*用户所上传图片的Uri地址*/
    public static List<Uri> mSelect = new ArrayList<>();
    /*用户发表说说的内容*/
    public static String content = "";
    public static int money=0;
    /*服务器IP地址*/
    public static final String IP="39.105.68.228";
    /*端口*/
    public static final String POST="8080";
    /*服务器名称*/
    public static final String URL="community";
    /*文件上传路径*/
    public static final String UPLOAD_PATH= Environment.getExternalStorageDirectory().getPath()+"/uploadIMG";
    /*登陆请求接口*/
    public static final String LOGINACTION="userAction_login.action";
    /*用户更新接口*/
    public static final String USERUPDATE="userAction_update.action";
    /*备用登陆接口*/
    public static final String LOGINACTION2="userAction_login2.action";
    /*公告信息接口*/
    public static final String PLAACTION ="placard_getList.action";
    /*文件上传接口*/
    public static final String FILEUPLOAD="fileupload.action";
    /*报修问题提交的接口*/
    public static final String REPAIR="repair_commitRepair.action";
    /*获取所有说说的接口*/
    public static final String FINDALLSS ="ssAction_findAllSS.action";
    /*发表说说的接口*/
    public static final String SENDSS = "ssAction_addSS.action";
    /*删除说说的接口*/
    public static final String REMOVESS="ssAction_removeSS.action";
    /*获取商品的信息*/
    public static final String GETSHOPS ="product_getAllProduct.action";
    /*获取开始界面的商品信息*/
    public static final String STARTUPSHOPS = "product_getStartUpShops.action";
    /*添加购物车清单*/
    public static final String CARTITEM = "cart_addCartItem.action";
    /*获取购物车清单*/
    public static final String CARTINFO = "cart_getCartInfo.action";
    /*获取房屋消息*/
    public static final String HOUSEINFO="house_getAllHouseInfo.action";
    /*获取收货地址信息*/
    public static final String ADDRESSINFO= "address_getAddress.action";
    /*修改收货地址信息*/
    public static final String CHANGEADDRESS="address_changeAddress.action";
    /*删除收货地址*/
    public static final String REMOVEADDRESS="address_removeAddress.action";
    /*添加收货地址*/
    public static final String ADDADDRESS="address_addAddress.action";
    /*添加商品收藏地址*/
    public static final String ADDFAVORITEPRODUCT = "favorite_addFavoriteItem.action";
    /*获取所有的收藏信息地址*/
    public static final String GETALLFAVORITEINFO = "favorite_getFavoriteInfo.action";
    /*删除已收藏的信息*/
    public static final String REMOVEFAVORITEINFO="favorite_removeFavoriteItem.action";
    /*用户充值*/
    public static final String CHONGZHI = "wallet_chongzhi.action";
    /*获取用户余额*/
    public static final String GETUSERWALLET = "wallet_getUserWallet.action";
    /*添加充值记录的接口*/
    public static final String ADDHISTORY="history_addHistory.action";
    /*获取当前用户的所有充值记录*/
    public static final String GETALLUSERHISTORY = "history_getAllUserHistory.action";
    /*上传头像*/
    public static final String HEADIMG="userHead_addUserHeadImg.action";
    /*获取用户的头像*/
    public static final String GETUSERHEAD="userHead_getUserHeadImg.action";
    /*用户提交订单*/
    public static final String COMMITORDER="order_addOrder.action";
    /*修改用户订单*/
    public static final String UPDATEORDER="order_updateOrder.action";
    /*用户查询订单*/
    public static final String GETALLORDER = "order_getUserOrder.action";
    /*更新用户积分*/
    public static final String UPDATESCORE= "score_updateScore.action";
    /*获取用户积分*/
    public static final String GETUSERSCORE="score_getUserScore.action";
    /*提交认证信息*/
    public static final String UPDATEAUTH="auth_update2.action";
    /*获取认证信息*/
    public static final String GETMUAUTH="auth_getMyAuth.action";
    /*获取缴费订单*/
    public static final String GETALLPAYS = "pay_getAllPays.action";
    /*获取缴费订单项*/
    public static final String GETPAYITEM = "payitem_getUserPayItem.action";
    /*修改缴费订单项*/
    public static final String UPDATEPAYITEM="payitem_updatePayItem.action";
    /*用户注册*/
    public static final String USERREGIST="userAction_regist.action";
    /*忘记密码*/
    public static final String FORGET="userAction_forget.action";
    /*通过手机获取用户*/
    public static final String GETUSERBYTEL="userAction_getUserByTel.action";
    /*条件查询房屋*/
    public static final String GETHOUSEBYSORT="house_getHouseBySort.action";
    /*获取用户的所有说说*/
    public static final String GETUSERALLSS="ssAction_getSSByUid.action";
    /*获取当前用户的所有报修订单*/
    public static final String GETUSERALLREPAIR="repair_getUserAllRepair.action";
    /*获取用户的认证信息*/
    public static final String GETUSERAUTH="auth_getUserAuth.action";
    /*登陆请求路径*/
    public static final String LOGIN_URL="http://"+IP+":"+POST+"/"+URL+"/"+LOGINACTION;
    /*公告请求路径*/
    public static final String  PLA_URL="http://"+IP+":"+POST+"/"+URL+"/"+PLAACTION;
    /*文件上传路径*/
    public static final String UPLOAD_URL="http://"+IP+":"+POST+"/"+URL+"/"+FILEUPLOAD;
    /*报修问题的接口*/
    public static final String REPAIR_URL="http://"+IP+":"+POST+"/"+URL+"/"+REPAIR;
    /*获取所有说说的请求路径*/
    public static final String FINDALLSS_URL = "http://"+IP+":"+POST+"/"+URL+"/"+FINDALLSS;
    /*发表说说的请求路径*/
    public static final String SENDSS_URL =  "http://"+IP+":"+POST+"/"+URL+"/"+SENDSS;
    /*删除说说的请求路径*/
    public static final String REMOVESS_URL = "http://"+IP+":"+POST+"/"+URL+"/"+REMOVESS;
    /*获取商品的信息*/
    public static final String GETALLSHOPS ="http://"+IP+":"+POST+"/"+URL+"/"+GETSHOPS;
    /*获取开始界面的商品信息*/
    public static final String GETSTARTUPSHOPS = "http://"+IP+":"+POST+"/"+URL+"/"+STARTUPSHOPS;
    /*添加购物车清单*/
    public static final String ADDCARTITEM ="http://"+IP+":"+POST+"/"+URL+"/"+CARTITEM;
    /*获取购物车清单*/
    public static final String GETCARTINFO ="http://"+IP+":"+POST+"/"+URL+"/"+CARTINFO;
    /*获取房屋消息*/
    public static final String GETHOUSEINFO = "http://"+IP+":"+POST+"/"+URL+"/"+HOUSEINFO;
    /*获取用户的收货地址*/
    public static final String GETADDRESSINFO = "http://"+IP+":"+POST+"/"+URL+"/"+ADDRESSINFO;
    /*修改用户收货地址*/
    public static final String CHANGEADDRESSINFO = "http://"+IP+":"+POST+"/"+URL+"/"+CHANGEADDRESS;
    /*添加用户收货地址*/
    public static final String ADDADDRESSINFO = "http://"+IP+":"+POST+"/"+URL+"/"+ADDADDRESS;
    /*删除用户收货地址*/
    public static final String REMOVEADDRESSINFO = "http://"+IP+":"+POST+"/"+URL+"/"+REMOVEADDRESS;
    /*添加用户收藏信息地址*/
    public static final String ADDFAVORITEPRODUCT_URL="http://"+IP+":"+POST+"/"+URL+"/"+ADDFAVORITEPRODUCT;
    /*获取所有用户收藏信息地址*/
    public static final String GETALLFAVORITEINFO_URL ="http://"+IP+":"+POST+"/"+URL+"/"+GETALLFAVORITEINFO;
    /*删除用户已收藏的信息*/
    public static final String REMOVEFAVORITE_URL="http://"+IP+":"+POST+"/"+URL+"/"+REMOVEFAVORITEINFO;
    /*用户充值接口*/
    public static final String USERCHONGZHI = "http://"+IP+":"+POST+"/"+URL+"/"+CHONGZHI;
    /*获取用户余额信息接口*/
    public static final String GETUSERWALLET_URL = "http://"+IP+":"+POST+"/"+URL+"/"+GETUSERWALLET;
    /*添加用户充值记录的接口*/
    public static final String ADDHISTORY_URL = "http://"+IP+":"+POST+"/"+URL+"/"+ADDHISTORY;
    /*获取所有用户的充值记录*/
    public static final String GETALLUSERHISTORY_URL = "http://"+IP+":"+POST+"/"+URL+"/"+GETALLUSERHISTORY;
    /*获取用户的头像*/
    public static final String GETUSERHEAD_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERHEAD;
    /*上传用户的头像*/
    public static final String HEADIMG_URL = "http://"+IP+":"+POST+"/"+URL+"/"+HEADIMG;
    /*用户提交订单的接口*/
    public static final String COMMITORDER_URL="http://"+IP+":"+POST+"/"+URL+"/"+COMMITORDER;
    /*修改用户订单的接口*/
    public static final String UPDATEORDER_URL="http://"+IP+":"+POST+"/"+URL+"/"+UPDATEORDER;
    /*用户查询订单的接口*/
    public static final String GETALLORDER_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETALLORDER;
    /*更新用户积分的接口*/
    public static final String UPDATESCORE_URL="http://"+IP+":"+POST+"/"+URL+"/"+UPDATESCORE;
    /*获取用户积分接口*/
    public static final String GETUSERSCORE_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERSCORE;
    /*提交用户认证信息接口*/
    public static final String UPDATEAUTH_URL="http://"+IP+":"+POST+"/"+URL+"/"+UPDATEAUTH;
    /*获取认证信息接口*/
    public static final String GETMYAUTH_URL ="http://"+IP+":"+POST+"/"+URL+"/"+GETMUAUTH;
    /*获取所有的订单*/
    public static final String GETALLPAYS_URL ="http://"+IP+":"+POST+"/"+URL+"/"+GETALLPAYS;
    /*获取获取用户缴费的订单项*/
    public static final String GETPAYITEM_URL ="http://"+IP+":"+POST+"/"+URL+"/"+GETPAYITEM;
    /*修改用户缴费订单*/
    public static final String UPDATEPAYITEM_URL="http://"+IP+":"+POST+"/"+URL+"/"+UPDATEPAYITEM;
    /*用户注册接口*/
    public static final String USERREGIST_URL="http://"+IP+":"+POST+"/"+URL+"/"+USERREGIST;
    /*忘记密码接口*/
    public static final String FORGET_URL="http://"+IP+":"+POST+"/"+URL+"/"+FORGET;
    /*通过手机获取用户*/
    public static final String GETUSERBYTEL_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERBYTEL;
    /*备用登陆接口*/
    public static final String LOGINACTION_URL="http://"+IP+":"+POST+"/"+URL+"/"+LOGINACTION2;
    /*按条件获取房屋*/
    public static final String GETHOUSEBYSORT_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETHOUSEBYSORT;
    /*获取用户所有说说的接口*/
    public static final String GETUSERALLSS_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERALLSS;
    /*获取当前用户所有的保修订单的接口*/
    public static final String GETUSERALLREPAIR_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERALLREPAIR;
    /*获取用户认证信息*/
    public static final String GETUSERAUTH_URL="http://"+IP+":"+POST+"/"+URL+"/"+GETUSERAUTH;
    /*用户信息更新*/
    public static final String USERUPDATE_URL="http://"+IP+":"+POST+"/"+URL+"/"+USERUPDATE;
}
