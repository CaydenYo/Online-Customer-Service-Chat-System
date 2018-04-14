<template>
	<div id="cs_dialogue">
		<el-row>
			<el-col :span="5">
                <div class="grid-content">
				    <el-container class = "dialogue">
					    <el-header>
                            当前会话
                            <el-button class = "add-dialogue" icon="el-icon-circle-plus">接入新会话</el-button>
                        </el-header>
					    <el-main>
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
			    </div>
            </el-col>
			<el-col :span="9"><div class="grid-content bg-purple-light">
				<message></message>
				<usertext></usertext>
			</div></el-col>
			<el-col :span="5">
                <div class="grid-content">
				    <el-tabs type="border-card">
					   <el-tab-pane label="用户资料">用户管理</el-tab-pane>
					   <el-tab-pane label="知识库">配置管理</el-tab-pane>
				    </el-tabs>
			    </div>
            </el-col>
			<el-col :span="5"><div class="grid-content">
				<el-tabs type="border-card">
					<el-tab-pane label="常用语">常用语</el-tab-pane>
					<el-tab-pane label="聊天记录">配置管理</el-tab-pane>
				</el-tabs>
			</div></el-col>
		</el-row>
	</div>
</template>


<script>
import {mapState} from 'vuex'
import message from '../CS_embeddedChat/message'
import usertext from '../CS_embeddedChat/usertext'

export default {
	name: 'list',
	data () {
		return {
			
		}
	},
	components: {
		message,
		usertext,
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
#list {
    ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }
    li {
        padding: 12px 15px;
        border-bottom: 1px solid #E6E6E6;
        cursor: pointer;
        &:hover {
            background-color: rgba(255, 255, 255, 0.03);
        }
    }
    li.active {/*注意这个是.不是冒号:*/
        background-color: rgba(255, 255, 255, 0.1);
    }
    .avatar {
        border-radius: 2px;
        width: 30px;
        height: 30px;
        vertical-align: middle;
    }
    .name {
        display: inline-block;
        margin-left: 15px;
    }
}
.add-dialogue {
    margin-left: 11%;
}
.dialogue {
    height: 100%;
}
.el-tabs {
    height: 100%;
}
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
.bg-purple-light {
	background: #e5e9f2;
}
.grid-content {
	border-radius: 4px;
	min-height: 36px;
	height: 100%;
    overflow: hidden;
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