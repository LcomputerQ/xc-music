<template>
  <transition name="aside-fade">
    <div class="yin-current-play" v-if="showAside">
      <h2 class="title">当前播放</h2>
      <div class="control">共 {{ (currentPlayList && currentPlayList.length) || 0 }} 首</div>
      <ul class="menus">
        <li
          v-for="(item, index) in currentPlayList"
          :class="{ 'is-play': songId === item.id }"
          :key="index"
          @click="a(item,index)">
          {{ item.name +'-'+item.ar[0].name}}
        </li>
      </ul>
    </div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, computed, onMounted } from "vue";
import { useStore } from "vuex";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";

export default defineComponent({
  setup() {
    const { proxy } = getCurrentInstance();
    const store = useStore();
    const { getSongTitle, playMusic } = mixin();
    const songId = computed(() => store.getters.songId); // 音乐 ID
    const currentPlayList = computed(() => store.getters.currentPlayList); // 当前播放
    const showAside = computed(() => store.getters.showAside); // 是否显示侧边栏

    async function a(item,index){
        const resultSong = (await HttpManager.getSongPlay(
          item.id
        )) as ResponseBody;  
        playMusic({
            id: item.id,
            url: resultSong.data[0].url,
            pic: item.al.picUrl,
            index:index,
            name: item.name,
            lyric: item.lyric,
            currentSongList: currentPlayList,
          })
    }
    onMounted(() => {
      document.addEventListener('click', () => {
        proxy.$store.commit('setShowAside', false)
      }, true)
    })
    return {
      a,
      songId,
      currentPlayList,
      showAside,
      getSongTitle,
      playMusic,
    };
  },
  
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/yin-current-play.scss";
</style>
