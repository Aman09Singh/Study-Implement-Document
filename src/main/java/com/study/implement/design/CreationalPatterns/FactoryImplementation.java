package com.study.implement.design.CreationalPatterns;

public class FactoryImplementation {

    public interface Product {
        void doSomething();
    }

    public static class ProductA implements  Product{
        @Override
        public void doSomething(){
            System.out.println("Product A implementation");
        }
    }

    public static class ProductB implements Product{

        @Override
        public void doSomething(){
            System.out.println("Product B implementation");
        }
    }

    public static class FactoryClass{

        public enum ProductType{
            A,B
        }

        public static Product createProduct(ProductType type){

            switch(type){
                case A -> {
                    return new ProductA();
                }
                case B -> {
                    return new ProductB();
                }
                default -> throw new IllegalArgumentException();
            }
        }

    }

    public static void main(String[] args) {

        Product productA = FactoryClass.createProduct(FactoryClass.ProductType.A);
        productA.doSomething();
        Product productB = FactoryClass.createProduct(FactoryClass.ProductType.B);
        productB.doSomething();
    }

}
