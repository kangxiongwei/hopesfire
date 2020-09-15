<!--封装一下基于wangEditor的富文本编辑器-->
<template>
    <div ref="editor">

    </div>
</template>

<script>

    import E from 'wangeditor'

    export default {
        name: "ViewEditor",
        data() {
            return {
                editorHtml: ''
            }
        },
        mounted() {
            const editor = new E(this.$refs.editor);
            editor.create();
        }
    }
</script>

<style scoped>

</style>
