<template>
  <!-- 顶部超大播放图 -->
  <div id="top">
    <video
      src="https://a.sinaimg.cn/mintra/pic/2112130543/weibo_login.mp4"
      style="width: 100%; height: 100%; object-fit: cover"
      autoplay
    ></video>
    <div style="position: absolute; top: 100px; left: 40%; color: aliceblue">
      XC-云社区
    </div>
    <!-- <img :src="topImg" alt="" /> -->
  </div>
  <!-- <div style="background-color: rgba(160, 159, 159, 0.1);"> -->
  <div
    class="common-layout play-list-container"
    id="cont"
    style="background-color: transparent"
  >
    <el-container style="background-color: white">
      <el-aside id="left-user">
        <!-- 菜单列表 -->
        <el-col :span="12" id="left-list" style="padding-left: 50px">
          <h2 class="mb-2" style="padding: 10px 10px">分类</h2>
          <el-menu default-active="1">
            <el-menu-item index="1" @click="toReco()">
              <el-icon>
                <BellFilled />
              </el-icon>
              <span>推荐</span>
            </el-menu-item>
            <el-menu-item index="2" @click="toImg()">
              <el-icon>
                <PictureFilled />
              </el-icon>
              <span>图文</span>
            </el-menu-item>
            <el-menu-item index="3" @click="toVideo()">
              <el-icon>
                <VideoCameraFilled />
              </el-icon>
              <span>视频</span>
            </el-menu-item>
            <el-menu-item index="4" @click="toFrie()">
              <el-icon>
                <Avatar />
              </el-icon>
              <span>好友</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-aside>
      <el-container style="background-color: transparent">
        <!-- 发布动态 -->
        <el-header id="publish-post">
          <h2>
            <el-icon style="padding-right: 10px"> <Promotion /> </el-icon>动态
          </h2>
          <div>
            <el-button round text @click="centerDialogfus()">
              <el-icon> <Edit /> </el-icon>发布图文
            </el-button>
            <!-- 发表图文 -->
            <el-dialog
              v-model="centerDialogVisible"
              title="发布图文动态"
              width="30%"
              center
              class="relPost"
            >
              <!-- 发布图文表单 -->
              <el-form
                :model="numberValidateForm"
                ref="numberValidateForm"
                class="demo-ruleForm"
              >
                <el-form-item prop="content">
                  <el-input
                    type="textarea"
                    id="conttd"
                    :autosize="{ minRows: 5, maxRows: 15 }"
                    placeholder="请输入发表内容"
                    v-model="myPost.content"
                    autocomplete="off"
                  ></el-input>
                  <div v-show="isupcont" style="color: red">请输入内容</div>
                </el-form-item>

                <el-form-item>
                  <el-select
                    v-model="myPost.topic"
                    placeholder="#你可以选择话题"
                  >
                    <el-option
                      v-for="(item, index) in rightList"
                      :key="index"
                      :label="`#${item}`"
                      :value="index"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <!-- 上传图片 -->
              </el-form>
              <template #footer>
                <span class="dialog-footer">
                  <form
                    :action="formUrl"
                    target="nm_iframe"
                    id="myfrom"
                    method="post"
                    enctype="multipart/form-data"
                    style="display: flex; flex-direction: column"
                  >
                    <input type="file" name="file" id="myfile" />
                    <div style="padding-top: 10px">
                      <el-button @click="centerDialogVisible = false"
                        >取消</el-button
                      >
                      <button type="submit" @click="relPost" class="fb">
                        发布
                      </button>
                    </div>
                  </form>
                  <iframe
                    id="id_iframe"
                    name="nm_iframe"
                    style="display: none"
                  ></iframe>
                </span>
              </template>
            </el-dialog>
          </div>
        </el-header>
        <!-- 动态列表 -->

        <el-main id="publish-list">
          <PostList></PostList>
          <div class="posts">
            <!-- 单个列表 -->
            <div class="post" v-for="(post, index) in viewList" :key="index">
              <!-- 头部信息 -->
              <div class="post-head">
                <el-avatar :size="50" :src="'/serve' + post.uimg" />
                <div class="userinfo">
                  <p>{{ post.username }}</p>
                  <p>{{ new Date(post.creatTime).toLocaleString() }}</p>
                </div>
              </div>
              <div class="post-cont">
                <div>
                  {{ post.content }}
                </div>
                <span>#{{ rightList[post.topicid] }}</span>

                <!-- 图片显示 -->
                <div>
                  <el-row>
                    <el-col :span="16">
                      <el-row>
                        <el-col :span="8" v-if="post.type == 'img'">
                          <el-image
                            style="
                              width: 250px;
                              height: 150px;
                              border-radius: 10px;
                            "
                            :src="'/serve' + post.contentUrl"
                            :fit="fit"
                            :preview-src-list="['/serve' + post.contentUrl]"
                          >
                          </el-image>
                        </el-col>
                        <el-col
                          :span="12"
                          id="videoo"
                          v-if="post.type == 'video'"
                        >
                          <video
                            width="200"
                            height="120"
                            :src="'/serve' + post.contentUrl"
                            controls
                          >
                            <!-- <source src="http://vodkgeyttp8.vod.126.net/cloudmusic/b549/core/ad55/54d96cea3ae893c714055591cbbaf812.mp4?wsSecret=bd47bb12d1cc5866047d84d0ce5dc06e&wsTime=1679830180" type="video/mp4"> -->
                            您的浏览器不支持 video 标签。
                          </video>
                          <!-- <iframe src="//player.bilibili.com/player.html?aid=482617016&bvid=BV12T411e7Yo&cid=1028262266&page=1" scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"> </iframe> -->
                        </el-col>
                      </el-row>
                    </el-col>
                  </el-row>
                </div>
              </div>

              <div class="demo-collapse">
                <el-collapse accordion style="background-color: transparent">
                  <el-collapse-item
                    name="1"
                    style="background-color: transparent"
                  >
                    <template
                      #title
                      style="background-color: rgb(57, 130, 213, 0.3)"
                    >
                      <!-- 帖子底部功能 -->
                      <div class="post-foot">
                        <div>
                          <el-icon
                            v-if="!userList.isLike"
                            @click="UpLike(index)"
                          >
                            <Pointer />
                          </el-icon>
                          <el-icon v-else @click="UpLike(index)">
                            <Pointer /> </el-icon
                          ><span>点赞</span>
                        </div>
                        <div>
                          <el-icon> <ChatDotSquare /> </el-icon>评论
                          {{
                            userArr.filter((data) => {
                              return data.songId == post.id;
                            }).length
                          }}
                        </div>
                      </div>
                    </template>
                    <div id="conts">
                      <el-row id="inputss">
                        <el-input
                          type="textarea"
                          :rows="2"
                          placeholder="请输入内容"
                          v-model="contents"
                        >
                        </el-input>
                      </el-row>
                      <el-row style="height: 30px">
                        <el-col :span="18"> </el-col>
                        <el-col :span="2">
                          <el-button
                            type="primary"
                            circle
                            @click="stEmolist"
                            style="
                              background-color: rgba(240, 248, 255, 0);
                              border: 0;
                              margin-left: 15px;
                            "
                          >
                            <svg
                              t="1683378159299"
                              class="icon"
                              viewBox="0 0 1024 1024"
                              version="1.1"
                              xmlns="http://www.w3.or
                            g/2000/svg"
                              p-id="3540"
                              width="32"
                              height="32"
                            >
                              <path
                                d="M512 512m-511.982387 0a511.982387 511.982387 0 1 0 1023.964774 0 511.982387 511.982387 0 1 0-1023.964774 0Z"
                                fill="#F9C228"
                                p-id="3541"
                              ></path>
                              <path
                                d="M917.619539 199.639491C761.536154-3.082215 483.485105-56.554248 265.951152 62.930031a531.620502 531.620502 0 0 0-166.598142 387.161472c0 294.430822 238.686756 533.099966 533.099966 533.099966a535.424837 535.424837 0 0 0 101.219951-9.598899 514.289646 514.289646 0 0 0 90.705195-55.973031C1048.393533 745.138768 1090.223598 423.672515 917.619539 199.639491z"
                                fill="#FCDC22"
                                p-id="3542"
                              ></path>
                              <path
                                d="M972.042656 550.272308c-111.329618 114.904988-252.600757 198.670795-415.482628 260.033299 0 0 55.867355 156.664603 207.829377 101.53698 250.628139-90.846096 207.653251-361.570279 207.653251-361.570279z"
                                fill="#FC9B88"
                                p-id="3543"
                              ></path>
                              <path
                                d="M522.690884 570.08655a80.119986 64.145304 90 1 0 128.290609 0 80.119986 64.145304 90 1 0-128.290609 0Z"
                                fill="#282828"
                                p-id="3544"
                              ></path>
                              <path
                                d="M860.519298 429.449467a80.119986 54.881046 90 1 0 109.762092 0 80.119986 54.881046 90 1 0-109.762092 0Z"
                                fill="#282828"
                                p-id="3545"
                              ></path>
                              <path
                                d="M953.813553 724.584795a261.319023 261.319023 0 0 0-116.014585-59.988717C754.121225 723.616099 659.699759 771.434469 556.560028 810.305607c0 0 55.867355 156.664603 207.829377 101.53698 110.959752-40.209701 164.361335-115.64472 189.424148-187.257792z"
                                fill="#EA0F1A"
                                p-id="3546"
                              ></path></svg
                          ></el-button>
                        </el-col>
                        <el-col :span="4" style="padding-bottom: 10px">
                          <el-button
                            style="height: 40px; width: 80%"
                            type="primary"
                            @click="setComments(post.id, user.userId, contents)"
                            >发布</el-button
                          ></el-col
                        >
                      </el-row>
                      <el-row>
                        <el-col style="margin-top: 15px">
                          <!-- 表情包 -->
                          <div class="emojiss" v-show="isEmoShow">
                            <ul
                              style="
                                border-radius: 5px;
                                display: flex;
                                justify-content: left;
                                flex-wrap: wrap;
                                background-color: #fafafa;
                              "
                            >
                              <li
                                v-for="(em, index) in emojis"
                                :key="index"
                                @click="apEmo(em)"
                                style="cursor: pointer; font-size: 30px"
                              >
                                {{ em }}
                              </li>
                            </ul>
                          </div>
                        </el-col>
                      </el-row>
                      <el-row
                        id="conT"
                        v-for="coment in userArr.filter((data) => {
                          return data.songId == post.id;
                        })"
                        v-if="isReload"
                        :key="coment.commentId"
                      >
                        <!-- 间距 -->
                        <el-col :span="1"></el-col>
                        <!-- 头像 -->
                        <el-col :span="2" style="margin-top: 30px">
                          <el-avatar
                            :size="70"
                            :src="
                              `/serve/` +
                              usernameAnduserImg.filter((data) => {
                                return data.id == coment.uid;
                              })[0].avator
                            "
                          />
                        </el-col>
                        <!-- 名字 -->
                        <el-col :span="5" style="margin-top: 30px">
                          <h2>
                            {{
                              usernameAnduserImg.filter((data) => {
                                return data.id == coment.uid;
                              })[0].username
                            }}
                          </h2>
                          <p style="line-height: 18px; margin-top: 20px">
                            {{ coment.createtime }}
                          </p>
                        </el-col>
                        <!-- 内容 -->
                        <el-col :span="14" style="margin-top: 30px">
                          <p style="font-size: 20px">: {{ coment.content }}</p>
                        </el-col>
                        <!-- 点赞和删除 -->
                        <el-col :span="2" style="margin-top: 30px">
                          <div style="position: relative; bottom: -40px">
                            <el-icon
                              size="20px"
                              style="
                                margin-top: 30px;
                                position: relative;
                                top: 3px;
                              "
                            >
                              <Pointer />
                            </el-icon>
                            <b style="font-size: 18px"> | 回复</b>
                          </div>
                          <span
                            v-if="coment.uid == user.userId"
                            @click="delComent(coment)"
                            style="
                              color: red;
                              cursor: pointer;
                              position: relative;
                              right: -35px;
                              top: -100px;
                              font-size: 16px;
                            "
                            >x</span
                          >
                        </el-col>
                      </el-row>
                    </div>
                  </el-collapse-item>
                </el-collapse>
              </div>
            </div>
          </div>
        </el-main>
      </el-container>
      <el-aside width="200px" id="right-user">
        <!-- 菜单列表 -->
        <el-col :span="12">
          <h3 class="mb-2" style="padding: 10px 5px">#热门话题</h3>
          <el-menu class="el-menu-vertical-demo" id="rightlist">
            <el-menu-item
              :index="index"
              v-for="(right, index) in rightList"
              :key="index"
            >
              <span>{{ index + 1 }}</span>
              <p>{{ right }}</p>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-aside>
    </el-container>
  </div>
  <!-- </div> -->
</template>

<script>
import WeixinEmojis from "vue-weixin-emojis";
import "vue-weixin-emojis/dist/vue-weixin-emojis.css";
import { HttpManager } from "@/api";
import "@/assets/icons/index.js";
import { ElMessageBox } from "element-plus";
import {
  ChatDotSquare,
  Avatar,
  Star,
  Edit,
  VideoCameraFilled,
  PictureFilled,
  BellFilled,
  Sunny,
} from "@element-plus/icons-vue";
import {
  StarFilled,
  Pointer,
  Menu,
  Promotion,
  CaretLeft,
  Plus,
} from "@element-plus/icons-vue";
import axios from "axios";
import { getCurrentInstance } from "vue";
import mixin from "@/mixins/mixin";
export default {
  data() {
    return {
      isEmoShow: false,
      // 表情包
      emojis: [
        "😀",
        "😄",
        "😅",
        "🤣",
        "😂",
        "😉",
        "😊",
        "😍",
        "😘",
        "😜",
        "😝",
        "😏",
        "😒",
        "🙄",
        "😔",
        "😴",
        "😷",
        "🤮",
        "🥵",
        "😎",
        "😮",
        "😰",
        "😭",
        "😱",
        "😩",
        "😡",
        "💀",
        "👽",
        "🤓",
        "🥳",
        "😺",
        "😹",
        "😻",
        "🤚",
        "💩",
        "👍",
        "👎",
        "👏",
        "🙏",
        "💪",
      ],
      value: "",
      msgs: [],
      isReload: true,
      contents: "",
      // comtList: this.CommentsList(70),
      formUrl: "/serve/file/add?",
      // 用户信息
      user: getCurrentInstance().proxy.$store.state.user,
      // 上传帖子信息
      myPost: {
        uid: getCurrentInstance().proxy.$store.state.user.userId,
        avter: getCurrentInstance().proxy.$store.state.user.userPic,
        uname: getCurrentInstance().proxy.$store.state.user.username,
        content: "",
        topic: "",
        postype: "",
      },

      // 判断提示
      isPro: false,
      // rule:[{ required: true, message: '内容不能为空' }],
      // 发表图文表单
      numberValidateForm: {
        content: "",
      },
      // 空值提示框
      isupcont: false,
      dialogImageUrl: "",
      dialogVisible: false,
      // 上传文件
      fileList: [
        // {
        //     name:null,
        //     url:null
        // }
      ],
      // 是否上传文件
      flag: false,

      // 图文弹窗机关
      centerDialogVisible: false,
      topImg:
        "https://ts1.cn.mm.bing.net/th/id/R-C.74ad086892eeda549cb3946e681f2d9b?rik=D4wiqL4a5XQscA&riu=http%3a%2f%2fi0.hdslb.com%2fbfs%2farticle%2fa22a162bca94cdf7438ea556dfa021b181bf3873.jpg&ehk=hs8df19fFE7Vy8LX3nQoJ%2fSBt1ePFXZeKfdX4Lev0d4%3d&risl=&pid=ImgRaw&r=0",
      // 话题列表
      rightList: [
        "周杰伦",
        "铃芽户缔",
        "周深",
        "春天的第一条动态",
        "乌梅子酱",
        "春天的第一张照片",
        "can i be your dog",
        "孔乙己文学",
        "你别太荒谬",
        "到此为止再爱就不礼貌了",
        "crush",
        "摆烂式社交",
        "我没惹你们任何人",
      ],
      // 用户列表
      userList: [],
      // 总帖子列表
      postList: [],
      // 图片路径列表
      urlList: [],
      // 显示帖子列表
      viewList: [],
      // 好友帖子列表
      firenList: [],
      // 图文帖子列表
      imgList: [],
      // 视频帖子列表
      videoList: [],
      // 详情列表
      postMess: [""],
      // s视频URL
      videoUrl: [],
      userArr: [],
      usernameAnduserImg: [],
      pyNewArray: [],
    };
  },
  created() {
    this.inIT();
    // 获取帖子列表
    axios({
      method: "GET",
      url: "/serve/dynamic/all",
    }).then((res) => {
      this.postList = res.data;
      // 过滤地址
      for (let i = 0; i < this.postList.length; i++) {
        this.urlList.push(res.data[i].contentUrl);
        // 过滤视频和图文
        if (res.data[i].type == "img") {
          this.imgList.push(res.data[i]);
        }
        if (res.data[i].type == "video") {
          this.videoList.push(res.data[i]);
          this.videoUrl.push(res.data[i].contentUrl.split("!!"));
        }
      }
      this.viewList = this.postList;
      console.log('qqq');
      console.log(res.data);
      this.pyNewArray = this.postList;
    });
  },
  components: {
    Pointer,
    Plus,
    ElMessageBox,
    CaretLeft,
    Promotion,
    Menu,
    StarFilled,
    ChatDotSquare,
    BellFilled,
    PictureFilled,
    Avatar,
    Star,
    VideoCameraFilled,
    Edit,
    Sunny,
    WeixinEmojis,
  },

  methods: {
    apEmo(em) {
      this.contents = this.contents + em;
    },
    stEmolist() {
      this.isEmoShow = !this.isEmoShow;
    },
    inIT() {
      // 用户名字和头像
      axios({
        method: "GET",
        url: "/serve/user",
      }).then((res) => {
        this.usernameAnduserImg = res.data.data;
      });
      // 用户评论
      axios({
        method: "GET",
        url: "/serve/comment/alls",
      }).then((res) => {
        this.userArr = res.data;
      });
    },
    // s刷新
    rekoadPart() {
      this.isReload = false;
      this.$nextTick(() => {
        this.isReload = true;
      });
    },
    setComments(id, uid, content) {
      if (sessionStorage.getItem("isLogin") != "true") {
        this.$message({
          message: "请先登录",
          type: "warning",
        });
        return;
      }
      if (content != "" && sessionStorage.getItem("isLogin") == "true") {
        HttpManager.setComment(uid, id, content).then((res) => {
          this.inIT();
          console.log(res);
        });
        this.contents = "";
        this.rekoadPart();
      } else {
        this.$message({
          message: "请输入内容",
          type: "warning",
        });
      }
    },
    // 改变发布图文机关
    centerDialogfus() {
      if (sessionStorage.getItem("isLogin") != "true") {
        this.$router.push({
          path: "/sign-in",
        });
      } else {
        this.centerDialogVisible = true;
      }
    },
    // 收藏和点赞
    UpCollect(id) {
      this.userList.isCollect = !this.userList.isCollect;
    },
    UpLike(id) {
      this.userList.isLike = !this.userList.isLike;
    },
    // 发布帖子
    relPost(event) {
      var filename = document.getElementById("myfile").value.split(".")[1];
      if (this.myPost.content.trim() != "" && this.myPost.content != null) {
        // document.getElementById('conttd').className = 'one1'
        // 判断发送类型
        if (filename == "mp4") {
          this.formUrl +=
            "id=" +
            this.myPost.uid +
            "&uimg=" +
            this.myPost.avter +
            "&content=" +
            this.myPost.content +
            "&topicid=" +
            this.myPost.topic +
            "&type=video";
        } else {
          this.formUrl +=
            "id=" +
            this.myPost.uid +
            "&uimg=" +
            this.myPost.avter +
            "&content=" +
            this.myPost.content +
            "&topicid=" +
            this.myPost.topic +
            "&type=img";
        }
        this.centerDialogVisible = false;
        axios({
          method: "GET",
          url: "/serve/dynamic/all",
        }).then((res) => {
          this.postList = res.data;
          // 过滤地址
          for (let i = 0; i < this.postList.length; i++) {
            console.log(res.data[i].contentUrl.split("!!"));
            this.urlList.push(res.data[i].contentUrl.split("!!"));
            // 过滤视频和图文
            if (res.data[i].type == "img") {
              this.imgList.push(res.data[i]);
            }
            if (res.data[i].type == "video") {
              this.videoList.push(res.data[i]);
              this.videoUrl.push(res.data[i].contentUrl.split("!!"));
            }
          }
          this.viewList = this.postList;
          this.pyNewArray = this.postList;
        });
        ElNotification({
          title: "Title",
        });
      } else {
        this.centerDialogVisible = false;
        event.preventDefault();
      }
      // this.rekoadPart();
      this.inIT();
    },
    // 删除评论
    delComent(id) {
      HttpManager.deleteComment(id.commentId).then((res) => {
        if (res == "success") {
          this.inIT();
          this.rekoadPart();
        }
      });
    },
    // 切回推荐
    toReco() {
      this.viewList = this.postList;
    },
    // 切图文
    toImg() {
      this.viewList = this.imgList;
    },
    // 切视频
    toVideo() {
      this.viewList = this.videoList;
    },
    toFrie() {
      if (sessionStorage.getItem("isLogin") != "true") {
        this.viewList = [];
      } else {
        let pyarr = [];
        HttpManager.getfiendsall(this.user.userId).then((res) => {
          res.forEach((element) => {
            pyarr.push(element.prid);
          });
          this.viewList = this.pyNewArray.filter((obj) => {
            return pyarr.some((ele) => ele === obj.uid);
          });
        });
      }
    },
  },
};
</script>
<style scoped>
.el-collapse {
  --el-collapse-content-bg-color: none;
  --el-collapse-header-bg-color: #c9d7e694;
  border-radius: 3px;
}

.el-main {
  --el-main-padding: 0;
}
</style>
<style lang="less">
.el-main {
  --el-main-padding: 0;
}

.fb {
  display: inline-block;
  height: 32px;
  width: 56px;
  border: 1px solid rgba(160, 159, 159, 0.625);
  color: #5c5b5b;
  border-radius: 4.5px;
  background-color: white;
}

.fb:hover {
  cursor: pointer;
  color: #409eff;
  background-color: #ecf5ff;
  border: 1px solid #d9ecff;
}

.one1 {
  border: none;
}

.two1 {
  border: 1px red solid;
}

// 顶部大图
#top {
  width: 100%;
  height: 200px;
  font-size: 50px;
  font-weight: bolder;
  // display: flex;
  // justify-content: center;
  // align-items: center;
  // background-size: 100% auto;
  // background-position: center;
  // background-image: url('https://ts1.cn.mm.bing.net/th/id/R-C.74ad086892eeda549cb3946e681f2d9b?rik=D4wiqL4a5XQscA&riu=http%3a%2f%2fi0.hdslb.com%2fbfs%2farticle%2fa22a162bca94cdf7438ea556dfa021b181bf3873.jpg&ehk=hs8df19fFE7Vy8LX3nQoJ%2fSBt1ePFXZeKfdX4Lev0d4%3d&risl=&pid=ImgRaw&r=0');
  // img {
  //     width: 100%;
  //     height: 200px;
  //     object-fit: cover;
  // }
}

#cont {
  border-top: 1px rgb(54, 158, 149) solid;
  margin-top: 30px;
}

.el-menu {
  background-color: transparent;
}

// 左列表
#left-user {
  background-color: transparent;
  height: 800px;
  width: 240px;
  overflow: hidden;
  text-align: center;

  .el-menu-item {
    float: left;
    height: 80px;
    overflow: hidden;
    width: 200px;
    font-size: 20px;
    font-weight: 600;
  }
}

// 右列表
#right-user {
  overflow: hidden;

  #rightlist {
    .el-menu-item {
      height: 40px;
      width: 200px;
      text-align: center;
      font-size: 18px;
      font-weight: 300;
    }

    .el-menu-item:hover {
      color: #999999;
    }

    span {
      color: black;
      font-size: 18px;
      font-weight: 600;
      padding-right: 10px;
    }
  }
}

// 发布动态部分
#publish-post {
  padding-bottom: 0;
  margin: 0 20px;
  // background-color: bisque;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px rgb(0, 149, 255) solid;

  .relPost {
    padding: 10px 10px;

    .demo-ruleForm {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: left;
      // .upload-demo{
      //     display: block;
      // }
    }
  }
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

//  帖子列表
#publish-list {
  .post > div {
    padding: 10px;
  }

  .post:hover {
    background: rgba(191, 184, 184, 0.261);
    box-shadow: rgba(88, 88, 236, 0.25) 0px 6px 12px -2px,
      rgba(234, 14, 14, 0.3) 0px 3px 7px -3px;
  }

  .post {
    // border-bottom: 1px #999999 solid;
    border-radius: 10px;
    background: #b7b4b436;
    margin-top: 10px;
    padding: 5px 0;

    // 帖子用户信息
    .post-head {
      display: flex;
      justify-items: center;
      align-items: center;

      .userinfo {
        margin-top: 10px;
        padding-left: 10px;

        p:first-child {
          padding-bottom: 5px;
          font-size: 17px;
          color: black;
        }

        p:last-child {
          font-size: 14px;
          color: #999999;
        }
      }
    }

    // 帖子内容
    .post-cont {
      #videoo > video {
        width: 100%;
      }

      span {
        padding: 10px 5px;
        color: rgb(34, 115, 255);
      }

      span:hover {
        color: #999999;
      }

      .el-row {
        padding: 5px 0;
      }

      .el-col {
        padding-right: 5px;
      }

      .music-mes {
        height: 80px;
        padding: 10px 10px;
        background-color: #fbfde2;
        display: flex;
        align-items: center;

        .mic-aver {
          padding: 0 0;
          width: 60px;
          height: 60px;
          margin: 0px;
          background-size: cover;

          .el-icon {
            padding: 10px;
          }
        }

        .Music-info {
          padding-left: 10px;
        }
      }
    }

    // 帖子底部
    .post-foot {
      font-size: 18px;
      padding: 10px 0;
      height: 20px;
      display: flex;
    }

    .post-foot > div {
      display: flex;
      justify-content: center;
      align-items: center;

      .el-icon {
        padding-right: 8px;
        padding-left: 10px;
      }
    }

    #inputss {
      margin: 10px;
    }

    // 评论
    #conts {
      #conT {
        margin-top: 15px;
        padding: 15px 0;
        border-radius: 15px;
        height: 150px;
        background: #fcfcfc;

        p {
          font-size: 18px;
        }
      }

      #conT:hover {
        box-shadow: rgba(94, 94, 239, 0.25) 0px 6px 12px -2px,
          rgba(215, 111, 111, 0.3) 0px 3px 7px -3px;
      }
    }
  }
}
</style>