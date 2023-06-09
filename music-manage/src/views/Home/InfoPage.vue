<template>
	<el-row :gutter="20">
		<el-col :span="6">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><user /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ userCount }}</div>
						<div>用户总数</div>
					</div>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6" @click="getOnlineNumber()">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><Iphone /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ songCount }}</div>
						<div>在线人数</div>
					</div>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><Opportunity /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ DailyVisits }}</div>
						<div>日访问量</div>
					</div>
				</div>
			</el-card>
		</el-col>
		<el-col :span="6">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><Histogram /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ AccumulateVisits }}</div>
						<div>累计访问量</div>
					</div>
				</div>
			</el-card>
		</el-col>
	</el-row>
	<el-row :gutter="20">
		<el-col :span="15">
			<h3>日访问量折线图</h3>
			<el-card
				class="cav-info"
				shadow="hover"
				:body-style="{ padding: '0px' }"
				id="Visits"
			></el-card>
		</el-col>
		<el-col :span="9">
			<el-calendar v-model="value" />
		</el-col>
	</el-row>
</template>
<script lang="ts" setup>
// import { ref, reactive, getCurrentInstance } from "vue";
import { ref, reactive,getCurrentInstance } from "vue";
import * as echarts from "echarts";
import { RouterName } from "@/enums";
import mixin from "@/mixins/mixin";
import {
	Mic,
	Document,
	User,
	Headset,
	Opportunity,
	Avatar,
	Iphone,
	Histogram,
} from "@element-plus/icons-vue";
import { HttpManager } from "@/api/index";
import { log } from "console";

const { proxy } = getCurrentInstance();
const value = ref(new Date())
//用户人数
const userCount = ref(0);
//在线人数
const songCount = ref(0);
// 日访问量
const DailyVisits = ref(0);
// 积累访问量
const AccumulateVisits = ref(0);
const Visits = {
	xAxis: {
		type: "category",
		data: ["前五天", "前四天", "前三天", "前二天", "前天", "昨天", "今天"],
	},
	yAxis: {
		type: "value",
	},
	tooltip: {
		//提示框组件
		trigger: "item", //item数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
		axisPointer: {
			// 坐标轴指示器，坐标轴触发有效
			type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
		},
		formatter: "访问量 <br/>{b} : {c} <br/>", //{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
	},
	series: [
		{
			data: [],
			type: "line",
		},
	],
};
const { changeSex, routerManager } = mixin();
function getOnlineNumber(){

	const breadcrumbList = reactive(
        {
          path: RouterName.Consumer,
          name: "在线人数",
        },
      );
      // console.log(id); 
      proxy.$store.commit("setBreadcrumbList", breadcrumbList);
	routerManager(RouterName.OnlineNumber,{path: RouterName.OnlineNumber});
};
// 获取积累人数
setInterval(function () {
	HttpManager.getjournaling("/journaling/data").then((res) => {
		// console.log(res);
		DailyVisits.value = res.online;
		AccumulateVisits.value = res.accumulate;
		Visits.series[0].data = res.visitList;
		Visits.series[0].data.push(res.online);
		const VisitsChart = echarts.init(document.getElementById("Visits"));
		VisitsChart.setOption(Visits);
		//获取用户总人数
		HttpManager.getAllUser().then((res) => {
			userCount.value = res.data.length;
		});
		//获取在线人数
		HttpManager.getOnline().then((res) => {
			songCount.value = res.length;
		});
	});
}, 1500);

// HttpManager.getAllSong().then((res) => {
//   songCount.value = (res as ResponseBody).data.length;
// });
// HttpManager.getSongList().then((res) => {
//   const result = res as ResponseBody;
//   songListCount.value = result.data.length;
//   for (let item of result.data) {
//     for (let i = 0; i < songStyle.xAxis.data.length; i++) {
//       if (item.style.includes(songStyle.xAxis.data[i])) {
//         songStyle.series[0].data[i]++;
//       }
//     }
//   }

// });
</script>

<style scoped>
.card-content {
	display: flex;
	align-items: center;
	justify-content: space-around;
	height: 100px;
	padding-left: 20%;
	text-align: center;
}

.card-left {
	display: flex;
	font-size: 3rem;
}

.card-right {
	flex: 1;
	font-size: 14px;
}

.card-num {
	font-size: 30px;
	font-weight: bold;
}

h3 {
	margin: 10px 0;
	text-align: center;
}
.cav-info {
	border-radius: 6px;
	overflow: hidden;
	width: 100%;
	height: 500px;
	background-color: white;
}
</style>
