<template>
    <div class="manage">
        <!-- :close-on-click-modal='false'点击组件外dialog不会关闭 -->
        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose"
            :close-on-click-modal='false'>
            <!-- 用户的表单信息 :rules="rules"-->
            <!-- ref="form" ref ：获取该表单form组件 -->
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="邮箱" prop="userEmail">
                    <el-input placeholder="请输入用户邮箱" v-model="form.userEmail" :disabled="isInput"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="passWord">
                    <el-input type="password" placeholder="请输入密码" v-model="form.passWord" autocomplete="off"></el-input>
                </el-form-item>
                <!-- prop="userName" -->
                <el-form-item label="用户名" prop="userName">
                    <el-input placeholder="请输入用户名" v-model="form.userName"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-select v-model="form.sex" placeholder="请选择">
                        <!-- 这里value需要动态绑定 -->
                        <el-option label="男" :value=1></el-option>
                        <el-option label="女" :value=2></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="出生日期" prop="birthday">
                    <el-col>
                        <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="form.birthday"
                            style="width: 100%" :picker-options="expireTimeOption">
                        </el-date-picker>
                    </el-col>
                </el-form-item>
            </el-form>

            <!-- 对话框底部部分 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>
        </el-dialog>
        <div class="manager-header ">
            <el-button @click="handleAdd" type="primary">
                + 新增
            </el-button>
            <!-- form搜索区 -->
            <el-form :inline="true" :model="userForm" @submit.native.prevent>
                <el-form-item>
                    <el-input placeholder="请输入搜索内容" v-model="userForm.userName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSumit">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 用户列表table -->
        <div class="common-table">
            <el-table stripe height="90%" :data="tableData" style="width: 100%">
                <el-table-column prop="userEmail" label="邮箱"> </el-table-column>
                <el-table-column prop="userName" label="姓名"> </el-table-column>
                <el-table-column prop="sex" label="性别">
                    <template slot-scope="scope">
                        <span style="margin-left:10px">{{ scope.row.sex === 1 ? '男' : '女' }}</span>
                    </template>
                </el-table-column>

                <el-table-column prop="birthday" label="出生日期"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 页码 -->
            <div class="pager">
                <el-pagination layout="prev, pager, next" :total="total" @current-change="handlePage">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import { getUserList, addUser, updateUser, deleteUser } from '../api/manageUser'
export default {
    data() {
        return {
            nowPage: 1,
            expireTimeOption: {
                disabledDate(date) {
                    // disabledDate 文档上：设置禁用状态，参数为当前日期，要求返回 Boolean
                    return date.getTime() >= Date.now();
                },
            },
            isInput: false,
            //对话框dialog是否显示
            dialogVisible: false,
            form: {
                userEmail: "",
                passWord: "",
                userName: "",
                sex: "",
                birthday: "",
            },
            // 表单校验
            rules: {
                userEmail: [
                    { required: true, message: "请输入邮箱", trigger: 'blur' },
                ],
                passWord: [
                    { required: true, message: "请输入密码", trigger: 'blur' },
                ],
                userName: [
                    { required: true, message: "请输入用户名", trigger: 'blur' },
                ],
                sex: [
                    { required: true, message: "请选择性别", trigger: 'blur' },
                ],
                birthday: [
                    { required: true, message: "请选择出生日期", trigger: 'blur' },
                ]
            },
            tableData: [],
            modelType: true, //true表示的是新增的弹窗，false表示的是编辑的弹窗
            //搜索用户
            userForm: {
                userName: "",
            },
            total: 0, //总条数
        };
    },
    mounted() {
        // 展示所有用户
        this.getList(1,null);
    },
    methods: {
        //新增用户
        handleAdd() {
            //true表示的是新增的弹窗，false表示的是编辑的弹窗
            this.modelType = true;
            //在还没有显示对话框之前就调用this.$refs[‘form’].resetFields()，会报错
            this.dialogVisible = true;
            this.isInput = false;
            this.$nextTick(() => {
                this.$refs['form'].resetFields();
            });
        },
        // 编辑用户
        handleEdit(row) {
            this.modelType = false;
            //弹窗内容回显,需要对当前行数据进行深拷贝，否则会出错
            //弹窗内容回显,需要对当前行数据进行深拷贝，否则会出错
            //弹窗内容回显,需要对当前行数据进行深拷贝，否则会出错
            //不能用this.form = row
            this.form = JSON.parse(JSON.stringify(row));
            this.dialogVisible = true;
            this.isInput = true;
        },
        // 删除用户
        handleDelete(row) {
            // 弹窗提示
            this.$confirm('是否删除用户'+row.userName+'?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async() => {
                await deleteUser({
                    userEmail:row.userEmail
                }).then(res => {
                    if (res.success === true) {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getList(this.nowPage,null);
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        //搜索查询
        onSumit() {
            this.getList(1,this.userForm.userName)
        },
        //获得所有的用户
        getList(nowPage,userName) {
            getUserList({
                nowPage: nowPage,
                userName:userName
            }).then(res => {
                // 返回的表格数据
                this.tableData = res.data.records;
                // 总记录数
                this.total = res.data.total;
                //每页显示的条数
                //res.data.size
            })
            // await this.$axios.get('/user/selectByPage', {
            //     nowPage: nowPage
            // }).then(res => {
            //     // 返回的表格数据
            //     this.tableData = res.data.records;
            //     // 总记录数
            //     this.total = res.data.total;
            //     //每页显示的条数
            //     //res.data.size
            // })
        },
        //选择页面的回调函数
        handlePage(val) {
            this.nowPage = val;
            this.getList(val,this.userForm.userName);
        },

        /*
        before-close 仅当用户通过点击关闭图标或遮罩关闭 Dialog 时起效。如果你在 footer 具名 slot 里添加了用于关闭 Dialog 的按钮，那么可以在按钮的点击回调函数里加入 before-close 的相关逻辑。
        */
        handleClose() {
            //resetFields()清空表单
            this.$refs.form.resetFields()
            this.dialogVisible = false
        },
        cancel() {
            this.handleClose();
        },
        //添加用户 编辑用户
        submit: function () {
            // 提交用户表单
            //validate  对整个表单进行校验的方法，参数为一个回调函数。该回调函数会在校验结束后被调用，并传入两个参数：是否校验成功和未通过校验的字段。若不传入回调函数，则会返回一个 promise
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //添加用户
                    if (this.modelType === true) {
                        await addUser(this.form).then(res => {
                            if (res.success === true) {
                                this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                            } else {
                                this.$message({
                                    message: '添加失败',
                                    type: 'error'
                                });
                            }
                        })
                    }
                    //编辑用户
                    else {
                        await updateUser(this.form).then(res => {
                            if (res.success === true) {
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                            } else {
                                this.$message({
                                    message: '修改失败',
                                    type: 'error'
                                });
                            }
                        })
                    }
                    this.getList(this.nowPage,null);
                    //清空表单
                    this.$refs.form.resetFields();
                    // 关闭弹窗
                    this.dialogVisible = false;
                }
            })
        }
    }
};
</script>
<style lang="less" scoped>
.manage {
    height: 100%;

    .manager-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .common-table {
        position: relative;
        height: calc(100% - 62px);

        .pager {
            float: right;
        }
    }
}
</style>
