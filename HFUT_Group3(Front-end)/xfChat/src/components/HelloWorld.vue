<template>
  <div>
    <div style="height:100px;overflow:auto;">
      <ul class="messages" v-chat-scroll>
        <li v-for="todo in todos">
      {{ todo.text }}
    </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      todos: [
      { text: '整个牛项目' }
    ]
    }
  }
}
</script>


