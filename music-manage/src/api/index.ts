import { get, post, deletes, getBaseURL } from './request'

const HttpManager = {

  // 获取访问量
  getjournaling:(url) => get(`/serve`+url),
  // 获取图片信息
  attachImageUrl: (url) => `${getBaseURL()}/${url}`,
  // =======================> 管理员 API
  // 是否登录成功
  getLoginStatus: (params) => post(`/serve/admin/login/status`, params),

  // =======================> 用户 API
  // 返回所有用户
  getAllUser: () => get(`/serve/user`),
  //返回在线用户数
  getOnline:()=>get(`/serve/user/status`),
  // 返回指定ID的用户
  getUserOfId: (id) => get(`/serve/user/detail?id=${id}`),
  // 添加用户
  setUser: (params) => post(`/serve/user/add`, params),
  // 更新用户信息
  updateUserMsg: (params) => post(`/serve/user/update`, params),
  // 删除用户
  deleteUser: (id) => get(`/serve/user/delete?id=${id}`),

  // =======================> 收藏列表 API
  // 返回的指定用户ID收藏列表
  getCollectionOfUser: (userId) => post(`/serve/collections/all?uid=${userId}`),
  //返回所有用户地址
  getAddressAll:()=> get(`/serve/user/regionAll`),
  //返回所有用户性别总数
  getSexAll:()=> get(`/serve/user/sexAll`),
  // 删除收藏的歌曲
  deleteCollection: (userId, songId) => post(`/serve/collections/del?uid=${userId}&&songid=${songId}`),

  // =======================> 评论列表 API
  // 获得指定歌曲ID的评论列表
  getCommentOfSongId: (songId) => get(`comment/song/detail?songId=${songId}`),
  // 获得指定歌单ID的评论列表
  getCommentOfSongListId: (songListId) => get(`/serve/comment/songList/detail?songListId=${songListId}`),
  // 删除评论
  deleteComment: (id) => get(`/serve/comment/delete?id=${id}`),

  // =======================> 歌曲 API

  // 返回的指定用户ID收藏列表
  getSongOfId: (id) => get(`/api/song/detail?ids=${id}`),

  // =======================> 歌单 API
  // 添加歌单
  setSongList: (params) => post(`songList/add`, params),
  // 获取全部歌单
  getSongList: () => get(`songList`),
  // 更新歌单信息
  updateSongListMsg: (params) => post(`songList/update`, params),
  // 删除歌单
  deleteSongList: (id) => get(`songList/delete?id=${id}`),

  // =======================> 歌单歌曲 API
  // 给歌单添加歌曲
  setListSong: (params) => post(`listSong/add`, params),
  // 返回歌单里指定歌单ID的歌曲
  getListSongOfSongId: (songListId) => get(`listSong/detail?songListId=${songListId}`),
  // 删除歌单里的歌曲
  deleteListSong: (songId) => get(`listSong/delete?songId=${songId}`),


}

export { HttpManager }
