<template>
  <div class="search-song">
    <song-list-test :songList="currentSongList"></song-list-test>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, watch, onMounted, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import SongListTest from "@/components/SongListTest.vue";
import { HttpManager } from "@/api";

export default defineComponent({
  components: {
    SongListTest,
  },
  setup() {
    const { proxy } = getCurrentInstance();
    const store = useStore();

    const currentSongList = ref([]); // 存放的音乐
    const searchWord = computed(() => store.getters.searchWord);
    watch(searchWord, (value) => {
      searchSong(value);
    });

    // 搜索音乐
    async function searchSong(value) {
      if (!value) {
        currentSongList.value = [];
        return;
      }
      const result = (await HttpManager.getSongOfSingerName(value)) as ResponseBody;
      if (!result.result.songs.length) {
        currentSongList.value = [];
        (proxy as any).$message({
          message: "暂时没有相关歌曲",
          type: "warning",
        });
      } else {
        console.log(result.result.songs);
        currentSongList.value = result.result.songs;
      }
    }

    onMounted(() => {
      searchSong(proxy.$route.query.keywords);
    });

    return {
      currentSongList,
    };
  },
});
</script>
