<template>
  <aside>
    <SidebarPost v-for="post in posts" :post="post" :user="users[post.userId]" :key="post.id"/>
  </aside>
</template>

<script>
import SidebarPost from "./SidebarPost";

export default {
  name: "Sidebar",
  components: {
    SidebarPost
  },
  props: ["posts", "users"]
}
</script>

<style scoped>

</style>