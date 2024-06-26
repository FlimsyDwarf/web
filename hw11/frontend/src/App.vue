<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts" :users="users" :comments="comments"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            posts: [],
            users: [],
            comments: [],
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
          this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }
        this.$root.$emit("onUpdateData")
        setInterval(() => {
          this.$root.$emit("onUpdateData")
        }, 1000);
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }

            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, name, password) => {
          if (login === "") {
            this.$root.$emit("onRegisterValidationError", "Login is required");
          }
          else if (name === "") {
            this.$root.$emit("onRegisterValidationError", "Name is required");
          }
          else if (password === "") {
            this.$root.$emit("onRegisterValidationError", "Password is required");
          } else {
            axios.post("/api/1/register", {
              login, name, password
            }).then(response => {
              localStorage.setItem("jwt", response.data);
              this.$root.$emit("onUpdateUsers");
              this.$root.$emit("onJwt", response.data);
            }).catch(error => {
              this.$root.$emit("onRegisterValidationError", error.response.data);
            });
          }
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

        this.$root.$on("onWritePost", (title, text) => {
          if (this.user === null) {
              this.$root.$emit("onWritePostValidationError", "Access denied");
          } else if (title === "") {
              this.$root.$emit("onWritePostValidationError", "Title is required");
          } else if (text === "") {
              this.$root.$emit("onWritePostValidationError", "Text is required");
          } else {
              const jwt = localStorage.getItem("jwt")
              axios.post("/api/1/posts/writePost", {
                title, text
              }, {
                params: {
                  jwt
                }
              }).then(() => {
                this.$root.$emit("onUpdatePosts");
              }).catch(error => {
                this.$root.$emit("onWritePostValidationError", error.response.data);
              });
          }
        });

      this.$root.$on("onWriteComment", (post, text) => {
        if (this.user !== null) {
          if (! ((post.id - 1) in this.posts)) {
            this.$root.$emit("onWriteCommentValidationError", "Post doesn't exist");
          } else if (!text || text.trim().length === 0) {
            this.$root.$emit("onWriteCommentValidationError", "Text can't be blank");
          } else {
            const jwt = localStorage.getItem("jwt")
            const postId = post.id;
            axios.post("/api/1/posts/writeComment", {
              text, post
            }, {
              params: {
                jwt, postId
              }
            }).then(() => {
              this.$root.$emit("onUpdateComments");
            }).catch(error => {
              this.$root.$emit("onWriteCommentValidationError", error.response.data);
            });
          }
        } else {
          this.$root.$emit("onWriteCommentValidationError", "No access");
        }
      });

      this.$root.$on("onUpdateUsers", () => {
        axios.get("/api/1/users").then(response => {
          this.users = response.data;
        });
      });

      this.$root.$on("onUpdatePosts", () => {
        axios.get("/api/1/posts").then(response => {
          this.posts = response.data;
        });
      });

      this.$root.$on("onUpdateComments", () => {
        axios.get("/api/1/comments").then(response => {
          this.comments = response.data;
        }).catch(error => {
          this.$root.$emit("onUpdateCommentsError", error.response.data);
        });
      });
      this.$root.$on("onUpdateData", () => {
        this.$root.$emit("onUpdatePosts")
        this.$root.$emit("onUpdateUsers")
        this.$root.$emit("onUpdateComments")
      });

    }
}
</script>

<style>
#app {

}
</style>
