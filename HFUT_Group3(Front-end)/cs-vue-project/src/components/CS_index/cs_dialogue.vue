<template>
	<div id="cs_dialogue">
		<el-row>
			<el-col :span="5"><div class="grid-content bg-purple">
				<el-container style="height:100%;">
					<el-header>Header</el-header>
					<el-main style="height:100%;">
						<div id="list">
							<ul>
								<li v-for="item in sessions" :class="{ active: item.id === currentSessionId }" v-on:click="changeCurrentSessionId(item.id)"><!--   :class="[item.id === currentSessionId ? 'active':'']" -->
									<img class="avatar" :src="item.user.img">
									<p class="name">{{item.user.name}}</p>
								</li>
							</ul>
						</div>
					</el-main>
				</el-container>
			</div></el-col>
			<el-col :span="9"><div class="grid-content bg-purple-light"></div></el-col>
			<el-col :span="5"><div class="grid-content bg-purple">
				<el-tabs type="border-card">
					<el-tab-pane label="用户资料">用户管理</el-tab-pane>
					<el-tab-pane label="知识库">配置管理</el-tab-pane>
				</el-tabs>
			</div></el-col>
			<el-col :span="5"><div class="grid-content bg-purple-light">
				<el-tabs type="border-card">
					<el-tab-pane label="常用语">用户管理</el-tab-pane>
					<el-tab-pane label="聊天记录">配置管理</el-tab-pane>
				</el-tabs>
			</div></el-col>
		</el-row>
	</div>
</template>


<script>
import {mapState} from 'vuex'

export default {
	name: 'list',
	data () {
		return {
			
		}
	},
	computed: mapState([
		'sessions',
		'currentSessionId'
		]),
	methods:{
		changeCurrentSessionId:function (id) {
			this.$store.commit('changeCurrentSessionId',id)
		}
	}
}
</script>

<style lang="scss" scoped>

.el-row {
	height: 100%;
	margin-bottom: 20px;
	&:last-child {
		margin-bottom: 0;
	}
}
.el-col {
	border-radius: 4px;
	height: 100%;
}
.bg-purple-dark {
	background: #99a9bf;
}
.bg-purple {
	background: #d3dce6;
}
.bg-purple-light {
	background: #e5e9f2;
}
.grid-content {
	border-radius: 4px;
	min-height: 36px;
	height: 100%;
}
.row-bg {
	padding: 10px 0;
	background-color: #f9fafc;
}
#cs_dialogue {
	height: 100%;
}

.el-header, .el-footer {
	background-color: #B3C0D1;
	color: #333;
	text-align: center;
	line-height: 60px;
}

.el-aside {
	background-color: #D3DCE6;
	color: #333;
	text-align: center;
	line-height: 200px;
}

.el-main {
	background-color: #E9EEF3;
	color: #333;
	text-align: center;
	line-height: 160px;
}

body > .el-container {
	margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
	line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
	line-height: 320px;
}
</style>