var userOption;

function initUserOption(found,unique,total) {
	userOption = {
		    title: {
		        text: '用户数据',
		        left: 'center',
		        top: 20,
		    },

		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },

		    series : [
		        {
		            name:'数据成分',
		            type:'pie',
		            radius :[0, '55%'],
		            center: ['50%', '50%'],
		            data:[
		                {value:found-unique, name:'共有发现缺陷'},
		                {value:unique, name:'独有发现缺陷'},
		                {value:total-found, name:'预估未发现缺陷'}
		            ].sort(function (a, b) { return a.value - b.value}),
		        
		        }
		    ]
		};
}

