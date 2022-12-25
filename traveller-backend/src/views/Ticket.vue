<template>
    <div class="manage">
        <!-- :close-on-click-modal='false'点击组件外dialog不会关闭 -->
        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose"
            :close-on-click-modal='false'>
            <!-- 车票的表单信息 :rules="rules"-->
            <!-- ref="form" ref ：获取该表单form组件 -->
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="车次" prop="trips">
                    <el-input placeholder="请输入车次" v-model="form.trips"></el-input>
                </el-form-item>
                <el-form-item label="出发站" prop="startSite">
                    <el-input placeholder="请输入出发站" v-model="form.startSite"></el-input>
                </el-form-item>
                <el-form-item label="终点站" prop="arriveSite">
                    <el-input placeholder="请输入终点站" v-model="form.arriveSite"></el-input>
                </el-form-item>
                <el-form-item label="出发时间">
                    <el-col :span="11">
                        <el-form-item prop="startDay">
                            <el-date-picker :picker-options="pickerOptions" type="date" value-format="yyyy-MM-dd"
                                placeholder="出发日期" v-model="form.startDay" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col class="line" :span="2">----</el-col>
                    <el-col :span="11">
                        <el-form-item prop="startTime">
                            <el-time-picker placeholder="出发时刻" v-model="form.startTime" style="width: 100%;"
                                format='HH:mm' value-format="HH:mm"></el-time-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>

                <el-form-item label="到达时间">
                    <el-col :span="11">
                        <el-form-item prop="arriveDay">
                            <el-date-picker :picker-options="pickerOptions" type="date" value-format="yyyy-MM-dd"
                                placeholder="到达日期" v-model="form.arriveDay" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col class="line" :span="2">----</el-col>
                    <el-col :span="11">
                        <el-form-item prop="arriveTime">
                            <el-time-picker placeholder="到达时刻" v-model="form.arriveTime" style="width: 100%;"
                                format='HH:mm' value-format="HH:mm"></el-time-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="票数" prop="totalNum">
                    <el-input placeholder="请输入票数" v-model="form.totalNum"></el-input>
                </el-form-item>
                <el-form-item label="价格" prop="price">
                    <el-input placeholder="请输入票价" v-model="form.price"></el-input>
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
        <!-- 车票列表table -->
        <div class="common-table">
            <el-table stripe height="90%" :data="tableData" style="width: 100%">
                <el-table-column prop="trips" label="车次" width="70px"> </el-table-column>
                <el-table-column prop="startSite" label="出发站" width="80px"> </el-table-column>
                <el-table-column prop="arriveSite" label="终点站" width="80px"></el-table-column>
                <el-table-column prop="startDay" label="出发日期" width="100px"></el-table-column>
                <el-table-column prop="startTime" label="出发时刻" width="80px"></el-table-column>
                <el-table-column prop="arriveDay" label="到达日期" width="100px"></el-table-column>
                <el-table-column prop="arriveTime" label="到达时刻" width="80px"></el-table-column>
                <el-table-column prop="useTime" label="用时" width="90px">
                    <template slot-scope="scope">
                        <span>{{ toHourMinute(scope.row.useTime) }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="totalNum" label="总票数" width="80px"></el-table-column>
                <el-table-column prop="nowNum" label="剩余票数" width="80px"></el-table-column>
                <el-table-column prop="price" label="价格" width="100px"></el-table-column>
                <el-table-column prop="state" label="状态" width="60px">
                    <template slot-scope="scope">
                        <span>{{ scope.row.state === 1 ? '有效' : '无效' }}</span>
                    </template>
                </el-table-column>
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
import { getTicketList, addTicket, updateTicket, deleteTicket } from '../api/manageTicket'
export default {
    data() {
        return {
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() < Date.now() - 8.64e7;
                }
            },
            nowPage: 1,
            //对话框dialog是否显示
            dialogVisible: false,
            form: {
                trips: '',
                startSite: '',
                arriveSite: '',
                startDay: '',
                startTime: '',
                arriveDay: '',
                arriveTime: '',
                useTime: '',
                totalNum: '',
                nowNum: '',
                price: '',
                state: ''
            },
            // 表单校验
            rules: {
                trips: [
                    { required: true, message: "请输入车次", trigger: 'blur' },
                ],
                startSite: [
                    { required: true, message: "请输入出发站", trigger: 'blur' },
                ],
                arriveSite: [
                    { required: true, message: "请输入终点站", trigger: 'blur' },
                ],
                startDay: [
                    { required: true, message: "请选择出发日期", trigger: 'blur' },
                ],
                startTime: [
                    { required: true, message: "请选择出发时刻", trigger: 'blur' },
                ],
                arriveDay: [
                    { required: true, message: "请选择到达日期", trigger: 'blur' },
                ],
                arriveTime: [
                    { required: true, message: "请选择到达时刻", trigger: 'blur' },
                ],
                totalNum: [
                    { required: true, message: "请输入票数", trigger: 'blur' },
                ],
                price: [
                    { required: true, message: "请输入票价", trigger: 'blur' },
                ],
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
        // 展示所有车票
        this.getList(1);
    },
    computed: {
        takeTime() {
            let startDate = Date.parse(this.form.startDay+' '+this.form.startTime);
            let endDate = Date.parse(this.form.arriveDay+' '+this.form.arriveTime);
            return  (endDate - startDate) / (60 * 1000);
        }
    },
    methods: {
        toHourMinute(minutes) {
            return (Math.floor(minutes / 60) + "小时" + (minutes % 60) + "分");
        },
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
            this.$confirm('是否删除从' + row.startSite +'开往'+row.arriveSite+ '的车票?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                await deleteTicket({
                    pkId:row.pkId
                }).then(res => {
                    if (res.success === true) {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getList(this.nowPage);
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
            //this.getList(1, this.userForm.userName)
        },
        //获得所有的车票
        getList(nowPage) {
            getTicketList({
                nowPage: nowPage,
            }).then(res => {
                // 返回的表格数据
                this.tableData = res.data.records;
                // 总记录数
                this.total = res.data.total;
                //每页显示的条数
                //res.data.size
            })
        },
        //选择页面的回调函数
        handlePage(val) {
            this.nowPage = val;
            this.getList(val);
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
        //添加车票 编辑车票
        submit: function () {
            // 提交表单
            //validate  对整个表单进行校验的方法，参数为一个回调函数。该回调函数会在校验结束后被调用，并传入两个参数：是否校验成功和未通过校验的字段。若不传入回调函数，则会返回一个 promise
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    if (this.form.arriveDay < this.form.startDay || (this.form.arriveDay === this.form.startDay && this.form.startTime >= this.form.arriveTime)) {
                        this.$message({
                            message: '设置失败!到达时间应晚于出发时间',
                            type: 'error'
                        });
                    }
                    else {
                        this.form.useTime=this.takeTime;
                        console.log(this.takeTime)
                        //添加车票
                        if (this.modelType === true) {
                            await addTicket(this.form).then(res => {
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
                        //编辑车票
                        else {
                            await updateTicket(this.form).then(res => {
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
                        this.getList(this.nowPage);
                        //清空表单
                        this.$refs.form.resetFields();
                        // 关闭弹窗
                        this.dialogVisible = false;
                    }
                }
            })
        }
    }
}
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