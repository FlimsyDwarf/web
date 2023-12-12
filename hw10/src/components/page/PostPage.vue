<template>
  <div>
    <Post :user="user" :post="post" :comments="comments"/>
    <div class="form">
      <div class="header">Write Post</div>
      <div class="body">
        <form @submit.prevent="onWriteComment">
          <div class="field">
            <div class="name">
              <label for="text">Text</label>
            </div>
            <div class="value">
              <textarea id="text" name="text" v-model="text" />
            </div>
          </div>
          <div class="field error">{{ error }}</div>
          <div class="button-field">
            <input type="submit" value="Write">
          </div>
        </form>
      </div>
    </div>

      <article v-for="comment in filteredComments" :key="comment.id">
        <div class="title">
          by {{user.login}}
        </div>
        <div class="body">
          {{comment.text}}
        </div>
      </article>
  </div>
</template>

<script>
import Post from "@/components/page/Post.vue";
export default {
  name: "PostPage",
  props: ["post", "user", "comments", "userId"],
  data: function () {
    return {
      error: "",
      text: "",
    }
  },
  components: {Post},
  computed: {
    filteredComments: function () {
      return Object.values(this.comments).filter(comment => comment.postId === this.post.id);
    },

  },
  methods: {
    onWriteComment: function () {
      this.error = "";
      this.$root.$emit("onWriteComment", this.post.id, this.text);
    },
  },
  beforeMount() {
    this.$root.$on("onWriteCommentValidationError", error => this.error = error);
  }
}
</script>

<style scoped>
  .form {
    margin-bottom: 1rem;
  }
  textarea {
    resize: none;
  }
</style>