<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PollingInvitation extends Model
{
    use HasFactory;
    protected $table = "pollinginvitations";      // Rename table name if you want , it depends on your local DataBase
    protected $fillable=['room_id','user_id','availability','confirmation'];
    public $timestamps=false;

    ###################### Relations Begin  ######################
    
    public function pollingroom(){
        return $this -> belongsTo('App\Models\PollingRoom','room_id');
    }


    public function user(){
        return $this -> belongsTo('App\User','user_id');
    }
    
    ###################### Relations End  ######################    
}
