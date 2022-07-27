<template>
    <div class="home-container">
        <div style="width:360px;">
            <el-upload class="upload-demo" ref="upexcelInfo" with-credentials :data="{username: user.name}" drag action="http://127.0.0.1:8888/api/model/upload"
                multiple :auto-upload="false">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text"><em>Chick to choose your dataset</em></div>
            </el-upload>
            <el-button @click="uploadBtn">upload</el-button>
        </div>


    </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    name: 'index',
    data() {
        return { 
   
        }
    },
      computed: {
    ...mapGetters('account', ['user']),
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