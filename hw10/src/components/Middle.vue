<template>
    <div class="middle">
        <Sidebar :posts="viewPosts" :users="users"/>
        <main>
            <Index v-if="page === 'Index'" :users="users" :posts="posts" :comments="comments"/>
            <PostPage v-if="page === 'PostPage'" :users="users" :post="post" :comments="Object.values(this.comments).filter(comment => comment.postId === post.id)"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <UsersPage v-if="page === 'UsersPage'" :users="users"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "@/components/page/Register.vue";
import UsersPage from "@/components/page/UsersPage.vue";
import PostPage from "@/components/page/PostPage.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: "",
            user: {},
        }
    },
    components: {
      PostPage,
      UsersPage,
        Register,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
    },
    props: ["posts", "users", "comments", "userId"],
  methods: {
    filteredComments: function (post) {
      return Object.values(this.comments).filter(comment => comment.postId === post.id);
    }
  },
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) =>{ this.page = page});
        this.$root.$on("onPostPage", (post) => {
          this.post = post;
          this.page = "PostPage";
        });
    },
}
</script>

<style scoped>

</style>
