require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	/*增加滚动条*/
	new iScroll("content");
	
	optionChart2 = {
			tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		        data:['产品总数','匹配数','匹配率']
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
		            name:'产品总数',
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
	
	
	
	/**
	 * 查询铁塔费用及数量
	 */
	function aaa(){
		Common.callSvc("TowerBean.getTowerProductRate", null, function(resultData){
			var list = resultData.get('dataList');
			var tt_total = new Array();
			var match_number = new Array();
			var city_name = new Array();
			var match_rate = new Array();
			for(var i = 0; i < list.length; i++){
				tt_total.push(list.get(i).get('tt_total'));
				match_number.push(list.get(i).get('match_number'));
				city_name.push(list.get(i).get('city_name'));
				match_rate.push(list.get(i).get('match_rate'));
			}
			optionChart2.xAxis[0].data = city_name;
			optionChart2.series[0].data = tt_total;
			optionChart2.series[1].data = match_number;
			optionChart2.series[2].data = match_rate;
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(optionChart2);
			 
		});
	}
	
	
	
	setTimeout(function(){
		aaa();
	},100);
		

	
	
});