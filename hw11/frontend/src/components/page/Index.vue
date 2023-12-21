<template>
  <div>
    <Post v-for="post in posts" :key="post.id" :post="post"/>
  </div>
</template>

<script>

import Post from "@/components/page/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts"],
}
</script>

<style scoped>

</style>
