use std::f64::consts::E;

fn main(){

    let _a = 0.5_f64;
    let _x = [[0,0,1],[1,1,0],[0,0,0],[1,1,1],[1,0,1],[0,1,1]];
    let _answer = [0,1,0,1,1,1];
    let mut def_w = [0.15_f64,-0.15_f64,0.1_f64];
    let mut def_t = 0.2_f64;
    let mut t = def_t.clone();
    let mut w = def_w.clone();
    let mut avg_err = 0_f64;

    for _i in 0..2{

        println!("{0:<3} | {1:<7} || {2:<7} | {3:<7} | {4:<7} | {5:<7} | {6:<7} || {7:<7} | {8:<7} | {9:<7} | {10:<7}",
                                                                            "x", "f(x)", "in", "si(in)", "si(in)'", "error", "d_wi", "t", "w1", "w2","w3");
        for j in 0.._x.len(){

            let mut _in: f64 = 0.0;

            for k in 0.._x[j].len(){

                _in += (_x[j][k] as f64) * w[k];
            }

            _in += -t;

            let _out: f64 = sigmoid(_in);
            let _err: f64 = _answer[j] as f64 - _out;
            let mut delta_w: f64 = _a *  _err * (_out * (1.0 - _out));

            avg_err += _err.powf(2.0);

            for z in 0..w.len(){

                w[z] += delta_w * _x[j][z] as f64;
            }

            t -= delta_w;

            println!("{0:<03.2} | {1:<7.3} || {2:<7.3} | {3:<7.3} | {4:<7.3} | {5:<7.3} | {6:<7.3} || {7:<7.3} | {8:<7.3} | {9:<7.3} | {10:<7.3}", _x[j][0]*100+_x[j][1]*10+_x[j][2], _answer[j], _in, _out, (_out * (1.0 - _out)), _err, delta_w, t, w[0], w[1],w[2]);
        }
        
    avg_err /=  _x.len() as f64;

    println!("Average error:{:.3}|| Net Ajustments|t1:{:.3}|w1:{:.3}|w2:{:.3}|w3:{:.3}",avg_err,t-def_t,w[0]-def_w[0],w[1]-def_w[1],w[2]-def_w[2]);
    
    def_t = t;
    def_w = w.clone();
    avg_err = 0.0;
    }
}

fn sigmoid(x: f64) -> f64{
    return 1.0 / ( 1.0 + E.powf(-x));
}
