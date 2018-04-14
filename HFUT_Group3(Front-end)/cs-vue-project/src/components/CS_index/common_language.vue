<template>
    <div id="common_language">
        <el-tabs class="cl-index" v-model="activeName" @tab-click="handleClick">
            <el-tab-pane class="knowledge-index"label="知识库管理" name="first">
                <el-container>
                    <el-header class="kmanage-head">
                        <el-button type="primary" class="add-knowledge">添加知识</el-button>
                        <el-input class="search-knowledge">
                        <i class="el-icon-edit el-input__icon"
                        slot="suffix"/>
                        </el-input>
                    </el-header>
                    <el-main>
                        <el-row :gutter="12">
                            <el-col :span="8">
                                <el-card shadow="always">
                                    总是显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="hover">
                                    鼠标悬浮时显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="never">
                                    从不显示
                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row :gutter="12">
                            <el-col :span="8">
                                <el-card shadow="always">
                                    总是显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="hover">
                                    鼠标悬浮时显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="never">
                                    从不显示
                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row :gutter="12">
                            <el-col :span="8">
                                <el-card shadow="always">
                                    总是显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="hover">
                                    鼠标悬浮时显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="never">
                                    从不显示
                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row :gutter="12">
                            <el-col :span="8">
                                <el-card shadow="always">
                                    总是显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="hover">
                                    鼠标悬浮时显示
                                </el-card>
                            </el-col>
                            <el-col :span="8">
                                <el-card shadow="never">
                                    从不显示
                                </el-card>
                            </el-col>
                        </el-row>
                    </el-main>
                </el-container>
            </el-tab-pane>
            <el-tab-pane label="常用语管理" name="second">
                <el-container>
                    <el-header class="kmanage-head">
                        <el-button type="primary" class="add-commonlanguage">添加常用语</el-button>
                        <el-button type="danger" class="delete-commonlanguage">删除常用语</el-button>
                    </el-header>
                    <el-main>
                        <el-table
                        ref="multipleTable"
                        :data="tableData3"
                        tooltip-effect="dark"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                            <el-table-column
                            type="selection"
                            width="50">
                            </el-table-column>
                            <el-table-column
                            label="常用语"
                            prop="common_language"
                            width="1100">
                            </el-table-column>
                            <el-table-column
                            label="频率"
                            prop="frequency">
                            </el-table-column>
                        </el-table>
                    </el-main>
                </el-container>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
export default {
    data() {
        return {
        activeName: 'first',
        tableData3: [{
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }, {
          common_language: '你好！计蒜客',
          frequency: '1',
        }],
        multipleSelection: []
      }
    },
methods: {
    handleClick(tab, event) {
        console.log(tab, event);
    },
    toggleSelection(rows) {
        if (rows) {
            rows.forEach(row => {
                this.$refs.multipleTable.toggleRowSelection(row);
            });
        }else {
            this.$refs.multipleTable.clearSelection();
        }
    },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    }
}
};
</script>

<style type="text/css" scoped>
.el-row {
    margin-bottom: 10px;
}
.cl-index {
    height: 100%;
    width: 100%
}
.knowledge-index {
    height: 100%;
    width: 100%;
}
.kmanage-head {
    height: 50px;
    margin: 20px;
    border-bottom: 1px solid #E6E6E6;
}
.add-knowledge {
    float: left;
    margin-left: 10px;
}
.add-commonlanguage {
    float: left;
    margin-left: 10px;
}
.delete-commonlanguage {
    float: left;
    margin-left: 10px;
}
.search-knowledge {
    width: 10%;
    float: left;
    margin-left: 10px;
}
#common_language {
    width: 100%;
    height: 100%;
}
.el-tabs {
    height: 100%;
}
</style>