package com.example.concepts.ProgrammingParadigms.FunctionalProgramming

fun myFunction(num1 : Int,num2 : Int,num3 :Int) : Int
{
    return (num1+num2+num3)/3
}


// how to pass func as arg in higher order function - NameofFunctionParameter : (type of arg1,type of arg2...) -> function return type

fun HighOrder(num1:Int,num2:Int,num3:Int,funcName: (Int,Int,Int) -> Int)
{
    println(funcName(num1,num2,num3))
}

fun main()
{
    HighOrder(50,80,40,::myFunction)     // :: NameofFunction which is going to be passed a parameter

    // in this one , instead of passing myFunction , we are creating and passing a lamda function

    // structure of lambda functions -- { functions paramters  -> function body }

    HighOrder(50,80,40 ,{ num1: Int, num2: Int, num3: Int -> (num1 + num2 + num3) / 3 })
}

