<template>
    <div class="manage">
        <!-- :close-on-click-modal='false'点击组件外dialog不会关闭 -->
        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose"
            :close-on-click-modal='false'>
            <!-- 用户的表单信息 :rules="rules"-->
            <!-- ref="form" ref ：获取该表单form组件 -->
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="景点名" prop="sightsName">
                    <el-input placeholder="请输入景点名" v-model="form.sightsName"></el-input>
                </el-form-item>
                <el-form-item label="景点图片" prop="sightsImage">
                    <el-upload class="avatar-uploader" :headers="headers" action="http://localhost:8888/upload/image"
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="form.sightsImage" :src="form.sightsImage" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="所属省份" prop="province">
                    <el-input placeholder="请输入省份" v-model="form.province"></el-input>
                </el-form-item>
                <el-form-item label="所属城市" prop="city">
                    <el-input placeholder="请输入城市" v-model="form.city"></el-input>
                </el-form-item>
                <el-form-item label="参观日期" prop="visitDay">
                    <el-col>
                        <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="form.visitDay"
                            style="width: 100%" :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="门票价格" prop="price">
                    <el-input placeholder="请输入门票价格" v-model="form.price"></el-input>
                </el-form-item>
                
                <el-form-item label="总数量" prop="totalNum">
                    <el-input placeholder="请输入门票总数量" v-model="form.totalNum"></el-input>
                </el-form-item>
                <el-form-item label="景点描述" prop="sightBriefIntroduction">
                    <el-input type="textarea" placeholder="请输入景点描述" v-model="form.sightBriefIntroduction"></el-input>
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
            <el-form :inline="true" :model="sightsForm" @submit.native.prevent>
                <el-form-item>
                    <el-input placeholder="请输入搜索内容" v-model="sightsForm.sightsName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSumit">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 列表table -->
        <div class="common-table">
            <el-table stripe height="90%" :data="tableData" style="width: 100%">
                <el-table-column prop="sightsName" label="景点名" width="90px"> </el-table-column>
                <el-table-column prop="sightsImage" label="景点图片">
                    <!-- 图片的显示 -->
                    <template slot-scope="scope">
                        <img :src="scope.row.sightsImage" height="70" />
                    </template>
                </el-table-column>
                <el-table-column prop="province" label="所属省份" width="80px"></el-table-column>
                <el-table-column prop="city" label="所属城市" width="80px"></el-table-column>
                <el-table-column prop="visitDay" label="参观日期" width="100px"></el-table-column>
                <el-table-column prop="price" label="价格" width="80px"></el-table-column>
                <el-table-column prop="totalNum" label="总票数" width="70px"></el-table-column>
                <el-table-column prop="nowNum" label="剩余票数" width="80px"></el-table-column>
                <el-table-column prop="state" label="状态" width="60px">
                    <template slot-scope="scope">
                        <span>{{ scope.row.state === 1 ? '有效' : '无效' }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="sightBriefIntroduction" label="景点介绍" show-overflow-tooltip></el-table-column>
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
import Cookie from 'js-cookie'
import { getSightsList,addSights,updateSights,deleteSights } from '@/api/manageSights';
export default {
    computed: {
        headers() {
            let token = JSON.parse(Cookie.get('user')).token;
            return {
                authorization: token
            };
        },
    },
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
                sightsName: "",
                sightsImage: "",
                province: "",
                city: "",
                price: "",
                visitDay:'',
                totalNum: "",
                nowNum:"",
                state:"",
                sightBriefIntroduction: ""
            },
            // 表单校验
            rules: {
                sightsName: [
                    { required: true, message: "请输入景点名", trigger: 'blur' },
                ],

                province: [
                    { required: true, message: "请输入所属省份", trigger: 'blur' },
                ],
                city: [
                    { required: true, message: "请输入所属城市", trigger: 'blur' },
                ],
                totalNum: [
                    { required: true, message: "请输入门票数量", trigger: 'blur' },
                ],
                visitDay: [
                    { required: true, message: "请选择参观日期", trigger: 'blur' },
                ],
                price: [
                    { required: true, message: "请输入门票价格", trigger: 'blur' },
                ],
                sightsImage: [
                    {
                        required: true,
                        message: '上传景点图片',
                        trigger: 'change'
                    }
                ],
                sightBriefIntroduction: [
                    { required: true, message: "请输入景点介绍", trigger: 'blur' },
                ],
            },
            tableData: [],
            modelType: true, //true表示的是新增的弹窗，false表示的是编辑的弹窗
            //搜索
            sightsForm: {

            },
            total: 0, //总条数
        };
    },
    mounted() {
        // 展示
        this.getList(1);
    },
    methods: {
        //上传图片
        handleAvatarSuccess(res, file) {
            console.log(res.data);
            this.form.sightsImage = res.data;
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt10M = file.size / 1024 / 1024 < 10;

            if (!isJPG && !isPNG) {
                this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
            }
            if (!isLt10M) {
                this.$message.error('上传头像图片大小不能超过 10MB!');
            }
            return (isJPG || isPNG) && isLt10M;
        },
        //新增
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
        // 编辑
        handleEdit(row) {
            this.modelType = false;
            //弹窗内容回显,需要对当前行数据进行深拷贝，否则会出错
            //不能用this.form = row
            this.form = JSON.parse(JSON.stringify(row));
            this.dialogVisible = true;
            this.isInput = true;
        },
        // 删除
        handleDelete(row) {
            // 弹窗提示
            this.$confirm('是否删除景点' + row.sightsName + '?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(async () => {
                await deleteSights({
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
            this.getList(1)
        },
        //获得
        getList(nowPage) {
            getSightsList({
                nowPage: nowPage,
            }).then(res => {
                 // 返回的表格数据
                 this.tableData = res.data.records;
                // 总记录数
                 this.total = res.data.total;
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
        //添加 编辑
        submit: function () {
            // 提交表单
            //validate  对整个表单进行校验的方法，参数为一个回调函数。该回调函数会在校验结束后被调用，并传入两个参数：是否校验成功和未通过校验的字段。若不传入回调函数，则会返回一个 promise
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //添加
                    if (this.modelType === true) {
                        await addSights(this.form).then(res => {
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
                    //编辑
                    else {
                        await updateSights(this.form).then(res => {
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

//照片上传样式
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 300px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 100%;
    height: 100%;
    display: block;
}
</style>
