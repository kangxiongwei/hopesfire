<template>
    <div class="our_news">
        <Row style="height: 30px">
            <Col span="24" class="banner_tabs">
                <Tabs v-model="bannerValue" type="card" :animated="true" @on-click="changeNavBanner">
                    <TabPane v-for="item in navBanners" :label="item.banner" :name="item.id + ''">
                        <Row style="height: 100%">
                            <Col span="6">
                                <CellGroup>
                                    <div v-for="item in listArticles" style="margin-bottom: 3px">
                                        <Cell extra="详情"
                                              :title="item.title"
                                              :to="'/ctl/article/get/' + item.id"
                                              target="_blank">
                                        </Cell>
                                        <Divider dashed style="margin-top: 0; margin-bottom: 0;"/>
                                    </div>
                                </CellGroup>
                            </Col>
                            <Col span="12">
                                <Card v-for="item in imageArticles" dis-hover style="margin-left: 5px; margin-bottom: 3px" :title="item.title">
                                    <p v-html="item.content"></p>
                                </Card>
                            </Col>
                            <Col span="6">
                                <Card v-for="item in contentArticles" dis-hover style="margin-left: 5px; margin-bottom: 3px" :title="item.title">
                                    <p v-html="item.content"></p>
                                </Card>
                            </Col>
                        </Row>
                    </TabPane>
                </Tabs>
            </Col>
        </Row>
    </div>
</template>

<script>

    import banner from '../../api/banner'
    import article from "../../api/article";

    export default {
        name: "OurNews",

        data() {
            return {
                navBanners: [],
                bannerValue: '',
                listArticles: [],
                imageArticles: [],
                contentArticles: []
            }
        },

        methods: {
            changeNavBanner(name) {
                article.doListArticles(this, {
                    bannerId: name
                }).then(res => {
                    this.selectBannerArticles(res);
                })
            },
            selectBannerArticles(articles) {
                this.listArticles = [];
                this.imageArticles = [];
                this.contentArticles = [];
                articles.forEach(item => {
                    //文章列表
                    if (item.banner.bannerType === 2) {
                        this.listArticles.push(item);
                    }
                    //文章内容
                    if (item.banner.bannerType === 3) {
                        this.imageArticles.push(item);
                    }
                    //图片文章
                    if (item.banner.bannerType === 4) {
                        this.contentArticles.push(item);
                    }
                })
            },
            listBanners() {
                banner.doListBanners(this, {
                    parent: {
                        id: 0
                    },
                    status: 1
                }).then(res => {
                    this.navBanners = res;
                    if (res != null) {
                        this.changeNavBanner(res[0].id);
                    }
                })
            }
        },

        mounted() {
            this.listBanners();
        }

    }
</script>

<style scoped>
    .our_news {
        width: 100%;
        height: 100%;
    }
</style>
