<template>
  <el-container>
    <el-aside class="album-slide">
      <el-image class="album-img" fit="contain" :src="srcimg" />
      <h3 class="album-info">{{ classification }}</h3>
    </el-aside>
    <el-main class="album-main">
      <h1>简介</h1>
      <p>{{ introduction }}</p>
      <!--评分-->
      <div class="album-score">
        <div>
          <h3>评分</h3>
          <el-rate
            v-model="value"
            size="large"
            :max=10
            disabled
            show-score
            text-color="#ff9900"
            score-template="{value}分"
          />
        </div>
        <div>
          <h3>我的评分      {{ score }}</h3>
          <el-rate
            allow-half
            :max=10
            v-model="score"
            :disabled="disabledRank"
            @click="pushValue()"
          ></el-rate>
        </div>
      </div>
      <!--歌曲-->
      <song-list class="album-body" :songList="songLists"></song-list>
      <comment :playId="songListId" :type="1"></comment>
    </el-main>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, ref, computed, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import mixin from "@/mixins/mixin";
import SongList from "@/components/SongList.vue";
import Comment from "@/components/Comment.vue";
import { HttpManager } from "@/api";
// import { id } from "element-plus/lib/locale";

export default defineComponent({
  components: {
    SongList,
    Comment,
  },
  data() {
    return {
      srcimg: "", // 歌单id
      Id: window.location.href.split("/")[4],
      songLists: [], //歌曲
      introduction: ``, //简介
      classification: ``, //类别
    };
  },
  // 获取歌单信息

  setup() {
    const { proxy } = getCurrentInstance();
    const store = useStore();
    const { checkStatus } = mixin();
    const value = ref(3.7)
    
    const currentSongList = ref([]); // 存放的音乐
    const songListId = ref(""); // 歌单 ID
    const score = ref(0);
    const rank = ref(0);
    // Imgsrc
    const disabledRank = ref(false);
    const songDetails = computed(() => store.getters.songDetails); // 单个歌单信息
    const userId = computed(() => store.getters.userId);

    songListId.value = songDetails.value.id; // 给歌单ID赋值
    // 随机评分
    function setpingfen(){
      let randomnumber = Math.round((Math.random()*4+6)*10)/10
      value.value = randomnumber
    }
    setpingfen()
    
    // 提交评分
    async function pushValue() {
      if (disabledRank.value || !checkStatus()) return;

      const params = new URLSearchParams();
      params.append("songListId", songListId.value);
      params.append("consumerId", userId.value);
      params.append("score", (score.value * 2).toString());
      try {
        const result = (await HttpManager.setRank(params)) as ResponseBody;
        (proxy as any).$message({
          message: result.message,
          type: result.type,
        });

        if (result.success) {
          disabledRank.value = true;
        }
      } catch (error) {
        console.error(error);
      }
    }

    // getUserRank(userId.value, songListId.value);
    // getRank(songListId.value); // 获取评分

    return {
      songDetails,
      rank,
      score,
      disabledRank,
      currentSongList,
      songListId,
      attachImageUrl: HttpManager.attachImageUrl,
      pushValue,
      value
    };
  },
  // 获取图片路径
  async created() {
    await HttpManager.getUrlsrc(this.Id).then((res) => {
      if ((res as ResponseBody).playlist.algTags != null) {
        this.classification = (res as ResponseBody).playlist.algTags.join();
      }
      this.introduction = (res as ResponseBody).playlist.description;
      this.srcimg = (res as ResponseBody).playlist.coverImgUrl;
      // console.log(this.classification);
    });
    await HttpManager.getSinginglistmusic(this.Id).then((res) => {
      this.songLists = (res as ResponseBody).songs;
      // console.log((res as ResponseBody).songs);
    });
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.album-slide {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;

  .album-img {
    height: 250px;
    width: 250px;
    border-radius: 10%;
  }

  .album-info {
    width: 70%;
    padding-top: 2rem;
  }
}

.album-main {
  h1 {
    font-size: 22px;
  }

  p {
    color: rgba(0, 0, 0, 0.5);
    margin: 10px 0 20px 0px;
  }
  /*歌单打分*/
  .album-score {
    display: flex;
    align-items: center;
    margin: 1vw;

    h3 {
      margin: 10px 0;
    }
    span {
      font-size: 60px;
    }
    & > div:last-child {
      margin-left: 10%;
    }
  }

  .album-body {
    margin: 20px 0 20px 0px;
  }
}

@media screen and (min-width: $sm) {
  .album-slide {
    position: fixed;
    width: 400px;
  }
  .album-main {
    min-width: 600px;
    padding-right: 10vw;
    margin-left: 400px;
  }
}

@media screen and (max-width: $sm) {
  .album-slide {
    display: none;
  }
}
</style>
