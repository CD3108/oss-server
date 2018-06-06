require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	new iScroll("content");
	
	
	
	optionChart2 = {
		    tooltip: {
		        trigger: 'item'
		    },
		    toolbox: {
		        feature: {
		            dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:['匹配天线','未匹配天线','匹配率']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '天线数',
		         

		            axisLabel: {
		                formatter: '{value} 根'
		            }
		        },
		        {
		            type: 'value',
		            name: '匹配率',
		            min: 0,
		            max: 100,
		            interval: 20,
		            axisLabel: {
		                formatter: '{value} %'
		            }
		        }
		    ],
		    series: [
		        {
		            name:'匹配天线',
		            type:'bar',
		            barWidth:15,
		            barGap:3,
		            stack: '广告',
		            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
		        },
		        {
		            name:'未匹配天线',
		            type:'bar',
					barWidth:20,
		            stack: '广告',
		            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
		        },
		        {
		            name:'匹配率',
		            type:'line',
		            yAxisIndex: 1,
		            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
		        }
		    ]
		};
	
	
	
	
	
	
	setTimeout(function(){
		getTowerInfo();
	},100);
	
	
	/**
	 * 查询铁塔费用及数量
	 */
	function getTowerInfo(){
		Common.callSvc("AlarmBean.getTowerNumAll", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			var tower = resultData.get("list");
			optionChart2.xAxis.data = tower.get('city_nameList').items;
			optionChart2.series[0].data = tower.get('match_numberList').items;
			optionChart2.series[1].data = tower.get('not_match_numberList').items;
			optionChart2.series[2].data = tower.get('match_rateList').items;
			var chart2 = ec.init(document.getElementById('chart2')); 
			chart2.setOption(optionChart2);
			 
		});
	}
	
});