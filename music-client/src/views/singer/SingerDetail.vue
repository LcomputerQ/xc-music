<template>
  <el-container>
    <el-aside class="album-slide">
      <el-image class="singer-img" fit="contain" :src="songDetails.picUrl" />
      <div class="album-info">
        <h2>基本资料</h2>
        <ul>
          <li>性别：{{ sex }}</li>
          <li>生日：{{arr[0] }}</li>
          <li>故乡：{{ arr[1] }}</li>
        </ul>
      </div>
    </el-aside>
    <el-main class="album-main">
      <h1>{{ songDetails.name }}</h1>
      <!-- 歌手描述 -->
      <p>{{ SingerDetailed }}</p>
      <song-list :songList="currentSongList"></song-list>
    </el-main>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onMounted } from "vue";
import { useStore } from "vuex";
import mixin from "@/mixins/mixin";
import SongList from "@/components/SongList.vue";
import { HttpManager } from "@/api";
import { getBirth } from "@/utils";

export default defineComponent({
  components: {
    SongList,
  },
  setup() {
    const store = useStore();
    const { getUserSex } = mixin();
    const arr = ref([])
    // 性别
    const sex = ref();
    const currentSongList = ref([]);
    const SingerDetailed = ref();
    const songDetails = computed(() => store.getters.songDetails) as any;
    
     
   
    onMounted(async () => {
      try {
        const result = await HttpManager.getSongOfSingerId(songDetails.value.id) as ResponseBody;
        // 歌手详细
        const result1 = await HttpManager.getSingerDetailed(songDetails.value.id) as ResponseBody;
        SingerDetailed.value = result1.briefDesc;
        currentSongList.value = result.songs;
        // console.log(currentSongList.value);
        // console.log(SingerDetailed.value.split('，')[1].split('出生于')[0]);
        console.log(SingerDetailed.value);
        // 判断性别
        if(SingerDetailed.value.match(/男歌手/)=='男歌手'){
          sex.value = '男';
        }else if(SingerDetailed.value.match(/女歌手/)=='女歌手'){
          sex.value = '女';
        }
        else{
          sex.value = '未知';
        }

        // 判断出生和住址
        if((/^\d{4}年\d{1,2}月\d{1,2}/).test(SingerDetailed.value.split('，')[1].split('出生于')[0])==true){
          arr.value =  SingerDetailed.value.split('，')[1].split('出生于')
        }else if((/^\d{4}年\d{1,2}月\d{1,2}/).test(SingerDetailed.value.split('，')[2].split('出生于')[0])==true){
          arr.value =  SingerDetailed.value.split('，')[2].split('出生于')
        }
        else{
          arr.value = ['未知','未知']
        }
        // 字符串出问题 数据格式化
      } catch (error) {
        arr.value = ['未知','未知']
        console.error(error);
      }
    });
    return {
      sex,
      arr,
      SingerDetailed,
      songDetails,
      currentSongList,
      attachImageUrl: HttpManager.attachImageUrl,
      getBirth,
      getUserSex,
    };
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

  .singer-img {
    height: 250px;
    width: 250px;
    border-radius: 10%;
  }

  .album-info {
    width: 60%;
    padding-top: 2rem;
    li {
      width: 100%;
      height: 30px;
      line-height: 30px;
    }
  }
}

.album-main {
  p {
    color: rgba(0, 0, 0, 0.5);
    margin: 10px 0 20px 0px;
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
