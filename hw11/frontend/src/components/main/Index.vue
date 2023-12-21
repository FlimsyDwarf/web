<template>
  <div>
    <Post v-for="post in reversePosts" :key="post.id" :post="post" :user="users[post.userId]"/>
  </div>
</template>

<script>

import Post from "@/components/page/Post.vue";

export default {
  name: "Index",
  components: {Post},
  props: ["users", "posts"],
  computed: {
    reversePosts: function() {
      return Object.values(this.posts).slice().reverse()
    },
  },
}
</script>

<style scoped>

</style>
