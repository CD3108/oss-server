require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	/*增加滚动条*/
	new iScroll("content");
	
	
	setTimeout(function(){
		aaa();
	},100);
		
	function aaa(){
		
		Common.callSvc("TowerBean.getTowerFeesRate", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var co_total_6 = new Array();
			var cuc_total_6 = new Array();
			var city_name = new Array();
			var match_rate = new Array();
			for(var i = 0; i < list.length; i++){
				co_total_6.push(list.get(i).get('co_total_6'));
				cuc_total_6.push(list.get(i).get('cuc_total_6'));
				city_name.push(list.get(i).get('city_name'));
				match_rate.push(list.get(i).get('match_rate'));
			}
			
			option = {
					
				    tooltip: {
				        trigger: 'item',
				        triggerOn:'click'
				        
				    },
				    legend: {
				        data:['联通提供费用','铁塔提供费用','匹配率']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: city_name
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '费用(万元)',
				            min: 0,
				            axisLabel: {
				                formatter: '{value}'
				            },
				            axisLabel:{
				            	rotate:60,
				            
				            }
				        },
				        {
				            type: 'value',
				            name: '匹配率%',
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
				            name:'铁塔提供费用',
				            type:'bar',
							
				            data:co_total_6
				        },
				        {
				            name:'联通提供费用',
				            type:'bar',

				            data:cuc_total_6
				        },
				        {
				            name:'匹配率',
				            type:'line',
				            yAxisIndex: 1,
				            data:match_rate
				        }
				    ]
				};
			
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	
	
});