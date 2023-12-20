<template>
  <div>
    <Post v-for="post in reversePosts" :key="post.id" :post="post" :user="users[post.userId]"
              :comments="filteredComments(post)"/>
  </div>
</template>

<script>
import Post from "@/components/page/Post.vue";
export default {
    name: "Index",
    components: {Post},
    props: ["users", "posts", "comments"],
    computed: {
      reversePosts: function() {
        return Object.values(this.posts).slice().reverse()
      },
    },
    methods: {
      filteredComments: function (post) {
        return Object.values(this.comments).filter(comment => comment.postId === post.id);
      }
    }
}
</script>

<style scoped>

</style>
