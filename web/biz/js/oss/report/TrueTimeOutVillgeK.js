require(["domReady!","common","util","mobile","echarts3"], function(doc,Common,util,Mobile,ec) {
	
	function splitData(rawData) {
	    var categoryData = [];
	    var values = []
	    for (var i = 0; i < rawData.length; i++) {
	        categoryData.push(rawData[i].splice(0, 1)[0]);
	        values.push(rawData[i])
	    }
	    return {
	        categoryData: categoryData,
	        values: values
	    };
	}
	
	
	new iScroll("BaseStationRate");
	
	// 数据意义：开盘(open)1，收盘(close)2，最低(lowest)3，最高(highest)4
	
	option0 = {
			   
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'line'
		        },
		        formatter:function(params, ticket, callback){
		        	console.log(params);
		            var result = params[0].seriesName + "<br>"
		            + params[0].name + "<br>"
		            + "日均值:"+params[0].value[0]+"<br>"
		            + "当前值"+params[0].value[1] + "<br>"
		            + "最低值:"+params[0].value[2] + "<br>"
		            + "最高值:"+params[0].value[3] + "<br>";
		            return result;
		        },
		    },
		    legend: {
		        data: ['退服小区个数']
		    },
		    xAxis: {
		        type: 'category',
		        data: [],
		        axisLabel:{
	            	rotate:45,
	            	interval:0
	            }
		    },
		    yAxis: {
		    	type: 'value',
   		    	max: 100000,
		    	axisLabel: {
	                formatter: '{value}'
	            },
	            axisLabel:{
	            	rotate:60,
	            
	            }
		    },
		    series: [
		        {
		            name: '退服小区个数',
		            type: 'candlestick',
		            data: [],
		        }
		    ]
		};
	
		/*-------------------*/

       	option1 = {
       			   
       		    tooltip: {
       		        trigger: 'axis',
       		        axisPointer: {
       		            type: 'line'
       		        },
       		        formatter:function(params, ticket, callback){
       		        	console.log(params);
       		            var result = params[0].seriesName + "<br>"
       		            + params[0].name + "<br>"
       		            + "日均值:"+params[0].value[0]+"<br>"
       		            + "当前值"+params[0].value[1] + "<br>"
       		            + "最低值:"+params[0].value[2] + "<br>"
       		            + "最高值:"+params[0].value[3] + "<br>";
       		            return result;
       		        },
       		    },
       		    legend: {
       		        data: ['退服小区次数']
       		    },
       		    xAxis: {
       		        type: 'category',
       		        data: [],
       		        axisLabel:{
       	            	rotate:45,
       	            	interval:0
       	            }
       		    },
       		    yAxis: {
       		    	type: 'value',
       		    	max: 100000,
       		    	axisLabel: {
       	                formatter: '{value}'
       	            },
       	            axisLabel:{
       	            	rotate:60,
       	            
       	            }
       		    },
       		    series: [
       		        {
       		            name: '退服小区次数',
       		            type: 'candlestick',
       		            data: [],
       		        }
       		    ]
       		};
       	
       	/*-------------------*/

       	option2 = {
       			   
       		    tooltip: {
       		        trigger: 'axis',
       		        axisPointer: {
       		            type: 'line'
       		        },
       		        formatter:function(params, ticket, callback){
       		        	console.log(params);
       		            var result = params[0].seriesName + "<br>"
       		            + params[0].name + "<br>"
       		            + "日均值:"+params[0].value[0]+"<br>"
       		            + "当前值"+params[0].value[1] + "<br>"
       		            + "最低值:"+params[0].value[2] + "<br>"
       		            + "最高值:"+params[0].value[3] + "<br>";
       		            return result;
       		        },
       		    },
       		    legend: {
       		        data: ['退服小区时长']
       		    },
       		    xAxis: {
       		        type: 'category',
       		        data: [],
       		        axisLabel:{
       	            	rotate:45,
       	            	interval:0
       	            }
       		    },
       		    yAxis: {
       		    	type: 'value',
       		    	max: 100000,
       		    	axisLabel: {
       	                formatter: '{value}'
       	            },
       	            axisLabel:{
       	            	rotate:60,
       	            
       	            }
       		    },
       		    series: [
       		        {
       		            name: '退服小区时长',
       		            type: 'candlestick',
       		            data: [],
       		        }
       		    ]
       		};
	
		function aaa(){
				Common.callSvc("AlarmBean.getTrueTimeOutVillge", null, function(resultData){
					var list = resultData.get('dataList');
					var city_name = new Array();
					var RiJunNumber = new Array();
					var RiJunFrequency = new Array();
					var dayRiJunSC = new Array();
					for(var i = 0; i < list.length; i++){
						city_name.push(list.get(i).get('city_name'));
						
						var RiJunNumber_ = new Array();
						RiJunNumber_.push(list.get(i).get('RiJunNumber'));
						RiJunNumber_.push(list.get(i).get('todayNumber'));
						RiJunNumber_.push(list.get(i).get('nearly30DaysMinNumber'));
						RiJunNumber_.push(list.get(i).get('nearly30DaysMaxNumber'));
						RiJunNumber.push(RiJunNumber_);
						
						var RiJunFrequency_ = new Array();
						RiJunFrequency_.push(list.get(i).get('RiJunFrequency'));
						RiJunFrequency_.push(list.get(i).get('todayFrequency'));
						RiJunFrequency_.push(list.get(i).get('nearly30DaysMinFrequency'));
						RiJunFrequency_.push(list.get(i).get('nearly30DaysMaxFrequency'));
						RiJunFrequency.push(RiJunFrequency_);
						
						var dayRiJunSC_ = new Array();
						dayRiJunSC_.push(list.get(i).get('dayRiJunSC'));
						dayRiJunSC_.push(list.get(i).get('daytodaySC'));
						dayRiJunSC_.push(list.get(i).get('nearly30DaysMinSC_'));
						dayRiJunSC_.push(list.get(i).get('nearly30DaysMaxSC_'));
						dayRiJunSC.push(dayRiJunSC_);
						
					}
					
					option0.xAxis.data = city_name;
					option1.xAxis.data = city_name;
					option2.xAxis.data = city_name;
					
					option0.series[0].data = RiJunNumber;
					option1.series[0].data = RiJunFrequency;
					option2.series[0].data = dayRiJunSC;
					
					var chart2 = ec.init(document.getElementById('je2')); 
					chart2.setOption(option2);
					
					var chart0 = ec.init(document.getElementById('je0')); 
					chart0.setOption(option0);
					
					var chart1 = ec.init(document.getElementById('je1')); 
					chart1.setOption(option1);
					
				});
		}
	
	setTimeout(function(){
		aaa();
	},100);
	
});