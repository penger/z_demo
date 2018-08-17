package com.base.designpattern.strategy;
/**
 * 将每一个方法和行为封装成一个类，符合单一职责原则
 * 避免使用多个条件转换语句，将选择的算法和行为逻辑区分开
 * @author gongp
 *
 */
public class StrategyTest {

	public static void main(String[] args) {
		Strategy strategya  = new StrategyA();
		Strategy strategyb  = new StrategyB();
		Strategy strategyc  = new StrategyC();

		Context context = new Context(strategyc);
		context.doit();
	}

}

interface Strategy {
	public void strategyMethod (String str);
}

class StrategyA implements Strategy{

	@Override
	public void strategyMethod(String x) {
		System.out.println("A");
	}
	
}

class StrategyB implements Strategy{

	@Override
	public void strategyMethod(String x) {
		System.out.println("B");
	}
	
}

class StrategyC implements Strategy{

	@Override
	public void strategyMethod(String x) {
		System.out.println("C");
	}
	
}

class Context{
	
	private Strategy strategy;
	

	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void doit() {
		strategy.strategyMethod("gugugu");
	}
	
}