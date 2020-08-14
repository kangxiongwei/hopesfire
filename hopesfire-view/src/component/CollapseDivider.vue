<template>
    <Divider :orientation="orientation" style="margin-top: 5px; margin-bottom: 5px;">
        <div @click="doClickDividerIcon">
            <Icon :type="dividerIconType"/>
            {{title}}
        </div>
    </Divider>
</template>

<script>
    export default {
        name: "CollapseDivider",
        props: [
            'title',
            'orientation',
            'collapsed'
        ],
        data() {
            return {
                dividerIconType: 'ios-arrow-forward',
                dividerIconOpened: false
            }
        },
        methods: {
            doClickDividerIcon() {
                this.dividerIconOpened = !this.dividerIconOpened;
                this.doChangeIconType();
            },
            doChangeIconType() {
                this.dividerIconType = this.dividerIconOpened === true ? 'ios-arrow-down' : 'ios-arrow-forward'
            }
        },
        mounted() {
            this.orientation = this.orientation === null ? 'center' : this.orientation;
            this.dividerIconOpened = this.collapsed;
            this.doChangeIconType();
        }
    }
</script>

<style scoped>

</style>
