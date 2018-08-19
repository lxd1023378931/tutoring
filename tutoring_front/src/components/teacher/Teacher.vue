<template>
  <div id="teacher" style="width: 100%">
    <el-table
      :data="teachersMsg"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="教员编号">
      </el-table-column>
      <el-table-column
        label="头像">
        <template slot-scope="scope">
          <img v-bind:src="scope.row.logo" width="80px" height="80px"/>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="education"
        label="学历">
      </el-table-column>
      <el-table-column
        prop="job"
        label="工作">
      </el-table-column>
      <el-table-column
        prop="salary"
        :formatter="formatSalary"
        label="工资">
      </el-table-column>
      <el-table-column
        label="评分">
        <template slot-scope="scope">
          <el-rate v-model="scope.row.grade" disabled show-score text-color="#ff9900" score-template="{value}">
          </el-rate>
        </template>
      </el-table-column>
      <el-table-column
        prop="disable"
        :formatter="formatDisable"
        label="状态">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="查看">
        <template slot-scope="scope">
          <el-button @click="showDetail(scope.row)" type="text" size="small">详情</el-button>
          <el-button @click="showHistory(scope.row)" type="text" size="small">记录</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="教师详情:" :visible.sync="showFlag">
        <div style="width: 100%;max-height: 350px;overflow: auto">
          <table style="margin:auto 30px;">
            <tr>
              <td width="50px">教师编号</td>
              <td width="40%">{{teacherMsg.id}}（{{teacherMsg.username}}）</td>
              <td width="30%" rowspan="4"><img v-bind:src="teacherMsg.logo" width="120px" height="160px"/></td>
            </tr>
            <tr>
              <td>姓名</td>
              <td>{{teacherMsg.name}}</td>
            </tr>
            <tr>
              <td>电话</td>
              <td>{{teacherMsg.phone}}</td>
            </tr>
            <tr>
              <td>邮箱</td>
              <td>{{teacherMsg.email}}</td>
            </tr>
            <tr>
              <td>性别</td>
              <td>{{teacherMsg.sex}}</td>
            </tr>
            <tr>
              <td>居住地</td>
              <td>{{teacherMsg.province}}{{teacherMsg.city}}{{teacherMsg.address}}</td>
            </tr>
            <tr>
              <td>生日</td>
              <td colspan="2">{{teacherMsg.birthday}}</td>
            </tr>
            <tr>
              <td>工作</td>
              <td colspan="2">{{teacherMsg.job}}</td>
            </tr>
            <tr>
              <td>专业</td>
              <td colspan="2">{{teacherMsg.major}}（{{teacherMsg.school}}）</td>
            </tr>
            <tr>
              <td>工作</td>
              <td colspan="2">{{teacherMsg.job}}</td>
            </tr>
            <tr>
              <td>户籍</td>
              <td colspan="2">{{teacherMsg.navitePlace}}</td>
            </tr>
            <tr>
              <td>授课科目</td>
              <td colspan="2">{{teacherMsg.subject}}</td>
            </tr>
            <tr>
              <td>授课区域</td>
              <td colspan="2">{{teacherMsg.area}}</td>
            </tr>
            <tr>
              <td>授课方式</td>
              <td colspan="2">{{teacherMsg.teachingMethod}}</td>
            </tr>
            <tr>
              <td>个人介绍</td>
              <td colspan="2">{{teacherMsg.describution}}</td>
            </tr>
            <tr>
              <td>特长</td>
              <td colspan="2">{{teacherMsg.talent}}</td>
            </tr>
            <tr>
              <td>爱好</td>
              <td colspan="2">{{teacherMsg.hobby}}</td>
            </tr>
            <tr>
              <td>期望工资</td>
              <td colspan="2">{{teacherMsg.salary}}元/{{teacherMsg.salaryUnit}}</td>
            </tr>
            <tr>
              <td>学生评价</td>
              <td>
                <el-rate v-model="teacherMsg.grade" disabled show-score text-color="#ff9900" score-template="{value}">
                </el-rate>
              </td>
              <td>
                <el-button v-bind:type="teacherMsg.isUsed" @click="disable()" v-bind:id="teacherMsg.id" plain>{{teacherMsg.isUsedMsg}}</el-button>
              </td>
            </tr>
          </table>
        </div>
    </el-dialog>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[4, 8, 12, 16]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import Vue from 'vue'
  import Element from '../../../node_modules/element-ui'
  import '../../../node_modules/element-ui/lib/theme-chalk/index.css'

  Vue.use(Element)
  export default {
    name: 'teacher',
    data() {
      return {
        teachersMsg: [],
        teacherMsg:[],
        showFlag:false,
        currentPage:1,
        pageSize:4,
        total:0
      }
    },
    mounted() {
      this.$http.get('http://localhost:8091/api/m/teacher/list?pageIndex='+(this.currentPage-1)+'&pageSize='+this.pageSize).then((response) => {
        if (response.body && response.body.status == 1) {
          this.teachersMsg = response.body.object.data
          this.total = response.body.object.total
        } else {
          this.$message({
            message: response.body && response.body.status != 1002 ? response.body.message : '请求出错',
            type: 'warning'
          });
        }
      }, (response) => {
        this.$message.error('请求出错');
      });
    },
    methods:{
      formatSalary(row,column){
        return row.salary+'元/'+row.salaryUnit
      },
      formatDisable(row,column){
        return row.disable == 'Y'?'已禁用':'已启用'
      },
      showDetail(row){
        this.teacherMsg = row
        this.teacherMsg.isUsed=this.teacherMsg.disable == 'Y'?'success':'danger'
        this.teacherMsg.isUsedMsg=this.teacherMsg.disable == 'Y'?'启用':'禁用'
        this.showFlag = true
      },
      disable(){
        this.$http.put('http://localhost:8091/api/m/teacher/status?id='+this.teacherMsg.id).then((response) => {
          if (response.body && response.body.status == 1) {
            this.loadData()
            this.showFlag = false
          } else {
            this.$message({
              message: response.body && response.body.status != 1002 ? response.body.message : '请求出错',
              type: 'warning'
            });
          }
        }, (response) => {
          this.$message.error('请求出错');
        });
      },
      loadData(){
        this.$http.get('http://localhost:8091/api/m/teacher/list?pageIndex='+(this.currentPage-1)+'&pageSize='+this.pageSize).then((response) => {
          if (response.body && response.body.status == 1) {
            this.teachersMsg = response.body.object.data
            this.total = response.body.object.total
          } else {
            this.$message({
              message: response.body && response.body.status != 1002 ? response.body.message : '请求出错',
              type: 'warning'
            });
          }
        }, (response) => {
          this.$message.error('请求出错');
        });
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.loadData();
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.loadData();
        console.log(`当前页: ${val}`);
      }
    }
  }
</script>
<style>
</style>
