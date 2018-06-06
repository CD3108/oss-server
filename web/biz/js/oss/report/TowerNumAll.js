require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	new iScroll("content");
	
	var char1Data,char2Data;
	var optionChart2 = {
			   title: {
			        text: '计费铁塔总数对比'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			    	x:'right',
			        data:['铁塔公司','联通公司']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisLabel:{
			            	rotate:45,
			            	interval:0
			            },
			            
			            data: ['太原','大同','阳泉','长治','晋城','朔州','晋中','运城','忻州','临汾','吕梁']
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '铁塔数量',
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    grid:{
			    	left:'15%',
			    	width:'80%'
			    },
			    backgroundColor:'#ffffff',
			    color:['#5091d1','#74b338', '#fba946'],
			    series: [
			        {
			            name:'铁塔公司',
			            type:'bar',
			            barGap:'-0.1',
			            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4]
			        },
			        {
			            name:'联通公司',
			            type:'bar',
			            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0]
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
			var tower = resultData.get("tower");
			optionChart2.xAxis.data = tower.get('xAxis_data').items;
			optionChart2.series[0].data = tower.get('series_data_tt').items;
			optionChart2.series[1].data = tower.get('series_data_lt').items;
			var chart2 = ec.init(document.getElementById('chart2')); 
			chart2.setOption(optionChart2);
			 
		});
	}
	
});