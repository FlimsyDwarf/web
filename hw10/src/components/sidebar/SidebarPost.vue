<template>
  <section>
    <div class="header">
      {{ post.title }}
    </div>
    <div class="body">
      {{ post.text }}
    </div>
    <div class="footer">
      <a href="#" @click.prevent="onPostPage">View all</a>
    </div>
  </section>
</template>

<script>
export default {
  name: "SidebarPost",
  props: ["post", "user"],
  methods: {
    onPostPage: function () {
      this.$root.$emit("onPostPage", this.post, this.user);
    }
  }
}
</script>

<style scoped>
section {
  margin-bottom: 1rem;
}
</style>