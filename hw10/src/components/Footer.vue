<template>
    <footer>
      <div>
        Users: {{ usersAmount }}
      </div>
      <div>
        Posts: {{ postsAmount }}
      </div>
      <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersAmount", "postsAmount"],
};
</script>

<style scoped>
</style>