<template>
  <article>
    <div class="title">
      <a href="#" @click.prevent="onPostPage">{{post.title}}</a>
    </div>
    <div class="information">By {{user.login}}</div>
    <div class="body">




      {{

        post.text

      }}
    </div>
    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
        11.12.2023
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a href="#" @click.prevent="onPostPage">{{commentsAmount}}</a>
      </div>
    </div>
  </article>
</template>

<script>

export default {
  name: "Post",
  props: ["post", "user", "comments"],
  computed: {
    commentsAmount: function () {
      // return this.filteredComments.length
      // console.log(this.comments);
      // console.log(this.comments.length);
      console.log(this.post.text);

      return  Object.keys(this.comments).length
    },
    filteredComments: function () {
      return Object.values(this.comments).filter(comment => comment.postId === this.post.id);
    }
  },
  methods: {
    onPostPage: function () {
      this.$root.$emit("onPostPage", this.post);
    }
  }
}
</script>

<style scoped>
</style>