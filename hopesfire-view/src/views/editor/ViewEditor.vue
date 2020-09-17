<!--封装一下基于wangEditor的富文本编辑器-->
<template>
    <div ref="editor" >

    </div>
</template>

<script>

    import E from 'wangeditor'

    export default {
        name: "ViewEditor",
        props: {
            content: {

            }
        },
        data() {
            return {
                editorHtml: ''
            }
        },
        mounted() {
            this.editor = new E(this.$refs.editor);
            this.editor.customConfig.onchange = html => {
                this.editorHtml = html;
                let text = this.editor.txt.text()
                this.$emit('on-change', html, text)
            };
            this.editor.create();
        },
        methods: {
            getEditorHtml() {
                return this.editorHtml;
            }
        }
    }
</script>

<style scoped>

</style>
