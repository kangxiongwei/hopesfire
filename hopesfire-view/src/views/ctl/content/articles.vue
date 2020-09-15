<template>
    <div class="ctl_article">
        <Row type="flex" align="middle">
            <Col :span="22">
                <Form inline style="height: 34px; line-height: 34px">
                    <FormItem></FormItem>
                </Form>
            </Col>
            <Col :span="2">
                <Button type="primary" @click="addArticle">添加文章</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="articleTableHeader" :data="articleTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="">编辑</Button>
                <Button type="error" size="small" @click="">删除</Button>
            </template>
        </Table>
        <Drawer title="编辑文章"
                :closable="true"
                :width="50"
                v-model="saveArticleDrawer"
                draggable mask>
            <Form ref="articleForm" :model="articleForm">
                <FormItem prop="header">
                    <Row>
                        <Col span="2"><span class="ctl_article_label">标题：</span></Col>
                        <Col span="16">
                            <Input type="text" v-model="articleForm.header" clearable></Input>
                        </Col>
                        <Col span="4" :offset="2">
                            <Upload ref="uploadArticle"
                                    multiple
                                    action="/ctl/attach/upload"
                                    name="files"
                                    :with-credentials="true">
                                <Button icon="ios-cloud-upload" type="primary">标题图片</Button>
                            </Upload>
                        </Col>
                    </Row>
                </FormItem>
                <FormItem prop="body">
                    <Row>
                        <Col><span class="ctl_article_label">正文：</span></Col>
                    </Row>
                    <Row>
                        <ViewEditor></ViewEditor>
                    </Row>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveArticle()" type="primary">保存</Button>
                    <Button @click="resetArticle()" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>

<script>

    import ViewEditor from "../../editor/ViewEditor";

    export default {
        name: "articles",
        components: {
            ViewEditor
        },
        data() {
            return {
                articleTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '标题',
                        key: 'header'
                    },
                    {
                        title: '创建时间',
                        key: 'createTime'
                    }
                ],
                articleTable: [

                ],
                saveArticleDrawer: false,
                articleForm: {
                    id: null,
                    bannerId: null,
                    header: '',
                    headerImgId: null,
                    body: ''
                }
            }
        },
        computed: {
            editor() {
                return this.$refs.articleQuillEditor.quill
            }
        },
        methods: {
            onEditorChange({html}) {
                this.articleForm.body = html
            },
            addArticle() {
                this.saveArticleDrawer = true;
            },
            saveArticle() {

            },
            resetArticle() {

            }
        }
    }
</script>

<style scoped>
    .ctl_article {
        width: 100%;
        height: 100%;
    }
    .ctl_article_label {
        font-weight: bold;
    }
</style>
