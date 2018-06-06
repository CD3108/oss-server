require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	/*增加滚动条*/
	new iScroll("content");
	
	
	setTimeout(function(){
		aaa();
	},100);
		
	function aaa(){
		
		Common.callSvc("ResBean.getNetworkRing", null, function(resultData){
			
			
			var list = resultData.get('dataList');
			var SDH3 = new Array();
			var BTS3 = new Array();
			var NODEB3 = new Array();
			var area_name = new Array();
			
			for(var i = 0; i < list.length; i++){
				SDH3.push(list.get(i).get('SDH3').replace('%',''));
				BTS3.push(list.get(i).get('BTS3').replace('%',''));
				area_name.push(list.get(i).get('area_name'));
				NODEB3.push(list.get(i).get('NODEB3').replace('%',''));
			}
			option = {
					tooltip: {
				        trigger: 'item',
				        triggerOn:'click'
				        
				    },
				    legend: {
				    	data:['SDH成环率','2G成环率','3G成环率','4G成环率']
				    },
				    xAxis:  {
				        type: 'category',
				        data: area_name,
			            axisLabel:{
			            	rotate:45,
			            	interval:0
			            }
				    },
				    yAxis: {
				        type: 'value',
				        name: '成环率',
				        min: 0,
				        axisLabel: {
				            formatter: '{value} %',
				            rotate:60,
				        }
				    },
				    series: [
						        {
						            name:'SDH成环率',
						            type:'line',
						            data:SDH3
						        },
						        {
						            name:'2G成环率',
						            type:'line',
						            data:BTS3
						        },
						        {
						            name:'3G成环率',
						            type:'line',
						            data:NODEB3
						        },
//						        {
//						            name:'4G成环率',
//						            type:'line',
//						            data:[100,100,100,100,100,100,100,100,100,100,100,100]
//						        }
						    ]
				};
			
			var chart2 = ec.init(document.getElementById('je')); 
			chart2.setOption(option);
			 
		});
		
	}
	
	
});