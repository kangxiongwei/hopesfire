<style scoped>
    .content {

    }
</style>
<template>
    <div class="content">
        <Table stripe border :columns="userTableHeader" :data="userTable"></Table>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                userTable: [],
                userTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '姓名',
                        key: 'username'
                    },
                    {
                        title: '性别',
                        key: 'sex'
                    }
                ]
            }
        },
        mounted() {
            this.listUsers();
        },
        methods: {
            listUsers() {
                this.$post('/ctl/auth/user/find', {"page": 1, "pageSize": 10})
                    .then(response => {
                        let data = response.data;
                        if (data.code === 200) {
                            this.userTable = data.data;
                        }
                    });
            }
        }
    }
</script>
