<template>
 <div class="yes">
   <!--轮播图-->
   <el-carousel class="swiper-container" type="card" height="20vw" :interval="4000">
    <el-carousel-item v-for="(item, index) in swiperList" :key="index">
      <img :src="item.picImg" />
    </el-carousel-item>
  </el-carousel>
  <!--热门歌单-->
  <play-list  class="play-list-container"  title="歌单" path="song-sheet-detail" :playList="songList"></play-list>
  <!--热门歌手-->
  <play-list class="play-list-container" title="歌手" path="singer-detail" :playList="singerList"></play-list>
 </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";
import PlayList from "@/components/PlayList.vue";
import { swiperList, NavName } from "@/enums";
import { HttpManager } from "@/api";
import mixin from "@/mixins/mixin";

const songList = ref([]); // 歌单列表
const singerList = ref([]); // 歌手列表
const { changeIndex } = mixin();
try {
  HttpManager.getSongList().then((res) => {
    songList.value = (res as ResponseBody).result;
  });

  HttpManager.getAllSinger().then((res) => {
    singerList.value = (res as ResponseBody).artists;
  });
  HttpManager.getjournalings("/serve/journaling/all");
  onMounted(() => {
    changeIndex(NavName.Home);
  });
} catch (error) {
  console.error(error);
}
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

/*轮播图*/
.swiper-container {
  width: 90%;
  // background-color:rgb(217, 235, 235) ;
  border-radius:10px;
  margin: auto;
  padding-top: 20px;
  img {
    width: 100%;
  }
}

.swiper-container:deep(.el-carousel__indicators.el-carousel__indicators--outside) {
  display: inline-block;
  transform: translateX(30vw);
}

.el-slider__runway {
  background-color: $color-blue;
}
.yes{
  animation:bg-color 8s infinite linear;
  // animation-direction: alternate;
}
@keyframes bg-color {
0% { background-color: rgb(150, 250, 230) }
50%{ background-color:#5acdea; }
100% { background-color:  rgb(150, 250, 230)  }

}
</style>
