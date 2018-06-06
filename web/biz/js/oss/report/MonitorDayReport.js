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
	
	optionChart2 = {
			tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		        data:['工单数','回单数','回单率']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: [],
		            axisLabel:{
		            	rotate:45,
		            	interval:0
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '工单数',
		         

		            axisLabel: {
		                formatter: '{value}'
		            },
		            axisLabel:{
		            	rotate:60,
		            
		            }
		        },
		        {
		            type: 'value',
		            name: '回单率%',
		            min: 0,
		            max: 100,
		            interval: 20,
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
		            name:'工单数',
		            type:'bar',
		            stack:'haha',
		            data:[]
		        },
		        {
		            name:'回单数',
		            type:'bar',
		            stack:'haha',
		            data:[]
		        },
		        {
		            name:'回单率',
		            type:'line',
		            yAxisIndex: 1,
		            data:[]
		        }
		    ]
		};
		function aaa(){
				
				Common.callSvc("OrderBean.getMonitorDayReport", null, function(resultData){
					var list = resultData.get('dataList');
					var area_name = new Array();
					var ordernum = new Array();
					var repordernum = new Array();
					var receiptrate = new Array();
					for(var i = 0; i < list.length - 1; i++){
						area_name.push(list.get(i).get('area_name'));
						ordernum.push(list.get(i).get('ordernum'));
						repordernum.push(parseInt(list.get(i).get('ordernum') * list.get(i).get('receiptrate').replace('%','') / 100));
						receiptrate.push(list.get(i).get('receiptrate').replace('%',''));
					}
					optionChart2.xAxis[0].data = area_name;
					optionChart2.series[0].data = ordernum;
					optionChart2.series[1].data = repordernum;
					optionChart2.series[2].data = receiptrate;
					var chart2 = ec.init(document.getElementById('je')); 
					chart2.setOption(optionChart2);
					
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