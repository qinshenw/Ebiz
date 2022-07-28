<template>
  <common-layout>
    <div class="top">
      <div class="header">
        <img alt="logo" class="logo" src="@/assets/img/logo.png"/>
        <span class="title">{{ systemName }}</span>
      </div>
      <div class="desc">{{ systemDescribe }}</div>
    </div>
    <div class="login">
      <a-form @submit="onSubmit" :form="form">
        <a-form-item>
          <a-input autocomplete="autocomplete" size="large" placeholder="Login Username"
              v-decorator="['username', {rules: [{ required: true, message: 'Please enter your username', whitespace: true}]}]">
            <a-icon slot="prefix" type="user"/>
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-input size="large" placeholder="Login password" autocomplete="autocomplete" type="password"
              v-decorator="['password', {rules: [{ required: true, message: 'Please enter your password', whitespace: true}]}]">
            <a-icon slot="prefix" type="lock"/>
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button :loading="logging" style="width: 100%;margin-top: 24px" size="large" htmlType="submit"
                    type="primary">Login
          </a-button>
        </a-form-item>
        <div>
          <router-link style="float: right" to="/register" >Register Account</router-link>
        </div>
      </a-form>
    </div>
  </common-layout>
</template>

<script>
  import CommonLayout from '@/layouts/CommonLayout'
  import {getRoutesConfig, login} from '@/services/user'
  import {setAuthorization} from '@/utils/request'
  import {loadRoutes} from '@/utils/routerUtil'
  import {mapMutations,mapActions} from 'vuex'
  import {systemDescribe, systemName} from "@/config"

  export default {
    name: 'Login',
    components: {CommonLayout},
    data() {
      return {
        systemDescribe,
        systemName,
        logging: false,
        form: this.$form.createForm(this)
      }
    },
    computed: {},
    methods: {
      ...mapMutations('account', ['setUser', 'setPermissions', 'setRoles']),

      onSubmit(e) {
        e.preventDefault()
        this.form.validateFields((err) => {
          if (!err) {
            this.logging = true
            const name = this.form.getFieldValue('username')
            const password = this.form.getFieldValue('password')
            login(name, password).then(this.afterLogin)
              .catch(() => {
                this.logging = false
              });
          }
        })
      },
      afterLogin(res) {
        this.logging = false
        const {user, permissions, roles} = res.data
        this.setUser(user)
        this.setPermissions(permissions)
        this.setRoles(roles)
        setAuthorization(res.data)
        // 获取路由配置
        this.$router.push('/notice/index')
        this.$messasuccess("Successfully Login, welcome back!", 3)
      },
      changeLan(lang) {
        console.log(this.$t.locale)
      },
    }
  }
</script>

<style lang="less" scoped>
  .common-layout {

    .top {
      text-align: center;

      .header {
        height: 44px;
        line-height: 44px;

        a {
          text-decoration: none;
        }

        .logo {
          height: 44px;
          vertical-align: top;
          margin-right: 16px;
        }

        .title {
          font-size: 33px;
          color: @title-color;
          font-family: 'Myriad Pro', 'Helvetica Neue', Arial, Helvetica, sans-serif;
          font-weight: 600;
          position: relative;
          top: 2px;
        }

      }

      .desc {
        font-size: 14px;
        color: @text-color-second;
        margin-top: 12px;
        margin-bottom: 40px;
      }

    }

    .login {
      width: 368px;
      margin: 0 auto;

      @media screen and (max-width: 576px) {
        width: 95%;
      }

      @media screen and (max-width: 320px) {
        .captcha-button {
          font-size: 14px;
        }
      }

      .icon {
        font-size: 24px;
        color: @text-color-second;
        margin-left: 16px;
        vertical-align: middle;
        cursor: pointer;
        transition: color 0.3s;

        & :hover {
          color: @primary-color;
        }

      }
    }
  }
</style>