<template>
  <video id="v1" autoplay loop muted>
    <source
      src="https://video.699pic.com/videos/55/06/24/a_vYJbXmjvQQbt1555550624.mp4"
      type="video/mp4"
    />
  </video>
  <div
    style="
      padding: 40px 60px;
      background-position: center;
      background-size: 100% auto;
    "
  >
    <div
      class="common-layout play-list-container"
      id="cont"
      style="
        border-radius: 10px;
        background-color: transparent;
        overflow: hidden;
      "
    >
      <div style="border-radius: 10px">
        <el-container style="background-color: transparent">
          <!-- 右列表 -->
          <el-aside width="260px" id="left-list">
            <div class="userList">
              <el-avatar :size="100" :src="`/serve/${user.userPic}`" />
              <h1 style="color: white; font-size: 20px">{{ user.username }}</h1>
            </div>
            <el-menu>
              <el-menu-item index="2" @click="chat()">
                <el-icon>
                  <Comment />
                </el-icon>
                <span>聊天</span>
              </el-menu-item>
              <el-menu-item index="2" @click="changingoverhs()">
                <el-icon>
                  <User />
                </el-icon>
                <span>好友</span>
              </el-menu-item>
              <el-menu-item index="2" @click="kongjian()">
                <el-icon>
                  <Promotion />
                </el-icon>
                <span>空间</span>
              </el-menu-item>
              <el-menu-item index="2">
                <el-icon>
                  <BellFilled />
                </el-icon>
                <span>推荐</span>
              </el-menu-item>
              <el-menu-item index="2">
                <el-icon>
                  <Place />
                </el-icon>
                <span>小游戏</span>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <!-- 聊天界面 -->
          <el-main v-if="changingover == '聊天'" id="contt">
            <el-container style="border-radius;:20px;">
              <el-header class="head">
                <el-row style="height: 100%">
                  <el-col :span="4">
                    <div>
                      <h1>好友列表</h1>
                      <el-input
                        v-model="input"
                        placeholder="请输入好友名称"
                        style="width: 240px"
                        @keyup="added"

                      />
                    </div>
                  </el-col>
                  <el-col :span="6"></el-col>
                  <el-col
                    :span="10"
                    style="
                      height: 100%;
                      display: flex;
                      justify-content: center;
                      align-items: center;
                    "
                  >
                    <div style="text-align: center; font-size: 28px">
                      {{ fanusername }}
                    </div>
                  </el-col>
                </el-row>
              </el-header>
              <!-- 聊天界面 -->
              <el-container>
                <el-aside width="28%" class="head">
                  <div
                    v-for="(item, index) in firendList"
                    :key="index"
                    class="cell"
                    @click="SwitchFriends(item, index)"
                    style="cursor: pointer"
                  >
                    <div>
                      <el-avatar
                        :src="`/serve/${item.pyurl}`"
                        style="margin-left: 20px"
                      />
                      <span
                        style="position: relative; left: 10px; top: -10px"
                        >{{ item.pyname }}</span
                      >
                    </div>
                  </div>
                </el-aside>
                <el-container>
                  <!-- 聊天内容 -->
                  <el-main
                    style="
                      background-color: rgb(0, 115, 255, 0.2);
                      border-top-left-radius: 10px;
                      border-top-right-radius: 10px;
                      height: 400px;
                    "
                  >
                    <div style="width: 100%; height: 100%; position: relative">
                      <tbody>
                        <tr v-for="(obj, index) in firendsorr" :key="index">
                          <td
                            style="margin-right: 20px"
                            v-if="obj.mycontent == null ? true : false"
                          >
                            <el-avatar
                              :src="`/serve/${chatUrl}`"
                              style="
                                margin-left: 10px;
                                margin-top: 5px;
                                cursor: pointer;
                              "
                            />
                          </td>
                          <td
                            v-if="obj.mycontent == null ? false : true"
                            style="
                              margin-left: 20px;
                              float: right;
                              cursor: pointer;
                            "
                          >
                            <el-avatar :src="`/serve/${user.userPic}`" />
                          </td>
                          <td
                            style="
                              background: white;
                              padding: 10px;
                              border-radius: 5px;
                              cursor: pointer;
                            "
                            :class="obj.mycontent == null ? '' : 'frcontents'"
                          >
                            {{ obj.frcontent || obj.mycontent }}
                          </td>
                        </tr>
                      </tbody>
                      <!-- 表情列表 -->
                      <div class="emojiss" v-show="isEmoShow">
                        <ul style="border-radius: 5px">
                          <li
                            v-for="(em, index) in emojis"
                            :key="index"
                            @click="apEmo(em)"
                            style="cursor: pointer"
                          >
                            {{ em }}
                          </li>
                        </ul>
                      </div>
                    </div>
                  </el-main>
                  <el-footer
                    height="100px"
                    style="
                      background-color: rgb(247, 247, 247);
                      padding: 10px 0;
                    "
                  >
                    <div>
                      <el-row>
                        <el-col :span="2"></el-col>
                        <el-col :span="16">
                          <el-input v-model="form.desc" type="textarea" />
                        </el-col>
                        <el-col
                          :span="4"
                          style="
                            display: flex;
                            justify-content: center;
                            align-items: center;
                          "
                        >
                          <el-button
                            type="primary"
                            circle
                            @click="stEmolist"
                            style="
                              background-color: aliceblue;
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
                          <el-button type="primary" @click="onSubmit"
                            >发送</el-button
                          >
                        </el-col>
                      </el-row>
                    </div>
                  </el-footer>
                </el-container>
              </el-container>
            </el-container>
          </el-main>

          <!-- 好友搜索界面 -->
          <el-main
            v-if="changingover == '好友'"
            id="contt"
            style="height: 580px; background-color: rgb(246, 246, 246, 0.6)"
          >
            <el-row style="height: 100%">
              <!-- 左边的好友搜索 -->
              <el-col :span="12">
                <el-row style="padding: 30px 0">
                  <el-col :span="5"></el-col>
                  <el-col :span="12">
                    <el-input
                      v-model="input1"
                      size="large"
                      placeholder="请输入需要搜索的好友"
                      :prefix-icon="Search"
                    />
                  </el-col>
                  <el-button
                    color="#3CA1A3"
                    :dark="isDark"
                    @click="Searchforfriends"
                    plain
                    style="height: 40px"
                    >搜索</el-button
                  >
                </el-row>
                <el-row
                  v-for="(value, index) in Searchinformationyes"
                  :key="index"
                >
                  <el-col :span="3"></el-col>
                  <el-col :span="18">
                    <!-- 搜索的好友名片 -->
                    <el-card style="width: 100%; padding: 0">
                      <div
                        style="
                          display: flex;
                          align-items: center;
                          flex-direction: row;
                        "
                      >
                        <el-avatar
                          :size="60"
                          :src="`/serve/${value.avator}`"
                          style="margin-left: 10px"
                        />

                        <div
                          style="
                            padding-left: 10px;
                            font-size: 18px;
                            text-indent: 2px;
                          "
                        >
                          {{ value.username }}
                        </div>
                        <div style="padding-left: 120px">
                          <el-button
                            :dark="isDark"
                            @click="
                              firendList.filter((res) => {
                                return res.pyname == value.username;
                              }).length == 0
                                ? add(value)
                                : open2()
                            "
                            plain
                            >{{
                              firendList.filter((res) => {
                                return res.pyname == value.username;
                              }).length != 0
                                ? "已成为好友"
                                : "添加"
                            }}</el-button
                          >
                        </div>
                      </div>
                    </el-card>
                  </el-col>
                </el-row>
              </el-col>
              <!-- 右边的好友验证 -->
              <el-col :span="12" style="padding: 0 10px" id="fridenzz">
                <el-row
                  style="padding: 20px 0; border-bottom: #666666 2px solid"
                >
                  <el-col :span="2"></el-col>
                  <div
                    style="
                      font-size: 28px;
                      font-weight: 600;
                      color: rgb(10, 10, 10);
                    "
                  >
                    好友申请
                  </div>
                </el-row>
                <el-row>
                  <el-col :span="2"></el-col>
                  <el-col :span="20">
                    <el-card
                      v-for="(value, index) in FriendAddMessage"
                      :key="index"
                    >
                      <el-row>
                        <el-col :span="5">
                          <el-avatar
                            :size="60"
                            :src="`/serve/${value.avator}`"
                            style="margin-left: 20px"
                          />
                        </el-col>
                        <el-col :span="11">
                          <div
                            style="
                              display: flex;
                              flex-direction: column;
                              justify-content: space-evenly;
                              height: 100%;
                              padding-left: 10px;
                            "
                          >
                            <div
                              style="
                                padding-left: 10px;
                                font-size: 18px;
                                text-indent: 2px;
                              "
                            >
                              {{ value.username }}
                            </div>
                            <p style="padding-left: 10px; font-size: 10px">
                              3月29日 12:12
                            </p>
                          </div>
                        </el-col>
                        <el-col :span="8">
                          <div
                            style="
                              display: flex;
                              align-items: center;
                              justify-content: center;
                              height: 100%;
                            "
                          >
                            <el-button
                              color="#626aef"
                              :dark="isDark"
                              plain
                              @click="agree(value)"
                              >同意</el-button
                            >
                            <el-button
                              color="pink"
                              :dark="isDark"
                              plain
                              @click="refuse(value)"
                              >拒绝</el-button
                            >
                          </div>
                        </el-col>
                      </el-row>
                    </el-card>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-main>
          <!-- 空间 -->
          <el-main
            v-if="changingover == '空间'"
            style="height: 580px; color: white"
            id="kongjiana"
          >
            <el-row style="height: 100%">
              <el-row
                style="
                  height: 12%;
                  width: 100%;
                  margin-bottom: 0;
                  padding-left: 20px;
                  border-bottom: 2px #999999 solid;
                  display: flex;
                  align-items: center;
                "
              >
                <div style="font-size: 30px; color: white">我的动态</div>
              </el-row>
              <el-row style="height: 88%; width: 100%">
                <!--  -->
                <el-col :span="18" style="padding-top: 20px; height: 100%">
                  <div
                    class="myposts"
                    style="padding: 20px"
                    v-for="(post1, index) in postList"
                    :key="index"
                  >
                    <!-- 帖子列表 -->
                    <el-row style="margin-bottom: 10px; width: 100%">
                      <el-col :span="3">
                        <el-avatar :size="50" :src="`/serve/${post1.uimg}`" />
                      </el-col>
                      <el-col :span="8">
                        <div>
                          <p>{{ user.username }}</p>
                          <p>
                            {{ new Date(post1.creatTime).toLocaleString() }}
                          </p>
                        </div>
                      </el-col>
                      <el-col :span="12"></el-col>
                      <el-col :span="1">
                        <span
                          style="color: white; cursor: pointer"
                          @click="open(post1.id)"
                          >x</span
                        >
                      </el-col>
                    </el-row>
                    <el-row>
                      <div>
                        {{ post1.content }}
                      </div>
                    </el-row>
                    <el-row id="imgList" style="width: 100%">
                      <e-col
                        :span="12"
                        v-for="(imgs, index) in this.urlList[index]"
                        :key="index"
                      >
                        <img
                          v-if="post1.type == 'img'"
                          :src="'/serve' + imgs"
                          alt=""
                        />
                        <video
                          :src="'/serve' + imgs"
                          v-if="post1.type == 'video'"
                          style="width: 300px; height: 150px"
                          controls
                        ></video>
                      </e-col>
                    </el-row>
                    <el-row>
                      <!--  -->
                      <div class="demo-collapse" style="width: 100%">
                        <el-collapse
                          accordion
                          style="background-color: transparent"
                        >
                          <el-collapse-item
                            name="1"
                            style="background-color: transparent"
                          >
                            <template
                              #title
                              style="
                                background-color: rgb(57, 130, 213, 0.3);
                                width: 100%;
                              "
                            >
                              <el-row
                                style="
                                  width: 100%;
                                  color: white;
                                  font-size: 18px;
                                "
                              >
                                <!-- 抽屉标题 -->
                                <el-col :span="4">
                                  <el-icon
                                    v-if="!userList.isLike"
                                    @click="UpLike(index)"
                                  >
                                    <Star />
                                  </el-icon>
                                  <el-icon v-else @click="UpLike(index)">
                                    <Pointer />
                                  </el-icon>
                                  点赞
                                </el-col>
                                <el-col :span="4">
                                  <el-icon>
                                    <ChatDotSquare />
                                  </el-icon>
                                  评论
                                  {{
                                    userArr.filter((data) => {
                                      return data.songId == post1.id;
                                    }).length
                                  }}
                                </el-col>
                                <el-col :span="4">
                                  <el-icon
                                    v-if="!userList.isCollect"
                                    @click="UpCollect(index)"
                                  >
                                    <Star />
                                  </el-icon>
                                </el-col>
                              </el-row>
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
                              <el-row>
                                <el-col :span="20"></el-col>
                                <el-col :span="4" style="padding-bottom: 10px">
                                  <el-button
                                    style="height: 30px; width: 80%"
                                    type="primary"
                                    @click="
                                      setComments(post1.id, post1.uid, contents)
                                    "
                                    >发布</el-button
                                  ></el-col
                                >
                              </el-row>
                              <el-row
                                id="conT"
                                v-for="coment in userArr.filter((data) => {
                                  return data.songId == post1.id;
                                })"
                                v-if="isReload"
                                :key="coment.commentId"
                                style="color: white"
                              >
                                <!-- 间距 -->
                                <!-- <el-col :span="1"></el-col> -->
                                <!-- 头像 -->
                                <el-col :span="3">
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
                                <el-col :span="5">
                                  <h2>
                                    {{
                                      usernameAnduserImg.filter((data) => {
                                        return data.id == coment.uid;
                                      })[0].username
                                    }}
                                  </h2>
                                  <p
                                    style="line-height: 18px; margin-top: 20px"
                                  >
                                    {{ coment.createtime }}
                                  </p>
                                </el-col>
                                <!-- 内容 -->
                                <el-col :span="13">
                                  <p style="font-size: 20px">
                                    : {{ coment.content }}
                                  </p>
                                </el-col>
                                <!-- 点赞和删除 -->
                                <el-col :span="3">
                                  <span
                                    v-if="coment.uid == user.userId"
                                    @click="delComent(coment)"
                                    style="
                                      color: red;
                                      cursor: pointer;
                                      position: relative;
                                      right: -35px;
                                      top: 0px;
                                      font-size: 16px;
                                    "
                                    >x</span
                                  >
                                  <div
                                    style="position: relative; bottom: -40px"
                                  >
                                    <el-icon
                                      size="20px"
                                      style="position: relative; top: 3px"
                                    >
                                      <Pointer />
                                    </el-icon>
                                    <b style="font-size: 16px"> | 回复</b>
                                  </div>
                                </el-col>
                              </el-row>
                            </div>
                          </el-collapse-item>
                        </el-collapse>
                      </div>
                      <!--  -->
                    </el-row>
                  </div>
                </el-col>
              </el-row>
            </el-row>
          </el-main>
          <!-- 小游戏 -->
          <el-main v-if="changingover == '游戏'"> </el-main>
        </el-container>
      </div>
    </div>
  </div>
</template>
<script>
import { Search } from "@element-plus/icons-vue";
import { reactive } from "vue";
import { ref } from "vue";
import { getCurrentInstance } from "vue";
import { HttpManager } from "@/api";
import "@/assets/icons/index.js";
import { ElNotification } from "element-plus";
import { h } from "vue";
import axios from "axios";
import { ElMessageBox } from "element-plus";
import { ElMessage } from "element-plus";
import {
  Pointer,
  Star,
  StarFilled,
  ChatDotSquare,
  Message,
  Comment,
  User,
  Promotion,
  BellFilled,
  Place,
  circleUrl,
} from "@element-plus/icons-vue";
export default {
  data() {
    return {
      contents: "",
      isReload: true,
      //
      postList: "",
      myPost: [],
      urlList: [],
      user: getCurrentInstance().proxy.$store.state.user,
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
      userList: {
        isLike: true,
        isCollect: true,
      },
      isEmoShow: false,
      videoUrl: [],
      userArr: [],
      usernameAnduserImg: [],
    };
  },
  created() {
    this.getPost();
    this.inIT();
  },
  methods: {
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

    // 发布
    setComments(id, uid, content) {
      if (content != "") {
        HttpManager.setComment(uid, id, content).then((res) => {
          console.log(res);
        });
        this.contents = "";
        // this.rekoadPart();
        this.inIT();
      }
    },
    // s刷新
    rekoadPart() {
      this.isReload = false;
      this.$nextTick(() => {
        this.isReload = true;
      });
    },
    // 删除帖子
    delPost(id) {
      console.log(id);
      axios.post(`/serve/dynamic/del?id=${id}`).then((res) => {
        console.log(res);
      });
      this.getPost();
      this.inIT();
    },
    open(id) {
      this.$confirm("此操作将永久该动态, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.delPost(id);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
        this.getPost();
        this.inIT();
    },

    // 删除评论
    delComent(id) {
      HttpManager.deleteComment(id.commentId).then((res) => {
        if (res == "success") {
          this.inIT();
          // this.rekoadPart();
        }
      });
    },
    getPost() {
      let left = this;
      // 获取帖子列表
      axios({
        method: "GET",
        url: "/serve/dynamic/byid?uid=" + this.user.userId,
      }).then((res) => {
        left.postList = res.data;
        // 过滤地址
        for (let i = 0; i < res.data.length; i++) {
          this.urlList.push(res.data[i].contentUrl.split("!!"));
          if (res.data[i].uid == this.user.userId) {
            left.myPost.push(res.data[i]);
          }
        }
      });
    },

    stEmolist() {
      this.isEmoShow = !this.isEmoShow;
    },
    apEmo(emoji) {
      this.form.desc = this.form.desc + emoji;
    },
  },
  components: {
    Pointer,
    StarFilled,
    Star,
    ChatDotSquare,
    Comment,
    User,
    Promotion,
    BellFilled,
    Place,
  },
  setup(props) {
    const open2 = () => {
      ElNotification({
        title: "提示",
        message: "对方已是您的好友",
        type: "success",
      });
    };
    const form = reactive({
      desc: "",
    });
    // 切换对象
    const changingover = ref("聊天");
    // 搜索内容
    const SearchContent = ref([]);

    const { proxy } = getCurrentInstance();
    //上方的显示
    const fanusername = ref();
    // 好友列表数据
    const firendList = ref([]);
    // 用来装总信息的
    const firendsorr = ref([]);
    // 搜索框里面的值
    const input1 = ref("");
    //搜索到的好友信息
    const Searchinformationyes = ref();
    //好友添加消息的出现
    const FriendAddMessage = ref([]);
    // 过滤数组
    const input= ref("");
    const filtrationList=ref([]);
    const userId = ref(proxy.$store.state.user.userId);
    const chatUrl = ref("");
    const pyidyes = ref();
    //用来装好友列表的
    async function getfirendsall() {
      firendList.value = await HttpManager.getfiendsall(userId.value);
      filtrationList.value = firendList.value;
    }
    // 搜索已添加好友
    function added(){
      firendList.value = filtrationList.value.filter((res)=>{
        return res.pyname.indexOf(input.value)!=-1
      })

    }
    getfirendsall();
    //点击切换好友的函数
    function SwitchFriends(item) {
      fanusername.value = item.pyname;
      pyidyes.value = item.prid;
      chatUrl.value = item.pyurl;
      //获取好友聊天内容
      async function getfirendsconst() {
        firendsorr.value = await HttpManager.getCatserve(
          userId.value,
          pyidyes.value
        );
      }
      getfirendsconst();
      setInterval(function () {
        getfirendsconst();
      }, 3000);
    }
    // 好友搜索界面

    function changingoverhs() {
      changingover.value = "好友";
    }
    function chat() {
      changingover.value = "聊天";
    }
    function kongjian() {
      changingover.value = "空间";
    }
    const onSubmit = () => {
      HttpManager.getSendachatmessage(userId.value, pyidyes.value, form.desc);
      async function getfirendsconst() {
        firendsorr.value = await HttpManager.getCatserve(
          userId.value,
          +pyidyes.value
        );
        form.desc = "";
      }
      getfirendsconst();
    };
    // =========================好友界面的列表
    async function Searchforfriends() {
      if (input1.value != "") {
        const rest = await HttpManager.getUserOfname(input1.value);
        let arr = new Array();
        rest.forEach((obj) => {
          if (userId.value != obj.id) {
            arr.push(obj);
          }
        });
        Searchinformationyes.value = arr;
      }
    }
    async function add(value) {
      await HttpManager.AddFriends(userId.value, value.id);
    }

    // 好友消息显示
    async function Message() {
      let yes = await HttpManager.HaoYouXiaoXi(userId.value);
      let good = await HttpManager.getUserOfId(yes[0].pyid);
      FriendAddMessage.value = good.data;
    }
    async function agree(value) {
      location.reload();
      let type = 2;
      await HttpManager.Confirmconsen(userId.value, value.id, type);
    }
    async function refuse(value) {
      location.reload();
      await HttpManager.Confirmconsen(userId.value, value.id, 0);
    }
    Message();
    return {
      input,
      added,
      Pointer,
      kongjian,
      SwitchFriends,
      firendList,
      fanusername,
      firendsorr,
      onSubmit,
      form,
      changingover,
      changingoverhs,
      chat,
      SearchContent,
      input1,
      Search,
      Message,
      Comment,
      User,
      Promotion,
      BellFilled,
      Place,
      ElMessageBox,
      circleUrl,
      Searchforfriends,
      Searchinformationyes,
      add,
      FriendAddMessage,
      agree,
      refuse,
      chatUrl,
      open2,
    };
  },
};
</script>
<style scoped>
.el-icon svg {
  color: white;
}

.el-collapse {
  --el-collapse-content-bg-color: none;
  --el-collapse-header-bg-color: none;
  border-radius: 3px;
}

body {
  overflow: hidden;
}
</style>
<style >
.el-card__body {
  padding: 10px;
}

.el-main {
  --el-main-padding: 0;
  background-color: transparent;
}
</style>
<style scoped>
#contt {
  border-top-right-radius: 40px;
  border-bottom-right-radius: 40px;
  /* border-bottom-left-radius:8% ; */
  background-color: rgba(211, 211, 211, 0.3);
}

.myposts:hover {
  background-color: rgb(255, 255, 255, 0.1);
}

#kongjiana {
  gap: 20px;
  border-radius: 3px;
  backdrop-filter: blur(0px);
  background-color: rgba(235, 255, 255, 0.178);
  box-shadow: rgba(0, 0, 0, 0.3) 2px 8px 8px;
  border: 1px rgba(255, 255, 255, 0.4) solid;
  border-bottom: 1px rgba(40, 40, 40, 0.35) solid;
  border-right: 1px rgba(40, 40, 40, 0.35) solid;
}

.tjlist:hover {
  background-color: rgba(205, 255, 254, 0.2);
}

#contt section {
}

#cont {
  /* box-shadow:#999999 10px 10px 10px; */
}

#cont > div {
  /* background: linear-gradient(
    rgb(73, 183, 157) 0%,
    rgb(73, 180, 194) 100%
  ); */
}

#imgList img {
  height: 100px;
  padding-right: 10px;
}

#left-list {
  border-top-left-radius: 40px;
  border-bottom-left-radius: 40px;
  /* color: white; */
  backdrop-filter: blur(4px);
  background-color: rgba(0, 191, 255, 0.043);
  box-shadow: rgba(0, 0, 0, 0.3) 2px 8px 8px;
  border: 2px rgba(255, 255, 255, 0.4) solid;
  border-bottom: 2px rgba(40, 40, 40, 0.35) solid;
  border-right: 2px rgba(40, 40, 40, 0.35) solid;
}

video#v1 {
  position: fixed;
  right: 0px;
  bottom: 0px;
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
  /*加滤镜*/
  /*filter: blur(15px); //背景模糊设置 */
  /*-webkit-filter: grayscale(100%);*/
  /*filter:grayscale(100%); //背景灰度设置*/
  z-index: -11;
}

source {
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
}

#left-list .userList {
  padding-top: 10px;
  height: 160px;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

#left-list > .el-menu {
  margin-top: 20px;
  padding: 10px;
  background-color: transparent;
  width: 100%;
}

#left-list > .el-menu > .el-menu-item {
  font-size: 16px;
  color: white;
  margin: 10px;
}

#left-list > .el-menu > .el-menu-item:hover {
  background-color: rgb(255, 255, 255, 0.1);
}

#contt {
  /* border-radius: 20px; */
}

tbody {
  display: inline-block;
  width: 100%;
  height: 50px;
}

tr {
  display: inline-block;
  width: 100%;
  height: 50px;
}

td {
  display: inline-block;
}

.emojiss {
  position: absolute;
  right: 30px;
  bottom: 10px;
}

.emojiss ul {
  background-color: white;
  width: 200px;
}

.emojiss ul li {
  padding: 1px;
}

.head {
  /* overflow: hidden; */
  /* transform:100px; */
  --el-header-height: 80px;
  background-color: rgb(246, 246, 246, 0.6);
}

.head > div {
  padding-top: 10px;
}

.head h1 {
  font-size: 20px;
  padding: 5px;
}

.head .el-input {
  width: 25%;
}

.head > h3 {
  margin-top: 18px;
}

#fridenzz {
  gap: 20px;
  border-radius: 3px;
  backdrop-filter: blur(0px);
  background-color: rgba(0, 0, 0, 0.065);
  box-shadow: rgba(0, 0, 0, 0.3) 2px 8px 8px;
  border: 1px rgba(255, 255, 255, 0.4) solid;
  border-bottom: 1px rgba(40, 40, 40, 0.35) solid;
  border-right: 1px rgba(40, 40, 40, 0.35) solid;
}

.frcontents {
  float: right;
  background: greenyellow !important;
  border-radius: 5px;
}

.cell {
  width: 80%;
  margin: 20px auto;
  background: white;
  border-radius: 10px;
  height: 10%;
  display: flex;
  align-items: center;
  transition: transform 1s;
  transition: width 1s;
}

.cell:hover ~ .cell {
  transform: translate(0px, 10px);
  transform: rotateX(60deg);
  transform: rotateY(-30deg);
  transition: transform 1s;
}

.cell:hover {
  box-shadow: 0 10px 6px -6px #c8a7a7;
  width: 85%;
}

.input {
  position: absolute;
  top: -40px;
  right: 100px;
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>