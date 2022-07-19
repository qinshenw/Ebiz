<template>
    <div class="home-container">
        <div style="width:360px;">
            <el-upload class="upload-demo" ref="upexcelInfo" drag action="/upload"
                multiple :auto-upload="false">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text"><em>Chick to choose your dataset</em></div>
            </el-upload>
            <el-button @click="uploadBtn">upload</el-button>
        </div>


    </div>
</template>

<script>
export default {
    name: 'index',
    data() {
        return {}
    },
    methods: {
        uploadBtn() {
            this.$refs.upexcelInfo.submit();

       
        },
    },
}
</script>

<style scoped>
</style>