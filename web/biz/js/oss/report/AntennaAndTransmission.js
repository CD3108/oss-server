require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	/*增加滚动条*/
	new iScroll("content");
	
	
	setTimeout(function(){
		aaa();
	},100);
		
	function aaa(){
		
		Common.callSvc("ResBean.getAntennaAndTransmission", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var v_all_wx = new Array();
			var v_Match_wx = new Array();
			var city_name = new Array();
			var v_rate_wx = new Array();
			for(var i = 0; i < list.length; i++){
				v_all_wx.push(list.get(i).get('v_all_wx'));
				v_Match_wx.push(list.get(i).get('v_Match_wx'));
				city_name.push(list.get(i).get('city_name'));
				v_rate_wx.push(list.get(i).get('v_rate_wx').replace('%',''));
			}
			
			option = {
					
				    tooltip: {
				        trigger: 'item',
				        triggerOn:'click'
				        
				    },
				    legend: {
				        data:['无线资源总数','无线与传输匹配数','匹配率']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: city_name,
				            axisLabel:{
				            	rotate:45,
				            	interval:0
				            }
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
				            name:'无线资源总数',
				            type:'bar',
							stack:'luoqilai',
				            data:v_all_wx
				        },
				        {
				            name:'无线与传输匹配数',
				            type:'bar',
				            stack:'luoqilai',
				            data:v_Match_wx
				        },
				        {
				            name:'匹配率',
				            type:'line',
				            yAxisIndex: 1,
				            data:v_rate_wx
				        }
				    ]
				};
			
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	
	
});