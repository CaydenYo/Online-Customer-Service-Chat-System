<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="stylus" scoped>
#app
  display: flex
  border-radius 50px
  margin: 20px auto
  width: 860px
  height: 600px
  .main
    flex: 1
    height: 600px
    background: #f2f2f2
</style>
