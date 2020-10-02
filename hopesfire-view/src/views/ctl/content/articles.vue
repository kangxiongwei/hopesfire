<template>
    <div class="ctl_article">
        <Row type="flex" align="middle">
            <Col :span="22">
                <Form ref="articleQueryForm" :model="articleQueryForm" inline
                      style="height: 34px; line-height: 34px" :label-width="70" label-position="left">
                    <FormItem prop="title" label="文章标题">
                        <Input v-model="articleQueryForm.title"></Input>
                    </FormItem>
                    <FormItem prop="status" label="状态">
                        <Input type="text" v-model="articleQueryForm.status"></Input>
                    </FormItem>
                    <FormItem prop="keyword" label="关键词">
                        <Input type="text" v-model="articleQueryForm.keyword"></Input>
                    </FormItem>
                    <FormItem prop="auditor" label="审核人">
                        <Input type="text" v-model="articleQueryForm.auditor"></Input>
                    </FormItem>
                    <FormItem :label-width="0" style="text-align: center">
                        <Button @click="findArticles()" type="primary" size="small" icon="ios-search">查询</Button>
                        <Button @click="resetQueryForm()" type="primary" size="small" icon="ios-nuclear">重置</Button>
                    </FormItem>
                </Form>
            </Col>
            <Col :span="2" style="text-align: center">
                <Button type="primary" @click="addArticle">添加文章</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="articleTableHeader" :data="articleTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="updateArticle(row)">编辑</Button>
                <Button type="error" size="small" @click="deleteArticle(row)">删除</Button>
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
                        <ViewEditor ref="viewEditor" @on-change="changeEditorText"></ViewEditor>
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
    import ArticleTablePlugin from "../../plugins/ArticleTablePlugin";
    import banner from '../../../api/banner'
    import article from '../../../api/article'

    export default {
        name: "articles",
        components: {
            ViewEditor,
            ArticleTablePlugin
        },
        data() {
            return {
                articleTableHeader: [
                    {
                        title: '展开/收起',
                        type: 'expand',
                        width: 100,
                        render: (h, params) => {
                            return h(ArticleTablePlugin, {
                                props: {
                                    row: params.row
                                }
                            })
                        }
                    },
                    {
                        title: '标题',
                        key: 'title',
                        tooltip: true
                    },
                    {
                        title: '栏目',
                        key: 'bannerId',
                        tooltip: true
                    },
                    {
                        title: '图片',
                        key: 'iconId'
                    },
                    {
                        title: '关键词',
                        key: 'keyword',
                        tooltip: true
                    },
                    {
                        title: '摘要',
                        key: 'summary',
                        tooltip: true
                    },
                    {
                        title: '权重',
                        key: 'weight',
                        width: 80,
                        sortable: true
                    },
                    {
                        title: '状态',
                        key: 'status'
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
                articleQueryForm: {
                    title: null,
                    keyword: null,
                    status: null,
                    auditor: null
                },
                navBanners: []
            }
        },
        methods: {
            findArticles() {
                article.doFindArticle(this, {
                    status: this.articleQueryForm.status,
                    title: this.articleQueryForm.title === '' ? null : this.articleQueryForm.title,
                    keyword: this.articleQueryForm.keyword === '' ? null : this.articleQueryForm.keyword,
                    auditor: this.articleQueryForm.auditor === '' ? null : this.articleQueryForm.auditor
                }).then(res => {
                    this.articleTable = res.records;
                });
            },
            addArticle() {
                this.saveArticleDrawer = true;
            },
            updateArticle(row) {
                this.saveArticleDrawer = true;
                this.articleForm.id = row.id;
                this.articleForm.title = row.title;
                this.articleForm.bannerId = row.bannerId;
                this.articleForm.iconId = row.iconId;
                this.articleTable.keyword = null;
                this.articleForm.keywordTags = [];
                if (row.keyword != null && row.keyword !== '') {
                    let keywords = row.keyword.split("|");
                    keywords.forEach((item) => {
                        this.articleForm.keywordTags.push(item);
                    })
                }
                this.articleForm.summary = row.summary;
                this.changeEditorHtml(row.content);
                this.articleForm.auditor = row.auditor;
            },
            deleteArticle(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '请问您确认要删除这条记录吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        article.doDeleteArticle(this, {
                            id: row.id
                        }).then(() => {
                            this.findArticles();
                        });
                    }
                });
            },
            saveArticle() {
                this.articleForm.content = this.$refs['viewEditor'].getEditorHtml();
                article.doSaveArticle(this, this.articleForm).then(res => {
                    this.saveArticleDrawer = false;
                    this.articleForm.keywordTags = [];
                    this.resetArticle();
                    this.findArticles();
                })
            },
            resetArticle() {
                this.$refs['articleForm'].resetFields();
                this.$refs['viewEditor'].resetEditorHtml();
            },
            resetQueryForm() {
                console.log("重置")
                this.$refs['articleQueryForm'].resetFields();
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
            changeEditorHtml(html) {
                this.$refs['viewEditor'].setEditorHtml(html);
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
