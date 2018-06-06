require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	
	/*基站匹配率横竖滚动*/
	new iScroll('content1', {
	    bounce: false,
	    snap: false,
	    momentum: true,
	    hScrollbar: true,
	    hScroll: true,
	    vScrollbar: true,
	    vScroll: true,
	    checkDOMChanges: true,
	    onScrollEnd: function() {

	    }
	});
	
	new iScroll("BaseStationRate");
	
	setTimeout(function(){
		aaa();
	},100);
	
	function aaa(){
		
		Common.callSvc("AlarmBean.getAlarmTrueTime", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var daliy_active_alarm = new Array();
			var ordernum = new Array();
			var area_name = new Array();
			var orderalarm = new Array();
			for(var i = 0; i < list.length - 1; i++){
				ordernum.push(list.get(i).get('ordernum'));
				daliy_active_alarm.push(list.get(i).get('daliy_active_alarm'));
				area_name.push(list.get(i).get('area_name'));
				orderalarm.push(list.get(i).get('orderalarm'));
			}
			option = {
					tooltip: {
				        trigger: 'item'
				    },
				    legend: {
				        data:['当日实时活动告警数','当日工单数','工单告警比']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: area_name,
				            axisLabel:{
				            	rotate:45,
				            	interval:0
				            }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '数量',
				            axisLabel: {
				                formatter: '{value}'
				            },
				            axisLabel:{
				            	rotate:60,
				            }
				        },
				        {
				            type: 'value',
				            name: '工单告警比%',
				            min: 0,
				            axisLabel: {
				                formatter: '{value} %'
				            },
				            axisLabel:{
				            	rotate:-60,
				            
				            }
				        }
				    ],
				    series: [
				        {
				            name:'当日实时活动告警数',
				            type:'bar',
				            data:daliy_active_alarm
				        },
				        {
				            name:'当日工单数',
				            type:'bar',
				            data:ordernum
				        },
				        {
				            name:'工单告警比',
				            type:'line',
				            yAxisIndex: 1,
				            data:orderalarm
				        }
				    ]
				};
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	
	setTimeout(function(){
		aaa();
	},100);
	/*	
	//饼图
	function aaa(){
		
		Common.callSvc("TowerBean.getBaseStationRate", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var match_number = 0;
			var not_match_number = 0;
			for(var i = 0; i < list.length; i++){
				match_number += parseInt(list.get(i).get('match_number'));
				not_match_number += parseInt(list.get(i).get('not_match_number'));
			}
			
			option = {
					
				    tooltip: {
				        trigger: 'item',
				        triggerOn:'click'
				        
				    },
				    legend: {
				        data:['匹配','未匹配']
				    },
				    series: [
						{
						    name:'全省匹配率',
						    type:'pie',
						    avoidLabelOverlap: false,
						    label: {
						        normal: {
						            show: false,
						            position: 'center'
						        },
						        emphasis: {
						            show: true,
						            textStyle: {
						                fontSize: '30',
						                fontWeight: 'bold'
						            }
						        }
						    },
						    data:[
						        {value:match_number, name:'匹配'},
						        {value:not_match_number, name:'未匹配'}
						    ]
						}

				    ]
				};
			
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	*/
	
});