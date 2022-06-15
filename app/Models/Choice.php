<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Choice extends Model
{
    protected $table = "choices";
    protected $fillable=['box_id','option1','option2','option3','option4','option5'];
    public $timestamps=false;






    
    #################### Relations Begin  ######################
    
    public function pollingbox(){
        return $this -> belongsTo('App\Models\PollingBox','box_id');
     }
 
 
 
 
     ###################    Relations End   #####################
}
