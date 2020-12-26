<template>
    <div class="our_news">
        <Row style="height: 30px">
            <Col span="24" class="banner_tabs">
                <Tabs v-model="bannerValue" type="card" :animated="true" @on-click="changeNavBanner">
                    <TabPane v-for="item in navBanners" :label="item.banner" :name="item.id + ''" :key="item.id">
                        <Row style="height: 100%">
                            <Col span="12">
                                <Card v-for="item in leftArticles" dis-hover style="margin-bottom: 3px" :title="item.title" :key="item.id">
                                    <p v-html="item.content"></p>
                                </Card>
                            </Col>
                            <Col span="6">
                                <Card v-for="item in leftArticles" dis-hover style="margin-left: 5px; margin-bottom: 3px" :title="item.title" :key="item.id">
                                    {{item.summary}}
                                </Card>
                            </Col>
                            <Col span="6">
                                <Card v-for="item in leftArticles" dis-hover style="margin-left: 5px; margin-bottom: 3px" :title="item.title" :key="item.id">
                                    {{item.summary}}
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
                leftArticles: [],
                centerArticles: [],
                rightArticles: []
            }
        },

        methods: {
            changeNavBanner(name) {
                article.doListArticles(this, {
                    bannerId: name
                }).then(res => {
                    this.leftArticles = res;
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
