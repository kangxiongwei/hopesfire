<template>
    <div>
        <Row class="expand-row">
            <Col span="8">
                <span class="expand-key">审核状态: </span>
                <span class="expand-value">{{ row.status }}</span>
            </Col>
            <Col span="8">
                <span class="expand-key">审核人: </span>
                <span class="expand-value">{{ row.auditor }}</span>
            </Col>
            <Col span="8">
                <span class="expand-key">审核意见: </span>
                <span class="expand-value">{{ row.auditReport }}</span>
            </Col>
        </Row>
        <Row class="expand-row">
            <Col>
                <span class="expand-key">文章内容: </span>
                <span class="expand-value" v-html="row.content"></span>
            </Col>
        </Row>
    </div>
</template>

<script>
    export default {
        name: "ArticleTablePlugin",
        props: {
            row: Object
        }
    }
</script>

<style scoped>
    .expand-row {
        margin-bottom: 16px;
    }

    .expand-key {
        font-weight: bold;
    }
</style>