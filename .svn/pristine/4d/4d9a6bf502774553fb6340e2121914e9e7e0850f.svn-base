require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	new iScroll("content");
	
	var char1Data,char2Data;
	var optionChart1 = {
		    title: {
		        text: '前6月租用费',
		        subtext: '万元'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    grid:{
		    	left:'15%',
		    	width:'80%'
		    },
		    legend: {
		    	x:'center',
		        data:['铁塔公司','联通公司']
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        axisLabel:{
	            	rotate:45,
	            	interval:0
	            },
		        data: ['一月','二月','三月','四月','五月','六月']
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value}'
		        }
		    },
		    backgroundColor:'#ffffff',
		    color:['#5091d1','#fba946', '#74b338'],
		    series: [
		        {
		            name:'铁塔公司',
		            type:'line',
		            data:[11, 11, 15, 13, 12, 13],
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值',symbolSize:80}
		                ]
		            }
		        },
		        {
		            name:'联通公司',
		            type:'line',
		            data:[5, 10, 8, 14, 16, 14],
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值',symbolSize:80}
		                ]
		            }
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
		Common.callSvc("TowerBean.getMainPageInfos", null, function(resultData){
			if(typeof(resultData) == "string" ){
				resultData = new Wade.DataMap(resultData);
			}
			var fee = resultData.get("fee");
			var tower = resultData.get("tower");
			optionChart1.xAxis.data = fee.get('xAxis_data').items;
			optionChart1.series[0].data = fee.get('series_data_tt').items;
			optionChart1.series[1].data = fee.get('series_data_lt').items;
			var chart1 = ec.init(document.getElementById('chart1')); 
			chart1.setOption(optionChart1);
			
		});
	}
	
});