import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const now = new Date();

const store = new Vuex.Store({
	state: {
		sessions: [{
			id: 1,
			serving: true,
			waiting: false,
			robot: false,
			user: {
				name: '示例介绍',
				img: './src/assets/images/2.png'
			},
			messages: [{
				content: '测试',
				date: now
			}]
		}, {
			id: 2,
			serving: true,
			waiting: false,
			robot: false,
			user: {
				name: 'webpack',
				img: 'assets/images/3.jpg'
			},
			messages: [{
				content: 'Hi，我是webpack2哦',
				date: now
			}]
		},{
			id: 3,
			serving: false,
			waiting: true,
			robot: false,
			user: {
				name: 'webpack',
				img: 'assets/images/3.jpg'
			},
			messages: [{
				content: 'Hi，我是webpack3哦',
				date: now
			}]
		},{
			id: 4,
			serving: false,
			waiting: true,
			robot: false,
			user: {
				name: 'yukang',
				img: 'assets/images/3.jpg'
			},
			messages: [{
				content: 'Hi，我是yukang哦',
				date: now
			}]
		}],
		robotChatting: [],
		currentSessionId: 1,
		filterKey: ''
	},
	getters: {

	},
	mutations: {
		changeCurrentSessionId(state, id) {
			state.currentSessionId = id;
		},
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
			state.robotChatting.push({
				content: msg.content,
				date: msg.date,
				self: msg.isSelf
			})
		},
		addMessage(state, payload) {
			alert(payload.itemId)
			state.sessions[payload.itemId - 1].messages.push({
				content: payload.msg.content,
				date: payload.msg.date,
				self: !payload.msg.isSelf
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
			alert(JSON.stringify(state.sessions[id - 1]))
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