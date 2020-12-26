<template>
    <div style="height: 100%; width: 100%">
        <Carousel v-if="this.homeImages.length > 0" loop autoplay :autoplay-speed="3000"
                  :radius-dot="true" arrow="always"
                  style="width: 100%; height: 100%; text-align: center">
            <CarouselItem v-for="item in homeImages" class="home-carousel-item" :key="item.id">
                <img style="height: calc(100vh - 128px); width: 100%" :src="item" alt=""/>
            </CarouselItem>
        </Carousel>
        <img v-if="this.homeImages.length <= 0" style="width: 100%; height: 100%;" src="../../images/login-bg.jpg"/>
    </div>
</template>

<script>
    import home from "../../api/home";

    export default {
        name: "HomePage",
        data() {
            return {
                homeImages: []
            }
        },
        methods: {
            listHomeCarousel() {
                home.doListHomeCarousel(this, {
                    attachType: 1
                }).then(res => {
                    res.forEach(item => {
                        this.homeImages.push(item.thumbnailUrl);
                    })
                })
            }
        },
        mounted() {
            this.listHomeCarousel();
        }
    }
</script>

<style type="less">
    .ivu-carousel-list {
        height: 100%;
        width: 100%;
    }
</style>

<style scoped>

</style>
