import {post} from '../libs/axios'

const HOME_CAROUSEL_URL = '/ctl/home/carousel'

function doListHomeCarousel(vuex, params) {
    return post(HOME_CAROUSEL_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            return null;
        }
    })
}

export default {doListHomeCarousel}
