<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pollingbox extends Model
{
    protected $table = "pollingboxes";
    protected $fillable=['room_id','body','option1','option2','option3','option4','option5'];
    public $timestamps=false;






    #################### Relations Begin  ######################
    
    public function pollingroom(){
        return $this -> belongsTo('App\Models\PollingRoom','room_id');
     }
    

    public function choice(){
        return $this -> hasOne('App\Models\Choice','box_id');
    }





    ###################    Relations End   #####################
}
