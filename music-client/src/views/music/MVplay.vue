<template>
	<div>
		<el-row class="mv">
			<el-col :span="16" :xs="24">
				<div class="message">
					<span>{{ mvpages }}</span
					><samp class="mvxx">{{ mvpage }}</samp>
				</div>
				<video
					class="mvs"
					:src="urlsong"
					style="width: 100%"
					controls
					autoplay
				/>
			</el-col>
			<el-col :span="8" :xs="24"  class="xsmv">
				<el-row v-for="item in mvList" :key="item.id" class="similarityMv" @click="getMv(item.id)">
					<el-col :span="10">
						<el-image style="width: 100%" :src="item.cover" :fit="fit" />
					</el-col>
					<el-col :span="14" align="center" justify="center">
						<el-text class="mx-1" size="large">{{ item.briefDesc }}</el-text>
            <br>
						<el-text class="mx-1" size="large">{{
							item.name + "-" + item.artistName
						}}</el-text>
					</el-col>
				</el-row>
			</el-col>
		</el-row>
	</div>
</template>

<script>
import { ref, computed } from "vue";
import { getCurrentInstance } from "vue";
import { HttpManager } from "@/api";

export default {
	setup(props) {
		const { proxy } = getCurrentInstance();
		//mvid
		const songid = ref(proxy.$store.getters.songDetails.id);
		//mv播放地址
		const urlsong = ref();
		//mv详细信息
		const mvDetail = ref();
		//mv页面展示信息
		const mvpage = ref();
		//mv页面展示信息
		const mvpages = ref();
		//相似mv
		const mvList = ref([]);

		async function MvVideoConnection() {
			//获取mv播放地址
			const result = await HttpManager.getMvVideoConnection(songid.value);
			urlsong.value = result.data.url;
      console.log(urlsong.value);
			//获取mv信息
			const results = await HttpManager.getMvDetail(songid.value);
			mvDetail.value = results.data;
			mvpage.value = results.data.name + "[" + results.data.artistName + "]";
			if (results.data.alias) mvpages.value = results.data.alias[0];
			else {
				mvpages.value = "";
			}
			//获取相似mv
			const mvLists = await HttpManager.getSimilarityMv(songid.value);
			mvList.value = mvLists.mvs;
			console.log(mvLists.mvs);
		}

		MvVideoConnection();
    function getMv(id){
      songid.value=id;
      MvVideoConnection();
    }
		return {
			urlsong,
			mvpage,
			mvpages,
			mvList,
      getMv
		};
	},
};
</script>

<style>
.mv {
	width: 80%;
	height: 80vh;
	margin: 0 auto;
	/* border: 1px solid red; */
  background-color: rgb(247, 245, 245);
}
.mvs {
	padding-top: 20px;
	text-align: center;
}
.message {
	padding: 20px 30px;
	font-size: 22px;
}
.mvxx {
	padding-left: 20px;
}
.similarityMv {
	margin: 10px 20px;
}
.xsmv{
  margin-top:80px ;
}
</style>