import { RouterName } from "./router-name";

export const enum NavName {
  Home = "首页",
  SongSheet = "歌单",
  Singer = "歌手",
  MV = "MV",
  Trends = "动态", 
  ChatWithFriends = "好友聊天",
  MyTrends = "我的动态",
  Personal = "个人主页",
  Setting = "设置",
  SignIn = "登录",
  SignUp = "注册",
  SignOut = "退出",
  shoppingcentre="商城"
}

// 左侧导航栏
export const HEADERNAVLIST = [
  {
    name: NavName.Home,
    path: RouterName.Home,
  },
  {
    name: NavName.SongSheet,
    path: RouterName.SongSheet,
  },
  {
    name: NavName.Singer,
    path: RouterName.Singer,
  },
  {
    //音乐MV
    name: NavName.MV,
    path: RouterName.MV,
  },
  // 公共动态
  {
    name: NavName.Trends,
    path: RouterName.Dynamic,
  },
  // 商城
  {
    name: NavName.shoppingcentre,
    // path: RouterName.,
  },
];

// 右侧导航栏
export const SIGNLIST = [
  {
    name: NavName.SignIn,
    path: RouterName.SignIn,
  },
  {
    name: NavName.SignUp,
    path: RouterName.SignUp,
  },
];

// 用户下拉菜单项
export const MENULIST = [
    // 好友聊天
  {
    name: NavName.ChatWithFriends,
    path: RouterName.Chat,
  },
  //我的动态
  {
    name: NavName.MyTrends,
    path: RouterName.Personal,
  },
  {
    name: NavName.Personal,
    path: RouterName.Personal,
  },
  {
    name: NavName.Setting,
    path: RouterName.Setting,
  },
  {
    name: NavName.SignOut,
    path: RouterName.SignOut,
  },
];
