<template>
	<el-row :gutter="20">
		<el-col :span="12">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><Female /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ Man }}</div>
						<div>男性用户</div>
					</div>
				</div>
			</el-card>
		</el-col>
		<el-col :span="12">
			<el-card shadow="hover" :body-style="{ padding: '0px' }">
				<div class="card-content">
					<div class="card-left">
						<el-icon><Male /></el-icon>
					</div>
					<div class="card-right">
						<div class="card-num">{{ Girl }}</div>
						<div>女性用户</div>
					</div>
				</div>
			</el-card>
		</el-col>
	</el-row>
	<el-row :gutter="40">
		<el-col :span="24">
			<h3>用户分布图</h3>
			<el-card
				class="cav-info"
				shadow="hover"
				:body-style="{ padding: '0px' }"
				ref="area"
				id="area"
			></el-card>
		</el-col>
	</el-row>
</template>

<script>
import { ref, reactive } from "vue";
import * as echarts from "echarts";
import { Female, Male } from "@element-plus/icons-vue";
import { HttpManager } from '@/api';

export default {
	 data() {
		 return {
			Man: 0,
			Girl: 0,
			optionList: [],
		};
	},
	mounted: function () {
		this.$nextTick(() => {
			this.init();
		});
	},
	methods: {
		async init() {
			//获取性别总数
			const sexList = await HttpManager.getSexAll();
			console.log(sexList);
			this.Girl = sexList[1].avator;
			this.Man = sexList[0].avator;
			const option = {
				legend: {
					orient: "vertical",
					x: "right",
					y: "center",
					padding: 100,
				},
				tooltip: {
					//提示框组件
					trigger: "item", //item数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
					axisPointer: {
						// 坐标轴指示器，坐标轴触发有效
						type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
					},
					formatter: "地区 <br/>{b} : {c} <br/>百分比 : {d}%", //{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
				},
				toolbox: {
					show: true,
					feature: {
						mark: { show: true },
						dataView: { show: true, readOnly: false },
						restore: { show: true },
						saveAsImage: { show: true },
					},
				},
				series: [
					{
						name: "Nightingale Chart",
						type: "pie",
						radius: [50, 250],
						center: ["40%", "50%"],
						roseType: "area",
						itemStyle: {
							borderRadius: 8,
						},

						data: this.optionList,
					},
				],
			};
			// 获取用户地区分数总数
			const AddressList = await HttpManager.getAddressAll();
			const options = () => {
				for (var i = 0; i < AddressList.length; i++) {
					this.optionList[i] = {
						name: AddressList[i].location,
						value: AddressList[i].phoneNum,
					};
				}
			};
			options();
			const Areas = echarts.init(document.getElementById("area"));
			Areas.setOption(option);
		},
	},
	components: {
		Female,
		Male,
	},
};
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
.cav-info {
	border-radius: 6px;
	overflow: hidden;
	width: 100%;
	height: 570px;
	background-color: white;
}
</style>