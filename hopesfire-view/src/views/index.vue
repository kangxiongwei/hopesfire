<style scoped>
    .index {
        width: 100%;
        height: 100%;
        background: #2d2d2d;
    }

    .header_nav {
        margin: 10px 0 0 10px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
    }

    .header_nav_hover {
        margin: 10px 0 0 10px;
        border-top: 1px solid white;
        border-bottom: 1px solid white;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
    }

    .main {
        width: 100%;
        height: 100%;
        text-align: center;
    }
</style>
<template>
    <div class="index">
        <Row type="flex" justify="start" align="middle" :gutter="16">
            <Col span="2" :class="homeNavClass">
                <div @mouseover="selectHeaderNav('home')" @mouseout="unSelectHeaderNav('home')">
                    首页
                </div>
            </Col>
            <Col span="2" :class="ctlNavClass" :offset="1">
                <div @mouseover="selectHeaderNav('ctl')" @mouseout="unSelectHeaderNav('ctl')" @click="selectCtlPage">
                    控制台
                </div>
            </Col>
        </Row>
        <br>
        <Row type="flex" justify="center" style="width: 100%; height: auto;">
            <Col span="24" class="main">正文</Col>
        </Row>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                homeNavClass: 'header_nav',
                ctlNavClass: 'header_nav'
            }
        },
        methods: {
            handleStart() {
                this.$Modal.info({
                    title: 'Bravo',
                    content: 'Now, enjoy the convenience of View UI.'
                });
            },
            selectHeaderNav(type) {
                if (type === 'home') {
                    this.homeNavClass = 'header_nav_hover';
                } else if (type === 'ctl') {
                    this.ctlNavClass = 'header_nav_hover';
                }
            },
            unSelectHeaderNav(type) {
                if (type === 'home') {
                    this.homeNavClass = 'header_nav';
                } else if (type === 'ctl') {
                    this.ctlNavClass = 'header_nav';
                }
            },
            selectCtlPage() {
                this.$router.push({path: '/ctl'});
            }
        }
    }
</script>
