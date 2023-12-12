<template>
  <div>
    <Post v-for="post in posts" :key="post.id" :post="post" :user="users[post.userId]"
              :comments="comments"/>
  </div>
</template>

<script>
import Post from "@/components/page/Post.vue";
export default {
    name: "Index",
    components: {Post},
    props: ["users", "posts", "comments"],
    computed: {
    }
}
</script>

<style scoped>

</style>
