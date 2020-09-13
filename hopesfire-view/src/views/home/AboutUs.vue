<template>
    <div class="about_us">
        <Card dis-hover style="width:480px; margin: 0 auto; height: 100%">
            <p slot="title" style="text-align: left">
                <Icon type="md-at"></Icon>
                关于我们
            </p>
            <List>
                <ListItem>
                    <ListItemMeta style="text-align: left" title="项目介绍" description="该网站主要用于学习交流，基于Java+VUE+IViewUI构建而成"/>
                </ListItem>
                <ListItem>
                    <ListItemMeta style="text-align: left" title="源码" description="https://github.com/kangxiongwei/hopesfire"/>
                </ListItem>
                <ListItem>
                    <ListItemMeta style="text-align: left" title="联系我们">
                        <p slot="description">
                            微信：kxw597527229
                        </p>
                    </ListItemMeta>
                    <template slot="extra">
                        <img src="http://hopesfire.cn/attach/avatar/thumbnails/thumbnail_200_200_1600010495203_wxer.jpeg" alt=""/>
                    </template>
                </ListItem>
            </List>
        </Card>
    </div>
</template>

<script>
    export default {
        name: "AboutUs"
    }
</script>

<style scoped>
    .about_us {
        width: 100%;
        height: 100%;
        text-align: center;
        background: url('../../images/login-bg.jpg') no-repeat fixed;
        background-size: 100% 100%;
    }
</style>
