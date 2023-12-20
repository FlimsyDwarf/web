<template>
  <div>
    <Post :user="users[post.userId]" :post="post" :comments="comments"/>
    <div class="form">
      <div class="header">Write Comment</div>
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

      <article v-for="comment in comments" :key="comment.id">
        <div class="title">
          by {{users[comment.userId].login}}
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
  props: ["post", "users", "comments"],
  data: function () {
    return {
      error: "",
      text: "",
    }
  },
  components: {Post},
  computed: {
  },
  methods: {
    onWriteComment: function () {
      this.error = "";
      this.$root.$emit("onWriteComment", this.post.id, this.text);
      this.text = ""
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