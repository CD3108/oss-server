require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	
	/*基站匹配率横竖滚动*/
	var iscroll1 = new iScroll('BaseStationRate', {
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
				
				Common.callSvc("TowerBean.getBaseStationRate", null, function(resultData){
					
					
					var list = resultData.get('dataList');
					var total = new Array();
					var match_number = new Array();
					var city_name = new Array();
					var match_rate = new Array();
					var flag = 0,num = 0,pipei = 0;
					for(var i = 0; i < list.length; i++){
//						total.push(list.get(i).get('total'));
//						match_number.push(list.get(i).get('match_number'));
//						city_name.push(list.get(i).get('city_name'));
//						match_rate.push(list.get(i).get('match_rate'));
						
						flag++;
						num += parseInt(list.get(i).get('total'));
						pipei += parseInt(list.get(i).get('match_number'));
						
						if(flag == 5){
							flag = 0;
							total.push(num);
							match_number.push(pipei);
							city_name.push(list.get(i).get('city_name'));
							match_rate.push(((pipei / num) * 100).toFixed(2));
							num = 0;pipei = 0;
						}
						
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