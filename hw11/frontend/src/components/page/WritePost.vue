<template>
    <div class="form">
        <div class="header">Write Post</div>
        <div class="body">
            <form id="postForm" @submit.prevent="onWritePost">
                <div class="field">
                    <div class="name">
                        <label for="title">Title:</label>
                    </div>
                    <div class="value">
                        <input type="text" id="title" name="title" v-model="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text:</label>
                    </div>
                    <div class="value">
                        <textarea type="text" id="text" name="text" v-model="text"></textarea>
                    </div>
                </div>
                <div class="error">{{ error }}</div>
                <div class="button-field">
                    <input type="submit" value="Write">
                </div>
            </form>
        </div>
    </div>
</template>

<script>

export default {
    name: "WritePost",
    data: function () {
        return {
            title: "",
            text: "",
            error: ""
        }
    },
    methods: {
        onWritePost: function () {
            this.error = "";
            this.$root.$emit("onWritePost", this.title.trim(), this.text)
            if (this.error === "") {
                this.text = "";
                this.title = "";
            }
        }
    },
    beforeMount() {
        this.$root.$on("onWritePostValidationError", error => this.error = error);
    }
}
</script>

<style scoped>
textarea {
  resize: none;
}
</style>