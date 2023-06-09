<template>
  <div class="play-list-container">
    <yin-nav
      :styleList="singerStyle"
      :activeName="activeName"
      @click="handleChangeView"
    ></yin-nav>
    <play-list :playList="data" path="singer-detail"></play-list>
    <el-pagination
      class="pagination"
      background
      layout="total, prev, pager, next"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="allPlayList.length"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import { singerStyle } from "@/enums";
import { HttpManager } from "@/api";

// data
const activeName = ref("全部歌手");
const pageSize = ref(15); // 页数
const currentPage = ref(1); // 当前页
const allPlayList = ref([]);
// computed
const data = computed(() => {
  return allPlayList.value.slice(
    (currentPage.value - 1) * pageSize.value,
    currentPage.value * pageSize.value
  );
});

// 获取所有歌手
async function getAllSinger() {
  const result = (await HttpManager.getAllSingers()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.artists;
}

getAllSinger();

// 获取当前页
function handleCurrentChange(val) {
  currentPage.value = val;
}

function handleChangeView(item) {
  activeName.value = item.name;
  allPlayList.value = [];
  if (item.name === "全部歌手") {
    getAllSinger();
  } else if (item.name == "男歌手") {
    getSingerSex();
  } else if (item.name == "女歌手") {
    getSingerSexwoman();
  } else if (item.name == "组合歌手") {
    Groupsinger();
  }
}

// 男歌手
async function getSingerSex() {
  const result = (await HttpManager.getSingerOfSex()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.artists;
}
//女歌手
async function getSingerSexwoman() {
  const result = (await HttpManager.getSingerOfSexwoman()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.artists;
}
//组合歌手
async function Groupsinger() {
  const result = (await HttpManager.Groupsingeryes()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.artists;
}
</script>
