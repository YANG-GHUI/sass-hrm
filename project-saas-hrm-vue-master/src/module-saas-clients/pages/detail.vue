<template>
  <div class="dashboard-container">
    <div class="app-container">
      <el-card shadow="never">
        <el-tabs v-model="activeName">
          <el-tab-pane label="企业信息" name="first">
            <!--form表单
                model ： 双向绑定的数据对象
            -->
            <el-form label-position="left" ref="form" :model="formData" label-width="80px">
              <el-form-item label="企业名称">
                <el-input v-model="formData.name" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="公司地区">
                <el-input v-model="formData.companyAddress" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="公司地址">
                <el-input v-model="formData.companyAddress" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="审核状态">
                <el-input v-if="formData.auditState == 1" value="已通过" style="width:300px" disabled></el-input>
                <el-input v-else style="width:300px" value="未通过" disabled></el-input>
              </el-form-item>
              <el-form-item label="营业执照">
                <el-image>
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
              </el-form-item>
              <el-form-item label="法人代表">
                <el-input v-model="formData.legalRepresentative" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="公司电话">
                <el-input v-model="formData.companyPhone" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="formData.mailbox" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="公司规模">
                <el-input v-model="formData.companySize" style="width:300px" disabled></el-input>
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="formData.remarks" style="width:300px"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="audit">审核</el-button>
                <el-button @click="refuse">拒绝</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="账户信息" name="second">账户信息</el-tab-pane>
          <el-tab-pane label="交易记录" name="third">交易记录</el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
  </div>
</template>

<script>
import {detail, auditCompany} from '@/api/base/saasClient'

export default {
  name: 'saas-clients-detail',
  data() {
    return {
      activeName: 'first',
      formData: {}
    }
  },
  methods: {
    detail(id) {
      detail({id: id}).then(res => {
        this.formData = res.data.data
        console.log(id)
        console.log(this.formData)
      })
    },
    audit() {
      this.formData.auditState = '1'
      auditCompany(this.formData).then(res => {
        this.$message({
          type: 'success',
          message: '审核通过！'
        })
      })
      this.$router.push({path: '/saas-clients/index'})
    },
    refuse() {
      this.formData.auditState = '0'
      auditCompany(this.formData).then(res => {
        this.$message({
          type: 'warning',
          message: '已拒绝！'
        })
      })
      this.$router.push({path: '/saas-clients/index'})
    }
  },
  // 创建完毕状态
  created() {
    var id = this.$route.params.id
    this.detail(id);
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.alert {
  margin: 10px 0px 0px 0px;
}

.pagination {
  margin-top: 10px;
  text-align: right;
}
</style>
