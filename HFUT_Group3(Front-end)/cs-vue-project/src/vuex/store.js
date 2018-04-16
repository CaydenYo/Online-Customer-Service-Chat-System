import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const now = new Date();

const store = new Vuex.Store({
	state: {
		sessions: [{
			id: 1,
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
			user: {
				name: 'webpack',
				img: 'assets/images/3.jpg'
			},
			messages: [{
				content: 'Hi，我是webpack哦',
				date: now
			}]
		}],
		currentSessionId: 1,
		filterKey: ''
	},
	getters: {

	},
	mutations: {
		changeCurrentSessionId(state, id) {
			state.currentSessionId = id;
		},
		addMessage(state, msg) {
			state.sessions[state.currentSessionId - 1].messages.push({
				content: msg,
				date: new Date(),
				self: true
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