<template>
  <div>
    <Post :post="truePost"/>
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

    <article v-for="comment in truePost.comments" :key="comment.id">
      <div class="title">
        by {{comment.user.login}}
      </div>
      <div class="body">
        {{comment.text}}
      </div>
    </article>
  </div>
</template>

<script>
import Post from "@/components/page/Post.vue";
import axios from "axios";
export default {
  name: "PostPage",
  props: ["post"],
  data: function () {
    return {
      error: "",
      text: "",
      truePost: ""
    }
  },
  components: {Post},
  computed: {
  },
  methods: {
    onWriteComment: function () {
      this.error = "";
      this.$root.$emit("onWriteComment", this.post, this.text.trim());
      if (this.error === "") {
        this.text = "";
        this.title = "";
      }
    },
  },
  beforeMount() {
    this.$root.$on("onWriteCommentValidationError", error => this.error = error);

    const postId = this.post.id;
    // this.$root.$emit("onUpdatePosts")
    this.truePost = this.post;
    setInterval(() => {
      axios.get("/api/1/post", {params: {
          postId
        }}).then(response => {
        this.truePost = response.data;
      });
    }, 2000);
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