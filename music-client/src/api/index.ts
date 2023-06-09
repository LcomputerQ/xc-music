import { id } from "element-plus/lib/locale";
import { getBaseURL, get, post, deletes } from "./request";

const HttpManager = {
  // 获取图片信息
  attachImageUrl: (url) => url ? `${getBaseURL()}/${url}` : "https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png",
  // =======================> 用户 API
  // 登录
  signIn: (params) => post(`/serve/user/login/status`, params),
  // 注册
  SignUp: (params) => post(`/serve/user/add`, params),
  // 删除用户
  deleteUser: (id) => get(`/serve/user/delete?id=${id}`),
  // 更新用户信息
  updateUserMsg: (params) => post(`/serve/user/update`, params),
  updateUserPassword: (params) => post(`/serve/user/updatePassword`, params),
  //改变用户在线状态
  getState: (stateCode, uId) => post(`/serve/user/upCode?statusCode=${stateCode}&id=${uId}`),
  // 返回指定ID的用户
  getUserOfId: (id) => get(`/serve/user/detail?id=${id}`),
    // 返回指定name的用户
    getUserOfname: (username) => get(`/serve/user/uname?name=${username}`),
  // 更新用户头像
  uploadUrl: (userId) => `/serve/user/avatar/update?id=${userId}`,

  // =======================> 歌单 API
  // 获取全部歌单
  getSongList: () => get("/api/personalized?limit=15"),
  

  // =======================> 歌手 API
  // 返回所有歌手
  getAllSinger: () => get("/api/artist/list?cat=1001&limit=10"),
  // 通过性别对歌手分类
  // getSingerOfSex: (sex) => get(`singer/sex/detail?sex=${sex}`),
  //歌手界面的全部歌手返回获取
  getAllSingers: () => get("/api/artist/list?type=-1&area=-1&limit=100"),
  // 男歌手
  getSingerOfSex: () => get(`api/artist/list?type=1&area=-1&limit=50`),
  //女歌手
  getSingerOfSexwoman: () => get(`api/artist/list?type=2&area=-1&limit=50`),
  //组合歌手
  Groupsingeryes: () => get(`api/artist/list?type=3&area=-1&limit=50`),
  // =======================> 收藏 API
  // 返回的指定用户ID的收藏列表
  getCollectionOfUser: (userId) => post(`/serve/collections/all?uid=${userId}`),
  // 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
  setCollection: (userId, songId) => post(`/serve/collections/add?uid=${userId}&songid=${songId}&type=1`),
  //根据用户id和歌曲id删除收藏歌曲
  deleteCollection: (userId, songId) => post(`/serve/collections/del?uid=${userId}&&songid=${songId}`),


  // =======================> 评分 API
  // 提交评分
  setRank: (params) => post(`rankList/add`, params),
  // 获取指定歌单的评分
  getRankOfSongListId: (songListId) => get(`rankList?songListId=${songListId}`),
  // 获取指定用户的歌单评分
  getUserRank: (consumerId, songListId) => get(`/rankList/user?consumerId=${consumerId}&songListId=${songListId}`),

  // =======================> 评论 API
  // 添加评论
  setComment: (uid,songId,content) => post(`/serve/comment?uid=${uid}&songId=${songId}&content=${content}`),
  // 删除评论
  deleteComment: (commentId) => post(`/serve/comment/del?commentId=${commentId}`),
  // 点赞
  setSupport: (params) => post(`comment/like`, params),
  // 返回数据库评论
  getAllCot:(songId)=>get(`/serve/comment?songId=${songId}`),
  // 返回所有评论
  getAllComment: (type, id) => {
    let url = "";
    if (type === 1) {
      url = `comment/songList/detail?songListId=${id}`;
    } else if (type === 0) {
      url = `comment/song/detail?songId=${id}`;
    }
    return get(url);
  },

  // =======================> 歌曲 API
  // 播放指定歌曲路径
  getSongPlay: (id) => get(`/api/song/url?id=${id}`),
  // 返回指定歌曲ID的歌曲
  getSongOfId: (id) => get(`/api/song/detail?ids=${id}`),
  // 返回指定歌手ID的歌曲
  getSongOfSingerId: (id) => get(`/api/artist/top/song?id=${id}`),
  // 返回歌手详细
  getSingerDetailed: (id) => get(`/api/artist/desc?id=${id}`),
  // 获取歌曲图片
  getMusicImgUrl: (id) => get(`/api/song/detail?ids=${id}`),
  // 返回指定歌手名的歌曲
  getSongOfSingerName: (keywords) => get(`/api/search?keywords=${keywords}&limit=60`),
  // 下载音乐
  downloadMusic: (url) => get(url, { responseType: "blob" }),
  // 用户访问
  getjournalings: (url) => get(url),
  // 获取歌单图片
  getUrlsrc: (id) => get(`/api/playlist/detail?id=${id}`),
  //获取歌单音乐
  getSinginglistmusic: (id) => get(`/api/playlist/track/all?id=${id}&limit=10&offset=1`),
  //获取歌单
  getGoodpLaylist: (cat) => get(`/api/top/playlist/?limit=80&cat=${cat}`),
  // 获取歌词
  getLyric: (id) => get(`/api/lyric?id=${id}`),
  //歌单评论
  getcommenton: (id) => get(`/api/comment/playlist?id=${id}`),
  //获取歌曲评论
  getsongcommenton: (id) => get(`/api/comment/music?id=${id}&limit=30`),
  //获取收藏歌曲
  getCollectsongs: (id) => post(`/serve/collections/all?uid=${id}`),
  //搜索搜藏歌曲
  getsearchforCollectsongs: (userid, songid) => get(`http://localhost:8888/collections/selectById?uid=${userid}&songid=${songid}`),

  //MV api
  // 最新MV
  getNewMV: () => get(`/api/mv/first?limit=50`),
  // 推荐MV
  getrecommendMv: () => get(`/api/top/mv`),
  //获取mv视频连接
  getMvVideoConnection: (songid) => get(`/api/mv/url?id=${songid}`),
  //获取mv类别
  getGetallmvs: (songname) => get(`/api/mv/all?area=${songname}&limit=50`),
  //获取MV信息
  getMvDetail: (mvId) => get(`/api//mv/detail?mvid=${mvId}`),
  //获取相似mv
  getSimilarityMv: (mvId) => get(`/api/simi/mv?mvid=${mvId}`),
  //聊条API
  //获取朋友列表
  getfiendsall: (userid) => get(`/serve/friends/all?uid=${userid}`),
  //获取聊天数据
  getCatserve: (userid, firendid) => get(`/serve/send/all?uid=${userid}&frid=${firendid}`),
  //发送聊天信息
  getSendachatmessage: (userid, pyid, conts) => get(`/serve/send/add?uid=${userid}&frid=${pyid}&mycontent=${conts}`),
   //添加好友路径
   AddFriends:(myid,pyid) => get(`/serve/audit/add?myid=${myid}&pyid=${pyid}`),

   HaoYouXiaoXi:(myid) => get(`/serve/audit/all?myid=${myid}`),
   // 确定好友同意界面
   Confirmconsen:(myid,pyid,type) =>get(`/serve/audit/get?myid=${myid}&pyid=${pyid}&type=${type}`)
};

export { HttpManager };
