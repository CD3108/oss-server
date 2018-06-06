require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	
	new iScroll("content");
	
	
	optionChart2 = {
			tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		        data:['总数','匹配数','匹配率']
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
		            name:'总数',
		            type:'bar',
		            data:[]
		        },
		        {
		            name:'匹配数',
		            type:'bar',
		            data:[]
		        },
		        {
		            name:'匹配率',
		            type:'line',
		            yAxisIndex: 1,
		            data:[]
		        }
		    ]
		};
		function aaa(){
				
				Common.callSvc("TowerBean.getResourceAdapter", null, function(resultData){
					
					
					var list = resultData.get('dataList');
					var total = new Array();
					var match_number = new Array();
					var city_name = new Array();
					var match_rate = new Array();
					for(var i = 0; i < list.length; i++){
						total.push(list.get(i).get('total'));
						match_number.push(list.get(i).get('match_number'));
						city_name.push(list.get(i).get('city_name'));
						match_rate.push(list.get(i).get('match_rate'));
					}
					optionChart2.xAxis[0].data = city_name;
					optionChart2.series[0].data = total;
					optionChart2.series[1].data = match_number;
					optionChart2.series[2].data = match_rate;
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
		
		Common.callSvc("TowerBean.getResourceAdapter", null, function(resultData){
			
			
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