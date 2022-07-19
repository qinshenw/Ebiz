<template>
  <div class="header-tool">

  </div>
</template>

<script>
export default {
  name: 'HeaderTool',
  data() {
    return {
      spin: false
    }
  },
  methods: {

    loadCache() {
      this.$store.dispatch("dictCache/loadCache");
    }
  }
}
</script>

<style lang="less">
.header-tool {
  .search-icon {
    font-size: 16px;
    cursor: pointer;
  }
}
</style>
