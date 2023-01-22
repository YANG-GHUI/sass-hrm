<template>
  <div class="dashboard-container">
    <div class="app-container">
      <el-card shadow="never">
        <div class='organization-index'>
          <div class='organization-index-top'>
            <div class='main-top-title'>
              <el-tabs v-model="activeName">
                <el-tab-pane label="组织结构" name="first"></el-tab-pane>
                <div class="el-tabs-report">
                  <a class="el-button el-button--primary el-button--mini" title="导出">导入</a>
                  <a class="el-button el-button--primary el-button--mini" title="导出">导出</a>
                </div>
              </el-tabs>
            </div>
          </div>
          <div style="overflow: scroll;white-space:nowrap" class="treBox">
            <div class="treeCon clearfix">
                    <span>
                      <i class="fa fa-university" aria-hidden="true"></i>
                      <span><strong>{{ departData.companyName }}</strong></span>
                    </span>
              <div class="fr">
                      <span class="treeRinfo">
                        <div class="treeRinfo">
                          <span>{{ departData.companyManager }}</span>
                          <span>在职  <em class="colGreen" title="在职人数">---</em>&nbsp;&nbsp;(<em class="colGreen"
                                                                                               title="正式员工">---</em>&nbsp;/&nbsp;<em
                            class="colRed" title="非正式员工">---</em>)</span>
                        </div>
                        <div class="treeRinfo">
                          <el-dropdown class="item">
                            <span class="el-dropdown-link">
                              操作<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>
                                  <el-button type="text" @click="handleAdd()">添加子部门</el-button>
                                </el-dropdown-item>
                              <el-dropdown-item>
                                <el-button type="text" @click="handleList()">查看待分配员工</el-button>
                              </el-dropdown-item>
                            </el-dropdown-menu>
                          </el-dropdown>
                        </div>
                      </span>
              </div>
            </div>
            <!--
              构造树形列表
                叶子 <i class="fa fa-male"></i>
                非叶子
                  展开 <i class="fa fa-minus-square-o">
                  闭合 <i class="fa fa-plus-square-o">
              <div class="generalClass" slot-scope="{node,data}" style="width:99%">
            -->
            <div class="block">
              <el-tree
                :props="{label: 'name'}"
                :data="depts"
                node-key="id"
                default-expand-all
              >
                <div class="generalClass" slot-scope="{node,data}" style="width:99%">
                  <span>
                    <i v-if="node.isLeaf" class="fa fa-male"></i>
                    <i v-else :class="node.expanded ? 'fa fa-minus-square-o':'fa fa-plus-square-o'"></i>
                    <span>{{ node.label }}</span>
                  </span>
                  <div class="fr">
                    <span class="treeRinfo">
                        <div class="treeRinfo">
                          <span>{{ data.manager }}</span>
                          <span>在职  <em class="colGreen" title="在职人数">---</em>&nbsp;&nbsp;(
                            <em class="colGreen" title="正式员工">---</em>&nbsp;/&nbsp;
                            <em class="colRed" title="非正式员工">---</em>)</span>
                        </div>
                        <div class="treeRinfo">
                          <el-dropdown class="item">
                            <span class="el-dropdown-link">
                              操作<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>
                                  <el-button type="text" @click="handleAdd(data.id)">添加子部门</el-button>
                                </el-dropdown-item>
                              <el-dropdown-item>
                                  <el-button type="text" @click="handleUpdate(data.id)">查看该部门</el-button>
                                </el-dropdown-item>
                              <el-dropdown-item>
                                  <el-button type="text" @click="handleRemove(data)">删除该部门</el-button>
                                </el-dropdown-item>
                              <el-dropdown-item>
                                <el-button type="text" @click="handleList()">查看待分配员工</el-button>
                              </el-dropdown-item>
                            </el-dropdown-menu>
                          </el-dropdown>
                        </div>
                      </span>
                  </div>
                </div>
              </el-tree>
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <el-dialog title="新增部门" :visible.sync="dialogFormVisible">
      <el-form label-position="left" :model="dept">
        <el-form-item label="部门名称" :label-width="formLabelWidth">
          <el-input v-model="dept.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门编码" :label-width="formLabelWidth">
          <el-input v-model="dept.code" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门介绍" :label-width="formLabelWidth">
          <el-input v-model="dept.introduce" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门负责人" :label-width="formLabelWidth">
          <el-input v-model="dept.manager" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog()">取 消</el-button>
        <el-button type="primary" @click="saveDept()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<!-- 引入组件 -->
<script>
import {list, saveOrUpdate, detail, remove} from '@/api/base/departments'
import commonApi from '@/utils/common'

export default {
  data() {
    return {
      activeName: 'first',
      departData: {},
      depts: [],

      pid: "",
      dialogFormVisible: false,
      formLabelWidth: "120px",
      dept: {}
    }
  },
  methods: {
    init(companyId) {
      list({companyId: companyId}).then(res => {
        this.departData = res.data.data
        this.depts = commonApi.transformTozTreeFormat(res.data.data.depts)
      })
    },
    closeDialog() {
      // 关闭弹窗
      this.dialogFormVisible = false
      // 清空表单
      this.dept = {}
    },
    handleAdd(pid) {
      this.pid = pid
      this.dialogFormVisible = true
    },
    handleUpdate(id) {
      this.dialogFormVisible = true
      this.initDetail(id)
    },
    handleRemove(data) {
      this.$confirm(
        `本次操作将删除${data.name}，删除后不可恢复，确认删除吗？`
      ).then(() => {
        remove({id: data.id}).then(res => {
          this.$message({
            message: res.data.message,
            type: res.data.success ? "success" : "error"
          });
          location.reload()
        })
      })
    },
    handleList() {

    },
    saveDept() {
      this.dept.pid = this.pid
      saveOrUpdate(this.dept).then(res => {
        if (res.data.success) {
          // 重新加载页面
          location.reload()
        }
        // 关闭弹窗
        this.closeDialog()
      })
    },
    initDetail(id) {
      detail({id: id}).then(res => {
        this.dept = res.data.data
      })
    }
  },
  created() {
    var companyId = 1
    this.init(companyId)
  },
}
</script>
<style rel="stylesheet/scss" lang="scss">
.el-dropdown {
  color: #000000
}

.el-tree-node__content > .el-tree-node__expand-icon {
  padding: 0px;
}

.el-tree-node__expand-icon {
  color: #ffffff
}

.generalClassNode {
  padding-left: 20px;
}

.el-tree-node__content {
  font-size: 16px;
  line-height: 36px;
  height: 36px;
}

.custom-tree-node {
  padding-left: 20px;
}

.objectTree {
  overflow: auto;
  z-index: 100;
  width: 300px;
  border: 1px solid #dcdfe6;
  margin-top: 5px;
  left: 70px;
}

.el-tabs__content {
  overflow: initial;
}

.boxpad {
  margin-left: -40px;
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
.el-tree-node__expand-icon {

}

.el-icon-caret-right {
}

.el-tree-node__content {
  font-size: 14px;
  line-height: 36px;
}

.generalClass {
  font-size: 14px;
  line-height: 36px;
  color: #000000
}

.all {
  position: relative;
  min-height: 100%;
  padding-bottom: 200px;
}

.organization-main:after,
.organization-index-top:after {
  display: block;
  clear: both;
  content: '';
  visibility: hidden;
  height: 0;
}

.organization-main {
  font-size: 14px;
  font-size: 14px;
}

.organization-index {
  padding-bottom: 20px;
  margin-left: 20px;
}

.main-top-title {
  padding-left: 20px;
  padding-top: 20px;
  text-align: left;
}

::-webkit-scrollbar-thumb {
  background-color: #018ee8;
  height: 50px;
  outline-offset: -2px;
  outline: 8px solid #fff;
  -webkit-border-radius: 4px;
}

::-webkit-scrollbar-track-piece {
  background-color: #fff;
  -webkit-border-radius: 0;
}

::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

::-webkit-scrollbar-thumb:hover {
  background-color: #fb4446;
  height: 50px;
  -webkit-border-radius: 4px;
}

.modal-total {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background: #000;
  z-index: 90;
  opacity: 0.2;
}

.modal {
  width: 400px;
  height: 300px;
  background-color: #ffffff;
  z-index: 999;
  position: absolute;
  left: 45%;
  top: 20%;
  text-align: center;
}

.treBox {
  padding: 30px 120px 0;
}

.organization-index-top {
  position: relative;

  .el-tabs-report {
    position: absolute;
    top: -50px;
    right: 15px;
  }
}

.treeCon {
  border-bottom: 1px solid #cfcfcf;
  padding: 10px 0;
  margin-bottom: 10px;

  .el-dropdown {
    color: #333;
  }
}

.treeRinfo {
  display: inline-block;
}

.treeRinfo span {
  padding-left: 30px;
}
</style>
