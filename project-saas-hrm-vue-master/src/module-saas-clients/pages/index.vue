<template>
  <div class="dashboard-container">
    <div class="app-container">
      <el-card shadow="never">
        <!--elementui的table组件
          data：数据模型
         -->
        <el-table :data="dataList" border style="width: 100%">
          <!--el-table-column : 构造表格中的每一列
            prop： 数组中每个元素对象的属性名
          -->
          <el-table-column align="center" fixed type="index" label="序号" width="50"></el-table-column>
          <el-table-column align="center" fixed prop="name" label="企业名称" width="250"></el-table-column>
          <el-table-column align="center" prop="version" label="当前版本" width="80"></el-table-column>
          <el-table-column align="center" prop="legalRepresentative" label="联系人" width="100"></el-table-column>
          <el-table-column align="center" prop="companyPhone" label="联系电话" width="120"></el-table-column>
          <el-table-column align="center" prop="companyAddress" label="所在地区" width="350"></el-table-column>
          <el-table-column align="center" prop="renewalDate" :formatter="formatTime" label="申请时间"
                           width="200"></el-table-column>
          <el-table-column align="center" prop="expirationDate" :formatter="formatTime" label="截至时间"
                           width="200"></el-table-column>
          <el-table-column align="center" prop="balance" label="当前余额" width="120"></el-table-column>
          <el-table-column align="center" prop="mailbox" label="邮箱" width="200"></el-table-column>
          <el-table-column align="center" prop="remarks" label="备注" width="120"></el-table-column>
          <el-table-column align="center" fixed="right" label="状态" width="100">
            <!--scope:传递当前行的所有数据 -->
            <template slot-scope="scope">
              <!--开关组件
                  active-value：激活的数据值
                  active-color：激活的颜色
                  inactive-value：未激活
                  inactive-color：未激活的颜色
               -->
              <el-switch
                v-model="scope.row.state"
                inactive-value="0"
                active-value="1"
                disabled
                active-color="#13ce66"
                inactive-color="#ff4949">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <router-link :to="'/saas-clients/details/'+scope.row.id">
                <el-button type="text">查看</el-button>
              </router-link>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
import {list} from '@/api/base/saasClient'

export default {
  name: 'saas-clients-index',
  data() {
    return {
      dataList: []
    }
  },
  methods: {
    getList() {
      // 调用API发起请求
      // res=响应数据
      list().then(res => {
        this.dataList = res.data.data
      })
    },
    // 时间格式化
    formatTime(row, column) {
      let data = row[column.property]
      let dtime = new Date(data)
      const year = dtime.getFullYear()
      let month = dtime.getMonth() + 1
      if (month < 10) {
        month = '0' + month
      }
      let day = dtime.getDate()
      if (day < 10) {
        day = '0' + day
      }
      let hour = dtime.getHours()
      if (hour < 10) {
        hour = '0' + hour
      }
      let minute = dtime.getMinutes()
      if (minute < 10) {
        minute = '0' + minute
      }
      let second = dtime.getSeconds()
      if (second < 10) {
        second = '0' + second
      }
      return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second
    }
  },
  // 创建完毕状态
  created() {
    this.getList()
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
