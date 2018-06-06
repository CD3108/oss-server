require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	/*增加滚动条*/
	new iScroll("content");
	
	
	setTimeout(function(){
		aaa();
	},100);
		
	function aaa(){
		
		Common.callSvc("TowerBean.getTowerImportResSuccess", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var total = new Array();
			var success_number = new Array();
			var city_name = new Array();
			var success_rate = new Array();
			for(var i = 0; i < list.length; i++){
				total.push(list.get(i).get('total'));
				success_number.push(list.get(i).get('success_number'));
				city_name.push(list.get(i).get('city_name'));
				success_rate.push(list.get(i).get('success_rate'));
			}
			
			option = {
					
				    tooltip: {
				        trigger: 'item',
				        triggerOn:'click'
				        
				    },
				    legend: {
				        data:['铁塔公司提供铁塔数','导入成功数','成功率']
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
				            name: '成功率%',
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
				            name:'铁塔公司提供铁塔数',
				            type:'bar',
							
				            data:total
				        },
				        {
				            name:'导入成功数',
				            type:'bar',

				            data:success_number
				        },
				        {
				            name:'成功率',
				            type:'line',
				            yAxisIndex: 1,
				            data:success_rate
				        }
				    ]
				};
			
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	
	
});