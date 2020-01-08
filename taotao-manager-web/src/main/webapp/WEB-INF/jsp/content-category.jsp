<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
		//右击鼠标触发事件
		onContextMenu: function(e,node){
			//关闭鼠标的默认事件
            e.preventDefault();
			//选中右击鼠标的节点
            $(this).tree('select',node.target);
			//获取结点，展示菜单栏
            $('#contentCategoryMenu').menu('show',{
            	//右击鼠标的坐标显示
                left: e.pageX,
                top: e.pageY
            });
        },
        //在添加的节点被编辑之后触发
        onAfterEdit : function(node){
        	//获取树本省
        	var _tree = $(this);
        	//表示是新增节点
        	if(node.id == 0){
        		// 新增节点
        		//parentId:node.parentId,name:node.text
        		//parentId：就是新增的父节点的id name就是新增节点的文本   两个参数从下面传递
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			//提交成功返回200
        			if(data.status == 200){
        				//更新节点 
        				_tree.tree("update",{
            				target : node.target,//表示更新哪一个节点
            				id : data.data.id //更新新增节点的id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});
//处理点击菜单的事件
function menuHandler(item){
	//获取树
	var tree = $("#contentCategory");
	//获取被选中的节点 就是鼠标右击时 所在的节点
	var node = tree.tree("getSelected");
	//表示添加  判断选择的是添加 重命名 还是删除
	if(item.name === "add"){
		//在被点击的节点下增加一个子节点
		tree.tree('append', {
            parent: (node?node.target:null),//被添加的子节点的父节点
            //
            data: [{
                text: '新建分类',//节点的内容
                id : 0, //节点的id
                parentId : node.id //新建的节点的父节点id
            }]
        }); 
		//找到id为0的节点  即为新建的节点
		var _node = tree.tree('find',0);
		//选中id为0的节点 就是新增的节点  开启编辑
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	//重命名
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
		//删除
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{id:node.id},function(data){
						//是父节点 不能删除成功
		    			if(data.isParent){
		    				$.messager.alert('提示','删除失败!');
		    			}else{
							tree.tree("remove",node.target);
		    				$.messager.alert('提示','删除成功!');
		    			}
					
				});	
			}
		});
	}
}
</script>