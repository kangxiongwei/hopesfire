<template>
    <div class="our_news">
        <Row style="height: 30px">
            <Col span="24" class="banner_tabs">
                <Tabs v-model="bannerValue" type="card" :animated="true" @on-click="changeNavBanner">
                    <TabPane v-for="item in navBanners" :label="item.banner" :name="item.id">
                        <Row style="height: 100%">
                            <Col span="12">
                                <Card v-for="item in leftArticles"
                                      dis-hover
                                      style="margin-bottom: 3px"
                                      :title="item.title">
                                    {{item.article}}
                                </Card>
                            </Col>
                            <Col span="6">
                                <Card v-for="item in leftArticles"
                                      dis-hover
                                      style="margin-left: 5px; margin-bottom: 3px"
                                      :title="item.title">
                                    {{item.article}}
                                </Card>
                            </Col>
                            <Col span="6">
                                <Card v-for="item in leftArticles"
                                      dis-hover
                                      style="margin-left: 5px; margin-bottom: 3px"
                                      :title="item.title">
                                    {{item.article}}
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
    export default {
        name: "OurNews",

        data() {
            return {
                navBanners: [
                    {
                        id: "1",
                        banner: '热门资讯'
                    },
                    {
                        id: "2",
                        banner: '旅游攻略'
                    },
                    {
                        id: "3",
                        banner: '减肥瘦身'
                    }
                ],
                bannerValue: '',
                leftArticles: [
                    {
                        title: '厦门',
                        article: '厦门， 简称“厦”或“鹭”，别称鹭岛， 是福建省副省级市、计划单列市，国务院批复确定的中国经济特区，东南沿海重要的中心城市、港口及风景旅游城市 [1]  。截至2019年，全市下辖6个区，总面积1700.61平方公里，常住人口429万人，户籍人口261.10万人，常住外来人口达221万人。'
                    },
                    {
                        title: '广州',
                        article: '广州，简称“穗”，别称羊城、花城，是广东省省会、副省级市、国家中心城市、超大城市，国务院批复确定的中国重要的中心城市、国际商贸中心和综合交通枢纽 [1]  。截至2018年，全市下辖11个区，总面积7434平方千米，建成区面积1249.11平方千米，常住人口1530.59万人，城镇化率86.46%。'
                    },
                    {
                        title: '天津',
                        article: '天津，简称“津”，别称津沽、津门，是中华人民共和国省级行政区、直辖市、国家中心城市、超大城市，国务院批复确定的环渤海地区的经济中心 [1]  。截至2018年，全市下辖16个区，总面积11966.45平方千米，2019年末，常住人口1561.83万人，城镇人口1303.82万人，城镇化率83.48%。'
                    }
                ],
                centerArticles: [],
                rightArticles: []
            }
        },

        methods: {
            changeNavBanner(name) {
                console.log("选中的导航：" + name);
            }
        }

    }
</script>

<style scoped>
    .our_news {
        width: 100%;
        height: 100%;
    }
</style>
