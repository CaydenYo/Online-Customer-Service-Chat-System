import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const now = new Date();

const store = new Vuex.Store({
	state: {
		sessions: [],
		robotChatting: [],
		currentSessionId: 1,
		filterKey: '',
		clientMessage: {
		    senderId: '',
		    nickname: '',
		    img: '',
		    receiverId: ''
		},
		customerServiceMessage: {
			senderId: '',
			nickname: '',
			img: '',
			receiverId: '',
			company_Id: ''
		}
	},
	getters: {

	},
	mutations: {
		//存储用户个人信息
		clientLoginSuccess(state, payload) {
			alert("开始储存用户信息：" + JSON.stringify(payload))
			state.clientMessage.senderId = payload.senderId
			state.clientMessage.nickname = payload.nickname
			state.clientMessage.img = payload.img
			alert("储存用户信息完毕：" + JSON.stringify(state.clientMessage))
		},
		//储存用户receiverId
		saveClientReveiverId(state, payload) {
			state.clientMessage.receiverId = payload
		},
		//存储客服个人信息
		customerServiceloginSuccess(state, payload) {
			alert("开始储存客服信息：" + JSON.stringify(payload))
			state.customerServiceMessage.senderId = payload.senderId
			state.customerServiceMessage.nickname = payload.nickname
			state.customerServiceMessage.company_Id = payload.company_Id
			alert("储存客服信息完毕：" + JSON.stringify(state.customerServiceMessage))
		},
		//储存客服receiverId
		savecustomerServiceReveiverId(state, payload) {
			state.customerServiceMessage.receiverId = payload
		},
		//客服人员功能
		changeCurrentSessionId(state, id) {
			state.currentSessionId = id;
		},
		clearWaitings(state) {
			for(var i = 0;i < state.sessions.length;i++){
				if (state.sessions[i].waiting == true) {
					state.sessions.splice(i, 1)
				}
			}
		},
		addToWaitings(state, payload) {
			state.sessions.push({
				id: state.sessions.length + 1,
				serving: false,
				waiting: true,
				robot: false,
				user: {
					customer_id: payload.customer_id,
					name: payload.customer_nickname,
					img: payload.customer_img
				},
				messages: []
			})
		},
		addItem(state) {
			state.sessions.push({
				id: state.sessions.length + 1,
				user: {
					name: '测试',
					img: './src/assets/images/2.png'
				},
				messages: [{
					content: 'hi!!!我是第' + (state.sessions.length + 1) + '个!!',
					date: new Date(),
					self: true
				}]
			})
		},
		changeToServing(state, id){
			state.sessions[id - 1].waiting = false;
			state.sessions[id - 1].serving = true;
		},
		addMessage(state, payload) {
			alert("往store中加信息: "+ JSON.stringify(payload))
			if(payload.msg.isTransfer == true) {
				state.sessions.push({
					id: state.sessions.length + 1,
					serving: true,
					waiting: false,
					robot: false,
					user: {
						customer_id: payload.msg.receiverId,
						name: payload.msg.servedClient.name,
						img: payload.msg.servedClient.img
					},
					messages: [{
						content: payload.msg.content,
						date: payload.msg.date,
						self: payload.msg.isSelf
					}]
				})
				alert("已完成会话转接插入且session长度为"+state.sessions.length)
			}else{
				alert('客户的itemId'+payload.itemId)
				state.sessions[payload.itemId - 1].messages.push({
				content: payload.msg.content,
				date: payload.msg.date,
				self: payload.msg.isSelf
			})
			}
		},
		addImg(state, payload) {
			alert("往store中加信息: "+ JSON.stringify(payload))
			if(payload.msg.isTransfer == true) {
				state.sessions.push({
					id: state.sessions.length + 1,
					serving: true,
					waiting: false,
					robot: false,
					user: {
						customer_id: payload.msg.receiverId,
						name: payload.msg.servedClient.name,
						img: payload.msg.servedClient.img
					},
					messages: [{
						content: payload.msg.content,
						date: payload.msg.date,
						self: payload.msg.isSelf,
					}]
				})
				alert("已完成会话转接插入且session长度为"+state.sessions.length)
			}else{
				alert('客户的itemId'+payload.itemId)
				state.sessions[payload.itemId - 1].messages.push({
				content: payload.msg.content,
				date: payload.msg.date,
				self: payload.msg.isSelf,
				isImg: true
			})
			}
		},
		closeConversation(state, payload) {
			state.sessions.splice(payload - 1, 1)
		},
		finishedChangeCS(state, payload) {
			state.sessions.splice(payload - 1, 1)
		},
		clearMessages(state, payload) {
			state.sessions[payload - 1].messages.splice(0, state.sessions[payload - 1].messages.length)
		},
		//客户功能
		addToRobotChatting(state, payload) {
			state.sessions.push({
				id: state.sessions.length + 1,
				serving: false,
				waiting: false,
				robot: true,
				user: {
					name: payload,
					img: './src/assets/images/2.png'
				},
				messages: []
			})
			alert(JSON.stringify(state.sessions[state.sessions.length - 1]))
		},
		addRobotMessage(state, msg) {
			alert("往机器人信息中添加"+JSON.stringify(msg))
			state.robotChatting.push({
				name: msg.nickname,
				content: msg.content,
				date: msg.date,
				self: msg.isSelf
			})
		},
		addClientMessage(state, payload) {
			state.sessions.push({
				name: payload.nickname,
				content: payload.content,
				date: payload.date,
				self: payload.isSelf,
				img: payload.img
			})
		},
		INIT_DATA(state) {
			let data = localStorage.getItem('vue-chat-session');
			//console.log(data)
			if (data) {
				state.sessions = JSON.parse(data);
			}
		}
	},
	actions: {
		initData(context) {
			context.commit('INIT_DATA')
		}
	}
})


store.watch(function (state) {
	return state.sessions
}, function (val) {
	console.log('CHANGE: ', val);
	localStorage.setItem('vue-chat-session', JSON.stringify(val));
}, {
	deep: true /*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})


export default store;