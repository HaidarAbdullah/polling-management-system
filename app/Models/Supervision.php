<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Supervision extends Model
{
    use HasFactory;
    protected $table = "supervisions";      ### Rename table name if you want , it depends on your local DataBase ###
    protected $fillable=['user_id','room_id'];
    public $timestamps=false;
}
