<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <PostPage v-if="page === 'PostPage'" :post="post"/>
            <WritePost v-if="page === 'WritePost'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "@/components/page/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "@/components/page/Users.vue";
import WritePost from "@/components/page/WritePost.vue";
import PostPage from "@/components/page/PostPage.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: null,
        }
    },
    components: {
      PostPage,
      WritePost,
      Users,
        Register,
        Enter,
        Index,
        Sidebar
    },
    props: ["posts", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page);

        this.$root.$on("onPostPage", (post) => {
          this.post = post;
          console.log(this.post)
          this.page = "PostPage";
        });
    }
}
</script>

<style scoped>

</style>
