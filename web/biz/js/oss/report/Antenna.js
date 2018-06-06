require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	new iScroll("content");
	
	
	
	optionChart2 = {
			tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		        data:['匹配天线','未匹配天线','匹配率']
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
		            name: '天线数',
		         

		            axisLabel: {
		                formatter: '{value} 根'
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
		            name:'匹配天线',
		            type:'bar',
		            data:[]
		        },
		        {
		            name:'未匹配天线',
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
	
	
	
	
	
	
	setTimeout(function(){
		getAntennaInfo();
	},100);
	
	
	/**
	 * 查询铁塔费用及数量
	 */
	function getAntennaInfo(){
		Common.callSvc("AntennaBean.getAntennaNumAll", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			var tower = resultData.get("list");
			optionChart2.xAxis[0].data = tower.get('city_nameList').items;
			optionChart2.series[0].data = tower.get('match_numberList').items;
			optionChart2.series[1].data = tower.get('not_match_numberList').items;
			optionChart2.series[2].data = tower.get('match_rateList').items;
			var chart2 = ec.init(document.getElementById('chart2')); 
			chart2.setOption(optionChart2);
			 
		});
	}
	
});