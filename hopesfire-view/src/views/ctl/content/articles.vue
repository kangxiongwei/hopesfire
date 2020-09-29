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
        <Drawer title="编辑文章" v-model="saveArticleDrawer" :closable="true" :width="50" draggable mask>
            <Form ref="articleForm" :model="articleForm" :label-width="70" label-position="left">
                <FormItem prop="header" label="文章标题">
                    <Input type="text" v-model="articleForm.title" clearable></Input>
                </FormItem>
                <FormItem label="所属栏目">
                    <Select v-model="articleForm.bannerId">
                        <Option v-for="item in navBanners" :value="item.id">{{item.banner}}</Option>
                    </Select>
                </FormItem>
                <FormItem label="标题图片">
                    <Row>
                        <Upload ref="uploadArticle" multiple action="/ctl/attach/upload"
                                name="files"
                                :data="attachAttributes"
                                :with-credentials="true"
                                :show-upload-list="false"
                                :before-upload="beforeUploadAttach"
                                :on-success="uploadAttachSuccess"
                                :on-error="uploadAttachError">
                            <Button icon="ios-cloud-upload" type="primary">标题图片</Button>
                            <Tooltip content="非必填，配合图片栏目会有效展示图片" placement="right-start">
                                <Icon type="md-help-circle" size="14"></Icon>
                            </Tooltip>
                        </Upload>
                    </Row>
                </FormItem>
                <FormItem label="关键词">
                    <Input type="text" v-model="articleForm.keyword" placeholder="按Enter或Tab确认关键词"
                           @on-blur="addKeywordTag"
                           @on-enter="addKeywordTag">
                    </Input>
                    <Tag v-for="(item, index) in articleForm.keywordTags" :name="index"
                         closable @on-close="deleteKeywordTag">{{item}}
                    </Tag>
                </FormItem>
                <FormItem label="文章摘要">
                    <Input type="text" v-model="articleForm.summary"></Input>
                </FormItem>
                <FormItem prop="content" style="margin-left: -70px">
                    <Row>
                        <Col><span class="ctl_article_label">文章内容</span></Col>
                    </Row>
                    <Row>
                        <ViewEditor v-model:content="this.articleForm.content"
                                    @on-change="changeEditorText"></ViewEditor>
                    </Row>
                </FormItem>
                <FormItem label="审核人">
                    <Input type="text" v-model="articleForm.auditor"></Input>
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
    import banner from '../../../api/banner'
    import article from '../../../api/article'

    export default {
        name: "articles",
        components: {
            ViewEditor
        },
        data() {
            return {
                articleTableHeader: [
                    {
                        title: '标题',
                        key: 'title'
                    },
                    {
                        title: '图片',
                        key: 'iconId'
                    },
                    {
                        title: '栏目',
                        key: 'bannerId'
                    },
                    {
                        title: '关键词',
                        key: 'keyword'
                    },
                    {
                        title: '摘要',
                        key: 'summary'
                    },
                    {
                        title: '内容',
                        key: 'content',
                        render: (h, params) => {
                            return h('div',
                                {
                                    domProps: {
                                        innerHTML: params.row.content
                                    }
                                }
                            )
                        }
                    },
                    {
                        title: '权重',
                        key: 'weight'
                    },
                    {
                        title: '状态',
                        key: 'status'
                    },
                    {
                        title: '审核意见',
                        key: 'auditReport'
                    },
                    {
                        title: '审核人',
                        key: 'auditor'
                    },
                    {
                        title: '发布时间',
                        key: 'publishTime'
                    },
                    {
                        title: '创建时间',
                        key: 'createTime'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        fixed: 'right',
                        align: 'center',
                        width: 150
                    }
                ],
                articleTable: [],
                saveArticleDrawer: false,
                articleForm: {
                    id: null,
                    bannerId: null,
                    title: '',
                    iconId: null,
                    keyword: '',
                    summary: '',
                    content: '',
                    auditor: '',
                    keywordTags: []
                },
                attachAttributes: {
                    attachType: 3
                },
                navBanners: []
            }
        },
        methods: {
            findArticles() {
                article.doFindArticle(this, {}).then(res => {
                    this.articleTable = res.records;
                });
            },
            addArticle() {
                this.saveArticleDrawer = true;
            },
            saveArticle() {
                article.doSaveArticle(this, this.articleForm).then(res => {
                    this.saveArticleDrawer = false;
                    this.articleForm.keywordTags = [];
                    this.resetArticle();
                })
            },
            resetArticle() {
                this.$refs['articleForm'].resetFields();
            },
            addKeywordTag() {
                let keyword = this.articleForm.keyword;
                this.articleForm.keyword = '';
                if (keyword === '' || keyword == null) {
                    return;
                }
                this.articleForm.keywordTags.push(keyword);
            },
            deleteKeywordTag(event, name) {
                this.articleForm.keywordTags.splice(name, 1);
            },
            changeEditorText(html, text) {
                this.articleForm.content = html;
            },
            listBanners() {
                banner.doListBanners(this, {
                    status: 1,
                    parent: {
                        id: 0
                    }
                }).then(res => {
                    this.navBanners = res;
                })
            },
            beforeUploadAttach(files) {

            },
            uploadAttachSuccess(response) {
                if (response.code === 200) {
                    this.$Message.info("上传成功！");
                    this.articleForm.iconId = response.data;
                } else {
                    this.$Message.error("上传失败，请联系管理员！");
                }
            },
            uploadAttachError(error) {
                this.$Message.error("上传失败，请联系管理员！");
            }
        },
        mounted() {
            this.findArticles();
            this.listBanners();
        }
    }
</script>

<style scoped>
    .ctl_article {
        width: 100%;
        height: 100%;
    }

</style>
