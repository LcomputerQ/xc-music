<template>
  <div class="play-list-container">
    <yin-nav
      :styleList="MVStyle"
      :activeName="activeName"
      @click="handleChangeView"
    ></yin-nav>

    <play-list-copy :playList="data" path="singer-detail"></play-list-copy>

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
import PlayListCopy from "@/components/PlayListcopy.vue";
import { MVStyle } from "@/enums";
import { HttpManager } from "@/api";

// data
const activeName = ref("最新MV");
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

// 获取最新的mv
async function getAlltNewMV() {
  const result = (await HttpManager.getNewMV()) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}

getAlltNewMV();

// 获取当前页
function handleCurrentChange(val) {
  currentPage.value = val;
}

function handleChangeView(item) {
  activeName.value = item.name;
  allPlayList.value = [];
  if (item.name === "最新MV") {
    getAlltNewMV();
  } else if (item.name == "推荐MV") {
    getrecommend();
  } else if (item.name == "内地MV") {
    getSingerSexwoman();
  } else if (item.name == "欧美MV") {
    Groupsinger();
  } else if (item.name == "韩国MV") {
    HGpsinger();
  } else if (item.name == "日本MV") {
    RBpsinger();
  }
}

// 推荐MV
async function getrecommend() {
  const result = (await HttpManager.getrecommendMv()) as ResponseBody;
  console.log(result);
  currentPage.value = 1;
  allPlayList.value = result.data;
}
//内地MV
async function getSingerSexwoman() {
  const result = (await HttpManager.getGetallmvs("内地")) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}
//欧美MV
async function Groupsinger() {
  const result = (await HttpManager.getGetallmvs("欧美")) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}
// 韩国MV
async function HGpsinger() {
  const result = (await HttpManager.getGetallmvs("韩国")) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}
async function RBpsinger() {
  const result = (await HttpManager.getGetallmvs("日本")) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}
</script>
