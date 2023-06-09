<template>
  <div class="comment">
    <h2 class="comment-title">
      <span>评论</span>
      <span class="comment-desc">共
        {{
          addNumber
        }}
        条评论</span>
    </h2>
    <el-input class="comment-input" type="textarea" placeholder="期待您的精彩评论..." :rows="2" v-model="textarea" />
    <el-button class="sub-btn" v-if="songcommenton.length != 0" type="primary" @click="submitComment()">发表评论</el-button>
    <el-button class="sub-btn" v-if="songsong.length != 0" type="primary" @click="submitComments()">发表评论</el-button>
  </div>
  <ul class="popular">
    <li v-for="(item, index) in commentList[0]" :key="index">
      <el-image class="popular-img" fit="contain" :src="'/serve' + item.avator" />
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.username }}</li>
          <li class="time">{{ item.createtime }}</li>
          <li class="content">{{ item.content }}</li>
        </ul>
      </div>

      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, index)">
        <div><yin-icon :icon="iconList.Support"></yin-icon> {{ item.up }}</div>
        
        <div style="padding-left: 60px; font-size: 10px; height: 100%;" v-if="item.uid == userId">
          <span @click="delComt(item.commentId)"
            style="color:rgba(0, 0, 0, 0.6) ;position: relative; left: -10px;top:-5px;">x</span>
        </div>
        <el-icon v-if="item.userId === userId" @click="deleteComment(item.id, index)">
          <delete />
        </el-icon>
      </div>
    </li>
  </ul>
  <ul class="popular" v-if="songcommenton.length != 0">
    <li v-for="(item, index) in songcommenton" :key="index">
      <el-image class="popular-img" fit="contain" :src="item.user.avatarUrl" />
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.user.nickname }}</li>
          <li class="time">{{ item.timeStr }}</li>
          <li class="content">{{ item.content }}</li>
        </ul>
      </div>
      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, index)">
        <div><yin-icon :icon="iconList.Support"></yin-icon> {{ 99 }}</div>

        <el-icon v-if="item.userId === userId" @click="deleteComment(item.id, index)">
          <delete />
        </el-icon>
      </div>
    </li>
  </ul>
  <ul class="popular" v-else-if="songsong.length > 0">
    <!--  -->
    <li v-for="(item, index) in comList" :key="index">
      <el-image class="popular-img" fit="contain" :src="item.user.avatarUrl" />
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.user.nickname }}</li>
          <li class="time">{{ item.timeStr }}</li>
          <li class="content">{{ item.content }}</li>
        </ul>
      </div>
      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, index)">
        <div><yin-icon :icon="iconList.Support"></yin-icon> {{ 99 }}</div>
      </div>
    </li>
    <li v-for="(item, index) in songsong" :key="index">
      <el-image class="popular-img" fit="contain" :src="item.user.avatarUrl" />
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.user.nickname }}</li>
          <li class="time">{{ item.timeStr }}</li>
          <li class="content">{{ item.content }}</li>
        </ul>
      </div>
      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, index)">
        <div>
          <yin-icon :icon="iconList.Support"></yin-icon> {{ 99 }} 
        </div>
      </div>
    </li>
  </ul>
</template>

<script lang="ts">
import {
  defineComponent,
  getCurrentInstance,
  ref,
  toRefs,
  computed,
  watch,
  reactive,
  onMounted,
  render,
  nextTick,
} from "vue";
import { useStore } from "vuex";
import { Delete } from "@element-plus/icons-vue";
import YinIcon from "@/components/layouts/YinIcon.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { Icon } from "@/enums";
import { formatDate } from "@/utils";
import { ElNotification } from "element-plus";
import { h } from "vue";
export default defineComponent({
  components: {
    YinIcon,
    Delete,
  },
  props: {
    playId: Number || String, // 歌曲ID或歌单ID
    type: Number, // 歌单（1）/歌曲（0）
  },
  setup(props) {
    const { proxy } = getCurrentInstance();
    const store = useStore();
    const { checkStatus } = mixin();
    const { playId, type } = toRefs(props);
    const comList = ref([]);
    
    // 歌单或则歌曲id
    const songid = ref(props.playId);
    //歌单的评论
    const songcommenton = ref([]);
    const addNumber = ref(0);
    //歌曲的评论
    const songsong = ref([]);
    const user = {};
    const commentList = ref([]); // 存放评论内容
    const textarea = ref(""); // 存放输入内容

    const iconList = reactive({
      Support: Icon.Support,
    });
    const userId = computed(() => store.getters.userId);
    const songId = computed(() => store.getters.songId);

    // 获取歌单和歌曲评论
    async function getsonyes() {
      await HttpManager.getAllCot(songid.value).then((res) => {
        commentList.value.unshift(res);
      });
      if (commentList.value[0].length > 0) {
        for (let i = 0; i < commentList.value[0].length; i++) {
          await HttpManager.getUserOfId(commentList.value[0][i].uid).then(
            (res) => {
              try {
                commentList.value[0][i].username = (
                  res as ResponseBody
                ).data[0].username;
                commentList.value[0][i].avator = (
                  res as ResponseBody
                ).data[0].avator;
              } catch (Exception) { }
            }
          );
        }
      }
      await HttpManager.getcommenton(songid.value).then((res) => {
        songcommenton.value = (res as ResponseBody).comments;

      });
      await HttpManager.getsongcommenton(songid.value).then((res) => {
        songsong.value = (res as ResponseBody).comments;
      });
      addNumber.value = songsong.value.length + commentList.value[0].length + songcommenton.value.length;
    }
    getsonyes();

    // 添加歌单评论
    async function submitComment() {
      if (!checkStatus() || textarea.value == "") return;
      let nowtime = new Date();
      var ok = {
        content: textarea.value,
        timeStr: nowtime.toLocaleString(),
        user: {
          nickname: proxy.$store.state.user.username,
          avatarUrl: `/serve/${proxy.$store.state.user.userPic}`,
        },
      };
      await HttpManager.setComment(
        proxy.$store.state.user.userId,
        window.location.href.split("/")[
        window.location.href.split("/").length - 1
        ],
        ok.content
      );
      textarea.value = "";
      getsonyes()
    }
    // 添加歌曲评论
    async function submitComments() {
      if (!checkStatus()) return;
      let nowtime = new Date();
      var ok = {
        content: textarea.value,
        timeStr: nowtime.toLocaleString(),
        user: {
          nickname: proxy.$store.state.user.username,
          avatarUrl: `/serve/${proxy.$store.state.user.userPic}`,
        },
      };
      await HttpManager.setComment(
        proxy.$store.state.user.userId,
        window.location.href.split("/")[
        window.location.href.split("/").length - 1
        ],
        ok.content
      );
      textarea.value = "";
      getsonyes()
    }

    // 删除评论
    async function deleteComment(id, index) {
      const result = (await HttpManager.deleteComment(id)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });

      if (result.success) commentList.value.splice(index, 1);
    }
    async function delComt(commentId) {
      console.log(commentId);
      await HttpManager.deleteComment(commentId).then((res) => {
        if (res == "success") {
          ElNotification({
            title: "提示",
            message: h("i", { style: "color: teal" }, "删除成功"),
          });
          getsonyes()
        }
      });
    }
    // 点赞
    async function setSupport(id, up, index) {
      // 连接点赞数增加的接口

      if (!checkStatus()) return;

      const params = new URLSearchParams();
      params.append("id", id);
      params.append("up", up + 1);

      const result = (await HttpManager.setSupport(params)) as ResponseBody;
    }

    return {
      addNumber,
      userId,
      commentList,
      textarea,
      iconList,
      comList,
      attachImageUrl: HttpManager.attachImageUrl,
      submitComment,
      setSupport,
      formatDate,
      deleteComment,
      delComt,
      songcommenton,
      songsong,
      submitComments,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

/*评论*/
.comment {
  position: relative;
  margin-bottom: 60px;

  .comment-title {
    height: 50px;
    line-height: 50px;

    .comment-desc {
      font-size: 14px;
      font-weight: 400;
      color: $color-grey;
      margin-left: 10px;
    }
  }

  .comment-input {
    display: flex;
    margin-bottom: 20px;
  }

  .sub-btn {
    position: absolute;
    right: 0;
  }
}

/*热门评论*/
.popular {
  width: 100%;

  >li {
    border-bottom: solid 1px rgba(0, 0, 0, 0.1);
    padding: 15px 0;
    display: flex;

    .popular-img {
      width: 50px;
    }

    .popular-msg {
      padding: 0 20px;
      flex: 1;

      li {
        width: 100%;
      }

      .time {
        font-size: 0.6rem;
        color: rgba(0, 0, 0, 0.5);
      }

      .name {
        color: rgba(0, 0, 0, 0.5);
      }

      .content {
        font-size: 1rem;
      }
    }

    .comment-ctr {
      display: flex;
      align-items: center;
      width: 80px;
      font-size: 1rem;
      cursor: pointer;

      .el-icon {
        margin: 0 10px;
      }

      &:hover,
      :deep(.icon):hover {
        color: $color-grey;
      }
    }
  }
}

.icon {
  @include icon(1em);
}
</style>
