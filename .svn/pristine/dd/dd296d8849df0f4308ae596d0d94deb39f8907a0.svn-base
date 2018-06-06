require(["domReady!","common","wmWebUI","mobile","echarts3","util"], function(doc,Common,WmWebUI,Mobile,ec) {
	
	/**var wmTabbar = new WmTabbar("myTabbar");
	wmTabbar.create();
	
	var wmTabbarItems = wmTabbar.getTabbarItems();
	for(var i =0;i<wmTabbarItems.length;i++){
		wmTabbarItems[i].setAction(onClickTabbarItem);
	}**/
	var char1Data,char2Data,char3Data;
	var optionChart1 = {
		    title: {
		        text: '前6月租用费',
		        subtext: '万元'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    grid:{
		    	left:'15%',
		    	width:'80%'
		    },
		    legend: {
		    	x:'center',
		        data:['铁塔公司','联通公司']
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        axisLabel:{
	            	rotate:45,
	            	interval:0
	            },
		        data: ['一月','二月','三月','四月','五月','六月']
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value}'
		        }
		    },
		    backgroundColor:'#ffffff',
		    color:['#5091d1','#fba946', '#74b338'],
		    series: [
		        {
		            name:'铁塔公司',
		            type:'line',
		            data:[11, 11, 15, 13, 12, 13],
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值',symbolSize:80}
		                ]
		            }
		        },
		        {
		            name:'联通公司',
		            type:'line',
		            data:[5, 10, 8, 14, 16, 14],
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值',symbolSize:80}
		                ]
		            }
		        }
		    ]
		};
	
	var optionChart2 = {
			   title: {
			        text: '计费铁塔总数对比'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			    	x:'right',
			        data:['铁塔公司','联通公司']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisLabel:{
			            	rotate:45,
			            	interval:0
			            },
			            
			            data: ['太原','大同','阳泉','长治','晋城','朔州','晋中','运城','忻州','临汾','吕梁']
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '铁塔数量',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    grid:{
			    	left:'15%',
			    	width:'80%'
			    },
			    backgroundColor:'#ffffff',
			    color:['#5091d1','#74b338', '#fba946'],
			    series: [
			        {
			            name:'铁塔公司',
			            type:'bar',
			            barGap:'-0.1',
			            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4]
			        },
			        {
			            name:'联通公司',
			            type:'bar',
			            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0]
			        }
			    ]
			};
	

	var optionChart3 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		       {
		            name:'业务指标',
		            type:'gauge',
		            min:0,
		            max:100,
		            splitNumber:10,
		            radius: '95%',
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                 color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
		                 width: 8,
		                 shadowColor : '#fff', //默认透明
		                 shadowBlur: 10
		                }
		            },
		            axisLabel: {            // 坐标轴小标记
		                show:false
		            },
		            axisTick: {            // 坐标轴小标记
		                length:12,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                length:15,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'auto'
		                }
		            },
		            pointer: {
		                width:5
		            },
		            detail: {
				        formatter:'{value}%',
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontSize: 14,
		                    fontWeight: 'bolder'
		                }
		            },
		            data:[{value: 100}]
		        }
		    ]
		};
	var optionChart31 = {
			tooltip : {
				formatter: "{a} <br/>{b} : {c}%"
			},
			series: [
			         {
			        	 name:'业务指标',
			        	 type:'gauge',
			        	 min:0,
			        	 max:100,
			        	 splitNumber:10,
			        	 radius: '95%',
			        	 axisLine: {            // 坐标轴线
			        		 lineStyle: {       // 属性lineStyle控制线条样式
			        			 color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
			        			 width: 8,
			        			 shadowColor : '#fff', //默认透明
			        			 shadowBlur: 10
			        		 }
			        	 },
			        	 axisLabel: {            // 坐标轴小标记
			        		 show:false,
			        		 textStyle: {       // 属性lineStyle控制线条样式
			        			 fontWeight: 'bolder',
			        			 color: '#000',
			        			 shadowColor : '#000', //默认透明
			        			 shadowBlur: 10
			        		 }
			        	 },
			        	 axisTick: {            // 坐标轴小标记
			        		 length :6,        // 属性length控制线长
			        		 lineStyle: {       // 属性lineStyle控制线条样式
			        			 color: '#fff',
			        			 shadowColor : '#fff', //默认透明
			        			 shadowBlur: 10
			        		 }
			        	 },
			        	 splitLine: {           // 分隔线
			        		 length :8,         // 属性length控制线长
			        		 lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
			        			 width:3,
			        			 color: '',
			        			 shadowColor : '#fff', //默认透明
			        			 shadowBlur: 10
			        		 }
			        	 },
			        	 pointer: {           // 分隔线
			        		 shadowColor : '#000', //默认透明
			        		 shadowBlur: 8,
			        		 width:3
			        	 },
			        	 title : {
			        		 textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			        			 fontWeight: 'bolder',
			        			 fontSize: 12,
			        			 fontStyle: 'italic',
			        			 color: '#000',
			        			 shadowColor : '#000', //默认透明
			        			 shadowBlur: 10
			        		 }
			        	 },
			        	 detail : {
			        		 formatter:'{value}%',
			        		 textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			        			 fontWeight: 'bolder',
			        			 fontSize: 16,
			        			 color: 'rgba(30,144,255,0.8)'
			        		 }
			        	 },
			        	 data:[{value: 40}]
			         }
			         ]
	};
	
	WmWebUI.initTags(["myTabbar"],function(MyTabbar){
		var items = MyTabbar.getTabbarItems();
		for(var i =0;i<items.length;i++){
			var item = items[i];
			item.setAction(onClickTabbarItem);
		}
	});
	function onClickTabbarItem(){
		var wmTabbar = WmWebUI.select("myTabbar");
		var index = wmTabbar.currIndex;
		if(index ==0){
			$("#title1").removeClass('uhide');
			$("#title2").addClass('uhide');
		}else if(index == 1){
			$("#title2").removeClass('uhide');
			$("#title1").addClass('uhide');
			$("#title-text").html("报表查询");
		}else if(index == 2){
			$("#title2").removeClass('uhide');
			$("#title1").addClass('uhide');
			$("#title-text").html("工作平台");
		}else if(index == 3){
			$("#title2").removeClass('uhide');
			$("#title1").addClass('uhide');
			$("#title-text").html("我的");
		}
	}
	setTimeout(function(){
		getOrderInfo();
		getTowerInfo();
	},100);
	/**
	 * 查询工单数和回单率
	 */
	function getOrderInfo(){
		Common.callSvc("OrderBean.getOrderInfos", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			$("#order_num").html(resultData.get("ORDER_NUM"));
			//$("#order_route").html(resultData.get("ORDER_ROUTE"));

			var chart3 = ec.init(document.getElementById('chart3')); 
			optionChart3.series[0].data[0].value = resultData.get("ORDER_ROUTE");
			chart3.setOption(optionChart3, true);
			
		});
	};
	
	
	/**
	 * 查询铁塔费用及数量
	 */
	function getTowerInfo(){
		Common.callSvc("TowerBean.getMainPageInfos", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			var fee = resultData.get("fee");
			var tower = resultData.get("tower");
			optionChart1.xAxis.data = fee.get('xAxis_data').items;
			optionChart1.series[0].data = fee.get('series_data_tt').items;
			optionChart1.series[1].data = fee.get('series_data_lt').items;
			var chart1 = ec.init(document.getElementById('chart1')); 
			chart1.setOption(optionChart1);
			char1Data = fee;
			 
			optionChart2.xAxis.data = tower.get('xAxis_data').items;
			optionChart2.series[0].data = tower.get('series_data_tt').items;
			optionChart2.series[1].data = tower.get('series_data_lt').items;
			var chart2 = ec.init(document.getElementById('chart2')); 
			chart2.setOption(optionChart2);
			char2Data = tower;
			 
		});
	}
	/**setTimeout(function(){
		var chart1 = ec.init(document.getElementById('chart1')); 
		 chart1.setOption(optionChart1);
		 
		 var chart2 = ec.init(document.getElementById('chart2')); 
		 chart2.setOption(optionChart2); 
    },200);**/
	
	$("#report li,#common li").each(function(index, item) {
		if (item.getAttribute("action") != null) {
			$(item).tap(function() {
				var param = new Wade.DataMap();
				/*特殊符号测试*/
				param.put("flag",";/?:@&=+$,#()[]!#%*'");
				param.put("url","http://www.163.com?action=1&aaaa=2");
				param.put("chinese","中文测试");
				Mobile.openPage(item.getAttribute("action"),param);
			});
		} else {
			item.onclick = function() {
				alert("敬请期待……");
			};
		}
	});
	
	$("#chart1").tap(function(){
		var dataList = new Wade.DatasetList();
		var xAxis_data = char1Data.get('xAxis_data').items;
		var series_data_tt = char1Data.get('series_data_tt').items;
		var series_data_lt = char1Data.get('series_data_lt').items;
		for(var i=0;i<xAxis_data.length;i++){
			data = new Wade.DataMap();
			data.put("year_month",xAxis_data[i]);
			data.put("tower_fee",series_data_tt[i]);
			data.put("lt_fee",series_data_lt[i]);
			var ce = parseFloat(series_data_tt[i])-parseFloat(series_data_lt[i]);
			data.put("ce",ce);
			if(i%2 == 0){
				data.put("style","");
			}else{
				data.put("style"," ubc ubt ubb c-gra1 ");
			}
			
			dataList.add(data);
		}
		
		/**var data1 = new Wade.DataMap();
		data1.put("year_month","201601");
		data1.put("tower_fee","2323");
		data1.put("lt_fee","33333");
		data1.put("ce","-22");
		data1.put("style","");
		dataList.add(data1);
		var data2 = new Wade.DataMap();
		data2.put("year_month","201601");
		data2.put("tower_fee","2323");
		data2.put("lt_fee","33333fffffffffff");
		data2.put("ce","22");
		data2.put("style"," ubc ubt ubb c-gra1 ");
		dataList.add(data2);**/
		var param = new Wade.DataMap();
		param.put("dataList",dataList);
		Mobile.openTemplate("TowerHireFee",param);
	});
	$("#chart2").tap(function(){
		var dataList = new Wade.DatasetList();
		var xAxis_data = char2Data.get('xAxis_data').items;
		var series_data_tt = char2Data.get('series_data_tt').items;
		var series_data_lt = char2Data.get('series_data_lt').items;
		for(var i=0;i<xAxis_data.length;i++){
			data = new Wade.DataMap();
			data.put("year_month",xAxis_data[i]);
			data.put("tower_num",series_data_tt[i]);
			data.put("lt_num",series_data_lt[i]);
			var ce = parseFloat(series_data_tt[i])-parseFloat(series_data_lt[i]);
			data.put("ce",ce);
			if(i%2 == 0){
				data.put("style","");
			}else{
				data.put("style"," ubc ubt ubb c-gra1 ");
			}
			
			dataList.add(data);
		}
		
		var param = new Wade.DataMap();
		param.put("dataList",dataList);
		Mobile.openTemplate("TowerNum",param);
	});
	
	/*将session中的的数据填到“我的”页面中*/
	Common.get(function(data){
		data = new Wade.DataMap(data);
		
		//alert(data.get('departName'));
		
		$("#username").append(data.get('departName'));
		$("#staffId").append(data.get('staffId'));
		$("#areaName").append(data.get('areaName'));
		$("#departName").append(data.get('departName'));
		$("#phone").append(data.get('phone'));
	},'userinfo');
	/*修改用户信息*/
	$('#setuser').tap(function(){
		alert('敬请期待...');
	});
	/*修改密码*/
	$('#setpwd').tap(function(){
		alert('敬请期待...');
	});
	/*修改用户信息*/
	$('#setxx').tap(function(){
		alert('敬请期待...');
	});
	/*退出登录*/
	$('#signout').tap(function(){
		Common.logoutAccount();
	});
	
	
});
