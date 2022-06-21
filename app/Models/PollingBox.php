<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PollingBox extends Model
{
    use HasFactory;
    protected $table = "pollingboxes";      // Rename table name if you want , it depends on your local DataBase
    protected $fillable=['room_id','body','option1','option2','option3','option4','option5'];
    public $timestamps=false;
    
    ###################### Relations Begin  ######################
    
    public function pollingroom(){
        return $this -> belongsTo('App\Models\PollingRoom','room_id');
    }
    

    public function choice(){
        return $this -> hasOne('App\Models\Choice','box_id');
    }

    ###################### Relations End  ######################    
}
