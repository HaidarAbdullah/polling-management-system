<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PollingRoom extends Model
{
    use HasFactory;
    protected $table = "pollingrooms";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['room_identifier','user_id','open_time','close_time','title','is_conditional','condition_type','condition_desc'];
    public $timestamps=false;
}
