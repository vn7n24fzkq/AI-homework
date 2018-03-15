use std::f64::consts::E;
use std::io;

fn main(){

    let _a = 0.5_f64;
    let _x = [[0,0,1],[1,1,0],[0,0,0],[1,1,1],[1,0,1],[0,1,1]];
    let _answer = [0,1,0,1,1,1];
    let mut def_w = [0.15_f64,-0.15_f64,0.1_f64];
    let mut def_t = 0.2_f64;
    let mut t = def_t.clone();
    let mut w = def_w.clone();
    let mut avg_err = 0_f64;

    println!("Enter training times");
    let mut input_times = String::new();
    io::stdin()
        .read_line(&mut input_times)
        .expect("failed to read from stdin");

    let times: i32= input_times.trim().parse().unwrap();

    for _i in 0..times{
        println!("This is {} generation",_i+1);
        println!("{0:<3} | {1:<7} || {2:<7} | {3:<7} | {4:<7} | {5:<7} | {6:<7} || {7:<7} | {8:<7} | {9:<7} | {10:<7}",
                                                                            "x", "f(x)", "in", "si(in)", "si(in)'", "error", "d_wi", "t", "w1", "w2","w3");
        for j in 0.._x.len(){
            avg_err += train(_x[j],_answer[j],_a,&mut t,&mut w);         
        }
        
    avg_err /=  _x.len() as f64;

    println!("Average error:{:.3}|| Net Ajustments|t1:{:.3}|w1:{:.3}|w2:{:.3}|w3:{:.3}",avg_err,t-def_t,w[0]-def_w[0],w[1]-def_w[1],w[2]-def_w[2]);
    
    def_t = t;
    def_w = w.clone();
    avg_err = 0.0;
    }

    let mut input_x = String::new();

    println!("Enter test data. e.g. 110");
    io::stdin()
        .read_line(&mut input_x)
        .expect("failed to read from stdin");
    let test_data: i32 = input_x.trim().parse().unwrap();
    let input_arr = [test_data % 100, test_data % 10 ,test_data % 1];
    println!("x is {}{}{} , sigma(in) is {:.3}",input_arr[0],input_arr[1],input_arr[2],predict(input_arr,&mut t,&mut w));
}

fn train(input: [i32; 3],ans: i32,_a: f64,t: &mut f64,w: &mut [f64; 3]) -> f64{
    let mut _in: f64 = 0.0;

    for i in 0..input.len(){
        _in += (input[i] as f64) * &w[i];
    }

    _in -= *t;

    let _out: f64 = sigmoid(_in);
    let _err: f64 = ans as f64 - _out;
    let delta_w: f64 = _a * _err * (_out * (1.0 - _out));

    for j in 0..w.len(){
        w[j] += delta_w * input[j] as f64;
    }

    *t -= delta_w;

    println!("{0:<03.2} | {1:<7.3} || {2:<7.3} | {3:<7.3} | {4:<7.3} | {5:<7.3} | {6:<7.3} || {7:<7.3} | {8:<7.3} | {9:<7.3} | {10:<7.3}", input[0]*100+input[1]*10+input[2], ans, _in, _out, (_out * (1.0 - _out)), _err, delta_w, t, w[0], w[1], w[2]);
    return _err.powf(2.0);
}

fn predict(input: [i32; 3],t: &mut f64,w: &mut [f64; 3]) -> f64{
    let mut _in: f64 = 0.0;

    for i in 0..input.len(){
        _in += (input[i] as f64) * &w[i];
    }

    _in -= *t;

    return sigmoid(_in);
}

fn sigmoid(x: f64) -> f64{
    return 1.0 / ( 1.0 + E.powf(-x));
}
