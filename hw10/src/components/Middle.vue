<template>
    <div class="middle">
        <Sidebar :posts="viewPosts" :users="users"/>
        <main>
            <Index v-if="page === 'Index'" :users="users" :posts="posts" :comments="comments"/>
            <PostPage v-if="page === 'PostPage'" :user="user" :post="post" :comments="comments"/>
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
    props: ["posts", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);
        this.$root.$on("onPostPage", (post, user) => {
          this.post = post;
          this.user = user;
          console.log(post);
          console.log(user);
          this.page = "PostPage";
        });
    },
}
</script>

<style scoped>

</style>
